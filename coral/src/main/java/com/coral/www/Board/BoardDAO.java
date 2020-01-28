package com.coral.www.Board;


import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.coral.www.like.LikeDTO;


@Repository
public class BoardDAO {
	@Inject
	SqlSession sqlSession;
	static final String namespace="com.coral.www.mappers.BoardMapper.";
	
	public int total(BoardDTO dto) {
		return sqlSession.selectOne(namespace+"total",dto);
	}
	public List<BoardDTO> listPage(BoardDTO dto) {
		return sqlSession.selectList(namespace+"listPage",dto);
	}
	public List<CategoryDTO> categorylist(String permission) {
		return sqlSession.selectList(namespace+"categorylist",permission);
	}
	public boolean write(BoardDTO dto) {
		int result = sqlSession.insert(namespace+"write",dto);
		return result==1;
	}
	public BoardDTO detail(String no) {
		return sqlSession.selectOne(namespace+"detail",no);
	}
	public String newBno() {
		return sqlSession.selectOne(namespace+"newBno");
	}
	public boolean viewCntUpd(String no) {
		int result = sqlSession.update(namespace+"viewCntUpd",no);
		return result==1;
	}
	public boolean likeCntUpd(LikeDTO dto) {
		int result = sqlSession.update(namespace+"likeCntUpd",dto);
		return result==1;
	}
	public boolean update(BoardDTO dto) {
		int result = sqlSession.update(namespace+"update",dto);
		return result==1;
	}
	public int deleteAll(String id) {
		return sqlSession.update(namespace+"deleteAll",id);
	}
	public boolean delete(String no) {
		int result = sqlSession.update(namespace+"delete",no);
		return result>0;
	}
}
