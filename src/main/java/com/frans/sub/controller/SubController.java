package com.frans.sub.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.frans.sub.service.SubService;

@Controller
public class SubController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired SubService subService;
	
	@GetMapping(value="/subRegister")
	public String subRegister(Model model) {
		
		model.addAttribute("menuList", subService.menuList());
		
		return "subRegister";
	}
	
}
