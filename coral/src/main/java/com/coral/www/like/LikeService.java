package com.coral.www.like;


import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class LikeService {
	@Inject
	LikeDAO dao;
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
	public boolean insert(LikeDTO dto){
		return dao.insert(dto);
	}
}
