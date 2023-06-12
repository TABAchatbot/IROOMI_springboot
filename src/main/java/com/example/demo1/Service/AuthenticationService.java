package com.example.demo1.Service;


import com.example.demo1.Config.JwtUtility;
import com.example.demo1.Dao.UserMapper;
import com.example.demo1.Dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtility jwtUtility;

    // Access Token을 생성하고 반환하는 method
    public String authenticateUser(String id, String pw) {
        User user = userMapper.findByUserid(id);
        if (user != null && pw.equals(user.getPw())) {
            String token = jwtUtility.generateToken(user.getId(), user.getUsername());
            return token;
        }
        throw new BadCredentialsException("Invalid username or password");
        //AuthenticationException
    }

    // Access Token의 유효성을 검증하는 method
    public String verifyAccessToken(String token) {
        if (jwtUtility.validateToken(token)) {
            return jwtUtility.getUsernameFromToken(token);
        }
        throw new BadCredentialsException("Invalid access token");
    }

    // 사용자 ID 기반으로 Refresh Token을 발급하는 method
    public String issueRefreshToken(String id) {
        User user = userMapper.findByUserid(id);
        if (user != null) {
            String refreshToken = jwtUtility.generateRefreshToken(user.getId(), user.getUsername());
            // 저장소에 refreshToken을 저장하는 로직 추가
            return refreshToken;
        }
        throw new BadCredentialsException("User not found");
    }



}
