/* 
 * ReplyService.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.like;

import java.util.List;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
public interface ReplyService {
	/* 댓글 서비스 인터페이스  */
	
	/**
	 * 댓글 전송
	 * 
	 * @param bno
	 * @param id
	 * @param content
	 * @return
	 */
	public boolean send(String bno, String id, String content);
	
	/**
	 * 댓글 목록
	 * 
	 * @param bno
	 * @return
	 */
	public List<ReplyDTO> getList(String bno);
	
	/**
	 * 댓글 수정
	 * 
	 * @param dto
	 * @return
	 */
	public boolean update(ReplyDTO dto);
}

