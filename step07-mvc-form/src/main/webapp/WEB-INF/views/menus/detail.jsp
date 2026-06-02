<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>메뉴 상세</title>
</head>
<body>
    <h1>메뉴 상세</h1>
    <%-- MenuController에서 넘어온 'menu' 객체의 필드값 출력 --%>
    <p>번호: ${menu.id}</p>
    <p>메뉴명: ${menu.name}</p>
    <p>카테고리: ${menu.category}</p>
    <p>가격: ${menu.price}원</p>
    
    <p>
        <%-- 주문 폼으로 이동 시 menuId를 쿼리 스트링으로 전달 --%>
        <a href="${pageContext.request.contextPath}/orders/new?menuId=${menu.id}">이 메뉴 주문하기</a>
        <a href="${pageContext.request.contextPath}/menus">목록으로</a>
    </p>
</body>
</html>

