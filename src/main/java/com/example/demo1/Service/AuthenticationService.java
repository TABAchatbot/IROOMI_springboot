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

    public String authenticateUser(String id, String pw) {
        User user = userMapper.findByUserid(id);
        if (user != null && pw.equals(user.getPw())) {
            String token = jwtUtility.generateToken(user.getId(), user.getUsername());
            return token;
        }
        throw new BadCredentialsException("Invalid username or password");
        //AuthenticationException
    }




}
