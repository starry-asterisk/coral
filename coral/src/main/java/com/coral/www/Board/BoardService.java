package com.coral.www.Board;



import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.coral.www.application.JFileWriter;
import com.coral.www.like.LikeDTO;

@Service
public class BoardService {
	@Inject
	BoardDAO dao;
	public void addList(Model model, HttpServletRequest request, String keyword) {
		BoardDTO dto = new BoardDTO();
		if(keyword!=null) {
			model.addAttribute("keyword", keyword);
			dto.setNo(keyword);
			dto.setId(keyword);
			dto.setTitle(keyword);
			dto.setTag(keyword);
		}
		dto.setPage(request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page")));
		dto.setAmount(request.getParameter("amount")==null?50:Integer.parseInt(request.getParameter("amount")));
		model.addAttribute("amount", dto.getAmount());
		model.addAttribute("Endpage", (int) Math.ceil((double)dao.total(dto)/(double)dto.getAmount()));
		model.addAttribute("Currentpage", dto.getPage());
		model.addAttribute("BoardList", dao.listPage(dto));
	}
	
	public List<CategoryDTO> categorylist() {
		return dao.categorylist();
	}
	public BoardDTO detail(String no) {
		dao.viewCntUpd(no);
		return dao.detail(no);
	}
	public boolean likeUpd(String no, int div) {
		LikeDTO dto = new LikeDTO();
		dto.setDiv(div);
		dto.setNo(no);
		return dao.likeCntUpd(dto);
	}
	public String write(BoardDTO dto) {
		try {
			dto.setNo(dao.newBno());
			if(dto.getContents().getBytes().length>1000) {
				new JFileWriter("board\\"+dto.getNo()+".txt",dto.getContents());
				dto.setContents("${linked}board\\"+dto.getNo()+".txt");
			}
			return dao.write(dto)?dto.getNo():null;
		}catch(Exception e) {
			new JFileWriter().delFile("board\\"+dto.getNo()+".txt");
			return null;
		}
	}

	public String update(BoardDTO dto) {
		try {
			if(dto.getContents().getBytes().length>1000) {
				new JFileWriter("board\\"+dto.getNo()+".txt",dto.getContents());
				dto.setContents("${linked}board\\"+dto.getNo()+".txt");
			}
			return dao.update(dto)?dto.getNo():null;
		}catch(Exception e) {
			new JFileWriter().delFile("board\\"+dto.getNo()+".txt");
			return null;
		}
	}
}
