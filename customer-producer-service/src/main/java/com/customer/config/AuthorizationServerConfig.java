package com.customer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  private static final String CLIEN_ID = "mobile-client-id";
  private static final String CLIENT_SECRET = "mobile-client-secret";
  private static final String GRANT_TYPES = "client_credentials";
  private static final String[] SCOPES = {"all", "read", "write", "edit", "delete"};
  private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 15 * 60;

  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

    configurer
        .inMemory()
        .withClient(CLIEN_ID)
        .secret(passwordEncoder.encode(CLIENT_SECRET))
        .authorizedGrantTypes(GRANT_TYPES)
        .scopes(SCOPES)
        .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager);
  }
}
