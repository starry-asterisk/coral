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
	var contents = myEditor.getData().replace(check.dataUrl,"<img:>");
	if($(".title_area").val()==undefined||$(".title_area").val()==""){
		alert("제목을 입력하세요");
		$(".title_area").focus();
		return;
	}else if($('.custom-select select option:selected').val()==""){
		alert("카테고리를 선택하세요");
		return;
	}else if(contents==""){
		alert("내용을 입력하세요");
		return;
	}
	attachmentList.forEach(function(item,index){
		if(item!=undefined){
			form.addValue("filesName",item[0]);
			form.addValue("filesType",item[1]);
			form.addValue("files",item[2]);
			if(item[3]){
				contents = contents.replace("<img:>","<img:"+index+">");
			}
		}
	});
	form.addValue("title",$(".title_area").val());
	form.addValue("contents",contents);
	form.addValue("tag",getTag());
	form.addValue("category",$('.custom-select select option:selected').val());
	if(status!=undefined&&status!=""){
		form.addValue("status",status);
	}
	form.submit();
}
var attachmentList = [];
$("input[name=files]").on("change",function(){
	/*FileUpload();*/
	var fileList = $(this)[0].files;
	for(i=0;i<fileList.length;i++){
		if(check.exe.test(fileList[i].name.toLowerCase())){
			alert("exe파일은 사용할 수 없습니다!");
			return;
		}
    }
	// 읽기
    for(i=0;i<fileList.length;i++){
    	var reader = new FileReader();
    	reader.readAsDataURL(fileList[i]);
    	reader.name = fileList[i].name;
    	reader.isImage = $(this).data("image");
    	reader.onload = function(e){
    		addAttach(e.target.result, e.target.name, e.target.isImage);
    	}
    }
    this.type='text';
    this.value='';
    this.type='file';
});
function delAttach(button){
	if(button==undefined){
		$("div.fileList").html("");
		attachmentList = [];
	}else{
		var i = button.data("index")-1;
		if(attachmentList[i][1].indexOf("image")>-1){
			var targetIdx = myEditor.getData().indexOf($("img[alt='img']").eq(0).attr("src"));
			var toIdx = myEditor.getData().indexOf("figure",targetIdx);
			var fromIdx = myEditor.getData().lastIndexOf("figure",targetIdx);
			myEditor.setData(myEditor.getData().replaceAt(fromIdx,toIdx,""));
		}
		attachmentList[i]=undefined;
		button.prev().remove();
		button.next().remove();
		button.remove();
	}
}
function addAttach(data_url, name, isImage){
	if(isImage){
		if(check.tail.test(name.toLowerCase())){
			myEditor.setData(myEditor.getData()+"<p><img alt='img' src='"+data_url+"'></img></p>");
			attachmentList.push([name,data_url.split(",")[0],data_url.split(",")[1],true]);
		}else{
			alert("이미지 가 아닌 첨부파일 : "+name);
			return;
		}
	}else{
		attachmentList.push([name,data_url.split(",")[0],data_url.split(",")[1],false]);
	}
	var fileListDiv = $("input[data-image="+isImage+"]").prev();
	if(fileListDiv.html()!=""){
		fileListDiv.append("<hr>");
	}
	fileListDiv.append("<span>"+name+"</span><button tpye='button' style='float:right' onclick='delAttach($(this))' data-index="+attachmentList.length+"> × </button");
	
	
}

$("button.fold").click(function(){
	if($(this).next().hasClass('folded')){
		$(this).next().removeClass('folded');
	}else{
		$(this).next().addClass('folded');
	}
});
$('.user_sub').css({
    'transition': 'all 1.5s' 
});
$(window).scroll(function(){
    $('.user_sub').css({
        'top': $(this).scrollTop()
    });
});