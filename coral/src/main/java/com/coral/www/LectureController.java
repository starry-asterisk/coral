package com.coral.www;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coral.www.File.FileDTO;
import com.coral.www.File.FileService;
import com.coral.www.Lecture.LectureDTO;
import com.coral.www.Lecture.LectureService;
import com.coral.www.application.JFileWriter;

@RequestMapping("/lecture")
@Controller
public class LectureController {
	@Inject
	LectureService service;

	@Inject
	FileService fileService;

	@RequestMapping("")
	public String board(Model model, HttpServletRequest request, @RequestParam(required=false) String cl_no,  @RequestParam(required=false) String isAjax) {
		service.addList(model, request, cl_no);
		
		if(isAjax!=null) {
			if(cl_no!=null) {
				return "include/lecture";
			}else {
				return "include/class";
			}
		}else {
			if(cl_no!=null) {
				model.addAttribute("Board_type", "include/lecture");
			}else {
				model.addAttribute("Board_type", "include/class");
			}
		}
		
		model.addAttribute("attachment", "common/blank");
		return "list";
	}
	@Transactional
	@RequestMapping(value="/create",method = { RequestMethod.POST })
	public String create(LectureDTO dto,@RequestParam(required=false) String[] files,@RequestParam(required=false) String[] filesName,@RequestParam(required=false) String[] filesType, HttpServletRequest request) throws ParseException, UnsupportedEncodingException{
		dto.setAttachment(files!=null?'P':'N');
		dto.setId((String) request.getSession().getAttribute("id"));
		return "redirect:/board"+"?Code=alert('"+URLEncoder.encode(fileService.insert(files , filesType , filesName , service.write(dto))?"게시글이 등록되었습니다":"등록에 실패했습니다", "UTF-8")+"');";
	}
	@Transactional
	@RequestMapping(value="/update",method = { RequestMethod.POST })
	public String uppdate(LectureDTO dto,@RequestParam(required=false) String[] files,@RequestParam(required=false) String[] filesName,@RequestParam(required=false) String[] filesType, HttpServletRequest request) throws ParseException, UnsupportedEncodingException{
		dto.setAttachment(files!=null?'P':'N');
		if(dto.getStatus()=='N') {
			dto.setAttachment('N');
			files=null;
			filesName=null;
			filesType=null;
		}
		dto.setId((String) request.getSession().getAttribute("id"));
		return "redirect:/board"+"?Code=alert('"+URLEncoder.encode(fileService.update(files , filesType , filesName , service.update(dto))?(dto.getStatus()=='N'?"게시글이 삭제되었습니다":"게시글이 수정되었습니다"):"수정에 실패했습니다", "UTF-8")+"');";
	}
	
	@RequestMapping("/detail")
	public String detail(Model model, @RequestParam String bno, @RequestHeader("user-agent") String agent) {
		LectureDTO boarddto = service.detail(bno);
		if(boarddto.getContent().contains("${linked}")) {
			boarddto.setContent(new JFileWriter().readFile(boarddto.getContent().replace("${linked}", "")));
		}
		if(boarddto.getAttachment()=='P') {
			List<FileDTO> list = fileService.getAttachment(boarddto.getNo());
			model.addAttribute("attachment", list);
			for(FileDTO filedto:list) {
				boarddto.setContent(boarddto.getContent().replaceFirst("<img:"+filedto.getOrder()+">", filedto.getPath()));
			}
		}
		model.addAttribute("board", boarddto);
		if(agent.contains("MSIE")||agent.contains("Trident")) {
			model.addAttribute("include", "include/ckEdit4");
		}else {
			model.addAttribute("include", "include/ckEdit5");
		}
		return "detail";
	}
	
	@RequestMapping(value="/write",method = { RequestMethod.GET })
	public String editor(Model model, HttpServletRequest request) {
		model.addAttribute("id", request.getSession().getAttribute("id"));
		String agent = request.getHeader("user-agent");
		if(agent.contains("MSIE")||agent.contains("Trident")) {
			model.addAttribute("include", "include/ckEdit4");
		}else {
			model.addAttribute("include", "include/ckEdit5");
		}
		return "editor";
	}
	
	@Transactional
	@RequestMapping(value="/write",method = { RequestMethod.POST })
	public String upload(LectureDTO dto,@RequestParam(required=false) String[] files,@RequestParam(required=false) String[] filesName,@RequestParam(required=false) String[] filesType, HttpServletRequest request) throws ParseException, UnsupportedEncodingException{
		dto.setAttachment(files!=null?'P':'N');
		dto.setId((String) request.getSession().getAttribute("id"));
		return "redirect:/board"+"?Code=alert('"+URLEncoder.encode(fileService.insert(files , filesType , filesName , service.write(dto))?"게시글이 등록되었습니다":"등록에 실패했습니다", "UTF-8")+"');";
	}
	@RequestMapping(value="/edit",method = { RequestMethod.GET })
	public String edit(Model model, @RequestParam String bno, @RequestHeader("user-agent") String agent, HttpSession session) throws UnsupportedEncodingException {
		LectureDTO boarddto = service.detail(bno);
		if(!boarddto.getId().equals(session.getAttribute("id"))) {
			return "redirect:/"+"?Code=alert('"+URLEncoder.encode("타인의 게시물은 수정할 수 없습니다!", "UTF-8")+"');";
		}
		if(boarddto.getContent().contains("${linked}")) {
			boarddto.setContent(new JFileWriter().readFile(boarddto.getContent().replace("${linked}", "")));
		}
		if(boarddto.getAttachment()=='P') {
			List<FileDTO> list = fileService.getAttachment(boarddto.getNo());
			model.addAttribute("attachment", list);
			for(FileDTO filedto:list) {
				if(boarddto.getContent().contains("<img:"+filedto.getOrder()+">")) {
					filedto.setImage(true);
					boarddto.setContent(boarddto.getContent().replaceFirst("<img:"+filedto.getOrder()+">", filedto.getPath()));
				}
			}
		}
		model.addAttribute("board", boarddto);
		if(agent.contains("MSIE")||agent.contains("Trident")) {
			model.addAttribute("include", "include/ckEdit4");
		}else {
			model.addAttribute("include", "include/ckEdit5");
		}
		model.addAttribute("isNew", false);
		return "editor";
	}
	@Transactional
	@RequestMapping(value="/edit",method = { RequestMethod.POST })
	public String edit(LectureDTO dto,@RequestParam(required=false) String[] files,@RequestParam(required=false) String[] filesName,@RequestParam(required=false) String[] filesType, HttpServletRequest request) throws ParseException, UnsupportedEncodingException{
		dto.setAttachment(files!=null?'P':'N');
		if(dto.getStatus()=='N') {
			dto.setAttachment('N');
			files=null;
			filesName=null;
			filesType=null;
		}
		dto.setId((String) request.getSession().getAttribute("id"));
		return "redirect:/board"+"?Code=alert('"+URLEncoder.encode(fileService.update(files , filesType , filesName , service.update(dto))?(dto.getStatus()=='N'?"게시글이 삭제되었습니다":"게시글이 수정되었습니다"):"수정에 실패했습니다", "UTF-8")+"');";
	}
}
