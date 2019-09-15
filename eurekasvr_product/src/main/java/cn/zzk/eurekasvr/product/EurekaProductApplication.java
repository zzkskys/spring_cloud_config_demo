package cn.zzk.eurekasvr.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Create Time : 2019/09/10
 *
 * @author zzk
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker //使用 Hystrix ，若没有使用该注解，则 Hystrix 熔断器不会出于活动状态，服务启动时也不会有异常及错误提示。
public class EurekaProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaProductApplication.class, args);
    }
}
