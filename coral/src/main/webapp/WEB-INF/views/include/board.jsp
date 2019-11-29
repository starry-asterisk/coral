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
			<td>${list.bno }</td>
			<td><a href="/board/detail?bno=${list.bno }">${list.title }</a></td>
			<td>${list.name }</td>
			<td>${list.time }</td>
			<td>${list.count }</td>
		</tr>
	</c:forEach>
</table>
<c:forEach var= "idx" begin="${Firstpage}" end="${Lastpage}" step="1">
<a href="/board?page=${idx}"> ${idx} </a>
</c:forEach>