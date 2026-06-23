package com.spring.security;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * OAuth2 소셜 로그인 사용자 처리 서비스
 *
 * ┌─────────────────────────────────────────────────────────────────┐
 * │  OAuth2 Authorization Code Flow (6단계)                         │
 * │                                                                 │
 * │  1. 사용자가 "Google로 로그인" 클릭                              │
 * │     → GET /oauth2/authorization/google                          │
 * │                                                                 │
 * │  2. Spring이 Google 로그인 페이지로 리다이렉트                   │
 * │                                                                 │
 * │  3. 사용자가 Google 계정으로 로그인 승인                          │
 * │                                                                 │
 * │  4. Google이 Authorization Code를 가지고 redirect_uri로 리다이렉트│
 * │     → GET /login/oauth2/code/google?code=4/...                  │
 * │                                                                 │
 * │  5. Spring이 Authorization Code로 Google에 Access Token 요청    │
 * │                                                                 │
 * │  6. Google Access Token으로 사용자 정보(sub, email, name) 요청   │
 * │     → loadUser() 호출 ← 여기가 이 클래스의 역할                   │
 * └─────────────────────────────────────────────────────────────────┘
 *
 * Google 사용자 속성 (attributes):
 *   sub    — 사용자 고유 ID (변하지 않음)
 *   email  — 이메일
 *   name   — 이름
 *   picture — 프로필 사진 URL
 */

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{@Override
  
  
  
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'loadUser'");
  }

}
