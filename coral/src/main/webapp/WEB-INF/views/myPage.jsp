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
			<table>
				<tr>
					<td>${userInfo.name}</td>
				</tr>
				<tr>
					<td>${userInfo.id} / ${userInfo.grade}
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
					</td>
				</tr>
				<tr>
					<td>${userInfo.mail}</td>
				</tr>
				<tr>
					<td>${userInfo.phone}</td>
				</tr>
				<tr>
					<td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm:ss" value = "${userInfo.date}" /></td>
				</tr>
			</table>
		</div>
		<div class="toolbar">
		<button type="button" onclick="myApp('map','.workSpace');" title="내 정보 관리"><i class="fas fa-users-cog"></i></button>
		<button type="button" onclick="" title="수강관리"><i class="fas fa-school"></i></button>
		<button type="button" onclick="" title="활동기록"><i class="fas fa-history"></i></button>
		<button type="button" onclick="" title="보안"><i class="fas fa-lock"></i></button>
		</div>
		<div class="workSpace">
		<a class="calendar" width="400" data-header=true schedule="${ pre_schedule}"></a>
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

</script>
<script src="/js/web-functions.js" type="text/javascript"
	charset="utf-8"></script>
	<script src="/js/ajax.js" type="text/javascript"
	charset="utf-8"></script>
</html>