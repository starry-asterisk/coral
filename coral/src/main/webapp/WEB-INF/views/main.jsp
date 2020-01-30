<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<!-- JQuery -->
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>

<!-- 외부 css -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/ext/fontawesome/css/all.css" />
<link rel="stylesheet" type="text/css" href="/resources/ext/weather-icons-master/css/weather-icons.css"/>
<!-- 내부 css -->
<link rel="stylesheet" type="text/css" href="/css/default.css"/>
<link rel="stylesheet" type="text/css" href="/css/main-page.css"/>
<link rel="stylesheet" type="text/css" href="/css/list-style.css"/>


<head>
<meta charset="UTF-8">
<title>Coral - Main</title>
</head>
<body>

<jsp:include page="\common\header1.jsp"></jsp:include>

<button class="main_sub_sideBtn square_btn" id="codeRun" title="실행버튼" style='margin-top:160px'>Run</button>
<button class="main_sub_sideBtn square_btn" id="Boardlist" title="게시글 목록" style='margin-top:80px'>Board</button>
<button class="main_sub_sideBtn square_btn" id="memo" title="메모장">Memo</button>

<div class="base_">
	<div class="main_sub">
		<div class="Board_List" style="display:none;overflow-y:auto;" contenteditable="true"><hr>메모장 입니다. 페이지 새로고침시에는 삭제되기때문에 주의 해주세요<hr><br>-</div>
		<div class="Board_List" style="display:none;overflow-y:auto;">게시글이 없습니다</div>
		<div class="Board_List">
			<!-- ACE에디터  -->
			<pre id="editor">public class HelloWorld {
    public static void main(String args[]) {
        System.out.println("Hello World");
    }
}</pre>
			<!-- ACE에디터  -->
		</div>
		<div class="Search"><input type="text" name="key_word" placeholder="검색 키워드를 입력하세요!" spellcheck="false"><button  class="icon_search_btn search" title="검색"></button></div>
	</div>
	<div class="user_sub">
			<div class="login">
				<jsp:include page="${loginform }.jsp" ></jsp:include>
			</div>
	</div>
	<div class="feed_sub">
		<div class="feed_sub_main" style="overflow-y: auto;">
			<div style="min-height:250px;padding:30px 0 30px 0">게시판 검색 영역</div>
			<div style="border-top:1px solid #eee;min-height:250px;padding:30px 0 30px 0">강좌 검색 영역</div>
			<div style="border-top:1px solid #eee;min-height:230px;padding:30px 0 30px 0">회원 검색 영역</div>
		</div>
		<div class="feed_sub_side">
			<div id="side1">
				<table width="100%" height="100%">
        			<tr height="80%">
            			<td width="25%"></td>
            			<td></td>
            			<td width="25%"></td>
        			</tr>
        			<tr height="20%">
            			<td colspan="3"></td>
        			</tr>
    			</table>
			</div>
			<div id="side2">
				<img style="display:" src="http://cdn.news2day.co.kr/news-images/peg/news/201912/xTZUeZmNZSrGuk5bThPec9lPt94k3yq2tNEUfU74-1575447442.jpg" alt="광고1">
				<img style="display:none" src="https://www.i-boss.co.kr/design/upload_file/__HTMLEDITOR__/dmaRzEoOQc/17ce9f5ce6fb8320c0501512da85749a_15789601923371.png" alt="광고2">
			</div>
			<div id="side3" style="overflow-y:hidden">
				<img width="100%" src="https://sellers.coupang.com/wp-content/uploads/2018/11/cover_sellers.jpg" alt="광고3">
			</div>
		</div>
	</div>
</div>

<jsp:include page="\common\footer.jsp"></jsp:include>

</body>
<!-- ACE에디터  -->
<script src="/resources/ext/ace/ace.js" type="text/javascript" charset="utf-8"></script>
<script>
	ace.require("ace/ext/language_tools");
    var editor = ace.edit("editor");
    editor.setTheme("ace/theme/chaos");
    editor.session.setMode("ace/mode/java");
    editor.setShowPrintMargin (false);
</script>
<script src="/resources/ext/fontawesome/js/all.js" type="text/javascript" charset="utf-8"></script>
<!-- 내부 js -->
<script src="/js/web-functions.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/ajax.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/weather.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
weather();
setInterval(function(){
	$("#side2 img:first-child").css("display",$("#side2 img:first-child").css("display")=="none"?"":"none");
	$("#side2 img:last-child").css("display",$("#side2 img:last-child").css("display")=="none"?"":"none");
},10000);
</script>
</html>