<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- JQuery -->
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
console.log("${code}");
$.ajax({
	type : "POST",
	url : "https://accounts.google.com/o/oauth2/token",
	header:{"Content-Type":"application/x-www-form-urlencoded"},
	data : {
		code:"${code}",
		client_id:"273138075636-a4r5k7hv9aa9uqb1fphcutatajt8na3l",
		client_secret:"kJ1sGMEeayDDOzRcrd2cOSt8",
		redirect_uri:"https://www.coralprogram.com/callBack",
		grant_type:"authorization_code"
	},  // 전송할 내용(폼태그)
	error : function(){
		alert("통신상태가 완활하지 않습니다");
	},
	success : function(data){
		console.log(data);
	}
});
</script>
<title>Document</title>
</head>
<body>
</body>
</html>


