/* 
 * LectureServiceImpl.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.Lecture;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.coral.www.application.JFileWriter;
import com.coral.www.like.LikeDTO;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
@Service
public class LectureServiceImpl implements LectureService {
	/* 강좌&강의 서비스  */
	
	/** DAO */
	@Inject
	LectureDAO dao;
	
	@Override
	public void addList(Model model, HttpServletRequest request, String cl_no, String keyword) {
		LectureDTO dto = new LectureDTO();
		if(cl_no==null&&keyword!=null) {
			model.addAttribute("keyword", keyword);
			dto.setCl_no(keyword);
			dto.setCl_title(keyword);
			dto.setCl_description(keyword);
			dto.setId(keyword);
			dto.setCl_tag(keyword);
		}
		dto.setPage(request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page")));
		dto.setAmount(request.getParameter("amount")==null?20:Integer.parseInt(request.getParameter("amount")));
		model.addAttribute("amount", dto.getAmount());
		model.addAttribute("Endpage", (int) Math.ceil((double)(cl_no!=null?dao.total(cl_no):dao.total(dto))/(double)dto.getAmount()));
		model.addAttribute("Currentpage", dto.getPage());
		if(cl_no!=null) {
			dto.setCl_no(cl_no);
			model.addAttribute("LectureList", dao.listPage(dto));
			model.addAttribute("Class", dao.description(cl_no));
			model.addAttribute("cl_no", cl_no);
		}else {
			model.addAttribute("ClassList", dao.listPageCL(dto));
		}
	}
	
	@Override
	public LectureDTO detail(String no, boolean isView) {
		if(isView)dao.viewCntUpd(no);
		return dao.detail(no);
	}
	
	@Override
	public boolean likeUpd(String no, int div) {
		LikeDTO dto = new LikeDTO();
		dto.setDiv(div);
		dto.setNo(no);
		return dao.likeCntUpd(dto);
	}
	
	@Override
	@Transactional
	public String write(LectureDTO dto) {
		try {
			dto.setNo(dao.newLEno());
			if(dto.getContents().getBytes().length>1000) {
				new JFileWriter("board\\"+dto.getNo()+".txt",dto.getContents());
				dto.setContents("${linked}board\\"+dto.getNo()+".txt");
			}
			return dao.write(dto)?dto.getNo():null;
		}catch(Exception e) {
			e.printStackTrace();
			new JFileWriter().delFile("board\\"+dto.getNo()+".txt");
			return null;
		}
	}
	
	@Override
	@Transactional
	public boolean create(LectureDTO dto) {
		try {
			dto.setCl_no(dao.newCLno());
			return dao.create(dto)?true:false;
		}catch(Exception e) {
			return false;
		}
	}
	
	@Override
	@Transactional
	public String update(LectureDTO dto) {
		try {
			if(dto.getContents().getBytes().length>1000) {
				new JFileWriter("board\\"+dto.getNo()+".txt",dto.getContents());
				dto.setContents("${linked}board\\"+dto.getNo()+".txt");
			}
			return dao.update(dto)?dto.getNo():null;
		}catch(Exception e) {
			e.printStackTrace();
			new JFileWriter().delFile("board\\"+dto.getNo()+".txt");
			return null;
		}
	}
	
	@Override
	@Transactional
	public boolean updateCL(LectureDTO dto) {
		return dao.updateCL(dto);
	}
	
	@Override
	public boolean CLExit(LectureDTO dto) {
		return dao.CLExit(dto);
	}
	
	@Override
	public boolean isCL(String cl_no) {
		return dao.total(cl_no)>0;
	}
	
	@Override
	public boolean LExit(LectureDTO dto) {
		return dao.LExit(dto);
	}
}
