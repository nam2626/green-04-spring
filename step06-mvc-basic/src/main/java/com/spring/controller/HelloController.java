package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
	@GetMapping
	public String hello(Model model) {
		model.addAttribute("greeting", "안녕하세요. hello 컨트롤러 입니다.");
		return "hello";
	}
}
