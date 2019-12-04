package com.coral.www.Cookie;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class CookieDAO {
	@Inject
	SqlSession sqlSession;
	static final String namespace="com.coral.www.mappers.CookieMapper.";
	
	public boolean series(CookieDTO dto) {
		int result = sqlSession.selectOne(namespace+"selectSeries",dto);
		return result==1?true:false;
	}
	public CookieDTO token(CookieDTO dto) {
		return sqlSession.selectOne(namespace+"selectToken",dto);
	}
	public boolean insert(CookieDTO dto) {
		return sqlSession.insert(namespace+"insert", dto)==1?true:false;
		
	}
	public boolean update(CookieDTO dto) {
		return sqlSession.update(namespace+"update", dto)==1?true:false;
		
	}
	public boolean delete(CookieDTO dto) {
		return sqlSession.delete(namespace+"delete", dto)==1?true:false;
		
	}
}
