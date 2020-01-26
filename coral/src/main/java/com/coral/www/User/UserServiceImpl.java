package com.coral.www.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@Service
public class UserServiceImpl implements UserService {
	@Inject
	UserDAO dao;
	
	@Override
	public void addList(Model model, HttpServletRequest request, String keyword) {
		UserDTO dto = new UserDTO();
		if(keyword!=null) {
			model.addAttribute("keyword", keyword);
			dto.setId(keyword);
			dto.setName(keyword);
			dto.setGrade(keyword);
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
		try {
			dto = dao.getInfo(dto);
		}catch(Exception e) {
			e.printStackTrace();
			dto = null;
		}
		return dto;
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
							session.setAttribute("id", dto.getId());
							session.setAttribute("user-agent", dto.getPlatform());
							session.setAttribute("ip", dto.getIp());
							session.setAttribute("grade", dao.getInfo(dto).getGrade());
							break;
						case "정지중":
							dto.setMsg("정지된 회원입니다");
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
	public boolean checkGrade(String id, String grade) {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		Map<String,Integer> gradeMap = new HashMap<String,Integer>();
		gradeMap.put("관리자", 3);
		gradeMap.put("교사", 2);
		gradeMap.put("학생", 1);
		return gradeMap.get(dao.getInfo(dto).getGrade())>=gradeMap.get(grade);
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
	@Inject
	LectureDAO ldao;
	@Inject
	BoardDAO bdao;
	@Inject
	ReplyDAO rdao;
	@Override
	public boolean updateStatus(UserDTO dto) {
		if(dto.getStatus().equals("탈퇴됨")) {
			ldao.deleteAll(dto.getId());
			bdao.deleteAll(dto.getId());
			rdao.deleteAll(dto.getId());
		}
		return dao.updateStatus(dto);
	}
}
