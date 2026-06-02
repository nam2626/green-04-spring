package com.spring.problem03.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderForm {
	
	// TODO 1: customerName, menuName, quantity, requestMessage 필드 작성
	// TODO 2: 기본 생성자 작성
	// TODO 3: 모든 필드의 getter/setter 작성
	private String customerName;
	private String menuName;
	private int quantity;
	private String requestMessage;
}

