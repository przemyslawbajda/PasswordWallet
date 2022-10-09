package com.wallet.demo.service;

import com.wallet.demo.entity.Password;
import com.wallet.demo.entity.User;
import com.wallet.demo.payload.PasswordRequest;
import com.wallet.demo.payload.response.ResponseMessage;
import com.wallet.demo.repository.PasswordRepository;
import com.wallet.demo.repository.UserRepository;
import com.wallet.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordService {

    private PasswordRepository passwordRepository;
    private JwtUtils jwtUtils;
    private UserService userService;

    @Autowired
    public PasswordService(PasswordRepository passwordRepository, JwtUtils jwtUtils, UserService userService) {
        this.passwordRepository = passwordRepository;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    public ResponseEntity<?> addPassword(PasswordRequest passwordRequest){

        if(!jwtUtils.validateJwtToken(passwordRequest.getJwtToken())){
            return ResponseEntity.badRequest().body(
                    new ResponseMessage(ResponseMessage.ERR_UNAUTHORIZED_ACTION)
            );
        }

        String userLogin = jwtUtils.getUsernameFromJwtToken(passwordRequest.getJwtToken());

        User user = userService.getUserByLogin(userLogin);


        Password password = new Password(passwordRequest.getPassword(),
                                        passwordRequest.getWebAddress(),
                                        passwordRequest.getDescription(),
                                        passwordRequest.getLogin());

        password.setUser(user);

        passwordRepository.save(password);

        return ResponseEntity.ok(
                new ResponseMessage(ResponseMessage.NEW_PASSWORD_ADDED)
        );
    }

    public ResponseEntity<?> editPassword(PasswordRequest passwordRequest) {
        if(!jwtUtils.validateJwtToken(passwordRequest.getJwtToken())){
            return ResponseEntity.badRequest().body(
                    new ResponseMessage(ResponseMessage.ERR_UNAUTHORIZED_ACTION)
            );
        }

        Optional<Password> password = passwordRepository.findById(passwordRequest.getId());

        password.ifPresent( thePassword -> {
            thePassword.setPassword(passwordRequest.getPassword());
            thePassword.setDescription(passwordRequest.getDescription());
            thePassword.setWebAddress(passwordRequest.getWebAddress());
            thePassword.setLogin(passwordRequest.getLogin());

            passwordRepository.save(thePassword);
        } );
        //TODO: orElseThrow()

        return ResponseEntity.ok(
                new ResponseMessage(ResponseMessage.PASSWORD_EDITED)
        );
    }
}
