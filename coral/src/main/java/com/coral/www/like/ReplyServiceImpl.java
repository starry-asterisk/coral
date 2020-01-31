package com.coral.www.like;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService{
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
