replyList(bno,get);

if(tags.length>1){
	tags = tags.split("#");
	tags.forEach(function(tag){
		if(tag.length!=0){
			$("#tag").before("<a href='/board?keyword="+tag+"'style='margin-right:5px;color:#337ab7'>#"+tag+"</a>");
		}
	});
}else if($("#tag").length > 0){
	$("#tag").before("#");
}

$("#like").click(function(){
	thumb(bno,1);
});
$("#unlike").click(function(){
	thumb(bno,-1);
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
$("#newReply").on("paste",function(e){
	e.preventDefault();
	catchPaste(e, this, function(clipData) {
		$(".div tr:last-child td div").html($(".div tr:last-child td div").html()+clipData);
	  });
});
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

function attReply(obj){
	$("#reply").width("100%");
	if(obj==false){
		$("#reply").append(document.createElement("tr"));
		$("#reply tr").append(document.createElement("td"));
		$("#reply tr td ").append("댓글이 없습니다");
		$("#reply tr td ").css("padding-bottom","25px");
		$("#reply tr td ").css("padding-top","25px");
	}else{
		var tr = [$(document.createElement("tr")),$(document.createElement("tr"))];
		if(obj.upddate==null){
			obj.upddate = obj.regdate;
		}else{
			obj.upddate = new Date(obj.upddate);
		}
		tr[0].append("<td rowspan='2'><button onclick=\"location.href='/userpage?id="+obj.id+"'\">"+((obj.path!=null&&obj.path!=undefined&&obj.path!='')?("<img src='"+obj.path+"' alt='프로필'></button>"):obj.id.substring(0,1))+"</td>");
		tr[0].append("<td>"+obj.id+"<span>"+(obj.upddate.getFullYear()+"/"+(obj.upddate.getMonth()+1)+"/"+obj.upddate.getDate()+" "+obj.upddate.getHours()+":"+obj.upddate.getMinutes())+"</span><button type='button' title='댓글메뉴' class='reBtn'><i class='fas fa-ellipsis-h'></i></button></td>");
		tr[0].append("<td rowspan='2'></td>");
		tr[1].append("<td>"+obj.content+"</td>");
		tr[0].find(".reBtn").on('click',function(){
			btnSpace($(this), obj);
		});
		$("#reply").prepend(tr, obj);
	}
}
function btnSpace(btn, obj){
	td = btn.parent();
	td.next().css("height",td.prev().css("height"));
	div = $(document.createElement('div'));
	div.append("<button type='button' title='신고' class='reEditBtn' ><i class='fas fa-exclamation-triangle'></i></button>");
	div.append("<button type='button' title='수정' class='reEditBtn' ><i class='fas fa-marker'></i></button>");
	div.append("<button type='button' title='삭제' class='reEditBtn' ><i class='fas fa-trash-alt'></i></button>");
	div.append("<button type='button' title='메뉴닫기' onclick=\"$(this).parent().parent().css(\'width\',0)&&$(this).parent().remove();\" style='position: absolute;top: 30px;right: 17px;''><i class='fas fa-ellipsis-h'></i></button>");
	td.next().append(div);
	td.next().css("width","100%");
	div.find(".reEditBtn[title='신고']").on('click',function(){
		if(id!=undefined&&id!=""){
			var reanson = getReason('R');
			var result = report(bno+":"+obj.no,false,id,reanson);
			$("button.btn.btn-default").on("click",function(){
				reportSubmit(result.id , result.type , result.reason());
			});
		}else{
			alert("로그인 이후에 신고기능을 이용할 수 있습니다.");
		}
		div.parent().css('width',0);
		div.remove();
	});
	div.find(".reEditBtn[title='수정']").on('click',function(){
		contents = prompt();
		if(replyUpd(contents, obj.bno, obj.no)){
			alert("수정성공!");
			div.parent().parent().next().find("td").html(contents);
		}else{
			alert("수정실패!");
		}
		div.parent().css('width',0);
		div.remove();
	});
	div.find(".reEditBtn[title='삭제']").on('click',function(){
		if(replyUpd(false, obj.bno, obj.no)){
			alert("삭제성공!");
			div.parent().parent().next().remove();
			div.parent().parent().remove();
		}else{
			alert("삭제실패!");
			div.parent().css('width',0);
			div.remove();
		}
	});
}
