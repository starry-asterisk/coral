package com.coral.www.User;

import java.util.Date;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAO {
	@Inject
	SqlSession sqlSession;
	static final String namespace="com.coral.www.mappers.UsersMapper.";
	
	public boolean isLogin(UserDTO dto) {
		int result = sqlSession.selectOne(namespace+"isLogin",dto);
		return result==1?true:false;
	}
	public boolean isId(UserDTO dto) {
		int result = sqlSession.selectOne(namespace+"isId",dto);
		return result==1?true:false;
	}
	public boolean isMail(UserDTO dto) {
		int result = sqlSession.selectOne(namespace+"isMail",dto);
		return result==1?true:false;
	}
	public UserDTO getInfo(UserDTO dto) {
		return sqlSession.selectOne(namespace+"getInfo",dto);
	}
	public boolean insertHistory(UserDTO dto) {
		return sqlSession.insert(namespace+"insertHistory", dto)==1?true:false;
		
	}
	public boolean addUser(UserDTO dto) {
		return sqlSession.insert(namespace+"insertUser", dto)==1?true:false;
		
	}
	
	public boolean mailVerify(UserDTO dto) {
		return sqlSession.update(namespace+"mailVerify", dto)==1?true:false;
		
	}
	public UserDTO lastLogin(String id) {
		return sqlSession.selectOne(namespace+"lastLogin",id);
	}
}
