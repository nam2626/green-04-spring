package com.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 주문 상세 항목 엔티티 클래스
 * 하나의 주문(Order) 내에서 어떤 메뉴(MenuItem)를 몇 개 주문했는지 기록합니다.
 */
@Entity
@Table(name="order_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 어떤 주문(Order)에 포함된 항목인지 나타냄 (N:1)
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	/**
	 * 주문한 메뉴(MenuItem) 정보 (N:1)
	 */
	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_item_id", nullable = false)
	private MenuItem menuItem;
	
	@NonNull
	@Column(nullable = false)
	private Integer quantity; // 주문 수량
	
	@NonNull
	@Column(name = "unit_price", nullable = false)
	private Integer unitPrice; // 주문 시점의 메뉴 가격 (메뉴 가격이 나중에 바뀌어도 주문 당시 가격 유지)
	
}
