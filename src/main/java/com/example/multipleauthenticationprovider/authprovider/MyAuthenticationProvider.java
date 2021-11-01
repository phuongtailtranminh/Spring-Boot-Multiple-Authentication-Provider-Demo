package com.example.multipleauthenticationprovider.authprovider;

import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    if (!"MY".equals(authentication.getDetails())) {
      throw new BadCredentialsException("Go next");
    }

    log.info("MyAuthenticationProvider {}", authentication);

    String username = authentication.getPrincipal().toString();
    String password = String.valueOf(authentication.getCredentials());

    // TODO: 11/1/2021 Check with real MY authentication provider
    if ("jackie-my".equals(username) && "password".equals(password)) {
      return new UsernamePasswordCountryAuthenticationToken(username, password, Collections.emptyList());
    }

    throw new BadCredentialsException("Go next");
  }

  @Override
  public boolean supports(Class<?> authenticationType) {
    return UsernamePasswordCountryAuthenticationToken.class.equals(authenticationType);
  }
}
