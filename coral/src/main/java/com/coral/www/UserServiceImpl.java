package com.coral.www;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Inject
	UserDAO dao;
	public void login(UserDTO dto, HttpServletRequest request) {
		System.out.println("asdf1");
		try {
			if(dao.isLogin(dto)) {
				dto.setIp(request.getRemoteAddr());
				dto.setPlatform(request.getHeader("user-agent"));
				dao.insertHistory(dto);
				System.out.print(dto.getId()+"/"+dto.getPw());
				dao.getInfo(dto);
				
			}else {
				System.out.println("실패");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
