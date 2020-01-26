<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="list_TYPE1">
	<tr>
		<td>아이디</td>
	</tr>
	<c:forEach var= "list" items="${UserList}">
		<tr style="height: 2em;">
			<td>${list.id }</td>
		</tr>
	</c:forEach>
	<tr height="3em">
		<td class="swipeBtnArea btnRow user_row">
		
		</td>
	</tr>
</table>
<script>
mkPageBtn(".user_row","/board",${U_Currentpage},${U_Endpage},${U_amount},'&keyword=${keyword}');
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