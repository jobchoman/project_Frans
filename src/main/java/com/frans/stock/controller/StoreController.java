package com.frans.stock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.frans.stock.service.StoreService;

@Controller
public class StoreController {
	
	@Autowired StoreService storeservice;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	/* 매장 리스트 */
	@GetMapping(value="/storeList.go")
	public ModelAndView storeList() {
		logger.info("매장 리스트 컨트롤러");
		return storeservice.storeList();
	}
	
	
	/* 매장 등록 */
	/*
	@PostMapping(value="/storeWrite.go")
	public ModelAndView storeWriteGo() {
		
	}
	*/
	
	
	/* 매장 수정 */
	
	
	
	/* 매장 상세보기 */
	
	
	
}
