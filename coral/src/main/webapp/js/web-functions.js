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




var Day = ["SUN","MON","TUE","WEN","THU","FRI","SAT"];
var today = new Date();

if($(".calendar")!="undefined"){
	BuildCalendar(today);
}
function CalendarMove(Year,Month){
	today.setFullYear(Year)
	today.setMonth(Month);
	BuildCalendar(today);
}
function BuildCalendar(date){
	var place = $(".calendar").last();
	var width = place.attr("width").replace("px","");
	var header = place.data("header");
	var origin = new Object();
	origin.Month = date.getMonth();
	origin.Year = date.getFullYear();
	var calendar = document.createElement("table");
	calendar.setAttribute("class","calendar");
	place.before(calendar);
	place.remove();
	calendar = $("table.calendar");
	calendar.width(width);
	calendar.attr("width",width+"px");
	calendar.attr("data-header",header);
	calendar.height(width*(0.82-(header?0:0.18))+"px");
	calendar.css("font-size",width*0.02+"px");
	date.setMonth(date.getMonth()+1);
	date.setDate(0);
	var lastDay=date.getDate();
	date.setDate(1);
	date.setDate(1-(date.getDay()==0?7:date.getDay()));
	var firstDay=date.getDate();
	if(header){
		calendar.append(document.createElement("tr"));
		var tr = $("table.calendar tr");
		tr.append(document.createElement("td"));
		tr.children("td").last().html("&#60;");
		tr.children("td").last().attr("onClick","CalendarMove("+origin.Year+","+(origin.Month-1)+")");
		tr.append(document.createElement("td"));
		tr.children("td").last().attr("colspan","5");
		tr.children("td").last().html("<span style='font-weight:900;font-size:2.5em;color:#51565d;'>"+(origin.Month+1)+"</span><br/>"+origin.Year);
		tr.append(document.createElement("td"));
		tr.children("td").last().attr("onClick","CalendarMove("+origin.Year+","+(origin.Month+1)+")");
		tr.children("td").last().html("&#62;");
		tr.children("td").css("height","2em");
		tr.children("td").css("border","0");
		tr.children("td").css("text-align","center");
		calendar.append(document.createElement("tr"));
		tr = $("table.calendar tr").last();
		for(i=0;i<7;i++){
			var th = document.createElement("th");
			th.innerHTML=Day[i];
			tr.append(th);
		}
	}
	for(i=0;i<42;i++){
		if(i%7==0){
			calendar.append(document.createElement("tr"));
			tr = $("table.calendar tr").last();
		}
		tr.append(document.createElement("td"));
		tr.children("td").last().html(today.getDate());
		if(origin.Month != date.getMonth()){
			tr.children("td").last().addClass("none");
			tr.children("td").last().css("background-size",width*0.036+"px");
		}
		today.setDate(today.getDate()+1);
	}
	$("table.calendar td:not(.none):not(td[colspan])").on("click", function(){
		/*클릭시의 이벤트를 지정가능합니다*/
		var msg = $(this).html();
		alert(msg);
	});
	$("table.calendar td[colspan]").on("click", function(e){
		var contents = document.createElement("div");
		contents = $(contents);
		contents.html("오늘은 "+(origin.Month+1)+"월 입니다.<br/>");
		contents.append(document.createElement("input"));
		contents.children("input").attr("type","text");
		contents.children("input").attr("placeholder","YYYY-MM");
		contents.append(document.createElement("button"));
		contents.children("button").html("해당 월로 이동");
		contents.children("button").on("click",function(){
			var pattern = /[^0-9]/gi;
			var value = contents.children("input").val().replace(pattern,"");
			if(value.length<4){
				CalendarMove(origin.Year,value-1);
			}else if(value.length==4){
				CalendarMove(value,origin.Month);
			}else{
				CalendarMove(value.substring(0,4),value.substring(4)-1);
			}
			delPop();
		});
		mkPop(e, contents);
	});
}

function mkPop(e, contents){
	/*클릭시의 이벤트를 지정가능합니다*/
	var popup = document.createElement("div");
	popup.setAttribute("class","popup");
	$('body').append(popup);
	popup = $('.popup');
	popup.css("position","absolute");
	popup.css("top",e.pageY+38);
	popup.css("left",e.pageX-95);
	popup.append(contents);
	popup.after(document.createElement("div"));
	$(".popup+div").addClass("popup_back");
	$(".popup_back").click(function(){
		delPop();
	});
}
function delPop(){
	$(".popup").remove();
	$(".popup_back").remove();
}
