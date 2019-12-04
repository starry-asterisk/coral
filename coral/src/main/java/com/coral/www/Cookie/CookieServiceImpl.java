package com.coral.www.Cookie;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

@Service
public class CookieServiceImpl implements CookieService {
	@Inject
	private CookieDAO dao;
	private CookieDTO dto ;
	private JSONObject json;
	@Override
	public Boolean delete(HttpServletResponse response, Cookie loginCookie) {
		try {
	    	if(loginCookie!=null) {
	    		dto = new CookieDTO();
	    		json = (JSONObject) new JSONParser().parse(URLDecoder.decode(loginCookie.getValue(), "UTF-8"));
	    		dto.setSeries((String) json.get("series"));
	    		dao.delete(dto);
	    		loginCookie = new Cookie("loginCookie",null);
	    		loginCookie.setValue(null);
	    		loginCookie.setDomain("www.coralprogram.com");
	    		loginCookie.setPath("/");
	    		loginCookie.setMaxAge(0);
	    		response.addCookie(loginCookie);
	    	}
			return true;
		}catch( Exception e) {
			return false;
		}
	}

	@Override
	public Boolean refresh(HttpServletResponse response, Cookie loginCookie) {
		try {
	    	if(loginCookie!=null) {
	    		dto = new CookieDTO();
	    		json = (JSONObject) new JSONParser().parse(URLDecoder.decode(loginCookie.getValue(), "UTF-8"));
	    		dto.setSeries((String) json.get("series"));
	    		if(dao.series(dto)) {
	    			dto.setToken((String) json.get("token"));
	    			dto=dao.token(dto);
	    			if(dto!=null) {
	    				dto.setToken(UUID.randomUUID().toString());
	    				json.put("token", dto.getToken());
	        			loginCookie.setValue(URLEncoder.encode(json.toJSONString(), "UTF-8"));
						loginCookie.setDomain("www.coralprogram.com");
						loginCookie.setPath("/");
						loginCookie.setMaxAge(604800);
						response.addCookie(loginCookie);
						dao.update(dto);
						return true;
	    			}else {
	    				return delete(response, loginCookie);
	    			}
	    		}
	    	}
		}catch( Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean create(HttpServletResponse response, HttpSession session) {
		try {
			dto = new CookieDTO();
			dto.setId((String) session.getAttribute("id"));
			dto.setSeries(UUID.randomUUID().toString());
			dto.setToken(session.getId());
			String value = "{\"id\":\""+dto.getId()+
					"\",\"series\":\""+dto.getSeries()+
					"\",\"token\":\""+dto.getToken()+"\"}";
			Cookie loginCookie = new Cookie("loginCookie",URLEncoder.encode(value, "UTF-8"));
			loginCookie.setDomain("www.coralprogram.com");
			loginCookie.setPath("/");
			loginCookie.setMaxAge(604800);
			response.addCookie(loginCookie);
			dao.insert(dto);
			return true;
		}catch( Exception e) {
			return false;
		}
	}
}
