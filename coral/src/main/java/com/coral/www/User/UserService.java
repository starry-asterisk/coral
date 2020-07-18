/* 
 * UserService.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.User;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
public interface UserService {
	/* 이용자 서비스 인터페이스  */
	
	/**
	 * 이용자 목록
	 * 
	 * @param model
	 * @param request
	 * @param keyword
	 */
	public void addList(Model model, HttpServletRequest request, String keyword);
	
	/**
	 * 이용자 정보 by dto
	 * 
	 * @param dto
	 * @return
	 */
	public UserDTO getInfo(UserDTO dto);
	
	/**
	 * 이용자 정보 by id
	 * 
	 * @param id
	 * @return
	 */
	public UserDTO getInfo(String id);
	
	/**
	 * 로그인 처리
	 * 
	 * @param dto
	 * @param session
	 * @return
	 */
	public UserDTO login(UserDTO dto, HttpSession session);
	
	/**
	 * 로그인 기록 삽입
	 * 
	 * @param dto
	 * @return
	 */
	public UserDTO insertHistory(UserDTO dto);
	
	/**
	 * 아이디 중복 체크
	 * 
	 * @param id
	 * @return
	 */
	public boolean isId(String id);
	
	/**
	 * 계정 생성
	 * 
	 * @param dto
	 * @return
	 */
	public boolean addUser(UserDTO dto);
	
	/**
	 * 메일 중복 체크
	 * 
	 * @param dto
	 * @return
	 */
	public boolean isMail(UserDTO dto);
	
	/**
	 * 인증 메일 전송
	 * 
	 * @param dto
	 * @throws Exception
	 */
	public void mail(UserDTO dto) throws Exception;
	
	/**
	 * 메일 활성화
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public boolean mailVerify(UserDTO dto) throws Exception;
	
	/**
	 * 마지막 로그인 기록
	 * 
	 * @param id
	 * @return
	 */
	public Date lastLogin(String id);

	/**
	 * 로그인 정보 확인
	 * 
	 * @param dto
	 * @return
	 */
	public boolean isLogin(UserDTO dto);
	
	/**
	 * 로그인 기록 목록
	 * 
	 * @param id
	 * @return
	 */
	public List<UserDTO> historyList(String id);
	
	/**
	 * 계정 정보 수정
	 * 
	 * @param dto
	 * @return
	 */
	public boolean update(UserDTO dto);
	
	/**
	 * 계정 상태 변경
	 * 
	 * @param dto
	 * @return
	 */
	public boolean updateStatus(UserDTO dto);
	
	/**
	 * 스케쥴 추가
	 * 
	 * @param insert
	 * @param id
	 * @return
	 */
	public boolean scheduleInsertList(String insert, String id);
	
	/**
	 * 스케쥴 수정
	 * 
	 * @param update
	 * @param id
	 * @return
	 */
	public boolean scheduleUpdateList(String update, String id);
	
	/**
	 * 스케쥴 삭제
	 * 
	 * @param delete
	 * @param id
	 * @return
	 */
	public boolean scheduleDeleteList(String delete, String id);
	
	/**
	 * 스케쥴 가져오기
	 * 
	 * @param id
	 * @return
	 */
	public ScheduleDTO scheduleSelect(String id);
	
	/**
	 * 스케쥴 목록
	 * 
	 * @param id
	 * @return
	 */
	public List<ScheduleDTO> scheduleSelectList(String id);
}
