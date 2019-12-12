package com.coral.www.Board;


import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class BoardDAO {
	@Inject
	SqlSession sqlSession;
	static final String namespace="com.coral.www.mappers.BoardMapper.";
	
	public int total() {
		return sqlSession.selectOne(namespace+"total");
	}
	public List<BoardDTO> listPage(BoardDTO dto) {
		return sqlSession.selectList(namespace+"listPage",dto);
	}
}
