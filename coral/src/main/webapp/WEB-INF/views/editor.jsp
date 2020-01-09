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
		<input type="text" class="title_area" placeholder="제목">
		<textarea name="content" id="editor">
			${content }
		</textarea>
	</div>
	<div class="user_sub">
		<input type="text" class="basic_input" placeholder="작성자" readonly value="${id}">
		<input type="text" class="basic_input" placeholder="예약일자">
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
		<div class="custom-select">
  		<select>
    		<option value="">카테고리 선택:</option>
    		<c:forEach var= "list" items="${Category}">
				<option value="${list.code}">${list.name}</option>
			</c:forEach>
  		</select>
		</div>
		<div class="tag_form" placeholder="태그 입력"><input type="text" value="" maxlength="25" ></div>
		<button type="button" class="basic_button" onclick="upload('P')">발행</button>
		<button type="button" class="basic_button" onclick="upload('S')">임시저장</button>
	</div>
	${attachment}
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
</html>