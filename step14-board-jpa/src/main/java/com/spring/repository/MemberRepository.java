package com.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

  boolean existsByUsername(String username);

  boolean existsByNickname(String nickname);

  Optional<Member> findByUsername(String username);


}
