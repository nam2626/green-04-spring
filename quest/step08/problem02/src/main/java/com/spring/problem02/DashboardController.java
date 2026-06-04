package com.spring.problem02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 대시보드 컨트롤러 [완성된 파일 — 수정 불필요]
 *
 * /dashboard 경로는 dispatcher-context.xml 설정에 의해
 * AdminCheckInterceptor 의 적용 대상입니다.
 * → 비로그인 상태로 접근하면 인터셉터가 차단하고 로그인 페이지로 보냅니다.
 */
@Controller
public class DashboardController {

    /**
     * GET /dashboard → dashboard.jsp 렌더링
     * AdminCheckInterceptor를 통과한 인증된 사용자만 이 메서드에 도달할 수 있습니다.
     */
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // WEB-INF/views/dashboard.jsp
    }
}
