package com.frans.stats.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.frans.stats.service.StatService;


@RestController
public class StatController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired StatService statservice;
	

	@GetMapping(value="/subStat")
	public ModelAndView subStat() {
		
		return new ModelAndView("subStat");
	}
	
	@GetMapping(value="/sub/provinceList.do")
	public HashMap<String, Object> provinceListCall() {
		
		return statservice.provinceListCall();
	}
	
	@GetMapping(value="/sub/cityList.do")
	public HashMap<String, Object> cityListCall(@RequestParam int idx) {
		logger.info("proinceIdx = "+idx);
		return statservice.cityListCall(idx);
	}
	
	@GetMapping(value="/sub/shopList.do")
	public HashMap<String, Object> shopListCall(@RequestParam int idx) {
		logger.info("cityIdx = "+idx);
		return statservice.shopListCall(idx);
	}
	
	@GetMapping(value="/sub/subList.do")
	public HashMap<String, Object> subListCall(@RequestParam String subSort) {
		logger.info(subSort);
		return statservice.subListCall(subSort);
	}
	
}
