package com.wallet.demo.service;

import com.wallet.demo.entity.LoginAttempt;
import com.wallet.demo.entity.User;
import com.wallet.demo.repository.LoginAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class LoginAttemptService {

    @Autowired
    LoginAttemptRepository loginAttemptRepository;

    public LoginAttempt generateLoginAttempt(User user, boolean isSuccessful) {
        LoginAttempt loginAttempt = new LoginAttempt();
        loginAttempt.setUser(user);
        loginAttempt.setAttemptDate(LocalDateTime.now());
        loginAttempt.setSuccessful(isSuccessful);

        return loginAttempt;
    }

    public void save(LoginAttempt loginAttempt) {
        loginAttemptRepository.save(loginAttempt);
    }

    public LoginAttempt getLastSuccessOrFailAttempt(User user, boolean isSuccessful){

        return loginAttemptRepository.findFirstByUserAndIsSuccessfulOrderByAttemptDateDesc(user, isSuccessful);
    }
}


