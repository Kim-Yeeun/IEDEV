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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/layout.css">
</head>

<body>
	<div class="container">
		<br><br>
		<h4>회원가입</h4><br>
		<form action="join" method="post" id="join-form">
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
		
			<div class="row">
				<div class="col">
					<label for="id">id</label>
					<input type="text" class="form-control" id="id" name="id" placeholder="enter id">
				</div>
				<div class="col align-self-end">
					<button type="button" class="btn btn-info" id="checkId-btn">아이디 중복확인</button>
				</div>
			</div>
			<br>
		
			<div class="form-group">
				<label for="name">name</label>
				<input type="text" class="form-control" id="name" name="name" placeholder="enter name">
			</div>
		
			<div class="form-group">
				<label for="password">password</label>
				<input type="password" class="form-control" id="password" name="password" placeholder="enter password">
			</div>
		
			<div class="form-group">
				<label for="checkPassword">check password</label>
				<input type="password" class="form-control" id="checkPassword" name="checkPassword" placeholder="check password">
			</div>
		
			<div class="form-group">
				<label for="email">email</label>
				<input type="text" class="form-control" id="email" name="email" placeholder="enter email">
			</div>
		
			<div class="form-group">
				<label for="phone">phone</label>
				<input type="text" class="form-control" id="phone" name="phone" placeholder="enter phone (ex.000-0000-0000)">
			</div>
			<br>
			
			<div class="join-btn" align="right">
				<button id="join-btn" class="btn btn-primary btn-sm">회원가입</button>
				<a class="btn btn-secondary btn-sm" href="/main">취소</a>
			</div>
			<br><br>
		</form>
	</div>

<script>
var exp=/^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;

$(document).ready(function(){
	$("#join-btn").click(function(){
		if($("#id").val()==""){
			alert("아이디를 입력하세요.");
			$("#id").focus();
			return false;
		}
		
		if($("#password").val()==""){
			alert("비밀번호를 입력하세요.");
			$("#password").focus();
			return false;
		}
		
		if($("#checkPassword").val()==""){
			alert("비밀번호 확인은 필수입니다.");
			$("#checkPassword").focus();
			return false;
		}
		
		if($("#password").val()!=$("#checkPassword").val()){
			alert("비밀번호가 일치하지 않습니다.");
			$("#checkPassword").focus();
			return false;
		}
		
		if($("#name").val()==""){
			alert("이름을 입력하세요.");
			$("#name").focus();
			return false;
		}
		
		if($("#email").val()==""){
			alert("이메일을 입력하세요.");
			$("#email").focus();
			return false;
		}
		
		if($("#phone").val()==""){
			alert("전화번호를 입력하세요. ('-' 기입 필수)");
			$("#phone").focus();
			return false;
		}
		
		if(!$("#phone").val().match(exp)){
			alert("전화번호를 양식대로 입력해주세요.");
			$("#phone").focus();
			return false;
		}
		
		$("#joinForm").submit();
	})
	
	$("#checkId-btn").click(function(){
		window.open("checkId","","width=600 height=500")
	});
});

</script>

</body>

</html>

<%@ include file="../include/footer.jsp" %>