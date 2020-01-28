<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="map">
  <tr>
    <td colspan="2">
    	<table class="list_TYPE1">
	<tr>
		<td width="15%">신고자</td>
		<td width="15%">신고대상</td>
		<td width="5%">타입</td>
		<td width="10%">사유코드</td>
		<td width="10%">신고일</td>
		<td width="8%">상태</td>
		<td width="8%">완료일</td>
	</tr>
	<c:forEach var= "list" items="${reportList}">
		<tr>
			<td>${list.id }</td>
			<td>${list.object }</td>
			<td>${list.code=='u'?'이용자':list.object.contains(":")?"댓글":"게시글" }</td>
			<td>${list.rscode }</td>
			<td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm" value = "${list.regdate}" /></td>
			<td>${list.status }</td>
			<td><fmt:formatDate pattern = "yyyy-MM-dd HH:mm" value = "${list.completedate }" />
			<c:if test="${list.completedate==null}"><button type="button" onclick='reportComplete($(this),confirm("처벌한다/안한다"))'>처리</button></c:if>
			</td>
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
table.list_TYPE1 td:nth-child(7) button{
	height: 24px;
    width: 60px;
    border: 1px solid #ddd;
}
table.list_TYPE1 td:nth-child(7) button:active{
	background: #eee;
}
</style>
<script>
mkPageBtn(".report_row","/mypage?app=report",${R_Currentpage},${R_Endpage},${R_amount});
function reportComplete(button, isPunish){
	var form = mkForm("/myApp/report","POST");
	form.addValue("object",button.parent().parent().find("td").eq(1).html());
	switch(button.parent().parent().find("td").eq(2).html()){
	case "댓글":case "게시글":
		form.addValue("code","b");
		break;
	case "이용자":
		form.addValue("code","u");
		break;
	default:
		return;
	}
	form.addValue("rscode",button.parent().parent().find("td").eq(3).html());
	form.addValue("status",isPunish?"P":"N");
	form.submit();
}
</script>
    </td>
  </tr>
</table>