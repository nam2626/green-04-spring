<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- HelloController의 greet 메서드에서 담은 데이터 출력 --%>
	<h1>${message}</h1>
	<p>로그인 한 사람 : ${name }</p>

	<!-- <a href="/step06-mvc-basic/">홈으로 돌아가기</a> -->
	<a href="${pageContext.request.contextPath }/">홈으로 돌아가기</a>
</body>
</html>