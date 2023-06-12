package com.example.demo1.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtility {

    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 안전한 비밀 키 생성
    private final long expirationTime = 86400000; // JWT 만료 시간 (24시간)
    private final long refreshTokenExpirationTime = 2592000000L; // Refresh Token 만료 시간 (30일)


    // 사용자 ID와 사용자명을 기반으로 JWT를 생성
    public String generateToken(String userId, String username) {
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(secretKey)
                .compact();
    }

    // 주어진 토큰을 파싱하여 토큰에 포함된 클레임 정보를 추출
    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    //주어진 토큰의 유효성을 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // 주어진 토큰에서 사용자명(subject)을 추출하여 반환
    public String getUsernameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    // 사용자 ID와 사용자명을 기반으로 Refresh Token을 생성
    public String generateRefreshToken(String userId, String username) {
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + refreshTokenExpirationTime);

        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(secretKey)
                .compact();
    }



}
