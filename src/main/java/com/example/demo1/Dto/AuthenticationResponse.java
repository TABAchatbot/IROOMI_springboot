package com.example.demo1.Dto;

public class AuthenticationResponse {
    private String token;
    private String tokenType;

    public AuthenticationResponse(String token) {
        this.token = token;
        this.tokenType = "Bearer";
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }
}
