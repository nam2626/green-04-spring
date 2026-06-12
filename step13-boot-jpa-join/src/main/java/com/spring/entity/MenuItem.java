package com.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 메뉴 아이템 엔티티 클래스
 * 카페에서 판매하는 개별 메뉴(아메리카노, 카페라떼 등) 정보를 담습니다.
 */
@Entity
@Table(name="menu_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String name; // 메뉴 이름
	
	@Column(nullable = false)
	@NotNull(message = "가격을 입력하세요")
	@Min(value = 100, message = "가격은 100원 이상이어야 합니다.") // 최소 금액 검증
	private Integer price; // 메뉴 가격

	@Column(length = 50)
	private String category; // 메뉴 카테고리 (커피, 케이크, 푸드 등)
	
	@Column(nullable = false)
	private boolean available = true; // 판매 가능 여부 (품절 여부 체크)

	/**
	 * 편리한 객체 생성을 위한 커스텀 생성자
	 */
	public MenuItem(String name, Integer price, String category) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
	}
	
}
