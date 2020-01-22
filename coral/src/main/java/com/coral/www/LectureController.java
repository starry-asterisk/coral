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
	public String board(Model model, HttpServletRequest request, @RequestParam(required=false) String cl_no, @RequestParam(required=false) String keyword,  @RequestParam(required=false) String isAjax) {
		service.addList(model, request, cl_no, keyword);
		
		if(isAjax!=null) {
			if(cl_no!=null) {
				return "include/lecture";
			}else {
				return "include/class";
			}
		}else {
			if(cl_no!=null) {
				model.addAttribute("Board_type", "include/lecture");
				model.addAttribute("attachment", "include/Lside");
			}else {
				model.addAttribute("Board_type", "include/class");
				model.addAttribute("attachment", "include/Cside");
			}
		}
		
		
		return "list";
	}
	@Transactional
	@RequestMapping(value="/create",method = { RequestMethod.POST })
	public String create(LectureDTO dto, HttpServletRequest request) throws ParseException, UnsupportedEncodingException{
		dto.setId((String) request.getSession().getAttribute("id"));
		return "redirect:/lecture"+"?Code=alert('"+URLEncoder.encode(service.create(dto)?"게시글이 등록되었습니다":"등록에 실패했습니다", "UTF-8")+"');";
	}
	@Transactional
	@RequestMapping(value="/update",method = { RequestMethod.POST })
	public String uppdate(LectureDTO dto , HttpServletRequest request) throws ParseException, UnsupportedEncodingException{
		dto.setId((String) request.getSession().getAttribute("id"));
		return "redirect:/lecture"+"?Code=alert('"+URLEncoder.encode((service.updateCL(dto)?(dto.getStatus()=='N'?"게시글이 삭제되었습니다":"게시글이 수정되었습니다"):"수정에 실패했습니다"), "UTF-8")+"');";
	}
	
	@RequestMapping("/course")
	public String course(Model model, @RequestParam String no, @RequestHeader("user-agent") String agent) {
		LectureDTO dto = service.detail(no,true);
		if(dto.getContents().contains("${linked}")) {
			dto.setContents(new JFileWriter().readFile(dto.getContents().replace("${linked}", "")));
		}
		if(dto.getAttachment()=='P') {
			List<FileDTO> list = fileService.getAttachment(dto.getNo());
			model.addAttribute("attachment", list);
			for(FileDTO filedto:list) {
				dto.setContents(dto.getContents().replaceFirst("<img:"+filedto.getOrder()+">", filedto.getPath()));
			}
		}
		model.addAttribute("board", dto);
		if(agent.contains("MSIE")||agent.contains("Trident")) {
			model.addAttribute("include", "include/ckEdit4");
		}else {
			model.addAttribute("include", "include/ckEdit5");
		}
		return "detail";
	}
	
	@RequestMapping(value="/write",method = { RequestMethod.GET })
	public String editor(Model model, @RequestParam String cl_no, @RequestHeader("user-agent") String agent, HttpSession session) throws Exception {
		LectureDTO dto = new LectureDTO();
		dto.setCl_no(cl_no);
		dto.setId((String)session.getAttribute("id"));
		if(!service.CLExit(dto)){
			return "redirect:/"+"?Code=alert('"+URLEncoder.encode("타인의 강좌에는 강의를 업로할 수가 없습니다!", "UTF-8")+"');";
		}
		if(agent.contains("MSIE")||agent.contains("Trident")) {
			model.addAttribute("include", "include/ckEdit4");
		}else {
			model.addAttribute("include", "include/ckEdit5");
		}
		model.addAttribute("cl_no", cl_no);
		return "editor";
	}
	
	
	@RequestMapping(value="/write",method = { RequestMethod.POST })
	public String upload(LectureDTO dto,@RequestParam(required=false) String[] files,@RequestParam(required=false) String[] filesName,@RequestParam(required=false) String[] filesType, HttpServletRequest request) throws ParseException, UnsupportedEncodingException{
		dto.setAttachment(files!=null?'P':'N');
		dto.setId((String) request.getSession().getAttribute("id"));
		if(!service.CLExit(dto)){
			return "redirect:/"+"?Code=alert('"+URLEncoder.encode("타인의 강좌에는 강의를 업로할 수가 없습니다!", "UTF-8")+"');";
		}
		return "redirect:/lecture?cl_no="+dto.getCl_no()+"&Code=alert('"+URLEncoder.encode(fileService.insert(files , filesType , filesName , service.write(dto))?"게시글이 등록되었습니다":"강의 등록에 실패했습니다", "UTF-8")+"');";
		
	}
	@RequestMapping(value="/edit",method = { RequestMethod.GET })
	public String edit(Model model, @RequestParam String no, @RequestHeader("user-agent") String agent, HttpSession session) throws UnsupportedEncodingException {
		LectureDTO dto = service.detail(no,false);
		if(!dto.getId().equals(session.getAttribute("id"))) {
			return "redirect:/lecture?Code=alert('"+URLEncoder.encode("타인의 강의물은 수정할 수 없습니다!", "UTF-8")+"');";
		}
		if(dto.getContents().contains("${linked}")) {
			dto.setContents(new JFileWriter().readFile(dto.getContents().replace("${linked}", "")));
		}
		if(dto.getAttachment()=='P') {
			List<FileDTO> list = fileService.getAttachment(dto.getNo());
			model.addAttribute("attachment", list);
			for(FileDTO filedto:list) {
				if(dto.getContents().contains("<img:"+filedto.getOrder()+">")) {
					filedto.setImage(true);
					dto.setContents(dto.getContents().replaceFirst("<img:"+filedto.getOrder()+">", filedto.getPath()));
				}
			}
		}
		model.addAttribute("board", dto);
		model.addAttribute("cl_no", dto.getCl_no());
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
		dto.setId((String) request.getSession().getAttribute("id"));
		if(!service.LExit(dto)) {
			return "redirect:/lecture?Code=alert('"+URLEncoder.encode("타인의 강의물은 수정할 수 없습니다!", "UTF-8")+"');";
		}
		dto.setAttachment(files!=null?'P':'N');
		if(dto.getStatus()=='N') {
			dto.setAttachment('N');
			files=null;
			filesName=null;
			filesType=null;
		}
		return "redirect:/lecture?cl_no="+dto.getCl_no()+"&Code=alert('"+URLEncoder.encode(fileService.update(files , filesType , filesName , service.update(dto))?(dto.getStatus()=='N'?"강의글이 삭제되었습니다":"강의글이 수정되었습니다"):"수정에 실패했습니다", "UTF-8")+"');";
	}
}
