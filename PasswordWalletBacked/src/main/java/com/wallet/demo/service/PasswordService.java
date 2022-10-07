package com.wallet.demo.service;

import com.wallet.demo.repository.PasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PasswordService {

    private PasswordRepository passwordRepository;

    @Autowired
    public PasswordService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }
}
