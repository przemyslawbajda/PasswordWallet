package com.wallet.demo.controller;

import com.wallet.demo.entity.Password;
import com.wallet.demo.payload.PasswordRequest;
import com.wallet.demo.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PasswordController {

    PasswordService passwordService;

    @Autowired
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping("passwords")
    public ResponseEntity<?> addPassword(@RequestBody PasswordRequest passwordRequest, @RequestHeader("JwtToken") String jwtToken){

        return passwordService.addPassword(passwordRequest, jwtToken);
    }

    @PutMapping("passwords")
    public ResponseEntity<?> editPassword(@RequestBody PasswordRequest passwordRequest, @RequestHeader("JwtToken") String jwtToken){

        return passwordService.editPassword(passwordRequest, jwtToken);
    }

    @GetMapping("passwords")
    public List<Password> getPasswords(@RequestHeader("JwtToken") String jwtToken){


        return passwordService.getPasswords(jwtToken);
    }
}
