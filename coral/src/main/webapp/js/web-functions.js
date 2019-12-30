/**
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

function report(identifier, identifier_type, reporter, resons){
	$("#myModal").modal();
	$("button.btn.btn-default").off("click");
	var body = $(".modal-body");
	var title = $(".modal-title");
	var button = $("button.btn.btn-default");
	title.html("이용자 신고하기");
	button.html("신고");
	body.html("");
	body.append("<span style='line-height:46px;'>피 신고 대상</span>");
	body.append("<input type='text' readonly value='"+identifier+"' class='basic_input' style='width:70%;margin-right:5%;margin-top:7px;margin-bottom:3px;float:right'>");
	body.append("<br><span style='line-height:46px;'>신고자 아이디</span>");
	body.append("<input type='text' readonly value='"+reporter+"' class='basic_input' style='width:70%;margin-right:5%;margin-top:7px;margin-bottom:3px;float:right'>");
	body.append("<br><span style='line-height:46px;'>신고일시</span>");
	body.append("<input type='text' readonly value='"+new Date()+"' class='basic_input' style='width:70%;margin-right:5%;margin-top:7px;margin-bottom:3px;float:right'>");
	body.append("<div class='custom-select'></div>");
	body.children(".custom-select").append("<select></select>");
	body.find("select").append("<option value=''>사유를 선택해 주세요</option>");
	for(i=0;i<resons.length;i++){
		body.find("select").append("<option value='"+resons[i].split(":")[0]+"'>"+resons[i].split(":")[1]+"</option>");
	}
	createSelect(body.children(".custom-select"));
	return{
		id:identifier,
		type:identifier_type,
		reason:function(){
			return $('.custom-select select option:selected').val();
		}
	}
}
createSelect(document.getElementsByClassName("custom-select"));
function createSelect(x){
	var x, i, j, selElmnt, a, b, c;
	/* Look for any elements with the class "custom-select": */
	
	for (i = 0; i < x.length; i++) {
	  selElmnt = x[i].getElementsByTagName("select")[0];
	  /* For each element, create a new DIV that will act as the selected item: */
	  a = document.createElement("DIV");
	  a.setAttribute("class", "select-selected");
	  a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
	  x[i].appendChild(a);
	  /* For each element, create a new DIV that will contain the option list: */
	  b = document.createElement("DIV");
	  b.setAttribute("class", "select-items select-hide");
	  for (j = 1; j < selElmnt.length; j++) {
	    /* For each option in the original select element,
	    create a new DIV that will act as an option item: */
	    c = document.createElement("DIV");
	    c.innerHTML = selElmnt.options[j].innerHTML;
	    c.addEventListener("click", function(e) {
	        /* When an item is clicked, update the original select box,
	        and the selected item: */
	        var y, i, k, s, h;
	        s = this.parentNode.parentNode.getElementsByTagName("select")[0];
	        h = this.parentNode.previousSibling;
	        for (i = 0; i < s.length; i++) {
	          if (s.options[i].innerHTML == this.innerHTML) {
	            s.selectedIndex = i;
	            h.innerHTML = this.innerHTML;
	            y = this.parentNode.getElementsByClassName("same-as-selected");
	            for (k = 0; k < y.length; k++) {
	              y[k].removeAttribute("class");
	            }
	            this.setAttribute("class", "same-as-selected");
	            break;
	          }
	        }
	        h.click();
	    });
	    b.appendChild(c);
	  }
	  x[i].appendChild(b);
	  a.addEventListener("click", function(e) {
	    /* When the select box is clicked, close any other select boxes,
	    and open/close the current select box: */
	    e.stopPropagation();
	    closeAllSelect(this);
	    this.nextSibling.classList.toggle("select-hide");
	    this.classList.toggle("select-arrow-active");
	  });
	}
}

function closeAllSelect(elmnt) {
  /* A function that will close all select boxes in the document,
  except the current select box: */
  var x, y, i, arrNo = [];
  x = document.getElementsByClassName("select-items");
  y = document.getElementsByClassName("select-selected");
  for (i = 0; i < y.length; i++) {
    if (elmnt == y[i]) {
      arrNo.push(i)
    } else {
      y[i].classList.remove("select-arrow-active");
    }
  }
  for (i = 0; i < x.length; i++) {
    if (arrNo.indexOf(i)) {
      x[i].classList.add("select-hide");
    }
  }
}

