<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${grade=='관리자'||grade=='교사'}">
<button id="class" class="subbtn" type="button">강좌개설</button>
<div id="form" style="display:none">
<input name="title" type="text" class="basic_input" placeholder="제목">
<div name="description" class="basic_input" contenteditable="true" style="margin-top: 0;padding: 7px;">설명을 입력하세요</div>
<div name="tag" class="tag_form" placeholder="태그 입력" style="margin-top: 15px;"><input type="text" value="" maxlength="25"></div>
<button id="reset" type="button">취소</button>
<button id="submit" type="button">개강</button>
</div>
<link rel="stylesheet" type="text/css" href="/css/Editor.css"/>
<script src="/js/editor.js"></script>
<script>
$("#class").click(function(){
	$('#form').css('display','');
	$(this).css('display','none');
});

$("#reset").click(function(){
	alert('작성이 취소되었습니다');
	$('#form').css('display','none');
	$('#form input[name=title]').val('');
	$('#form div[name=description]').html('설명을 입력하세요');
	$('#form div[name=tag] button').trigger('click');
	$('#form div[name=tag] input').val('');
	$("#class").css('display','');
});

$("#submit").click(function(){
	if($('#form div[name=description]').html()!=undefined&&$('#form div[name=description]').html()!='설명을 입력하세요'){
		if($('#form input[name=title]').val()!=undefined&&$('#form input[name=title]').val().length>0){
			if(getTag().length>1){
				$("#class").css('display','');
				var form = mkForm('/lecture/create','POST');
				form.addValue('cl_title',$("#form input[name=title]").val());
				form.addValue('cl_description',$("#form div[name=description]").html());
				form.addValue('cl_tag',getTag());
				form.submit();
				$('#form').css('display','none');
			}else{
				alert('태그를 입력하세요');
			}			
		}else{
			alert('제목을 입력하세요');
		}
	}else{
		alert('설명을 입력하세요');
	}
});
</script>
<style>

#class{
	margin: 20px 0 0 0;
}
#reset,  #submit{
    border: 1px solid #eee;
    height: 30px;
    padding: 0 10px 0 10px;
    margin-bottom:15px;
}
#submit:active, #reset:active {
    box-shadow: 0 0 10px 1px rgba(0,0,0,0.02) inset;
    outline: 1px solid skyblue;
}
#reset{
	margin-left:5%;
}
#submit{
	margin-left:15px;
	margin-right: 70%;
}
#form{
	border-top: 1px solid #eee;
	border-bottom: 1px solid #eee;
	margin: 20px 0 0 0;
}
</style>
</c:if>
<button class="subbtn" type="button" style="border-top-width:0;" onclick="location.href='/board'">게시판</button>
<button class="subbtn" type="button" style="border-top-width:0;" onclick="location.href='/'">메인</button>
