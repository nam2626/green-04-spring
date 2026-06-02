package com.spring.review.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.review.dto.ProductDTO;

/*
 * [복습 포인트] @RequestMapping("/products")
 * - 이 컨트롤러의 모든 URL은 /products 로 시작한다.
 * - @GetMapping("/search") → 실제 URL: GET /products/search
 * - @GetMapping("/{id}")   → 실제 URL: GET /products/1, /products/2, ...
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    // 실제 서비스에서는 DB에서 조회하지만 여기서는 복습 목적으로 고정 데이터 사용
    private final List<ProductDTO> productList = List.of(
            new ProductDTO(1L, "에스프레소", "커피", 2500, "진한 에스프레소 샷"),
            new ProductDTO(2L, "아이스 아메리카노", "커피", 3500, "시원한 아이스 아메리카노"),
            new ProductDTO(3L, "카페라떼", "커피", 4000, "부드러운 밀크폼"),
            new ProductDTO(4L, "딸기 에이드", "음료", 4500, "새콤달콤 딸기 에이드"),
            new ProductDTO(5L, "크로플", "디저트", 5000, "바삭한 크로플")
    );

    /*
     * [복습 포인트] ModelAndView
     * - Model(데이터) + 뷰 이름을 하나의 객체로 묶어서 반환
     * - view.addObject(k, v)  ↔  model.addAttribute(k, v)
     * - view.setViewName(...)  ↔  return "...";
     */
    @GetMapping
    public ModelAndView list(ModelAndView view) {
        view.addObject("products", productList);
        view.setViewName("products/list");
        return view;
    }

    /*
     * [복습 포인트] @RequestParam
     * - 쿼리스트링 ?keyword=커피 에서 keyword 값을 받는다.
     * - defaultValue: 파라미터가 없을 때 사용할 기본값
     *
     * 비교: 파라미터명과 변수명이 같으면 @RequestParam 없이도 바인딩된다.
     *   public ModelAndView search(String keyword, ModelAndView view) { ... }
     */
    @GetMapping("/search")
    public ModelAndView search(
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            ModelAndView view) {
        List<ProductDTO> result = productList.stream()
                .filter(p -> p.getName().contains(keyword) || p.getCategory().contains(keyword))
                .toList();
        view.addObject("products", result);
        view.addObject("keyword", keyword);
        view.setViewName("products/list");
        return view;
    }

    /*
     * [복습 포인트] @PathVariable
     * - URL 경로의 {id} 부분을 메서드 파라미터로 받는다.
     * - GET /products/3 → productId = 3
     *
     * @RequestParam과의 차이:
     *   @RequestParam → URL의 쿼리스트링(?key=value)
     *   @PathVariable → URL의 경로 자체(/resource/{id})
     */
    @GetMapping("/{productId}")
    public ModelAndView detail(@PathVariable("productId") long productId, ModelAndView view) {
        ProductDTO found = productList.stream()
                .filter(p -> p.getId() == productId)
                .findFirst()
                .orElse(null);
        view.addObject("product", found);
        view.setViewName("products/detail");
        return view;
    }
}
