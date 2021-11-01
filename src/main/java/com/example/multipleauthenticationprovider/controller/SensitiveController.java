package com.example.multipleauthenticationprovider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SensitiveController {

  @GetMapping("/sensitive")
  public String getSensitive() {
    return "s3ns1t1v3";
  }

}
