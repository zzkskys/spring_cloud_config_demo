package cn.zzk.zuulsvr;

import cn.zzk.zuulsvr.utils.TokenInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 如何判断是否使用 @EnableZuulServer 注解？ 使用此注解将创建一个 Zuul 服务器，它不会加载任何 Zuul 反向代理过滤器，
 * 也不会使用 Netflix Eureka 进行服务发现。开发人员想要构建自己的路由服务，而不使用任何 Zuul 预置的功能时会使用 @EnableZuulServer,
 * 举例来讲，当开发人员需要使用 Zuul 与 Eureka 之外的其他服务发现引擎(如 Consul) 进行集成的时候。
 *
 * @author zzk
 */
//@EnableZuulServer
@SpringBootApplication
@EnableZuulProxy
public class ZuulServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }
}
