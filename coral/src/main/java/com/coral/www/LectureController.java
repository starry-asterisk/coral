/* 
 * LectureController.java		1.0.0 2020.01.31
 * 
 * Copyright all reserved coral
 */

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

/**
 * @version			1.0.0 2020.01.31
 * @author			김현우, 이창현, 박승리, 백현욱, 장지수
 */
@RequestMapping("/lecture")
@Controller
public class LectureController {
	/* 강좌&강의 관리 컨트롤러 입니다 */
	
	/** 강좌&강의 서비스 */
	@Inject
	LectureService service;

	/** 첨푸파일 서비스 */
	@Inject
	FileService fileService;

	/**
	 * 강좌&강의 목록
	 * 
	 * @param model
	 * @param request
	 * @param cl_no
	 * 강좌 번호가 있다면 강의목록을 보여준다
	 * @param keyword
	 * 검색어
	 * @param isAjax
	 * Ajax 구분용 변수
	 * @return
	 */
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
	
	
	
	/**
	 * 강좌 개설
	 * 
	 * @param dto
	 * 강좌 정보
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
	@Transactional
	@RequestMapping(value="/create",method = { RequestMethod.POST })
	public String create(LectureDTO dto, HttpServletRequest request) throws ParseException, UnsupportedEncodingException{
		dto.setId((String) request.getSession().getAttribute("id"));
		return "redirect:/lecture"+"?Code=alert('"+URLEncoder.encode(service.create(dto)?"게시글이 등록되었습니다":"등록에 실패했습니다", "UTF-8")+"');";
	}
	
	/**
	 * 강좌 수정
	 * 
	 * @param dto
	 * 강좌 정보
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
	@Transactional
	@RequestMapping(value="/update",method = { RequestMethod.POST })
	public String uppdate(LectureDTO dto , HttpServletRequest request) throws ParseException, UnsupportedEncodingException{
		dto.setId((String) request.getSession().getAttribute("id"));
		return "redirect:/lecture"+"?Code=alert('"+URLEncoder.encode((service.updateCL(dto)?(dto.getStatus()=='N'?"게시글이 삭제되었습니다":"게시글이 수정되었습니다"):"수정에 실패했습니다"), "UTF-8")+"');";
	}
	
	/**
	 * 강의 조회
	 * 
	 * @param model
	 * @param no
	 * 강의 번호
	 * @param agent
	 * 클라이언트 정보
	 * @return
	 */
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
	
	/**
	 * 강의 작성 페이지로 이동
	 * 
	 * @param model
	 * @param cl_no
	 * 강좌 번호
	 * @param agent
	 * 클라이언트 정보
	 * @param session
	 * 계정 정보
	 * @return
	 * @throws Exception
	 */
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
	
	/**
	 * 강의 업로드
	 * 
	 * @param dto
	 * 강의 정보
	 * @param files
	 * @param filesName
	 * @param filesType
	 * 첨부파일 정보
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/write",method = { RequestMethod.POST })
	public String upload(LectureDTO dto,@RequestParam(required=false) String[] files,@RequestParam(required=false) String[] filesName,@RequestParam(required=false) String[] filesType, HttpServletRequest request) throws ParseException, UnsupportedEncodingException{
		dto.setAttachment(files!=null?'P':'N');
		dto.setId((String) request.getSession().getAttribute("id"));
		if(!service.CLExit(dto)){
			return "redirect:/"+"?Code=alert('"+URLEncoder.encode("타인의 강좌에는 강의를 업로할 수가 없습니다!", "UTF-8")+"');";
		}
		return "redirect:/lecture?cl_no="+dto.getCl_no()+"&Code=alert('"+URLEncoder.encode(fileService.insert(files , filesType , filesName , service.write(dto))?"게시글이 등록되었습니다":"강의 등록에 실패했습니다", "UTF-8")+"');";
		
	}
	
	/**
	 * 수정 페이지로 이동
	 * 
	 * @param model
	 * @param no
	 * 강의 번호
	 * @param agent
	 * 클라이언트 정보
	 * @param session
	 * 계정 정보
	 * @return
	 * @throws UnsupportedEncodingException
	 */
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
	
	/**
	 * 강의 수정 적용
	 * 
	 * @param dto
	 * 강의 정보
	 * @param files
	 * @param filesName
	 * @param filesType
	 * 첨부파일 정보
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
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
