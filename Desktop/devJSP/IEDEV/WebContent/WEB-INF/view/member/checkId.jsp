<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>I.E.DEV</title>
<link rel="stylesheet" type="text/css" href="bootstrap-3.3.2-dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script> 
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container"> 
		<div class="row"> 
			<div class="col"> 
				<br> 
				<input type="text" class="form-control" id="id" name="id" placeholder="enter id"> 
				<button type="button" id="confirm-btn" class="btn btn-primary">아이디 사용여부</button> 
			</div> 
		</div> 
	</div>
<script>

$(document).ready(function(){
	$("#confirm-btn").click(function(){ 
		if($("#id").val()==""){ 
			alert("아이디를 입력하세요"); 
			$("#id").focus(); 
			return false; 
		} 
		$.ajax({ 
			type: "post", 
			url : "checkId", 
			data: {"id":$("#id").val()}, 
			success: function(val){ 
				if(val.trim()=="y"){ 
					alert("사용가능한 아이디입니다."); 
					$(opener.document).find("#id").val($("#id").val()); 
					self.close(); 
				} else if(val.trim()=="n"){ 
					alert("중복된 아이디입니다."); 
					$("#id").val(""); 
				} 
			}, 
			error: function(e){ 
				alert("error:"+e); 
			} 
		});
	})
});

</script>

</body>
</html>