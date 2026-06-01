package com.spring.problem02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * [실습] 메뉴 및 주문 처리를 담당하는 컨트롤러
 * 
 * @RequestMapping("/menu"): 이 클래스의 모든 매핑 URL 앞에 "/menu"가 자동으로 붙습니다.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    /**
     * 메뉴 정보 조회 (GET /menu)
     * 
     * ModelAndView: Model(데이터)과 View(이동할 페이지)를 한 번에 다룰 수 있는 객체입니다.
     */
	@GetMapping
	public ModelAndView menu(ModelAndView view) {
		// 데이터를 "key-value" 쌍으로 담습니다.
		view.addObject("menuName", "불고기버거");
		view.addObject("price", 8500);
		view.addObject("category", "버거");
		
		// 이동할 JSP 파일의 이름을 지정합니다 (/WEB-INF/views/menu.jsp)
		view.setViewName("menu");
		
		return view;
	}

    /**
     * 주문 처리 (GET /menu/order)
     * 
     * @param item: URL 쿼리 스트링의 'item' 파라미터 값을 받아옵니다.
     * @param qty: URL 쿼리 스트링의 'qty' 파라미터 값을 int로 자동 변환하여 받아옵니다.
     */
	@GetMapping("/order")
	public ModelAndView order(String item, int qty, ModelAndView view) {
		// 전달받은 데이터를 다시 JSP로 전달하기 위해 저장합니다.
		view.addObject("itemName", item);
		view.addObject("quantity", qty);
		
		// 계산된 총액 데이터 추가
		view.addObject("totalPrice", qty * 8500);
		
		// 이동할 JSP 이름 지정 (/WEB-INF/views/order.jsp)
		view.setViewName("order");
		
		// 콘솔 출력 (서버 확인용)
		System.out.println("주문 수량: " + qty);
		
		return view;
	}

}
