/* 
 * ReportServiceImpl.java		1.0.0 2020.02.02
 * 
 * Copyright all reserved coral
 */

package com.coral.www.Report;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.coral.www.Board.BoardDAO;
import com.coral.www.Lecture.LectureDAO;
import com.coral.www.Lecture.LectureDTO;
import com.coral.www.User.UserDAO;
import com.coral.www.like.ReplyDAO;
import com.coral.www.like.ReplyDTO;

/**
* @version			1.0.0 2020.01.31
* @author			김현우, 이창현, 박승리, 백현욱, 장지수
*/
@Service
public class ReportServiceImpl implements ReportService {
	/* 신고 서비스  */
	
	/** 신고 DAO */
	@Inject
	ReportDAO dao;
	
	/** 계정 DAO */
	@Inject
	UserDAO udao;
	
	/** 게시판 DAO */
	@Inject
	BoardDAO bdao;
	
	/** 강좌&강의  DAO */
	@Inject
	LectureDAO ldao;
	
	/** 댓글 DAO */
	@Inject
	ReplyDAO rdao;
	
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
	public void addList(Model model, HttpServletRequest request, String keyword, String rscode) {
		ReportDTO dto = new ReportDTO();
		if(keyword!=null) {
			model.addAttribute("keyword", keyword);
			dto.setObject(keyword);
		}
		dto.setRscode(rscode);
		dto.setPage(request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page")));
		dto.setAmount(request.getParameter("amount")==null?20:Integer.parseInt(request.getParameter("amount")));
		model.addAttribute("R_amount", dto.getAmount());
		model.addAttribute("R_Endpage", (int) Math.ceil((double)dao.total(dto)/(double)dto.getAmount()));
		model.addAttribute("R_Currentpage", dto.getPage());
		model.addAttribute("reportList", dao.reportList(dto));
	}
	
	@Transactional
	@Override
	public boolean punishment(ReportDTO dto) {
		if(dao.updateReport(dto)) {
			if(dto.getStatus().equals("P")) {
				if(dto.getCode().equals("b")) {
					if(dto.getObject().length()>9) {
						ReplyDTO rdto = new ReplyDTO();
						rdto.setBno(dto.getObject().split(":")[0]);
						rdto.setNo(Integer.parseInt(dto.getObject().split(":")[1]));
						dto.setId(rdao.select(rdto.getBno()).get(rdto.getNo()).getId());
						rdao.delete(rdto);
					}else {
						LectureDTO ldto;
						switch(dto.getObject().substring(0,1)) {
						case "L":
							dto.setId(ldao.detail(dto.getObject()).getId());
							ldto = new LectureDTO();
							ldto.setStatus('N');
							ldto.setNo(dto.getObject());
							ldao.update(ldto);
							break;
						case "C":
							dto.setId(ldao.description(dto.getObject()).getId());
							ldto = new LectureDTO();
							ldao.deleteLE(dto.getObject());
							ldto.setCl_no(dto.getObject());
							ldao.deleteCL(ldto);
							break;
						case "B":
							dto.setId(bdao.detail(dto.getObject()).getId());
							bdao.delete(dto.getObject());
							break;
						}
					}
				}
				dao.insertPunish(dto);
			}
			return true;
		}else {
			return false;
		}
		
	}
	
	@Transactional
	@Override
	public boolean closeClass(ReportDTO dto) {
		if(dao.updateReport(dto)) {
			if(dto.getStatus().equals("P")) {
				LectureDTO ldto = new LectureDTO();
				ldao.deleteLE(dto.getObject());
				ldto.setCl_no(dto.getObject());
				ldao.deleteCL(ldto);
			}
			return true;
		}else {
			return false;
		}
		
	}
}
