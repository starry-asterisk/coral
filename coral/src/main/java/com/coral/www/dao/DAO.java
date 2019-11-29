package com.coral.www.dao;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class DAO {
	@Inject
	SqlSession sqlSession;
	static final String namespace="com.coral.www.mappers.BoardMapper.";
	
	public String getCount() {
		int result = sqlSession.selectOne(namespace+"Count");
		return "결과 는 :"+result;
	}
}
