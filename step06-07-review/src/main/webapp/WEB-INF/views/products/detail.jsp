<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세</title>
</head>
<body>
    <h1>상품 상세</h1>

    <%--
        [복습 포인트] @PathVariable로 받은 productId로 조회한 product 객체를 EL로 출력
        - 컨트롤러에서 view.addObject("product", found) 로 담은 데이터
    --%>
    <p>번호: ${product.id}</p>
    <p>상품명: ${product.name}</p>
    <p>카테고리: ${product.category}</p>
    <p>가격: ${product.price}원</p>
    <p>설명: ${product.description}</p>

    <p>
        <%--
            장바구니 폼으로 이동할 때 productId를 쿼리스트링으로 전달
            → CartController의 @GetMapping("/new")에서 long productId로 받음
        --%>
        <a href="${pageContext.request.contextPath}/cart/new?productId=${product.id}">장바구니 담기</a>
        <a href="${pageContext.request.contextPath}/products">목록으로</a>
    </p>
</body>
</html>
