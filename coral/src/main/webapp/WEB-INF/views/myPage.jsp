<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<!-- JQuery -->
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="/js/jquery.form.js"></script>

<!-- 외부 css -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- 내부 css -->

<link rel="stylesheet" type="text/css"
	href="/css/default.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/ext/fontawesome/css/all.css" />
<link rel="stylesheet" type="text/css"
	href="/css/userPage.css" />

<head>
<meta charset="UTF-8">
<title>Coral -</title>
</head>

<body>

	<div class="background">
		<div class="profile_back"></div>
		<div class="profile">
			<div id="profile_image">
				<label><i class="fas fa-camera"></i><input type="file" id="prof_img_file" name="file" onChange="profileUpload(this,$('#profile_image button'))"></label>
				<c:choose>
					<c:when test="${empty prof_image}">
						<button type="button" style="background-color:coral">
							<i class="fas fa-user" title="새로운 프로필 이미지"></i>
						</button>
					</c:when>
					<c:otherwise>
						<button type="button">
							<img alt="프로필이미지" src="${prof_image}" height="100%">
						</button>
					</c:otherwise>
				</c:choose>
				
			</div>
			<table class="inform">
				<tr>
					<td class="title" style="padding:0;"><span style="float:left">회원명</span><span style="float:right">등급</span></td>
				</tr>
				<tr>
					<td><span style="float:left">${userInfo.name}</span>
						<span style="float:right">${userInfo.grade}
							<c:choose>
								<c:when test="${userInfo.grade=='관리자'}">
									<i class="fas fa-user-tie"></i>
								</c:when>
								<c:when test="${userInfo.grade=='교사'}">
									<img alt="" src="/resources/icon/professor.svg" width="19em">
								</c:when>
								<c:otherwise>
									<img alt="" src="/resources/icon/student.svg" width="19em">
								</c:otherwise>
							</c:choose>
						</span>
					</td>
				</tr>
				<tr>
					<td class="title">아이디</td>
				</tr>
				<tr>
					<td>${userInfo.id}</td>
				</tr>
				<tr>
					<td class="title">이메일</td>
				</tr>
				<tr>
					<td>${userInfo.mail=="P"?"미인증 이메일":userInfo.mail}</td>
				</tr>
				<tr>
					<td class="title">최근 접속 일시</td>
				</tr>
				<tr>
					<td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss" value = "${userInfo.date}" /></td>
				</tr>
				<tr class="profile_nav">
					<td style="padding-top:10px;">
					<button onclick="location.href='/board'" style="text-align:center;">> 게시판　</button>
					<button onclick="location.href='/lecture'" style="text-align:center;">> 강좌　　</button>
					<button onclick="location.href='/'" style="text-align:center;">> 메인　　</button></td>
				</tr>
			</table>
			<table id="clockKR">
        <tr>
            <td class="1">한</td>
            <td class="2">두</td>
            <td class="3">세</td>
            <td class="4">네</td>
            <td class="5">다</td>
            <td class="6">섯</td>
        </tr>
        <tr>
            <td class="7">여</td>
            <td class="8">섯</td>
            <td class="9">일</td>
            <td class="10">곱</td>
            <td class="11">여</td>
            <td class="12">덟</td>
        </tr>
        <tr>
            <td class="13">아</td>
            <td class="14">홉</td>
            <td class="15">열</td>
            <td class="16">한</td>
            <td class="17">두</td>
            <td class="18">시</td>
        </tr>
        <tr>
            <td class="19">자</td>
            <td class="20">이</td>
            <td class="21">삼</td>
            <td class="22">사</td>
            <td class="23">오</td>
            <td class="24">십</td>
        </tr>
        <tr>
            <td class="25">정</td>
            <td class="26">일</td>
            <td class="27">이</td>
            <td class="28">삼</td>
            <td class="29">사</td>
            <td class="30">육</td>
        </tr>
        <tr>
            <td class="31">오</td>
            <td class="32">오</td>
            <td class="33">칠</td>
            <td class="34">팔</td>
            <td class="35">구</td>
            <td class="36">분</td>
        </tr>
    </table>
		</div>
		<div class="toolbar">
		
		<button type="button" onclick="myApp('schedule','.workSpace');" data-toggle="tooltip" data-placement="bottom" title="일정관리"><i class="fas fa-calendar-alt"></i></button>
		<button type="button" onclick="myApp('private','.workSpace');" data-toggle="tooltip" data-placement="bottom" title="내 정보 관리"><i class="fas fa-users-cog"></i></button>
		<!-- <button type="button" onclick="myApp('lecture','.workSpace');" data-toggle="tooltip" data-placement="bottom" title="수강관리"><i class="fas fa-school"></i></button> -->
		<button type="button" onclick="myApp('history?page=1&amount=10','.workSpace');" data-toggle="tooltip" data-placement="bottom" title="활동기록"><i class="fas fa-history"></i></button>
		<button type="button" onclick="myApp('security','.workSpace');" data-toggle="tooltip" data-placement="bottom" title="보안"><i class="fas fa-lock"></i></button>
		<c:choose>
			<c:when test="${userInfo.grade=='관리자'}">
		<button type="button" onclick="myApp('active','.workSpace');" data-toggle="tooltip" data-placement="bottom" title="활동"><i class="fas fa-chart-line"></i></button>
		<button type="button" onclick="myApp('apply','.workSpace');" data-toggle="tooltip" data-placement="bottom" title="신청"><i class="fas fa-archive"></i></button>
		<button type="button" onclick="myApp('report','.workSpace');" data-toggle="tooltip" data-placement="bottom" title="신고"><i class="fas fa-exclamation-triangle"></i></button>
			</c:when>
		</c:choose>
		</div>
		<div class="workSpace">
		
		</div>
	</div>

	<jsp:include page="\common\footer.jsp"></jsp:include>


</body>
<!-- 외부 js -->
<script src="/resources/ext/fontawesome/js/all.js" type="text/javascript"
	charset="utf-8"></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d23098cc45488875ac04e03d47349bfd"></script>
<script type="text/javascript" src="/js/services.js"></script>
<!-- 내부 js -->
<script src="/js/web-functions.js" type="text/javascript"
	charset="utf-8"></script>
<script src="/js/ajax.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript">
var address = '${userInfo.address}';
var company = '${userInfo.company}';
$('.profile').css({
    'transition': 'all 1s' 
});
$(window).scroll(function(){
    $('.profile').css({
        'top': $(this).scrollTop()
    });
});
$('[data-toggle="tooltip"]').tooltip();
myApp('${empty app?"private":app}','.workSpace');
</script>
</html>