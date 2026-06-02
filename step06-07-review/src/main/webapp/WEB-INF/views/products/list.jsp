<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
</head>
<body>
    <h1>상품 목록</h1>

    <%--
        [복습 포인트] 검색 폼
        - GET 방식: 검색 조건이 URL에 노출되어 북마크/공유가 가능하다.
        - action의 contextPath 사용 필수
    --%>
    <form action="${pageContext.request.contextPath}/products/search" method="get">
        <input type="text" name="keyword" value="${keyword}" placeholder="상품명 또는 카테고리">
        <button>검색</button>
        <a href="${pageContext.request.contextPath}/products">전체보기</a>
    </form>

    <%--
        [복습 포인트] c:choose / c:when / c:otherwise
        - Java의 if-else 역할
        - empty: null이거나 빈 컬렉션이면 true
    --%>
    <c:choose>
        <c:when test="${empty products}">
            <p>검색 결과가 없습니다.</p>
        </c:when>
        <c:otherwise>
            <%--
                [복습 포인트] c:forEach
                - Java의 for-each 역할
                - var: 각 반복 항목의 이름, items: 반복할 컬렉션
            --%>
            <table border="1">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>상품명</th>
                        <th>카테고리</th>
                        <th>가격</th>
                        <th>상세/주문</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.id}</td>
                            <td>${product.name}</td>
                            <td>${product.category}</td>
                            <td>${product.price}원</td>
                            <td>
                                <%-- @PathVariable: URL에 id를 포함시켜 상세 페이지로 이동 --%>
                                <a href="${pageContext.request.contextPath}/products/${product.id}">상세보기</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

    <p><a href="${pageContext.request.contextPath}/">홈으로</a></p>
</body>
</html>
