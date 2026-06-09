package com.spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dto.MenuDTO;
import com.spring.service.MenuService;

@Controller
@RequestMapping("/menus")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ModelAndView list(ModelAndView view, String category, String keyword) {
        List<MenuDTO> list = null;
        System.out.println("카테고리: " + category);
        System.out.println("키워드: " + keyword);
        if(keyword != null && !keyword.isEmpty()) {
            // 키워드 검색
            list = menuService.search(keyword);
            view.addObject("keyword", keyword);
        } else if(category != null && !category.isEmpty()) {
            // 카테고리 검색
            view.addObject("category", category);
            list = menuService.findByCategory(category);
        } else {
            // 전체 조회
            list = menuService.findAll();
        }
        
        view.setViewName("menu/list");
        // 카테고리 등록
        view.addObject("categories", List.of("버거", "사이드", "음료", "디저트"));
        view.addObject("menus", list);
        return view;
    }
}
