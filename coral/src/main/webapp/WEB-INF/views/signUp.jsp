<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<!-- JQuery -->
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>

<!-- 외부 css -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- 내부 css -->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/default.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/Editor.css" />

<head>
<meta charset="UTF-8">
<title>Coral - 목록</title>
</head>

<body>
	<input type="text" maxlength="20" minlength="8" style="width:0;border:0;"><input type="password" maxlength="25" minlength="8" style="width:0;border:0;">
	<jsp:include page="${contextPath }\common\header2.jsp"></jsp:include>


	<div class="base_">
		<div class="form">
			<hr>
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" maxlength="20" minlength="8" name="id">
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" maxlength="25" minlength="8"
						name="password"></td>
				</tr>
				<tr>
					<td>비밀번호 재입력</td>
					<td><input type="password" maxlength="25" minlength="8"
						name="passwordRe"></td>
				</tr>
				<tr>
					<td>성명</td>
					<td><input type="text" maxlength="5" minlength="2" name="name">
					</td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="date" name="birth"></td>
				</tr>
				<tr>
					<td>성별</td>
					<td><select name="gender">
							<option hidden selected>----</option>
							<option>남자</option>
							<option>여자</option>
							<option>기타</option>
					</select></td>
				</tr>
				<tr>
					<td>핸드폰 번호</td>
					<td><select name="phone_front">
							<option hidden selected>----</option>
							<option>010</option>
							<option>011</option>
							<option>016</option>
							<option>017</option>
							<option>019</option>
					</select>-<input type="text" maxlength="4" minlength="4" name="phone_middle">-<input
						type="text" maxlength="4" minlength="4" name="phone_behind">
					</td>
				</tr>
				<tr style="display:${is}">
					<td>회사 명</td>
					<td><input type="text" maxlength="50" minlength="1"
						name="company_name"></td>
				</tr>
				<tr style="display:${is}">
					<td>회사 주소</td>
					<td><input type="text" readonly name="address" value=""
						></td>
				</tr>
				<tr style="display:${is}">
					<td>회사 주소 상세</td>
					<td><input type="text" maxlength="50" minlength="0"
						name="address_detail" value=""></td>
				</tr>
				<tr style="display:${is}">
					<td>회사 연락처</td>
					<td><select name="tel_front">
							<option hidden selected>----</option>
							<option>02</option>
							<option>031</option>
							<option>032</option>
							<option>033</option>
							<option>041</option>
							<option>043</option>
							<option>042</option>
							<option>044</option>
							<option>051</option>
							<option>052</option>
							<option>053</option>
							<option>054</option>
							<option>055</option>
							<option>061</option>
							<option>062</option>
							<option>063</option>
							<option>064</option>
							<option>070</option>
					</select>-<input type="text" maxlength="4"
						minlength="3" name="tel_middle">-<input
						 type="text" maxlength="4" minlength="4"
						name="tel_behind"></td>
				</tr>
				<tr>
					<td>이메일 주소</td>
					<td><input type="text" maxlength="40" minlength="1"
						name="email_front">@<input type="text" maxlength="40"
						minlength="1" name="email_behind"></td>
				</tr>
				<tr>
					<td>개인정보 제공 동의</td>
					<td>동의합니다<input type="checkbox" value=true name="agree">
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<button type="button" class="submit">가입하기</button>
						<button type="button" class="reset">취소하기</button>
					</td>
				</tr>
			</table>
			<hr>
		</div>
	</div>


	<jsp:include page="${contextPath }\common\footer.jsp"></jsp:include>


</body>
<style>
.form {
	width: 60%;
	min-height: 800px;
	position:relative;
	left:20%;
	text-align: center;
	background: white;
	border: 1px solid;
}

.form * {
	height: 2.5rem;
	line-height: 2.5rem;
	vertical-align: middle;
}

.form table {
	width: 70%;
	position: relative;
	left:50%;
	transform:translate(-50%);
}

.form table td {
	height: 4rem;
}
.form table td:first-child{
	width: 30%;
	padding-right:3%;
	text-align: right;
}
.form table td:not(:first-child){
	width: 67%;
	text-align: left;
}
.form input {
	height: 3rem;
	margin: 0;
}

.form input:not([type="chekcbox"] ), .form select {
	border: 1px solid;
	border-radius: 0.3rem;
	background: grey;
}
</style>
<script type="text/javascript">
$(".reset").on("click",function(){
	location.href='<%=(request.getHeader("referer") == null
					? "https://www.coralprogram.com"
					: request.getHeader("referer"))%>';
					});
	$(".submit").on("click", function() {
		var form = $(document.createElement("form"));
		var value = new Object();
		if ($(".form input, .form select").hasClass("pass")) {
			alert("지나가시게 젊은이");
		} else {
			alert("입구컷");
		}
		value.id = $("input[name='id']").val();
		value.password = $("input[name='password']").val();
		value.passwordRe = $("input[name='passwordRe']").val();
		value.name = $("input[name='name']").val();
		value.gender = $("select[name='gender']").val();
		value.phone_front = $("select[name='phone_front']").val();
		value.phone_middle = $("input[name='phone_middle']").val();
		value.phone_behind = $("input[name='phone_behind']").val();
		value.address = $("input[name='address']").val();
		value.address_detail = $("input[name='address_detail']").val();
		value.company_name = $("input[name='company_name']").val();
		value.tel_front = $("select[name='tel_front']").val();
		value.tel_middle = $("input[name='tel_middle']").val();
		value.tel_behind = $("input[name='tel_behind']").val();
		value.agree = $("input[name='agree']").val();
		console.log(value);
	});
	var check = new Object;
	check.mail = /^[a-z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/;
	check.URL = /^(file|gopher|news|nntp|telnet|https?|ftps?|sftp):\/\/([a-z0-9-]+\.)+[a-z0-9]{2,4}.*$/;
	check.tel = /(\d{3}).*(\d{3}).*(\d{4})/;
	check.date = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
	check.tail = /([^\s]+(?=\.(jpg|gif|png))\.\2)/;
	check.id = /(?=.*\d)(?=.*[a-zA-Z]).{8,15}/;
	check.name = /[가-힣]{1,5}/;
	$("input[name='name']").focusout(function() {
		if (check.name.test($(this).val())) {
			$(this).addClass("pass");
		} else {
			alert("영 안좋은 이름이야");
			$(this).val("");
		}
	});
	$("input[name='id']").focusout(function() {
		if (check.id.test($(this).val())) {
			$(this).addClass("pass");
		} else {
			alert("영 안좋은 아이디이야");
			$(this).val("");
		}
	});
	$("input[name='password']").focusout(function() {
		if (check.id.test($(this).val())) {
			$(this).addClass("pass");
		} else {
			alert("이 비밀번호 난 반대 일세");
			$(this).val("");
		}
	});
	$("input[name='passwordRe']").focusout(function() {
		if ($(this).val() == $("input[name='password']").val()) {
			$(this).addClass("pass");
		} else {
			alert("서로 맞닿지 아니하며");
			$(this).val("");
		}
	});
</script>
<!-- 외부 js -->
<script
	src="https://cdn.ckeditor.com/ckeditor5/15.0.0/classic/ckeditor.js"></script>

<!-- 내부 js -->
<script src="${contextPath}/js/web-functions.js" type="text/javascript"
	charset="utf-8"></script>
</html>