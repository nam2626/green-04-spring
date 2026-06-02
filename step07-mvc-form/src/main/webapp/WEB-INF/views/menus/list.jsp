<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴 목록</title>
</head>
<body>
	<h1>메뉴 목록</h1>
	<!-- 메뉴명 검색하는 폼 -->
	<form action="" method="get">
		<input type="text" name="keyword" value="${keyword }" placeholder="메뉴명 또는 카테고리를 입력하세요">
		<button>검색</button>
		<a href="">전체보기</a>
	</form>
	<!-- 메뉴 출력하는 표 
		번호, 메뉴명, 카테고리, 가격, 주문
		단 menus에 값이 하나도 없으면, 검색 결과가 없습니다.
	-->
	<c:choose>
		<c:when test="${menus.empty }">
			<p>검색 결과가 없습니다.</p>
		</c:when>
		<c:otherwise>
			<table>
				<thead>
					<tr>
						<th>번호</th>
						<th>메뉴명</th>
						<th>카테고리</th>
						<th>가격</th>
						<th>주문</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="menu" items="${menus }">
						<tr>
							<td>${menu.id }</td>
							<td>${menu.name }</td>
							<td>${menu.category }</td>
							<td>${menu.price }</td>
							<td><a href="">주문</a></td>
						</tr>					
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>	
</body>
</html>








