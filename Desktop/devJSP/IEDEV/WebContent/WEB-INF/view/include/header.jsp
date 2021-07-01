<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>I.E.DEV</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script> 
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script> 
</head>

<body>
	<nav class="navbar navbar-expand-sm bg-secondary navbar-dark">
		<a class="navbar-brand" href="/main">
			<h3>I.E.DEV</h3>
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#header_menu">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="header_menu">
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
				<c:when test="${sessionScope.type==0}">
					<li class="nav-item">
						<a class="nav-link" onclick="return confirm('로그이웃 하시겠습니까?')" href="/member/logout">로그아웃</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/member/modifyView">정보수정</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" onclick="return confirm('정말 탈퇴하시겠습니까?')" href="/member/delete">탈퇴</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/member/list">회원리스트</a>
					</li>
				</c:when>
				<c:when test="${sessionScope.type==1}">
					<li class="nav-item">
						<a class="nav-link" onclick="return confirm('로그이웃 하시겠습니까?')" href="/member/logout">로그아웃</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/member/modifyView">정보수정</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" onclick="return confirm('정말 탈퇴하시겠습니까?')" href="/member/delete">탈퇴</a>
					</li>
				</c:when>
				<c:when test="${sessionScope.type==2}">
					<li class="nav-item">
						<a class="nav-link" onclick="return confirm('로그이웃 하시겠습니까?')" href="/member/logout">로그아웃</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/member/modifyView">정보수정</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" onclick="return confirm('정말 탈퇴하시겠습니까?')" href="/member/delete">탈퇴</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/portfolio/detail?id=${sessionScope.id}">이력서</a>
					</li>
				</c:when>
			</c:choose>
			
			<li class="nav-item">
				<a class="nav-link" href="/notice/list">공지사항</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/post/list">기업 프로젝트</a>
			</li>
		</ul>
		</div>
		
		<c:if test="${not empty sessionScope.id}">
			<span class="navbar-text" style="color:black">
				${sessionScope.id}님, 반갑습니다.
			</span>
		</c:if>
	</nav>
</body>

</html>