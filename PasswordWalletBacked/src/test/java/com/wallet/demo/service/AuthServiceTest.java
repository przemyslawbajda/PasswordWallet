package com.wallet.demo.service;

import com.wallet.demo.utils.JwtUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    private AuthService authService;

    @Mock
    private UserService userService;
    @Mock
    JwtUtils jwtUtils;
    @Mock
    PasswordService passwordService;

    @Before
    public void setUp() {
        authService = new AuthService(userService, jwtUtils,passwordService);
    }


    @Test
    public void calculateSHA512_When_everythingWentCorrectly() {
        assertEquals("ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff",
                authService.calculateSHA512("test"));
    }

    @Test(expected = NullPointerException.class)
    public void calculateSHA512_throwsNullPointerException_When_GotNull() {
        authService.calculateSHA512(null);
    }

    @Test
    public void calculateHMAC_When_everythingWentCorrectly() {
        assertEquals("9ba1f63365a6caf66e46348f43cdef956015bea997adeb06e69007ee3ff517df10fc5eb860da3d43b82c2a040c931119d2dfc6d08e253742293a868cc2d82015",
                authService.calculateHMAC("test", "test"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateHMAC_throwsIllegalArgumentException_When_GotNulls() {
        authService.calculateHMAC(null, null);
    }
}