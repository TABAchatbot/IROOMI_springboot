package com.example.demo1.Controller;

import com.example.demo1.Dto.AuthenticationRequest;
import com.example.demo1.Dto.AuthenticationResponse;
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
            String token = authenticationService.authenticateUser(request.getId(), request.getPassword());
            AuthenticationResponse response = new AuthenticationResponse(token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/verifyAccessToken")
    public ResponseEntity<?> verifyAccessToken(@RequestBody String accessToken) {
        try {
            String username = authenticationService.verifyAccessToken(accessToken);
            // Access Token이 유효한 경우에 대한 처리 추가
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
