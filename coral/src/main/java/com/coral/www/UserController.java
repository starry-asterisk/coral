package com.coral.www;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;
import com.coral.www.Cookie.CookieService;
import com.coral.www.File.FileDTO;
import com.coral.www.File.FileService;
import com.coral.www.User.UserDTO;
import com.coral.www.User.UserService;
import com.coral.www.application.Sha;

@Controller
public class UserController {
	
	
	@Inject
	UserService userService;
	@Inject
	CookieService cookieService;
	@Inject
	FileService fileService;
	
	@RequestMapping(value = "/signUp", method = { RequestMethod.GET })
	public String signUp(HttpServletRequest request) {
		String page;
		if(request.getParameter("grade")!=null) {
			switch(request.getParameter("grade")){
			case "student":
				request.setAttribute("is", "none");
			case "teacher":
				page = "signUp";
				break;
			default:
				page = "redirect:/error?errorcode=405";
				break;
			}
		}else {
			page = "redirect:/error?errorcode=405";
		}
		return page;
	}
	
	@RequestMapping(value = "/signUp", method = {RequestMethod.POST}, produces="application/text;charset=utf-8")
	public String signUpComplete(UserDTO dto,HttpServletRequest request) throws Exception {
		
		dto.setPw(Sha.get256(dto.getPw(), dto.getId()));
		
		if(dto.getAddress()==null) {
			dto.setAddress("");
			dto.setCompany("");
			dto.setTel("");
		}
		
		String mail = dto.getMail();
		dto.setMail("{verify-"+Sha.get512(dto.getId(), dto.getMail()).substring(0,16)+"}"+mail);
		if(userService.addUser(dto)) {
			dto.setLogin_status(1);
			dto.setPlatform(request.getHeader("user-agent"));
			dto.setIp(request.getRemoteAddr());
			userService.login(dto, request.getSession());
			dto.setMail(mail);
			userService.mail(dto);
		}
		return "redirect:/"+"?Code=alert('"+URLEncoder.encode("회원가입이 완료되었습니다. 인증메일이 발송 되었으니 인증을 해주시면 더 많은 서비스 이용이 가능합니다", "UTF-8")+"')";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserDTO dto,
						HttpServletRequest request,
						HttpServletResponse response,
						HttpSession session) throws ParseException, IOException {
		/*이전 주소의 처리*/
		String REFERER = (String)request.getHeader("REFERER");
		if(REFERER==null) {
			REFERER = "/";
		}else {
			REFERER = REFERER.replaceAll("http://www.coralprogram.com", "");
			if(REFERER.contains("?")) {
				REFERER = REFERER.split("\\?")[0];
			}
		}
		
		dto.setIp(request.getRemoteAddr());
		dto.setPlatform(request.getHeader("user-agent"));
		if(session.getAttribute("id")==null) {
			dto.setPw(Sha.get256((String) dto.getPw(), dto.getId()));
			dto.setLogin_status(1);
			dto = userService.login(dto, session);
			if(dto.getMsg()==null) {
				if(Boolean.valueOf(request.getParameter("remember_me"))) {
					/*쿠키생성*/
					cookieService.create(response, session);
				}
			}else {
				REFERER+="?Code=alert('"+URLEncoder.encode(dto.getMsg(), "UTF-8")+"')";
			}
		}else {
			REFERER+="?Code=alert('"+URLEncoder.encode("이미 로그인중 입니다", "UTF-8")+"')";
		}
		return "redirect:"+REFERER;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse reponse) throws ParseException, UnsupportedEncodingException {
		/*이전 주소의 처리*/
		String REFERER = (String)request.getHeader("REFERER");
		if(REFERER==null) {
			REFERER = "/";
		}else {
			REFERER = REFERER.replaceAll("http://www.coralprogram.com", "");
			if(REFERER.contains("?")) {
				REFERER = REFERER.split("\\?")[0];
			}
		}
		
		try {
			request.getSession().invalidate();
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			cookieService.delete(reponse, loginCookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:"+REFERER;
	}
	
	@RequestMapping("/emailConfirm")
	public String emailConfirm(Model model, @RequestParam String id, @RequestParam String email, @RequestParam String authkey ) throws Exception{
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setMail(email);
		dto.setMsg(authkey.substring(0,16));
		return "redirect:/"+"?Code=alert('"+URLEncoder.encode("이메일 인증에 "+(userService.mailVerify(dto)?"성공":"실패")+"했습니다!", "UTF-8")+"')";
	}
	
	@RequestMapping("/userpage")
	public String userPage(@RequestParam String id,Model model) {
		
		return "";
	}
	
	@RequestMapping("/mypage")
	public String myPage(HttpSession session,Model model) {
		UserDTO dto = new UserDTO();
		dto.setId((String)session.getAttribute("id"));
		model.addAttribute("profileImage", fileService.getAttachment(dto.getId()));
		dto = userService.getInfo(dto);
		if(dto.getMail().contains("{verify-")) {
			dto.setMail("미인증 이메일");
		}
		dto.setDate(userService.lastLogin(dto.getId()));
		for(FileDTO profile:fileService.getAttachment(dto.getId())) {
			model.addAttribute("prof_image", profile.getPath());
		}
		model.addAttribute("userInfo", dto);
		return "myPage";
	}
	
	@RequestMapping("/myApp/map")
	public String map(HttpServletResponse response, HttpSession session) {
		if(session.getAttribute("id")==null) {
			response.setStatus(401);
		}
		return "myApplication/map";
	}
	
	@RequestMapping("/myApp/schedule")
	public String schedule(HttpServletResponse response, HttpSession session) {
		if(session.getAttribute("id")==null) {
			response.setStatus(401);
		}
		return "myApplication/schedule";
	}
}
