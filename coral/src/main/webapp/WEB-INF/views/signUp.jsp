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
					<td><input type="date" name="birth"></td><td></td>
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
					<td><input type="text" readonly name="zipcode" value=""> 
					<button type="button" onclick="execDaumPostcode()">찾기</button></td><td></td>
				</tr>
				<tr style="display:${is}">
					<td>회사 기본 주소</td>
					<td><input type="text" readonly name="address" value=""></td><td></td>
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
						name="email_front">@<input type="text" maxlength="40"
						minlength="1" name="email_behind"></td><td></td>
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
<script src="${contextPath}/js/web-functions.js" type="text/javascript"
	charset="utf-8"></script>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                $("input[name=address]").val(extraAddr);
            
            } else {
            	$("input[name=address]").val('');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            $('input[name=zipcode]').val(data.zonecode);
            $("input[name=address]").val(addr);
            // 커서를 상세주소 필드로 이동한다.
            $("input[name=address_detail]").focus();
        }
    }).open();
}
$(".reset").on("click",function(){
	location.href='<%=(request.getHeader("referer") == null
					? "https://www.coralprogram.com"
					: request.getHeader("referer"))%>';
					});
	$(".submit").on("click", function() {
		
		
		if ($(".form input, .form select").hasClass("pass")) {
			var form = mkForm("/signUpComplete","post");
			form.addValue('id',$('input[name=id]').val());
			form.addValue('pw',$('input[name=password]').val());
			form.addValue('name',$('input[name=name]').val());
			form.addValue('birth',$('input[name=birth]').val());
			form.addValue('gender',$('select[name=gender] option:not([hidden]):selected').val());
			form.addValue(
					'phone',
					$('select[name=phone_front] option:not([hidden]):selected').val()+
					"-"+$('input[name=phone_middle]').val()+
					"-"+$('input[name=phone_behind]').val()
			);
			form.addValue('address',$('input[name=zipcode]').val()+"/"+$('input[name=address]').val()+"/"+$('input[name=address_detail]').val());
			form.addValue('tel',$('select[name=tel_front] option:not([hidden]):selected').val()+"-"+$('input[name=tel_middle]').val()+"-"+$('input[name=tel_behind]').val());
			form.addValue('name',$('input[name=name]').val());
			form.addValue('company',$('input[name=company_name]').val());
			form.addValue('mail',$('input[name=email_front]').val()+"@"+$('input[name=email_behind]').val());
			
			form.submit();
		} else {
			alert("입구컷");
		}
		
	});
	
	var check = new Object;
	check.mail = /^[a-z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/;
	check.URL = /^(file|gopher|news|nntp|telnet|https?|ftps?|sftp):\/\/([a-z0-9-]+\.)+[a-z0-9]{2,4}.*$/;
	check.tel = /(\d{3}).*(\d{3}).*(\d{4})/;
	check.date = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
	check.tail = /([^\s]+(?=\.(jpg|gif|png))\.\2)/;
	check.id = /(?=.*\d)(?=.*[a-zA-Z]).{8,15}/;
	check.name = /[가-힣]{1,5}/;
	
	$(".form input, .form select").focusout(function(){
		$(this).removeClass("pass");
		switch($(this).attr("name")){
			case 'id':
				if(check.id.test($(this).val())){
					$(this).addClass("pass");
				}
				break;
			case 'password':
				if(check.id.test($(this).val())){
					$(this).addClass("pass");
				}
				break;
			case 'passwordRe':
				if(check.id.test($(this).val())){
					if($(this).val()==$("input[name=password]").val()){
						$(this).addClass("pass");
					}
				}
				break;
			case 'name':
				if(check.name.test($(this).val())){
					$(this).addClass("pass");
				}
				break;
			case 'birth':
				if($(this).val()!=""){
					$(this).addClass("pass");
				}
				break;
			case 'gender':
				if($(this).children("option:not([hidden]):selected").val()!=undefined){
					$(this).addClass("pass");
				}
				break;
			case 'phone_front':
				if($(this).children("option:not([hidden]):selected").val()!=undefined){
					$(this).addClass("pass");
				}
				break;
			case 'phone_middle':
				if($(this).val()!=""){
					$(this).addClass("pass");
				}
				break;
			case 'phone_behind':
				if($(this).val()!=""){
					$(this).addClass("pass");
				}
				break;
			case 'email_front':
				if(check.mail.test($(this).val()+"@coralprogram.com")){
					$(this).addClass("pass");
				}
				break;
			case 'email_behind':
				if(check.mail.test("test001@"+$(this).val())){
					$(this).addClass("pass");
				}
				break;
			case 'agreement':
				if($(this).is(":checked")){
					$(this).addClass("pass");
				}
				break;
			default:
				$(this).addClass("pass");
				break;
		}
	});
</script>
</html>