/**
 * 
 */


$(document).ready(function(){
	$(window).scroll(function(){
		if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
			$(".ScrollUpButton").css("display","");
		  } else {
			  $(".ScrollUpButton").css("display","none");
		  }
	});
	$(".ScrollUpButton").click(function(){
		  document.body.scrollTop = 0; // For Safari
		  document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
		});
	$(".id").click(function(){
		$(".id").children("input").focus();
	});
	$(".id").children("input").focus(function(){
		$(".id").css("outline","white solid 1px");
	});
	$(".id").children("input").focusout(function(){
		$(".id").css("outline","none");
	});
	$(".password").click(function(){
		$(".password").children("input").focus();
	});
	$(".password").children("input").focus(function(){
		$(".password").css("outline","white solid 1px");
	});
	$(".password").children("input").focusout(function(){
		$(".password").css("outline","none");
	});
	$("#memo").click(function(){
		$(".Board_List:nth-child(1)").css("display","")
		$(".Board_List:nth-child(2)").css("display","none")
		$(".Board_List:nth-child(3)").css("display","none")
	});
	$("#Boardlist").click(function(){
		$(".Board_List:nth-child(1)").css("display","none")
		$(".Board_List:nth-child(2)").css("display","")
		$(".Board_List:nth-child(3)").css("display","none")
	});
});


if($(".calendar")!=undefined){
	var calendar = BuildCalendar(".calendar");
}

