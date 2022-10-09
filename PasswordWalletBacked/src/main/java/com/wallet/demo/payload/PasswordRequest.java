package com.wallet.demo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordRequest {

    private Long id;
    private String password;
    private String webAddress;
    private String description;
    private String login;
    private String jwtToken;
}
