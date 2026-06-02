<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- HelloController에서 담은 'greeting' 출력 --%>
	<h1>${greeting }</h1>
	
	<!-- <a href="/step06-mvc-basic/">홈으로 돌아가기</a> -->
	<%-- 동적인 컨텍스트 경로 사용 --%>
	<a href="${pageContext.request.contextPath }/">홈으로 돌아가기</a>
</body>
</html>