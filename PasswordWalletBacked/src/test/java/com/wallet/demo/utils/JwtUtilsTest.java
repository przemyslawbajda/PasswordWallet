package com.wallet.demo.utils;

import org.junit.Before;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtUtilsTest {

    private JwtUtils jwtUtils;


    @Before
    public void setUp(){
        jwtUtils = new JwtUtils();
    }


    @Test
    public void getUsernameFromJwtToken_When_TokenIsValid() {
        String jwtToken = jwtUtils.calculateJwt("nowy");
        String login = jwtUtils.getUsernameFromJwtToken(jwtToken);
        assertEquals("nowy", login);
    }


}