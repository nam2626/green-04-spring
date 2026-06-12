package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.entity.MenuItem;
import com.spring.service.MenuItemService;

import jakarta.validation.Valid;

/**
 * 메뉴 관리 컨트롤러
 */
@RequestMapping("/menus")
@Controller
public class MenuController {
	
	private final MenuItemService menuItemService;

	public MenuController(MenuItemService menuItemService) {
		this.menuItemService = menuItemService;
	}
	
	/**
	 * 메뉴 목록 조회
	 */
	@GetMapping
	public ModelAndView list(ModelAndView view) {
		view.addObject("menus", menuItemService.findAll());
		view.setViewName("menu/list");
		return view;
	}
	
	/**
	 * 메뉴 등록 폼 요청
	 */
	@GetMapping("/new")
	public ModelAndView form(ModelAndView view) {
		view.addObject("menu", new MenuItem());
		view.setViewName("menu/form");
		return view;
	}
	
	/**
	 * 메뉴 등록 실행
	 */
	@PostMapping
	public String save(@Valid @ModelAttribute("menu") MenuItem menuItem,
			BindingResult br, RedirectAttributes ra) {
		if(br.hasErrors()) {
			return "menu/form";
		}
		menuItemService.save(menuItem);
		ra.addFlashAttribute("message", "메뉴가 성공적으로 추가되었습니다.");
		return "redirect:/menus";
	}
	
	/**
	 * 메뉴 수정 폼 요청
	 */
	@GetMapping("/{id}/edit")
	public ModelAndView edit(ModelAndView view, @PathVariable Long id) {
		view.addObject("menu", menuItemService.findById(id));
		view.setViewName("menu/form");
		return view;
	}
	
	/**
	 * 메뉴 수정 실행
	 */
	@PostMapping("/{id}/edit")
	public String update(@Valid @ModelAttribute("menu") MenuItem menu, 
				BindingResult br, RedirectAttributes ra, @PathVariable Long id) {
		if(br.hasErrors()) {
			return "menu/form";
		}
		menu.setId(id);
		menuItemService.update(menu);
		ra.addFlashAttribute("message", "메뉴 정보가 수정되었습니다.");
		return "redirect:/menus";
	}
	
	/**
	 * 메뉴 삭제 실행
	 */
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes ra) {
		menuItemService.delete(id);
		ra.addFlashAttribute("message", "메뉴가 삭제되었습니다.");
		return "redirect:/menus";
	}
}
