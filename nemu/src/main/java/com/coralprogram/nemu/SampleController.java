package com.coralprogram.nemu;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		
		logger.info("a");
	}
	
	@RequestMapping("/member")
	public void doMember() {
		
		logger.info("b");
	}
	@RequestMapping("/admin")
	public void doAdmin() {
		
		logger.info("c");
	}
	
}
