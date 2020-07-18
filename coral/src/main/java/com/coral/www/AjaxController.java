/* 
 * AjaxController.java		1.0.0 2020.01.31
 * 
 * Copyright all reserved coral
 */

package com.coral.www;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.coral.www.Board.BoardService;
import com.coral.www.File.FileService;
import com.coral.www.Lecture.LectureDTO;
import com.coral.www.Lecture.LectureService;
import com.coral.www.Report.ReasonDTO;
import com.coral.www.Report.ReportDTO;
import com.coral.www.Report.ReportService;
import com.coral.www.User.UserDTO;
import com.coral.www.User.UserService;
import com.coral.www.application.JTester;
import com.coral.www.like.LikeDTO;
import com.coral.www.like.LikeService;
import com.coral.www.like.ReplyDTO;
import com.coral.www.like.ReplyService;

/**
 * @version			1.0.0 2020.01.31
 * @author			김현우, 이창현, 박승리, 백현욱, 장지수
 */
@Controller
@RequestMapping(value = "/ajax", method = RequestMethod.POST)
public class AjaxController {
	/* Ajax요청들을 처리하기 위한 전용 컨트롤러 입니다 */
	
	/** 사용자 서비스 */
	@Inject
	UserService userService;
	
	/** 신고 서비스 */
	@Inject
	ReportService reportService;
	
	/** 게시판 서비스 */
	@Inject
	BoardService boardService;
	/** 강좌&강의 서비스 */
	@Inject
	LectureService lectureService;
	/** 첨부파일 서비스 */
	@Inject
	FileService fileService;
	/** 댓글 서비스 */
	@Inject
	ReplyService replyService;
	/** 좋아요 서비스 */
	@Inject
	LikeService likeService;
	
	
	/**
	 * 코드 실행 메소드
	 * 
	 * @param receive
	 * 작성한 코드
	 * @return 
	 * 실행결과 
	 */
	@ResponseBody
	@RequestMapping(value = "/runCode", produces="application/text;charset=utf-8")
	public String runCode(@RequestParam("code")String receive) {
		String result="옳바르게 클래스 이름을 작성해 주세요";
		String fileName;
		try {
			fileName = receive.substring(receive.indexOf("class ")+5,receive.indexOf("{")).trim();
			JTester tester = new JTester(fileName,receive);
			result = tester.javac();
			result += tester.java();
			tester.close();
			
		}catch(Exception e) {}
		return result;
	}
	
	/**
	 * 아이디 중복확인
	 * 
	 * @param id
	 * @return (String) boolean
	 */
	@ResponseBody
	@RequestMapping(value = "/idExit", produces="application/text;charset=utf-8")
	public String idExit(@RequestParam("id")String id) {
		return !userService.isId(id)+"";
	}
	
	/**
	 * 메일 중복확인
	 * 
	 * @param mail
	 * @return (String) boolean
	 */
	@ResponseBody
	@RequestMapping(value = "/mailExit", produces="application/text;charset=utf-8")
	public String mailExit(@RequestParam("mail")String mail) {
		UserDTO dto = new UserDTO();
		dto.setMail(mail);
		return !userService.isMail(dto)+"";
	}
	
	/**
	 * 사유 목록 조회
	 * 
	 * @param identifier
	 * 사유 타입
	 * @return List<ReasonDTO>
	 */
	@ResponseBody
	@RequestMapping(value = "/getReason")
	public List<ReasonDTO> getReason(@RequestParam("identifier")char identifier) {
		List<ReasonDTO> list = reportService.reasonList(identifier);
		return list;
	}
	
	/**
	 * 신고 하기
	 * 
	 * @param dto
	 * 신고 정보
	 * @param session
	 * 사용자 정보
	 * @return (String) boolean
	 */
	@ResponseBody
	@RequestMapping(value = "/report", produces="application/text;charset=utf-8")
	public String report(ReportDTO dto,HttpSession session) {
		dto.setId((String) session.getAttribute("id"));
		if(lectureService.isCL(dto.getObject())) {
			LectureDTO ldto = new LectureDTO();
			ldto.setCl_no(dto.getObject());
			ldto.setId(dto.getId());
			if(lectureService.CLExit(ldto)) {
				return (reportService.insertReport(dto)!=null)+"";
			}else {
				return "false";
			}
		}
		return (reportService.insertReport(dto)!=null)+"";
	}
	
	/**
	 * 좋아요 처리
	 * 
	 * @param session
	 * 사용자 정보
	 * @param bno
	 * 게시물 번호
	 * @param thumb
	 * 좋아요/싫어요 구분
	 * @return boolean
	 */
	@Transactional
	@ResponseBody
	@RequestMapping(value = "/like",method = { RequestMethod.POST })
	public boolean like(HttpSession session,@RequestParam String bno, @RequestParam int thumb ) {
		LikeDTO dto = likeService.isLiked(bno,(String)session.getAttribute("id"),thumb);
		if(dto!=null) {
			if(lectureService.detail(bno,false)!=null) {
				return likeService.insert(dto)&&lectureService.likeUpd(bno, thumb);
			}else {
				return likeService.insert(dto)&&boardService.likeUpd(bno, thumb);
			}
			
		}
		return false;
	}
	
	/**
	 * 댓글 작성
	 * 
	 * @param session
	 * 작성자
	 * @param bno
	 * 게시물번호
	 * @param content
	 * 댓글 내용
	 * @return boolean
	 */
	@Transactional
	@ResponseBody
	@RequestMapping(value = "/replySend",method = { RequestMethod.POST })
	public boolean replySend(HttpSession session,@RequestParam String bno,@RequestParam String content) {
		return replyService.send(bno,(String)session.getAttribute("id"),content);
	}
	
	/**
	 * 댓글 수정
	 * 
	 * @param session
	 * 사용자 정보
	 * @param dto
	 * 댓글 정보
	 * @return boolean
	 */
	@Transactional
	@ResponseBody
	@RequestMapping(value = "/replyUpd",method = { RequestMethod.POST })
	public boolean replyUpd(HttpSession session,ReplyDTO dto) {
		if(dto.getStatus()=='P') {
			return false;
		}else {
			dto.setId((String)session.getAttribute("id"));
			return replyService.update(dto);
		}
	}
	
	/**
	 * 댓글 목록
	 * 
	 * @param bno
	 * 게시물 번호
	 * @return 댓글목록
	 */
	@Transactional
	@ResponseBody
	@RequestMapping(value = "/replyList",method = { RequestMethod.POST })
	public List<ReplyDTO> replyList(@RequestParam String bno) {
		return replyService.getList(bno);
	}
	
	/**
	 * 프로필 이미지 등록
	 * 
	 * @param session
	 * 사용자 정보
	 * @param file
	 * 이미지
	 * @return 이미지 URI
	 */
	@Transactional
	@ResponseBody
	@RequestMapping(value="/newProfImg",method = { RequestMethod.POST })
	public String updProfImg(HttpSession session,@RequestParam("file") MultipartFile file) {
		return fileService.newProfImg(file, (String)session.getAttribute("id"));
	}
}
