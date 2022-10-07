package com.wallet.demo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String login;
    private String password;
    private Boolean isHash;
}
