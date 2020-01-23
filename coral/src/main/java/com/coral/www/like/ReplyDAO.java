
package com.coral.www.like;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
@Repository
public class ReplyDAO {
	@Inject
	SqlSession sqlSession;
	static final String namespace="com.coral.www.mappers.ReplyMapper.";
	
	public boolean insert(ReplyDTO dto) {
		int result =  sqlSession.insert(namespace+"insert",dto);
		return result==1;
	}
	public boolean update(ReplyDTO dto) {
		int result =  sqlSession.update(namespace+"update",dto);
		return result==1;
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
}
