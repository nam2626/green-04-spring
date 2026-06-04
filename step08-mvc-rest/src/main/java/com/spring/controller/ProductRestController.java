package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping
	public String list() {
		return "home";
	}
	
}












