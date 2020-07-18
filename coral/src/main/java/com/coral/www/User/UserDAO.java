/* 
 * UserDAO.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.User;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
@Repository
public class UserDAO {
	/* 이용자 Data Access Object */
	
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
		return (int)sqlSession.selectOne(namespace+"isLogin",dto)==1;
	}
	
	public boolean isId(UserDTO dto) {
		return (int)sqlSession.selectOne(namespace+"isId",dto)==1;
	}
	
	public boolean isMail(UserDTO dto) {
		return (int)sqlSession.selectOne(namespace+"isMail",dto)==1;
	}
	
	public UserDTO getInfo(UserDTO dto) {
		return sqlSession.selectOne(namespace+"getInfo",dto);
	}
	
	public boolean insertHistory(UserDTO dto) {
		return sqlSession.insert(namespace+"insertHistory", dto)==1;
	}
	
	public boolean addUser(UserDTO dto) {
		return sqlSession.insert(namespace+"insertUser", dto)==1;
	}
	public boolean mailVerify(UserDTO dto) {
		return sqlSession.update(namespace+"mailVerify", dto)==1;
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
		return sqlSession.update(namespace+"update", dto)==1;
	}
	
	public boolean updateStatus(UserDTO dto) {
		return sqlSession.update(namespace+"updateStatus", dto)==1;
	}
	
	public boolean scheduleInsert(ScheduleDTO dto) {
		return sqlSession.insert(namespace+"scheduleInsert", dto) > 0;
	}
	
	public boolean scheduleUpdate(ScheduleDTO dto) {
		return sqlSession.update(namespace+"scheduleUpdate", dto) > 0;
	}
	
	public boolean scheduleDelete(ScheduleDTO dto) {
		return sqlSession.delete(namespace+"scheduleDelete", dto) > 0;
	}
	
	public ScheduleDTO scheduleSelect(String id) {
		return sqlSession.selectOne(namespace+"scheduleSelect", id);
	}
	
	public List<ScheduleDTO> scheduleSelectList(String id) {
		return sqlSession.selectList(namespace+"scheduleSelectList", id);
	}
}
