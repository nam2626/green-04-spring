package com.spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
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

    public ModelAndView list(ModelAndView view){
        List<MenuDTO> list = null;

        list = menuService.findAll();
        view.setViewName("menu/list");
        view.addObject("menus", list);
        return view;
    }
}
