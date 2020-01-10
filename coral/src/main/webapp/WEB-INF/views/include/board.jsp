<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="list_TYPE1">
	<tr>
		<td width="15%">NO</td>
		<td width="47%">제목</td>
		<td width="10%">작성자</td>
		<td width="2%"></td>
		<td width="10%">날짜</td>
		<td width="8%">조회수</td>
		<td width="8%">추천수</td>
	</tr>
	<c:forEach var= "list" items="${BoardList}">
		<tr>
			<td>${list.no }</td>
			<td><a href="/board/detail?bno=${list.no }" title="${list.title }">${list.title }</a></td>
			<td><span>${list.id }</span></td>
			<td><button class="report"></button></td>
			<td><fmt:formatDate pattern = "MM-dd HH:mm" value = "${list.regdate }" /></td>
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
mkPageBtn(".swipeBtnArea","/board",${Currentpage},${Endpage},${amount});
var reporter = "${id}";
$(".report").click(function(){
	if(reporter!=undefined&&reporter!=""){
		var reanson = getReason('R');
		var result = report($(this).parent().prev().children("span").html(),true,reporter,reanson);
		$("button.btn.btn-default").on("click",function(){
			reportSubmit(result.id , result.type , result.reason());
		});
	}else{
		alert("로그인 이후에 신고기능을 이용할 수 있습니다.");
	}
});
</script>