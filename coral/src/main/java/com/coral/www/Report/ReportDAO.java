package com.coral.www.Report;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class ReportDAO {
	@Inject
	SqlSession sqlSession;
	static final String namespace="com.coral.www.mappers.ReportMapper.";
	
	public List<ReportDTO> reasonList(char identifier) {
		List<ReportDTO> returns = sqlSession.selectList(namespace+"reasonList",identifier);
		return returns; 
	}
}
