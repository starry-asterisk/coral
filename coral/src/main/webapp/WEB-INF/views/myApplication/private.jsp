<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="map">
  <tr>
    <td>
    	<span>프로필</span>
    	<input autocomplete="off" readonly type="text" maxlength="20" minlength="8" value="${userInfo.id}" class="pass" >:Id
    	<input type="text" maxlength="5" minlength="2" name="name" value="${userInfo.name}">:Name
    	<input type="date" name="birth" min="1900-01-01" max="2005-12-19" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${userInfo.birth}" />">:Birth
    	<div class="btn_area_btm"><button class="updProf">수정적용</button></div>
    </td>
    <td>
    	<span>기본연락처</span>
    	<select name="phone_front" style="max-width: 70px;width: 70px;min-width: 60px;">
			<option hidden="hidden" selected>----</option>
			<option value="010" ${userInfo.phone.split("-")[0] eq "010" ? "selected":""}>010</option>
			<option value="011" ${userInfo.phone.split("-")[0] eq "011" ? "selected":""}>011</option>
			<option value="016" ${userInfo.phone.split("-")[0] eq "016" ? "selected":""}>016</option>
			<option value="017" ${userInfo.phone.split("-")[0] eq "017" ? "selected":""}>017</option>
			<option value="019" ${userInfo.phone.split("-")[0] eq "019" ? "selected":""}>019</option>
		</select> - <input value='${userInfo.phone.split("-")[1]}' type="text" maxlength="4" minlength="4" name="phone_middle" style="max-width: 70px;width: 70px;min-width: 60px;"> - 
					<input value='${userInfo.phone.split("-")[2]}' type="text" maxlength="4" minlength="4" name="phone_behind" style="max-width: 70px;width: 70px;min-width: 60px;">:Phone
    	<input value='${userInfo.mail.contains("{verify")?"":userInfo.mail.split("@")[0]}' style="max-width: 100px;min-width: 30%;" type="text" maxlength="40" minlength="1" name="email_front" spellcheck="false">@<input value='${userInfo.mail.contains("{verify")?"         ":userInfo.mail.split("@")[1]}' style="max-width: 100px;min-width: 30%;" type="text" maxlength="40" minlength="1" name="email_behind" spellcheck="false" value="　">:Email
    	
    	
    	<div class="btn_area_btm">
    	<button class="updProf">수정적용</button></div>
    </td>
  </tr>
  <tr>
  	<td>
  		<span>개인정보 공개여부</span>
  		프로필 공개 여부 <input type="checkbox" value=true name="privacy"  ${userInfo.privacy eq 'O'.charAt(0) ? "checked":""}><br>
  		개인정보 제공 및 이용약관 동의<input type="checkbox" disabled checked class="pass"><br>
  		<div class="btn_area_btm"><button class="updProf">수정적용</button><button onclick="reciepeDetail();" style="float:right;">개인정보약관</button></div>
  	</td>
    <td>
    	<span>비밀번호 변경</span>
    	<input autocomplete="off" type="password" maxlength="25" minlength="8" name="password"  autocomplete="nope"> :Old
    	<input autocomplete="off" type="password" maxlength="25" minlength="8" name="password"  autocomplete="nope"> :New
    	<input type="password" maxlength="25" minlength="8" name="passwordRe"> :Re
    	<div class="btn_area_btm"><button class="updProf">수정적용</button></div>
    </td>
  </tr>
  <tr>
    <td>
  		<span>회사연락처</span>
  		<input readonly type="text" value='${userInfo.address.split("/")[0]}'>:Zip
    	<input readonly type="text" value='${userInfo.address.split("/")[1]}'>:Base
    	<input readonly type="text" value='${userInfo.address.split("/")[2]}'>:Detail
  		<div class="btn_area_btm"><button disabled>수정불가</button></div>
  	</td>
    <td><div id="map"></div></td>
  </tr>
