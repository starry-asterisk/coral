package com.coral.www;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public interface UserService {
	public void login(UserDTO dto, HttpServletRequest request);

}
