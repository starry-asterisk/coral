package com.coral.www;






import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coral.www.Board.BoardService;
import com.coral.www.Board.CategoryDTO;

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
	public String editor(Model model, HttpSession session) {
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		CategoryDTO dto ;
		for(int i=0;i<15;i++) {
			dto = new CategoryDTO();
			dto.setCode("C"+i);
			dto.setName("카테고리 "+i);
			list.add(dto);
		}
		model.addAttribute("Category", list);
		model.addAttribute("id", session.getAttribute("id"));
		return "editor";
	}
}
