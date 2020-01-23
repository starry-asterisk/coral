package com.coral.www.Lecture;


import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.coral.www.like.LikeDTO;


@Repository
public class LectureDAO {
	@Inject
	SqlSession sqlSession;
	static final String namespace="com.coral.www.mappers.LectureMapper.";
	
	
	public int total(LectureDTO dto) {
		return sqlSession.selectOne(namespace+"totalCL",dto);
	}
	public boolean updateCL(LectureDTO dto) {
		int result = sqlSession.update(namespace+"updateCL",dto);
		return result==1;
	}
	public List<LectureDTO> listPageCL(LectureDTO dto) {
		return sqlSession.selectList(namespace+"listPageCL",dto);
	}
	public boolean create(LectureDTO dto) {
		int result = sqlSession.insert(namespace+"create",dto);
		return result==1;
	}
	public String newCLno() {
		return sqlSession.selectOne(namespace+"newCLno");
	}
	public boolean CLExit(LectureDTO dto) {
		int result = sqlSession.selectOne(namespace+"CLExit",dto);
		return result==1;
	}
	public LectureDTO description(String cl_no) {
		return sqlSession.selectOne(namespace+"description",cl_no);
	}
	
	
	public int total(String cl_no) {
		return sqlSession.selectOne(namespace+"total",cl_no);
	}
	public boolean update(LectureDTO dto) {
		int result = sqlSession.update(namespace+"update",dto);
		return result==1;
	}
	public List<LectureDTO> listPage(LectureDTO dto) {
		return sqlSession.selectList(namespace+"listPage",dto);
	}
	public boolean write(LectureDTO dto) {
		int result = sqlSession.insert(namespace+"write",dto);
		return result==1;
	}
	public LectureDTO detail(String no) {
		return sqlSession.selectOne(namespace+"detail",no);
	}
	public String newLEno() {
		return sqlSession.selectOne(namespace+"newLEno");
	}
	public boolean viewCntUpd(String no) {
		int result = sqlSession.update(namespace+"viewCntUpd",no);
		return result==1;
	}
	public boolean likeCntUpd(LikeDTO dto) {
		int result = sqlSession.update(namespace+"likeCntUpd",dto);
		return result==1;
	}
	public boolean LExit(LectureDTO dto) {
		int result = sqlSession.selectOne(namespace+"LExit",dto);
		return result==1;
	}
	public int deleteAll(String id) {
		return sqlSession.update(namespace+"deleteAll",id);
	}
	public int deleteCL(LectureDTO dto) {
		return sqlSession.update(namespace+"deleteCL",dto);
	}
}
