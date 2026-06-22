package com.spring.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.SignupRequest;
import com.spring.entity.UserEntity;
import com.spring.repository.RefreshTokenRepository;
import com.spring.repository.UserRepository;
import com.spring.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;


// 로그인, 회원가입, 토큰 발급처럼 인증과 관련된 업무 로직을 모을 서비스이다.
@Service
// 이 클래스의 public 메서드는 기본적으로 하나의 DB 트랜잭션 안에서 실행된다.
@Transactional
@RequiredArgsConstructor
public class AuthService {
  // 회원 정보를 저장하거나 조회할 때 사용한다.
  private final UserRepository userRepository;
  // Refresh Token을 저장하거나 조회할 때 사용한다. 구체적인 기능은 다음 작업에서 작성한다.
  private final RefreshTokenRepository refreshTokenRepository;
  // 비밀번호 원문을 안전한 해시 값으로 바꾸고, 로그인 시 일치 여부를 확인한다.
  private final PasswordEncoder passwordEncoder;
  // 아이디와 비밀번호를 Spring Security의 인증 절차에 전달한다.
  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  public void signup(SignupRequest signupRequest) {
    if(userRepository.existsByUsername(signupRequest.getUsername())){
      throw new IllegalArgumentException("이미 사용중인 아이디 입니다. " + signupRequest.getUsername());
    }

    UserEntity entity = new UserEntity();
    entity.setUsername(signupRequest.getUsername());
    entity.setEmail(signupRequest.getEmail());
    entity.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

    userRepository.save(entity);
  }

  

}
