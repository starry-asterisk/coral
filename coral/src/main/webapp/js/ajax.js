/**
 * 
 */

$(document).ready(function(){
		$(".icon_search_btn").click(function(){
			//<ajax> 사용하기
			$.ajax({
				// 전송방식을 지정한다(GET, POST)
				type : "POST",
				// 호출 URL을 설정한다.
				// GET 방식일 경우 뒤에 파라미터를 붙여서 사용해도 된다.
				url : "/ajax/search",
				data : {json:"sample"},  // 전송할 내용(폼태그)
				error : function(){
					alert("통신상태가 완활하지 않습니다");
				},
				success : function(obj){
					var data = JSON.parse(obj);
					alert(data.result);
				}
			});
		});
		
		$(".main_sub_sideBtn").click(function(){
			var code = editor.getValue();
			//<ajax> 사용하기
			$.ajax({
				// 전송방식을 지정한다(GET, POST)
				type : "POST",
				// 호출 URL을 설정한다.
				// GET 방식일 경우 뒤에 파라미터를 붙여서 사용해도 된다.
				url : "/ajax/runCode",
				data : {json:code},  // 전송할 내용(폼태그)
				error : function(){
					alert("통신상태가 완활하지 않습니다");
				},
				success : function(obj){
					var data = JSON.parse(obj);
					alert("통신데이터 값 : " + data.success);
					alert(data.result);
				}
			});
		});
		$("#login").click(function(){
			var ajax = new Object();
			ajax.id = $("#id").val();
			ajax.pw = $("#password").val();
			jsonSEND = JSON.stringify(ajax);
			//<ajax> 사용하기
			$.ajax({
				// 전송방식을 지정한다(GET, POST)
				type : "POST",
				// 호출 URL을 설정한다.
				// GET 방식일 경우 뒤에 파라미터를 붙여서 사용해도 된다.
				url : "/ajax/login",
				data : {json:jsonSEND},  // 전송할 내용(폼태그)
				error : function(){
					alert("통신상태가 완활하지 않습니다");
				},
				success : function(obj){
					var data = JSON.parse(obj);
					if(data.success){
						$("#id").val("");
						$("#password").val("");
						$(".login").css("display","none");
						$(".loginAfter").css("display","block");
					}else{
						alert("로그인 실패!");
					}
				}
			});
		});
	});