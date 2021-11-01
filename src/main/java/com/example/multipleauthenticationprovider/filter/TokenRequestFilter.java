package com.example.multipleauthenticationprovider.filter;

import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
public class TokenRequestFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String tokenHeader = request.getHeader("Authorization");

    if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
      String token = tokenHeader.substring(7);

      if (isValidToken(token)) {

        // TODO: 11/2/2021 Load the necessary information from verified jwt token
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken("principal", "credentials",
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }

    }

    filterChain.doFilter(request, response);
  }

  private boolean isValidToken(String tokenHeader) {
    return "s3Cr3t-t0k3n".equals(tokenHeader);
  }
}
