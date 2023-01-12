package com.frans.main.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

}
