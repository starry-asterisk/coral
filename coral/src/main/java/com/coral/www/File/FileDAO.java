package com.coral.www.File;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class FileDAO {
	@Inject
	SqlSession sqlSession;
	static final String namespace="com.coral.www.mappers.FileMapper.";
	
	public boolean insert(FileDTO dto) {
		int result = sqlSession.insert(namespace+"insert",dto);
		return result==1?true:false;
	}
	public List<FileDTO> getList(String bno) {
		return sqlSession.selectList(namespace+"selectList",bno);
	}
	public int newOrder(String bno) {
		return sqlSession.selectOne(namespace+"newOrder",bno);
	}
	public boolean update(FileDTO dto) {
		int result = sqlSession.update(namespace+"update",dto);
		return result==1?true:false;
	}
	public boolean delete(FileDTO dto) {
		int result = sqlSession.delete(namespace+"delete",dto);
		return result==1?true:false;
	}
}
