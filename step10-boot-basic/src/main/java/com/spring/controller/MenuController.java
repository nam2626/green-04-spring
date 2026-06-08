package com.spring.controller;

import com.spring.dto.MenuDTO;
import com.spring.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/menus")
public class MenuController {

    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ModelAndView list(ModelAndView view){
        List<MenuDTO> list = menuService.findAll();

        view.addObject("menus",list);
        view.setViewName("menu/list");

        return view;
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(
                "successMessage", "메뉴가 삭제되었습니다.");
        menuService.delete(id);
        return "redirect:/menus";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView updateForm(@PathVariable Long id, ModelAndView view){
        MenuDTO menu = menuService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("메뉴를 찾을 수 없습니다. id=" + id));
        view.addObject("menu", menu);
        view.addObject("categories", List.of("커피", "음료", "디저트"));
        view.setViewName("menu/form");
        return view;
    }
}




