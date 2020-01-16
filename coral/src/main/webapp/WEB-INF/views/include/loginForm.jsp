<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="id" spellcheck="False">
	<input type="text" id="id">
</div>
<div class="password">
	<input type="password" id="password">
</div>
<button id="login">로그인</button>
<br>

<input type="checkbox" id="remember-me" value="remember-me">
<label style="color: white" for="remember-me">remember-me</label>
<br>
<br>
<br>
<br>
<button type="button" onClick="onSign();" style='color: white'>구글로그인</button>
<br>
<span style="color: white"><a href="/signUp?grade=teacher"
	style="color: white">교사가입</a> | <a href="/signUp?grade=student"
	style="color: white">학생가입</a></span>
	
	

<script>
	function onSign() {
		console.log('hello');
		$.ajax({
			// 전송방식을 지정한다(GET, POST)
			type : "POST",
			// 호출 URL을 설정한다.
			// GET 방식일 경우 뒤에 파라미터를 붙여서 사용해도 된다.
			url : "/googleLogin", // 전송할 내용(폼태그)
			error : function() {
				alert("통신상태가 완활하지 않습니다");
			},
			success : function(data) {
				location.href = data;
			}
		});
	}
</script>