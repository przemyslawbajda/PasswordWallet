package com.wallet.demo.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class PasswordRequest {

    private Long id;
    private String password;
    private String webAddress;
    private String description;
    private String login;
    private Long userId;
    private String jwtToken;
}
