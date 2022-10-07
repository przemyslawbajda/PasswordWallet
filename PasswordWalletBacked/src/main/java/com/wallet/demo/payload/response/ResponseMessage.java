package com.wallet.demo.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseMessage {

    String message;

    public final static String USER_REGISTER_SUCCESSFULLY = "User registered successfully";
}
