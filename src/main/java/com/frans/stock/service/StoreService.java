package com.frans.stock.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.frans.stock.dao.StoreDAO;
import com.frans.stock.dto.StoreDTO;


@Service
public class StoreService {
	
	@Autowired StoreDAO storedao;

	Logger logger = LoggerFactory.getLogger(getClass());

	public ModelAndView storeList() {
		logger.info("매장 리스트 서비스");
		ModelAndView mav = new ModelAndView("storeList");
		ArrayList<StoreDTO> storedto = storedao.storeList();
		mav.addObject("storelist",storedto);
		return mav;
	}
}
