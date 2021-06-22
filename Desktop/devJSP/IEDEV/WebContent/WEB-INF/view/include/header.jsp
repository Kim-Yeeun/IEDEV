<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>I.E.DEV</title>
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script> 
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script> 
	<script src="/IEDEV/js/member.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="/main">
			<h3>I.E.DEV</h3>
		</a>
		
		<ul class="navbar-nav">
			<c:choose>
				<c:when test="${empty sessionScope.id}">
					<li class="nav-item">
						<a class="nav-link" href="/member/login">로그인</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/member/join">회원가입</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="nav-item">
						<a class="nav-link" href="/member/logout">로그아웃</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/member/modifyView">정보수정</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/member/delete">탈퇴</a>
					</li>
				</c:otherwise>
			</c:choose>
			
			<li class="nav-item">
				<a class="nav-link" href="/notice/list">공지사항</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/post/list">기업 프로젝트</a>
			</li>
		</ul>
		
		<c:if test="${not empty sessionScope.id}">
			<span class="navbar-text">
				${sessionScope.id}님, 반갑습니다.
			</span>
		</c:if>
	</nav>
</body>
</html>