package com.coral.www.Report;


import java.util.List;

public interface ReportService {

	public List<ReasonDTO> reasonList(char identifier);
	public ReportDTO insertReport(ReportDTO dto);
}
