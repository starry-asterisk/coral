<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://cdn.ckeditor.com/ckeditor5/15.0.0/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/15.0.0/classic/translations/ko.js"></script>
<script type="text/javascript">
var myEditor;
/*;데이터 가져오기*/
ClassicEditor
.create( document.querySelector( '#editor' ), {
	language: 'ko'
} ).then( editor => {
            myEditor = editor;
            editor.isReadOnly = CK_Config.isReadonly;
            $(".ck.ck-editor__top.ck-reset_all").css("display",CK_Config.display);
        } ).catch( error => {
	console.error( error );
} );
</script>