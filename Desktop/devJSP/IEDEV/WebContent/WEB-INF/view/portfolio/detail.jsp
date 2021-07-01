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

	<style>
	th {
		background-color: #BAB86C;
		text-align: center;
	}
	</style>
</head>

<body>
	<div class="container">
		<br><br>
		<h4>이력서</h4>
		<div class="pf-btn" align="right">
			<c:choose>
				<c:when test="${sessionScope.type==2 and portfolio.birth eq null}">
					<a class="btn btn-primary btn-sm" href="regView?id=${sessionScope.id}">입력</a>
				</c:when>
				<c:when test="${sessionScope.type==2 and portfolio.birth ne null}">
					<a class="btn btn-success btn-sm" href="modifyView?id=${sessionScope.id}">수정</a>
				</c:when>
			</c:choose>
		</div>
		<br>
		<table class="table">
			<tbody>
			<tr>
				<th>이름</th>
				<td class="detail-view" colspan="3">${member.name}</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td class="detail-view" colspan="3">${portfolio.birth}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td class="detail-view" colspan="3">${member.email}</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td class="detail-view" colspan="3">${member.phone}</td>
			</tr>
			<tr>
				<th>자격증</th>
				<td class="detail-view" colspan="3">${portfolio.certificate}</td>
			</tr>
			<tr>
				<th>사용 가능한 언어</th>
				<td class="detail-view" colspan="3">${portfolio.language}</td>
			</tr>
			<tr>
				<th>프로젝트</th>
				<td class="detail-view" colspan="3">${portfolio.project}</td>
			</tr>
			<tr>
				<th>자기소개</th>
				<td class="detail-view" colspan="3">${portfolio.introduce}</td>
			</tr>
			</tbody>
		</table>
	</div>
	<br>
</body>
    
</html>
    
<%@ include file="../include/footer.jsp" %>