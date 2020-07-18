/* 
 * HomeController.java		1.0.0 2020.01.31
 * 
 * Copyright all reserved coral
 */

package com.coral.www;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coral.www.File.FileDTO;
import com.coral.www.File.FileService;
import com.coral.www.User.UserDTO;
import com.coral.www.User.UserService;

/**
 * @version			1.0.0 2020.01.31
 * @author			김현우, 이창현, 박승리, 백현욱, 장지수
 */
@Controller
public class HomeController {
	/* 메인페이지 컨트롤러 */
	
	/** 사용자 서비스 */
	@Inject
	UserService userService;
	
	/** 첨부파일 서비스 */
	@Inject
	FileService fileService;
	
	/**
	 * 메인페이지
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST})
	public String main(HttpServletRequest request, Model model) {
		if(request.getSession().getAttribute("id")!=null) {
			model.addAttribute("loginform", "include/loginAfter");
			
			UserDTO dto = userService.getInfo((String)request.getSession().getAttribute("id"));
			if (dto.getMail().contains("{verify-")) {
				dto.setMail("P");
			}
			for (FileDTO profile : fileService.getAttachment(dto.getId())) {
				model.addAttribute("prof_image", profile.getPath());
			}
			model.addAttribute("schedule", userService.scheduleSelect((String)request.getSession().getAttribute("id")));
			model.addAttribute("userInfo", dto);
		}else {
			request.setAttribute("loginform", "include/loginForm");
		}
		return "main";
	}
	
	
}
