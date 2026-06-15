package com.spring.entity;

/**
 * 회원번호(Long,PK)
 * 로그인 아이디(String, NotNull, Unique)
 * 암호(String, NotNull)
 * 닉네임(String, NotNull)
 * 권한(String, role = USER | ADMIN, default = 'USER')
 * 가입일(LocalDateTime, NotNull, default = CURRENT_TIMESTAMP)
 */
public class Member {

}
