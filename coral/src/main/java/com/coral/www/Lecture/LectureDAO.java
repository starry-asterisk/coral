/* 
 * LectureDAO.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.Lecture;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.coral.www.like.LikeDTO;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
@Repository
public class LectureDAO {
	/* 강좌&강의 Data Access Object */
	
	@Inject
	SqlSession sqlSession;
	
	static final String namespace="com.coral.www.mappers.LectureMapper.";
	
	public int total(LectureDTO dto) {
		return sqlSession.selectOne(namespace+"totalCL",dto);
	}
	
	public boolean updateCL(LectureDTO dto) {
		return (int)sqlSession.update(namespace+"updateCL",dto)==1;
	}
	
	public List<LectureDTO> listPageCL(LectureDTO dto) {
		return sqlSession.selectList(namespace+"listPageCL",dto);
	}
	
	public boolean create(LectureDTO dto) {
		return (int)sqlSession.insert(namespace+"create",dto)==1;
	}
	
	public String newCLno() {
		return sqlSession.selectOne(namespace+"newCLno");
	}
	
	public boolean CLExit(LectureDTO dto) {
		return (int)sqlSession.selectOne(namespace+"CLExit",dto)==1;
	}
	
	public LectureDTO description(String cl_no) {
		return sqlSession.selectOne(namespace+"description",cl_no);
	}
	
	public int total(String cl_no) {
		return sqlSession.selectOne(namespace+"total",cl_no);
	}
	
	public boolean update(LectureDTO dto) {
		return (int)sqlSession.update(namespace+"update",dto)==1;
	}
	
	public List<LectureDTO> listPage(LectureDTO dto) {
		return sqlSession.selectList(namespace+"listPage",dto);
	}
	
	public boolean write(LectureDTO dto) {
		return (int)sqlSession.insert(namespace+"write",dto)==1;
	}
	
	public LectureDTO detail(String no) {
		return sqlSession.selectOne(namespace+"detail",no);
	}
	
	public String newLEno() {
		return sqlSession.selectOne(namespace+"newLEno");
	}
	
	public boolean viewCntUpd(String no) {
		return (int)sqlSession.update(namespace+"viewCntUpd",no)==1;
	}
	
	public boolean likeCntUpd(LikeDTO dto) {
		return (int)sqlSession.update(namespace+"likeCntUpd",dto)==1;
	}
	
	public boolean LExit(LectureDTO dto) {
		return (int)sqlSession.selectOne(namespace+"LExit",dto)==1;
	}
	
	public int deleteAll(String id) {
		return sqlSession.update(namespace+"deleteAll",id);
	}
	
	public int deleteCL(LectureDTO dto) {
		return sqlSession.update(namespace+"deleteCL",dto);
	}
	
	public boolean deleteLE(String cl_no) {
		return (int)sqlSession.update(namespace+"deleteLE",cl_no) > 0;
	}
}
