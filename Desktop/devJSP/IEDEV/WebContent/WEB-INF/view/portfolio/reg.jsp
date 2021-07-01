<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>

<head>
<title>I.E.DEV</title>
<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="bootstrap-3.3.2-dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css">
</head>

<body>
	<div class="container">
		<br><br>
		<h4>이력서</h4><br>
		<form method="post" action="reg">		
			<div class="form-group">
				<label for="name">이름</label>
				<input type="text" class="form-control" name="name" value="${member.name}" readonly="readonly">
			</div>
			
			<div class="form-group">
				<label for="birth">생년월일</label>
				<input type="text" class="form-control" name="birth" placeholder="생년월일을 입력해주세요.">
			</div>
			
			<div class="form-group">
				<label for="email">이메일</label>
				<input type="text" class="form-control" name="email" value="${member.email}" readonly="readonly">
			</div>
			
			<div class="form-group">
				<label for="phone">전화번호</label>
				<input type="text" class="form-control" name="phone" value="${member.phone}" readonly="readonly">
			</div>
			
			<div class="form-group">
				<label for="certificate">자격증</label>
				<input type="text" class="form-control" name="certificate" placeholder="보유 자격증을 입력해주세요.">
			</div>
			
			<div class="form-group">
				<label for="language">사용 가능한 언어</label><br>
				<select id="language" name="language">
					<option value="java">JAVA</option>
					<option value="javascript">JAVASCRIPT</option>
					<option value="python">PYTHON</option>
					<option value="c">C</option>
					<option value="c++">HTML/CSS</option>
					<option value="c++">SQL</option>
				</select>
			</div>
			
			<div class="form-group">
				<label for="project">프로젝트</label>
				<input type="text" class="form-control" name="project" placeholder="Github 주소를 입력하거나 블로그 주소를 입력해주세요.">
			</div>
			
			<div class="form-group">
				<label for="introduce">자기소개</label>
				<input type="text" class="form-control" name="introduce" placeholder="간단히 자기소개를 해주세요.">
			</div>
			
			<div class="reg-btn" align="right">
				<input class="btn btn-primary btn-sm" type="submit" value="작성 완료"/>
				<a class="btn btn-secondary btn-sm" href="detail?id=${member.id}">취소</a>
			</div>
		</form>
	</div>
	<br>
</body>
    
</html>

<%@ include file="../include/footer.jsp" %>