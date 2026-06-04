package com.spring.problem04;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * API 요청/응답 로그를 출력하는 인터셉터(Interceptor)
 *
 * 인터셉터란?
 *   클라이언트의 HTTP 요청이 컨트롤러에 도달하기 전/후에
 *   끼어들어(intercept) 공통 처리를 할 수 있는 스프링 MVC 컴포넌트입니다.
 *
 * HandlerInterceptor 주요 메서드:
 *   - preHandle      : 컨트롤러 실행 전 호출  → return false 시 요청 차단
 *   - postHandle     : 컨트롤러 실행 후, View 렌더링 전 호출
 *   - afterCompletion: View 렌더링까지 모두 완료된 후 호출 (예외 여부 무관)
 */
// TODO 1: HandlerInterceptor 구현
// TODO 2: preHandle에서 sysout "[ApiLog] 요청: " + request.getMethod() + " " + request.getRequestURI() 후 return true
// TODO 3: afterCompletion에서 sysout "[ApiLog] 완료: " + request.getRequestURI()
public class ApiLogInterceptor implements HandlerInterceptor {

    /**
     * 컨트롤러 실행 전에 호출되는 메서드
     *
     * @param request  클라이언트의 HTTP 요청 정보 (URL, 파라미터 등 포함)
     * @param response 서버의 HTTP 응답 객체
     * @param handler  실행될 컨트롤러 메서드 정보
     * @return true  → 요청을 계속 처리 (컨트롤러로 전달)
     *         false → 요청을 여기서 차단 (컨트롤러 호출 안 함)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // request.getMethod()      : HTTP 메서드(GET, POST, PUT, DELETE 등)
        // request.getRequestURI()  : 요청 경로 (예: /api/menus)
        System.out.println("[ApiLog] 요청: " + request.getMethod() + " " + request.getRequestURI());
        return true; // 계속 진행
    }

    /**
     * View 렌더링까지 모두 끝난 후 호출되는 메서드
     * 요청 처리 중 예외가 발생해도 호출됩니다.
     *
     * @param ex 처리 중 발생한 예외 (정상 완료 시 null)
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        System.out.println("[ApiLog] 완료: " + request.getRequestURI());
    }
}
