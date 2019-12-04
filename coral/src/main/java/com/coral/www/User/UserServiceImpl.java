package com.coral.www.User;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Inject
	UserDAO dao;
	public UserDTO getInfo(UserDTO dto) {
		try {
			dto = dao.getInfo(dto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	public UserDTO login(UserDTO dto) {
		try {
			if(dao.isId(dto)) {
				if(dao.isLogin(dto)||(dto.getLogin_status()==-1)) {
					dao.insertHistory(dto);
					dto = dto.getLogin_status()==1?dao.getInfo(dto):null;
				}else {
					dto.setLogin_status(0);
					dao.insertHistory(dto);
					dto.setMsg("부정한 로그인 시도");
				}
			}else {
				dto.setMsg("Id오류");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	public UserDTO insertHistory(UserDTO dto) {
		try {
			if(!dao.insertHistory(dto)) {
				dto=null;
			};
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

}
