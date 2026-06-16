package com.spring.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.dto.MemberDTO;
import com.spring.entity.Member;
import com.spring.repository.MemberRepository;


@Service
public class MemberService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  public void register(MemberDTO form) {
    if(memberRepository.existsByUsername(form.getUsername()))
      throw new IllegalArgumentException("중복된 아이디 입니다. 다른 아이디를 입력해 주세요");
    if(memberRepository.existsByNickname(form.getNickname()))
      throw new IllegalArgumentException("중복된 닉네임 입니다. 다른 닉네임을 입력해 주세요");

    Member member = new Member();
    member.setUsername(form.getUsername());
    member.setNickname(form.getNickname());
    member.setPassword(passwordEncoder.encode(form.getPassword()));
    memberRepository.save(member);
  }

}
