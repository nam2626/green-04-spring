package com.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.service.AuthService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<Map<String,String>> signup(@RequestBody String entity) {
      
      return ResponseEntity.status(HttpStatus.CREATED).body(
        Map.of("message","회원가입이 완료되었습니다.")
      );
  }
  

}
