/**
 * 
 */
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                $("input[name=address]").val(extraAddr);
            
            } else {
            	$("input[name=address]").val('');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            $('input[name=zipcode]').val(data.zonecode);
            $("input[name=address]").val(addr);
            $('input[name=zipcode]').addClass("pass");
            $('input[name=address]').addClass("pass");
            // 커서를 상세주소 필드로 이동한다.
            $("input[name=address_detail]").focus();
        }
    }).open();
}

$(function(){
	$(".submit").on("click", function() {
		var pass = true;
		var input = ".form tr:not([style='display:none']) input, .form tr:not([style='display:none']) select";
		$(input).each(function(index, item){
			pass = $(item).hasClass("pass")?pass:false;
			if(!pass){
				$(item).focus();
			}
		});
		if (pass) {
			var form = mkForm("/signUpComplete","post");
			form.addValue('id',$('input[name=id]').val());
			form.addValue('pw',$('input[name=password]').val());
			form.addValue('name',$('input[name=name]').val());
			form.addValue('birth',$('input[name=birth]').val());
			form.addValue('gender',$('select[name=gender] option:not([hidden]):selected').val());
			form.addValue(
					'phone',
					$('select[name=phone_front] option:not([hidden]):selected').val()+
					"-"+$('input[name=phone_middle]').val()+
					"-"+$('input[name=phone_behind]').val()
			);
			if($(input).length==20){
				form.addValue('address',$('input[name=zipcode]').val()+"/"+$('input[name=address]').val()+"/"+$('input[name=address_detail]').val());
				form.addValue('tel',$('select[name=tel_front] option:not([hidden]):selected').val()+"-"+$('input[name=tel_middle]').val()+"-"+$('input[name=tel_behind]').val());
				form.addValue('company',$('input[name=company_name]').val());
				form.addValue('grade','학생');
			}else{
				form.addValue('grade','교사');
			}
			form.addValue('mail',$('input[name=email_front]').val()+"@"+$('input[name=email_behind]').val());
			form.addValue('privacy',$("input[name=privacy]").is(":checked")?'O':'X');
			
			
			form.submit();
		} else {
			alert("입구컷");
		}
		
	});
	
	var check = new Object;
	check.mail = /^[a-z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/;
	check.URL = /^(file|gopher|news|nntp|telnet|https?|ftps?|sftp):\/\/([a-z0-9-]+\.)+[a-z0-9]{2,4}.*$/;
	check.tel = /(\d{3}).*(\d{3}).*(\d{4})/;
	check.date = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
	check.tail = /([^\s]+(?=\.(jpg|gif|png))\.\2)/;
	check.id = /(?=.*\d)(?=.*[a-zA-Z]).{8,15}/;
	check.name = /[가-힣]{1,5}/;
	
	$(".form input, .form select").focusout(function(){
		$(this).removeClass("pass");
		var msg="";
		switch($(this).attr("name")){
			case 'id':
				if(check.id.test($(this).val())){
					if(idExit($(this).val())){
						$(this).addClass("pass");
						msg = "<span style='color:green'>정상!</span>";
					}else{
						msg = "중복";
					}
				}else{
					msg = "8~15자 사이,영문+숫자 조합으로 입력해주세요";
				}
				break;
			case 'password':
				if(check.id.test($(this).val())){
					$(this).addClass("pass");
				}else{
					msg = "8~15자 사이,영문+숫자 조합으로 입력해주세요";
				}
				break;
			case 'passwordRe':
				if(check.id.test($(this).val())){
					if($(this).val()==$("input[name=password]").val()){
						$(this).addClass("pass");
					}
				}else{
					msg = "비밀번호가 일치하지 않습니다";
				}
				break;
			case 'name':
				if(check.name.test($(this).val())){
					$(this).addClass("pass");
				}else{
					msg = "옳바른 이름 형식이 아닙니다";
				}
				break;
			case 'birth':
				if($(this).val()!=""){
					$(this).addClass("pass");
				}else{
					msg = "생년월일을 선택해 주세요";
				}
				break;
			case 'gender':
				if($(this).children("option:not([hidden]):selected").val()!=undefined){
					$(this).addClass("pass");
				}else{
					msg = "성별을 선택해 주세요";
				}
				break;
			case 'phone_front':
				if($(this).children("option:not([hidden]):selected").val()!=undefined){
					$(this).addClass("pass");
				}else{
					msg = "옳바른 폰 번호를 입력해 주세요";
				}
				break;
			case 'phone_middle':
				if($(this).val()!=""){
					$(this).addClass("pass");
				}else{
					msg = "옳바른 폰 번호를 입력해 주세요";
				}
				break;
			case 'phone_behind':
				if($(this).val()!=""){
					$(this).addClass("pass");
				}else{
					msg = "옳바른 폰 번호를 입력해 주세요";
				}
				break;
			case 'email_front':
				if(check.mail.test($(this).val()+"@coralprogram.com")){
					$(this).addClass("pass");
				}else{
					msg = "옳바른 이메일 양식이 아닙니다";
				}
				break;
			case 'email_behind':
				if(check.mail.test("test001@"+$(this).val())){
					$(this).addClass("pass");
				}else{
					msg = "옳바른 이메일 양식이 아닙니다";
				}
				break;
			case 'agreement':
				if($(this).is(":checked")){
					$(this).addClass("pass");
				}else{
					msg = "약관에 동의해 주세요";
				}
				break;
			default:
				if($(this).val()!=""&&$(this).val()!=undefined)
				$(this).addClass("pass");
				break;
		}
		$(this).parent().next().html(msg);
	});
});