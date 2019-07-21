# 配置服务器的构建

## 依赖

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-config-server</artifactId>
    </dependency>
</dependencies>
```

## 启动
使用 @EnableConfigService 注解,使得该项目成为 Spring Config 服务 -- 存储或读取微服务的配置

```java
@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServiceDemo {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigServiceDemo.class, args);
    }
}
```



## 构建配置文件

在 service_demo/src/main/resources/ 目录下创建 config/应用名/ 目录,不同的应用名目录
下存储不同应用的配置文件，然后创建不同的配置文件（配置文件的命名约定: 应用名称-环境名称.yml）.

例如配置文件 licensingservice.yml 的配置文件的内容 :
```yaml
spring:
  datasource:
    url: jdbc:postgresql://127.0.0.1:5432/demo
    username: postgres
    password: postgres
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

example:
  property: "I AM IN THE DEFAULT"
```
---

**yaml 的配置**

```yaml
server:
  port: 8888


# 使用 本地的文件配置系统
spring:
  profiles:
    active: native # 启用自定义的配置管理
  cloud:
    config:
      server:
        native:
          searchLocations: classpath:config/,classpath:config/licensingservice # 配置的搜索路径

```

---

这时，可以通过 http://localhost:8888/licensingservice/default 来访问
licensingservice.yml 的配置文件
