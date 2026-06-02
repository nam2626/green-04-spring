package com.spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dto.MenuDTO;
import com.spring.dto.OrderDTO;

/**
 * 주문 처리를 담당하는 컨트롤러
 */
@Controller
@RequestMapping("/orders")
public class OrderController {
	private final List<MenuDTO> menuList = List.of(
			new MenuDTO(1L, "불고기버거", "버거", 8500),
			new MenuDTO(2L, "치킨버거", "버거", 9000), 
			new MenuDTO(3L, "새우버거", "버거", 8000),
			new MenuDTO(4L, "아이스아메리카노", "음료", 3500));
	
	/**
	 * 주문 폼 페이지 이동
	 */
	@GetMapping("/new")
	public ModelAndView orderForm(long menuId, ModelAndView view ) {
		view.addObject("menus", menuList);
		view.addObject("seletedMenuId", menuId);
		
		view.setViewName("order/form");
		return view;
	}
	
	/**
	 * @PostMapping: HTTP POST 요청을 처리합니다. (주로 데이터 저장/등록 시 사용)
	 * 
	 * 커맨드 객체(Command Object):
	 * 매개변수에 OrderDTO와 같은 객체를 선언하면, 
	 * HTML 폼의 input name과 DTO의 필드명이 일치할 경우 자동으로 데이터가 주입됩니다.
	 */
	@PostMapping
	public ModelAndView order(ModelAndView view, OrderDTO order) {
		// OrderDTO : 고객명(customerName), 메뉴번호(menuId), 주문개수(quantity), 요청사항(requestMessage)
		
		// 선택된 메뉴 정보 찾기
		MenuDTO menu = menuList.stream().filter(item -> 
			item.getId() == order.getMenuId()).
					findFirst().orElse(new MenuDTO(0, "알수 없는 메뉴", "기타", 0));
		
		view.addObject("menu", menu);
		// 주문 내용(커맨드 객체)도 뷰로 전달
		view.addObject("orderForm", order);
		// 주문 총액 계산
		view.addObject("totalPrice", menu.getPrice() * order.getQuantity());
		
		view.setViewName("order/result");
		
		return view;
	}
	
	
	
}





