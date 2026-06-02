package com.spring.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// [복습 포인트] Lombok으로 getter/setter/생성자 자동 생성
// DTO 자동 바인딩이 동작하려면 기본 생성자(@NoArgsConstructor)와 setter(@Setter)가 반드시 있어야 한다
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {
    private long id;
    private String name;
    private String category;
    private int price;
    private String description;
}
