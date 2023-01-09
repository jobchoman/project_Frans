package com.frans.sign.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.frans.sign.dao.SignDAO;
import com.frans.sign.dto.DocFormDTO;
import com.frans.sign.dto.signDTO;

@Service
public class SignService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired SignDAO signdao;

	public HashMap<String, Object> signList() {
		logger.info("결재 문서 리스트 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<signDTO> signdto = signdao.signList();
		map.put("signdto", signdto);
		return map;
	}

	public HashMap<String, Object> dateSearch(String date1, String date2) {
		logger.info("결재 문서 기간 검색 서비스");
//		logger.info(date1+"/"+date2);
		
		String first_month = date1.substring(0,date1.indexOf("/"));
		String first_date = date1.substring(3,5);
		String first_year = date1.substring(date1.lastIndexOf("/")+1);
		logger.info(first_year+"년"+first_month+"월"+first_date+"일");
		
		String second_month = date2.substring(0,date2.indexOf("/"));
		String second_date = date2.substring(3,5);
		String second_year = date2.substring(date2.lastIndexOf("/")+1);
		logger.info(second_year+"년"+second_month+"월"+second_date+"일");
		
		String startdate = first_year+first_month+first_date;
		String enddate = second_year+second_month+second_date;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<signDTO> searchlist = signdao.dateSearch(startdate,enddate);
		map.put("searchlist", searchlist);
		
		return map;
	}

	public ModelAndView signWriteGo(String doc_form_idx) {
		ModelAndView mav = new ModelAndView("signWrite");
		DocFormDTO docformdto = signdao.signWriteGo(doc_form_idx);
		
		mav.addObject("docformdto",docformdto);
		return mav;
	}

	
}
