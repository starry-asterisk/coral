package com.coral.www.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coral.www.application.Sha;

@Service
public class UserServiceImpl implements UserService {
	@Inject
	UserDAO dao;
	
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
	public UserDTO login(UserDTO dto) {
		try {
			if(dao.isId(dto)) {
				if(dao.isLogin(dto)||(dto.getLogin_status()==-1)) {
					dao.insertHistory(dto);
					dto = dto.getLogin_status()==1?dao.getInfo(dto):null;
				}else {
					dto.setLogin_status(0);
					dao.insertHistory(dto);
					dto.setMsg("비밀번호 오류");
				}
			}else {
				dto.setMsg("Id 오류");
			}
			return dto;
		}catch(Exception e) {
			dto.setMsg("부정한 로그인 시도");
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
	public boolean newUser(UserDTO dto) {
		return dao.newUser(dto);
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
		if(dao.Verify(dto)&&dao.Verify_success(dto)) {
			return true;
		}else {
			return false;
		}
	}
}
