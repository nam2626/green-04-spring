package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Controller: 이 클래스가 Spring MVC의 컨트롤러임을 선언합니다.
 * 클라이언트의 요청(URL)을 받아서 처리하고, 어떤 뷰(JSP)를 보여줄지 결정하는 역할을 합니다.
 */
@Controller
/**
 * @RequestMapping("/hello"): 공통 URL 경로를 설정합니다. 
 * 이 컨트롤러 내의 모든 메서드는 /hello 로 시작하는 URL에 매핑됩니다.
 */
@RequestMapping("/hello")
public class HelloController {

	/**
	 * @GetMapping: HTTP GET 방식의 요청을 처리합니다.
	 * 여기서는 부모 경로인 '/hello'에 대한 GET 요청을 처리합니다.
	 * 
	 * @param model: 컨트롤러에서 뷰(JSP)로 데이터를 전달하기 위한 객체입니다.
	 * @return "hello": 뷰 리졸버(ViewResolver)에 의해 '/WEB-INF/views/hello.jsp'로 변환됩니다.
	 */
	@GetMapping
	public String hello(Model model) {
		// model.addAttribute("key", value): JSP에서 ${key}로 접근할 수 있게 데이터를 담습니다.
		model.addAttribute("greeting", "안녕하세요. hello 컨트롤러 입니다.");
		return "hello";
	}

	/*
	  URL: '/hello/greet'
	  방법 1: @RequestParam 사용 (전달받는 파라미터명과 변수명이 다를 때 유용)
	*/
//	@GetMapping("/greet")
//	public String greet(@RequestParam(name = "name", defaultValue = "손님") String name,
//			Model model) {
//		model.addAttribute("name", name);
//		model.addAttribute("message", name + "님, 반갑습니다.");
//		return "greet";
//	}
	
	/**
	 * URL: '/hello/greet?name=홍길동' 과 같은 요청을 처리합니다.
	 * 
	 * @param name: 쿼리 스트링의 'name' 파라미터 값이 자동으로 매핑됩니다.
	 *              (스프링 MVC는 파라미터 이름과 메서드 매개변수 이름이 같으면 자동으로 연결해줍니다.)
	 */
	@GetMapping("/greet")
	public String greet(String name, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("message", name + "님, 반갑습니다.");
		return "greet";
	}
	
	
}








