package com.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.RefreshToken;
import com.spring.entity.UserEntity;

// Refresh Token을 DB에 저장하고 조회할 저장소
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long>{

  // 토큰 값으로 조회
  Optional<RefreshToken> findByToken(String token);

  // 사용자의 기존 refresh token을 삭제
  void deleteByUser(UserEntity user);
}
