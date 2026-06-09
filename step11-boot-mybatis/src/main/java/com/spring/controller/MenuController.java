package com.spring.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView list(ModelAndView view,
            @RequestParam(name = "category", required = false) String category, 
            @RequestParam(name = "keyword", required = false) String keyword, 
            @RequestParam(name = "available", required = false) Boolean available) {
        List<MenuDTO> list = null;
        
        if((category != null && !category.isEmpty()) 
            || (keyword != null && !keyword.isEmpty()) 
            || available != null) {
            list = menuService.search(keyword, category, available);
        } else {
            list = menuService.findAll();
        }
        
        view.setViewName("menu/list");
        // 카테고리 등록
        view.addObject("categories", List.of("버거", "사이드", "음료", "디저트"));
        view.addObject("menus", list);
        return view;
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        int result = menuService.deleteById(id);
        if(result == 0) 
            redirectAttributes.addFlashAttribute(
                "failMessage", "메뉴 삭제에 실패했습니다. 메뉴를 다시 확인해주세요.");
        else
            redirectAttributes.addFlashAttribute(
                "successMessage", "메뉴가 성공적으로 삭제되었습니다.");
        return "redirect:/menus";
    }
}
