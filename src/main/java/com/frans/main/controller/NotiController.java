package com.frans.main.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frans.main.service.NotiService;
import com.frans.stock.dto.StockDTO;

@Controller
public class NotiController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired NotiService notiservice;
	
	//메인화면 알림 팝업
	@ResponseBody
	@GetMapping(value="/noti/notiList.ajax")
	public HashMap<String, Object> notiList(@RequestParam HashMap<String, String> params, HttpServletRequest req){

		HttpSession session = req.getSession();
		String emp_id = (String) session.getAttribute("loginId");
		logger.info("알림 리스트 params : {}",params);

		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StockDTO> notiList = notiservice.notiList(emp_id);
		map.put("notiList", notiList);
		return map;

	}
	
	// 알림 수신함 리스트
	@ResponseBody
	@RequestMapping(value="/noti/notiListBox.ajax")
	public HashMap<String, Object> notiListBox(@RequestParam HashMap<String, String> params, HttpServletRequest req){
		logger.info("params : {}",params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		HttpSession session = req.getSession();
		String emp_id = (String) session.getAttribute("loginId");
		ArrayList<StockDTO> notiListBox = notiservice.notiListBox(emp_id);
		map.put("data", notiListBox);

		return map;

	}
	
	@ResponseBody
	@RequestMapping(value="/noti/notiDelete.ajax")
	public String notiDelete(@RequestParam HashMap<String, String> params,HttpServletRequest req, Model model){
		String msg = "실패";
		HttpSession session = req.getSession();
		String emp_id = (String) session.getAttribute("loginId");
		
		logger.info("params : {}",params);
		logger.info("params : {}",emp_id);
		String noti_idx = params.get("noti_idx");
		if(noti_idx != null && noti_idx !="") {
			int del = notiservice.notiDelete(noti_idx,emp_id);			
			logger.info("del : "+del);
			if(del > 0) {
				msg = "성공";
			}
		}
			
		model.addAttribute("msg",msg);		
//		return "redirect:/revDetail.go?eat_idx="+eat_idx;
		return msg;

	}

}
