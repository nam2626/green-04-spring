package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	/**
	 * "redirect:" 키워드:
	 * 클라이언트에게 해당 URL로 다시 요청하도록 지시합니다. (HTTP 302)
	 * 브라우저의 주소창이 "/menus"로 변경됩니다.
	 */
	@GetMapping("/")
	public String home() {
		return "redirect:/menus";
	}
	
}
