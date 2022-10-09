package com.wallet.demo.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeMainPasswordRequest {

    private String oldPassword;
    private String newPassword;
    private String jwtToken;
}
