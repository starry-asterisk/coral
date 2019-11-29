package com.coral.www.Board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class BoardService {
	
	public static void List3(Model model, HttpServletRequest request, int oneView) {
		ArrayList<BoardDTO> list = BoardService.getList(999);
		int MAXpage = (int)(Math.ceil(list.size()/(oneView+0.0)));
		int page = 1;
		int prePage;
		int nextPage;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if(MAXpage > (page+4)) {
			nextPage = page+4;
		}else {
			nextPage = MAXpage;
		}
		if(page-4>0) {
			prePage = page-4;
		}else {
			prePage = 1;
		}
		model.addAttribute("Board_type", "include/board");
		model.addAttribute("Firstpage", prePage);
		model.addAttribute("Lastpage", nextPage);
		try {
			model.addAttribute("BoardList",list.subList((page-1)*oneView, page*oneView));
		}catch(Exception e) {
			model.addAttribute("BoardList",list.subList((page-1)*oneView,list.size()));
		}
	}

	public static ArrayList<BoardDTO> getList(int max) {
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		for(int i=0;i<max;i++) {
			BoardDTO e = new BoardDTO();
			e.setBno(i);
			e.setCount((int)(Math.random()*10000));
			e.setName("namer"+i);
			e.setTime("2019-11-19");
			e.setTitle("제목 없음");
			e.setThumb((int)(Math.random()*1000));
			list.add(e);
		}
		return list;
	}

}
