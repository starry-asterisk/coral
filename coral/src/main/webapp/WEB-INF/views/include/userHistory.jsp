<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="list_TYPE1">
	<tr>
		<td width="40%">활동일자</td>
		<td width="40%">접속IP</td>
		<td width="20%">구분</td>
	</tr>
	<c:forEach var= "row" items="${List}">
		<tr style="height: 2em;">
			<td><fmt:formatDate pattern = "yyyy년 MM월 dd일 HH:mm:ss" value = "${row.date }" /></td>
			<td>${row.ip }</td>
			<td>${row.login_status>0?"로그인":(row.login_status<0?"로그아웃":"부정시도") }</td>
		</tr>
	</c:forEach>
</table>