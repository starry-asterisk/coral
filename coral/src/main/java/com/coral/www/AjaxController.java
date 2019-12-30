package com.coral.www;



import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.coral.www.Report.ReasonDTO;
import com.coral.www.Report.ReportDTO;
import com.coral.www.Report.ReportService;
import com.coral.www.User.UserDTO;
import com.coral.www.User.UserService;
import com.coral.www.application.FileUploadService;
import com.coral.www.application.JTester;

@Controller
@RequestMapping("/ajax")
public class AjaxController {
	
	@Inject
	UserService userService;
	@Inject
	ReportService reportService;
	@Inject
	FileUploadService fileUploadService;
	
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(@RequestParam("json")String receive) {
		JSONObject returnObj = new JSONObject();
		returnObj.put("success", true);
		return returnObj.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/runCode", method = RequestMethod.POST, produces="application/text;charset=utf-8")
	public String runCode(@RequestParam("json")String receive) {
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
	@RequestMapping(value = "/idExit", method = RequestMethod.POST, produces="application/text;charset=utf-8")
	public String idExit(@RequestParam("id")String id) {
		return !userService.isId(id)+"";
	}
	@ResponseBody
	@RequestMapping(value = "/mailExit", method = RequestMethod.POST, produces="application/text;charset=utf-8")
	public String mailExit(@RequestParam("mail")String mail) {
		UserDTO dto = new UserDTO();
		dto.setMail(mail);
		return !userService.isMail(dto)+"";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getReason", method = RequestMethod.POST, produces="application/text;charset=utf-8")
	public String getReason(@RequestParam("identifier")char identifier) {
		JSONObject returnObj = new JSONObject();
		JSONArray returnlist = new JSONArray();
		for(ReasonDTO dto:reportService.reasonList(identifier)) {
			returnlist.add(dto.getCode()+":"+dto.getContent());
		}
		returnObj.put("result",returnlist);
		returnObj.put("success", true);
		return returnObj.toJSONString();
	}
	@ResponseBody
	@RequestMapping(value = "/report", method = RequestMethod.POST, produces="application/text;charset=utf-8")
	public String report(@RequestParam("id")String object,@RequestParam("rscode")String rscode,@RequestParam("type")String code,HttpSession session) {
		String value = "false";
		ReportDTO dto = new ReportDTO();
		dto.setRscode(rscode);
		dto.setCode(code);
		dto.setObject(object);
		dto.setId((String) session.getAttribute("id"));
		if(reportService.insertReport(dto)!=null) {
			value = "true";
		}
		return value;
	}
	@ResponseBody
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request,@RequestParam(required=false) List<MultipartFile> files) {
		String url="";
		for(MultipartFile file:files) {
			url += fileUploadService.restore(file)+";";
		}
		return url;
	}
}
