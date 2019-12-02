package com.coral.www;








import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public String login(@RequestParam String json, 
						@RequestHeader("user-agent") String agent,
						HttpServletRequest request,
						HttpSession session) throws ParseException {
		
		JSONParser p = new JSONParser();
		JSONObject receive = (JSONObject) p.parse(json);
		JSONObject returnObj = new JSONObject();
		UserDTO dto = new UserDTO();
		dto.setIp(request.getRemoteAddr());
		dto.setPlatform(agent);
		if(session.getAttribute("login")==null) {
			
			dto.setId((String) receive.get("id"));
			dto.setPw((String) receive.get("pw"));
			dto.setLogin_status(1);
			dto = userService.login(dto);
			if(dto.getMsg()==null) {
				session.setAttribute("login", true);
				session.setAttribute("id", receive.get("id"));
				session.setAttribute("pw", receive.get("pw"));
				session.setAttribute("user-agent", agent);
				session.setAttribute("ip", request.getRemoteAddr());
				returnObj.put("success", true);
			}else {
				returnObj.put("success", false);
				returnObj.put("errorMsg",dto.getMsg());
			}
		}else {
			dto.setId((String) session.getAttribute("id"));
			dto.setPw((String) session.getAttribute("pw"));
			dto = userService.getInfo(dto);
		}
		return returnObj.toJSONString();
	}
}
