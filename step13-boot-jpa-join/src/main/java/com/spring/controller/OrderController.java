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


@RequestMapping("/orders")
@Controller
public class OrderController {
	private final HomeController homeController;
	private final MemberService memberService;
	private final MenuItemService menuItemService;
	private final OrderService orderService;
	
	public OrderController(MemberService memberService, MenuItemService menuItemService, OrderService orderService, HomeController homeController) {
		this.memberService = memberService;
		this.menuItemService = menuItemService;
		this.orderService = orderService;
		this.homeController = homeController;
	}

//	@GetMapping
//	public String list(Long memberId, OrderStatus status, Model model) {
//		model.addAttribute("members", memberService.findAll());
//		model.addAttribute("statuses", OrderStatus.values());
//		model.addAttribute("orders", orderService.search(memberId,status));
//		model.addAttribute("selectedMemberId", memberId);
//		model.addAttribute("selectedStatus", status);
//		
//		return "order/list";
//	}
	@GetMapping
	public ModelAndView orderList(ModelAndView view ,@RequestParam(required = false) Long memberId, @RequestParam(required = false) OrderStatus orderStatus ){
		view.addObject("members", memberService.findAll());
        view.addObject("statuses", OrderStatus.values());
        view.addObject("orders" ,orderService.search(memberId,orderStatus));
        view.addObject("selectedMemberId", memberId);
        view.addObject("selectedStatus", orderStatus);
        view.setViewName("order/list");
        return view;
	}
	
	@GetMapping("/new")
	public ModelAndView form(ModelAndView view) {
		view.addObject("members", memberService.findAll());
		view.addObject("menuItems", menuItemService.findAll());
		view.setViewName("order/form");
		return view;
	}
	
	@PostMapping
	public String save(@RequestParam Long memberId,@RequestParam List<Long> menuItemIds,@RequestParam List<Integer> quantities,
			RedirectAttributes ra) {
		System.out.println(memberId);
		System.out.println(menuItemIds);
		System.out.println(quantities);
		try {
			Order order = orderService.save(memberId, menuItemIds, quantities);
			ra.addFlashAttribute("message", "주문이 완료 되었습니다.");
			return "redirect:/orders/"+order.getId();
		}catch (IllegalArgumentException e) {
			ra.addFlashAttribute("error", e.getMessage());
			return "redirect:/orders/new";
		}
		
	}
	
	@GetMapping("/{id}")
	public ModelAndView detail(@PathVariable Long id, ModelAndView view) {
		view.addObject("order", orderService.findById(id));
		view.addObject("statuses", OrderStatus.values());
		view.setViewName("order/detail");
		return view;
	}
	
}








