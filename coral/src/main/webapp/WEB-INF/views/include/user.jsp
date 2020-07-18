<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="list_TYPE1">
	<tr>
		<td>아이디</td>
		<td>닉네임</td>
		<td>등급</td>
		<td>조회수</td>
		<td>추천수</td>
	</tr>
	<c:forEach var= "list" items="${UserList}">
		<tr style="height: 2em;">
			<td onclick="location.href='/userpage?id=${list.id }'">${list.id }</td>
			<td>${list.name }</td>
			<td>${list.grade }</td>
			<td>${list.views }</td>
			<td>${list.recommends }</td>
		</tr>
	</c:forEach>
	<tr height="3em">
		<td colspan="5" class="swipeBtnArea btnRow user_row">
		
		</td>
	</tr>
</table>
<script>
var U_Currentpage = ${U_Currentpage};
var U_Endpage = ${U_Endpage};
var U_amount = ${U_amount};
mkPageBtn(".user_row", "/board", U_Currentpage, U_Endpage, U_amount, '&keyword=${keyword}');
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