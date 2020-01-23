<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="map">
  <tr>
    <td>
    	<span>회원 탈퇴</span>
    	<input autocomplete="off" type="password" maxlength="25" minlength="8" name="password"  autocomplete="nope"> :pw
    	<input type="password" maxlength="25" minlength="8" name="passwordRe"> :Re
    	<div class="btn_area_btm"><button class="updProf">탈퇴 확인</button></div>
    </td>
    <td>
    	<span></span>
    	<div class="btn_area_btm"><button class="updProf"></button></div>
    </td>
  </tr>
  <tr>
  </tr>
  <tr>
  </tr>
</table>
<script>
		$(".updProf").on("click",function(){
			var div = $(this).parent().parent();
			var active = true;
			div.children().trigger("focusout");
			if(active&&div.children("input").length>1){
				div.children("input").each(function(idx,item){
					active = active&&$(item).hasClass("pass");
				});
			}else if(active&&div.children("input").length==1){
				active = active&&div.children("input").hasClass("pass");
			}
			console.log(div.children("span").html()+" : "+active);
			if(!active){
				alert("옳바르게 비밀번호를 작성하세요");
				return;
			}
			if(confirm("정말로 회원 탈퇴를 하시겠습니까? 확인 버튼을 누르면 동의하신걸로 간주합니다")){
				var form = mkForm("/leave","POST");
				form.addValue("pw",div.children("input").eq(0).val());
				form.submit();
			}
		});

		$(".map input").focusout(function(){
			$(".map input").removeClass("pass");
			var msg="";
			if($("input[name=password]").val()==$("input[name=passwordRe]").val()){
				$(".map input").addClass("pass");
			}
		});
</script>