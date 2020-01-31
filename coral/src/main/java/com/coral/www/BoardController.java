/* 
 * BoardController.java		1.0.0 2020.01.31
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

import com.coral.www.Board.BoardDTO;
import com.coral.www.Board.BoardService;
import com.coral.www.File.FileDTO;
import com.coral.www.File.FileService;
import com.coral.www.application.JFileWriter;

/**
 * @version			1.0.0 2020.01.31
 * @author			김현우, 이창현, 박승리, 백현욱, 장지수
 */
@RequestMapping("/board")
@Controller
public class BoardController {
	/* 게시판 관리 컨트롤러 입니다 */
	
	/** 게시판 service */
	@Inject
	BoardService service;
	
	/** 첨부파일 service */
	@Inject
	FileService fileService;
	
	/**
	 * 게시물 목록
	 * 
	 * @param model
	 * @param request
	 * @param keyword
	 * 검색어
	 * @param isAjax 
	 * Ajax 구분 변수
	 * @return
	 */
	@RequestMapping("")
	public String board(Model model, HttpServletRequest request, @RequestParam(required=false) String keyword, @RequestParam(required=false) String isAjax) {
		service.addList(model, request, keyword);
		if(isAjax!=null) {
			return "include/board";
		}else {
			model.addAttribute("Category", service.categorylist(null));
		}
		model.addAttribute("Board_type", "include/board");
		model.addAttribute("attachment", "include/Bside");
		return "list";
	}
	
	/**
	 * 게시물 상세 페이지
	 * 
	 * @param model
	 * @param bno
	 * 게시물 번호
	 * @param agent
	 * 클라이언트 정보
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(Model model, @RequestParam String bno, @RequestHeader("user-agent") String agent) {
		BoardDTO boarddto = service.detail(bno);
		if(boarddto==null) {
			return "blank";
		}
		if(boarddto.getContents().contains("${linked}")) {
			boarddto.setContents(new JFileWriter().readFile(boarddto.getContents().replace("${linked}", "")));
		}
		if(boarddto.getAttachment()=='P') {
			List<FileDTO> list = fileService.getAttachment(boarddto.getNo());
			model.addAttribute("attachment", list);
			for(FileDTO filedto:list) {
				boarddto.setContents(boarddto.getContents().replaceFirst("<img:"+filedto.getOrder()+">", filedto.getPath()));
				/*기존 게시물 때문에 달림 나중에 정식판에선 삭제할것*/
				boarddto.setContents(boarddto.getContents().replaceFirst("<img:>", filedto.getPath()));
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
	
	/**
	 * 게시물 작성 페이지로 이동
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/write",method = { RequestMethod.GET })
	public String editor(Model model, HttpServletRequest request) {
		model.addAttribute("Category", service.categorylist((String)request.getSession().getAttribute("grade")));
		String agent = request.getHeader("user-agent");
		if(agent.contains("MSIE")||agent.contains("Trident")) {
			model.addAttribute("include", "include/ckEdit4");
		}else {
			model.addAttribute("include", "include/ckEdit5");
		}
		return "editor";
	}
	
	/**
	 * 게시물 등록
	 * 
	 * @param dto
	 * 게시물 정보
	 * @param files
	 * @param filesName
	 * @param filesType
	 * 첨부 파일 정보
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
	@Transactional
	@RequestMapping(value="/write",method = { RequestMethod.POST })
	public String upload(BoardDTO dto,@RequestParam(required=false) String[] files,@RequestParam(required=false) String[] filesName,@RequestParam(required=false) String[] filesType, HttpServletRequest request) throws ParseException, UnsupportedEncodingException{
		dto.setAttachment(files!=null?'P':'N');
		dto.setId((String) request.getSession().getAttribute("id"));
		return "redirect:/board"+"?Code=alert('"+URLEncoder.encode(fileService.insert(files , filesType , filesName , service.write(dto))?"게시글이 등록되었습니다":"등록에 실패했습니다", "UTF-8")+"');";
	}
	
	/**
	 * 게시물 수정 페이지로 이동
	 * 
	 * @param model
	 * @param bno
	 * 게시물 번호
	 * @param agent
	 * 클라이언트 정보
	 * @param session
	 * id값 추출
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/edit",method = { RequestMethod.GET })
	public String edit(Model model, @RequestParam String bno, @RequestHeader("user-agent") String agent, HttpSession session) throws UnsupportedEncodingException {
		BoardDTO boarddto = service.detail(bno);
		if(boarddto==null||!boarddto.getId().equals(session.getAttribute("id"))) {
			return "redirect:/"+"?Code=alert('"+URLEncoder.encode("타인의 게시물은 수정할 수 없습니다!", "UTF-8")+"');";
		}
		if(boarddto.getContents().contains("${linked}")) {
			boarddto.setContents(new JFileWriter().readFile(boarddto.getContents().replace("${linked}", "")));
		}
		if(boarddto.getAttachment()=='P') {
			List<FileDTO> list = fileService.getAttachment(boarddto.getNo());
			model.addAttribute("attachment", list);
			for(FileDTO filedto:list) {
				if(boarddto.getContents().contains("<img:"+filedto.getOrder()+">")) {
					filedto.setImage(true);
					boarddto.setContents(boarddto.getContents().replaceFirst("<img:"+filedto.getOrder()+">", filedto.getPath()));
				}
			}
		}
		model.addAttribute("board", boarddto);
		if(agent.contains("MSIE")||agent.contains("Trident")) {
			model.addAttribute("include", "include/ckEdit4");
		}else {
			model.addAttribute("include", "include/ckEdit5");
		}
		model.addAttribute("Category", service.categorylist((String)session.getAttribute("grade")));
		
		model.addAttribute("isNew", false);
		return "editor";
	}
	/**
	 * 게시물 수정 적용
	 * 
	 * @param dto
	 * 게시물 정보
	 * @param files
	 * @param filesName
	 * @param filesType
	 * 파일 정보
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
	@Transactional
	@RequestMapping(value="/edit",method = { RequestMethod.POST })
	public String uppdate(BoardDTO dto,@RequestParam(required=false) String[] files,@RequestParam(required=false) String[] filesName,@RequestParam(required=false) String[] filesType, HttpServletRequest request) throws ParseException, UnsupportedEncodingException{
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
