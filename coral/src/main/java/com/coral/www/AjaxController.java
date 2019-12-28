package com.coral.www;



import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coral.www.Report.ReportDTO;
import com.coral.www.Report.ReportService;
import com.coral.www.User.UserDTO;
import com.coral.www.User.UserService;
import com.coral.www.application.JTester;

@Controller
@RequestMapping("/ajax")
public class AjaxController {
	
	@Inject
	UserService userService;
	@Inject
	ReportService reportService;
	
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
		for(ReportDTO dto:reportService.reasonList(identifier)) {
			returnlist.add(dto.getCode()+":"+dto.getContent());
		}
		returnObj.put("result",returnlist);
		returnObj.put("success", true);
		return returnObj.toJSONString();
	}
	@ResponseBody
	@RequestMapping(value = "/report", method = RequestMethod.POST, produces="application/text;charset=utf-8")
	public String report(@RequestParam("id")String id,@RequestParam("rscode")String rscode,@RequestParam("type")String type) {
		System.out.println("\n"+id+"/"+type+"/"+rscode);
		return true+"";
	}
}
