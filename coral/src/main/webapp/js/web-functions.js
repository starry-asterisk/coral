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
	
});

function login(){
	$(".login").css("display","none");
	$(".loginAfter").css("display","block");
}

function logout(){
	$(".login").css("display","block");
	$(".loginAfter").css("display","none");
}

