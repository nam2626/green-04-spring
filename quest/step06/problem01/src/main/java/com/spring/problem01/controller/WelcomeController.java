package com.spring.problem01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * [완성된 파일 — 수정 불필요]
 * 환영 메시지를 보여주는 컨트롤러
 */
@Controller
public class WelcomeController {

    @GetMapping("/")
    public String main(Model model) {
    	return welcome(model); //차후에 redirect 방식으로 /welcome 
    }
	
	@GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("msg", "환영합니다! Spring MVC 첫 번째 실습");
        return "welcome";
    }
    
    
    
}
