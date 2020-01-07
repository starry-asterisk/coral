<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<!-- JQuery -->
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="/js/jquery.form.js"></script>

<!-- 외부 css -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- 내부 css -->

<link rel="stylesheet" type="text/css" href="${contextPath}/css/default.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/userPage.css"/>

<head>
<meta charset="UTF-8">
<title>Coral - </title>
</head>

<body>
<div class="userSide">
	<div id="profile_image">
		<button type="button">사진추가</button>
	</div>
	<table>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
		</tr>
	</table>
</div>
<div class="background">
	<div class="workSpace" contenteditable>

	</div>
	
</div>

<jsp:include page="${contextPath }\common\footer.jsp"></jsp:include>


</body>
<!-- 외부 js -->

<!-- 내부 js -->
<script src="${contextPath}/js/web-functions.js" type="text/javascript" charset="utf-8"></script>
</html>