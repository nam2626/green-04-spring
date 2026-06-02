package com.spring.problem04.dto;

import lombok.Data;

@Data
public class OrderForm {

    // TODO 4: customerName(String), menuId(Long), quantity(int), requestMessage(String) 필드 작성
    // TODO 5: 기본 생성자 작성
    // TODO 6: getter/setter 작성
	private String customerName;
	private Long menuId;
	private int quantity;
	private String requestMessage;
	

}

