/* 
 * ReportService.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.Report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
public interface ReportService {
	/* 신고 서비스 인터페이스  */
	
	/**
	 * 사유 목록
	 * 
	 * @param identifier
	 * @return
	 */
	public List<ReasonDTO> reasonList(char identifier);
	
	/**
	 * 신고 생성
	 * 
	 * @param dto
	 * @return
	 */
	public ReportDTO insertReport(ReportDTO dto);
	
	/**
	 * 처벌 생성
	 * 
	 * @param dto
	 * @return
	 */
	public boolean punishment(ReportDTO dto);
	
	/**
	 * 신고 목록
	 * 
	 * @param model
	 * @param request
	 * @param keyword
	 * @param rscode
	 */
	void addList(Model model, HttpServletRequest request, String keyword, String rscode);
	
	/**
	 * 폐강 처리
	 * 
	 * @param dto
	 * @return
	 */
	boolean closeClass(ReportDTO dto);
}
