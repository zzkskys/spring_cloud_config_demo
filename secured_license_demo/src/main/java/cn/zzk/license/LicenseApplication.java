package cn.zzk.license;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;

/**
 * Create Time : 2019/09/25
 *
 * @author zzk
 */
@SpringBootApplication
@EnableEurekaClient
@EnableOAuth2Client
public class LicenseApplication {
    public static void main(String[] args) {
        SpringApplication.run(LicenseApplication.class, args);
    }


    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public OAuth2ProtectedResourceDetails clientCredentialsResourceDetails() {
        return new ResourceOwnerPasswordResourceDetails();
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
    }


    @Bean
    @LoadBalanced
    public RestTemplate clientCredentialsRestTemplate(OAuth2ClientContext context) {
        return new OAuth2RestTemplate(clientCredentialsResourceDetails(), context);
    }
}
