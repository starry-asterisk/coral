replyList(bno,get);

if(tags.length>1){
	tags = tags.split("#");
	tags.forEach(function(tag){
		if(tag.length!=0){
			$("#tag").before("<a href='/ajax/search?keyword="+tag+"'style='margin-right:5px;color:#337ab7'>#"+tag+"</a>");
		}
	});
}else{
	$("#tag").before("#");
}

$("#like").click(function(){
	thumb(bno,1);
});
$("#unlike").click(function(){
	thumb(bno,-1);
});

$("#newReply").focus(function(){
	if($(this).html()==$(this).attr("placeholder")){
		$(this).html("");
	}
});
$("#newReply").focusout(function(){
	if($(this).html()==""){
		$(this).html($(this).attr("placeholder"));
	}
});
$("#newReply").on("input",function(){
	var length = $(this).html().replace( /[<][^>]*[>]/gi,"").length;
	$("#ReBytelimit span").html(length);
	if(length>200){
		$("#ReBytelimit span").css("color","red");
	}else{
		$("#ReBytelimit span").css("color","#333");
	}
	
});

function get(data){
	if(data==false){
		attReply(false);
	}else{
		data.forEach(function(obj, index){
			obj.regdate = new Date(obj.regdate);
			if(obj.update!=null&&obj.update!=undefined){
				obj.update = new Date(obj.update);
			}
			attReply(obj);
		});
	}
}
$("#report").click(function(){
	if(id!=undefined&&id!=""){
		var reanson = getReason('R');
		var result = report(bno,false,id,reanson);
		$("button.btn.btn-default").on("click",function(){
			reportSubmit(result.id , result.type , result.reason());
		});
	}else{
		alert("로그인 이후에 신고기능을 이용할 수 있습니다.");
	}
});

$("#edit").click(function(){
	if(id!=undefined&&id!=""){
		var reanson = getReason('R');
		var result = report(bno,false,id,reanson);
		$("button.btn.btn-default").on("click",function(){
			reportSubmit(result.id , result.type , result.reason());
		});
	}else{
		alert("로그인 이후에 신고기능을 이용할 수 있습니다.");
	}
});
