package com.coral.www.Report;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Inject
	ReportDAO dao;
	
	@Override
	public List<ReportDTO> reasonList(char identifier) {
		return (ArrayList<ReportDTO>) dao.reasonList(identifier);
	}
}
