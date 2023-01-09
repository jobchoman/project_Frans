package com.frans.member.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.frans.member.service.OrgChartService;

@RestController
public class OrgChartController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired OrgChartService orgChartService;
	
//	@GetMapping(value="/orgChartEmpList.go")
//	public String orgChartEmpList() {
//		
//		return "orgChartEmpList";
//	}
	
	@RequestMapping(value="/orgChartList.go")
	public ModelAndView orgChartEmpList() {
		// @RestController 로 인해서 문자열이 그대로 전달 - 프로젝트 실행하면 index 라는 문자열이 찍힘
		// 해결 방법은 ModelAndView 사용
		return new ModelAndView("orgChartEmpList");
	}
	
	@RequestMapping(value="/addEmpPopup")
	public ModelAndView addEmpPopup() {
		return new ModelAndView("addEmpPopup");
	}
	
	@PostMapping(value="/orgChartSearch.do")
	public HashMap<String, Object> searchEmp(@RequestParam String emp_name) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		logger.info("검색어 = "+emp_name);

		map.put("empList", orgChartService.searchEmp(emp_name));
		
		return map;
	}
	
}
