<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="//cdn.ckeditor.com/4.13.1/full/ckeditor.js"></script>
<script type="text/javascript">
var myEditor = CKEDITOR.replace(document.querySelector( '#editor' ),{
	on :
	{
	   instanceReady : function ()
	   {
	      // Hide the editor top bar.
		  $('.cke_top').css('display',CK_Config.display);
		  $("#cke_1_contents").height('500px');
	   }
	}
});

</script>