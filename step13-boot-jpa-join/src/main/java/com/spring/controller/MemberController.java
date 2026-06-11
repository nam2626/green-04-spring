package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.service.MemberService;

@Controller
@RequestMapping("/members")
public class MemberController {
	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping
	public ModelAndView list(ModelAndView view) {
		view.addObject("members", memberService.findAll());
		view.setViewName("member/list");
		return view;
	}
}







