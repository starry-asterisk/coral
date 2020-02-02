/* 
 * FileDAO.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.File;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
@Repository
public class FileDAO {
	/* 첨부파일 Data Access Object */
	
	@Inject
	SqlSession sqlSession;
	static final String namespace="com.coral.www.mappers.FileMapper.";
	
	public boolean insert(FileDTO dto) {
		return (int)sqlSession.insert(namespace+"insert",dto)==1;
	}
	
	public List<FileDTO> getList(String bno) {
		return sqlSession.selectList(namespace+"selectList",bno);
	}
	
	public int newOrder(String bno) {
		return sqlSession.selectOne(namespace+"newOrder",bno);
	}
	
	public boolean update(FileDTO dto) {
		return (int)sqlSession.update(namespace+"update",dto)==1;
	}
	
	public boolean delete(FileDTO dto) {
		return (int)sqlSession.delete(namespace+"delete",dto)==1;
	}
}
