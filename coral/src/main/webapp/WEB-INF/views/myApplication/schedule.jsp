<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<a class="calendar" width="400" data-header=true schedule="${ pre_schedule}"></a>
<div class="C_side" style="width: 320px; height: 656px; left: 1476.5px; top: 60px; font-size: 16px;">
	<table>
		<tr>
			<td><button onclick="$('.C_side').css('display', 'none')"> × </button></td>
		</tr>
		<tr>
		<td></td>
		</tr>
		<tr>
			<td><label for="start_day">시작일</label><input id="start_day"><br>
				<label for="end_day">종료일</label><input id="end_day"></td>
		</tr>
	</table>
</div>
<script>
var calendar = BuildCalendar(".calendar");
</script>