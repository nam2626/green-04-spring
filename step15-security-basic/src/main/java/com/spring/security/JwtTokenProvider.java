package com.spring.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
  private final SecretKey secretKey;
  private final long accessExpiration;
  private final long refreshExpiration;
  
  public JwtTokenProvider(@Value("${jwt.secret}") String secretKey, 
    @Value("${jwt.access-expiration}") long accessExpiration, 
    @Value("${jwt.refresh-expiration}") long refreshExpiration) {
    this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    this.accessExpiration = accessExpiration;
    this.refreshExpiration = refreshExpiration;
  }
  /**
     * Access Token 생성 (만료: 30분)
     * Payload: sub(username), role, iat, exp
     */
  public String generateAccessToken(UserDetails details){
    String role = details.getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse("ROLE_USER");

    return Jwts.builder().subject(details.getUsername()).claim("role", role).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+accessExpiration)).signWith(secretKey).compact();
  }

   /**
     * Refresh Token 생성 (만료: 7일)
     * Payload: sub(username), iat, exp — role은 포함하지 않음
     */
  public String generateRefreshToken(UserDetails details){
    return Jwts.builder().subject(details.getUsername()).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+refreshExpiration)).signWith(secretKey).compact();
  }

  /**
     * 내부: Claims 파싱 (서명 검증 + 만료 확인)
     * 만료됐으면 ExpiredJwtException, 위변조됐으면 SecurityException 발생
     */
  public Claims parseClaims(String token){
    return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
  }

  /**
     * 토큰 유효성 검증
     * @return 유효하면 true, 만료/위변조/형식오류 시 false
     */
  public boolean validateToken(String token){
    try {
      parseClaims(token);    
      return true;
    } catch (ExpiredJwtException e) {
      System.out.println("JWT 만료 : " + e.getMessage());
    } catch(JwtException | IllegalArgumentException e){
      System.out.println("유효하지 않은 JWT : " + e.getMessage());
    }
    return false;
  }

  //토큰에서 사용자 이름 추출
  public String getUsername(String token){
    return parseClaims(token).getSubject();
  }
  
  // Access Token에서 role 추출
  public String getRole(String token){
    return parseClaims(token).get("role", String.class);
  }

}






