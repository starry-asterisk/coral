package com.coral.www;




import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.coral.www.interceptor.BeanUtils;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST})
	public String main(HttpServletRequest request) {
		return "main";
	}
	@Inject
	UserService userService;
	@RequestMapping("/login")
	public String login(
						@RequestHeader("user-agent") String agent,
						HttpServletRequest request,
						HttpServletResponse response,
						HttpSession session) throws ParseException, UnsupportedEncodingException {
		/*이전 주소의 처리*/
		String REFERER = (String)request.getHeader("REFERER");
		if(REFERER==null) {
			REFERER = "/";
		}else {
			REFERER = REFERER.replaceAll("http://www.coralprogram.com", "");
		}
		JSONParser p = new JSONParser();
		UserDTO dto = new UserDTO();
		
		JSONObject receive = (request.getParameter("json")!=null)?(JSONObject) p.parse(request.getParameter("json")):new JSONObject();
		dto.setIp(request.getRemoteAddr());
		dto.setPlatform(agent);
		if(session.getAttribute("id")!=null){
			dto.setId((String) session.getAttribute("id"));
			dto = userService.getInfo(dto);
		}else {
			dto.setId((String) receive.get("id"));
			dto.setPw((String) receive.get("pw"));
			dto.setLogin_status(1);
			dto = userService.login(dto);
			if(dto.getMsg()==null) {
				/*로그인 정보 저장*/
				session.setAttribute("id", receive.get("id"));
				session.setAttribute("user-agent", agent);
				session.setAttribute("ip", request.getRemoteAddr());
				if((boolean)receive.get("remember_me")) {
					/*쿠키생성*/
					String value = "{\"id\":\""+(String)receive.get("id")+
							"\",\"series\":\""+UUID.randomUUID().toString()+
							"\",\"token\":\""+session.getId()+"\"}";
					Cookie loginCookie = new Cookie("loginCookie",URLEncoder.encode(value, "UTF-8"));
					loginCookie.setDomain("www.coralprogram.com");
					loginCookie.setPath("/");
					loginCookie.setMaxAge(604800);
					response.addCookie(loginCookie);
				}
			}else {
				
				request.setAttribute("errorMsg",dto.getMsg());
			}
		}
		return "redirect:"+REFERER;
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse reponse) throws ParseException, UnsupportedEncodingException {
		String REFERER = (String)request.getHeader("REFERER");
		if(REFERER==null) {
			REFERER = "/";
		}else {
			REFERER = REFERER.replaceAll("http://www.coralprogram.com", "");
		}
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("id");
		if (obj != null) {
			try {
				UserDTO dto = new UserDTO();
				dto.setLogin_status(-1);
				dto.setId((String) session.getAttribute("id"));
				dto.setPlatform((String) session.getAttribute("user-agent"));
				dto.setIp((String) session.getAttribute("ip"));
				userService.login(dto);
				session.invalidate();
				/*쿠키 가져오기*/
	        	Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
	        	if(loginCookie!=null) {
	        		loginCookie.setPath("/");
	        		loginCookie.setMaxAge(0);
	        		reponse.addCookie(loginCookie);
	        	}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:"+REFERER;
	}
}
