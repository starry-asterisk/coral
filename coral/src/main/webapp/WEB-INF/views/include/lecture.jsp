<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="list_TYPE1">
	<tr>
		<td width="15%">NO</td>
		<td width="47%">제목</td>
		<td width="10%">등록일</td>
		<td width="10%">최근 업데이트</td>
		<td width="8%">조회수</td>
		<td width="8%">추천수</td>
	</tr>
	<c:forEach var= "list" items="${LectureList}">
		<tr>
			<td>${list.no }</td>
			<td><a href="/lecture/course?no=${list.no }" title="${list.title }">${list.title }</a></td>
			<td><fmt:formatDate pattern = "MM-dd HH:mm" value = "${list.regdate}" /></td>
			<td><fmt:formatDate pattern = "MM-dd HH:mm" value = "${list.upddate}" /></td>
			<td>${list.views }</td>
			<td>${list.recommends }</td>
		</tr>
	</c:forEach>
	<tr height="3em">
		<td colspan="7" class="swipeBtnArea btnRow">
		
		</td>
	</tr>
</table>
<script>
mkPageBtn(".swipeBtnArea","/lecture",${Currentpage},${Endpage},${amount},'&cl_no=${cl_no}');
</script>