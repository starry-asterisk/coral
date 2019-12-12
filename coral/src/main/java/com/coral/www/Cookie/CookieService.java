package com.coral.www.Cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public interface CookieService {
	public Boolean refresh(HttpServletResponse reponse, Cookie loginCookie);
	public Boolean delete(HttpServletResponse reponse, Cookie loginCookie);
	public Boolean create(HttpServletResponse reponse, HttpSession session);
}
