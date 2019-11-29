package com.coral.www;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Inject
	UserDAO dao;
	public UserDTO login(UserDTO dto, HttpServletRequest request) {
		try {
			if(dao.isId(dto)) {
				dto.setIp(request.getRemoteAddr());
				dto.setPlatform(request.getHeader("user-agent"));
				if(dao.isLogin(dto)) {
					dto.setLogin_status(1);
					dao.insertHistory(dto);
					dto = dao.getInfo(dto);
					dto.setLogin_status(1);
				}else {
					dto.setLogin_status(0);
					dao.insertHistory(dto);
					logger.warn("부정한 로그인 시도");
				}
			}else {
				logger.warn("Id오류");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

}
