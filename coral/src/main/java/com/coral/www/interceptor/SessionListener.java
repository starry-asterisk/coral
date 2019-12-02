package com.coral.www.interceptor;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


import com.coral.www.UserDAO;
import com.coral.www.UserDTO;


import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;


@WebListener
public class SessionListener implements HttpSessionListener {
	private UserDAO dao;
	/**
     * Initialize the UserDAO once the application has started
     */
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		System.out.println(session.getId()+" :세션 생성됨");
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		System.out.println(session.getId()+" :세션 종료됨");
		Object obj = session.getAttribute("login");
		if (obj != null) {
			dao = (UserDAO) BeanUtils.getBean("userDAO");
			try {
				UserDTO dto = new UserDTO();
				dto.setLogin_status(-1);
				dto.setId((String) session.getAttribute("id"));
				dto.setPw((String) session.getAttribute("pw"));
				dto.setPlatform((String) session.getAttribute("user-agent"));
				dto.setIp((String) session.getAttribute("ip"));
				
				if(dao.isLogin(dto)) {
					dao.insertHistory(dto);
				}else {
					dto.setLogin_status(0);
					dao.insertHistory(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
