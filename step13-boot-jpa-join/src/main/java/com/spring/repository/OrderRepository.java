package com.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	 // ★ JOIN FETCH — 주문 목록 + 회원 (N+1 방지)
	@Query("select o from Order o join fetch o.member order by o.orderDate DESC")
	List<Order> findAllWithMember();
	
	// ★ 다중 JOIN FETCH — 주문 + 회원 + 주문항목 + 메뉴 한번에
	@Query("select distinct o from Order o "
			+ "join fetch o.member "
			+ "join fetch o.orderItems oi "
			+ "join fetch oi.menuItem "
			+ "order by o.orderDate desc")
	List<Order> findAllWithDetails();
	
    // ★ 상세 조회 — 주문항목 + 메뉴까지 함께 로딩
	@Query("select distinct o from Order o "
			+ "join fetch o.member "
			+ "join fetch o.orderItems oi "
			+ "join fetch oi.menuItem "
			+ "where o.id = :id")
	Optional<Order> findByIdWithDetails(@Param("id") Long id);
	
	// 상태별 조회 - 상태값이 일치하는 주문 항목만 회원과 같이 로딩, 정렬은 최근 주문일 부터
	
	
	// 회원별 주문 조회
	
	// 검색 (회원 + 상태 필터)
}







