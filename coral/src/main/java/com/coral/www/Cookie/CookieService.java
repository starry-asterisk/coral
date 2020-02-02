/* 
 * CookieDAO.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.Cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
public interface CookieService {
	/* 쿠키 서비스 인터페이스  */
	
	/**
	 * 쿠키삭제
	 * 
	 * @param reponse
	 * @param loginCookie
	 * @return
	 */
	public Boolean refresh(HttpServletResponse reponse, Cookie loginCookie);
	
	/**
	 * 쿠키삭제
	 * 
	 * @param reponse
	 * @param loginCookie
	 * @return
	 */
	public Boolean delete(HttpServletResponse reponse, Cookie loginCookie);
	
	/**
	 * 쿠키생성
	 * 
	 * @param reponse
	 * @param session
	 * @return
	 */
	public Boolean create(HttpServletResponse reponse, HttpSession session);
}
