package com.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
  // 사용자 아이디로 조회
  Optional<UserEntity> findByUsername(String username);

  // 이메일로 조회
  Optional<UserEntity> findByEmail(String email);

  // 아이디 중복 확인
  boolean existsByUsername(String username);

}
