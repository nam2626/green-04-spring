<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 입력 폼</title>
</head>
<body>
	<h1>주문 입력</h1>
	
	<%-- 
		POST 방식 주문 요청
		action: /orders (OrderController의 @PostMapping 메서드로 연결)
	--%>
	<form action="${pageContext.request.contextPath }/orders" method="post">
		<p>
			<label for="customerName">고객명</label>
			<%-- name 속성값이 DTO의 필드명과 일치해야 자동 매핑됨 --%>
			<input type="text" id="customerName" name="customerName" required>
		</p>
		<p>
			<label>메뉴</label> 
			<select name="menuId" id="menu">
				<%-- 
					메뉴 목록 반복 출력
					삼항 연산자를 이용해 전달받은 seletedMenuId와 일치하면 selected 속성 부여
				--%>
				<c:forEach var="item" items="${menus }">
					<option value="${item.id }"
						${item.id == seletedMenuId ? 'selected' : ''}>
						${item.name }(${item.price }원)</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<label>수량</label> 
			<input type="number" name="quantity" value="1" min="1" required>
		</p>
		<p>
			<label>요청사항</label> 
			<input type="text" name="requestMessage" placeholder="예: 양파 빼주세요">
		</p>
		<button>주문하기</button>
	</form>
	
</body>
</html>








