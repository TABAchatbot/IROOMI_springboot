package com.example.demo1.Controller;

import com.example.demo1.Dto.AuthenticationRequest;
import com.example.demo1.Dto.AuthenticationResponse;
import com.example.demo1.Dto.SignUpDto;
import com.example.demo1.Dto.User;
import com.example.demo1.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try {
            // 사용자 인증
            String token = authenticationService.authenticateUser(request.getId(), request.getPassword());

            // 토큰 생성에 성공한 경우
            return ResponseEntity.ok()
                    .body(new AuthenticationResponse(token)); // 토큰을 응답으로 반환

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody /*AuthenticationRequest request*/ User user) {
        try {
//            System.out.println(request.getId());
//            System.out.println(request.getPassword());

            System.out.println("회원가입 리퀘스트값 받기");
            System.out.println(user.getId());
            System.out.println(user.getPw());
            System.out.println(user.getUsername());
            System.out.println(user.getGender());
            System.out.println(user.getBirth_date());
            //회원가입
//            authenticationService.createUser(user);


            return ResponseEntity.ok().build();
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/verifyAccessToken")
    public ResponseEntity<?> verifyAccessToken(@RequestBody String accessToken) {
        try {
            String username = authenticationService.verifyAccessToken(accessToken);
            // Access Token이 유효한 경우에 대한 처리 추가......

            return ResponseEntity.ok().build();
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/issueRefreshToken")
    public ResponseEntity<?> issueRefreshToken(@RequestBody String username) {
        try {
            String refreshToken = authenticationService.issueRefreshToken(username);
            // Refresh Token 발급에 대한 처리 추가
            return ResponseEntity.ok(refreshToken);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
