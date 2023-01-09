package com.frans.sign.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.frans.sign.dto.DocFormDTO;
import com.frans.sign.service.DocFormService;
import com.frans.sign.service.SignService;

@Controller
public class SignController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired SignService signservice;
	@Autowired DocFormService docformservice;
	
	/* 결재 문서 작성 */
	
	
	/* 결재 문서 리스트 */
	@ResponseBody
	@GetMapping(value="/sign/list.do")
	public HashMap<String, Object> signList(){
		logger.info("결재 문서 리스트 컨트롤러");
		return signservice.signList();
	}
	
	@ResponseBody
	@GetMapping(value="/sign/dateSearch.do")
	public HashMap<String, Object> dateSearch(@RequestParam HashMap<String, String> params){
		logger.info("결재 문서 기간 검색 컨트롤러");
//		logger.info("params:{}",params);
		String date1 = params.get("date1");
		String date2 = params.get("date2");
//		logger.info(date1+"/"+date2);
		
		return signservice.dateSearch(date1,date2);
	}
	
	@GetMapping(value="/signWrite.go")
	public ModelAndView signWriteGo(@RequestParam String doc_form_idx){
		logger.info("결재 문서 작성 이동 컨트롤러");
		logger.info("idx: "+doc_form_idx);
		
		return signservice.signWriteGo(doc_form_idx);
	}
	
}



