package com.coral.www.User;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;


public interface UserService {
	public UserDTO getInfo(UserDTO dto);
	public UserDTO getInfo(String id);
	public UserDTO login(UserDTO dto, HttpSession session);
	public UserDTO insertHistory(UserDTO dto);
	public boolean isId(String id);
	public boolean addUser(UserDTO dto);
	public boolean isMail(UserDTO dto);
	public void mail(UserDTO dto) throws Exception;
	boolean mailVerify(UserDTO dto) throws Exception;
	public boolean checkGrade(String id, String grade);
	public Date lastLogin(String id);
	public List<UserDTO> historyList(String id);
	boolean isLogin(UserDTO dto);
	public boolean update(UserDTO dto);
	public boolean updateStatus(UserDTO dto);
}
