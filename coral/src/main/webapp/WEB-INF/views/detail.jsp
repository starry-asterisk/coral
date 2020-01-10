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
		<div class="title_area" style="line-height:50px">
		<span style="margin:10px;font-size: 1.5em;font-weight:bold;">${board.title }</span>
		<span style="margin-right:10px;float:right">추천수 : ${board.recommends }</span>
		<span style="margin-right:10px;float:right">조회수 : ${board.views }</span>
		</div>
		<textarea name="content" id="editor">
			${board.contents }
		</textarea>
	</div>
	<div class="user_sub">
	<br>
	
	<span style="float:left;text-align:left;margin-left:10px">게시글 번호 : ${board.no }<br>작성자        : <a href="/userpage?id=${board.id }">${board.id }</a></span>
	<button type="button" class="likeBtn" id="report" style="margin-left: 20%;"><i class="fas fa-exclamation-triangle"></i></button>
	<button type="button" class="likeBtn" id="like"><i class="far fa-thumbs-up"></i></button>
	<button type="button" class="likeBtn" id="unlike"><i class="far fa-thumbs-down"></i></button>
	<br>
	<hr>
	<div class="basic_input" style="margin-bottom:30px;overflow: hidden;">
		<div class="fileList">
			<c:forEach items="${attachment}" var="file" varStatus="status">
				<c:if test="${status.index!=0}"><hr></c:if>
				<a href="${file.path}" download>${file.name}</a><span style="float:right">${file.size}Byte</span><br>
			</c:forEach>
		</div>
	</div>
	<hr class="tag">
	<button id="tag" type="button" onclick="history.back()" style="height:56px;width:100%;margin:20px 0 20px 0;border:0px solid #eee;border-top-width:1px;border-bottom-width:1px;color:#337ab7">돌아가기</button>
	<div id="newReply" contenteditable style="min-height:90px;padding: 0 70px 0 70px;" placeholder="댓글을 입력해 주세요">댓글을 입력해 주세요</div>
	<button type="button" class="likeBtn" onclick="$('#newReply').html('');"style="margin-left:75%;"><i class="fas fa-toilet-paper"></i></button>
	<button type="button" class="likeBtn" onclick="replySend(bno, $('#newReply').html());"><i class="fas fa-paper-plane"></i></button>
	<hr>
	<table id="reply">
	</table>
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
var tags = "${board.tag }";
</script>
<jsp:include page="${include }.jsp" ></jsp:include>

<!-- 내부 js -->
<script src="/js/web-functions.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/ajax.js" type="text/javascript" charset="utf-8"></script>
<script>
replyList(bno,get);

if(tags.length>1){
	tags = tags.split("#");
	tags.forEach(function(tag){
		if(tag.length!=0){
			$("#tag").before("<a href='/ajax/search?keyword="+tag+"'style='margin-right:5px;color:#337ab7'>#"+tag+"</a>");
		}
	});
}else{
	$("#tag").before("#");
}

$("#like").click(function(){
	thumb(bno,1);
});
$("#unlike").click(function(){
	thumb(bno,-1);
});

$("#newReply").focus(function(){
	if($(this).html()==$(this).attr("placeholder")){
		$(this).html("");
	}
});
$("#newReply").focusout(function(){
	if($(this).html()==""){
		$(this).html($(this).attr("placeholder"));
	}
});

function get(data){
	if(data==false){
		attReply(false);
	}else{
		data.forEach(function(obj, index){
			attReply(obj, 'place')
		});
	}
}
$("#report").click(function(){
	if(reporter!=undefined&&reporter!=""){
		var reanson = getReason('R');
		var result = report(bno,false,id,reanson);
		$("button.btn.btn-default").on("click",function(){
			reportSubmit(result.id , result.type , result.reason());
		});
	}else{
		alert("로그인 이후에 신고기능을 이용할 수 있습니다.");
	}
});
</script>
<style>
hr.tag:after {
    content: "태그";
    height: 1em;
    color: black;
    position: absolute;
    left: 0;
    padding-top: 5px;
    padding-left: 15px;
}
</style>
</html>