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

@RequestMapping("/board")
@Controller
public class BoardController {
	@Inject
	BoardService service;

	@Inject
	FileService fileService;

	@RequestMapping("")
	public String board(Model model, HttpServletRequest request, @RequestParam(required=false) String isAjax) {
		service.addList(model, request);
		if(isAjax!=null) {
			return "include/board";
		}
		model.addAttribute("Board_type", "include/board");
		model.addAttribute("attachment", "include/Bside");
		return "list";
	}
	@RequestMapping("/detail")
	public String detail(Model model, @RequestParam String bno, @RequestHeader("user-agent") String agent) {
		BoardDTO boarddto = service.detail(bno);
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
	
	@RequestMapping(value="/write",method = { RequestMethod.GET })
	public String editor(Model model, HttpServletRequest request) {
		model.addAttribute("Category", service.categorylist());
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
	public String upload(BoardDTO dto,@RequestParam(required=false) String[] files,@RequestParam(required=false) String[] filesName,@RequestParam(required=false) String[] filesType, HttpServletRequest request) throws ParseException, UnsupportedEncodingException{
		dto.setAttachment(files!=null?'P':'N');
		dto.setId((String) request.getSession().getAttribute("id"));
		return "redirect:/board"+"?Code=alert('"+URLEncoder.encode(fileService.insert(files , filesType , filesName , service.write(dto))?"게시글이 등록되었습니다":"등록에 실패했습니다", "UTF-8")+"');";
	}
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
		model.addAttribute("Category", service.categorylist());
		model.addAttribute("isNew", false);
		return "editor";
	}
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
