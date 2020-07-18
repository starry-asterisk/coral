/* 
 * LectureService.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.Lecture;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
public interface LectureService {
	/* 강좌&강의 서비스 인터페이스  */
	
	/**
	 * 강좌&강의 목록 불러오기
	 * 
	 * @param model
	 * @param request
	 * @param cl_no
	 * @param keyword
	 */
	public void addList(Model model, HttpServletRequest request, String cl_no, String keyword);
	
	/**
	 * 강의 상세보기
	 * 
	 * @param no
	 * @param isView
	 * @return
	 */
	public LectureDTO detail(String no, boolean isView);
	
	/**
	 * 좋아요 수정
	 * 
	 * @param no
	 * @param div
	 * @return
	 */
	public boolean likeUpd(String no, int div);
	
	/**
	 * 강의 업로드
	 * 
	 * @param dto
	 * @return
	 */
	public String write(LectureDTO dto);
	
	/**
	 * 강좌 개설
	 * 
	 * @param dto
	 * @return
	 */
	public boolean create(LectureDTO dto);
	
	/**
	 * 강의 수정
	 * 
	 * @param dto
	 * @return
	 */
	public String update(LectureDTO dto);
	
	/**
	 * 강좌 정보 수정
	 * 
	 * @param dto
	 * @return
	 */
	public boolean updateCL(LectureDTO dto);
	
	/**
	 * 본인 강좌 존재 여부
	 * 
	 * @param dto
	 * @return
	 */
	public boolean CLExit(LectureDTO dto);
	
	/**
	 * 전체 강좌 존재 여부
	 * 
	 * @param cl_no
	 * @return
	 */
	public boolean isCL(String cl_no);
	
	/**
	 * 본인 강의 존재여부
	 * 
	 * @param dto
	 * @return
	 */
	public boolean LExit(LectureDTO dto);
}
