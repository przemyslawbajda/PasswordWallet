package com.wallet.demo.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message="Login can't be blank")
    private String login;

    @NotBlank
    private String password;

    private Boolean isHash;
}
