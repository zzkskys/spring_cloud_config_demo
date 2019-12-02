package cn.zzk.license;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

/**
 * Create Time : 2019/09/25
 *
 * @author zzk
 */
@SpringBootApplication
@EnableEurekaClient
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
    public OAuth2RestTemplate clientCredentialsRestTemplate(OAuth2ProtectedResourceDetails details) {
        DefaultOAuth2ClientContext context = new DefaultOAuth2ClientContext();
        return new OAuth2RestTemplate(details,context);
    }
}
