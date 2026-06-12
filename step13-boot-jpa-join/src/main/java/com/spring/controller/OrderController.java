package com.spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.entity.Order;
import com.spring.entity.OrderStatus;
import com.spring.service.MemberService;
import com.spring.service.MenuItemService;
import com.spring.service.OrderService;

/**
 * 주문 관리 컨트롤러
 */
@RequestMapping("/orders")
@Controller
public class OrderController {
	
	private final MemberService memberService;
	private final MenuItemService menuItemService;
	private final OrderService orderService;
	
	public OrderController(MemberService memberService, MenuItemService menuItemService, OrderService orderService) {
		this.memberService = memberService;
		this.menuItemService = menuItemService;
		this.orderService = orderService;
	}

	/**
	 * 주문 목록 및 검색 페이지
	 */
	@GetMapping
	public ModelAndView orderList(ModelAndView view, 
			@RequestParam(required = false) Long memberId, 
			@RequestParam(required = false) OrderStatus orderStatus) {
		
		view.addObject("members", memberService.findAll());
        view.addObject("statuses", OrderStatus.values()); // Enum 전체 목록 전달
        view.addObject("orders", orderService.search(memberId, orderStatus));
        view.addObject("selectedMemberId", memberId);
        view.addObject("selectedStatus", orderStatus);
        view.setViewName("order/list");
        return view;
	}
	
	/**
	 * 새 주문 작성 페이지 (회원 목록과 메뉴 목록을 함께 전달)
	 */
	@GetMapping("/new")
	public ModelAndView form(ModelAndView view) {
		view.addObject("members", memberService.findAll());
		view.addObject("menuItems", menuItemService.findAll());
		view.setViewName("order/form");
		return view;
	}
	
	/**
	 * 주문 실행
	 */
	@PostMapping
	public String save(@RequestParam Long memberId, 
			@RequestParam List<Long> menuItemIds, 
			@RequestParam List<Integer> quantities,
			RedirectAttributes ra) {
		
		try {
			Order order = orderService.save(memberId, menuItemIds, quantities);
			ra.addFlashAttribute("message", "주문이 정상적으로 완료되었습니다.");
			return "redirect:/orders/" + order.getId();
		} catch (IllegalArgumentException e) {
			ra.addFlashAttribute("error", e.getMessage());
			return "redirect:/orders/new";
		}
	}
	
	/**
	 * 주문 상세 내역 조회
	 */
	@GetMapping("/{id}")
	public ModelAndView detail(@PathVariable Long id, ModelAndView view) {
		view.addObject("order", orderService.findById(id));
		view.addObject("statuses", OrderStatus.values());
		view.setViewName("order/detail");
		return view;
	}
	
	/**
	 * 주문 상태 업데이트 (대기중 -> 완료 등)
	 */
	@PostMapping("/{id}/status")
	public String updateStatus(@PathVariable Long id, OrderStatus status, RedirectAttributes ra) {
		orderService.changeStatus(id, status);
		ra.addFlashAttribute("message", "주문 상태가 변경되었습니다.");
		return "redirect:/orders/" + id;
	}
	
	/**
	 * 주문 취소 및 내역 삭제
	 */
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes ra) {
		orderService.delete(id);
		ra.addFlashAttribute("message", "주문 내역이 삭제되었습니다.");
		return "redirect:/orders";
	}
	
}
