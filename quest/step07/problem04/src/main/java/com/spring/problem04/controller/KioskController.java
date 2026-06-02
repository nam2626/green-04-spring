package com.spring.problem04.controller;

import com.spring.problem04.dto.OrderForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/kiosk")
public class KioskController {

    // TODO 7: MenuItem 4개를 담은 menuList 작성
    // 불고기버거/버거/8500, 치킨버거/버거/9000, 새우버거/버거/8000, 아메리카노/음료/3500

    @GetMapping("/menus")
    public String menus(Model model) {
        // TODO 8: model에 "menus" 추가
        return "kiosk/menus";
    }

    @GetMapping("/menus/{menuId}")
    public String detail(@PathVariable Long menuId, Model model) {
        // TODO 9: menuId에 해당하는 메뉴를 찾아 model에 "menu"로 추가
        return "kiosk/detail";
    }

    @GetMapping("/menus/search")
    public String search(@RequestParam(defaultValue = "") String keyword, Model model) {
        // TODO 10: 메뉴명 또는 카테고리에 keyword가 포함된 메뉴만 model에 "menus"로 추가
        model.addAttribute("keyword", keyword);
        return "kiosk/menus";
    }

    @GetMapping("/orders/new")
    public String orderForm(@RequestParam(required = false, defaultValue = "1") Long menuId, Model model) {
        // TODO 11: 전체 메뉴 목록과 선택된 menuId를 model에 추가
        return "kiosk/form";
    }

    @PostMapping("/orders")
    public String order(@ModelAttribute OrderForm form, Model model) {
        // TODO 12: form.menuId로 선택 메뉴를 찾고 총 금액을 계산
        // TODO 13: model에 orderForm, menu, totalPrice 추가
        return "kiosk/result";
    }
}

