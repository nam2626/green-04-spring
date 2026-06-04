package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.ProductDTO;

//@RestController = @Controller + @ResponseBody
//메서드 리턴 값이 Jackson을 통해서 JSON으로 변환됨
@RestController
@RequestMapping("/api/products")
public class ProductRestController {
	private final List<ProductDTO> products = List.of(
	        new ProductDTO(1L, "아메리카노", 4000, "음료"),
	        new ProductDTO(2L, "카페라떼", 4500, "음료"),
	        new ProductDTO(3L, "치즈케이크", 5500, "디저트")
	    );
	
	/**
	 * 전체 상품 목록 JSON으로 반환
	 * @return 전체 상품목록 리스트
	 */
	@GetMapping
	public List<ProductDTO> list() {
		return products;
	}
	
	/**
	 * 	GET /api/products/{id} -> 특정 상품 JSON을 반환
	 */
	@GetMapping("/{id}")
	public ProductDTO detail(@PathVariable long id) {
		return products.stream().filter(item -> item.getId() == id)
				.findFirst().orElse(new ProductDTO(-1, "상품정보없음", 0, null));
	}
	
	/**
	 * POST /api/products -> 상품 등록 후 JSON으로 반환
	 * @RequestBody : 요청 본문의 JSON을 ProductDTO로 역직렬화
	 */
	@PostMapping
	public ProductDTO create(@RequestBody ProductDTO dto) {
		dto.setId(products.size()+1);
		products.add(dto);
		return dto;		
	}
}












