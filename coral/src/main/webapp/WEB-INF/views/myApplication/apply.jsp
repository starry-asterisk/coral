<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="map">
  <tr>
    <td colspan="2">
    	<table class="list_TYPE1">
	<tr>
		<td width="10%">신청자</td>
		<td width="15%">신청강좌</td>
		<td width="10%">사유코드</td>
		<td width="10%">신청일</td>
		<td width="8%">상태</td>
		<td width="8%">완료일</td>
	</tr>
	<c:forEach var= "list" items="${reportList}">
		<tr>
			<td onclick="location.href='/userpage?id=${list.id }'" title="해당 유저정보로 이동" style="cursor:pointer">${list.id }</td>
			<td onclick="location.href='/lecture?cl_no=${list.object }'" title="해당 강좌로 이동" style="cursor:pointer">${list.object }</td>
			<td>${list.rscode }</td>
			<td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm" value = "${list.regdate}" /></td>
			<td>${list.status }</td>
			<td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm" value = "${list.completedate }" />
			<c:if test="${list.completedate==null}"><button type="button" onclick='applyComplete($(this),confirm("승인한다/안한다"))'>처리</button></c:if>
			</td>
		</tr>
	</c:forEach>
	<tr height="3em">
		<td colspan="6" class="swipeBtnArea btnRow report_row">
		
		</td>
	</tr>
</table>
<style>
table.list_TYPE1 tr+tr {
    border-top: 1px solid #eee;
    height:41px;
}
table.list_TYPE1 {
	width:100%;
}
table.list_TYPE1 td:nth-child(6) button{
	height: 24px;
    width: 60px;
    border: 1px solid #ddd;
}
table.list_TYPE1 td:nth-child(6) button:active{
	background: #eee;
}
</style>
<script>
mkPageBtn(".report_row","/mypage?app=apply",${R_Currentpage},${R_Endpage},${R_amount});
function applyComplete(button, isPunish){
	var form = mkForm("/myApp/apply","POST");
	form.addValue("id",button.parent().parent().find("td").eq(0).html());
	form.addValue("object",button.parent().parent().find("td").eq(1).html());
	form.addValue("rscode",button.parent().parent().find("td").eq(2).html());
	form.addValue("status",isPunish?"P":"N");
	form.submit();
}
</script>
    </td>
  </tr>
</table>