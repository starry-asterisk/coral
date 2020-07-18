<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="map">
  <tr>
    <td colspan="2">
    	<span>카테고리 관리</span>
    	<div><button disabled>삭제</button><button disabled>적용</button><input readonly value="code"><input readonly value="name"><input readonly value="permission"></div>
    	<c:if test="${Category.size()>0}">
    	<c:forEach var= "list" items="${Category}">
			<div><button type="button" onclick="deleteCA(this)"><i class="fas fa-trash-alt"></i></button><button type="button" onclick="updateCA(this)"><i class="fas fa-pen-alt"></i></button><input readonly type="text" value="${list.code}"><input type="text" value="${list.name}"><input type="text" value="${list.permission}"></div>
		</c:forEach>
  		</c:if>
  		<hr>
  		<div class="new"><button disabled><i class="fas fa-ban"></i></button><button disabled><i class="fas fa-ban"></i></button><input type="text" placeholder="코드"><input type="text" placeholder="이름"><input type="text" placeholder="권한"></div>
  		<hr style="margin-bottom: 40px;">
  		<div class="btn_area_btm"><button class="updProf" onclick="insertCA()">추가</button></div>
    </td>
  </tr>
  <tr>
  	<td>
    	<span>카테고리 이전</span>
    	<c:if test="${Category.size()>0}">
  		<div class="custom-select" style="z-index: 50;">
  		<select>
    		<option value="">카테고리 선택:</option>
    		<c:forEach var= "list" items="${Category}">
				<option value="${list.code}">${list.name}</option>
			</c:forEach>
  		</select>
		</div>
  		<div class="custom-select">
  		<select>
    		<option value="">카테고리 선택:</option>
    		<c:forEach var= "list" items="${Category}">
				<option value="${list.code}">${list.name}</option>
			</c:forEach>
  		</select>
		</div>
  		</c:if>
    	<div class="btn_area_btm"><button class="updProf" onclick="moveCA()">이전</button></div>
    </td>
    <td>
    	<span><!-- 카테고리별 글 댓글 좋아요 조회수 --></span>
    	<div class="btn_area_btm"><button class="updProf"></button></div>
    </td>
  </tr>
  <tr>
    <td>
  		<span><!-- 강좌활동지표 --></span>
  		<div class="btn_area_btm"><button disabled></button></div>
  	</td>
    <td>
  		<span><!-- 유저활동지표 --></span>
  		<div class="btn_area_btm"><button class="updProf"></button></div>
  	</td>
  </tr>
</table>
<script>
createSelect(document.getElementsByClassName("custom-select"));
function deleteCA(Obj){
	var div = $(Obj).parent();
	var from = div.find("input").eq(0).val();
	var modal = toSelect();
	$("button.btn.btn-default").on("click",function(){
		var to = modal.result();
		if(to!=undefined&&to!=""){
			if(from!=to){
				var form = mkForm("/category/delete","POST");
				form.addValue("from",from);
				form.addValue("to",to);
				form.submit();
			}else{
				alert("카테고리를 제대로 선택해 주세요");
			}
		}else{
			alert("카테고리를 제대로 선택해 주세요");
		}
	});
}
function updateCA(Obj){
	var div = $(Obj).parent();
	var form = mkForm("/category/update","POST");
	form.addValue("code",div.find("input").eq(0).val());
	form.addValue("name",div.find("input").eq(1).val());
	form.addValue("permission",div.find("input").eq(2).val());
	form.submit();
}
function moveCA(){
	var from = $("select").eq(0).find("option:selected").val();
	var to = $("select").eq(1).find("option:selected").val();
	if(from!=to&&to!=undefined&&to!=""&&from!=undefined&&from!=""){
		var form = mkForm("/category/move","POST");
		form.addValue("from",from);
		form.addValue("to",to);
		form.submit();
	}else{
		alert("카테고리를 제대로 선택해 주세요");
	}
}
function insertCA(){
	var div = $(".new");
	var code = div.find("input").eq(0).val();
	var name = div.find("input").eq(1).val();
	var permission = div.find("input").eq(2).val();
	if(code!=undefined&&code!=""){
		if(name!=undefined&&name!=""){
			if(permission!=undefined&&permission!=""){
				var form = mkForm("/category/insert","POST");
				form.addValue("code",code);
				form.addValue("name",name);
				form.addValue("permission",permission);
				form.submit();
				return;
			}
		}
	}
	alert("카테고리를 제대로 선택해 주세요");
}
function toSelect(){
	$("#myModal").modal();
	$("button.btn.btn-default").off("click");
	var body = $(".modal-body");
	var title = $(".modal-title");
	var button = $("button.btn.btn-default");
	title.html("삭제 시 이동될 카테고리 선택하기");
	button.html("확정");
	body.html("");
	var clone = $(".custom-select select").eq(0).clone();
	body.append("<div class='custom-select'></div>");
	body.children(".custom-select").append(clone);
	createSelect(body.children(".custom-select"));
	return{
		result:function(){
			return body.find('option:selected').val();
		}
	}
}
</script>
<style>
.map tr:first-child input,.map tr:first-child select{
    min-width: 25%;
}
.map tr:first-child td>div:nth-child(n+1)>button:not(.updProf) {
    height: 30px;
    width:30px;
    margin: 0 8.6px 0 8.6px;
}
.map tr:first-child td>div:nth-child(n+2)>button:not(.updProf) {
    height: 30px;
    width:30px;
    margin: 0 8.6px 0 8.6px;
}
.map tr:first-child td>div:nth-child(2)>input {
    border:0;
}
.map tr:first-child td>div>input{
	margin: 0 5px 0 5px;
}
.map tr:first-child td>div:not(:last-child){
	margin-top:2px;
	margin-bottom:2px;
}
</style>
