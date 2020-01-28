package com.coral.www.Board;



import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
		dto.setCategory(request.getParameter("category"));
		dto.setPage(request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page")));
		dto.setAmount(request.getParameter("amount")==null?50:Integer.parseInt(request.getParameter("amount")));
		model.addAttribute("B_amount", dto.getAmount());
		model.addAttribute("B_Endpage", (int) Math.ceil((double)dao.total(dto)/(double)dto.getAmount()));
		model.addAttribute("B_Currentpage", dto.getPage());
		model.addAttribute("BoardList", dao.listPage(dto));
	}
	
	public List<CategoryDTO> categorylist(String permission) {
		if(permission!=null) {
			switch(permission) {
			case "관리자":
				return dao.categorylist("MANAGER");
			case "교사":
				return dao.categorylist("TEACH");
			default:
				return dao.categorylist("ANY");
			}
		}else {
			return dao.categorylist(null);
		}
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

	public boolean updateCA(CategoryDTO dto) {
		return dao.updateCA(dto);
	}

	public boolean insertCA(CategoryDTO dto) {
		return dao.insertCA(dto);
	}
	
	@Transactional
	public boolean deleteCA(String from, String to) {
		CategoryDTO dto = new CategoryDTO();
		dto.setCode(from);
		dto.setName(to);
		dao.moveCA(dto);
		if(dao.deleteCA(dto.getCode())) {
			return true;
		}
		return false;
	}

	public boolean moveCA(String from, String to) {
		CategoryDTO dto = new CategoryDTO();
		dto.setCode(from);
		dto.setName(to);
		return dao.moveCA(dto);
	}
}
