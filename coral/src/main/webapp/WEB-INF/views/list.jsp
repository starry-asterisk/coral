<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<!-- JQuery -->
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>

<!-- 외부 css -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- 내부 css -->
<link rel="stylesheet" type="text/css" href="${contextPath}/css/default.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/List.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/list-style.css"/>

<head>
<meta charset="UTF-8">
<title>Coral - 목록</title>
</head>

<body>

<jsp:include page="${contextPath }\common\header2.jsp"></jsp:include>

<div class="base_">
	<div class="Search"><input type="text" name="key_word" placeholder="검색 키워드를 입력하세요!" spellcheck="false"><button  class="icon_search_btn" title="검색"></button></div>
	<div class="board">
	<jsp:include page="${Board_type}.jsp" ></jsp:include>
	</div>
	<div class="user_sub"></div>
	<jsp:include page="${attachment}.jsp" ></jsp:include>
</div>

<jsp:include page="${contextPath }\common\footer.jsp"></jsp:include>

</body>

<!-- 내부 js -->
<script src="${contextPath}/js/web-functions.js" type="text/javascript" charset="utf-8"></script>
<script src="${contextPath}/js/ajax.js" type="text/javascript" charset="utf-8"></script>
<script>${Code}<%=request.getParameter("Code")%></script>

</html>