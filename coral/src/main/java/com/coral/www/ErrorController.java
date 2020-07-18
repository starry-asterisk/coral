/* 
 * ErrorController.java		1.0.0 2020.01.31
 * 
 * Copyright all reserved coral
 */

package com.coral.www;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @version			1.0.0 2020.01.31
 * @author			김현우, 이창현, 박승리, 백현욱, 장지수
 */
@Controller
public class ErrorController {
	
	/**
	 * 에러 페이지로 이동
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/error")
	public String main(HttpServletRequest request) throws Exception{
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
