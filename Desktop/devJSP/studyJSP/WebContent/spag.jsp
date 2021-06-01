<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 모델 출력하는 html 페이지 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	pageContext.setAttribute("result", "hello");
%>
<body>
	<%= request.getAttribute("result") %> 입니다.
	${requestScope.result} 입니다.<br>
	${names[1]}<br>
	${notice.title}<br>
	${result}<br>
	${param.n}<br>
	${header.host}<br>
	${pageContext.request.method}
</body>
</html>