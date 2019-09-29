package cn.zzk.secured;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 使用 @EnableResourceService 告诉 Spring Cloud 和 Spring Security 该服务是受保护资源。
 * 它强制执行一个过滤器，该过滤器会拦截对该服务的所有传入调用，检查传入调用的 HTTP header 中是否存在
 * OAuth2 访问令牌，然后调用 security.oauth2.resource.userInfoUri 中定义的回调URL 来查看令牌是否有效。
 * 一旦获悉令牌有效的， @EnableResourceServer 注解也会应用访问控制规则，以控制什么人可以访问服务。
 */
@SpringBootApplication
@EnableResourceServer
@EnableCircuitBreaker
public class SecuredDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecuredDemoApplication.class, args);
    }
}
