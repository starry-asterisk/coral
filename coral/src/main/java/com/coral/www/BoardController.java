package com.coral.www;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.coral.www.Board.BoardService;
import com.coral.www.application.FileUploadService;

@RequestMapping("/board")
@Controller
public class BoardController {
	@Inject
	BoardService service;
	@Inject
	FileUploadService fileUploadService;
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
		model.addAttribute("Category", service.categorylist());
		model.addAttribute("id", session.getAttribute("id"));
		return "editor";
	}
	
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request,@RequestParam("file") MultipartFile file) {
		System.out.println("---------------------------TEST------------------------------");
		String url = fileUploadService.restore(file);
		System.out.println(url);
		request.setAttribute("url", url);
		return "redirect:/";
	}
}
