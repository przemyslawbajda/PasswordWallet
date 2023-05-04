package com.wallet.demo.service;

import com.wallet.demo.entity.Password;
import com.wallet.demo.entity.SharedPassword;
import com.wallet.demo.entity.User;
import com.wallet.demo.payload.PasswordRequest;
import com.wallet.demo.payload.SharePasswordRequest;
import com.wallet.demo.payload.response.ResponseMessage;
import com.wallet.demo.repository.PasswordRepository;
import com.wallet.demo.repository.SharedPasswordRepository;
import com.wallet.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PasswordService {

    private PasswordRepository passwordRepository;
    private JwtUtils jwtUtils;
    private UserService userService;

    private SharedPasswordRepository sharedPasswordRepository;

    @Autowired
    public PasswordService(PasswordRepository passwordRepository, JwtUtils jwtUtils, UserService userService, SharedPasswordRepository sharedPasswordRepository) {
        this.passwordRepository = passwordRepository;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.sharedPasswordRepository = sharedPasswordRepository;
    }

    public ResponseEntity<?> addPassword(PasswordRequest passwordRequest, String jwtToken){

        if(!jwtUtils.validateJwtToken(jwtToken)){
            return ResponseEntity.badRequest().body(
                    new ResponseMessage(ResponseMessage.ERR_UNAUTHORIZED_ACTION)
            );
        }

        String userLogin = jwtUtils.getUsernameFromJwtToken(jwtToken);

        User user = userService.getUserByLogin(userLogin);

        String encryptedPassword = encryptPassword(passwordRequest.getPassword(), user.getPasswordHash());

        Password password = new Password(encryptedPassword,
                                        passwordRequest.getWebAddress(),
                                        passwordRequest.getDescription(),
                                        passwordRequest.getLogin());

        password.setUser(user);

        passwordRepository.save(password);

        return ResponseEntity.ok(
                new ResponseMessage(ResponseMessage.NEW_PASSWORD_ADDED)
        );
    }

    public ResponseEntity<?> editPassword(PasswordRequest passwordRequest, String jwtToken) {
        if(!jwtUtils.validateJwtToken(jwtToken)){
            return ResponseEntity.badRequest().body(
                    new ResponseMessage(ResponseMessage.ERR_UNAUTHORIZED_ACTION)
            );
        }

        String username = jwtUtils.getUsernameFromJwtToken(jwtToken);
        User user = userService.getUserByLogin(username);
        String encryptedPassword = encryptPassword(passwordRequest.getPassword(), user.getPasswordHash());

        Optional<Password> password = passwordRepository.findById(passwordRequest.getId());

        password.ifPresent( thePassword -> {
            thePassword.setPassword(encryptedPassword);
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

    public Set<Password> getPasswords(String jwtToken) {

        User user = userService.getUserByLogin(jwtUtils.getUsernameFromJwtToken(jwtToken));

        return this.passwordRepository.findAllByUser(user);
    }

    public ResponseEntity<?> getDecryptedPassword(Long passwordId, String jwtToken) {
        if(!jwtUtils.validateJwtToken(jwtToken)){
            return ResponseEntity.badRequest().body(
                    new ResponseMessage(ResponseMessage.ERR_UNAUTHORIZED_ACTION));
        }

        User user = userService.getUserByLogin(jwtUtils.getUsernameFromJwtToken(jwtToken));


        Password password = passwordRepository.findPasswordById(passwordId);
        String passwordHash = password.getPassword();

        String decryptedPassword = decryptPassword(passwordHash, user.getPasswordHash());

        return ResponseEntity.ok(
                new ResponseMessage(decryptedPassword)
        );
    }

    public ResponseEntity<?> deletePassword(Long passwordId, String jwtToken) {

        //checking if user that delete password is same user that owns password
        User user = userService.getUserByLogin(jwtUtils.getUsernameFromJwtToken(jwtToken));
        Password password = passwordRepository.findPasswordById(passwordId);

        if(!jwtUtils.validateJwtToken(jwtToken) ||
                user != password.getUser()){
            return ResponseEntity.badRequest().body(
                    new ResponseMessage(ResponseMessage.ERR_UNAUTHORIZED_ACTION));
        }

        passwordRepository.deleteById(passwordId);

        return ResponseEntity.ok(
                new ResponseMessage(ResponseMessage.PASSWORD_DELETED));
    }

    public static String encryptPassword(String password, String encryptionKey) {
        String algorithm = "AES";

        try{
            Key key = new SecretKeySpec(trimKey(encryptionKey).getBytes(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedPassword = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedPassword);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static String decryptPassword(String passwordToDecrypt, String encryptionKey){
        String algorithm = "AES";

        try{
            Key key = new SecretKeySpec(trimKey(encryptionKey).getBytes(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedPassword = cipher.doFinal(Base64.getDecoder().decode(passwordToDecrypt));
            return new String(decodedPassword);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static String trimKey(String key){
        return key.substring(0,32);
    }

    public ResponseEntity<?> sharePassword(SharePasswordRequest passwordRequest, String jwtToken){

        if(!jwtUtils.validateJwtToken(jwtToken)){
            return ResponseEntity.badRequest().body(
                    new ResponseMessage(ResponseMessage.ERR_UNAUTHORIZED_ACTION));
        }



        User user = new User();
        user.setId(passwordRequest.getUserId());

        Password password = new Password();
        password.setId(passwordRequest.getPasswordId());

        SharedPassword newSharedPassword = new SharedPassword();
        newSharedPassword.setUser(user);
        newSharedPassword.setPassword(password);

        sharedPasswordRepository.save(newSharedPassword);

        return ResponseEntity.ok(
                new ResponseMessage("password shared")
        );
    }

    public ResponseEntity<?> getSharedPassword(String jwtToken) {
        if(!jwtUtils.validateJwtToken(jwtToken)){
            return ResponseEntity.badRequest().body(
                    new ResponseMessage(ResponseMessage.ERR_UNAUTHORIZED_ACTION));
        }

        User user = userService.getUserByLogin(jwtUtils.getUsernameFromJwtToken(jwtToken));

        Set<SharedPassword> sharedPasswords = sharedPasswordRepository.findAllByUser(user);

        Set<Password> encryptedSharedPasswords = new HashSet<>();

        for( SharedPassword sharedPassword : sharedPasswords) {
            encryptedSharedPasswords.add(passwordRepository.findPasswordById(sharedPassword.getPassword().getId()));

        }

        Set<Password> decryptedSharedPasswords = new HashSet<>();;

        for (Password encryptedPassword : encryptedSharedPasswords) {

            User owner = userService.findUserById(encryptedPassword.getUser().getId());

            String decryptedPassword = decryptPassword(encryptedPassword.getPassword(), owner.getPasswordHash());
            encryptedPassword.setPassword(decryptedPassword);
            decryptedSharedPasswords.add(encryptedPassword);
        }


        return ResponseEntity.ok().body(decryptedSharedPasswords);

    }
}
