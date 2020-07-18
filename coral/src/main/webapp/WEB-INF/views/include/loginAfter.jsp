<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<table class="inform">
	<tr>
		<td rowspan="2"><c:choose>
				<c:when test="${empty prof_image}">
					<button type="button" class="prof_img">
						${fn:substring(userInfo.id, 0, 1)}</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="prof_img">
						<img alt="프로필이미지" src="${prof_image}" height="100%">
					</button>
				</c:otherwise>
			</c:choose></td>
		<td>
			${userInfo.name}　${userInfo.grade}
			<c:choose>
			<c:when test="${userInfo.grade=='관리자'}">
				<i class="fas fa-user-tie"></i>
			</c:when>
			<c:when test="${userInfo.grade=='교사'}">
				<img alt="" src="/resources/icon/professor.svg" width="19em">
			</c:when>
			<c:otherwise>
				<img alt="" src="/resources/icon/student.svg" width="19em">
			</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td>
			<button onclick="location.href='/logout'">로그아웃</button>
			<button onclick="location.href='/mypage'">내정보</button>
		</td>
	</tr>
</table>
<table style="width:100%" class="div">
	<tr>
		<td><div onclick="location.href='/board'">게시글</div></td>
		<td><div onclick="location.href='/lecture'">강좌글</div></td>
	</tr>
	<tr>
		<td colspan="2">
			<div style='border-left: 5px solid ${empty schedule.color?"green":schedule.color}'>
				<span class="s1"><fmt:formatDate pattern = "yyyy-MM-dd" value = "${schedule.start}" /> ~ <fmt:formatDate pattern = "yyyy-MM-dd" value = "${schedule.end}" /></span><button type='button' title='더보기' onclick="location.href='/mypage?app=schedule'"><i class='fas fa-ellipsis-h'></i></button><br>
				<span class="s2">${empty schedule.name?"일정이 없습니다":schedule.name}</span><br>
				<span class="s3">${schedule.contents}</span><br>
			</div>
		</td>
	</tr>
	<tr>
		<td colspan="2" style="padding-top:0;">
			<div style="border-left: 5px solid #ddd; height: 140px;" contenteditable="true" placeholder="수식을 작성하고 ENTER로 실행하세요"></div>
		</td>
	</tr>
</table>
<style>
.div tr:nth-child(2) td, .div tr:last-child td{
	padding:10px;
}
.div tr:nth-child(2) td div, .div tr:last-child td div{
	color:black;
	background:white;
	text-align:left;
	padding:10px;
	word-break:break-all;
	overflow-x:hidden;
	overflow-y:auto;
}
.div tr:nth-child(2) td div>button{
	float:right;
	font-size:1em;
}
.div tr:nth-child(2) td div>button *{
	color:#aaa;
}
.div tr:nth-child(2) td div span.s1{
	color: #bbb;
    font-size: .9em;
}
.div tr:nth-child(2) td div span.s2{
	color:grey;
	font-size: 1.3em;
}
.div tr:nth-child(2) td div span.s3{
	color:#aaa;
	font-size: 1em;
	margin-bottom:5px;
}
.div tr:first-child td>div{
	cursor:pointer;
	margin:0 5% 0 5%;
	line-height:35px;
}
.div tr:first-child td>div:hover{
	background:rgba(255,255,255,0.2)
}
.login * {
	color: white;
}
.login button.prof_img{
	overflow: hidden;
    border-radius: 50%;
    border: 1px solid #333;
    width: 110px;
    height: 110px;
    margin: 20px;
    box-shadow: 0 0 15px 15px rgba(0,0,0,0.15);
    color: white;
    font-size: 5em;
    font-weight: 900;
}
.inform{
	border-bottom:1px solid white;
	margin-bottom:10px;
	width:100%;
	height:150px;
}
.inform tr:first-child td:first-child{
	width:150px;
}
.inform tr:first-child td:last-child{
	text-align:left;
	padding-top:10%;
}
.inform tr:last-child td{
	text-align:left;
	padding-bottom:10%;
}
.inform tr:last-child button{
	margin: 2% 0 2% 0;
	width: 100px;
	height: 24px;
	font-size:11px;
	color:#ddd;
	text-align:center;
	border: 1px solid #ddd;
	border-radius: 3px;
	box-shadow: 0 0 10px 1px rgba(0,0,0,0.03);
}
.inform tr:last-child button:hover, .inform tr:last-child button:active{
	border-color: skyblue;
}
</style>
<script>
$(".div tr:last-child td div").on("keydown",function(key){
	if(key.keyCode==13){
		key.preventDefault();
		try {
			if(/^[-+]?[0-9(){}]+([-+*/%\^\=()]+[-+]?[0-9(){}]+)*$/gi.test($(this).html())){
				alert("계산결과 : "+eval($(this).html()));
			}else{
				alert("수식을 입력하세요");
			}
		} catch (e) {
			alert(e.message);
		}
		
	}
});
$(".div tr:last-child td div").on("paste",function(e){
	e.preventDefault();
	catchPaste(e, this, function(clipData) {
		$(".div tr:last-child td div").html($(".div tr:last-child td div").html()+clipData);
	  });
});
</script>