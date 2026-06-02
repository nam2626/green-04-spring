<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL Core 태그 라이브러리 사용 선언 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메뉴 목록</title>
</head>
<body>
	<h1>메뉴 목록</h1>
	
	<%-- 검색 폼: GET 방식으로 /menus/search 에 요청을 보냄 --%>
	<form action="${pageContext.request.contextPath }/menus/search" method="get">
		<input type="text" name="keyword" value="${keyword }" placeholder="메뉴명 또는 카테고리를 입력하세요">
		<button>검색</button>
		<a href="${pageContext.request.contextPath }/menus">전체보기</a>
	</form>

	<%-- 
		<c:choose>: 자바의 switch-case 또는 if-else if-else 와 유사한 제어 태그
	--%>
	<c:choose>
		<%-- 검색 결과가 없는 경우 --%>
		<c:when test="${empty menus }">
			<p>검색 결과가 없습니다.</p>
		</c:when>
		<%-- 검색 결과가 있는 경우 --%>
		<c:otherwise>
			<table border="1">
				<thead>
					<tr>
						<th>번호</th>
						<th>메뉴명</th>
						<th>카테고리</th>
						<th>가격</th>
						<th>상세</th>
					</tr>
				</thead>
				<tbody>
					<%-- 
						<c:forEach>: 리스트 데이터를 반복해서 출력 (for-each문과 동일)
						- items: 반복할 대상 리스트 (${menus})
						- var: 현재 반복 순서의 객체를 담을 변수명 (menu)
					--%>
					<c:forEach var="menu" items="${menus }">
						<tr>
							<td>${menu.id }</td>
							<td>${menu.name }</td>
							<td>${menu.category }</td>
							<td>${menu.price }원</td>
							<td>
								<%-- 상세 페이지 이동 (경로에 menu.id 포함) --%>
								<a href="${pageContext.request.contextPath }/menus/${menu.id}">보기</a>
							</td>
						</tr>					
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>	
</body>
</html>








