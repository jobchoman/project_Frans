package com.frans.stats.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SubStatController {

	@GetMapping(value="/subStat")
	public ModelAndView subStat() {
		
		return new ModelAndView("subStat");
	}
	
	
}
