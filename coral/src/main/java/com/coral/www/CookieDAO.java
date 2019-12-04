package com.coral.www;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class CookieDAO {
	@Inject
	SqlSession sqlSession;
	static final String namespace="com.coral.www.mappers.CookieMapper.";
	
	public boolean series(UserDTO dto) {
		int result = sqlSession.selectOne(namespace+"selectSeries",dto);
		return result==1?true:false;
	}
	public boolean token(UserDTO dto) {
		int result = sqlSession.selectOne(namespace+"selectToken",dto);
		return result==1?true:false;
	}
	public boolean insert(UserDTO dto) {
		return sqlSession.insert(namespace+"insert", dto)==1?true:false;
		
	}
	public boolean update(UserDTO dto) {
		return sqlSession.update(namespace+"update", dto)==1?true:false;
		
	}
	public boolean delete(UserDTO dto) {
		return sqlSession.delete(namespace+"delete", dto)==1?true:false;
		
	}
}
