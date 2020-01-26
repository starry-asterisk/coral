<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="schedule" style="text-align:left">
	<a class="calendar" width="400" data-header=true schedule="${ pre_schedule}"></a>
	<div class="C_invert">
		<table>
			<tr>
				<td><button onclick="$('.C_side').css('display', 'none')"> × </button></td>
			</tr>
			<tr>
			<td></td>
			</tr>
			<tr>
				<td><input id="start_day" placeholder="시작일">/
					<input id="end_day" placeholder="종료일"></td>
			</tr>
		</table>
	</div>
</div>

<style>
.schedule{
	padding:10px;
	max-width:1200px;
	position:relative;
	left:50%;
	transform:translate(-50%);
}
.calendar tr:nth-child(n+2) td{
	height:44px;
}
.C_invert{
	height: 328px; 
	font-size: 16px;
}
@media (min-width: 1070px) {
	.C_invert{
	padding: 0 0 0 10px;
	width: calc(100% - 400px);
	float: right;
	}
}
@media (max-width: 1069px) {
	.C_invert{
	padding-top: 10px;
	width: 400px;
	}
}
.C_invert table{
	width:100%;
	height:100%;
	position:relative;
}
.C_invert tr{
	border-top:1px solid #ddd;
}
.C_invert tr:nth-child(2){
	background: white;
}
.C_invert tr:nth-child(2) td{
	background:white;
	width: 100%;
    height: 239px;
    padding: 0;
    vertical-align: top;
    position: absolute;
    overflow-y: auto;
}
.C_invert tr:nth-child(2) td div{
	height: 44px;
	vertical-align: middle;
    line-height: 44px;
    text-align:left;
}
.C_invert tr:nth-child(2) td div>span{
	margin: 0 15px 0 15px;
}
.C_invert tr:nth-child(2) td div+div{
	border-top:1px solid #ddd;
}
.C_invert tr:nth-child(2) td div>button{
	width:36px;
	color:#ddd;
}
.C_invert tr:nth-child(2) td div>button:hover{
	color:red;
}
.C_invert tr:first-child{
	height: 44px;
}
.C_invert tr:last-child{
	border-bottom:1px solid #ddd;
	height: 44px;
}
</style>
<script>
var calendar = BuildCalendar(".calendar");
</script>