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
			type = type?'u':'b';
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
					if(obj){
						alert("신고가 접수되었습니다!");
					}else{
						alert("신고 접수가 실패 했습니다!");
					}
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
function profileUpload(tag,image_place){
	if(!check.tail.test(tag.files[0].name)){
		alert("이미지가 아닌 업로드 : "+tag.files[0].name);
		return;
	}
	var formData = new FormData(document.createElement("form"));
	formData.append("file", tag.files[0], tag.files[0].name);
	$.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/ajax/newProfImg",
        contentType: false,
        processData: false,
        data: formData,
        success: function (data) {
            console.log("SUCCESS : ", data);
            $(image_place).html("");
        	$(image_place).css("background-image","url("+data+")")
        	$(image_place).css("background-size","cover");
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
	tag.type = "text";
	tag.value = undefined;
	tag.type = "file";
	
}


function myApp(servlet,div){
	$.ajax({
        type: "GET",
        url: "/myApp/"+servlet,
        success: function (data) {
        	console.log("SUCCESS : True");
        	/*addHtmlPage(data,div);*/
        	$(div).html(data);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}

function thumb(no,value){
	$.ajax({
        type: "POST",
        url: "/ajax/like",
        data: {"bno":no,"thumb":value},
        async: false,
        success: function (data) {
        	console.log("SUCCESS : True");
        	alert(data?"이 게시물을 "+(value>0?"추천":"비추천")+" 했습니다":"추천할 수 없습니다!");
        },
        error: function (e) {
            console.log("ERROR : ", e);
            alert("통신오류!");
        }
    });
}


function addHtmlPage(data,div){
	$(div).html("");
    $(div).append(data.replaceAt(data.indexOf("<script"),data.lastIndexOf("</script>")+9,""));
    while(data.includes("<script")){
    	var line = data.substring(data.indexOf("<script"),data.indexOf("</script>")+9);
    	data = data.replaceAt(data.indexOf("<script"),data.indexOf("</script>")+9);
    	tag = document.createElement("script");
    	if(line.indexOf("src=\"")>-1){
    		tag.src = line.substring(line.indexOf("src=\"")+5, line.indexOf("\"",line.indexOf("src=\"")+5));
    	}else{
    		$(tag).html(line.substring(line.indexOf(">")+1,line.indexOf("</script",line.indexOf(">"))));
    	}
    	document.getElementsByTagName("head")[0].appendChild(tag);
    }
}
function replyList(boardNum, callback){
	$.ajax({
		// 전송방식을 지정한다(GET, POST)
		type : "POST",
		// 호출 URL을 설정한다.
		// GET 방식일 경우 뒤에 파라미터를 붙여서 사용해도 된다.
		url : "/ajax/replyList",
		data : {bno:boardNum},  // 전송할 내용(폼태그)
		async : false,
		error : function(){
			alert("통신상태가 완활하지 않습니다");
			reply='';
		},
		success : function(data){
			if(data.length==0){
				callback(false);
			}else{
				callback(data);
			}
		}
	});
}

function replySend(boardNum, content){
	if(content.length==0){
		alert("내용을 작성하세요");
		return;
	}
	$.ajax({
		// 전송방식을 지정한다(GET, POST)
		type : "POST",
		// 호출 URL을 설정한다.
		// GET 방식일 경우 뒤에 파라미터를 붙여서 사용해도 된다.
		url : "/ajax/replySend",
		data : {bno:boardNum,
				'content':content},  // 전송할 내용(폼태그)
		error : function(){
			alert("통신상태가 완활하지 않습니다");
		},
		success : function(data){
			console.log(data);
		}
	});
}

function replyUpd(value, bno, no){
	if(value==false){
		send = {status:'S',
				"bno":bno,
				"no":no}
	}else{
		send = {content:value,
				"bno":bno,
				"no":no}
	}
	$.ajax({
		// 전송방식을 지정한다(GET, POST)
		type : "POST",
		// 호출 URL을 설정한다.
		// GET 방식일 경우 뒤에 파라미터를 붙여서 사용해도 된다.
		url : "/ajax/replyUpd",
		data : send,  // 전송할 내용(폼태그)
		error : function(){
			alert("통신상태가 완활하지 않습니다");
			reply='';
		},
		success : function(data){
			reply=false;
		}
	});
}

function attReply(obj, place){
	if(obj==false){
		$("#reply").append(document.createElement("tr"));
		$("#reply tr").append(document.createElement("td"));
		$("#reply tr td ").append("댓글이 없습니다");
	}else{
		var tr = $(document.createElement("tr"));
		$("#reply").append(tr);
		tr.css("border-bottom","1px solid #eee")
		tr.append("<td>"+obj.id+"</td>");
		tr.append("<td>"+obj.content+"</td>");
		tr.append("<td>"+obj.regdate+"</td>");
		tr.append("<td>"+obj.upddate+"</td>");
	}
}