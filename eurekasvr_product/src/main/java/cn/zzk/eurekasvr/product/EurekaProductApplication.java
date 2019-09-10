package cn.zzk.eurekasvr.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Create Time : 2019/09/10
 *
 * @author zzk
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaProductApplication.class, args);
    }
}
