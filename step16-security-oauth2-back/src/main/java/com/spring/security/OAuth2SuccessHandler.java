package com.spring.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.entity.RefreshToken;
import com.spring.entity.UserEntity;
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

  private final UserRepository userRepository;
  private final RefreshTokenRepository refreshTokenRepository;
  private final JwtTokenProvider jwtTokenProvider;

  @Value("${app.frontend-url}")
  private String frontendUrl;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
        // 1. OAuth2User에서 email 추출
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        // 2. DB에서 UserEntity 조회(CustomOAuth2UserService에 저장되어 있음)
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("OAuth2 사용자를 찾을 수 없습니다."));

        // 3. JWT 발급
        String accessToken = jwtTokenProvider.generateAccessToken(userEntity);
        String refreshToken = jwtTokenProvider.generateRefreshToken(userEntity);
        // 4. RefreshToken을 DB 저장
        RefreshToken refresh = new RefreshToken();
        refreshTokenRepository.deleteByUser(userEntity);
        refresh.setToken(refreshToken);
        refresh.setUser(userEntity);
        refreshTokenRepository.save(refresh);
        
        // 5. 프론트엔드로 리다이렉트(토큰을 쿼리스트링으로 보냄, 단 실제 운영환경에서는 쿠키로 사용하는것을 권장함)
        String url = UriComponentsBuilder.fromUriString(frontendUrl + "/oauth2/callback").queryParam("accessToken", accessToken).queryParam("refreshToken", refreshToken).build().toUriString();

        response.sendRedirect(url);
      }
}
