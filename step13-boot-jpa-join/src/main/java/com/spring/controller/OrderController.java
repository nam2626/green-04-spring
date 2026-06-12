package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.entity.OrderStatus;
import com.spring.service.MemberService;
import com.spring.service.MenuItemService;
import com.spring.service.OrderService;


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

	@GetMapping
	public String list(Long memberId, OrderStatus status, Model model) {
		model.addAttribute("members", memberService.findAll());
		model.addAttribute("statuses", OrderStatus.values());
		model.addAttribute("orders", orderService.search(memberId,status));
		model.addAttribute("selectedMemberId", memberId);
		model.addAttribute("selectedStatus", status);
		
		return "order/list";
	}
	
}




