package com.example.controllers;

import ch.qos.logback.core.CoreConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ClientDetailService implements ClientDetailsService {
    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        System.out.println("called load client");
        SecurityClient securityClient=new SecurityClient();
        securityClient.setClientId("client1");
        securityClient.setClientSecret("client1");
        securityClient.setScope(Arrays.asList("read","write"));
        securityClient.setAuthorizedGrantTypes(Arrays.asList("password"));
        securityClient.setRefreshTokenValiditySeconds(500);
        securityClient.setAccessTokenValiditySeconds(500);
        return  securityClient;
    }
}
