package com.coral.www.Board;






import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/board")
@Controller
public class BoardController {
	@Inject
	BoardService service;
	@RequestMapping(value = "", method = { RequestMethod.GET, RequestMethod.POST})
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
	public String editor(Model model) {
		return "editor";
	}
}
