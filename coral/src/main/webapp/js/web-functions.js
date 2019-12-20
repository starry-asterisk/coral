/**
 * 
 */
function mkForm(action, method){
		var form = $(document.createElement("form"));
		$('body').append(form);
		form.attr("method",method);
		form.attr("action",action);
		return {
			submit:function(){
				form.submit();
			},
			addValue:function(name,value){
				var input = $(document.createElement("input"));
				input.attr("type","hidden");
				input.attr("name",name);
				input.val(value);
				form.append(input);
			}
			
		}
	}

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
	
	//달력 스케쥴 목록을 받아옴
	var ScheduleList = place.attr("schedule");
	
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
	$(".C_side").find("tr:nth-child(2) td").html("");
	
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
	td = $(".calendar").children("tr:not(:nth-child(1)):not(:nth-child(2))").children("td");
	if(header){
		var addSchedule = function(td_date){
			
			/*클릭시의 이벤트를 지정가능합니다*/
			var temp;
			var contents = $(".C_side");
			
			if(contents.html()==undefined){
				contents = mkSide(body, width, height);
				contents.append(document.createElement("table"));
				contents.css("font-size",width*0.02);
				contents.children().append(document.createElement("tr"));
				contents.children().append(document.createElement("tr"));
				contents.children().append(document.createElement("tr"));
				contents.find("tr").append(document.createElement("td"));
				contents.find("tr:first-child td").append("<button onClick='$(\".C_side\").css(\"display\",\"none\")'> × </button>");
				
				
				
				temp = $(document.createElement("label"));
				temp.attr("for","start_day");
				temp.html("시작일");
				contents.find("tr:last-child td").append(temp);
				
				temp = $(document.createElement("input"));
				temp.attr("id","start_day")
				contents.find("tr:last-child td").append(temp);
				
				contents.find("tr:last-child td").append("<br/>");
				
				temp = $(document.createElement("label"));
				temp.attr("for","end_day");
				temp.html("종료일");
				contents.find("tr:last-child td").append(temp);
				
				temp= $(document.createElement("input"));
				temp.attr("id","end_day");
				contents.find("tr:last-child td").append(temp);
				
			}else if(contents.css("display")=="none"){
				contents.css("display","");
			}
			if($("#start_day").val()==""){
				$("#start_day").val(td_date.getFullYear()+"-"+(td_date.getMonth()+1)+"-"+td_date.getDate());
			}else if($("#end_day").val()==""){
				$("#end_day").val(td_date.getFullYear()+"-"+(td_date.getMonth()+1)+"-"+td_date.getDate());

				var start_day = $("#start_day").val().split("-");
				start_day = new Date(start_day[0],start_day[1]-1,start_day[2]);
				var end_day = new Date(td_date.getFullYear(),td_date.getMonth(),td_date.getDate());
				
				
				if(end_day<start_day){
					temp = start_day;
					start_day = end_day;
					end_day = temp;	
				}
				var color = parseInt(Math.random() * 0xffffff).toString(16);
				var color = "#"+color;
				var move = new Date(origin.Year,origin.Month,1);
				var sum = (end_day-start_day)/1000/60/60/24;
				while(move<=end_day&&move.getMonth()==origin.Month){
					if(move>=start_day){
						var job = $(document.createElement("div"));
						job.addClass("job");
						job.css("background",color);
						console.log(color);
						day = $("table.calendar #"+move.getDate()+"");
						if(day.html().split("+")[1]!=undefined){
							data=day.html().split("+");
							day.html(data[0]);
							job.css("display","none");
							day.append(job);
							day.append("+"+(parseInt(data[1])+1));
							
						}else if(day.children("div").length==4){
							day.children("div:nth-child(3)").css("display","none");
							day.children("div:nth-child(4)").css("display","none");
							job.css("display","none");
							day.append(job);
							day.append("+3");
						}else{
							day.append(job);
						}
					}
					move.setDate(move.getDate()+1);
				}
				$(".C_side").find("tr:nth-child(2) td").append((start_day.getMonth()+1)+"/"+start_day.getDate()+"-"+(end_day.getMonth()+1)+"/"+end_day.getDate()+":"+(sum+1)+"day<br/>");
				body.attr("schedule",
						(body.attr("schedule")!=undefined?body.attr("schedule"):"")+
						start_day.getFullYear()+"/"+start_day.getMonth()+"/"+start_day.getDate()+
						"/"+end_day.getFullYear()+"/"+end_day.getMonth()+"/"+end_day.getDate()+";");
				$("#start_day").val("");
				$("#end_day").val("");
			}
		};
		td.height(td.height());
		$("table.calendar tr:not(.head) td:not(.none)").on("click", function(){
			var td_date = $(this).attr("id");
			addSchedule(new Date(origin.Year,origin.Month,td_date));
		});
		if(ScheduleList!=undefined){
			ScheduleList = ScheduleList.split(";");
			for(i=0;i<ScheduleList.length-1;i++){
				var st_ed = ScheduleList[i].split("/");
				addSchedule(new Date(st_ed[0],st_ed[1],st_ed[2]));
				addSchedule(new Date(st_ed[3],st_ed[4],st_ed[5]));
			}
		}
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
		addSchedule: function(date){
			addSchedule(date);
		},
		ScheduleList: ScheduleList,
		month: origin.Month,
		year: origin.Year
	}
}
function mkSide(body, width, height){
	var Poffset = body.parent().offset();
	var Coffset = body.offset();
	
	var contents = $(document.createElement("div"));
	
	contents.addClass("C_side");
	
	contents.width(width*0.4);
	contents.height(height);
	
	contents.css("left",Coffset.left+parseInt(width)-Poffset.left);
	contents.css("top",Coffset.top-Poffset.top-body.parent().css("border-width").replace("px",""));
	
	body.parent().prepend(contents);
	
	return contents;
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