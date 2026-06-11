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
@Table(name = "cars")
@Entity
public class CarDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;
	
	@Column
    private String brand;
	
	@Column(nullable = false, length = 100)
    @NotBlank(message = "모델명을 반드시 입력하세요")
    private String model;
	
	@Column(nullable = false)
    @NotNull(message = "연식을 반드시 입력하세요")
    @Min(value = 1900, message = "연식은 1900년 이상이어야 합니다")
    private Integer year;
	
	@Column
    private Integer mileage;
	
	@Column(nullable = false)
    @NotNull(message = "가격을 반드시 입력하세요")
    @Min(value = 0, message = "가격은 0 이상이어야 합니다")
    private Integer price;
	@Column
    private LocalDateTime registeredAt;
}
