/* 
 * UserServiceImpl.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.coral.www.Board.BoardDAO;
import com.coral.www.Lecture.LectureDAO;
import com.coral.www.application.MailUtils;
import com.coral.www.application.Sha;
import com.coral.www.like.ReplyDAO;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
@Service
public class UserServiceImpl implements UserService {
	/* 유저 서비스  */
	
	/** 계정 DAO */
	@Inject
	UserDAO dao;
	
	/** 강좌&강의 DAO */
	@Inject
	LectureDAO ldao;
	
	/** 게시판 DAO */
	@Inject
	BoardDAO bdao;
	
	/** 댓글 DAO */
	@Inject
	ReplyDAO rdao;
	
	@Override
	public void addList(Model model, HttpServletRequest request, String keyword) {
		UserDTO dto = new UserDTO();
		if(keyword!=null) {
			model.addAttribute("keyword", keyword);
			dto.setId(keyword);
			dto.setName(keyword);
			dto.setGrade(keyword);
		}
		if(request.getSession().getAttribute("grade")!=null&&request.getSession().getAttribute("grade").equals("관리자")) {
			dto.setPrivacy('O');
		}
		dto.setPage(request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page")));
		dto.setAmount(request.getParameter("amount")==null?50:Integer.parseInt(request.getParameter("amount")));
		model.addAttribute("U_amount", dto.getAmount());
		model.addAttribute("U_Endpage", (int) Math.ceil((double)dao.total(dto)/(double)dto.getAmount()));
		model.addAttribute("U_Currentpage", dto.getPage());
		model.addAttribute("UserList", dao.listPage(dto));
	}
	
	@Override
	public UserDTO getInfo(UserDTO dto) {
		try {
			dto = dao.getInfo(dto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	@Override
	public UserDTO getInfo(String id) {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		return getInfo(dto);
	}
	
	@Override
	public UserDTO login(UserDTO dto, HttpSession session) {
		try {
			if(dao.isId(dto)) {
				if(dto.getLogin_status()!=-1&&!dao.isLogin(dto)) {
					dto.setLogin_status(0);
					dao.insertHistory(dto);
					dto.setMsg("비밀번호 오류");
				}else if(dao.insertHistory(dto) && session!=null){
					/*로그인 정보 저장*/
					switch(dao.getInfo(dto).getStatus()) {
						case "활동중":
							if(!dao.isBan(dto.getId())) {
								session.setAttribute("id", dto.getId());
								session.setAttribute("user-agent", dto.getPlatform());
								session.setAttribute("ip", dto.getIp());
								session.setAttribute("grade", dao.getInfo(dto).getGrade());
							}else {
								UserDTO Rdto = new UserDTO();
								Rdto.setId(dto.getId());
								Rdto.setStatus("정지중");
								dao.update(Rdto);
								dto.setMsg("정지된 회원입니다");
							}
							break;
						case "정지중":
							if(dao.isBan(dto.getId())) {
								dto.setMsg("정지된 회원입니다");
							}else {
								session.setAttribute("id", dto.getId());
								session.setAttribute("user-agent", dto.getPlatform());
								session.setAttribute("ip", dto.getIp());
								session.setAttribute("grade", dao.getInfo(dto).getGrade());
								UserDTO Rdto = new UserDTO();
								Rdto.setId(dto.getId());
								Rdto.setStatus("활동중");
								dao.update(Rdto);
							}
							break;
						case "탈퇴됨":
							dto.setMsg("탈퇴된 회원입니다");
							break;
						default:
							break;
					}
				}
			}else {
				dto.setMsg("Id 오류");
			}
			return dto;
		}catch(Exception e) {
			dto.setMsg("부정한 로그인 시도");
			session.removeAttribute("id");
			session.removeAttribute("ip");
			session.removeAttribute("grade");
			session.removeAttribute("user-agent");
			session.invalidate();
			return dto;
		}
		
	}
	
	@Override
	public UserDTO insertHistory(UserDTO dto) {
		try {
			if(!dao.insertHistory(dto)) {
				dto=null;
			};
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	@Override
	public boolean isId(String id) {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		return dao.isId(dto);
	}
	
	@Override
	public boolean addUser(UserDTO dto) {
		return dao.addUser(dto);
	}
	
	@Override
	public boolean isLogin(UserDTO dto) {
		return dao.isLogin(dto);
	}
	
	@Override
	public boolean isMail(UserDTO dto) {
		return dao.isMail(dto);
	}

	@Override
	@Transactional
	public void mail(UserDTO dto) throws Exception {
	
		// 임의의 authkey 생성
		String authkey = Sha.get512(dto.getId(), dto.getMail());

		// mail 작성 관련 
		MailUtils sendMail = new MailUtils();
		
		//파일 객체 생성
        File file = new File("C:\\coding\\rel\\mailVerification.html");
        //입력 스트림 생성
        FileReader filereader = new FileReader(file);
        //입력 버퍼 생성
        BufferedReader bufReader = new BufferedReader(filereader);
        String line;
        String WLine = "";
        while((line = bufReader.readLine()) != null){
        	WLine += line;
        }
        //.readLine()은 끝에 개행문자를 읽지 않는다.            
        bufReader.close();
        
        WLine = WLine.replace("[link]","https://www.coralprogram.com/emailConfirm?id="
				+ dto.getId()
				+"&email="
				+dto.getMail()
				+"&authkey="
				+authkey
				);
        
		sendMail.setSubject("회원가입 이메일 인증");
		sendMail.setText(WLine);
		sendMail.setTo(dto.getMail());
		sendMail.send();
	}
	
	@Override
	public boolean mailVerify(UserDTO dto) throws Exception{
		return dao.mailVerify(dto);
	}

	@Override
	public Date lastLogin(String id) {
		return dao.lastLogin(id).getDate();
	}
	
	@Override
	public List<UserDTO> historyList(String id) {
		return dao.historyList(id);
	}
	
	@Override
	public boolean update(UserDTO dto) {
		return dao.update(dto);
	}
	
	@Override
	public boolean updateStatus(UserDTO dto) {
		if(dto.getStatus().equals("탈퇴됨")) {
			ldao.deleteAll(dto.getId());
			bdao.deleteAll(dto.getId());
			rdao.deleteAll(dto.getId());
		}
		return dao.updateStatus(dto);
	}
	
	@Override
	public boolean scheduleInsertList(String line, String id) {
		boolean result = true;;
		if(line!=null&&!line.equals("")) {
			String[] lines = line.split(";");
			System.out.println(line+": ins");
			for(String one:lines) {
				if(one.length()>1) {
					ScheduleDTO dto = new ScheduleDTO();
					Calendar cal = Calendar.getInstance();
					String[] unit = one.split("/");
					cal.set(Integer.parseInt(unit[0]), Integer.parseInt(unit[1]), Integer.parseInt(unit[2]), 0, 0, 0);
					cal.setTimeInMillis(cal.getTimeInMillis()/1000*1000);
					dto.setStart(new Date(cal.getTimeInMillis()));
					cal.set(Integer.parseInt(unit[3]), Integer.parseInt(unit[4]), Integer.parseInt(unit[5]), 0, 0, 0);
					cal.setTimeInMillis(cal.getTimeInMillis()/1000*1000);
					dto.setEnd(new Date(cal.getTimeInMillis()));
					dto.setColor(unit[6]);
					dto.setName(unit[7]);
					dto.setContents(unit[8]);
					dto.setId(id);
					result = result&&dao.scheduleInsert(dto);
				}
			}
		}
		return result;
	}
	
	@Override
	public boolean scheduleUpdateList(String line, String id) {
		boolean result = true;;
		if(line!=null&&!line.equals("")) {
			String[] lines = line.split(";");
			System.out.println(line+": upd");
			for(String one:lines) {
				if(one.length()>1) {
					ScheduleDTO dto = new ScheduleDTO();
					Calendar cal = Calendar.getInstance();
					String[] unit = one.split("/");
					cal.set(Integer.parseInt(unit[0]), Integer.parseInt(unit[1]), Integer.parseInt(unit[2]), 0, 0, 0);
					cal.setTimeInMillis(cal.getTimeInMillis()/1000*1000);
					dto.setStart(new Date(cal.getTimeInMillis()));
					cal.set(Integer.parseInt(unit[3]), Integer.parseInt(unit[4]), Integer.parseInt(unit[5]), 0, 0, 0);
					cal.setTimeInMillis(cal.getTimeInMillis()/1000*1000);
					dto.setEnd(new Date(cal.getTimeInMillis()));
					dto.setColor(unit[6]);
					dto.setName(unit[7]);
					dto.setContents(unit[8]);
					dto.setId(id);
					result = result&&dao.scheduleUpdate(dto);
				}
			}
		}
		return result;
	}
	
	@Override
	public boolean scheduleDeleteList(String line, String id) {
		boolean result = true;;
		if(line!=null&&!line.equals("")) {
			String[] lines = line.split(";");
			System.out.println(line+": del");
			for(String one:lines) {
				if(one.length()>1) {
					ScheduleDTO dto = new ScheduleDTO();
					Calendar cal = Calendar.getInstance();
					String[] unit = one.split("/");
					cal.set(Integer.parseInt(unit[0]), Integer.parseInt(unit[1]), Integer.parseInt(unit[2]), 0, 0, 0);
					cal.setTimeInMillis(cal.getTimeInMillis()/1000*1000);
					dto.setStart(new Date(cal.getTimeInMillis()));
					cal.set(Integer.parseInt(unit[3]), Integer.parseInt(unit[4]), Integer.parseInt(unit[5]), 0, 0, 0);
					cal.setTimeInMillis(cal.getTimeInMillis()/1000*1000);
					dto.setEnd(new Date(cal.getTimeInMillis()));
					dto.setColor(unit[6]);
					dto.setName(unit[7]);
					dto.setContents(unit[8]);
					dto.setId(id);
					result = result&&dao.scheduleDelete(dto);
				}
			}
		}
		return result;
	}
	
	@Override
	public ScheduleDTO scheduleSelect(String id) {
		return dao.scheduleSelect(id);
	}
	
	@Override
	public List<ScheduleDTO> scheduleSelectList(String id) {
		return dao.scheduleSelectList(id);
	}
}
