package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.OrderItem;

/**
 * 주문 상세 항목 레포지토리 인터페이스
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
	
	/**
	 * 통계 기능: 메뉴별 총 판매 수량 집계
	 * Object[] 배열 형태로 결과를 반환 (메뉴명, 합계 수량)
	 */
	@Query("select oi.menuItem.name, sum(oi.quantity)"
			+ " from OrderItem oi"
			+ " group by oi.menuItem.name"
			+ " order by sum(oi.quantity) desc")	
	List<Object[]> findMenuSalesSurmmary();

}
