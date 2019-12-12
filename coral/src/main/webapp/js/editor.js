$(".tag_form").click(function(){
			$(".tag_form input").focus();
});
$(".tag_form input").keydown(function (key) {
	if(key.keyCode == 13 || key.keyCode == 188){
		if($(".tag_form input").val()!=""){
			key.preventDefault();
		    var span = document.createElement("span");
		    var btn = document.createElement("button");
		    btn.innerHTML="&#215;";
		    span.innerHTML=$(".tag_form input").val();
		    $(".tag_form input").before(span);
		    $(".tag_form span:last").append(btn);
		    $(".tag_form span:last").attr("class","tag_element");
		    $(".tag_form span:last button").attr("class","deleteBtn");
		    $(".tag_form span:last button").attr("type","button");
		    $(".tag_form input").val("");
		    $(".deleteBtn").click(function(){
		    	$(this).parent().remove();
		    });
		}else{
			alert("태그 이름을 입력해 주세요!");
		}
	}
});
ClassicEditor
.create( document.querySelector( '#editor' ) )
.catch( error => {
	console.error( error );
} );