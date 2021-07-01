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
		text-align: center;
		background-color: #98AFC7;
	}
	
	td {
		text-align: center;
	}
	</style>
</head>

<body>
	<div class="container">
		<br><br>
		<h4>공지사항</h4>
		<div class="detail-btn" align="right">
			<c:choose>
				<c:when test="${sessionScope.type==0}">
					<a class="btn btn-secondary btn-sm" href="list">목록</a>
					<a class="btn btn-success btn-sm" href="modifyView?no=${n.no}">수정</a>
					<a class="btn btn-danger btn-sm" onclick="return confirm('이 공지사항을 삭제하시겠습니까?')" href="delete?no=${n.no}">삭제</a>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${sessionScope.type==1}">
					<a class="btn btn-secondary btn-sm" href="list">목록</a>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${sessionScope.type==2}">
					<a class="btn btn-secondary btn-sm" href="list">목록</a>
				</c:when>
			</c:choose>
		</div>
		<br>
		<table class="table">
			<tbody>
				<tr>
					<th>번호</th>
					<td colspan="3">${n.no}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="3">${n.title}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td colspan="3"><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${n.regDate}"/></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${n.writerId}</td>
					<th>조회수</th>
					<td><fmt:formatNumber type="number" pattern="##,####" value="${n.views}"/></td>
				</tr>
				<tr>
					<th>첨부파일</th> 
					<td colspan="3">
						<c:forTokens var="fileName" items="${n.files}" delims="," varStatus="st">
							<c:set var="style" value=""/>
							<c:if test="${ fn:endsWith(fileName, '.zip') }">
								<c:set var="style" value="font-weight:bold; color:red;"/>
							</c:if>
							<a href="${fileName}" style="${style}">${fn:toUpperCase(fileName)}</a>
							<c:if test="${!st.last}">
								/
							</c:if>
						</c:forTokens>
					</td>
				</tr>
				<tr>
					<td colspan="4" style="padding:20px; text-align:left">${n.content}</td>
				</tr>
			</tbody>
		</table>	

		<div class="detail-next-prev">
			<table class="table border-default">
				<tr>
					<th>다음글</th>
					<td colspan="3"><a class="detail-next" href="detail?no=${next.no}">${next.title}</a></td>
				</tr>
				<tr>
					<th>이전글</th>
					<td colspan="3"><a class="detail-prev" href="detail?no=${prev.no}">${prev.title}</a></td>
				</tr>
			</table>
		</div>	
	</div>
</body>
    
</html>
    
