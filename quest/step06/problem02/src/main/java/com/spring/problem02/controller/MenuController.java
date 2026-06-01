package com.spring.problem02.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// TODO 1: @Controller 어노테이션을 추가하라.
//         → Spring MVC가 이 클래스를 요청 처리 Handler로 인식한다.

// TODO 2: @RequestMapping("/menu") 를 클래스 레벨에 추가하라.
//         → 이 클래스의 모든 메서드는 /menu 로 시작하는 URL을 처리한다.
public class MenuController {

    // TODO 3: GET /menu 요청을 처리하는 메서드를 작성하라.
    //   - @GetMapping 추가 (메서드 레벨 URL 없음 → 클래스 레벨 /menu 만 사용)
    //   - Model 파라미터 추가
    //   - model.addAttribute("menuName", "불고기버거")
    //   - model.addAttribute("price", 8500)
    //   - model.addAttribute("category", "버거")
    //   - return "menu"  ← ViewResolver가 /WEB-INF/views/menu.jsp 로 변환


    // TODO 4: GET /menu/order?item=불고기버거&qty=2 요청을 처리하는 메서드를 작성하라.
    //   - @GetMapping("/order") 추가
    //   - @RequestParam String item 파라미터 추가
    //   - @RequestParam(defaultValue = "1") int qty 파라미터 추가
    //   - Model 파라미터 추가
    //   - model.addAttribute("itemName", item)
    //   - model.addAttribute("quantity", qty)
    //   - model.addAttribute("totalPrice", qty * 8500)
    //   - return "order"

}
