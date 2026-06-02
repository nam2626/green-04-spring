<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- ${message}: 컨트롤러의 Model에 담긴 "message" 값을 출력 (Expression Language) --%>
	<h1>${message }</h1>
	<p>서버시간 : ${serverTime }</p>
	
	<ul>
		<%-- 
			${pageContext.request.contextPath}: 
			현재 웹 애플리케이션의 루트 경로(/step06-mvc-basic)를 동적으로 가져옵니다. 
			경로가 바뀌어도 유연하게 대응할 수 있어 권장되는 방식입니다.
		--%>
		<li><a href="${pageContext.request.contextPath }/hello">인사페이지</a></li>
		<li>
			<%-- /hello/greet?name=홍길동 : 쿼리 스트링을 통해 파라미터 전달 --%>
			<a href="${pageContext.request.contextPath }/hello/greet?name=홍길동">
				홍길동으로 인사
			</a>
		</li>
	</ul>
</body>
</html>




