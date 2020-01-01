package com.coral.www;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coral.www.Board.BoardDTO;
import com.coral.www.Board.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {
	@Inject
	BoardService service;

	@RequestMapping("")
	public String board(Model model, HttpServletRequest request) {
		service.addList(model, request);
		model.addAttribute("Board_type", "include/board");
		model.addAttribute("attachment", "common/blank");
		return "list";
	}
	@RequestMapping("/detail")
	public String detail(Model model, HttpServletRequest request) {
		model.addAttribute("bno", request.getParameter("bno"));
		return "detail";
	}
	@RequestMapping("/editor")
	public String editor(Model model, HttpServletRequest request) {
		model.addAttribute("Category", service.categorylist());
		model.addAttribute("id", request.getSession().getAttribute("id"));
		String agent = request.getHeader("user-agent");
		if(agent.contains("MSIE")||agent.contains("Trident")) {
			model.addAttribute("include", "include/ckEdit4");
		}else {
			model.addAttribute("include", "include/ckEdit5");
		}
		return "editor";
	}
	@RequestMapping("/upload")
	public String upload(BoardDTO dto,@RequestParam(required = false) String files){
		System.out.println(dto.toString());
		System.out.print(files.toString());
		return "redirect:/board";
	}
}
