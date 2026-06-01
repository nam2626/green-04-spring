package com.spring.problem03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {
	@GetMapping
	public String main(HttpServletRequest request) {
		return "redirect:/product/list";//redirect 이동할때
	}
}







