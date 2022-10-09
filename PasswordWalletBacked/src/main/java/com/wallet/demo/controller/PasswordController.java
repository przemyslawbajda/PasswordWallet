package com.wallet.demo.controller;

import com.wallet.demo.payload.PasswordRequest;
import com.wallet.demo.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PasswordController {

    PasswordService passwordService;

    @Autowired
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping("/passwords")
    public ResponseEntity<?> addPassword(@RequestBody PasswordRequest passwordRequest){

        return passwordService.addPassword(passwordRequest);
    }

    @PutMapping("/passwords")
    public ResponseEntity<?> editPassword(@RequestBody PasswordRequest passwordRequest){

        return passwordService.editPassword(passwordRequest);
    }
}
