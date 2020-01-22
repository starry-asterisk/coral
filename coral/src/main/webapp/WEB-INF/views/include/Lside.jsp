<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test='${!(empty grade || grade=="학생")}'>
<button class="subbtn" type="button" style="margin-top:20px;" onclick="location.href='/lecture/write?cl_no=${cl_no}'">강의 업로드</button>
</c:if>
<button class="subbtn" type="button" style="border-top-width:0;" onclick="history.back()">돌아가기</button>

<div class="classInfo">
<div>강좌 번호<input type="text" value="${!empty Class?Class.cl_no:''}" readonly></div>
<c:choose>
	<c:when test="${!empty Class?(Class.id==id):false}">
<div>강좌 제목<input type="text" value="${!empty Class?Class.cl_title:''}"  id="title"></div>
<div>강좌 설명<div class="description" contenteditable='true'>${!empty Class?Class.cl_description:''}</div></div>
<div><div class="tag_form" placeholder="태그 입력" style="margin-top: 15px;"><input type="text" value="" maxlength="25"></div></div>
<div>교사 이름<input type="text" value="${!empty Class?Class.id:''}" readonly></div>
<div><button class="editClass">수정 완료</button><button class="closeApply">폐강 신청</button></div>
<link rel="stylesheet" type="text/css" href="/css/Editor.css"/>
	</c:when>
	<c:otherwise>
<div>강좌 제목<input type="text" value="${!empty Class?Class.cl_title:''}" readonly></div>
<div>강좌 설명<div class="description">${!empty Class?Class.cl_description:''}</div></div>
<div>태그 목록<input type="text" value="${!empty Class?Class.cl_tag:''}" readonly></div>
<div>교사 이름<input type="text" value="${!empty Class?Class.id:''}" readonly></div>
	</c:otherwise>
</c:choose>
</div>
<style>
.editClass, .closeApply{
	width:90px;
	height:30px;
	border:1px solid #eee;
}
.closeApply{
	margin-left:15px;
}
.editClass:hover, .editClass:active, .closeApply:hover, .closeApply:active{
	outline:1px solid skyblue;
}
.editClass:active, .closeApply:active{
	box-shadow: 0 0 10px 5px rgba(0,0,0,0.05) inset;
}
.classInfo{
	border-bottom:1px solid #eee;
	padding-bottom:10px;
}
.classInfo>div{
	text-align:left;
	padding:10px 20px 0 20px;
}
.classInfo>div>input, .description{
	float:right;
	text-align:left;
	width:80%;
} 
</style>
<c:if test="${!empty Class?(Class.id==id):false}">
<script src="/js/editor.js" type="text/javascript" charset="utf-8"></script>

<script>
var tags = '${!empty Class?Class.cl_tag:false}';
if(tags!='false'){
	tags = tags.split("#");
	tags.forEach(function(tag){
		if(tag!=null&&tag!=''&&tag!=undefined){
			addTag(tag);
		}
	});
}
$(".tag_form input").trigger("click");
$(".tag_form input").trigger("focusout");
$(".editClass").click(function(){
	var form = mkForm("lecture/update","POST");
	form.addValue("cl_no","${!empty Class?Class.cl_no:''}");
	form.addValue("cl_title",$("#title").val());
	form.addValue("cl_description",$(".description").html());
	form.addValue("cl_tag",getTag());
	form.submit();
});
$(".closeApply").click(function(){
	if("${!empty Class?(Class.id==id):false}"!="false"){
		var reanson = getReason('F');
		var result = closeApply("${!empty Class?(Class.cl_no):''}","${id}",reanson);
		$("button.btn.btn-default").on("click",function(){
			reportSubmit(result.id , result.type , result.reason());
		});
	}else{
		alert("본인의 강좌만 폐강 할수 있습니다");
	}
});
</script>
</c:if>

