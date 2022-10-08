package com.wallet.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

    private String message;
    private String userLogin;
    private String jwt;

}
