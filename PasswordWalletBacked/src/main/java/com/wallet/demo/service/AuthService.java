package com.wallet.demo.service;

import com.wallet.demo.entity.User;
import com.wallet.demo.payload.LoginRequest;
import com.wallet.demo.payload.RegisterRequest;
import com.wallet.demo.payload.response.LoginResponse;
import com.wallet.demo.payload.response.ResponseMessage;
import com.wallet.demo.utils.JwtUtils;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Date;

import static javax.xml.crypto.dsig.SignatureMethod.HMAC_SHA512;

@Service
public class AuthService {

    private UserService userService;

    JwtUtils jwtUtils;

    @Value("${app.pepper}")
    private static String PEPPER;

    @Autowired
    public AuthService(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<?> loginUser(LoginRequest loginRequest){

        if(!userService.checkIfUserAlreadyExist(loginRequest.getLogin())){
            return ResponseEntity.badRequest().body(
                    new ResponseMessage(ResponseMessage.ERR_INCORRECT_LOGIN_PASSWORD)
            );
        }

        User user = userService.getUserByLogin(loginRequest.getLogin());

        String hashedPassword = user.getIsPasswordKeptAsHash()
                                        ? calculateSHA512(PEPPER+user.getSalt()+loginRequest.getPassword())
                                        : calculateHMAC(loginRequest.getPassword());


        if(!user.getPasswordHash().equals(hashedPassword)){
            return ResponseEntity.badRequest().body(
                    new ResponseMessage(ResponseMessage.ERR_INCORRECT_LOGIN_PASSWORD)
            );
        }

        String jwt = jwtUtils.calculateJwt(user.getLogin());

        return ResponseEntity.ok(
                new LoginResponse(ResponseMessage.USER_LOGIN_SUCCESSFULLY,
                                    user.getLogin(),
                                    jwt)
        );

    }


    public ResponseEntity<ResponseMessage> registerUser(RegisterRequest registerRequest){

        if(userService.checkIfUserAlreadyExist(registerRequest.getLogin())){
            return ResponseEntity.badRequest().body(
                    new ResponseMessage(ResponseMessage.ERR_USER_ALREADY_EXISTS)
            );
        }

        User newUser = new User(registerRequest.getLogin(), registerRequest.getIsHash());

        // Salt?
        newUser.setSalt(generateSalt());

        newUser.setPasswordHash(registerRequest.getIsHash()
                                ? calculateSHA512(PEPPER+newUser.getSalt()+registerRequest.getPassword()
        )                       : calculateHMAC(registerRequest.getPassword()));
        
        userService.save(newUser);

        return ResponseEntity.ok(
                new ResponseMessage(ResponseMessage.USER_REGISTER_SUCCESSFULLY));
    }

    public String calculateSHA512(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(text.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashText = no.toString(16);

            while (hashText.length() < 32)
                hashText = "0" + hashText;

            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }



    //nie wyszukuje HMAC_SHA512
    public String calculateHMAC(String text) {
        String key="key";
        Mac sha512Hmac;
        String result="";
        try {
            final byte[] byteKey = key.getBytes(StandardCharsets.UTF_8);
            sha512Hmac = Mac.getInstance(HMAC_SHA512);
            SecretKeySpec keySpec = new SecretKeySpec(byteKey, HMAC_SHA512);
            sha512Hmac.init(keySpec);
            byte[] macData = sha512Hmac.doFinal(text.getBytes(StandardCharsets.UTF_8));
            result = Base64.getEncoder().encodeToString(macData);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes.toString();
    }


}
