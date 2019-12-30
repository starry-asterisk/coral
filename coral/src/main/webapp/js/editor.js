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
var myEditor;
/*;데이터 가져오기*/
ClassicEditor
.create( document.querySelector( '#editor' ), {
	language: 'ko',
	filebrowserUploadUrl : 'https://coralprogram.com/board/upload'
} )
.then( editor => {
            myEditor = editor;
        } )
.catch( error => {
	console.error( error );
} );
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
	var form = mkForm("/board/upload","POST");
	form.attr("enctype","multipart/form-data");
	form.append($("input[type=file]"));
	form.addValue("title",$(".title_area").val());
	form.addValue("content",myEditor.getData());
	form.addValue("tag",getTag());
	form.addValue("category",$('.custom-select select option:selected').val());
	if(status!=undefined&&status!=""){
		form.addValue("status",status);
	}
	form.submit();
}
$("input[name=file]").on("change",function(){
	var url = FileUpload();
	/*
	if(url!=undefined&&url!=""){
		myEditor.setData(myEditor.getData()+"<p><img alt='naver' src='"+url+"'></img></p>");
	}*/
	
	myEditor.setData(myEditor.getData()+"<p><img alt='naver' src='"+url+"'></img></p>");
	
});

