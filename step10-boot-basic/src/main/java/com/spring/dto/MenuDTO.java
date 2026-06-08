package com.spring.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class MenuDTO {
    private Long id;
    @NotBlank(message = "메뉴명은 필수 입력값입니다.")
    private String name;
    @NotNull(message = "가격은 필수 입력값입니다.")
    @Min(value = 0, message = "가격은 0원 이상 입력값입니다.")
    private Integer price;
    private String category;
    private String description;
    private boolean available = true;
}
