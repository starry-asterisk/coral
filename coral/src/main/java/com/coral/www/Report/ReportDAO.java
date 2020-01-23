package com.coral.www.Report;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.coral.www.Board.BoardDTO;


@Repository
public class ReportDAO {
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
		return sqlSession.insert(namespace+"report_insert",dto) ==1? true:false;
	}
	public Object listPage(ReportDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
}
