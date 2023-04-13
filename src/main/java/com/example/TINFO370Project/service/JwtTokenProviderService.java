package com.example.TINFO370Project.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenProviderService {

    public String jwtTokenCreator(String username,Long id){
        String jwtToken = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis()+60000*30))
                .withSubject(username)
                .withClaim("id",id)
                .withClaim("username",username)
                .sign(Algorithm.HMAC512("cos"));

    return jwtToken;
    }
}
