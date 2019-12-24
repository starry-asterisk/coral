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
<link rel="stylesheet" type="text/css" href="${contextPath}/css/Editor.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/default.css"/>


<head>
<meta charset="UTF-8">
<title>Coral - 목록</title>
</head>

<body>

<jsp:include page="${contextPath }\common\header2.jsp"></jsp:include>


<div class="base_">
	<div class="board">
		<input type="text" class="title_area" placeholder="제목">
		<textarea name="content" id="editor">
			${content }
		</textarea>
	</div>
	<div class="user_sub">
		<div class="custom-select">
  		<select>
    		<option>카테고리 선택:</option>
    		<option value="1">Audi</option>
    		<option value="2">BMW</option>
    		<option value="3">Citroen</option>
    		<option value="4">Ford</option>
    		<option value="5">Honda</option>
    		<option value="6">Jaguar</option>
    		<option value="7">Land Rover</option>
    		<option value="8">Mercedes</option>
    		<option value="9">Mini</option>
    		<option value="10">Nissan</option>
    		<option value="11">Toyota</option>
    		<option value="12">Volvo</option>
  		</select>
		</div>
		<input type="text" class="basic_input" placeholder="예약일자">
		<input type="text" class="basic_input" placeholder="첨부파일">
		<div class="tag_form" placeholder="태그 입력"><input type="text" value="" maxlength="25" ></div>
		<button type="button" class="basic_button">발행</button>
		<button type="button" class="basic_button">임시저장</button>
	</div>
	${attachment}
</div>


<jsp:include page="${contextPath }\common\footer.jsp"></jsp:include>


</body>
<!-- 외부 js -->
<script src="https://cdn.ckeditor.com/ckeditor5/15.0.0/classic/ckeditor.js"></script>

<!-- 내부 js -->
<script src="${contextPath }/js/editor.js"></script>
<script src="${contextPath}/js/web-functions.js" type="text/javascript" charset="utf-8"></script>
<script>${Code}<%=request.getParameter("Code")%></script>
</html>