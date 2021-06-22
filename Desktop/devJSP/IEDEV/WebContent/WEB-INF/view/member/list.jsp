<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member list</title>
</head>
<body>

<div class="container">
	<br>
	<h3>회원 리스트</h3><br><br>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>유형</th>
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${members}" var="m">
				<c:if test="${m.type==0}">
					<c:set var="mode" value="관리자"/>
				</c:if>
				<c:if test="${m.type==1}">
					<c:set var="mode" value="기업회원"/>
				</c:if>
				<c:if test="${m.type==2}">
					<c:set var="mode" value="개인회원"/>
				</c:if>
				<tr>
					<c:if test="${m.type==0}">
						<td>관리자</td>
					</c:if>
					<c:if test="${m.type==1}">
						<td>기업회원</td>
					</c:if>
					<c:if test="${m.type==2}">
						<td>개인회원</td>
					</c:if>
					<td>${m.id}</td>
					<td>${m.name}</td>
					<td>${m.email}</td>
					<td>${m.phone}</td>
					<c:if test="${m.type==1}">
						<td onclick="del('${m.id}')">삭제</td>
					</c:if>
					<c:if test="${m.type==2}">
						<td onclick="del('${m.id}')">삭제</td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

</body>
</html>