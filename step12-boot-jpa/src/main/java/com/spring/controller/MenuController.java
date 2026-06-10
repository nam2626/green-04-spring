package com.spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.service.MenuService;

@Controller
@RequestMapping("/menus")
public class MenuController {
	
	private final MenuService menuService;

	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@GetMapping
	public String list(Model model, String keyword, String category) {
		model.addAttribute("keyword", keyword);
		model.addAttribute("category", category);
		model.addAttribute("menus", menuService.findAll());
		return "menu/list";
	}
	
	@GetMapping("/{id}/edit")
	public ModelAndView editView(ModelAndView view,@PathVariable Long id) {
		view.addObject("categories", List.of("버거","사이드","음료"));
		view.addObject("menu", menuService.findById(id));
		view.setViewName("menu/form");
		
		return view;
	}
	
}




