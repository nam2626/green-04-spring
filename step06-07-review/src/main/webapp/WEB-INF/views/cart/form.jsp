<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니 담기</title>
</head>
<body>
    <h1>장바구니 담기</h1>

    <%--
        [복습 포인트] POST 폼
        - method="post": 브라우저가 HTTP POST 요청을 보낸다.
        - action의 URL: CartController @PostMapping("/cart")가 처리
        - 각 input의 name 속성 = CartDTO의 필드명 → 자동 바인딩 발생
    --%>
    <form action="${pageContext.request.contextPath}/cart" method="post">
        <p>
            <label for="customerName">고객명</label>
            <input type="text" id="customerName" name="customerName" required>
        </p>
        <p>
            <label for="productId">상품 선택</label>
            <%--
                [복습 포인트] c:forEach + 삼항식으로 selected 처리
                - selectedProductId와 item.id가 같으면 selected 속성 추가
            --%>
            <select id="productId" name="productId">
                <c:forEach var="item" items="${products}">
                    <option value="${item.id}" ${item.id == selectedProductId ? 'selected' : ''}>
                        ${item.name} (${item.price}원)
                    </option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label for="quantity">수량</label>
            <input type="number" id="quantity" name="quantity" value="1" min="1" required>
        </p>
        <p>
            <label for="memo">요청사항</label>
            <input type="text" id="memo" name="memo" placeholder="예: 빨대 빼주세요">
        </p>
        <button type="submit">담기</button>
    </form>

    <p><a href="${pageContext.request.contextPath}/products">상품 목록으로</a></p>
</body>
</html>
