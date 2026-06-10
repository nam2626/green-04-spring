package com.spring.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {
	private Long id;
	@NotBlank(message = "메뉴명을 입력하세요")
	private String name;
	@NotNull(message = "가격을 입력하세요")
	@Min(value = 0, message = "가격은 100원 이상이어야 합니다.")
	private Integer price;
	private String category;
	private String description;
	private boolean available = true;

}
