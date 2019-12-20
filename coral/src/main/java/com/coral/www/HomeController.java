package com.coral.www;




import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;
import com.coral.www.Cookie.CookieService;
import com.coral.www.User.UserDTO;
import com.coral.www.User.UserService;
import com.coral.www.application.Sha;

@Controller
public class HomeController {
	
	
	@Inject
	UserService userService;
	@Inject
	CookieService cookieService;
	
	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST})
	public String main(HttpServletRequest request) {
		
		if(request.getSession().getAttribute("id")!=null) {
			request.setAttribute("loginform", "include/loginAfter");
		}else {
			request.setAttribute("loginform", "include/loginForm");
		}
		return "main";
	}
	@RequestMapping(value = "/signUp", method = { RequestMethod.GET, RequestMethod.POST})
	public String signUp(HttpServletRequest request) {
		String page;
		if(request.getParameter("grade")!=null) {
			switch(request.getParameter("grade")){
			case "student":
				request.setAttribute("is", "none");
				page = "signUp";
				break;
			case "teacher":
				request.setAttribute("is", "");
				page = "signUp";
				break;
			default:
				page = "redirect:/error?errorcode=500";
				break;
				
			}
		}else {
			page = "redirect:/error?errorcode=500";
		}
		
		return page;
	}
	
	@RequestMapping(value = "/signUpComplete", method = { RequestMethod.GET, RequestMethod.POST}, produces="application/text;charset=utf-8")
	public String signUpComplete(UserDTO dto,HttpServletRequest request) throws UnsupportedEncodingException {
		dto.setPw(Sha.get512(dto.getPw(), dto.getId()));
		if(dto.getAddress()==null) {
			dto.setAddress("");
			dto.setCompany("");
			dto.setTel("");
		}
		if(userService.newUser(dto)) {
			dto.setLogin_status(1);
			dto.setPlatform(request.getHeader("user-agent"));
			dto.setIp(request.getRemoteAddr());
			userService.login(dto);
			/*로그인 정보 저장*/
			HttpSession session = request.getSession();
			session.setAttribute("id", dto.getId());
			session.setAttribute("user-agent", dto.getPlatform());
			session.setAttribute("ip", dto.getIp());
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
						@RequestHeader("user-agent") String agent,
						HttpServletRequest request,
						HttpServletResponse response,
						HttpSession session,
						Model model) throws ParseException, IOException {
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
			dto.setPw(Sha.get512((String) receive.get("pw"), dto.getId()));
			dto.setLogin_status(1);
			dto = userService.login(dto);
			if(dto.getMsg()==null) {
				/*로그인 정보 저장*/
				session.setAttribute("id", receive.get("id"));
				session.setAttribute("user-agent", agent);
				session.setAttribute("ip", request.getRemoteAddr());
				if((boolean)receive.get("remember_me")) {
					/*쿠키생성*/
					cookieService.create(response, session);
				}
			}else {
				REFERER+="?Code=alert('"+URLEncoder.encode(dto.getMsg(), "UTF-8")+"');";
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
				session.invalidate();
				Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
				cookieService.delete(reponse, loginCookie);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("loginform", "include/loginForm");
		return "redirect:"+REFERER;
	}
}
