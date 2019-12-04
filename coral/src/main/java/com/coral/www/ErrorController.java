package com.coral.www;






import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	
	
	@RequestMapping(value = "/error")
	public String main(HttpServletRequest request) {
		int ErrorCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String msg;
		switch(ErrorCode){
		case 404:
			msg = "페이지를 찾을 수 없습니다.";
			break;
		default:
			msg = "알 수 없는 오류 입니다.";
		}
		request.setAttribute("ErrorMSG", ErrorCode+" "+msg);
		return "error";
	}
}
