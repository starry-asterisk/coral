/**
 * 
 */

$(document).ready(function(){
		$(".icon_search_btn.search").click(function(){
			var keyword = $("input[name=key_word]").val();
			boardList("board",20,1,$(".feed_sub_main div:nth-child(1)"),keyword);
			boardList("lecture",20,1,$(".feed_sub_main div:nth-child(2)"),keyword);
			boardList("user",20,1,$(".feed_sub_main div:nth-child(3)"),keyword);
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
						if($("header div.result").length==0){
							$("header").addClass("viewMode");
							var timer = setTimeout(function() {    
								$("header div.result").remove();
								$("header").append("<div class='result'><pre>실행결과\n"+result+"</pre><button type='button'><i class='fas fa-times'></i></button></div>");
								$("header div.result>button").on("click",function(){
									$("header div.result").remove();
									$("header").removeClass("viewMode");
								});
								clearTimeout(timer);     
							}, 1500);
						}else{
							$("header div.result").remove();
							$("header").append("<div class='result'><pre>실행결과\n"+result+"</pre><button type='button'><i class='fas fa-times'></i></button></div>");
							$("header div.result>button").on("click",function(){
								$("header div.result").remove();
								$("header").removeClass("viewMode");
							});
						}
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
	if(!check.tail.test(tag.files[0].name.toLowerCase())){
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
        	$(div).html(data);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
}



function boardList(servlet,amount,page,div,keyword){
	$.ajax({
        type: "GET",
        url: "/"+servlet,
        data: 
        {	
        	isAjax:true,
        	"amount":amount,
        	"page":page,
        	"keyword":keyword,
        },
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

function replySend(boardNum, place){
	if(typeof(place)!='String'){
		var content = $(place).html().replace(/<\/?[a-zA-Z]*\/?>/g,'');
	}
	if(content.length==0){
		alert("내용을 작성하세요");
		return;
	}else if(content.length>200){
		alert("덧글은 200자를 초과 할 수 없습니다");
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
			if(data){
				alert("댓글 등록에 성공했습니다!");
				$("#reply").html("");
				replyList(bno,get);
				$(place).html("");
			}else{
				alert("로그인후 덧글을 달아주시거나 다시 시도해 주세요");
			}
		}
	});
}

function replyUpd(value, bno, no){
	var result;
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
		async : false,
		error : function(){
			alert("통신상태가 완활하지 않습니다");
			result=false;
		},
		success : function(data){
			result=data;
		}
	});
	return result;
}
