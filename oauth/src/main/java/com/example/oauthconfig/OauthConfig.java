package com.example.oauthconfig;

import com.example.controllers.ClientDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class OauthConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .checkTokenAccess("permitAll()");//allows everyone to access this endpoint localhost:8080/oauth/check_token?token=c3888c18-f5c7-45d5-8509-3c3c16264905
                        .checkTokenAccess("isAuthenticated()");//allows only authenticated users to access this endpoint localhost:8080/oauth/check_token?token=c3888c18-f5c7-45d5-8509-3c3c16264905
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("client1")
//                .secret("client1")
//                .scopes("read")
//                .authorizedGrantTypes("password");
        clients.withClientDetails(getClientDetailService());

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
    @Bean
    public ClientDetailService getClientDetailService()
    {
        return new ClientDetailService();
    }
}
