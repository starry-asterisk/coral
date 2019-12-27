package com.coral.www.Report;


import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Inject
	ReportDAO dao;
	
	@Override
	public ArrayList<ReportDTO> getRsList(char identifier) {
		return (ArrayList<ReportDTO>) dao.Rslist(identifier);
	}
}
