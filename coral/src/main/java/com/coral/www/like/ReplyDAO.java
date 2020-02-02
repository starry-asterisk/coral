/* 
 * ReplyDAO.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.like;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
@Repository
public class ReplyDAO {
	/* 댓글 Data Access Object */
	
	@Inject
	SqlSession sqlSession;
	
	static final String namespace="com.coral.www.mappers.ReplyMapper.";
	
	public boolean insert(ReplyDTO dto) {
		return (int)sqlSession.insert(namespace+"insert",dto)==1;
	}
	
	public boolean update(ReplyDTO dto) {
		return (int)sqlSession.update(namespace+"update",dto)==1;
	}
	
	public List<ReplyDTO> select(String bno) {
		return sqlSession.selectList(namespace+"select",bno);
	}
	
	public int count(String bno) {
		return sqlSession.selectOne(namespace+"count",bno);
	}
	
	public int deleteAll(String id) {
		return sqlSession.update(namespace+"deleteAll",id);
	}
	
	public int delete(ReplyDTO dto) {
		return sqlSession.update(namespace+"delete",dto);
	}
}
