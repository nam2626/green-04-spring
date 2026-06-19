package com.spring.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

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

}