/* If the user clicks anywhere outside the select box,
then close all select boxes: */
document.addEventListener("click", closeAllSelect);

function reciepeDetail(){
    $("#myModal").modal();
    $("button.btn.btn-default").off("click");
    var body = $(".modal-body");
	var title = $(".modal-title");
	var button = $("button.btn.btn-default");
	title.html("이용약관");
	button.html("닫기");
	body.html("<p style='text-align:left;word-break:keep-all;'>텍스트를 입력하세요.</p>");
	$(".modal-body p").html("제 1 장 총 칙<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 1 조 (목적)<br>" + 
			"이 약관은 코딩중계사 코랄(이하 \"사이트\"라 합니다)에서 제공하는 인터넷서비스(이하 \"서비스\"라 합니다)의 이용 조건 및 절차에 관한 기본적인 사항을 규정함을 목적으로 합니다.<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 2 조 (약관의 효력 및 변경)<br>" + 
			"① 이 약관은 서비스 화면이나 기타의 방법으로 이용고객에게 공지함으로써 효력을 발생합니다.<br>" + 
			"② 사이트는 이 약관의 내용을 변경할 수 있으며, 변경된 약관은 제1항과 같은 방법으로 공지 또는 통지함으로써 효력을 발생합니다.<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 3 조 (용어의 정의)<br>" + 
			"이 약관에서 사용하는 용어의 정의는 다음과 같습니다.<br>" + 
			"① 회원 : 사이트와 서비스 이용계약을 체결하거나 이용자 아이디(ID)를 부여받은 개인 또는 단체를 말합니다.<br>" + 
			"② 신청자 : 회원가입을 신청하는 개인 또는 단체를 말합니다.<br>" + 
			"③ 아이디(ID) : 회원의 식별과 서비스 이용을 위하여 회원이 정하고 사이트가 승인하는 문자와 숫자의 조합을 말합니다.<br>" + 
			"④ 비밀번호 : 회원이 부여 받은 아이디(ID)와 일치된 회원임을 확인하고, 회원 자신의 비밀을 보호하기 위하여 회원이 정한 문자와 숫자의 조합을 말합니다.<br>" + 
			"⑤ 해지 : 사이트 또는 회원이 서비스 이용계약을 취소하는 것을 말합니다.<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 2 장 서비스 이용계약<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 4 조 (이용계약의 성립)<br>" + 
			"① 이용약관 하단의 동의 버튼을 누르면 이 약관에 동의하는 것으로 간주됩니다.<br>" + 
			"② 이용계약은 서비스 이용희망자의 이용약관 동의 후 이용 신청에 대하여 사이트가 승낙함으로써 성립합니다.<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 5 조 (이용신청)<br>" + 
			"① 신청자가 본 서비스를 이용하기 위해서는 사이트 소정의 가입신청 양식에서 요구하는 이용자 정보를 기록하여 제출해야 합니다.<br>" + 
			"② 가입신청 양식에 기재하는 모든 이용자 정보는 모두 실제 데이터인 것으로 간주됩니다. 실명이나 실제 정보를 입력하지 않은 사용자는 법적인 보호를 받을 수 없으며, 서비스의 제한을 받을 수 있습니다.<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 6 조 (이용신청의 승낙)<br>" + 
			"① 사이트는 신청자에 대하여 제2항, 제3항의 경우를 예외로 하여 서비스 이용신청을 승낙합니다.<br>" + 
			"② 사이트는 다음에 해당하는 경우에 그 신청에 대한 승낙 제한사유가 해소될 때까지 승낙을 유보할 수 있습니다.<br>" + 
			"가. 서비스 관련 설비에 여유가 없는 경우<br>" + 
			"나. 기술상 지장이 있는 경우<br>" + 
			"다. 기타 사이트가 필요하다고 인정되는 경우<br>" + 
			"③ 사이트는 신청자가 다음에 해당하는 경우에는 승낙을 거부할 수 있습니다.<br>" + 
			"가. 다른 개인(사이트)의 명의를 사용하여 신청한 경우<br>" + 
			"나. 이용자 정보를 허위로 기재하여 신청한 경우<br>" + 
			"다. 사회의 안녕질서 또는 미풍양속을 저해할 목적으로 신청한 경우<br>" + 
			"라. 기타 사이트 소정의 이용신청요건을 충족하지 못하는 경우<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 7 조 (이용자정보의 변경)<br>" + 
			"회원은 이용 신청시에 기재했던 회원정보가 변경되었을 경우에는, 온라인으로 수정하여야 하며 변경하지 않음으로 인하여 발생되는 모든 문제의 책임은 회원에게 있습니다.<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 3 장 계약 당사자의 의무<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 8 조 (사이트의 의무)<br>" + 
			"① 사이트는 회원에게 각 호의 서비스를 제공합니다.<br>" + 
			"가. 신규서비스와 도메인 정보에 대한 뉴스레터 발송<br>" + 
			"나. 추가 도메인 등록시 개인정보 자동 입력<br>" + 
			"다. 도메인 등록, 관리를 위한 각종 부가서비스<br>" + 
			"② 사이트는 서비스 제공과 관련하여 취득한 회원의 개인정보를 회원의 동의없이 타인에게 누설, 공개 또는 배포할 수 없으며, 서비스관련 업무 이외의 상업적 목적으로 사용할 수 없습니다. 단, 다음 각 호의 1에 해당하는 경우는 예외입니다.<br>" + 
			"가. 전기통신기본법 등 법률의 규정에 의해 국가기관의 요구가 있는 경우<br>" + 
			"나. 범죄에 대한 수사상의 목적이 있거나 정보통신윤리 위원회의 요청이 있는 경우<br>" + 
			"다. 기타 관계법령에서 정한 절차에 따른 요청이 있는 경우<br>" + 
			"③ 사이트는 이 약관에서 정한 바에 따라 지속적, 안정적으로 서비스를 제공할 의무가 있습니다.<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 9 조 (회원의 의무)<br>" + 
			"① 회원은 서비스 이용 시 다음 각 호의 행위를 하지 않아야 합니다.<br>" + 
			"가. 다른 회원의 ID를 부정하게 사용하는 행위<br>" + 
			"나. 서비스에서 얻은 정보를 사이트의 사전승낙 없이 회원의 이용 이외의 목적으로 복제하거나 이를 변경, 출판 및 방송 등에 사용하거나 타인에게 제공하는 행위<br>" + 
			"다. 사이트의 저작권, 타인의 저작권 등 기타 권리를 침해하는 행위<br>" + 
			"라. 공공질서 및 미풍양속에 위반되는 내용의 정보, 문장, 도형 등을 타인에게 유포하는 행위<br>" + 
			"마. 범죄와 결부된다고 객관적으로 판단되는 행위<br>" + 
			"바. 기타 관계법령에 위배되는 행위<br>" + 
			"② 회원은 관계법령, 이 약관에서 규정하는 사항, 서비스 이용 안내 및 주의 사항을 준수하여야 합니다.<br>" + 
			"③ 회원은 내용별로 사이트가 서비스 공지사항에 게시하거나 별도로 공지한 이용 제한 사항을 준수하여야 합니다.<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 4 장 서비스 제공 및 이용<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 10 조 (회원 아이디(ID)와 비밀번호 관리에 대한 회원의 의무)<br>" + 
			"① 아이디(ID)와 비밀번호에 대한 모든 관리는 회원에게 책임이 있습니다. 회원에게 부여된 아이디(ID)와 비밀번호의 관리소홀, 부정사용에 의하여 발생하는 모든 결과에 대한 전적인 책임은 회원에게 있습니다.<br>" + 
			"② 자신의 아이디(ID)가 부정하게 사용된 경우 또는 기타 보안 위반에 대하여, 회원은 반드시 사이트에 그 사실을 통보해야 합니다.<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 11 조 (서비스 제한 및 정지)<br>" + 
			"① 사이트는 전시, 사변, 천재지변 또는 이에 준하는 국가비상사태가 발생하거나 발생할 우려가 있는 경우와 전기통신사업법에 의한 기간통신 사업자가 전기통신서비스를 중지하는 등 기타 불가항력적 사유가 있는 경우에는 서비스의 전부 또는 일부를 제한하거나 정지할 수 있습니다.<br>" + 
			"② 사이트는 제1항의 규정에 의하여 서비스의 이용을 제한하거나 정지할 때에는 그 사유 및 제한기간 등을 지체없이 회원에게 알려야 합니다.<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제5장 계약사항의 변경, 해지<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 12 조 (정보의 변경)<br>" + 
			"회원이 주소, 비밀번호 등 고객정보를 변경하고자 하는 경우에는 홈페이지의 회원정보 변경 서비스를 이용하여 변경할 수 있습니다.<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 13 조 (계약사항의 해지)<br>" + 
			"회원은 서비스 이용계약을 해지할 수 있으며, 해지할 경우에는 본인이 직접 서비스를 통하거나 전화 또는 온라인 등으로 사이트에 해지신청을 하여야 합니다. 사이트는 해지신청이 접수된 당일부터 해당 회원의 서비스 이용을 제한합니다. 사이트는 회원이 다음 각 항의 1에 해당하여 이용계약을 해지하고자 할 경우에는 해지조치 7일전까지 그 뜻을 이용고객에게 통지하여 소명할 기회를 주어야 합니다.<br>" + 
			"① 이용고객이 이용제한 규정을 위반하거나 그 이용제한 기간 내에 제한 사유를 해소하지 않는 경우<br>" + 
			"② 정보통신윤리위원회가 이용해지를 요구한 경우<br>" + 
			"③ 이용고객이 정당한 사유 없이 의견진술에 응하지 아니한 경우<br>" + 
			"④ 타인 명의로 신청을 하였거나 신청서 내용의 허위 기재 또는 허위서류를 첨부하여 이용계약을 체결한 경우<br>" + 
			"사이트는 상기 규정에 의하여 해지된 이용고객에 대해서는 별도로 정한 기간동안 가입을 제한할 수 있습니다.<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제6장 손해배상<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 14 조 (면책조항)<br>" + 
			"① 사이트는 회원이 서비스 제공으로부터 기대되는 이익을 얻지 못하였거나 서비스 자료에 대한 취사선택 또는 이용으로 발생하는 손해 등에 대해서는 책임이 면제됩니다.<br>" + 
			"② 사이트는 회원의 귀책사유나 제3자의 고의로 인하여 서비스에 장애가 발생하거나 회원의 데이터가 훼손된 경우에 책임이 면제됩니다.<br>" + 
			"③ 사이트는 회원이 게시 또는 전송한 자료의 내용에 대해서는 책임이 면제됩니다.<br>" + 
			"④ 상표권이 있는 도메인의 경우, 이로 인해 발생할 수도 있는 손해나 배상에 대한 책임은 구매한 회원 당사자에게 있으며, 사이트는 이에 대한 일체의 책임을 지지 않습니다.<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"제 15 조 (관할법원)<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"서비스와 관련하여 사이트와 회원간에 분쟁이 발생할 경우 사이트의 본사 소재지를 관할하는 법원을 관할법원으로 합니다.<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"[부칙]<br>" + 
			"<br>" + 
			" <br>" + 
			"<br>" + 
			"(시행일) 이 약관은 2019년 09월부터 시행합니다.");
}

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
			},
			append:function(Object){
				form.append(Object);
			},
			attr:function(name,value){
				form.attr(name,value);
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
	$(".id, .password").click(function(){
		$(this).children("input").focus();
	});
	$("#memo, #Boardlist, #codeRun").click(function(){
		switch($(this).attr("id")){
			case "memo":
				$(".Board_List:not(:nth-child(1))").css("display","none");
				$(".Board_List:nth-child(1)").css("display","");
				break;
			case "Boardlist":
				$(".Board_List:not(:nth-child(2))").css("display","none");
				$(".Board_List:nth-child(2)").css("display","");
				break;
			case "codeRun":
				$(".Board_List:not(:nth-child(3))").css("display","none");
				$(this).attr("title","한번 더 누르면 실행");
				break;
		}
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