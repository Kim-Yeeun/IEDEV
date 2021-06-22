var exp=/^[0-9]{3}-[0-9]{4}-[0-9]{4}$/;

$(document).ready(function(){
	$("#join").click(function(){
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
		
		if($("#checkPassword").val()){
			alert("비밀번호 확인은 필수입니다.");
			$("#checkPassword").focus();
			return false;
		}
		
		if($("#password").val()!=("#checkPassword").val()){
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
	
	$("#checkId").click(function(){
		window.open("checkId.jsp","","width=600 height=500")
	});
});

function del(id){
	if(confirm("정말로 탈퇴하시겠습니까?")){
		$.getJSON("delete?id="+id, function(data){
			var htmlStr = "";
			$.each(data.root, function(key, val){
				htmlStr += "<tr>";
				htmlStr += "<tr>" + val.id + "</td>";
				htmlStr += "<tr>" + val.name + "</td>";
				htmlStr += "<tr>" + val.email + "</td>";
				htmlStr += "<tr>" + val.phone + "</td>";
				htmlStr += "<tr>" + val.mode + "</td>";
				if(val.mode != '관리자'){
					htmlStr += "<td onclick=del('"+val.id+"')>삭제</td>";
				} else {
					htmlStr += "<td>type</td>";
				}
				htmlStr += "</tr>";
			})
			$("table tbody").html(htmlStr);
		})
	}
}