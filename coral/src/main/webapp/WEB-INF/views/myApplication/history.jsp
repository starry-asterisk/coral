<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="map">
  <tr>
    <td colspan="2">
    	<jsp:include page="../include/board.jsp"></jsp:include>
    </td>
  </tr>
  <tr>
  	<td colspan="2" style="vertical-align:top;">
    	<jsp:include page="../include/class.jsp"></jsp:include>
    </td>
  </tr>
  <tr>
  	<td colspan="2">
    	<jsp:include page="../include/userHistory.jsp"></jsp:include>
    </td>
  </tr>
</table>
<style>
.map>tbody>tr>td{
	padding:0;
}
.map>tbody>tr>td>table{
	width:100%;
}
</style>