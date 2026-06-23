package com.spring.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.spring.repository.RefreshTokenRepository;
import com.spring.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * OAuth2 로그인 성공 핸들러
 * 
 * OAuth2 인증 성공 후 JWT 발급하고 프론트엔드로 리다이렉트
 */


@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler{
  private final CustomOAuth2UserService customOAuth2UserService;
  private final UserRepository userRepository;
  private final RefreshTokenRepository refreshTokenRepository;
  private final JwtTokenProvider jwtTokenProvider;

  @Value("${app.frontend-url}")
  private String frontendUrl;

  OAuth2SuccessHandler(CustomOAuth2UserService customOAuth2UserService) {
    this.customOAuth2UserService = customOAuth2UserService;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
        // 1. OAuth2User에서 email 추출

        // 2. DB에서 UserEntity 조회(CustomOAuth2UserService에 저장되어 있음)

        // 3. JWT 발급

        // 4. RefreshToken을 DB 저장
        
        // 5. 프론트엔드로 리다이렉트(토큰을 쿼리스트링으로 보냄, 단 실제 운영환경에서는 쿠키로 사용하는것을 권장함)
        

      }
}
