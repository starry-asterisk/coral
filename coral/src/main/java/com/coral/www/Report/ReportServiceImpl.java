package com.coral.www.Report;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.coral.www.Board.BoardDTO;

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
	@Override
	public List<ReportDTO> reportList(ReportDTO dto) {
		return dao.reportList(dto);
	}
	/*
	@Override
	public void addList(Model model, HttpServletRequest request, String keyword) {
		ReportDTO dto;
		if(keyword!=null) {
			model.addAttribute("keyword", keyword);
			dto.setNo(keyword);
			dto.setId(keyword);
			dto.setTitle(keyword);
			dto.setTag(keyword);
		}
		dto.setPage(request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page")));
		dto.setAmount(request.getParameter("amount")==null?50:Integer.parseInt(request.getParameter("amount")));
		model.addAttribute("B_amount", dto.getAmount());
		model.addAttribute("B_Endpage", (int) Math.ceil((double)dao.total(dto)/(double)dto.getAmount()));
		model.addAttribute("B_Currentpage", dto.getPage());
		model.addAttribute("reportList", dao.reportList(dto));
	}*/
	@Override
	public void addList(Model model, HttpServletRequest request, String keyword) {
		// TODO Auto-generated method stub
		
	}
}
