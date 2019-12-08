package cn.zzk.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
public class JWTOAuth2Config extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final TokenStore tokenStore;

    private final DefaultTokenServices tokenServices;

    private final JwtAccessTokenConverter jwtAccessTokenConverter;

    private final TokenEnhancer jwtTokenEnhancer;

    public JWTOAuth2Config(AuthenticationManager authenticationManager,
                           UserDetailsService userDetailsService,
                           TokenStore tokenStore,
                           DefaultTokenServices tokenServices,
                           JwtAccessTokenConverter jwtAccessTokenConverter,
                           TokenEnhancer jwtTokenEnhancer) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenStore = tokenStore;
        this.tokenServices = tokenServices;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.jwtTokenEnhancer = jwtTokenEnhancer;
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtTokenEnhancer, jwtAccessTokenConverter));

        endpoints.tokenStore(tokenStore)                             //JWT
                .accessTokenConverter(jwtAccessTokenConverter)       //JWT
                .tokenEnhancer(tokenEnhancerChain)                   //JWT
                .authenticationManager(authenticationManager)
                .tokenServices(tokenServices)
                .userDetailsService(userDetailsService);
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("eagleeye")
                .secret("thisissecret")
                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                .scopes("webclient", "mobileclient");
    }
}
