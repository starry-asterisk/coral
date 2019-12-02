package com.coral.www;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public interface UserService {
	public UserDTO getInfo(UserDTO dto);
	public UserDTO login(UserDTO dto);
}
