package com.spring.controller;

import com.spring.dto.MenuDTO;
import com.spring.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView list(ModelAndView view, String keyword){
        List<MenuDTO> list = null;
        if  (keyword != null && !keyword.isEmpty()) {
            list = menuService.search(keyword);
            view.addObject("keyword", keyword);
        } else {
            list = menuService.findAll();
        }

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
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @Valid @ModelAttribute MenuDTO menu,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
        System.out.println("bindingResult = " + bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("menu", menu);
            model.addAttribute("categories", List.of("커피", "음료", "디저트"));
            return "menu/form";
        }
        redirectAttributes.addFlashAttribute(
                "successMessage", "메뉴가 수정되었습니다.");
        menuService.update(id, menu);
        return "redirect:/menus";
    }

    @GetMapping("/new")
    public ModelAndView newForm(ModelAndView view){
        view.addObject("menu", new MenuDTO());
        view.addObject("categories", List.of("커피", "음료", "디저트"));
        view.setViewName("menu/form");
        return view;
    }

//    데이터 등록
    @PostMapping
    public String create(@Valid @ModelAttribute MenuDTO menu, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
        System.out.println("bindingResult = " + bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("menu", menu);
            model.addAttribute("categories", List.of("커피", "음료", "디저트"));
            return "menu/form";
        }
        redirectAttributes.addFlashAttribute(
                "successMessage", "메뉴가 등록되었습니다.");

        menuService.save(menu);
        return "redirect:/menus";
    }


}




