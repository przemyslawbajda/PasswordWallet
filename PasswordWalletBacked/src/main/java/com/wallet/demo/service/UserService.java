package com.wallet.demo.service;

import com.wallet.demo.entity.User;
import com.wallet.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user){
        userRepository.save(user);
    }

    public Boolean checkIfUserAlreadyExist(String login){
        return userRepository.existsByLogin(login);
    }

    public User getUserByLogin(String login){
        return userRepository.findUserByLogin(login);
    }
}
