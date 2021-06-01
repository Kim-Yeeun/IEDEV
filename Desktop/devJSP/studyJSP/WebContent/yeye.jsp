<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String s = request.getParameter("cnt");
int cnt = 5;

if (s != null && !s.equals("")) {
	cnt = Integer.parseInt(s);
}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	for (int i = 0; i < cnt; i++) {
%>
	안녕? Hello!<br>
<%
	}
%>
</body>
</html>