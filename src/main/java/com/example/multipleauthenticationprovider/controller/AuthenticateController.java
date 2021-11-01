package com.example.multipleauthenticationprovider.controller;

import com.example.multipleauthenticationprovider.authprovider.UsernamePasswordCountryAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthenticateController {

  private final AuthenticationManager authenticationManager;

  public AuthenticateController(
      AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/authenticate")
  public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
    authenticationManager.authenticate(
        new UsernamePasswordCountryAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword(),
            loginDTO.getCountry()));

    // Only return the token in case authenticationManager.authenticate(...) doesn't throw exception
    // means can't login.
    // TODO: 11/1/2021 Generate JWT Token
    return ResponseEntity.ok("s3Cr3t-t0k3n");
  }

}
