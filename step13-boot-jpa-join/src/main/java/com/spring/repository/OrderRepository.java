package com.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.entity.Order;
import com.spring.entity.OrderStatus;

/**
 * 주문 레포지토리 인터페이스
 */
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	 /** 
	  * ★ JOIN FETCH — 주문 목록 조회 시 회원 정보(member)를 한꺼번에 로딩 
	  */
	@Query("select o from Order o join fetch o.member order by o.orderDate DESC")
	List<Order> findAllWithMember();
	
	/** 
	 * ★ 복합 FETCH JOIN — 주문 + 회원 + 주문항목 + 메뉴 정보를 단 한 번의 쿼리로 조회 
	 * 대규모 데이터에서는 주의해서 사용해야 하나, 연관관계 탐색 시 성능 효율이 매우 좋음
	 */
	@Query("select distinct o from Order o "
			+ "join fetch o.member "
			+ "join fetch o.orderItems oi "
			+ "join fetch oi.menuItem "
			+ "order by o.orderDate desc")
	List<Order> findAllWithDetails();
	
    /** 
     * 주문 상세 조회 (특정 ID)
     */
	@Query("select distinct o from Order o "
			+ "join fetch o.member "
			+ "join fetch o.orderItems oi "
			+ "join fetch oi.menuItem "
			+ "where o.id = :id")
	Optional<Order> findByIdWithDetails(@Param("id") Long id);
	
	/** 
	 * 검색 기능: 회원 ID와 주문 상태로 필터링
	 * :memberId IS NULL 처리를 통해 동적 쿼리와 유사한 효과를 냄
	 */
	@Query("select o from Order o join fetch o.member m "
			+ "where (:memberId IS NULL or m.id = :memberId) "
			+ "and (:status is null or o.status = :status) "
			+ "order by o.orderDate desc")
	List<Order> search(@Param("memberId") Long memberId, 
			@Param("status") OrderStatus status);
}
