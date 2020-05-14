package com.exteso.blog.oauth2.stepbystep.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private Environment environment;

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints
        //       .tokenStore(tokenStore())
        //               .tokenEnhancer(jwtTokenEnhancer())
        .authenticationManager(authenticationManager);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()");
  }

//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(jwtTokenEnhancer());
//    }

//    @Bean
//    protected JwtAccessTokenConverter jwtTokenEnhancer() {
//        String pwd = environment.getProperty("keystore.password");
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
//                new ClassPathResource("jwt.jks"),
//                pwd.toCharArray());
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
//        return converter;
//    }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient("service-account-1")
        .secret("service-account-1-secret")
        .authorizedGrantTypes("client_credentials")
        .autoApprove(true)
        .redirectUris("https://snap.elastic.snaplogic.com/api/1/rest/admin/oauth2callback/rest")
        .scopes("resource-server-read", "resource-server-write")
        .authorities("ROLE_RS_READ", "ROLE_RS_WRITE")
        .accessTokenValiditySeconds(3000);
  }

}