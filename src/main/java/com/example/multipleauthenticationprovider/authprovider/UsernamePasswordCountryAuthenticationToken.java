package com.example.multipleauthenticationprovider.authprovider;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UsernamePasswordCountryAuthenticationToken extends AbstractAuthenticationToken {

  private String username;
  private String password;
  private String country;

  public UsernamePasswordCountryAuthenticationToken(
      String username, String password, Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.username = username;
    this.password = password;
  }

  public UsernamePasswordCountryAuthenticationToken(String username, String password, String country) {
    super((Collection) null);
    this.username = username;
    this.password = password;
    this.country = country;
  }

  @Override
  public Object getCredentials() {
    return this.password;
  }

  @Override
  public Object getPrincipal() {
    return this.username;
  }

  @Override
  public Object getDetails() {
    return this.country;
  }
}
