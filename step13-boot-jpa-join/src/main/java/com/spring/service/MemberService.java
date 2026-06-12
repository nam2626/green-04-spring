package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.Member;
import com.spring.repository.MemberRepository;

/**
 * 회원 서비스 클래스
 * 비즈니스 로직을 처리하며, 레포지토리를 통해 DB에 접근합니다.
 * @Transactional(readOnly = true): 조회 전용 트랜잭션 (성능 최적화)
 */
@Transactional(readOnly = true)
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;

	// 생성자 주입 방식 (권장되는 의존성 주입 방식)
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	/**
	 * 모든 회원 목록 조회
	 */
	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	/**
	 * 회원 삭제
	 * @Transactional: 쓰기 작업이 포함되므로 별도로 선언 (readOnly = false)
	 */
	@Transactional
	public void delete(Long id) {
		memberRepository.deleteById(id);
	}

	/**
	 * 회원 저장 (등록)
	 */
	@Transactional
	public void save(Member member) {
		memberRepository.save(member);
	}

	/**
	 * 단일 회원 조회
	 */
	public Member findById(Long id) {
		return memberRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
	}

	/**
	 * 회원 정보 수정
	 * JPA의 더티 체킹(Dirty Checking)을 이용: 영속성 컨텍스트 내의 엔티티 값이 변경되면 트랜잭션 종료 시 자동으로 DB에 반영됨
	 */
	@Transactional
	public void update(Member member) {
		Member raw = findById(member.getId()); // 영속상태 엔티티 조회
		raw.setName(member.getName());
		raw.setEmail(member.getEmail());
		raw.setPhone(member.getPhone());
		// 별도의 save() 호출 없이도 자동으로 UPDATE 쿼리 실행
	}
	
}
