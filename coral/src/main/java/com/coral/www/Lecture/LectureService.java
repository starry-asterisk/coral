package com.coral.www.Lecture;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface LectureService {
	
	public void addList(Model model, HttpServletRequest request, String cl_no, String keyword);
	public LectureDTO detail(String no, boolean isView);
	public boolean likeUpd(String no, int div);
	public String write(LectureDTO dto);
	public boolean create(LectureDTO dto);
	public String update(LectureDTO dto);
	public boolean updateCL(LectureDTO dto);
	public boolean CLExit(LectureDTO dto);
	public boolean isCL(String cl_no);
	public boolean LExit(LectureDTO dto);
}
