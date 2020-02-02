/* 
 * UserController.java		1.0.0 2020.01.31
 * 
 * Copyright all reserved coral
 */

package com.coral.www;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.coral.www.Board.BoardService;
import com.coral.www.Board.CategoryDTO;
import com.coral.www.Cookie.CookieService;
import com.coral.www.File.FileDTO;
import com.coral.www.File.FileService;
import com.coral.www.Lecture.LectureService;
import com.coral.www.Report.ReportDTO;
import com.coral.www.Report.ReportService;
import com.coral.www.User.ScheduleDTO;
import com.coral.www.User.UserDTO;
import com.coral.www.User.UserService;
import com.coral.www.application.Sha;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.Birthday;
import com.google.api.services.people.v1.model.EmailAddress;
import com.google.api.services.people.v1.model.Name;
import com.google.api.services.people.v1.model.Person;
import com.google.api.services.people.v1.model.Photo;

/**
 * @version			1.0.0 2020.01.31
 * @author			김현우, 이창현, 박승리, 백현욱, 장지수
 */
@Controller
public class UserController {
	/* 사용자 관리 컨트롤러 입니다 */

	/** 사용자 서비스 */
	@Inject
	UserService userService;
	
	/** 쿠키 서비스 */
	@Inject
	CookieService cookieService;
	
	/** 첨부파일 서비스 */
	@Inject
	FileService fileService;
	
	/** 사용자 서비스 */
	@Inject
	LectureService lectureService;
	
	/** 게시판 서비스 */
	@Inject
	BoardService boardService;
	
	/** 신고 서비스 */
	@Inject
	ReportService reportService;
	
	/**
	 * 사용자 랭킹
	 * 
	 * @param model
	 * @param request
	 * @param keyword
	 * 검색어
	 * @param isAjax
	 * @return
	 */
	@RequestMapping("/user")
	public String board(Model model, HttpServletRequest request, @RequestParam(required=false) String keyword, @RequestParam(required=false) String isAjax) {
		userService.addList(model, request, keyword);
		if(isAjax!=null) {
			return "include/user";
		}
		model.addAttribute("Board_type", "include/user");
		model.addAttribute("attachment", "common/blank");
		return "list";
	}

	/**
	 * 회원가입 페이지로 이동
	 * 
	 * @param request
	 * @param grade
	 * @return
	 */
	@RequestMapping(value = "/signUp", method = { RequestMethod.GET })
	public String signUp(HttpServletRequest request, @RequestParam(required=false) String grade) {
		String page;
		if (grade != null) {
			switch (grade) {
			case "student":
				request.setAttribute("display", "none");
			case "teacher":
				page = "signUp";
				break;
			default:
				page = "redirect:/error?errorcode=405";
				break;
			}
		} else {
			page = "redirect:/error?errorcode=405";
		}
		return page;
	}

	/**
	 * 회원 등록
	 * 
	 * @param dto
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/signUp", method = { RequestMethod.POST }, produces = "application/text;charset=utf-8")
	public String signUpComplete(UserDTO dto, HttpServletRequest request) throws Exception {

		dto.setPw(Sha.get256(dto.getPw(), dto.getId()));

		if (dto.getAddress() == null) {
			dto.setAddress("");
			dto.setCompany("");
			dto.setTel("");
		}

		String mail = dto.getMail();
		dto.setMail("{verify-" + Sha.get512(dto.getId(), dto.getMail()).substring(0, 16) + "}" + mail);
		if (userService.addUser(dto)) {
			dto.setLogin_status(1);
			dto.setPlatform(request.getHeader("user-agent"));
			dto.setIp(request.getRemoteAddr());
			userService.login(dto, request.getSession());
			dto.setMail(mail);
			userService.mail(dto);
		}
		return "redirect:/" + "?Code=alert('"
				+ URLEncoder.encode("회원가입이 완료되었습니다. 인증메일이 발송 되었으니 인증을 해주시면 더 많은 서비스 이용이 가능합니다", "UTF-8") + "')";
	}

	/**
	 * 로그인 처리
	 * 
	 * @param dto
	 * 로그인 정보
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(UserDTO dto, HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ParseException, IOException {
		/* 이전 주소의 처리 */
		String REFERER = (String) request.getHeader("REFERER");
		if (REFERER == null) {
			REFERER = "/";
		} else {
			REFERER = REFERER.replaceAll("http://www.coralprogram.com", "");
			if (REFERER.contains("?")) {
				REFERER = REFERER.split("\\?")[0];
			}
		}

