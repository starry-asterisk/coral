package com.coral.www;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.coral.www.Board.BoardDTO;
import com.coral.www.Board.BoardService;
import com.coral.www.File.FileService;

@RequestMapping("/board")
@Controller
public class BoardController {
	@Inject
	BoardService service;

	@Inject
	FileService fileUploadService;

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
	@RequestMapping(value="/write",method = { RequestMethod.GET })
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
	
	@RequestMapping(value="/write",method = { RequestMethod.POST })
	public String upload(BoardDTO dto,@RequestParam(required=false) List<MultipartFile> files, HttpServletRequest request) throws ParseException, UnsupportedEncodingException{
		dto.setAttachment(files.size()!=0?'P':'N');
		dto.setId((String) request.getSession().getAttribute("id"));
		return "redirect:/board"+"?Code=alert('"+URLEncoder.encode(fileUploadService.insert(files , service.write(dto))?"게시글이 등록되었습니다":"등록에 실패했습니다", "UTF-8")+"');";
	}
}
