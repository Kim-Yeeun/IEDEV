<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="bootstrap-3.3.2-dist/css/bootstrap.min.css">
</head>
<body>
<h3>회원가입</h3>
<div class="container">
	<form action="join" method="post" id="joinForm">
		<div class="form-check-inline">
			<label class="form-check-label">
				<input type="radio" class="form-check-input" name="type" value="0">관리자
			</label>
		</div>
		<div class="form-check-inline">
			<label class="form-check-label">
				<input type="radio" class="form-check-input" name="type" value="1">기업
			</label>
		</div>
		<div class="form-check-inline">
			<label class="form-check-label">
				<input type="radio" class="form-check-input" name="type" value="2">개인
			</label>
		</div>
		
		<div class="row">
			<div class="col">
				<label for="id">id</label>
				<input type="text" class="form-control" id="id" placeholder="enter id" name="id">
			</div>
			<div class="col align-self-end">
				<button type="button" id="checkId" class="btn btn-primary">아이디 중복확인</button>
			</div>
		</div>
		
		<div class="form-group">
			<label for="name">name</label>
			<input type="text" class="form-control" id="name" placeholder="enter name" name="name">
		</div>
		
		<div class="form-group">
			<label for="password">password</label>
			<input type="password" class="form-control" id="password" placeholder="enter password" name="password">
		</div>
		
		<div class="form-group">
			<label for="checkPassword">check password</label>
			<input type="password" class="form-control" id="password" placeholder="enter password" name="password">
		</div>
		
		<div class="form-group">
			<label for="email">email</label>
			<input type="text" class="form-control" id="email" placeholder="enter email" name="email">
		</div>
		
		<div class="form-group">
			<label for="phone">phone</label>
			<input type="text" class="form-control" id="phone" placeholder="enter phone" name="phone">
		</div>
		<br>
		<button id="join" class="btn btn-primary">회원가입</button>
	</form>
</div>
</body>
</html>