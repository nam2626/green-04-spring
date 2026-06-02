package com.spring.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dto.MenuDTO;

@Controller
@RequestMapping("/menus")
public class MenuController {
	private final List<MenuDTO> menuList = List.of(
			new MenuDTO(1L, "불고기버거", "버거", 8500),
			new MenuDTO(2L, "치킨버거", "버거", 9000), 
			new MenuDTO(3L, "새우버거", "버거", 8000),
			new MenuDTO(4L, "아이스아메리카노", "음료", 3500));

	@GetMapping
	public ModelAndView list(ModelAndView view) {
		// 데이터 셋팅
		view.addObject("menus", menuList);
		// 뷰 경로
		view.setViewName("menus/list");
		return view;
	}
	
	@GetMapping("/search")
	public ModelAndView search(ModelAndView view, String keyword) {
		List<MenuDTO> list = null;
		//list에 검색 결과 저장
		
		// 데이터 셋팅
		view.addObject("menus", list);
		// 뷰 경로
		view.setViewName("menus/list");
		return view;
	}
	
	
}





