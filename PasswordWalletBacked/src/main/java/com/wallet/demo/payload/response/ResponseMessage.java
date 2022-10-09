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
    public final static String USER_LOGIN_SUCCESSFULLY = "User login successfully";

    public final static String MAIN_PASSWORD_CHANGED = "Main password changed";
    public final static String ERR_MAIN_OLD_PASSWORD_WRONG = "Wrong old password";

    public final static String NEW_PASSWORD_ADDED = "New password added";
    public final static String PASSWORD_EDITED = "Password edited";

    public final static String ERR_USER_ALREADY_EXISTS = "User with this login already exists!";
    public final static String ERR_INCORRECT_LOGIN_PASSWORD = "Incorrect login or password";
    public final static String ERR_UNAUTHORIZED_ACTION = "Unauthorized action";
}
