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
		<h4>기업 프로젝트</h4>
		<div class="detail-btn" align="right">
			<c:choose>
				<c:when test="${sessionScope.type==0}">
					<a class="btn btn-secondary btn-sm" href="list">목록</a>
					<a class="btn btn-danger btn-sm" onclick="return confirm('이 글을 삭제하시겠습니까?')" href="delete?no=${p.no}">삭제</a>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${sessionScope.type==1}">
					<a class="btn btn-secondary btn-sm" href="list">목록</a>
					<a class="btn btn-success btn-sm" href="modifyView?no=${p.no}">수정</a>
					<a class="btn btn-danger btn-sm" onclick="return confirm('이 글을 삭제하시겠습니까?')" href="delete?no=${p.no}">삭제</a>
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
					<th>제목</th>
					<td colspan="3">${p.title}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td colspan="3"><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${p.regDate}"/></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${p.writerId}</td>
					<th>조회수</th>
					<td><fmt:formatNumber type="number" pattern="##,####" value="${p.views}"/></td>
				</tr>
				<tr>
					<th>첨부파일</th> 
					<td colspan="3">
						<c:forTokens var="fileName" items="${p.files}" delims="," varStatus="st">
							<c:set var="style" value=""/>
							<c:if test="${fn:endsWith(fileName, '.zip')}">
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
					<td colspan="4" style="padding:20px; text-align:left">${p.content}</td>
				</tr>
			</tbody>
		</table>
	
		<c:if test="${not empty sessionScope.id && sessionScope.type==1}">
			<table style="border: 1px solid; width:100%">
				<c:forEach var="a" items="${applys}">
					<tr>
					</tr>
					<tbody>
						<tr>
							<td style="padding:10px"><a href="/portfolio/detail?id=${a.writerId}" style="color:green">${a.writerId}</a></td>
							<td style="padding:10px">${a.content}</td>
							<td style="padding:10px">${a.regDate}</td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</c:if>
	
		<form method="post" action="apply?pno=${p.no}">
			<div class="apply-form" align="right">
				<c:if test="${not empty sessionScope.id && sessionScope.type==2}">
					<div class="apply">
						<input type="text" class="form-control" id="content" placeholder="ex. 신청합니다." name="content"><br>
						<input class="btn btn-secondary btn-sm" type="submit" onclick="return confirm('신청하시겠습니까?')" value="신청하기">
					</div>
				</c:if>
			</div>
		</form>
		<br>
	
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
    
<%@ include file="../include/footer.jsp" %>