/* 
 * LikeService.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.like;

import org.springframework.stereotype.Service;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
@Service
public interface LikeService {
	/* 좋아요 서비스 인터페이스  */
	
	/**
	 * 좋아요 기록 조회
	 * 
	 * @param no
	 * @param id
	 * @param div
	 * @return
	 */
	public LikeDTO isLiked(String no, String id, int div);
	
	/**
	 * 좋아요 기록 삽입
	 * 
	 * @param dto
	 * @return
	 */
	public boolean insert(LikeDTO dto);
}
