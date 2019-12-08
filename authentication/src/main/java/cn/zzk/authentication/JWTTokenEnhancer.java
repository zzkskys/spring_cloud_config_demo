package cn.zzk.authentication;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * 对认证后的令牌进行扩展
 */
public class JWTTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        Map<String, Object> map = new HashMap<>();

        map.put("organizationId", "1");
        map.put("organizationName", "test");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
        return accessToken;
    }
}
