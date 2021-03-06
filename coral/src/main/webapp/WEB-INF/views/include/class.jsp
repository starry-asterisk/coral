<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="list_TYPE1">
	<tr>
		<td width="15%">NO</td>
		<td width="37%">제목</td>
		<td width="10%">작성자</td>
		<td width="2%"></td>
		<td width="10%">등록일</td>
		<td width="10%">최근 수정일</td>
		<td width="8%">조회수</td>
		<td width="8%">추천수</td>
	</tr>
	<c:forEach var= "list" items="${ClassList}">
		<tr>
			<td>${list.cl_no }</td>
			<td><a href="/lecture?cl_no=${list.cl_no }" title="${list.cl_title }">${list.cl_title }</a></td>
			<td><button style="background-size: cover;width:70px;height:70px;background-image: url('${empty list.cl_path?'/upload/profile.png':list.cl_path}')" onClick="location.href='/userpage?id=${list.id }'"></button><span onClick="location.href='/userpage?id=${list.id }'">${list.id }</span></td>
			<td><button class="report"></button></td>
			<td><fmt:formatDate pattern = "MM-dd HH:mm" value = "${list.regdate}" /></td>
			<td><fmt:formatDate pattern = "MM-dd HH:mm" value = "${list.upddate}" /></td>
			<td>${list.views }</td>
			<td>${list.recommends }</td>
		</tr>
	</c:forEach>
	<tr height="3em">
		<td colspan="8" class="swipeBtnArea btnRow class_row">
		
		</td>
	</tr>
</table>
<script>
mkPageBtn(".class_row","/lecture",${Currentpage},${Endpage},${amount},"&keyword=${keyword}");
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
<style>
table.list_TYPE1 tr+tr {
    border-top: 1px solid #eee;
}
</style>