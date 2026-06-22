package com.spring.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 *  DB에서 사용자를 조회해 스프링 시큐리티에 반환하는 서비스
 * 
 *  AuthenticationManager가 로그인 처리시에 이 메서드를 자동으로 호출
 *  AuthController.login()
 *  -> AuthenticationManager.authenticate()
 *  -> UserDetailServiceImpl.loadUserByUsername()
 *  -> PasswordEncoder.matches()로 비밀번호 검증
 * 
 */
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService{
  
  private final UserRepository userRepository;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다."));
  }

}
