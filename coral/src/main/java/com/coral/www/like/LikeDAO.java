/* 
 * LikeDAO.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.like;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
@Repository
public class LikeDAO {
	/* 좋아요 Data Access Object */
	
	@Inject
	SqlSession sqlSession;
	static final String namespace="com.coral.www.mappers.LikeMapper.";
	
	public boolean insert(LikeDTO dto) {
		return (int)sqlSession.insert(namespace+"insert",dto)==1;
	}
	
	public List<LikeDTO> select(LikeDTO dto) {
		return sqlSession.selectList(namespace+"select",dto);
	}
}
