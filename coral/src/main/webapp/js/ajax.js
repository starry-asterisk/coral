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
				success : function(data){
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
					data : {"code":code},  // 전송할 내용(폼태그)
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
		dataType : "json",
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

function reportSubmit(identifier, type, reason){
	if(identifier!=undefined&&identifier!=""){
		if(reason!=undefined&&reason!=""){
			var json = new Object();
			json.object = identifier;
			json.rscode = reason;
			json.code = type;
			$.ajax({
				// 전송방식을 지정한다(GET, POST)
				type : "POST",
				// 호출 URL을 설정한다.
				// GET 방식일 경우 뒤에 파라미터를 붙여서 사용해도 된다.
				url : "/ajax/report",
				data : json,  // 전송할 내용(폼태그)
				dataType : "json",
				async: false,
				error : function(){
					alert("통신상태가 완활하지 않습니다");
				},
				success : function(obj){
					if(obj=="true"){
						alert("신고가 접수되었습니다!");
					}
				}
			});
		}else{
			alert("사유를 선택해 주세요!");
		}
	}
}
var fileData="null";
function FileUpload(){
	var form = mkForm("/ajax/upload","POST");
	form.attr("enctype","multipart/form-data");
	files = $("input[name=files]");
	for(i=0;i<files[0].files.length;i++){
		var fileName = files[0].files[i].name;
		if(check.exe.test(fileName)){
			alert("exe파일은 전송 할수 없습니다\n파일이름 : "+fileName);
			return false;
		}
	}
	place = $(document.createElement("place"));
	files.before(place);
	form.append(files);
	form = form.form;
	form.ajaxForm({
		url : "/ajax/upload",
        enctype : "multipart/form-data",
        dataType : "json",
		error : function(){
			alert("통신상태가 완활하지 않습니다");
		},
		success : function(data){
			if(fileData!="null"){
				data.forEach(function(item){
					fileData.push(item);
				})
			}else{
				fileData = data;
			}
			data.forEach(function(item){
				if(item.path!=undefined&&item.path!=""){
					ckaddIMG(item.path);
				}
			});
		}
	});
	form.submit();
	place.replaceWith(files);
	form.remove();
}

function login(){
	var form = mkForm("/login","POST");
	form.addValue("id",$("#id").val());
	form.addValue("pw",$("#password").val());
	form.addValue("remember_me",$("#remember-me").is(":checked"));
	form.submit();
}


