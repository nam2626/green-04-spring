package com.spring.problem04.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    // TODO 1: id(Long), name(String), category(String), price(int) 필드 작성
    // TODO 2: 기본 생성자와 전체 필드 생성자 작성
    // TODO 3: getter/setter 작성
	private Long id;
	private String name;
	private String category;
	private int price;
	
}

