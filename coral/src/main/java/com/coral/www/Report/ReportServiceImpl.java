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
	public List<ReasonDTO> reasonList(char identifier) {
		return (ArrayList<ReasonDTO>) dao.reasonList(identifier);
	}
	@Override
	public ReportDTO insertReport(ReportDTO dto) {
		try {
			if(!dao.insertReport(dto)) {
				dto = null;
			};
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
