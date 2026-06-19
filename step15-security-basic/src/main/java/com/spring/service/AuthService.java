package com.spring.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.repository.RefreshTokenRepository;
import com.spring.repository.UserRepository;


// 로그인, 회원가입, 토큰 발급처럼 인증과 관련된 업무 로직을 모을 서비스이다.
@Service
// 이 클래스의 public 메서드는 기본적으로 하나의 DB 트랜잭션 안에서 실행된다.
@Transactional
public class AuthService {
  // 회원 정보를 저장하거나 조회할 때 사용한다.
  private final UserRepository userRepository;
  // Refresh Token을 저장하거나 조회할 때 사용한다. 구체적인 기능은 다음 작업에서 작성한다.
  private final RefreshTokenRepository refreshTokenRepository;
  // 비밀번호 원문을 안전한 해시 값으로 바꾸고, 로그인 시 일치 여부를 확인한다.
  private final PasswordEncoder passwordEncoder;
  // 아이디와 비밀번호를 Spring Security의 인증 절차에 전달한다.
  private final AuthenticationManager authenticationManager;

  // 생성자 주입을 사용하면 서비스가 동작하는 데 필요한 객체가 빠짐없이 준비된다.
  public AuthService(UserRepository userRepository, RefreshTokenRepository refreshTokenRepository,
      PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.refreshTokenRepository = refreshTokenRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
  }

  // 현재는 인증 기능에 필요한 의존성만 준비한 단계이다.
  // 회원가입과 로그인 메서드는 다음 작업에서 이 아래에 이어서 구현한다.
  

}
