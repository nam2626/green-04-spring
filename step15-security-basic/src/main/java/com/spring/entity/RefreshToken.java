package com.spring.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
@NoArgsConstructor
public class RefreshToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 토큰 소유자 - users 테이블과 FK
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false) 
  private UserEntity user;
  
  // refresh token 문자열 (JWT)
  @Column(nullable = false, unique = true, length = 600)
  private String token;

  // 만료시간
  @Column(nullable = false)
  private LocalDateTime expiresAt;

  public boolean isExpired(){
    return LocalDateTime.now().isAfter(expiresAt);
  }
}
