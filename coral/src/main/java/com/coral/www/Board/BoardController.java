package com.coral.www.Board;






import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	@RequestMapping(value = "", method = { RequestMethod.GET, RequestMethod.POST})
	public String board(Model model, HttpServletRequest request, @RequestParam(required=false) int Page) {
		if(Page!=0) {
			
		}else {
			
		}
		model.addAttribute("Board_type", "include/board");
		model.addAttribute("attachment", "include/blank");
		return "list";
	}
	@RequestMapping("/detail")
	public String detail(Model model, HttpServletRequest request) {
		model.addAttribute("bno", request.getParameter("bno"));
		return "detail";
	}
	@RequestMapping(value = "/editor", method = RequestMethod.POST)
	public String editor(Model model) {
		return "editor";
	}
}
