<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ page import="com.iedev.web.entity.Post"%>
<%@ page import="java.util.List"%>
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
		<h4>기업 프로젝트</h4>
		<div class="reg-btn" align="right">
			<c:choose>
				<c:when test="${sessionScope.type==1}">
					<a class="btn btn-secondary btn-sm" href="reg">프로젝트 등록</a>
				</c:when>
			</c:choose>
		</div>
		<br>
		<table class="table table-hover">
			<thead>
				<tr style="background-color:#98AFC7">
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>	
			<tbody>
				<c:forEach var="p" items="${list}">
					<tr>
						<td>${p.no}</td>	
						<td><a href="detail?no=${p.no}">${p.title}</a></td>
						<td>${p.writerId}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${p.regDate}"/></td>
						<td>${p.views}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
		<c:set var="page" value="${(empty param.p)?1:param.p}"/>
		<c:set var="startNum" value="${page-(page-1)%5}"/>
		<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/10), '.')}"/>
	
		<div class="page-view" style="text-align:right">
			<span>${(empty param.p)? 1 : param.p}</span> | ${lastNum} pages
		</div>
	
		<div class="page-list" style="text-align:center">
			<c:if test="${startNum > 1}">
				<a class="btn btn-prev" href="?p=${startNum-1}&t=&q=">이전</a>
			</c:if>
			<c:if test="${startNum <= 1}">
				<span class="btn btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</span>
			</c:if>
			<c:forEach var="i" begin="0" end="4">
				<c:if test="${(startNum+i) <= lastNum}">	
					<a class="-text- ${page == startNum+i ? 'orange' : ''} bold" href="?p=${ startNum+i }&f=${ param.f }&q=${ param.q}" >${ startNum+i }</a>	
				</c:if>
			</c:forEach>
			<c:if test="${startNum+5 <= lastNum}">
				<a class="btn btn-next" href="?p=${ startNum+5 }&t=&q=">다음</a>
			</c:if>
			<c:if test="${startNum+5 > lastNum}">
				<span class="btn btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</span>
			</c:if>	
		</div>
		<br>
	</div>
</body>

</html>

<%@ include file="../include/footer.jsp" %>