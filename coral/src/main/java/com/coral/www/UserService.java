package com.coral.www;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public interface UserService {
	public UserDTO login(UserDTO dto, HttpServletRequest request);

}
