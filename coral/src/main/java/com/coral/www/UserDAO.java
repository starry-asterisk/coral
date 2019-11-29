package com.coral.www;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAO {
	@Inject
	SqlSession sqlSession;
	static final String namespace="com.coral.www.mappers.UserMapper.";
	
	public boolean isLogin(UserDTO dto) {
		System.out.println("asdf3");
		int result = sqlSession.selectOne(namespace+"isLogin",dto);
		return result==1?true:false;
	}
	public UserDTO getInfo(UserDTO dto) {
		System.out.println("asdf4");
		return sqlSession.selectOne(namespace+"getInfo",dto);
	}
	public boolean insertHistory(UserDTO dto) {
		System.out.println("asdf5");
		return sqlSession.insert(namespace+"insertHistory", dto)==1?true:false;
		
	}
}
