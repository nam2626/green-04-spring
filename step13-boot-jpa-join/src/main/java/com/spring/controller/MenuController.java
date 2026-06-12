package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.entity.MenuItem;
import com.spring.service.MenuItemService;

import jakarta.validation.Valid;

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
	
	@GetMapping("/new")
	public ModelAndView form(ModelAndView view) {
		view.addObject("menu", new MenuItem());
		view.setViewName("menu/form");
		return view;
	}
	
	@PostMapping
	public String save(@Valid @ModelAttribute("menu") MenuItem menuItem,
			BindingResult br, RedirectAttributes ra) {
		if(br.hasErrors()) {
			return "menu/form";
		}
		menuItemService.save(menuItem);
		ra.addFlashAttribute("message", "메뉴 정보 추가 완료");
		return "redirect:/menus";
	}
	
}
