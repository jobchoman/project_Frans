package com.frans.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frans.main.service.MainService;

@Controller
public class MainController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MainService mainservice;

	   @GetMapping(value = "/")
	   public String index() {
	      return "memberLogin";
	   }
	   
	   @RequestMapping(value = "/{page}.go")
	   public String pageMove(@PathVariable String page) {
	      logger.info("page move : "+page);
	      
	      return page;
	   }
	   
	   @RequestMapping(value = "{root}/{sub}/{page}.go")
	   public String pageMove(@PathVariable String root, 
	         @PathVariable String sub,
	         @PathVariable String page) {
	      logger.info("page move : {}/{}/{}",root,sub,page);
	      
	      return root+"/"+sub+"/"+page;
	   }
	   
	   @RequestMapping(value="/main.go")
	   public ModelAndView main(HttpServletRequest req) {
		   logger.info("메인 구독권 리스트");
		   HttpSession session = req.getSession();
		   String loginId = (String) session.getAttribute("loginId");
		   return mainservice.main(loginId);
	   }

}
