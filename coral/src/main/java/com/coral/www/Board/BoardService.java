package com.coral.www.Board;



import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class BoardService {
	@Inject
	BoardDAO dao;
	public void addList(Model model, HttpServletRequest request) {
		BoardDTO dto = new BoardDTO();
		dto.setPage(request.getParameter("page")==null?1:Integer.parseInt(request.getParameter("page")));
		dto.setAmount(50);
		int Firstpage = dto.getPage()-5;
		int Lastpage = (int) Math.ceil((double)dao.total()/(double)dto.getAmount());
		if(Firstpage<1) {
			Firstpage = 1;
		}
		if((Lastpage-Firstpage)>9) {
			Lastpage = Firstpage+9;
		}else if(Lastpage<dto.getPage()) {
			Lastpage = dto.getPage();
		}
		model.addAttribute("Firstpage", Firstpage);
		model.addAttribute("Lastpage", Lastpage);
		model.addAttribute("BoardList", dao.listPage(dto));
	}
	

}
