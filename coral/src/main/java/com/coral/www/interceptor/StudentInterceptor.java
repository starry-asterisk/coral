package com.coral.www.interceptor;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class StudentInterceptor extends HandlerInterceptorAdapter{
	
    // preHandle() : 컨트롤러보다 먼저 수행되는 메서드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	/*선언*/
    	HttpSession session = request.getSession();
    	String obj =  (String)session.getAttribute("grade");
    	/*생명주기 설정*/
    	session.setMaxInactiveInterval(3600);
        /*로그인 중 인가?*/
    	if(obj==null) {
    		if(!request.getServletPath().contains("ajax")) {
        		response.sendRedirect("/");
        	}
            return false; 
    	}
        switch(obj) {
			case "관리자":
			case "교사":
			case "학생":
				break;
			default:
				if(!request.getServletPath().contains("ajax")) {
	        		response.sendRedirect("/");
	        	}
	            return false; 
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