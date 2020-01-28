<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="map">
  <tr>
    <td colspan="2">
    	<table class="list_TYPE1">
	<tr>
		<td width="15%">신청강좌</td>
		<td width="10%">사유코드</td>
		<td width="10%">신청일</td>
		<td width="8%">상태</td>
		<td width="8%">완료일</td>
	</tr>
	<c:forEach var= "list" items="${reportList}">
		<tr>
			<td onclick="location.href='/lecture?cl_no=${list.object }'">${list.object }</td>
			<td>${list.rscode }</td>
			<td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm" value = "${list.regdate}" /></td>
			<td>${list.status }</td>
			<td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm" value = "${list.completedate }" />${list.status=='P'?"":"-"}</td>
		</tr>
	</c:forEach>
	<tr height="3em">
		<td colspan="7" class="swipeBtnArea btnRow report_row">
		
		</td>
	</tr>
</table>
<style>
table.list_TYPE1 tr+tr {
    border-top: 1px solid #eee;
}
table.list_TYPE1 {
	width:100%;
}
</style>
<script>
mkPageBtn(".report_row","/lecture",${R_Currentpage},${R_Endpage},${R_amount});
</script>
    </td>
  </tr>
</table>