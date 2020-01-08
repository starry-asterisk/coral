<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
	href="${contextPath}/css/default.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/fontawesome/css/all.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/css/userPage.css" />

<head>
<meta charset="UTF-8">
<title>Coral -</title>
</head>

<body>

	<div class="background">
		<div class="profile_back"></div>
		<div class="profile">
			<div id="profile_image">
				<button type="button" title="새로운 프로필 이미지"><i class="fas fa-user"></i></button>
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
		<div class="toolbar btnRow">
		<button type="button" style="height: 48.1px;">1</button><button type="button" style="height: 48.1px;">1</button><button type="button" style="height: 48.1px;">1</button><button type="button" style="height: 48.1px;">1</button><button type="button" style="height: 48.1px;">1</button><button type="button" style="height: 48.1px;">1</button>
		</div>
		<div class="workSpace"></div>
	</div>

	<jsp:include page="${contextPath }\common\footer.jsp"></jsp:include>


</body>
<!-- 외부 js -->
<script src="${contextPath}/resources/fontawesome/js/all.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript">
$('.profile').css({
    'transition': 'all 1s' 
});
$(window).scroll(function(){
    $('.profile').css({
        'top': $(this).scrollTop()
    });
});

</script>
<!-- 내부 js -->
<script src="${contextPath}/js/web-functions.js" type="text/javascript"
	charset="utf-8"></script>
</html>