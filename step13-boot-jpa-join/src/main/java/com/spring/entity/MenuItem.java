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

@Entity
@Table(name="menu_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
	//MenuItem -> OrderItem 역방향 참조
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Column(nullable = false)
	@NotNull(message = "가격을 입력하세요")
	@Min(value = 100, message = "가격은 100원 이상이어야 합니다.")
	private Integer price;

	@Column(length = 50)
	private String category;
	
	@Column(nullable = false)
	private boolean available = true;

	public MenuItem(String name, Integer price, String category) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
	}
	
	
	
}








