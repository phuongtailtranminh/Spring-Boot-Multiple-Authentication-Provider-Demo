package com.example.multipleauthenticationprovider.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginDTO {
  private String username;
  private String password;
  private String country;
}
