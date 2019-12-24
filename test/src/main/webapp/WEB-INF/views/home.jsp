<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!-- JQuery -->
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<html>
<head>
	<title>main</title>
</head>
<body>
<div class="calendar"></div>
</body>
<script type="text/javascript">
mkCalendar(new Date());
function mkCalendar(origin){
	var place = $(".calendar");
	place.css("display","none");
	var calendar = $(document.createElement("table"));
	calendar.width("400px");
	calendar.height("300px");
	for(i=0;i<42;i++){
		calendar.append(document.createElement("tr"));
		for(j=0;j<7;j++){
			i++;
			calendar.children("tr:last-child").append(document.createElement("td"));
		}
	}
	var start = new Date(origin.getFullYear(),origin.getMonth(),origin.getDate());
	start.setDate(1);
	start.setDate(1-(start.getDay()==0?7:start.getDay()));
	var end = new Date(start.getFullYear(),start.getMonth(),start.getDate());
	end.setDate(end.getDate()+41);
	console.log(start);
	console.log(end);
	for(i=0;i<42;i++){
		var td = calendar.find("tr:nth-child("+(parseInt(i/7)+1)+") td:nth-child("+((i%7)+1)+")");
		if(start.getMonth()==origin.getMonth()){
			td.html(start.getDate());
		}else{
			td.css("background","grey");
			td.html(start.getDate());
		}
		start.setDate(start.getDate()+1);
		
	}
	place.before(calendar);
}
</script>
</html>
