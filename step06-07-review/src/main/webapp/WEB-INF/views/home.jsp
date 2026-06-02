<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC 복습</title>
</head>
<body>
    <h1>${title}</h1>
    <p>서버 시간: ${serverTime}</p>

    <%--
        [복습 포인트] ${pageContext.request.contextPath}
        - 프로젝트의 컨텍스트 경로를 동적으로 가져온다.
        - 예: /step06-07-review
        - 하드코딩("/products")하면 배포 경로가 바뀔 때 깨지므로 항상 contextPath를 사용한다.
    --%>
    <ul>
        <li><a href="${pageContext.request.contextPath}/products">상품 목록 (ModelAndView + JSTL)</a></li>
        <li><a href="${pageContext.request.contextPath}/products/search?keyword=커피">커피 검색 (@RequestParam)</a></li>
        <li><a href="${pageContext.request.contextPath}/products/2">상품 상세 (@PathVariable)</a></li>
        <li><a href="${pageContext.request.contextPath}/cart/new?productId=1">장바구니 담기 (GET 폼 + POST 처리)</a></li>
    </ul>
</body>
</html>
