package com.spring.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Controller: 메인 홈 화면을 처리하는 컨트롤러입니다.
 */
@Controller
/**
 * @RequestMapping("/"): 루트 경로(/)에 대한 요청을 처리합니다.
 */
@RequestMapping("/")
public class HomeController {
	/**
	 * 메인 페이지 요청 처리
	 * 
	 * @param model: 뷰에 전달할 데이터 컨테이너(내부적으로 request 영역에 저장됨)
	 * @return "home": 이동할 뷰 이름. ViewResolver 설정에 따라 "/WEB-INF/views/home.jsp"로 연결됨
	 */
	@GetMapping
	public String home(Model model) {
		// JSP에서 ${message}로 출력
		model.addAttribute("message", "Spring MVC 테스트");
		// JSP에서 ${serverTime}으로 출력
		model.addAttribute("serverTime", new Date().toLocaleString());
		return "home";
	}
}






