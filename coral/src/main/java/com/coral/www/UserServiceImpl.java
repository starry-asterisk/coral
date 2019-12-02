package com.coral.www;

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
			if(dao.isLogin(dto)) {
				dto = dao.getInfo(dto);
			}else {
				dto.setMsg("부정한 로그인 시도");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	public UserDTO login(UserDTO dto) {
		try {
			if(dao.isId(dto)) {
				if(dao.isLogin(dto)) {
					dao.insertHistory(dto);
					dto = dto.getLogin_status()==1?dao.getInfo(dto):dto;
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

}
