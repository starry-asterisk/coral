package com.coral.www.Board;






import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coral.www.dao.DAO;


@Controller
public class BoardController {
	
	
	@RequestMapping(value = "/teacher", method = { RequestMethod.GET, RequestMethod.POST})
	public String teacher(Model model, HttpServletRequest request) {
		BoardService.List3(model,request,50);
		model.addAttribute("Board_type", "include/teacher");
		model.addAttribute("attachment", "include/raiting");
		return "list";
	}
	@RequestMapping(value = "/class", method = { RequestMethod.GET, RequestMethod.POST})
	public String Class(Model model, HttpServletRequest request) {
		BoardService.List3(model,request,25);
		model.addAttribute("Board_type", "include/class");
		model.addAttribute("attachment", "include/blank");
		return "list";
	}
	@RequestMapping(value = "/board", method = { RequestMethod.GET, RequestMethod.POST})
	public String board(Model model, HttpServletRequest request) {
		BoardService.List3(model,request,100);
		model.addAttribute("attachment", "include/blank");
		return "list";
	}
	@RequestMapping(value = "/lesson", method = { RequestMethod.GET, RequestMethod.POST})
	public String lesson(Model model, HttpServletRequest request) {
		BoardService.List3(model,request,10);
		model.addAttribute("Board_type", "include/lesson");
		model.addAttribute("attachment", "include/raiting");
		return "list";
	}
	@RequestMapping(value = "/board/detail", method = { RequestMethod.GET, RequestMethod.POST})
	public String detail(Model model, HttpServletRequest request) {
		model.addAttribute("bno", request.getParameter("bno"));
		return "detail";
	}
	@Inject
	DAO dao;
	@RequestMapping(value = "/editor", method = { RequestMethod.GET, RequestMethod.POST})
	public String editor(Model model) {
		return "editor";
	}
}
