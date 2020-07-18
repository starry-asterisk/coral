/* 
 * LikeServiceImpl.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.like;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
@Service
public class LikeServiceImpl implements LikeService{
	/* 좋아요 서비스  */
	
	/** DAO */
	@Inject
	LikeDAO dao;
	
	@Override
	public LikeDTO isLiked(String no, String id, int div){
		LikeDTO dto = new LikeDTO();
		dto.setDiv(div);
		dto.setId(id);
		dto.setNo(no);
		List<LikeDTO> list = dao.select(dto);
		if(list.size()>=1) {
			dto = null;
		}
		return dto;
	}
	
	@Override
	public boolean insert(LikeDTO dto){
		return dao.insert(dto);
	}
}
