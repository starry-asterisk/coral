<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="list_TYPE1">
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>글쓴이</td>
		<td>작성시간</td>
		<td>조회수</td>
	</tr>
	<c:forEach var= "list" items="${BoardList}">
		<tr>
			<td>${list.no }</td>
			<td><a href="/board/detail?bno=${list.no }">${list.title }</a></td>
			<td><span>${list.id }</span><button class="report"></button></td>
			<td>${list.regdate }</td>
			<td>${list.views }</td>
		</tr>
	</c:forEach>
</table>
<c:forEach var= "idx" begin="${Firstpage}" end="${Lastpage}" step="1">
<a href="/board?page=${idx}"> ${idx} </a>
</c:forEach>
<script>
$(".report").click(function(){
	var a = [['a0','광고성 글'],['a1','욕설, 비하발언'],['a2','사유3'],['a3','테스입니다'],['a4','오늘은국밥']];
	var result = report($(this).prev().html(),'이창현',a);
	$("button.btn.btn-default").on("click",function(){
		reportSubmit(result.id , result.reason());
	});
});
</script>