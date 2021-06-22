<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인[기업회원]</title>
</head>
<body>
<div class="container">
	<form action="login" method="post" id="loginForm">
		<div class="form-group">
			<label for="id">id</label>
			<input type="text" class="form-control" id="id" placeholder="enter id" name="id">
		</div>
		<div class="form-group">
			<label for="password">password</label>
			<input type="password" class="form-control" id="password" placeholder="enter password" name="password">
		</div>
		<br>
		<button type="button" id="loginButton" class="btn btn-primary">로그인</button>
	</form>
</div>

<script>
$("#loginButton").click(function(){
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
	
	$.ajax({
		type : "post",
		url : "login",
		data : {"id":$("#id").val(), "password":$("#password").val()},
		success : function(value){
			switch(value.trim()){
			case "0" : alert("관리자로 로그인하였습니다."); location.href="/notice/list"; break;
			case "1" : alert("기업회원으로 로그인하였습니다."); location.href="/notice/list"; break;
			case "2" : alert("개인회원으로 로그인하였습니다."); location.href="/notice/list"; break;
			case "3" : alert("비밀번호가 잘못되었습니다. 다시 확인해주세요."); break;
			case "-1" : alert("존재하지 않는 회원입니다."); location.href="/member/join"; break;
			default : alert(value.trim());
			}
		},
		error : function(e){
			alert("error : " + e);
		}
	})
})
</script>
</body>
</html>