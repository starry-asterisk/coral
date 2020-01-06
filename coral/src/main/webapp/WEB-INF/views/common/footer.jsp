<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<button class="ScrollUpButton circle_btn" title="맨 위로 이동">&#10514;</button>

<footer>
	<div class="notice">
	<a href="/" style="font-weight:600;line-height:37.34px;">공지사항</a>
	</div>
	<div class="external_link">
		&#60;
		<a href="https://www.youtube.com/"><img title="유튜브홍보링크" alt="youtube" width="60px" height="60px" src="/resources/icon/youtube.png"></a>
		
		<a href="https://github.com/"><img title="깃헙링크" alt="github" width="60px" height="60px"  src="/resources/icon/github.png"></a>
		
		<a href="https://www.naver.com/"><img title="네이버" alt="naver" width="60px" height="60px"  src="/resources/icon/naver.png"></a>
		
		<a href="http://www.moel.go.kr/"><img title="고용노동부" alt="moel" width="60px" height="60px"  src="/resources/icon/government.png"></a>
		
		<a href="http://www.icia.co.kr/"><img title="icia교육원" alt="icia" width="60px" height="60px"  src="/resources/icon/icia.png"></a>
		&#62;
	</div>
	<div class="info">
	사이트소개 | 이용안내 | <button onclick="reciepeDetail();">이용약관</button> | 제작인원 소개<br/>
	Copyrightⓒ2019 Coral All rights reserved
	</div>
</footer>

 <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4 class="modal-title"></h4>
        </div>
        <div class="modal-body">
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal"></button>
        </div>
      </div>
      
    </div>
  </div>
  
  <script>
  var isReload = false;
  alert(window.onpopstate);
  window.onpopstate = function (event) {
	  alert(event.state);
	  if (event.state) {
	    // history changed because of pushState/replaceState
		  isReload = true;
	  }
	}
  if(!isReload){
	  ${Code}<%=request.getParameter("Code")%>
  }
  </script>