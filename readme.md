
#Spring Cloud Config 的个人示例

这是根据 Spring 微服务实战(Spring Microservices In Action) 一书的个人练习代码.
但是书中的 Spring Boot Spring Cloud 的相关代码有点陈旧(也可以说 Spring 的迭代迅速),
因此在练习时需要花费额外的精力去将旧版本的代码迁移到新版本中 -- 但这种精力的花费是值得的,
比较在示例性质的项目中还是使用新特性较好.

微服务配置管理的原则 (书上提倡的原则,但个人作为微服务的初学者未能理解,但不妨碍记录) :

- 分离 -- 将服务配置信息与服务的实际物理部署完全分离.应用程序配置不应该与服务实例一起部署.
相反,配置信息应该作为环境变量传递给正在启动的服务,或者在服务启动时从集中式存储库中读取.（个人理解：单体服务的配置文件可以不遵循此概念。
但是微服务系统中会有大量的微服务，若不遵循此概念则配置文件会散落在不同的微服务中，难以管理）

- 抽象 -- 将访问配置数据的功能抽象到一个服务接口中.应用程序使用基于 REST 的 JSON 服务来
检索配置数据,而不是编写直接访问服务存储库的代码(也就是从文件或使用 JDBC 从数据库中读取数据)

- 集中 -- 因为基于云的应用程序可能会有数百个微服务,所以最小化用于保存配置信息的不同存储库
的数量至关重要。将应用程序配置集中在尽可能少的存储库中。

- 稳定 -- 因为应用程序的配置信息与部署的服务完全隔离并集中存放，所以不管采用何种方案实现，
至关重要的一点就是保住其高可用和冗余

书的作者的提议 ： 将配置信息与实际代码分开之后，开发人员将创建一个需要进行管理和版本控制的外部依赖项。
管理不当的应用配置数据需要追踪和版本控制，因为管理不当的应用程序配置很容易滋生难以检查的bug 和计划
外的中断。（个人理解：微服务的配置文件需要独立的版本控制来进行管理。因为配置一旦出错是致命却又难以察觉的）



## 构建配置服务器 1.0: 将微服务的配置文件与项目代码库一同存储

Spring Cloud Config 用来集中管理微服务实例的配置信息.
它分为两个部分 : spring-cloud-config-server 与 spring-cloud-starter-config

spring-cloud-config-service 用于构建配置服务器，集中管理微服务的配置信息

spring-cloud-starter-config 引导微服务从配置服务器中获取配置



## 构建配置服务器 2.0 : 通过 git 维护配置文件

若微服务的数量较少，将 微服务的配置文件与项目的代码库一同存储时可行的。但微服务的
数量太多，需要对微服务的配置文件进行单独的管理 -- 例如通过 git 进行版本控制和维护。
因为配置文件的数据出现错误是致命却又不易察觉的。

具体的做法：
- 构建 git 项目，用于微服务的配置文件的管理 (这里就不详细展开，可以通过 url 查看)

- 修改 service_demo项目(配置服务器)的 yml

```yaml
server:
  port: 8888

# 使用 git 作为 配置管理服务
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/zzkskys/config-repo
          searchPaths: licensingservice
```




# 使用 Spring Cloud 配置服务器刷新属性

**通过 @RefreshScope 刷新属性**

Spring Boot 应用程序只会在启动时读取它们的属性，因此 Spring Cloud 配置服务器
进行的属性不会被 Spring Boot 应用程序自动获取。 Spring Boot Actuator 提供了
@RefreshScope 注解，允许开发团队访问 /actuator/refresh 端点(请求方法为 Post)，强制 Spring
Boot 应用程序读取应用配置。

例如:
```java
@RestController
@RefreshScope
@RequestMapping("/license")
public class LicenseController {

    @Value("${example.property}")
    private String description;


    @GetMapping("/show")
    public String getDescription() {
        return description;
    }
}
```


这时 刷新还是不成功的。因为 还需要将 Actuator 的部分信息暴露:

bootstrap.yml 的配置:
```yaml
spring:
  application:
    name: licensingservice
  profiles:
    active: default
  cloud:
    config:
      uri: http://localhost:8888
management:
  endpoints:
    web:
      exposure:
        include: refresh,health
``` 