<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../view/include/header.jsp" %>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>I.E.DEV</title>
<style>
	body, html {
		margin: 0px;
		padding: 0px;
		height: 100%;
	}
	
	.bg-img {
		border: 0;
		padding: 0;
		background-image: url('image/main.jpeg');
		min-height: 100%;
		background-position: center;
		background-size: cover;
	}
</style>
</head>

<body>
	<div class="bg-img">
		<div class="info-btn" align="center" style="padding:30px">
			<a class="btn btn-warning" id="info" href="info" style="padding:15px">사이트 자세히 알아보기</a>
		</div>
	</div>
</body>
</html>

<%@ include file="../view/include/footer.jsp" %>