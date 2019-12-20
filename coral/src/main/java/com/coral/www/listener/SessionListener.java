package com.coral.www.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.coral.www.User.UserDAO;
import com.coral.www.User.UserDTO;
import com.coral.www.application.BeanUtils;

import java.util.UUID;

import javax.servlet.http.*;



public class SessionListener implements HttpSessionListener {
	private UserDAO dao;
	/**
     * Initialize the UserDAO once the application has started
     */
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		System.out.println(session.getId()+" :세션 생성됨");
		session.setMaxInactiveInterval(3600);
		session.setAttribute("uuid", UUID.randomUUID().toString());
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		System.out.println(session.getId()+" :세션 종료됨");
		Object obj = session.getAttribute("id");
		if (obj != null) {
			dao = (UserDAO) BeanUtils.getBean("userDAO");
			try {
				UserDTO dto = new UserDTO();
				dto.setLogin_status(-1);
				dto.setId((String) session.getAttribute("id"));
				dto.setPlatform((String) session.getAttribute("user-agent"));
				dto.setIp((String) session.getAttribute("ip"));
				dao.insertHistory(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
