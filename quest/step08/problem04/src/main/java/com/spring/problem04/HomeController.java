package com.spring.problem04;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 루트(/) 경로 요청을 메뉴 관리 페이지로 전달하는 컨트롤러
 *
 * @Controller : 이 클래스가 스프링 MVC 컨트롤러임을 나타내는 어노테이션.
 *               반환값을 View 이름(JSP 경로)으로 처리합니다.
 */
@Controller
public class HomeController {

    /**
     * GET / 요청 시 메뉴 관리 페이지(manage.jsp)로 이동
     *
     * @GetMapping("/") : HTTP GET "/" 요청을 이 메서드와 연결합니다.
     * 반환 문자열 "menu/manage" 는
     *   → WEB-INF/views/menu/manage.jsp 를 렌더링하라는 의미입니다.
     *   (InternalResourceViewResolver의 prefix/suffix 설정 덕분)
     */
    @GetMapping("/")
    public String manager() {
        return "menu/manage"; // WEB-INF/views/menu/manage.jsp
    }
}
