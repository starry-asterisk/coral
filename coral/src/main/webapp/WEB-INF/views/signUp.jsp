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
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/signup.css" />
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
					<td><input autocomplete="off" type="text" maxlength="20" minlength="8" name="id" >
					</td><td></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input autocomplete="off" type="password" maxlength="25" minlength="8"
						name="password"  autocomplete="nope"></td><td></td>
				</tr>
				<tr>
					<td>비밀번호 재입력</td>
					<td><input type="password" maxlength="25" minlength="8"
						name="passwordRe"></td><td></td>
				</tr>
				<tr>
					<td>성명</td>
					<td><input type="text" maxlength="5" minlength="2" name="name">
					</td><td></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="date" name="birth" min="1900-01-01" max="2005-12-19" value="2000-01-01"></td><td></td>
				</tr>
				<tr>
					<td>성별</td>
					<td><select name="gender">
							<option hidden selected>----</option>
							<option value="ma">남자</option>
							<option value="fe">여자</option>
							<option value="et">기타</option>
					</select></td><td></td>
				</tr>
				<tr>
					<td>핸드폰 번호</td>
					<td><select name="phone_front">
							<option hidden selected>----</option>
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="019">019</option>
					</select> - <input type="text" maxlength="4" minlength="4" name="phone_middle"> - 
					<input type="text" maxlength="4" minlength="4" name="phone_behind">
					</td><td></td>
				</tr>
				<tr style="display:${is}">
					<td>회사 명</td>
					<td><input type="text" maxlength="50" minlength="1"
						name="company_name"></td><td></td>
				</tr>
				<tr style="display:${is}">
					<td>회사 우편 번호</td>
					<td><input type="text" readonly name="zipcode" value="" readonly> 
					<button type="button" onclick="execDaumPostcode()">찾기</button></td><td></td>
				</tr>
				<tr style="display:${is}">
					<td>회사 기본 주소</td>
					<td><input type="text" readonly name="address" value="" readonly></td><td></td>
				</tr>
				<tr style="display:${is}">
					<td>회사 주소 상세</td>
					<td><input type="text" maxlength="50" minlength="0"
						name="address_detail" value=""></td><td></td>
				</tr>
				<tr style="display:${is}">
					<td>회사 연락처</td>
					<td><select name="tel_front">
							<option hidden selected>----</option>
							<option value="02">02</option>
							<option value="031">031</option>
							<option value="032">032</option>
							<option value="033">033</option>
							<option value="041">041</option>
							<option value="043">043</option>
							<option value="042">042</option>
							<option value="044">044</option>
							<option value="051">051</option>
							<option value="052">052</option>
							<option value="053">053</option>
							<option value="054">054</option>
							<option value="055">055</option>
							<option value="061">061</option>
							<option value="062">062</option>
							<option value="063">063</option>
							<option value="064">064</option>
							<option value="070">070</option>
					</select> - <input type="text" maxlength="4" minlength="3" name="tel_middle"> - <input
						 type="text" maxlength="4" minlength="4"
						name="tel_behind"></td><td></td>
				</tr>
				<tr>
					<td>이메일 주소</td>
					<td><input type="text" maxlength="40" minlength="1"
						name="email_front" spellcheck="false">@<input type="text" maxlength="40"
						minlength="1" name="email_behind" spellcheck="false"></td><td></td>
				</tr>
				<tr>
					<td>프로필 공개 여부 </td>
					<td>공개합니다<input type="checkbox" value=true name="privacy">
					</td><td></td>
				</tr>
				<tr>
					<td>개인정보 제공 동의</td>
					<td>동의합니다<input type="checkbox" value=true name="agreement">
					</td><td></td>
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

<!-- 외부 js -->
<script
	src="https://cdn.ckeditor.com/ckeditor5/15.0.0/classic/ckeditor.js"></script>

<!-- 내부 js -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
$(".reset").on("click",function(){
	location.href='<%=(request.getHeader("referer") == null
					? "https://www.coralprogram.com"
					: request.getHeader("referer"))%>';
					});
</script>
<script src="${contextPath}/js/web-functions.js" type="text/javascript" charset="utf-8"></script>
<script src="${contextPath}/js/signUp.js" type="text/javascript" charset="utf-8"></script>
<script src="${contextPath}/js/ajax.js" type="text/javascript" charset="utf-8"></script>
</html>