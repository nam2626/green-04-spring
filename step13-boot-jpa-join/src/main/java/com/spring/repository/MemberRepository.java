package com.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.Member;

/**
 * 회원 레포지토리 인터페이스
 * JpaRepository를 상속받으면 기본적인 CRUD 메서드가 자동으로 제공됩니다.
 */
public interface MemberRepository extends JpaRepository<Member, Long>{
	
	/**
	 * 쿼리 메서드: 이메일로 회원 조회
	 * findBy + 필드명 형식으로 작성하면 JPA가 자동으로 쿼리 생성
	 */
	Optional<Member> findByEmail(String email);
	
	/**
	 * 생성일 기준 내림차순 정렬하여 모든 회원 조회
	 */
	List<Member> findAllByOrderByCreatedAtDesc();
	
	/**
	 * JPQL을 이용한 커스텀 쿼리
	 * join fetch: 연관된 주문 내역(orders)을 한 번의 쿼리로 함께 가져옴 (N+1 문제 해결)
	 * distinct: 중복 데이터 제거
	 */
	@Query("select distinct m from Member m left join fetch m.orders order by m.createdAt desc")
	List<Member> findAllWithOrders();
}
