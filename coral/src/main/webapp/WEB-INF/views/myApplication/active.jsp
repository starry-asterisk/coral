<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="map">
  <tr>
    <td>
    	<span></span>
    	<c:if test="${Category.size()>0}">
    	<c:forEach var= "list" items="${Category}">
			${list.code} / ${list.name} / ${list.permission} <br>
		</c:forEach>
  		</c:if>
  		카테고리별 글 댓글 좋아요 조회수
    </td>
    <td>
    	<span>강좌활동지표</span>
    	<div class="btn_area_btm"><button class="updProf"></button></div>
    </td>
  </tr>
  <tr>
  	<td>
  		<span>유저활동지표</span>
  		<div class="btn_area_btm"><button class="updProf"></button></div>
  	</td>
    <td>
    	<span></span>
    	<div class="btn_area_btm"><button class="updProf"></button></div>
    </td>
  </tr>
  <tr>
    <td>
  		<span></span>
  		<div class="btn_area_btm"><button disabled></button></div>
  	</td>
    <td>
    	<span></span>
  		<div class="btn_area_btm"><button disabled></button></div>
  	</td>
  </tr>
</table>


