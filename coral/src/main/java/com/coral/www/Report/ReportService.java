package com.coral.www.Report;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface ReportService {

	public List<ReasonDTO> reasonList(char identifier);
	public List<ReportDTO> reportList(ReportDTO dto);
	public ReportDTO insertReport(ReportDTO dto);
	public boolean punishment(ReportDTO dto);
	void addList(Model model, HttpServletRequest request, String keyword, String rscode);
	boolean closeClass(ReportDTO dto);
}
