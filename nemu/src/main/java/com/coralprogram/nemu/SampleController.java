package com.coralprogram.nemu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@RequestMapping("/sample/*")
@Controller
public class SampleController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping("/all")
	public void doAll() {
		
		logger.info("doo all can access");
	}
	
	@RequestMapping("/member")
	public void doMember() {
		
		logger.info("pls login first");
	}
	@RequestMapping("/admin")
	public void doAdmin() {
		
		logger.info("admin only can access");
	}
	
}
