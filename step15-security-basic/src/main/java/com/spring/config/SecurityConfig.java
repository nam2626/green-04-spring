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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final JwtAuthenticationFilter authenticationFilter;

  SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    http
      // CSRF 비활성화(REAT API - Bearer 토큰)
    .csrf(crsf -> crsf.disable())
    // 세션 미사용
    .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    // URL별 인증 규칙
    .authorizeHttpRequests(auth -> auth
      // 인증없이 접근이 가능한 경로 
      // 로그인, 회원가입      
      .requestMatchers("/auth/**").permitAll()
      // 게시글 조회는 공개(게시글 목록, 게시글 한건, 검색)
      .requestMatchers(HttpMethod.GET,"/api/posts/**").permitAll()
      // 나머지 경로는 전부 인증 필요
      .anyRequest().authenticated()
    ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  /**
     * AuthenticationManager — AuthService.login()에서
     * authenticationManager.authenticate(token) 호출 시 내부적으로
     * UserDetailsServiceImpl.loadUserByUsername() + PasswordEncoder.matches() 실행
     */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }
}
