package com.example.multipleauthenticationprovider.config;

import com.example.multipleauthenticationprovider.authprovider.MyAuthenticationProvider;
import com.example.multipleauthenticationprovider.authprovider.SgAuthenticationProvider;
import com.example.multipleauthenticationprovider.filter.TokenRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final SgAuthenticationProvider sgAuthenticationProvider;
  private final MyAuthenticationProvider myAuthenticationProvider;
  private TokenRequestFilter tokenRequestFilter;

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  public SecurityConfig(
      SgAuthenticationProvider sgAuthenticationProvider,
      MyAuthenticationProvider myAuthenticationProvider,
      TokenRequestFilter tokenRequestFilter) {
    this.sgAuthenticationProvider = sgAuthenticationProvider;
    this.myAuthenticationProvider = myAuthenticationProvider;
    this.tokenRequestFilter = tokenRequestFilter;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(sgAuthenticationProvider);
    auth.authenticationProvider(myAuthenticationProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/authenticate").anonymous()
        .anyRequest().authenticated()
        .and()
        .httpBasic()
        .and()
        // Session won't be used to store user state, otherwise strange behaviour happens
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf().disable();

    // Add a filter to validate the tokens with every request
    http.addFilterBefore(tokenRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }

}
