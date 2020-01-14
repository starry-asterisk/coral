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
		<p>추천수 : ${board.recommends } 조회수 : ${board.views } 작성일 : <fmt:formatDate pattern = "MM-dd HH:mm" value = "${board.regdate }" /></p>
			${board.contents }
		</textarea>
	</div>
	<div class="user_sub">
	<br>
	
	<span style="float:left;text-align:left;margin-left:10px">게시글 번호 : ${board.no }<br>작성자: <a href="/userpage?id=${board.id }">${board.id }</a></span>
	<button type="button" title="수정" class="likeBtn" onclick="${id==board.id}?location.href='/board/edit?bno=${board.no}':alert('본인의 게시물만 수정가능합니다.');" style="margin-left: 19%;"><i class="fas fa-edit"></i></button>
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
	<hr class="tag">
	<button id="tag" type="button" onclick="history.back()" style="height:56px;width:100%;margin:20px 0 20px 0;border:0px solid #eee;border-top-width:1px;border-bottom-width:1px;color:#337ab7">돌아가기</button>
	<div id="newReply" contenteditable style="min-height:90px;padding: 0 70px 0 70px;" placeholder="댓글을 입력해 주세요">댓글을 입력해 주세요</div>
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
$("#newReply").on("input",function(){
	var length = $(this).html().replace( /[<][^>]*[>]/gi,"").length;
	$("#ReBytelimit span").html(length);
	if(length>200){
		$("#ReBytelimit span").css("color","red");
	}else{
		$("#ReBytelimit span").css("color","#333");
	}
	
});

function get(data){
	if(data==false){
		attReply(false);
	}else{
		data.forEach(function(obj, index){
			obj.regdate = new Date(obj.regdate);
			if(obj.update!=null&&obj.update!=undefined){
				obj.update = new Date(obj.update);
			}
			attReply(obj);
		});
	}
}
$("#report").click(function(){
	if(id!=undefined&&id!=""){
		var reanson = getReason('R');
		var result = report(bno,false,id,reanson);
		$("button.btn.btn-default").on("click",function(){
			reportSubmit(result.id , result.type , result.reason());
		});
	}else{
		alert("로그인 이후에 신고기능을 이용할 수 있습니다.");
	}
});

$("#edit").click(function(){
	if(id!=undefined&&id!=""){
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
#reply{
	width: 100%;
	position: relative;
}
#reply tr:nth-child(2n-1) td:nth-child(1) button{
	width:60px;
	height:60px;
	overflow:hidden;
	border-radius:100%;
	border:1px solid #eee;
	box-shadow: 0 0 17px 2px rgba(60,64,66,0.05);
	margin:24px;
	font-size: 2.5em;
    font-weight: 900;
}
#reply tr:nth-child(2n-1) td:nth-child(1) button img{
	width:60px;
}
#reply tr:nth-child(2n){
	border-bottom:1px solid #eee;
}
#reply tr:nth-child(2n-1) td:nth-child(2) button{
	float: right;
    margin-right: 5%;
}
#reply tr:nth-child(2n) td{
	vertical-align: top;
    text-align: left;
    padding-bottom: 20px;
    padding-right: 3%;
    word-break: break-all;
}
#reply tr:nth-child(2n-1) td:nth-child(2){
	padding-bottom: 10px;
    padding-top: 30px;
    height: 1.5em;
    text-align:left;
}
#reply tr:nth-child(2n-1) td:nth-child(3){
	position: absolute;
    z-index: 2;
    width: 0;
    height: 100%;
    right: 0;
    background:pink;
    transition: width 1s;
    overflow:hidden;
}
#reply tr:nth-child(2n-1) td:nth-child(3) div{
	position: absolute;
    width: 442px;
    min-width: 100%;
    max-width: 459px;
    height: 100%;
    left: 0;
}
#reply tr:nth-child(2n-1) td:nth-child(1){
	width:108px;
	vertical-align: top;
}
#reply tr:nth-child(2n-1)>td>span{
	margin-left: 5px;
    color: #aaa;
}
#reply tr:last-child{
	border-bottom:0;
}
</style>
</html>