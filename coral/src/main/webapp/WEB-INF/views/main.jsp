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
<link rel="stylesheet" type="text/css" href="${contextPath}/css/main-page.css"/>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/list-style.css"/>

<head>
<meta charset="UTF-8">
<title>Coral - Main</title>
</head>
<body>

<jsp:include page="${contextPath }\common\header1.jsp"></jsp:include>

<button class="main_sub_sideBtn square_btn" id="codeRun" title="실행버튼" style='margin-top:160px'>▷</button>
<button class="main_sub_sideBtn square_btn" id="Boardlist" title="게시글 목록" style='margin-top:80px'>▷</button>
<button class="main_sub_sideBtn square_btn" id="memo" title="메모장">▷</button>

<div class="base_">
	<div class="main_sub">
		<div class="Board_List" style="display:none">a</div>
		<div class="Board_List" style="display:none">b</div>
		<div class="Board_List">
			<!-- ACE에디터  -->
			<pre id="editor">public class HelloWorld {
    public static void main(String args[]) {
        System.out.println("Hello World");
    }
}</pre>
			<!-- ACE에디터  -->
		</div>
		<div class="Search"><input type="text" name="key_word" placeholder="검색 키워드를 입력하세요!" spellcheck="false"><button  class="icon_search_btn" title="검색"></button></div>
	</div>
	<div class="user_sub">
			<div class="login">
				<jsp:include page="${loginform }.jsp" ></jsp:include>
			</div>
	</div>
	<div class="feed_sub">
		<div class="feed_sub_main">
		<br/>
		　　<a class="calendar" width="500" data-header=true></a>
		</div>
		<div class="feed_sub_side">
			<div id="side1">
			</div>
			<div id="side2">
			</div>
			<div id="side3">
			</div>
		</div>
	</div>
</div>

<jsp:include page="${contextPath }\common\footer.jsp"></jsp:include>

</body>
<!-- ACE에디터  -->
<script src="src-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
<script>
	ace.require("ace/ext/language_tools");
    var editor = ace.edit("editor");
    editor.setTheme("ace/theme/chaos");
    editor.session.setMode("ace/mode/java");
    editor.setShowPrintMargin (false);
</script>

<!-- 내부 js -->
<script src="${contextPath}/js/web-functions.js" type="text/javascript" charset="utf-8"></script>
<script src="${contextPath}/js/ajax.js" type="text/javascript" charset="utf-8"></script>

</html>