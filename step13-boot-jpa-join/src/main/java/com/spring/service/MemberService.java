package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.Member;
import com.spring.repository.MemberRepository;

import jakarta.validation.Valid;

@Transactional(readOnly = true)
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	@Transactional
	public void delete(Long id) {
		memberRepository.deleteById(id);
	}

	@Transactional
	public void save(Member member) {
		memberRepository.save(member);
	}
	
	
}






