package com.coral.www;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.coral.www.Board.BoardDTO;
import com.coral.www.File.FileService;
import com.coral.www.Report.ReasonDTO;
import com.coral.www.Report.ReportDTO;
import com.coral.www.Report.ReportService;
import com.coral.www.User.UserDTO;
import com.coral.www.User.UserService;
import com.coral.www.application.JTester;

@Controller
@RequestMapping(value = "/ajax", method = RequestMethod.POST)
public class AjaxController {
	
	@Inject
	UserService userService;
	@Inject
	ReportService reportService;
	@Inject
	FileService fileService;
	
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
	@RequestMapping(value="/newProfImg",method = { RequestMethod.POST })
	public String updProfImg(HttpSession session,@RequestParam("file") MultipartFile file) {
		return fileService.newProfImg(file, (String)session.getAttribute("id"));
	}
}
