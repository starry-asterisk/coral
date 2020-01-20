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
	
	public int total() {
		return sqlSession.selectOne(namespace+"total");
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
	public String newBno() {
		return sqlSession.selectOne(namespace+"newBno");
	}
	public boolean viewCntUpd(String no) {
		int result = sqlSession.update(namespace+"viewCntUpd",no);
		return result==1;
	}
	public boolean likeCntUpd(LikeDTO dto) {
		int result = sqlSession.update(namespace+"likeCntUpd",dto);
		return result==1;
	}
	public boolean update(LectureDTO dto) {
		int result = sqlSession.update(namespace+"update",dto);
		return result==1;
	}
}
