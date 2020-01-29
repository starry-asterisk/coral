<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table class="inform">
	<tr>
		<td rowspan="2"><c:choose>
				<c:when test="${empty prof_image}">
					<button type="button" class="prof_img">
						${fn:substring(userInfo.id, 0, 1)}</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="prof_img">
						<img alt="프로필이미지" src="${prof_image}" height="100%">
					</button>
				</c:otherwise>
			</c:choose></td>
		<td>
			${userInfo.name}　${userInfo.grade}
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
		<td>
			<button onclick="location.href='/logout'">로그아웃</button>
			<button onclick="location.href='/mypage'">내정보</button>
		</td>
	</tr>
</table>
<table style="width:100%">
	<tr>
		<td onclick="location.href='/board'">게시글</td>
		<td onclick="location.href='/lecture'">강좌글</td>
	</tr>
</table>
<fmt:formatDate pattern = "yyyy-MM-dd HH:mm" value = "${schedule.start}" />
<br>
<fmt:formatDate pattern = "yyyy-MM-dd HH:mm" value = "${schedule.end}" />
<br>
${schedule.name}<br>
${schedule.contents}<br>
${schedule.color}<br>
<style>
.login * {
	color: white;
}
.login button.prof_img{
	overflow: hidden;
    border-radius: 50%;
    border: 1px solid #333;
    width: 110px;
    height: 110px;
    margin: 20px;
    box-shadow: 0 0 15px 15px rgba(0,0,0,0.15);
    color: white;
    font-size: 5em;
    font-weight: 900;
}
.inform{
	border-bottom:1px solid white;
	margin-bottom:10px;
	width:100%;
	height:150px;
}
.inform tr:first-child td:first-child{
	width:150px;
}
.inform tr:first-child td:last-child{
	text-align:left;
	padding-top:10%;
}
.inform tr:last-child td{
	text-align:left;
	padding-bottom:10%;
}
.inform tr:last-child button{
	margin: 2% 0 2% 0;
	width: 100px;
	height: 24px;
	font-size:11px;
	color:#ddd;
	text-align:center;
	border: 1px solid #ddd;
	border-radius: 3px;
	box-shadow: 0 0 10px 1px rgba(0,0,0,0.03);
}
.inform tr:last-child button:hover, .inform tr:last-child button:active{
	border-color: skyblue;
}
</style>