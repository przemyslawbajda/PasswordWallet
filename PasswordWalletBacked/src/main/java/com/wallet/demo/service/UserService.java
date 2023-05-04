package com.wallet.demo.service;

import com.wallet.demo.entity.User;
import com.wallet.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

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

    public User findUserById(Long id){return userRepository.findById(id).orElseThrow(RuntimeException::new);}

    public List<User> findAllUsers(){return userRepository.findAll();}
}
