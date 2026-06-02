package com.spring.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dto.MenuDTO;

/**
 * 메뉴 관련 요청을 처리하는 컨트롤러
 */
@Controller
@RequestMapping("/menus")
public class MenuController {
	// 샘플 데이터 (실제 프로젝트에서는 DB에서 가져옴)
	private final List<MenuDTO> menuList = List.of(
			new MenuDTO(1L, "불고기버거", "버거", 8500),
			new MenuDTO(2L, "치킨버거", "버거", 9000), 
			new MenuDTO(3L, "새우버거", "버거", 8000),
			new MenuDTO(4L, "아이스아메리카노", "음료", 3500));

	/**
	 * ModelAndView:
	 * 데이터(Model)와 이동할 페이지(View) 정보를 한 번에 담아서 리턴할 수 있는 객체입니다.
	 */
	@GetMapping
	public ModelAndView list(ModelAndView view) {
		// view.addObject("key", value): 데이터 셋팅
		view.addObject("menus", menuList);
		// view.setViewName("path"): 뷰 경로 설정 (WEB-INF/views/menus/list.jsp)
		view.setViewName("menus/list");
		return view;
	}
	
	/**
	 * 메뉴 검색 처리
	 * @param keyword: 쿼리 스트링(?keyword=...)으로 전달된 검색어
	 */
	@GetMapping("/search")
	public ModelAndView search(ModelAndView view, String keyword) {
		// Java Stream API를 이용한 필터링 (메뉴명 또는 카테고리에 키워드가 포함된 경우)
		List<MenuDTO> list = menuList.stream()
				.filter(dto -> dto.getName().contains(keyword) || 
						dto.getCategory().contains(keyword)).toList();
		
		view.addObject("menus", list);
		view.addObject("keyword", keyword); // 검색어를 다시 전달하여 입력창에 유지
		view.setViewName("menus/list");
		return view;
	}
	
	/**
	 * @PathVariable: 
	 * URL 경로에 포함된 변수값을 추출합니다.
	 * 예: /menus/1 -> menuId에 1이 할당됨
	 */
	@GetMapping("/{menuId}")
	public ModelAndView detail(@PathVariable("menuId") long menuId, ModelAndView view) {
		for(MenuDTO dto : menuList) {
			if(dto.getId() == menuId) {
				view.addObject("menu", dto);
				break;
			}
		}
		view.setViewName("menus/detail");
		return view;
	}
	
	
	
}








