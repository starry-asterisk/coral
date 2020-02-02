/* 
 * CookieDAO.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.Cookie;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
@Repository
public class CookieDAO {
	/* 쿠키 Data Access Object */
	
	@Inject
	SqlSession sqlSession;

	static final String namespace="com.coral.www.mappers.CookieMapper.";
	
	public boolean series(CookieDTO dto) {
		return (int)sqlSession.selectOne(namespace+"selectSeries",dto)==1?true:false;
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
