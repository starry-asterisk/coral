package com.coral.www.like;

import java.util.List;

public interface ReplyService {
	
	public boolean send(String bno, String id, String content);
	public List<ReplyDTO> getList(String bno);
	public boolean update(ReplyDTO dto);
}

