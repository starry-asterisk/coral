package com.coral.www;


public interface UserService {
	public UserDTO getInfo(UserDTO dto);
	public UserDTO login(UserDTO dto);
	public UserDTO insertHistory(UserDTO dto);
}
