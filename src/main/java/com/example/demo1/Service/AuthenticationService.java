package com.example.demo1.Service;


import com.example.demo1.Config.JwtUtil;
import com.example.demo1.Dao.UserMapper;
import com.example.demo1.Dto.SignUpDto;
import com.example.demo1.Dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 사용자 정보를 가져오는 로직을 구현합니다.
        // 예를 들어, username을 사용하여 데이터베이스에서 사용자 정보를 조회하고 UserDetails 객체를 반환합니다.
        User user = userMapper.findByUserid(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getId(),
                user.getPw(),
                // 사용자의 권한을 설정합니다. 필요에 따라 권한을 동적으로 설정할 수도 있습니다.
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    //회원가입 하는 method
    public void createUser(User user){
        userMapper.InsertUser(user);
    }


    // Access Token을 생성하고 반환하는 method
    public String authenticateUser(String id, String pw) {
        User user = userMapper.findByUserid(id);
        if (user != null && pw.equals(user.getPw())) {
            String token = jwtUtil.generateToken(user.getId());

            //테스트용
            System.out.println("토큰이름 .........");
            System.out.println(token);


            return token;
        }else{
            throw new BadCredentialsException("Invalid username or password");
        }
            //AuthenticationException}

    }

    // Access Token의 유효성을 검증하는 method
    public String verifyAccessToken(String token) {
        //테스트용으로 if 문 안에있던거 올림
        System.out.println("validateToken 입니다요~");
        String usernameFromToken = jwtUtil.getUsernameFromToken(token);
        System.out.println("두구두구... id 값: " + usernameFromToken);
        return usernameFromToken;
        /*
        if (jwtUtil.validateToken(token)) {
            //출력 테스트용
            System.out.println("validateToken 입니다요~");
            String usernameFromToken = jwtUtil.getUsernameFromToken(token);
            return usernameFromToken;
        }
        throw new BadCredentialsException("Invalid access token");

         */
    }

    // 사용자 ID 기반으로 Refresh Token을 발급하는 method
    public String issueRefreshToken(String id) {
        User user = userMapper.findByUserid(id);
        if (user != null) {
            String refreshToken = jwtUtil.generateToken(user.getId());
            // 저장소에 refreshToken을 저장하는 로직 추가
            return refreshToken;
        }
        throw new BadCredentialsException("User not found");
    }



}
