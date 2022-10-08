package com.wallet.demo.utils;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.util.Date;

@Component
public class JwtUtils {

    String jwtSecret = "dj84He8N24kv8SV";

    public String calculateJwt(String login) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 10000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public String getUsernameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
}
