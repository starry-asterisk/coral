package com.coral.www;








import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coral.www.application.JTester;

@Controller
@RequestMapping("/ajax")
public class AjaxController {
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
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
		JSONObject returnObj = new JSONObject();
		String result="옳바르게 클래스 이름을 작성해 주세요";
		String fileName;
		try {
			fileName = receive.substring(receive.indexOf("class ")+5,receive.indexOf("{")).trim();
			JTester tester = new JTester(fileName,receive);
			result = tester.javac();
			result += tester.java();
			tester.close();
		}catch(Exception e) {}
		
		returnObj.put("result",result);
		returnObj.put("success", true);
		return returnObj.toJSONString();
	}
	@Inject
	UserService userService;
	@ResponseBody
	@RequestMapping("/login")
	public String login(@RequestParam String json, HttpServletRequest request) throws ParseException {
		UserDTO dto = new UserDTO();
		JSONParser p = new JSONParser();
		JSONObject receive = (JSONObject) p.parse(json);
		dto.setId((String) receive.get("id"));
		dto.setPw((String) receive.get("pw"));
		dto = userService.login(dto, request);
		JSONObject returnObj = new JSONObject();
		returnObj.put("success", dto.getLogin_status()==1?true:false);
		return returnObj.toJSONString();
	}
}
