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
<title>Coral - 목록</title>
</head>

<body>

<jsp:include page="\common\header2.jsp"></jsp:include>


<div class="base_">
	<div class="board">
		<input type="text" class="title_area" placeholder="제목" value="${board.title }">
		<textarea name="content" id="editor">
			${board.contents }
		</textarea>
	</div>
	<div class="user_sub">
	<c:if test="${!empty cl_no}">
		<input type="text" class="basic_input" placeholder="강좌번호" name="cl_no" readonly value="${cl_no}">
	</c:if>
		<input type="text" class="basic_input" placeholder="작성자" readonly value="${id}">
		<div class="basic_input" style="margin-bottom:30px;overflow: hidden;">
			<button class="reset" onclick="$($('.fileList')[0]).find('button').trigger('click')">리셋</button>
    		<button class="fold">접기</button>
			<div class="fileList"></div>
			<input name="files" type="file" placeholder="첨부파일" id="input-image" multiple data-image=true>
			<label for="input-image">이미지 업로드</label>
		</div>
		<div class="basic_input" style="margin-bottom:30px;overflow: hidden;">
			<button class="reset" onclick="$($('.fileList')[0]).find('button').trigger('click')">리셋</button>
    		<button class="fold">접기</button>
			<div class="fileList"></div>
			<input name="files" type="file" placeholder="첨부파일" id="input-file" multiple data-image=false>
			<label for="input-file">첨부파일 업로드</label>
		</div>
		<c:if test="${Category.size()>0}">
  		<div class="custom-select">
  		<select>
    		<option value="">카테고리 선택:</option>
    		<c:forEach var= "list" items="${Category}">
				<option value="${list.code}" ${board.tag.equals("false")?"":(board.category.equals(list.code)?"selected":"")}>${list.name}</option>
			</c:forEach>
  		</select>
		</div>
  		</c:if>
  	<c:if test="${empty cl_no}">
		<div class="tag_form" placeholder="태그 입력"><input type="text" value="" maxlength="25" ></div>
	</c:if>
		<button type="button" class="basic_button" onclick="upload('P',${isNew!=false},'${(empty cl_no)?"board":"lecture"}')">${isNew!=false?'발행':'수정완료'}</button>
		<button type="button" class="basic_button" onclick="if(confirm('강의글을 삭제 하시겠습니까?')){upload(${isNew!=false?'\'S\'':'\'N\''},${isNew!=false},'${(empty cl_no)?"board":"lecture"}')}">${isNew!=false?'임시저장':'삭제'}</button>
	</div>
</div>


<jsp:include page="\common\footer.jsp"></jsp:include>


</body>

<!-- 내부 js -->
<script type="text/javascript">
var CK_Config = new Object;
CK_Config.display= '';
CK_Config.isReadonly = false;
</script>
<jsp:include page="${include }.jsp" ></jsp:include>
<script src="/js/editor.js"></script>
<script src="/js/web-functions.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/ajax.js"></script>
<script type="text/javascript">
var no  = "${board.no }";
var cl_no  = "${cl_no }";
if(${(empty cl_no)&&board.tag!=null}){
	var tags = '${board.tag}';
	tags = tags.split("#");
	tags.forEach(function(tag){
		if(tag!=null&&tag!=''&&tag!=undefined){
			addTag(tag);
		}
	});
}

if(${(empty cl_no)&&board.category!=null}){
	var category = '${(empty cl_no)?board.category:""}';
	$("option[value="+category+"]").attr("selected","selected");
}

<c:forEach items="${attachment}" var="file">
loadAttach("${file.path}","${file.name}",${file.image},${file.order});
</c:forEach>
</script>
</html>