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

<header>
	<div class="logo">
	<a href="/" title="메인 화면으로 이동">코딩중계사 코랄</a>
	</div>
	<div class="bar">
	</div>
</header>


<div class="base_">
	<div class="main_sub">
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

<button class="ScrollUpButton" title="맨 위로 이동">&#10514;</button>
<button class="main_sub_sideBtn" title="실행버튼">▷</button>

<footer>
	<div class="notice">
	<a href="/" style="font-weight:600;line-height:37.34px;">공지사항</a>
	</div>
	<div class="external_link">
		&#60;
		<a href="https://www.youtube.com/"><img title="유튜브홍보링크" alt="youtube" width="60px" height="60px" src="/resources/icon/youtube.png"></a>
		
		<a href="https://github.com/"><img title="깃헙링크" alt="github" width="60px" height="60px"  src="/resources/icon/github.png"></a>
		
		<a href="https://www.naver.com/"><img title="네이버" alt="naver" width="60px" height="60px"  src="/resources/icon/naver.png"></a>
		
		<a href="http://www.moel.go.kr/"><img title="고용노동부" alt="moel" width="60px" height="60px"  src="/resources/icon/government.png"></a>
		
		<a href="http://www.icia.co.kr/"><img title="icia교육원" alt="icia" width="60px" height="60px"  src="/resources/icon/icia.png"></a>
		&#62;
	</div>
	<div class="info">
	사이트소개 | 이용안내 | 이용약관 | 제작인원 소개<br/>
	Copyrightⓒ2019 Coral All rights reserved
	</div>
</footer>



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
<script src="js/web-functions.js" type="text/javascript" charset="utf-8"></script>
<script src="js/ajax.js" type="text/javascript" charset="utf-8"></script>

</html>