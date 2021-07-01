<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

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
	</style>
</head>

<body>
	<div class="container">
		<br><br>
		<h4>기업 프로젝트 등록</h4><br>
		<form method="post" action="reg" enctype="multipart/form-data">
    		<table class="table">
        	     <tbody>
        	     	<tr>
        	        	<th>제목</th>
                   	 	<td colspan="3">
                    	    <input type="text" class="form-control" name="title" />
                    	</td>
                	</tr>
                	<tr>
                	    <th>첨부파일1</th>
                	    <td colspan="3"><input type="file" name="file" /> </td>
                	</tr>
                	<tr>
                	    <th>첨부파일2</th>
                	    <td colspan="3"><input type="file" name="file" /> </td>
                	</tr>
                	<tr class="content">
                	    <td colspan="4"><textarea class="form-control" name="content" id="content"></textarea></td>
                	</tr>
             	</tbody>
        	</table>
        
        	<div class="reg-btn" align="right">
             	<input class="btn btn-primary btn-sm" type="submit" value="등록"/>
             	<a class="btn btn-secondary btn-sm" href="list">취소</a>
        	</div>
     	</form>
	</div>
	<br>
</body>

</html>

<%@ include file="../include/footer.jsp" %>