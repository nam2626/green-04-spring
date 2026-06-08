package com.spring.controller;

import com.spring.dto.MenuDTO;
import com.spring.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
