<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="list_TYPE1">
	<tr>
		<td width="15%">NO</td>
		<td></td>
		<td width="47%">제목</td>
		<td width="10%">등록일</td>
		<td width="10%">최근수정일</td>
		<td width="8%">조회수</td>
		<td width="8%">추천수</td>
	</tr>
	<c:forEach var= "list" items="${LectureList}">
		<tr>
			<td><button style='background-size: cover;width:${empty list.cl_path?"70":"100"}px;height:70px;background-image: url("${empty list.cl_path?"/upload/image.png":list.cl_path}")'></button>${list.no }</td>
			<td></td>
			<td><a href="/lecture/course?no=${list.no }" title="${list.title }">${list.title }</a></td>
			<td><fmt:formatDate pattern = "MM-dd HH:mm" value = "${list.regdate}" /></td>
			<td><fmt:formatDate pattern = "MM-dd HH:mm" value = "${list.upddate}" /></td>
			<td>${list.views }</td>
			<td>${list.recommends }</td>
		</tr>
	</c:forEach>
	<tr height="3em">
		<td colspan="7" class="swipeBtnArea btnRow lecture_row">
		
		</td>
	</tr>
</table>
<style>
table.list_TYPE1 tr+tr {
    border-top: 1px solid #eee;
}
</style>
<script>
var Currentpage = ${Currentpage};
var Endpage = ${Endpage};
var amount = ${amount};
mkPageBtn(".lecture_row","/lecture",Currentpage,Endpage,amount,'&cl_no=${cl_no}');
</script>