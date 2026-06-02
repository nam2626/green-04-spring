package com.spring.review.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// [복습 포인트] 폼 데이터를 받는 DTO
// HTML form의 name 속성값과 필드명이 같으면 Spring이 자동으로 값을 채운다 (DTO 바인딩)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CartDTO {
    private String customerName; // <input name="customerName"> 과 매칭
    private long productId;      // <select name="productId"> 와 매칭
    private int quantity;        // <input name="quantity"> 와 매칭
    private String memo;         // <input name="memo"> 와 매칭
}
