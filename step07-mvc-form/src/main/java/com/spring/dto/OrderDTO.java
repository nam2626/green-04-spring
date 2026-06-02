package com.spring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderDTO {
	private String customerName;
	private int quantity;
	private String requestMessage;
	private long menuId;
}




