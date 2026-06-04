package com.spring.problem02;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 관리자 인증 여부를 확인하는 인터셉터
 *
 * 이 인터셉터는 /dashboard/** 경로에 적용됩니다.
 * 세션에 "adminUser" 키가 없으면 로그인 페이지로 리다이렉트하고 요청을 차단합니다.
 *
 * 인터셉터 동작 흐름:
 *   클라이언트 요청 → preHandle() → 컨트롤러 → postHandle() → View → afterCompletion()
 *   preHandle에서 return false 하면 컨트롤러로 요청이 전달되지 않습니다.
 */
public class AdminCheckInterceptor implements HandlerInterceptor {

    /**
     * 컨트롤러 실행 전 관리자 인증을 확인하는 메서드
     *
     * 처리 흐름:
     *   1. 현재 요청의 세션에서 "adminUser" 속성을 가져온다.
     *   2. null 이면 (미로그인) → 로그인 페이지로 리다이렉트 후 return false (요청 차단)
     *   3. null 이 아니면 (로그인 완료) → 로그 출력 후 return true (요청 통과)
     *
     * HttpSession 이란?
     *   서버가 클라이언트별로 유지하는 저장 공간입니다.
     *   로그인 성공 시 session.setAttribute("adminUser", username)으로 저장하고,
     *   여기서 session.getAttribute("adminUser")로 꺼내 확인합니다.
     */
    // TODO 1: preHandle 메서드를 오버라이드하라.
    //   - 세션에서 "adminUser" 속성을 꺼낸다.
    //   - "adminUser"가 null이면:
    //       1) sysout "[AdminCheckInterceptor] 미인증 접근 차단"
    //       2) response.sendRedirect(request.getContextPath() + "/login") 호출
    //       3) return false
    //   - "adminUser"가 있으면:
    //       1) sysout "[AdminCheckInterceptor] 인증 확인: " + adminUser
    //       2) return true

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 세션에서 로그인 정보 꺼내기
        // request.getSession(false) : 세션이 없으면 null 반환 (세션을 새로 만들지 않음)
        Object adminUser = request.getSession(false) != null
                ? request.getSession(false).getAttribute("adminUser")
                : null;

        if (adminUser == null) {
            // 미인증 상태: 로그인 페이지로 리다이렉트하고 요청 차단
            System.out.println("[AdminCheckInterceptor] 미인증 접근 차단");
            // getContextPath() : 애플리케이션 컨텍스트 경로 (예: "" 또는 "/myapp")
            response.sendRedirect(request.getContextPath() + "/login");
            return false; // 컨트롤러로 요청 전달 안 함
        }

        // 인증 완료: 요청을 컨트롤러로 통과시킴
        System.out.println("[AdminCheckInterceptor] 인증 확인: " + adminUser);
        return true;
    }
}