function BuildCalendar(JqueryName, date){
	//달력이 생길 위치의 기준이 되는 객체
	var place = $(JqueryName).last();
	
	//달력 헤더 유무
	var header = place.data("header");
	
	//요일 배열
	var Day = ["SUN","MON","TUE","WEN","THU","FRI","SAT"];
	
	//달력의 원래 날짜 저장
	var origin = new Object();
	if(arguments.length==1){
		date = new Date();
	}
	origin.Year = date.getFullYear();
	origin.Month = date.getMonth();
	origin.Date = date.getDate();
	
	
	//달력의 가로, 세로
	var width = place.attr("width");
	var height = width*(0.82-(header?0:0.18));
	
	//달력 객체 생성
	var body = $(document.createElement("table"));
	body.addClass("calendar");
	
	body.attr("width",width);
	body.attr("data-header",header);
	
	body.height(height);
	body.width(width);
	body.css("font-size",width*0.02);
	
	place.before(body);
	
	//기존 객체 제거
	place.remove();
	
	//해당 월 마지막 날짜
	date.setMonth(date.getMonth()+1);
	date.setDate(0);
	var lastDay=date.getDate();
	
	//해당 월 달력 시작일
	date.setDate(1);
	date.setDate(1-(date.getDay()==0?7:date.getDay()));
	var firstDay=date.getDate();
	
	if(header){
		var tr = $(document.createElement("tr"));
		tr.addClass("head");
		body.append(tr);
		
		var td = $(document.createElement("td"));
		tr.append(td);
		td.html("&#60;");
		td.attr("onClick","calendar.move("+origin.Year+","+(origin.Month-1)+")");
		
		td = $(document.createElement("td"));
		tr.append(td);
		td.attr("colspan","5");
		td.html("<span style='font-weight:900;font-size:2.5em;color:#51565d;'>"+(origin.Month+1)+"</span><br/>"+origin.Year);
		
		td = $(document.createElement("td"));
		tr.append(td);
		td.html("&#62;");
		td.attr("onClick","calendar.move("+origin.Year+","+(origin.Month+1)+")");
		
		tr.children("td").css("height","2em");
		tr.children("td").css("border","0");
		tr.children("td").css("text-align","center");
		
		var C_btn = tr.children("td:not(:nth-child(2))");
		C_btn.css("font-size","2em");
		C_btn.css("padding-top","0.55em");
		C_btn.css("margin-top","10px");
		
		tr = $(document.createElement("tr"));
		body.append(tr);
		for(i=0;i<7;i++){
			td = $(document.createElement("th"));
			td.html(Day[i]);
			tr.append(td);
		}
		
		/*해더 중앙 버튼 이벤트*/
		$("table.calendar td[colspan]").on("click", function(e){
			var contents = document.createElement("div");
			contents = $(contents);
			contents.html("오늘은 "+(origin.Month+1)+"월 입니다.<br/>");
			contents.append(document.createElement("input"));
			contents.children("input").attr("type","text");
			contents.children("input").attr("placeholder","YYYY-MM");
			contents.append(document.createElement("br"));
			contents.append(document.createElement("button"));
			contents.children("button").html("해당 월로 이동");
			contents.children("button").on("click",function(){
				var pattern = /[^0-9]/gi;
				var value = contents.children("input").val().replace(pattern,"");
				if(value.length<4){
					calendar.move(origin.Year,value-1);
				}else if(value.length==4){
					calendar.move(value,origin.Month);
				}else{
					calendar.move(value.substring(0,4),value.substring(4)-1);
				}
				delPop();
			});
			mkPop(e, contents);
		});
	}
	for(i=0;i<42;i++){
		if(i%7==0){
			tr = $(document.createElement("tr"))
			body.append(tr);
		}
		td = $(document.createElement("td"));
		td.html(date.getDate());
		tr.append(td);
		
		if(origin.Month != date.getMonth()){
			tr.children("td").last().addClass("none");
			tr.children("td").last().css("background-size",width*0.036+"px");
		}else{
			tr.children("td").last().attr("id",date.getDate());
		}
		
		date.setDate(date.getDate()+1);
	}
	$("table.calendar tr:not(.head) td:not(.none)").on("click", function(){
		/*클릭시의 이벤트를 지정가능합니다*/
		var Poffset = body.parent().offset();
		var Coffset = body.offset();
		var td_date = $(this).attr("id");
		var temp;
		
		if($(".C_side").html()==undefined){
			var contents = $(document.createElement("div"));
			
			contents.addClass("C_side");
			
			contents.width(width*0.4+25);
			contents.height(width*(0.82-(header?0:0.18))+"px");
			
			contents.css("left",Coffset.left+parseInt(width)-Poffset.left-25);
			contents.css("top",Coffset.top-Poffset.top);
			
			contents.append("<br/>");
			
			temp = $(document.createElement("label"));
			temp.attr("for","start_day");
			temp.html("시작일");
			contents.append(temp);
			
			temp = $(document.createElement("input"));
			temp.attr("id","start_day")
			temp.val(origin.Year+"-"+(origin.Month+1)+"-"+td_date);
			contents.append(temp);
			
			contents.append("<br/>");
			
			temp = $(document.createElement("label"));
			temp.attr("for","end_day");
			temp.html("종료일");
			contents.append(temp);
			
			temp= $(document.createElement("input"));
			temp.attr("id","end_day");
			contents.append(temp);
			
			
			body.parent().prepend(contents);
		}else{
			$("#end_day").val(origin.Year+"-"+(origin.Month+1)+"-"+td_date);
			
			var start_day = $("#start_day").val().split("-");
			start_day = new Date(start_day[0],start_day[1]-1,start_day[2]);
			var end_day = new Date(origin.Year,origin.Month,td_date);
			
			
			if(end_day<start_day){
				temp = start_day;
				start_day = end_day;
				end_day = temp;	
			}
			
			var sum = (end_day-start_day)/24/60/60/1000;
			if(new Date(end_day.getFullYear(),end_day.getMonth(),1)>start_day){
				start_day = 1;
			}else{
				start_day = start_day.getDate();
			}
			$(".C_side").append("<br/><button onClick='$(\".C_side\").remove()'>"+sum+"일 소요 예상 </button><br/>");
			var color = "#"+parseInt(Math.random() * 0xffffff).toString(16);
			for(i = start_day;i<=end_day.getDate();i++){
				var job = "<div class='job' style='background:"+color+"'></div>";
				$("table.calendar #"+i+"").append(job);
			}
		}
	});
	td = $(".calendar").children("tr:not(:nth-child(1)):not(:nth-child(2))").children("td");
	if(header){
		td.height(td.height());
	}else{
		$(".calendar").find("td").height(td.height());
	}
	
	return {
		move: function(Year,Month){
			BuildCalendar(JqueryName,new Date(Year,Month));
		},
		
		del: function(){
			$(JqueryName).remove();
			$(".C_side").remove();
		},
		
		month: origin.Month,
		year: origin.Year
	}
}

function mkPop(e, contents){
	/*클릭시의 이벤트를 지정가능합니다*/
	contents.addClass("popup");
	$('body').append(contents);
	contents.css("position","absolute");
	contents.css("top",e.pageY+30);
	contents.css("left",e.pageX-61);
	$(".popup").after(document.createElement("div"));
	$(".popup+div").addClass("popup_back");
	$(".popup_back").click(function(){
		delPop();
	});
}
function delPop(){
	$(".popup").remove();
	$(".popup_back").remove();
}