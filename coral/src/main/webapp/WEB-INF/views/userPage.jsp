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
<style>
.map td[colspan="2"]{
	padding:10px;
}
.map td[colspan="2"] table{
	width:100%;
}
</style>

<script src="/js/web-functions.js" type="text/javascript"
	charset="utf-8"></script>

<head>
<meta charset="UTF-8">
<title>Coral -</title>
</head>

<body>

	<div class="background">
		<div class="profile_back"></div>
		<div class="profile">
			<div id="profile_image">
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
					<button onclick="location.href=''" style="text-align:center;">> 강좌　　</button>
					<button onclick="location.href='/'" style="text-align:center;">> 메인　　</button></td>
				</tr>
			</table>
		</div>
		<div class="workSpace" style="padding-top: 0px;">
			<table class="map">
  <tr>
  	<td colspan="2">
  		<jsp:include page="include/board.jsp"></jsp:include>
  		<hr style="margin:3px;">
  		<jsp:include page="include/class.jsp"></jsp:include>
  	</td>
  </tr>
  <tr>
    <td>
    	<span>프로필</span>
    	<input autocomplete="off" readonly type="text" maxlength="20" minlength="8" value="${userInfo.id}" class="pass" >:Id
    	<input type="text" maxlength="5" minlength="2" name="name" value="${userInfo.name}">:Name
    	<input type="date" name="birth" min="1900-01-01" max="2005-12-19" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${userInfo.birth}" />">:Birth
    </td>
    <td>
    	<span>기본연락처</span>
    	<select name="phone_front" style="max-width: 70px;width: 70px;min-width: 60px;">
			<option hidden="hidden" selected>----</option>
			<option value="010" ${userInfo.phone.split("-")[0] eq "010" ? "selected":""}>010</option>
			<option value="011" ${userInfo.phone.split("-")[0] eq "011" ? "selected":""}>011</option>
			<option value="016" ${userInfo.phone.split("-")[0] eq "016" ? "selected":""}>016</option>
			<option value="017" ${userInfo.phone.split("-")[0] eq "017" ? "selected":""}>017</option>
			<option value="019" ${userInfo.phone.split("-")[0] eq "019" ? "selected":""}>019</option>
		</select> - <input value="${userInfo.phone.split("-")[1]}" type="text" maxlength="4" minlength="4" name="phone_middle" style="max-width: 70px;width: 70px;min-width: 60px;"> - 
					<input value="${userInfo.phone.split("-")[2]}" type="text" maxlength="4" minlength="4" name="phone_behind" style="max-width: 70px;width: 70px;min-width: 60px;">:Phone
    	<input value='${userInfo.mail=="P"?" ":userInfo.mail.split("@")[0]}' style="max-width: 100px;min-width: 30%;" type="text" maxlength="40" minlength="1" name="email_front" spellcheck="false">@<input value='${userInfo.mail=="P"?" ":userInfo.mail.split("@")[1]}' style="max-width: 100px;min-width: 30%;" type="text" maxlength="40" minlength="1" name="email_behind" spellcheck="false" value="　">:Email
    </td>
  </tr>
  <tr>
    <td>
  		<span>회사연락처</span>
  		<input readonly type="text" value="${userInfo.address.split("/")[0]}">:Zip
    	<input readonly type="text" value="${userInfo.address.split("/")[1]}">:Base
    	<input readonly type="text" value="${userInfo.address.split("/")[2]}">:Detail
  	</td>
    <td><div id="map"></div></td>
  </tr>
</table>
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
var container = document.getElementById('map');
var options = {
	center: new kakao.maps.LatLng(33.450701, 126.570667),
	level: 3
};

var map = new kakao.maps.Map(container, options);

if(address!=''){
	address_detail = address.split("/")[1];
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(address_detail, function(result, status) {

	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {

	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });

	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+company+'</div>'
	        });
	        infowindow.open(map, marker);

	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	    } 
	});   
}
</script>
</html>