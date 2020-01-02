function addTag(value){
	var span = $(document.createElement("span"));
    var btn = $(document.createElement("button"));
	span.attr("class","tag_element");
	span.val("#"+value);
    btn.attr("class","deleteBtn");
    $(".tag_form input").before(span);
    span.append(value);
    span.append(btn);
    btn.attr("type","button");
    btn.html("&#215;");
    $(".tag_form input").val("");
    $(".deleteBtn").click(function(){
    	$(this).parent().remove();
    });
}
if($(".tag_form input").val()!=""){
	var array = $(".tag_form input").val().split(" ");
	for(i=0;i<array.length;i++){
		addTag(array[i]);
	}
}
$(".tag_form").click(function(){
			$(".tag_form input").focus();
});
var placeholder = $(".tag_form").attr("placeholder");
if(placeholder!=undefined&&placeholder!=""){
	if($(".tag_form span").length=="0"){
		$(".tag_form").append(document.createElement("a"));
		$(".tag_form a").addClass("placeholder");
		$(".tag_form a").html(placeholder);
		$(".tag_form a").css("padding","0");
	}
}

$(".tag_form input").focus(function(){
	var tag = $(".tag_form");
	tag.removeClass();
	tag.addClass("focus tag_form");
	$(".placeholder").remove();
});
$(".tag_form input").focusout(function(){
	var tag = $(".tag_form");
	tag.removeClass();
	tag.addClass("tag_form");
	if($(".tag_form input").val()==""&&$(".tag_form span").length=="0"){
		$(".tag_form").append(document.createElement("a"));
		$(".tag_form a").addClass("placeholder");
		$(".tag_form a").html(placeholder);
		$(".tag_form a").css("padding","0");
	}
});
$(".tag_form input").keydown(function (key) {
	if(key.keyCode == 13 || key.keyCode == 188){
		if($(".tag_form input").val()!=""){
			key.preventDefault();
			var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/gi;
			var array = $(".tag_form input").val().replace(regExp,'').split(" ");
			for(i=0;i<array.length;i++){
				if(array[i]!=""){
					addTag(array[i]);
				}
			}
		}else{
			alert("태그 이름을 입력해 주세요!");
		}
	}
});

function getTag(){
	var values = $(".tag_form span");
	var returnValue = "";
	for(i=0;i<values.length;i++){
		returnValue += $(values[i]).val();
	}
	returnValue +="#";
	return returnValue;
}
function upload(status){
	var form = mkForm("/board/write","POST");
	form.attr("enctype","multipart/form-data");
	$("input[name=files]")[0].remove();
	form.append($("input[name=files]"));
	form.addValue("title",$(".title_area").val());
	form.addValue("contents",myEditor.getData().replace(check.dataUrl,"<img:>"));
	form.addValue("tag",getTag());
	form.addValue("category",$('.custom-select select option:selected').val());
	if(status!=undefined&&status!=""){
		form.addValue("status",status);
	}
	form.submit();
}

$("input[name=files]").on("change",function(){
	/*FileUpload();*/
	var fileList = $(this)[0].files;
	for(i=0;i<fileList.length;i++){
		if(check.exe.test(fileList[i].name.toLowerCase())){
			alert("exe파일은 사용할 수 없습니다!");
			return;
		}
    }
	
	var clone  = $(this).clone();
	clone.css("display","none");
	$(this).after(clone);
	// 읽기
    for(i=0;i<fileList.length;i++){
    	if(check.tail.test(fileList[i].name.toLowerCase())){
    		var reader = new FileReader();
        	reader.readAsDataURL(fileList[i]);
        	reader.onload = function(e){ckAddIMG(e.target.result);}
    	}else{
    		alert("이미지 가 아닌 첨부파일 : "+fileList[i].name)
    	}
    }
});
function ckAddIMG(el){
	myEditor.setData(myEditor.getData()+"<p><img alt='img' src='"+el+"'></img></p>");
}
