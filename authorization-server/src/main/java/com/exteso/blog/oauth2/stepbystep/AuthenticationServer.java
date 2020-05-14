package com.exteso.blog.oauth2.stepbystep;

import java.security.Principal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer
public class AuthenticationServer {

  private static final Log logger = LogFactory.getLog(AuthenticationServer.class);

  public static void main(String[] args) {
    SpringApplication.run(AuthenticationServer.class, args);
  }

  @RequestMapping("/user")
  public Principal user(Principal user) {
    logger.info("AS /user has been called");
    logger.debug("user info: " + user.toString());
    return user;
  }
}
