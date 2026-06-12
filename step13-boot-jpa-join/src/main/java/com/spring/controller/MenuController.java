package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.service.MenuItemService;

@RequestMapping("/menus")
@Controller
public class MenuController {
	private final MenuItemService menuItemService;

	public MenuController(MenuItemService menuItemService) {
		this.menuItemService = menuItemService;
	}
	
	@GetMapping
	public ModelAndView list(ModelAndView view) {
		view.addObject("menus", menuItemService.findAll());
		view.setViewName("menu/list");
		return view;
	}
	
}
