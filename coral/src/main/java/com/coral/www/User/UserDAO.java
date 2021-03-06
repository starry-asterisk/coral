package com.coral.www.User;


import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAO {
	@Inject
	SqlSession sqlSession;
	static final String namespace="com.coral.www.mappers.UsersMapper.";
	
	public int total(UserDTO dto) {
		return sqlSession.selectOne(namespace+"total",dto);
	}
	public List<UserDTO> listPage(UserDTO dto) {
		return sqlSession.selectList(namespace+"listPage",dto);
	}
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
	public boolean isBan(String id) {
		return (int)sqlSession.selectOne(namespace+"isBan",id)>0;
	}
	public List<UserDTO> historyList(String id) {
		return sqlSession.selectList(namespace+"historyList",id);
	}
	public boolean update(UserDTO dto) {
		int result = sqlSession.update(namespace+"update", dto);
		return result==1?true:false;
	}
	public boolean updateStatus(UserDTO dto) {
		int result = sqlSession.update(namespace+"updateStatus", dto);
		return result==1?true:false;
	}
	public boolean scheduleInsert(ScheduleDTO dto) {
		int result = sqlSession.insert(namespace+"scheduleInsert", dto);
		return result>0;
	}
	public boolean scheduleUpdate(ScheduleDTO dto) {
		int result = sqlSession.update(namespace+"scheduleUpdate", dto);
		return result>0;
	}
	public boolean scheduleDelete(ScheduleDTO dto) {
		int result = sqlSession.delete(namespace+"scheduleDelete", dto);
		return result>0;
	}
	public ScheduleDTO scheduleSelect(String id) {
		return sqlSession.selectOne(namespace+"scheduleSelect", id);
	}
	public List<ScheduleDTO> scheduleSelectList(String id) {
		return sqlSession.selectList(namespace+"scheduleSelectList", id);
	}
}
