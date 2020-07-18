/* 
 * ReplyServiceImpl.java		1.0.0 2020.02.02
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
public class ReplyServiceImpl implements ReplyService{
	/* 댓글 서비스  */
	
	/** DAO */
	@Inject
	ReplyDAO dao;
	
	@Override
	public boolean send(String bno, String id, String content) {
		ReplyDTO dto = new ReplyDTO();
		dto.setBno(bno);
		dto.setId(id);
		dto.setContent(content);
		dto.setNo(dao.count(bno));
		return dao.insert(dto);
	}

	@Override
	public List<ReplyDTO> getList(String bno) {
		return dao.select(bno);
	}

	@Override
	public boolean update(ReplyDTO dto) {
		return dao.update(dto);
	}

}
