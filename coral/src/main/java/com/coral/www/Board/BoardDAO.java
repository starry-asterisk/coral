/* 
 * BoardDAO.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.Board;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.coral.www.like.LikeDTO;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
@Repository
public class BoardDAO {
	/* 게시판 Data Access Object */
	
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
		return (int)sqlSession.insert(namespace+"write",dto)==1;
	}
	
	public BoardDTO detail(String no) {
		return sqlSession.selectOne(namespace+"detail",no);
	}
	
	public String newBno() {
		return sqlSession.selectOne(namespace+"newBno");
	}
	
	public boolean viewCntUpd(String no) {
		return (int)sqlSession.update(namespace+"viewCntUpd",no)==1;
	}
	
	public boolean likeCntUpd(LikeDTO dto) {
		return (int)sqlSession.update(namespace+"likeCntUpd",dto)==1;
	}
	
	public boolean update(BoardDTO dto) {
		return (int)sqlSession.update(namespace+"update",dto)==1;
	}
	
	public int deleteAll(String id) {
		return sqlSession.update(namespace+"deleteAll",id);
	}
	
	public boolean delete(String no) {
		return (int)sqlSession.update(namespace+"delete",no)>0;
	}
	
	public boolean updateCA(CategoryDTO dto) {
		return (int)sqlSession.update(namespace+"updateCA",dto)==1;
	}
	
	public boolean insertCA(CategoryDTO dto) {
		return (int)sqlSession.update(namespace+"insertCA",dto)==1;
	}
	
	public boolean deleteCA(String code) {
		return (int)sqlSession.update(namespace+"deleteCA",code)==1;
	}
	
	public boolean moveCA(CategoryDTO dto) {
		return (int)sqlSession.update(namespace+"moveCA",dto)>0;
	}
	
}
