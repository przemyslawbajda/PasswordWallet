package com.wallet.demo.controller;

import com.wallet.demo.payload.ChangeMainPasswordRequest;
import com.wallet.demo.payload.LoginRequest;
import com.wallet.demo.payload.RegisterRequest;
import com.wallet.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/WT")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest){

        return authService.loginUser(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest){

        return authService.registerUser(registerRequest);
    }

    @PutMapping("/change-main-password")
    public ResponseEntity<?> changeMainPassword(@RequestBody ChangeMainPasswordRequest changeMainPasswordRequest, @RequestHeader("JwtToken") String jwtToken){

        return authService.changeMainPassword(changeMainPasswordRequest, jwtToken);
    }

}
