package cn.zzk.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 若使用 @EnableResourceServer 则表明该服务是 OAuth2 的验证服务。
 * 并添加几个基于 REST 的端点用于 OAuth2 的验证和授权的过程。
 */
@SpringBootApplication
@RestController
@EnableResourceServer
@EnableAuthorizationServer // 该服务作为 OAuth2 服务
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @GetMapping("/user")
    public OAuth2Authentication user(OAuth2Authentication user) {
        return user;
    }
}
