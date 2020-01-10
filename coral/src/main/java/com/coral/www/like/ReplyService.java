package com.coral.www.like;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class ReplyService {
	@Inject
	ReplyDAO dao;
	
	public boolean send(String bno, String id, String content) {
		ReplyDTO dto = new ReplyDTO();
		dto.setBno(bno);
		dto.setId(id);
		dto.setContent(content);
		dto.setNo(dao.count(bno));
		return dao.insert(dto);
	}

	public List<ReplyDTO> getList(String bno) {
		return dao.select(bno);
	}

	public boolean update(ReplyDTO dto) {
		return dao.update(dto);
	}

}
