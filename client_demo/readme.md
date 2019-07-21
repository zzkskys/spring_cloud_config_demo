
# 构建微服务配置的引导类


## 依赖

```xml
   <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    </dependencies>
```

##  bootsrap.yml 的配置
```yaml
spring:
  application:
    name: licensingservice # 使用 licensingservice 微服务的配置文件
  profiles:
    active: default #配置环境为默认
  cloud:
    config:
      uri: http://localhost:8888 #访问配置文件的端口
```

