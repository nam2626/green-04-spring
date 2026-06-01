package com.spring.problem03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * [실습] 상품 목록과 상세 정보를 처리하는 컨트롤러
 * 
 * dispatcher-context.xml의 ViewResolver 설정에 의해
 * 반환된 문자열(뷰 이름)이 실제 JSP 파일 경로로 변환됩니다.
 * 예: "product/list" -> "/WEB-INF/views/product/list.jsp"
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    /**
     * 상품 목록 화면 요청 처리
     * URL: GET /product/list
     * 
     * Model: 컨트롤러에서 JSP로 데이터를 전달할 때 사용하는 객체입니다.
     */
    @GetMapping("/list")
    public String list(Model model) {
        // 실습용 상품 리스트 데이터 생성
        List<String> products = List.of("아메리카노", "카페라떼", "녹차라떼");
        
        // model.addAttribute(key, value): JSP에서 ${products}로 사용할 수 있게 데이터를 담습니다.
        model.addAttribute("products", products);
        
        return "product/list";
    }

    /**
     * 상품 상세 화면 요청 처리
     * URL: GET /product/detail?name=상품명
     * 
     * @RequestParam: URL 쿼리 파라미터(?name=...)의 값을 메서드 파라미터에 할당합니다.
     */
    @GetMapping("/detail")
    public String detail(@RequestParam String name, Model model) {
        // 전달받은 상품명을 다시 JSP로 전달합니다.
        model.addAttribute("productName", name);
        model.addAttribute("price", 4500);
        
        return "product/detail";
    }
}
