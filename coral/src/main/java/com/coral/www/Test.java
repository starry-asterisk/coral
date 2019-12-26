package com.coral.www;



import com.coral.www.User.UserDTO;
import com.coral.www.User.UserService;
import com.coral.www.User.UserServiceImpl;


public class Test {
	UserService userService = new UserServiceImpl();
	public void main() throws Exception {
		UserDTO dto = new UserDTO();
		dto.setId("test");
		dto.setMail("6507055@naver.com");
		userService.mail(dto);
	}
}
