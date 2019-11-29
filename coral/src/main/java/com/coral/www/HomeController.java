package com.coral.www;





import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST})
	public String main() {
		return "main";
	}
	@Inject
	UserService userService;
	
	@RequestMapping("/login")
	public String login(@RequestParam String id, @RequestParam String pw, HttpServletRequest request) {
		UserDTO dto = new UserDTO();
		logger.info(id+"/"+pw);
		dto.setId(id);
		dto.setPw(pw);
		userService.login(dto, request);
		return "main";
	}
}
