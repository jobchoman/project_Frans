package com.frans.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	   @GetMapping(value = "/")
	   public String index() {
	      return "index";
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

}
