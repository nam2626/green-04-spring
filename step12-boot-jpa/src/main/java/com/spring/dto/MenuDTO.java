package com.spring.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "menu_item")
public class MenuDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	@NotBlank(message = "메뉴명을 입력하세요")
	private String name;
	
	@Column(nullable = false)
	@NotNull(message = "가격을 입력하세요")
	@Min(value = 0, message = "가격은 100원 이상이어야 합니다.")
	private Integer price;
	
	@Column(length = 50)
	private String category;
	
	@Column(length = 500)
	private String description;

	@Column
	private boolean available = true;
	
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;
	

}