</table>

			
<script>
		var container = document.getElementById('map');
		var options = {
			center: new kakao.maps.LatLng(37.438899, 126.675114),
			level: 3
		};

		var map = new kakao.maps.Map(container, options);
		
		if(address!=''){
			address_detail = address.split("/")[1];
			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder();

			// 주소로 좌표를 검색합니다
			geocoder.addressSearch(address_detail, function(result, status) {

			    // 정상적으로 검색이 완료됐으면 
			     if (status === kakao.maps.services.Status.OK) {

			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			        // 결과값으로 받은 위치를 마커로 표시합니다
			        var marker = new kakao.maps.Marker({
			            map: map,
			            position: coords
			        });

			        // 인포윈도우로 장소에 대한 설명을 표시합니다
			        var infowindow = new kakao.maps.InfoWindow({
			            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+company+'</div>'
			        });
			        infowindow.open(map, marker);

			        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			        map.setCenter(coords);
			    } 
			});   
		}
		$(".updProf").on("click",function(){
			var div = $(this).parent().parent();
			var active = true;
			div.children().trigger("focusout");
			if(div.children("select").length>1){
				div.children("select").each(function(idx,item){
					active = active&&$(item).hasClass("pass");
				});
			}else if(div.children("select").length==1){
				active = active&&div.children("select").hasClass("pass");
			}
			if(active&&div.children("input").length>1){
				div.children("input").each(function(idx,item){
					active = active&&$(item).hasClass("pass");
				});
			}else if(active&&div.children("input").length==1){
				active = active&&div.children("input").hasClass("pass");
			}
			console.log(div.children("span").html()+" : "+active);
			if(!active){
				alert("옳바르게 수정내용을 작성하세요");
				return;
			}
			var form = mkForm("/newInfo","POST");
			switch(div.children("span").html()){
				case "프로필":
					form.addValue("name",div.children("input").eq(1).val());
					form.addValue("birth",div.children("input").eq(2).val());
					break;
				case "기본연락처":
					form.addValue("phone",$("option:selected").val()+"-"+div.children("input").eq(0).val()+"-"+div.children("input").eq(1).val());
					form.addValue("mail",div.children("input").eq(2).val()+"@"+div.children("input").eq(3).val());
					break;
				case "개인정보 공개여부":
					form.addValue("privacy",$(div.children("input").eq(0)).is(":checked")?'O':'X');
					break;
				case "비밀번호 변경":
					form.addValue("pw",div.children("input").eq(0).val());
					form.addValue("newPw",div.children("input").eq(1).val());
					break;
				default:
					return;
					break;
			}
			form.submit();
		});

		$(".map input, .map select").focusout(function(){
			$(this).removeClass("pass");
			var msg="";
			switch($(this).attr("name")){
				case 'password':
					if(check.id.test($(this).val())&&($("input[name=password]").eq(1).val()!=$("input[name=password]").eq(0).val())){
						$(this).addClass("pass");
					}
					break;
				case 'passwordRe':
					if(check.id.test($(this).val())){
						if($(this).val()==$("input[name=password]").eq(1).val()){
							$(this).addClass("pass");
						}
					}
					break;
				case 'name':
					if(check.name.test($(this).val())){
						$(this).addClass("pass");
					}
					break;
				case 'birth':
					if($(this).val()!=""){
						$(this).addClass("pass");
					}
					break;
				case 'phone_front':
					if($(this).children("option:not([hidden]):selected").val()!=undefined){
						$(this).addClass("pass");
					}
					break;
				case 'phone_middle':
				case 'phone_behind':
				default:
					if($(this).val()!=""&&$(this).val()!=undefined){
						$(this).addClass("pass");
					}
					break;
				case 'email_front':
					if(check.mail.test($(this).val()+"@coralprogram.com")){
						if($("input[name='email_behind']").val()!=undefined&&$("input[name='email_behind']").val()!=""){
							if(mailExit($(this).val()+"@"+$("input[name='email_behind']").val())=='true'){
								$(this).addClass("pass");
								msg = "<span style='color:green'>정상!</span>";
							}
						}else{
							$(this).addClass("pass");
						}
					}
					break;
				case 'email_behind':
					if(check.mail.test("test001@"+$(this).val())){
						if($("input[name='email_front']").val()!=undefined&&$("input[name='email_front']").val()!=""){
							if(mailExit($("input[name='email_front']").val()+"@"+$(this).val())=='true'){
								$(this).addClass("pass");
								msg = "<span style='color:green'>정상!</span>";
							}
						}else{
							$(this).addClass("pass");
						}
					}
					break;
			}
		});
</script>