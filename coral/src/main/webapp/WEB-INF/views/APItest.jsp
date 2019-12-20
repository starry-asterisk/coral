<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c36e9847d846ee38d80b7aea7b36e3bf"></script>
<head>
<meta charset="UTF-8">
<title>Healthy Cogi - PageName</title>

</head>
<body>
테스트화면<hr>
<a id="checkJson" style="cursor:pointer;">전송</a><br><br>
<div id="map" style="width:500px;height:400px;"></div>
<div id="output"></div>
</body>
<script type="text/javascript">
var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
	level: 3 //지도의 레벨(확대, 축소 정도)
};

var map = new kakao.maps.Map(container, options);

	$(function(){
		$("#checkJson").click(function(){
			$.ajax({
				method : "GET",
				url : "https://dapi.kakao.com/v3/search/book?target=title",
				data : {query:"미움받을 용기"},
				headers : {Authorization: "KakaoAK d23098cc45488875ac04e03d47349bfd"}
			})
			.done(function(msg){
				var result;
				console.log(msg.documents[0].title);
				console.log(msg.documents[0].contents);
				console.log(msg.documents[0].thumbnail);
				
				result = "<b>책 제목 : "+msg.documents[0].title+"<b><br>";
				result += "<b>책 내용 : "+msg.documents[0].contents+"<b><br>";
				result += "<img src="+msg.documents[0].thumbnail+"><hr>";
				
				$("#output").html(result);
			});
		});
	});
</script>
<script>
${Code}
<%=request.getParameter("Code")%>
</script>
</html>