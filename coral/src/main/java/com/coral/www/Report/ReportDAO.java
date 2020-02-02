/* 
 * ReportDAO.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.Report;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
@Repository
public class ReportDAO {
	/* 신고 Data Access Object */
	
	@Inject
	SqlSession sqlSession;
	
	static final String namespace="com.coral.www.mappers.ReportMapper.";
	
	public List<ReasonDTO> reasonList(char identifier) {
		List<ReasonDTO> returns = sqlSession.selectList(namespace+"reasonList",identifier);
		return returns; 
	}
	
	public List<ReportDTO> reportList(ReportDTO dto) {
		List<ReportDTO> returns = sqlSession.selectList(namespace+"report_List",dto);
		return returns; 
	}
	
	public boolean insertReport(ReportDTO dto) {
		return sqlSession.insert(namespace+"report_insert",dto) == 1;
	}
	
	public boolean insertPunish(ReportDTO dto) {
		return sqlSession.insert(namespace+"punish_insert",dto) == 1;
	}
	
	public boolean updateReport(ReportDTO dto) {
		return sqlSession.update(namespace+"report_update",dto) > 0;
	}
	
	public boolean updatePunish(ReportDTO dto) {
		return sqlSession.update(namespace+"punish_update",dto) > 0;
	}
	
	public int total(ReportDTO dto) {
		return sqlSession.selectOne(namespace+"total",dto);
	}
}
