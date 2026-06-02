package com.spring.review.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * [복습 포인트 1] @Controller
 * - Spring이 이 클래스를 웹 요청을 처리하는 Bean으로 인식한다.
 * - dispatcher-context.xml의 component-scan이 자동으로 등록한다.
 *
 * [복습 포인트 2] @RequestMapping("/")
 * - 클래스 레벨에 붙으면 이 컨트롤러 전체의 기본 URL prefix가 된다.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    /*
     * [복습 포인트 3] @GetMapping
     * - GET /  요청을 처리한다.
     * - @RequestMapping(method = RequestMethod.GET)의 축약형
     *
     * [복습 포인트 4] Model
     * - 컨트롤러 → JSP로 데이터를 전달하는 컨테이너
     * - 내부적으로 request scope에 저장되어 JSP에서 ${key}로 꺼낸다.
     *
     * [복습 포인트 5] String 반환 = 뷰 이름
     * - "home" → ViewResolver → /WEB-INF/views/home.jsp
     */
    @GetMapping
    public String home(Model model) {
        model.addAttribute("title", "Spring MVC 복습 프로젝트");
        model.addAttribute("serverTime", new Date().toLocaleString());
        return "home";
    }
}
