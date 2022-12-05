package com.wallet.demo.service;

import com.wallet.demo.repository.PasswordRepository;
import com.wallet.demo.utils.JwtUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PasswordServiceTest {

    private PasswordService passwordService;

    @Mock
    private PasswordRepository passwordRepository;
    @Mock
    private JwtUtils jwtUtils;
    @Mock
    private UserService userService;

    @Before
    public void setUp(){
        passwordService = new PasswordService(passwordRepository, jwtUtils, userService);
    }

    @Test
    public void encryptPassword_When_PasswordEncryptedSuccessfully() {
        String plainPassword = "password";
        String key = "ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff";
        assertEquals("pPOiJXKcmtkEfbyaCIgbDA==", PasswordService.encryptPassword(plainPassword, key));
    }

    @Test
    public void decryptPassword_When_PasswordDecryptedSuccessfully() {
        String encryptedPassword = "pPOiJXKcmtkEfbyaCIgbDA==";
        String key = "ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff";
        assertEquals("password", PasswordService.decryptPassword(encryptedPassword, key));
    }

    @Test(expected = IllegalArgumentException.class)
    public void encryptPassword_throwsIllegalArgumentException_When_GotNullPassword(){
        String key = "ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff";
        PasswordService.encryptPassword(null, key);
    }

    @Test(expected = IllegalArgumentException.class)
    public void encryptPassword_throwsIllegalArgumentException_When_GotNullKey(){
        String plainPassword = "password";
        PasswordService.encryptPassword(plainPassword, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void decryptPassword_throwsIllegalArgumentException_When_GotNullPassword(){
        String key = "ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff";
        PasswordService.decryptPassword(null, key);
    }

    @Test(expected = IllegalArgumentException.class)
    public void decryptPassword_throwsIllegalArgumentException_When_GotNullKey(){
        String encryptedPassword = "pPOiJXKcmtkEfbyaCIgbDA==";
        PasswordService.decryptPassword(encryptedPassword, null);
    }

    @Test
    public void trimKey_StringHas32Length_When_EverythingWentCorrectly(){
        String key = "ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff";
        assertEquals("ee26b0dd4af7e749aa1a8ee3c10ae992", PasswordService.trimKey(key));
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void trimKey_throwsStringIndexOutOfBoundsException_When_StringIsTooShort(){
        String key = "ee26b0dd4af7e749aa1a8ee3";
        assertEquals("ee26b0dd4af7e749aa1a8ee3c10ae992", PasswordService.trimKey(key));
    }
}