package com.coral.www.like;


import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class LikeDAO {
	@Inject
	SqlSession sqlSession;
	static final String namespace="com.coral.www.mappers.LikeMapper.";
	
	public boolean insert(LikeDTO dto) {
		int result =  sqlSession.insert(namespace+"insert",dto);
		return result==1;
	}
	public List<LikeDTO> select(LikeDTO dto) {
		return sqlSession.selectList(namespace+"select",dto);
	}
}
