<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" type="text/css" href="/css/Editor.css"/>
<link rel="stylesheet" type="text/css" href="/css/default.css"/>


<head>
<meta charset="UTF-8">
<title>Coral - ${board.title }</title>
</head>

<body>

<jsp:include page="\common\header2.jsp"></jsp:include>


<div class="base_">
	<div class="board">
		<input type="text" class="title_area" placeholder="제목" value="${board.title } | ${board.no }">
		<textarea name="content" id="editor">
			${board.contents }
		</textarea>
	</div>
	<div class="user_sub">
	</div>
	${attachment}
</div>

<a onclick="history.back()">돌아가기</a>
<jsp:include page="\common\footer.jsp"></jsp:include>


</body>
<!-- 외부 js -->
<script type="text/javascript">
var CK_Config = new Object;
CK_Config.display= 'none';
CK_Config.isReadonly = true;
</script>
<jsp:include page="${include }.jsp" ></jsp:include>

<!-- 내부 js -->
<script src="/js/web-functions.js" type="text/javascript" charset="utf-8"></script>
</html>