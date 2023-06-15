package com.example.demo1.Config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtil {

    private static final Algorithm ALGORITHM = Algorithm.HMAC256("iroomi");
    private static final long AUTH_TIME=30*60; //30분 * 60초
    private static final long REFRESH_TIME=60*60*24*7; //60분 * 60초 * 24시간 * 7일

    public static String generateToken(String username) {

        return JWT.create()
                .withSubject(username)
                .withClaim("exp", Instant.now().getEpochSecond()+AUTH_TIME)
                .sign(ALGORITHM);
    }

    public static String getUsernameFromToken(String token) {
        System.out.println(token);
        DecodedJWT decodedJWT = JWT.require(ALGORITHM).build().verify(token);
        return decodedJWT.getSubject();
    }


    public static String makeRefreshToken(String username){

        return JWT.create()
                .withSubject(username)
                .withClaim("exp", Instant.now().getEpochSecond()+REFRESH_TIME)
                .sign(ALGORITHM);
    }

    //verify 토큰 하는 함수도 ... 필요...... 근데 이건 나중에......






}
