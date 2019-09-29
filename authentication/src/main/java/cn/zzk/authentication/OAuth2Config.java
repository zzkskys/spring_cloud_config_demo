package cn.zzk.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * Create Time : 2019/09/24
 *
 * @author zzk
 */
@Configuration
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * inMemory() 方法表示使用 内存存储认证的凭证
     * withClient() 和 secrete() 提供了注册的应用程序名称 (eagleeye) 以及密匙(thissecret),密匙在 应用程序调用 OAuth2 服务器以接收 OAuth2 访问令牌时提供
     * authorizedGrantTypes() 表示授权类型列表
     * <p>
     * scopes() 方法用于定义调用应用程序在请求 OAuth2 服务器获取访问令牌时可以操作的范围。
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("eagleeye")
                .secret("{noop}thisissecret")
                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                .scopes("webclient", "mobileclient");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }
}
