<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<button class="subbtn" type="button" style="margin-top: 20px" onclick="location.href='/board/write'">글쓰기</button>
<button class="subbtn" type="button" style="border-top-width: 0;" onclick="location.href='/lecture'">강좌글</button>
<button class="subbtn" type="button" style="border-top-width: 0;" onclick="location.href='/'">메인</button>
<div class="custom-select">
	<select>
		<option value="">카테고리 선택:</option>
		<c:forEach var="list" items="${Category}">
			<option value="${list.code}"
				${board.tag.equals("false")?"":(board.category.equals(list.code)?"selected":"")}>${list.name}</option>
		</c:forEach>
	</select>
</div>
<script>
createSelect(document.getElementsByClassName("custom-select"));
</script>