<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="list_TYPE2">
	<c:forEach var= "list" items="${BoardList}" varStatus="status">
		<c:if test="${status.index%2==0}">
		<tr>
		</c:if>
			<td>
			<img alt="프로필 이미지" width="120px" height="120px" src="/resources/profile-example.png">
			${list.bno }
			<h2><a href="/board/detail?bno=${list.bno }">${list.title }</a></h2>
			${list.name }
			${list.time }
			${list.count }
			</td>
		<c:if test="${status.index%2==1}">
		</tr>
		</c:if>
	</c:forEach>
</table>
<c:forEach var= "idx" begin="${Firstpage}" end="${Lastpage}" step="1">
<a href="/teacher?page=${idx}"> ${idx} </a>
</c:forEach>