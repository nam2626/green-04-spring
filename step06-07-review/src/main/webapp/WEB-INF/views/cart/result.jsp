<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 담기 결과</title>
</head>
<body>
    <h1>장바구니 담기 결과</h1>

    <%--
        [복습 포인트] DTO 자동 바인딩 결과 확인
        - CartController @PostMapping에서 view.addObject("cart", cart)로 담은 CartDTO
        - view.addObject("product", product)로 담은 ProductDTO
        - view.addObject("totalPrice", ...)로 담은 계산된 총액
    --%>
    <p>고객명: ${cart.customerName}</p>
    <p>상품명: ${product.name}</p>
    <p>카테고리: ${product.category}</p>
    <p>단가: ${product.price}원</p>
    <p>수량: ${cart.quantity}</p>
    <p>요청사항: ${cart.memo}</p>
    <hr>
    <p><strong>합계: ${totalPrice}원</strong></p>

    <p>
        <a href="${pageContext.request.contextPath}/products">상품 목록으로</a>
        <a href="${pageContext.request.contextPath}/">홈으로</a>
    </p>
</body>
</html>
