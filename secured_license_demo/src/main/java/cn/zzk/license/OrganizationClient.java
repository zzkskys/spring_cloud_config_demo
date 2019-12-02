package cn.zzk.license;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Create Time : 2019/09/25
 *
 * @author zzk
 */
@Component
public class OrganizationClient {

    //todo : 因为 OAuth2RestTemplate 无法使用,则自定义 RestTemplate,Zuul 中的凭证传递
    @Autowired
    private OAuth2RestTemplate restTemplate;


    public Organization getOrganization() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String key = "Authorization";
        String value = requestAttributes.getRequest().getHeader(key);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(key, value);
        return restTemplate
                .exchange("http://localhost:5555/securedservice/organizations/2",
                        HttpMethod.GET, new HttpEntity<>(headers),
                        Organization.class)
                .getBody();
    }

    public String hello() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8760/hello?", String.class);
    }
}
