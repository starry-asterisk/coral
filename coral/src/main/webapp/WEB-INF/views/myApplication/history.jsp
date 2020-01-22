<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="map">
  <tr>
    <td colspan="2">
    	<span></span>
    	<div class="btn_area_btm"><button class="updProf"></button></div>
    </td>
  </tr>
  <tr>
  	<td colspan="2">
    	<span></span>
    	<div class="btn_area_btm"><button class="updProf"></button></div>
    </td>
  </tr>
  <tr>
  	<td colspan="2">
    	<span></span>
    	<div class="btn_area_btm"><button class="updProf"></button></div>
    </td>
  </tr>
</table>
<script>
boardList("board",10,1,$($(".map>tbody>tr>td").eq(0)),"${id}");
boardList("lecture",10,1,$($(".map>tbody>tr>td").eq(1)),"${id}");
myExtend("ajax/userHistory",$($(".map>tbody>tr>td").eq(2)));
</script>
<style>
.map>tbody>tr>td{
	padding:0;
}
.map>tbody>tr>td>table{
	width:100%;
}
</style>