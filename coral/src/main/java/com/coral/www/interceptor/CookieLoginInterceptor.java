package com.coral.www.interceptor;


import java.net.URLDecoder;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.coral.www.Cookie.CookieService;
import com.coral.www.User.UserDTO;
import com.coral.www.User.UserService;

public class CookieLoginInterceptor extends HandlerInterceptorAdapter{
	
    // preHandle() : 컨트롤러보다 먼저 수행되는 메서드
	@Inject
	UserService userService;
	@Inject
	CookieService cookieService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        request.setAttribute("servletPath", request.getServletPath());
    	/*선언*/
    	HttpSession session = request.getSession();
        /*세션이 null인 경우 작동*/
        if(session.getAttribute("id")==null) {
        	/*쿠키 가져오기*/
        	Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
        	/*쿠기 존재시에 자동 로그인 처리*/
        	if(loginCookie!=null) {
        		if(cookieService.refresh(response, loginCookie)) {
        			/*쿠기 값, 토큰, 고유키 가져오기 & 선언*/
            		JSONObject cookievalue = (JSONObject) new JSONParser().parse(URLDecoder.decode(loginCookie.getValue(), "UTF-8"));
            		UserDTO dto = new UserDTO();
            		
            		/*DB처리*/
            		dto.setIp(request.getRemoteAddr());
            		dto.setPlatform(request.getHeader("user-agent"));
        			dto.setId((String) cookievalue.get("id"));
        			dto.setLogin_status(1);
        			dto = userService.insertHistory(dto);
        			/*세션에 로그인 정보 저장*/
        			if(dto!=null){
        				dto = userService.getInfo(dto);
        				session.setAttribute("id", dto.getId());
            			session.setAttribute("user-agent", request.getHeader("user-agent"));
            			session.setAttribute("ip", request.getRemoteAddr());
            			session.setAttribute("grade", dto.getGrade());
            			/*쿠키갱신*/
            			
        			}else {
        				request.setAttribute("Code", "alert('로그인에 실패했습니다...')");
        			}
        		}
        	}
        }
        
        
        return true;
    }
  
    // 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메서드
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        super.postHandle(request, response, handler, modelAndView);
    }     
}