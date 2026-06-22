package com.spring.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// 이 클래스의 객체를 데이터베이스의 users 테이블 한 행과 연결한다.
@Entity
@Table(name = "users")
// Lombok이 각 필드의 getter와 setter를 자동으로 만들어 준다.
@Getter
@Setter
public class UserEntity implements UserDetails {

  // users 테이블의 기본 키(PK). DB가 새 회원의 번호를 자동으로 증가시킨다.
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 로그인할 때 사용하는 아이디이다. null은 저장할 수 없고 최대 50자까지 허용한다.
  @Column(nullable = false, length = 50)
  private String username;

  // 비밀번호 원문이 아니라 BCrypt로 암호화한 값을 저장할 공간이다.
  @Column(nullable = false, length = 255)
  private String password;

  // unique = true이므로 같은 이메일로 여러 회원을 저장할 수 없다.
  @Column(nullable = false, length = 100, unique = true)
  private String email;

  // 사용자의 권한을 나타낸다. 예: ROLE_USER, ROLE_ADMIN
  @Column(nullable = false, length = 20)
  private String role = "ROLE_USER";

  // 객체가 만들어진 시각을 가입 시각으로 기록하며, 이후 UPDATE 문에서는 변경하지 않는다.
  @Column(updatable = false)
  private LocalDateTime createdAt = LocalDateTime.now();

  // UserDetails 규칙에 맞게 현재 회원이 가진 권한 목록을 Spring Security에 전달한다.
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // 현재는 한 회원이 하나의 role만 가진 구조이므로 요소가 하나인 목록으로 반환한다.
    return List.of(new SimpleGrantedAuthority(role));
  }

 

}
