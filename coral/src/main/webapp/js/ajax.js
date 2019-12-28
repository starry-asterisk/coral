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
		
		$("#codeRun").click(function(){
			if($(".Board_List:nth-child(3)").css("display")!="none"){
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
					success : function(result){
						alert(result);
					}
				});
			}else{
				$(".Board_List:nth-child(3)").css("display","");
			}
		});
		$("#login").click(function(){
			login();
		});
		$("#password").keypress(function(){
			 if (window.event.keyCode == 13) {
	             // 엔터키가 눌렸을 때 실행할 내용
	             login();
	        }
		});
	});
function idExit(id){
	var answer;
	$.ajax({
		// 전송방식을 지정한다(GET, POST)
		type : "POST",
		// 호출 URL을 설정한다.
		// GET 방식일 경우 뒤에 파라미터를 붙여서 사용해도 된다.
		url : "/ajax/idExit",
		data : {"id":id},  // 전송할 내용(폼태그)
		async: false,
		error : function(){
			alert("통신상태가 완활하지 않습니다");
		},
		success : function(obj){
			answer = obj;
		}
	});
	
	return answer;
}

function mailExit(mail){
	var answer;
	$.ajax({
		// 전송방식을 지정한다(GET, POST)
		type : "POST",
		// 호출 URL을 설정한다.
		// GET 방식일 경우 뒤에 파라미터를 붙여서 사용해도 된다.
		url : "/ajax/mailExit",
		data : {"mail":mail},  // 전송할 내용(폼태그)
		async: false,
		error : function(){
			alert("통신상태가 완활하지 않습니다");
		},
		success : function(obj){
			answer = obj;
		}
	});
	
	return answer;
}

function getReason(identifier){
	var answer;
	$.ajax({
		// 전송방식을 지정한다(GET, POST)
		type : "POST",
		// 호출 URL을 설정한다.
		// GET 방식일 경우 뒤에 파라미터를 붙여서 사용해도 된다.
		url : "/ajax/getReason",
		data : {"identifier":identifier},  // 전송할 내용(폼태그)
		async: false,
		error : function(){
			alert("통신상태가 완활하지 않습니다");
		},
		success : function(obj){
			answer = JSON.parse(obj);
			if(answer.success==true){
				answer = answer.result;
			}
		}
	});
	
	return answer;
}

function reportSubmit(identifier, type, reason){
	if(identifier!=undefined&&identifier!=""){
		if(reason!=undefined&&reason!=""){
			$.ajax({
				// 전송방식을 지정한다(GET, POST)
				type : "POST",
				// 호출 URL을 설정한다.
				// GET 방식일 경우 뒤에 파라미터를 붙여서 사용해도 된다.
				url : "/ajax/report",
				data : {"id":identifier,"rscode":reason,"type":type},  // 전송할 내용(폼태그)
				async: false,
				error : function(){
					alert("통신상태가 완활하지 않습니다");
				},
				success : function(obj){
					alert("신고가 접수되었습니다!");
				}
			});
		}else{
			alert("사유를 선택해 주세요!");
		}
	}
}




function login(){
	var form = mkForm("/login","POST");
	form.addValue("id",$("#id").val());
	form.addValue("pw",$("#password").val());
	form.addValue("remember_me",$("#remember-me").is(":checked"));
	form.submit();
}