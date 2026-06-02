package com.spring.problem02.controller;

import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

// TODO 1: @Controller 추가
// TODO 2: @RequestMapping("/orders") 추가
public class OrderController {

    private final Map<String, Integer> priceMap = Map.of(
            "불고기버거", 8500,
            "치킨버거", 9000,
            "새우버거", 8000
    );

    // TODO 3: GET /orders/new 요청을 처리하는 @GetMapping 추가
    public String orderForm(Model model) {
        model.addAttribute("menus", priceMap.keySet());
        return "order/form";
    }

    // TODO 4: POST /orders 요청을 처리하는 @PostMapping 추가
    public String order(
            @RequestParam String customerName,
            @RequestParam String menuName,
            @RequestParam(defaultValue = "1") int quantity,
            Model model) {

        int unitPrice = priceMap.getOrDefault(menuName, 0);
        int totalPrice = unitPrice * quantity;

        // TODO 5: customerName, menuName, quantity, totalPrice를 model에 추가
        return "order/result";
    }
}

