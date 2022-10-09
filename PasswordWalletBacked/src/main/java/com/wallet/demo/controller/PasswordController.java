package com.wallet.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PasswordController {

    public ResponseEntity<?> addPassword(){

        return null;
    }

    public ResponseEntity<?> editPassword(){

        return null;
    }
}
