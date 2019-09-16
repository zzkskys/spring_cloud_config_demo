package cn.zzk.eurekasvr.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Create Time : 2019/09/10
 *
 * @author zzk
 */
@SpringBootApplication
@EnableDiscoveryClient  // 若不适用 DiscoveryClient 消费微服务，则可以移除该注解
@EnableFeignClients //若不使用 Feign 消费微服务，则可以移除该注解
@EnableHystrix //使用 Hystrix ，若没有使用该注解，则 Hystrix 熔断器不会出于活动状态，服务启动时也不会有异常及错误提示。
public class EurekaCustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaCustomerApplication.class, args);
    }

    /**
     * LoadBalanced 注解告诉 Spring Cloud 创建支持 Ribbon 的 RestTemplate 类。
     */
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
