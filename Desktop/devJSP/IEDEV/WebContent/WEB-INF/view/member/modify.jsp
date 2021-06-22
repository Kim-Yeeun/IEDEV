<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify information</title>
</head>
<body>

<div class="container">
	<form action="modify" method="post" id="modifyForm">
		<br>
		<div class="form-check-inline">
			<label class="form-check-label">
				<input type="radio" class="form-check-input" name="type" value="0">관리자
			</label>
		</div>
		<div class="form-check-inline">
			<label class="form-check-label">
				<input type="radio" class="form-check-input" name="type" value="1">기업회원
			</label>
		</div>
		<div class="form-check-inline">
			<label class="form-check-label">
				<input type="radio" class="form-check-input" name="type" value="2">개인회원
			</label>
		</div>
		<br>
		<div class="form-group">
			<label for="id">id</label>
			<input type="text" name="id" id="id" value="${member.id}" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="name">name</label>
			<input type="text" class="form-control" name="name" id="name" value="${member.name}">
		</div>
		<div class="form-group">
			<label for="password">password</label>
			<input type="password" class="form-control" name="password" id="password" value="${member.password}">
		</div>
		<div class="form-group">
			<label for="name">email</label>
			<input type="text" class="form-control" name="email" id="email" value="${member.email}">
		</div>
		<div class="form-group">
			<label for="name">phone</label>
			<input type="text" class="form-control" name="phone" id="phone" value="${member.phone}">
		</div>
		<br>
		<button type="submit" class="btn btn-primary">수정</button>
	</form>
</div>

</body>
</html>