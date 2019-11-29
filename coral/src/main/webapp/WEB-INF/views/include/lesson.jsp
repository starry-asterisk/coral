<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="list_TYPE2">
	<c:forEach var= "list" items="${BoardList}">
		<tr>
			<td>
			<img alt="섬네일 이미지" width="120px" height="120px" src="/resources/profile-example.png">
			${list.bno }
			<h2><a href="/board/detail?bno=${list.bno }">${list.title }</a></h2>
			${list.time }
			${list.count }
			</td>
		</tr>
	</c:forEach>
</table>
<c:forEach var= "idx" begin="${Firstpage}" end="${Lastpage}" step="1">
<a href="/lesson?page=${idx}"> ${idx} </a>
</c:forEach>