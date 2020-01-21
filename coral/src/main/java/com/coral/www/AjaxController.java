package com.coral.www;



import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.coral.www.Board.BoardService;
import com.coral.www.File.FileService;
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

@Controller
@RequestMapping(value = "/ajax", method = RequestMethod.POST)
public class AjaxController {
	
	@Inject
	UserService userService;
	@Inject
	ReportService reportService;
	@Inject
	FileService fileService;
	@Inject
	LikeService likeService;
	@Inject
	ReplyService replyService;
	@Inject
	BoardService boardService;
	@Inject
	LectureService lectureService;
	
	@ResponseBody
	@RequestMapping(value = "/search")
	public JSONObject search(@RequestParam("json")String receive) {
		JSONObject returnObj = new JSONObject();
		returnObj.put("success", true);
		return returnObj;
	}
	
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
	
	@ResponseBody
	@RequestMapping(value = "/idExit", produces="application/text;charset=utf-8")
	public String idExit(@RequestParam("id")String id) {
		return !userService.isId(id)+"";
	}
	@ResponseBody
	@RequestMapping(value = "/mailExit", produces="application/text;charset=utf-8")
	public String mailExit(@RequestParam("mail")String mail) {
		UserDTO dto = new UserDTO();
		dto.setMail(mail);
		return !userService.isMail(dto)+"";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getReason")
	public List<ReasonDTO> getReason(@RequestParam("identifier")char identifier) {
		List<ReasonDTO> list = reportService.reasonList(identifier);
		return list;
	}
	@ResponseBody
	@RequestMapping(value = "/report", produces="application/text;charset=utf-8")
	public String report(ReportDTO dto,HttpSession session) {
		dto.setId((String) session.getAttribute("id"));
		return (reportService.insertReport(dto)!=null)+"";
	}
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
	@Transactional
	@ResponseBody
	@RequestMapping(value = "/replySend",method = { RequestMethod.POST })
	public boolean replySend(HttpSession session,@RequestParam String bno,@RequestParam String content) {
		return replyService.send(bno,(String)session.getAttribute("id"),content);
	}
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
	@Transactional
	@ResponseBody
	@RequestMapping(value = "/replyList",method = { RequestMethod.POST })
	public List<ReplyDTO> replyList(@RequestParam String bno) {
		return replyService.getList(bno);
	}
	@Transactional
	@ResponseBody
	@RequestMapping(value="/newProfImg",method = { RequestMethod.POST })
	public String updProfImg(HttpSession session,@RequestParam("file") MultipartFile file) {
		return fileService.newProfImg(file, (String)session.getAttribute("id"));
	}
	
}
