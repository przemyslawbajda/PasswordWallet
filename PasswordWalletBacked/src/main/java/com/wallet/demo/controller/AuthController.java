package com.wallet.demo.controller;

import com.wallet.demo.payload.ChangeMainPasswordRequest;
import com.wallet.demo.payload.LoginRequest;
import com.wallet.demo.payload.RegisterRequest;
import com.wallet.demo.service.AuthService;
import com.wallet.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;
    private UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request){

        return authService.loginUser(loginRequest, request);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest){

        return authService.registerUser(registerRequest);
    }

    @PutMapping("/change-main-password")
    public ResponseEntity<?> changeMainPassword(@RequestBody ChangeMainPasswordRequest changeMainPasswordRequest, @RequestHeader("JwtToken") String jwtToken){

        return authService.changeMainPassword(changeMainPasswordRequest, jwtToken);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers(){

        return ResponseEntity.ok().body(userService.findAllUsers());
    }

}
