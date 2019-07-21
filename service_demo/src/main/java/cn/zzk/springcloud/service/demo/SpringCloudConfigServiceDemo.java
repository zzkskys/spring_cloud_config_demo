package cn.zzk.springcloud.service.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Create Time : 2019/07/21
 *
 * @author zzk
 */
@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServiceDemo {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigServiceDemo.class, args);
    }
}