		dto.setIp(request.getRemoteAddr());
		dto.setPlatform(request.getHeader("user-agent"));
		if (session.getAttribute("id") == null) {
			dto.setPw(Sha.get256((String) dto.getPw(), dto.getId()));
			dto.setLogin_status(1);
			dto = userService.login(dto, session);
			if (dto.getMsg() == null) {
				if (Boolean.valueOf(request.getParameter("remember_me"))) {
					/* 쿠키생성 */
					cookieService.create(response, session);
				}
			} else {
				REFERER += "?Code=alert('" + URLEncoder.encode(dto.getMsg(), "UTF-8") + "')";
			}
		} else {
			REFERER += "?Code=alert('" + URLEncoder.encode("이미 로그인중 입니다", "UTF-8") + "')";
		}
		return "redirect:" + REFERER;
	}

	/**
	 * 로그아웃 처리
	 * 
	 * @param request
	 * @param reponse
	 * @return
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse reponse)
			throws ParseException, UnsupportedEncodingException {
		/* 이전 주소의 처리 */
		String REFERER = (String) request.getHeader("REFERER");
		if (REFERER == null) {
			REFERER = "/";
		} else {
			REFERER = REFERER.replaceAll("http://www.coralprogram.com", "");
			if (REFERER.contains("?")) {
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

		return "redirect:" + REFERER;
	}
	
	/**
	 * 회원 탈퇴 처리
	 * 
	 * @param dto
	 * @param request
	 * @param newPw
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/leave")
	public String leave(UserDTO dto, HttpServletRequest request,@RequestParam(required=false) String newPw) throws Exception {
		dto.setId((String)request.getSession().getAttribute("id"));
		if(dto.getPw()!=null) {
			dto.setPw(Sha.get256((String) dto.getPw(), dto.getId()));
			if(!userService.isLogin(dto)) {
				dto.setMsg("비밀번호가 틀렸습니다");
			}else {
				dto.setStatus("탈퇴됨");
				if(!userService.updateStatus(dto)) {
					dto.setMsg("정보 수정에 실패했습니다");
				}else {
					request.getSession().invalidate();
					dto.setMsg("회원 탈퇴 처리 되었습니다");
				}
			}
		}
		
		return "redirect:/" + "?Code=alert('"
		+ URLEncoder.encode(dto.getMsg(), "UTF-8") + "')";
	}

	/**
	 * 이메일 인증
	 * 
	 * @param model
	 * @param id
	 * @param email
	 * @param authkey
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/emailConfirm")
	public String emailConfirm(Model model, @RequestParam String id, @RequestParam String email,
			@RequestParam String authkey) throws Exception {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setMail(email);
		dto.setMsg(authkey.substring(0, 16));
		return "redirect:/" + "?Code=alert('"
				+ URLEncoder.encode("이메일 인증에 " + (userService.mailVerify(dto) ? "성공" : "실패") + "했습니다!", "UTF-8") + "')";
	}

	/**
	 * 사용자 페이지
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/userpage")
	public String userPage(HttpServletRequest request,@RequestParam String id, Model model) throws UnsupportedEncodingException {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto = userService.getInfo(dto);
		if((request.getSession().getAttribute("grade")==null||!request.getSession().getAttribute("grade").equals("관리자"))&&dto.getPrivacy()!='O'&&!dto.getStatus().equals("탈퇴됨")) {
			return "redirect:/" + "?Code=alert('"+ URLEncoder.encode("조회 할 수 없는 회원입니다.", "UTF-8") + "')";
		}
		if (dto.getMail().contains("{verify-")) {
			dto.setMail("P");
		}
		dto.setDate(userService.lastLogin(dto.getId()));
		for (FileDTO profile : fileService.getAttachment(dto.getId())) {
			model.addAttribute("prof_image", profile.getPath());
		}
		model.addAttribute("userInfo", dto);

		boardService.addList(model, request, id);
		lectureService.addList(model, request, null, id);
		return "userPage";
	}

	/**
	 * 마이페이지
	 * 
	 * @param session
	 * @param model
	 * @param app
	 * @return
	 */
	@RequestMapping("/mypage")
	public String myPage(HttpSession session, Model model, @RequestParam(required=false) String app) {
		UserDTO dto = new UserDTO();
		dto.setId((String) session.getAttribute("id"));
		dto = userService.getInfo(dto);
		if (dto.getMail().contains("{verify-")) {
			dto.setMail("P");
		}
		dto.setDate(userService.lastLogin(dto.getId()));
		for (FileDTO profile : fileService.getAttachment(dto.getId())) {
			model.addAttribute("prof_image", profile.getPath());
		}
		model.addAttribute("userInfo", dto);
		model.addAttribute("app", app);
		return "myPage";
	}
	
	/**
	 * 스케쥴 페이지
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/myApp/schedule", method = RequestMethod.GET)
	public String scheduleGET(Model model, HttpSession session) {
		String schedule = "";
		for(ScheduleDTO dto:userService.scheduleSelectList((String) session.getAttribute("id"))) {
			schedule += dto.toString()+";";
		} 
		model.addAttribute("pre_schedule", schedule);
		return "myApplication/schedule";
	}
	
	/**
	 * 스케쥴 등록/삭제/수정
	 * 
	 * @param insert
	 * @param update
	 * @param delete
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/myApp/schedule", method = RequestMethod.POST)
	public String schedulePOST(@RequestParam String insert, @RequestParam String update, @RequestParam String delete, HttpSession session) {
		userService.scheduleInsertList(insert, (String) session.getAttribute("id"));
		userService.scheduleUpdateList(update, (String) session.getAttribute("id"));
		userService.scheduleDeleteList(delete, (String) session.getAttribute("id"));
		return "redirect:/mypage?app=schedule";
	}
	
	/**
	 * 개인정보 수정 페이지
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/myApp/private")
	public String map(Model model,  HttpSession session) {
		model.addAttribute("userInfo", userService.getInfo((String) session.getAttribute("id")));
		return "myApplication/private";
	}
	
	/**
	 * 강좌 관리 페이지
	 * 
	 * @return
	 */
	@RequestMapping("/myApp/lecture")
	public String lecture() {
		return "myApplication/lecture";
	}
	
	/**
	 * 활동 기록 페이지
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/myApp/history")
	public String history(Model model, HttpServletRequest request) {
		boardService.addList(model, request, (String) request.getSession().getAttribute("id"));
		lectureService.addList(model, request, null, (String) request.getSession().getAttribute("id"));
		model.addAttribute("List", userService.historyList((String) request.getSession().getAttribute("id")));
		return "myApplication/history";
	}
	
	/**
	 * 보안 관리 페이지
	 * 
	 * @return
	 */
	@RequestMapping("/myApp/security")
	public String security() {
		return "myApplication/security";
	}
	
	/**
	 * 활동 관리 페이지
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/myApp/active")
	public String active(Model model) {
		model.addAttribute("Category", boardService.categorylist(null));
		return "myApplication/active";
	}
	
	/**
	 * 카테고리 삽입
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping("/category/insert")
	public String insertCA(CategoryDTO dto) {
		boardService.insertCA(dto);
		return "redirect:/mypage?app=active";
	}
	
	/**
	 * 카테고리 삭제
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	@RequestMapping("/category/delete")
	public String deleteCA(@RequestParam String from, @RequestParam String to) {
		boardService.deleteCA(from,to);
		return "redirect:/mypage?app=active";
	}
	
	/**
	 * 카테고리 수정
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping("/category/update")
	public String updateCA(CategoryDTO dto) {
		boardService.updateCA(dto);
		return "redirect:/mypage?app=active";
	}
	
	/**
	 * 카테고리 이전
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	@RequestMapping("/category/move")
	public String moveCA(@RequestParam String from,@RequestParam String to) {
		boardService.moveCA(from,to);
		return "redirect:/mypage?app=active";
	}
	
	/**
	 * 폐강 신청관리 페이지
	 * 
	 * @param model
	 * @param request
	 * @param keyword
	 * @return
	 */
	@RequestMapping(value = "/myApp/apply", method = RequestMethod.GET)
	public String applyGET(Model model,HttpServletRequest request,@RequestParam(required=false)String keyword) {
		reportService.addList(model, request, keyword,"F");
		return "myApplication/apply";
	}
	
	/**
	 * 폐강 신청 처리
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/myApp/apply", method = RequestMethod.POST)
	public String applyPOST(ReportDTO dto) {
		reportService.closeClass(dto);
		return "redirect:/mypage?app=apply";
	}
	
	/**
	 * 신고 관리 페이지
	 * 
	 * @param model
	 * @param request
	 * @param keyword
	 * @return
	 */
	@RequestMapping(value = "/myApp/report", method = RequestMethod.GET)
	public String reportGET(Model model,HttpServletRequest request,@RequestParam(required=false)String keyword) {
		reportService.addList(model, request, keyword,"R");
		return "myApplication/report";
	}
	
	/**
	 * 신고 처리
	 * 
	 * @param dto
	 * @return
	 */
	@RequestMapping(value = "/myApp/report", method = RequestMethod.POST)
	public String reportPOST(ReportDTO dto) {
		reportService.punishment(dto);
		return "redirect:/mypage?app=report";
	}
	
	/**
	 * 사용자 정보 수정
	 * 
	 * @param dto
	 * @param request
	 * @param newPw
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/newInfo",method = { RequestMethod.POST })
	public String newInfo(UserDTO dto, HttpServletRequest request,@RequestParam(required=false) String newPw) throws Exception {
		dto.setId((String)request.getSession().getAttribute("id"));
		if(dto.getPw()!=null) {
			dto.setPw(Sha.get256((String) dto.getPw(), dto.getId()));
			if(!userService.isLogin(dto)||newPw==null||"".equals(newPw)) {
				return "redirect:/mypage" + "?Code=alert('"
						+ URLEncoder.encode("비밀번호가 틀렸습니다", "UTF-8") + "')";
			}else {
				dto.setPw(Sha.get256(newPw, dto.getId()));
			}
		}
		String mail = dto.getMail();
		if(mail!=null) {
			dto.setMail("{verify-" + Sha.get512(dto.getId(), dto.getMail()).substring(0, 16) + "}" + mail);
		}
		if(!userService.update(dto)) {
			return "redirect:/mypage" + "?Code=alert('"
					+ URLEncoder.encode("정보 수정에 실패했습니다", "UTF-8") + "')";
		}
		
		if(mail!=null) {
			dto.setMail(mail);
			userService.mail(dto);
		}
		return "redirect:/mypage" + "?Code=alert('"
		+ URLEncoder.encode("정보가 수정되었습니다", "UTF-8") + "')";
	}
	
	/** 구글 API 사용 정보 */
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	
	/** 구글 OAuth2 인증용 라이브러리 */
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;

	/**
	 * 로그인 페이지 이동
	 * 
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/googleLogin")
	public String gLogin(Model model, HttpSession session) throws Exception {
		/* 구글code 발행 */
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();

		/* 로그인페이지 이동 url생성 */
		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);

		return "redirect:"+url;
	}

	/**
	 * 구글 로그인 & 회원가입 처리
	 * 
	 * @param code
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/callBack", method = { RequestMethod.GET })
	public String gCallback(@RequestParam String code, HttpServletRequest request) throws IOException, ParseException {
		try {
			if(request.getSession().getAttribute("id")!=null) {
				return "redirect:/" + "?Code=alert('" + URLEncoder.encode("이미 로그인중 입니다", "UTF-8") + "')";
			}
			JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

			NetHttpTransport httpTransport = new NetHttpTransport();

			GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(httpTransport, jsonFactory,
					"https://oauth2.googleapis.com/token", "273138075636-a4r5k7hv9aa9uqb1fphcutatajt8na3l",
					"kJ1sGMEeayDDOzRcrd2cOSt8", code, "https://www.coralprogram.com/callBack").execute();

			GoogleCredential credential = new GoogleCredential().setAccessToken(tokenResponse.getAccessToken());
			List<String> scopes = new ArrayList<String>();

			scopes.add("https://www.googleapis.com/auth/user");
			scopes.add("https://www.googleapis.com/auth/userinfo");

			credential.createScoped(scopes);

			PeopleService peopleService = new PeopleService(new NetHttpTransport(), JacksonFactory.getDefaultInstance(),
					credential);

			Person person = peopleService.people().get("people/me")
					.setPersonFields("names,emailAddresses,birthdays,photos").execute();


			UserDTO dto = new UserDTO();
			if (person != null && person.size() > 0) {
				List<Name> names = person.getNames();
	            if (names != null && names.size() > 0) {
	            	dto.setId(names.get(0).getMetadata().getSource().getId());
	            	dto.setName(names.get(0).getDisplayName());
	            	dto.setPw("{google}");
	            	dto.setIp(request.getRemoteAddr());
	    			dto.setPlatform(request.getHeader("user-agent"));
	            	if(!userService.isId(dto.getId())) {
	                	List<EmailAddress> emailAddress = person.getEmailAddresses();
	                    if (emailAddress != null && emailAddress.size() > 0) {
	                    	dto.setMail(emailAddress.get(0).getValue());
	                    }
	                    List<Birthday> birthday = person.getBirthdays();
	                    Date birth = new Date();
	                    if (birthday != null && birthday.size() > 0) {
	                    	Calendar cal = Calendar.getInstance();
	                    	cal.set(birthday.get(0).getDate().getYear(),birthday.get(0).getDate().getMonth(),birthday.get(0).getDate().getDay());
	                    	birth = new Date(cal.getTimeInMillis());
	                    }
	                    dto.setBirth(birth);
	                    dto.setPhone("000-0000-0000");
	                    dto.setAddress("");
	        			dto.setCompany("");
	        			dto.setTel("");
	        			dto.setPrivacy('X');
	        			dto.setGender("et");
	        			dto.setGrade("학생");
	        			if(userService.addUser(dto)) {
	        				dto.setLogin_status(1);
	        				dto = userService.login(dto, request.getSession());
	        			}
	                }else {
	                	dto.setLogin_status(1);
	                	dto = userService.login(dto, request.getSession());
	                }
	            	List<Photo> photo = person.getPhotos();
	                if (photo != null && photo.size() > 0) {
	                	fileService.newProfImg(photo.get(0).getUrl(), dto.getId());
	                }
	            }
	            
	        } else {
	            System.out.println("No connections found.");
	        }
			if (dto.getMsg() != null) {
				return "redirect:/" + "?Code=alert('" + URLEncoder.encode(dto.getMsg(), "UTF-8") + "')";
			}
			return "redirect:/";
		}catch(Exception e) {return "redirect:/";}
		
	}

}
