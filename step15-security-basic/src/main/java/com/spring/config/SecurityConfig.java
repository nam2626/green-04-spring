package com.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

// Spring Security의 인증 방식, URL 접근 규칙, 보안 관련 객체를 설정하는 클래스이다.
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  // 모든 요청에서 JWT를 먼저 확인하도록 필터 체인에 등록할 필터이다.
  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http
      // 브라우저 세션/쿠키 대신 Bearer Token을 사용하는 REST API이므로 CSRF 보호를 끈다.
    .csrf(crsf -> crsf.disable())
    // 서버에 로그인 세션을 만들지 않고, 매 요청의 JWT로 인증 상태를 확인한다.
    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    // 요청 URL과 HTTP 메서드에 따라 공개 범위를 정한다.
    .authorizeHttpRequests(auth -> auth
      // 로그인과 회원가입은 아직 토큰이 없는 사용자도 접근해야 하므로 공개한다.
      .requestMatchers("/auth/**").permitAll()
      // 게시글의 목록·상세·검색 등 GET 조회 요청은 로그인하지 않아도 볼 수 있다.
      .requestMatchers(HttpMethod.GET,"/api/posts/**").permitAll()
      // 위에서 공개하지 않은 나머지 요청은 유효한 인증 정보가 있어야 한다.
      .anyRequest().authenticated()
    // 기본 아이디/비밀번호 인증 필터보다 앞에서 JWT 인증 필터가 실행되도록 한다.
    ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    // 비밀번호를 복호화 가능한 형태로 저장하지 않고 BCrypt 단방향 해시로 변환한다.
    return new BCryptPasswordEncoder();
  }

  /**
   * AuthService의 로그인 기능에서 아이디와 비밀번호 인증을 시작할 객체를 Bean으로 등록한다.
   * authenticate()가 호출되면 UserDetailsService로 회원을 찾고 PasswordEncoder로 비밀번호를 비교한다.
   */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }
}
