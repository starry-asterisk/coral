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
<link rel="stylesheet" type="text/css" href="/resources/ext/fontawesome/css/all.css" />

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
		<div class="title_area" style="line-height:50px;font-size: 1.5em;font-weight:bold;padding-left:25px;">
		${board.title }
		</div>
		<textarea name="content" id="editor">
		추천수 : ${board.recommends } / 조회수 : ${board.views } / 작성일 : <fmt:formatDate pattern = "MM-dd HH:mm" value = "${board.regdate }" />${board.tag.equals("false")?"":" / 카테고리 : "}${board.tag.equals("false")?"":board.category}
			${board.contents }
		</textarea>
	</div>
	<div class="user_sub">
	<br>
	
	<span style="float:left;text-align:left;margin-left:10px">게시글 번호 : ${board.no }<br>작성자: <a href="/userpage?id=${board.id }">${board.id }</a></span>
	<button type="button" title="수정" class="likeBtn" onclick='${id==board.id}?location.href="/${board.tag.equals("false")?"lecture/edit?no":"board/edit?bno"}=${board.no}":alert("본인의 게시물만 수정가능합니다.");' style="margin-left: 19%;"><i class="fas fa-edit"></i></button>
	<button type="button" title="신고" class="likeBtn" id="report"><i class="fas fa-exclamation-triangle"></i></button>
	<button type="button" title="추천" class="likeBtn" id="like"><i class="far fa-thumbs-up"></i></button>
	<button type="button" title="비추천" class="likeBtn" id="unlike"><i class="far fa-thumbs-down"></i></button>
	<br>
	<hr>
	<div class="basic_input" style="overflow: hidden;">
		<div class="fileList">
			<c:forEach items="${attachment}" var="file" varStatus="status">
				<c:if test="${status.index!=0}"><hr></c:if>
				<a href="${file.path}" download>${file.name}</a><span style="float:right">${file.size}Byte</span><br>
			</c:forEach>
		</div>
	</div>
	<c:if test='${board.no.substring(0,1).equals("B")}'>
		<hr class="tag">
	</c:if>
	<button id='${board.tag.equals("false")?"":"tag"}' type="button" onclick="history.back()" style="height:56px;width:100%;margin:20px 0 20px 0;border:0px solid #eee;border-top-width:1px;border-bottom-width:1px;color:#337ab7">돌아가기</button>
	<div id="newReply" contenteditable="true" style="min-height:90px;padding: 0 70px 0 70px;" placeholder="댓글을 입력해 주세요"></div>
	<button type="button" title="글자수 재한" class="likeBtn" id="ReBytelimit" style="width:120px;margin-left: 20px;font-size:1.2em;font-weight:900;"><span>0</span>/200</button>
	<button type="button" title="리셋 버튼" class="likeBtn" onclick="$('#newReply').html('');$('#ReBytelimit span').html(0);$('#ReBytelimit span').css('color','#333');" style="margin-left:44%;"><i class="fas fa-toilet-paper"></i></button>
	<button type="button" title="덧글 전송" class="likeBtn" onclick="replySend(bno, $('#newReply'));"><i class="fas fa-paper-plane"></i></button>
	<hr style='margin-bottom:0;'>
	<div style='overflow-x: hidden;
    max-height: 390.5px;'><table id="reply"></table></div>
	</div>
</div>


<jsp:include page="\common\footer.jsp"></jsp:include>


</body>
<!-- 외부 js -->
<script src="/resources/ext/fontawesome/js/all.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
var CK_Config = new Object;
CK_Config.display= 'none';
CK_Config.isReadonly = true;

var id   = "${id}";
var bno  = "${board.no }";
var tags = "${board.tag}";
</script>
<jsp:include page="${include }.jsp" ></jsp:include>

<!-- 내부 js -->
<script src="/js/web-functions.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/ajax.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/detail.js" type="text/javascript" charset="utf-8"></script>
</html>