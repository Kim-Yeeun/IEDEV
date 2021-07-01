<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>modify information</title>
<link rel="stylesheet" type="text/css" href="bootstrap-3.3.2-dist/css/bootstrap.min.css">
</head>

<body>
	<div class="container">
		<br><br>
		<h4>회원 정보 수정</h4><br>
		<form action="modify" method="post" id="modifyForm">
			<div class="check-type">
				<div class="form-check">
					<label class="form-check-label">
						<input type="radio" class="form-check-input" name="type" value="0">관리자
					</label>
				</div>
				<div class="form-check">
					<label class="form-check-label">
						<input type="radio" class="form-check-input" name="type" value="1">기업
					</label>
				</div>
				<div class="form-check">
					<label class="form-check-label">
						<input type="radio" class="form-check-input" name="type" value="2">개인
					</label>
				</div>
			</div>
			<br>
		
			<div class="form-group">
				<label for="id">id</label>
				<input type="text" class="form-control" name="id" id="id" value="${member.id}" readonly="readonly">
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
			<div class="mod-btn" align="right">
             	<input class="btn btn-success btn-sm" type="submit" value="수정"/>
             	<a class="btn btn-secondary btn-sm" href="/main">취소</a>
        	</div>
		</form>
	</div>
	<br>
</body>

</html>

<%@ include file="../include/footer.jsp" %>