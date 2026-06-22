package com.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.LoginRequest;
import com.spring.dto.SignupRequest;
import com.spring.dto.TokenReponse;
import com.spring.entity.UserEntity;
import com.spring.service.AuthService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
/**
 *  인증 REST API
 *  
 *  POST /auth/signup - 회원가입
 */
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  // 아이디, 암호, 이메일
  @PostMapping("/signup")
  public ResponseEntity<Map<String,String>> signup(@RequestBody SignupRequest signupRequest) {
      
      authService.signup(signupRequest);

      return ResponseEntity.status(HttpStatus.CREATED).body(
        Map.of("message","회원가입이 완료되었습니다.")
      );
  }
  
  @PostMapping("/login")
  public ResponseEntity<TokenReponse> login(@RequestBody LoginRequest req) {
    System.out.println("login");
      return ResponseEntity.ok(authService.login(req));
  }
  
  @PostMapping("/logout")
  public ResponseEntity<Map<String,String>> logout(@AuthenticationPrincipal UserEntity currentUser) {
      authService.logout(currentUser);      
      return ResponseEntity.ok( Map.of("message","로그아웃 완료"));
  }
  

}
