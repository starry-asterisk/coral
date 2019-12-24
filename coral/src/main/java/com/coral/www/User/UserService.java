package com.coral.www.User;

public interface UserService {
	public UserDTO getInfo(UserDTO dto);
	public UserDTO login(UserDTO dto);
	public UserDTO insertHistory(UserDTO dto);
	public boolean isId(String id);
	public boolean newUser(UserDTO dto);
	public boolean isMail(UserDTO dto);
	public void mail(UserDTO dto) throws Exception;
	boolean mailVerify(UserDTO dto) throws Exception;
}
