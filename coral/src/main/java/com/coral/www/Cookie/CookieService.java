package com.coral.www.Cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.web.util.WebUtils;

public interface CookieService {
	public Boolean refresh(HttpServletResponse reponse, Cookie loginCookie);
	public Boolean delete(HttpServletResponse reponse, Cookie loginCookie);
	public Boolean create(HttpServletResponse reponse, HttpSession session);
	public JSONObject info(HttpServletResponse reponse, HttpServletRequest request);
}
