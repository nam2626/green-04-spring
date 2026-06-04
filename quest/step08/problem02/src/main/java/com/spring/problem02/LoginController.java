package com.spring.problem02;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 로그인 / 로그아웃 처리 컨트롤러 [완성된 파일 — 수정 불필요]
 *
 * 세션(HttpSession) 기반 인증 흐름:
 *   1. GET  /login  → 로그인 폼 페이지(login.jsp) 표시
 *   2. POST /login  → 사용자 이름을 세션에 저장 후 대시보드로 리다이렉트
 *   3. GET  /logout → 세션 전체 무효화 후 로그인 페이지로 리다이렉트
 */
@Controller
public class LoginController {

    /**
     * 로그인 폼 페이지 표시
     * GET /login → login.jsp 렌더링
     */
    @GetMapping("/login")
    public String loginForm() {
        return "login"; // WEB-INF/views/login.jsp
    }

    /**
     * 로그인 처리
     * POST /login → 세션에 사용자 정보 저장 후 대시보드로 이동
     *
     * @RequestParam String username : HTML 폼의 name="username" 입력값을 받습니다.
     * HttpSession session            : 스프링이 현재 요청의 세션 객체를 자동으로 주입합니다.
     *
     * "redirect:/dashboard" : 브라우저에게 /dashboard 로 재요청(HTTP 302)하도록 지시합니다.
     *   → 폼 중복 제출 방지(PRG 패턴: Post → Redirect → Get)
     */
    @PostMapping("/login")
    public String login(@RequestParam String username, HttpSession session) {
        session.setAttribute("adminUser", username); // 세션에 로그인 정보 저장
        System.out.println("[LoginController] 로그인: " + username);
        return "redirect:/dashboard";
    }

    /**
     * 로그아웃 처리
     * GET /logout → 세션 무효화 후 로그인 페이지로 리다이렉트
     *
     * session.invalidate() : 현재 세션을 완전히 파기합니다.
     *   → 세션에 저장된 모든 데이터(adminUser 등)가 삭제됩니다.
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 전체 삭제 (로그아웃)
        return "redirect:/login";
    }
}
