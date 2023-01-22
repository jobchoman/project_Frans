package com.frans.main.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.frans.main.dao.MainDAO;
import com.frans.main.dto.MessageDTO;
import com.frans.sign.dto.DocFormDTO;
import com.frans.stock.dto.MenuDTO;
import com.frans.sub.dto.SubDTO;

@Service
public class MainService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MainDAO maindao;

	public ModelAndView main(String loginId) {
		ModelAndView mav = new ModelAndView();
		
		// 구독권
		ArrayList<SubDTO> sublistnum = maindao.main_subListNum(); // 횟수
		ArrayList<SubDTO> sublistday = maindao.main_subListDay(); // 요일
		mav.addObject("sublistnum",sublistnum);
		mav.addObject("sublistday",sublistday);
		
		
		// 메뉴
		ArrayList<MenuDTO> menulist = maindao.menuList();
		mav.addObject("menulist",menulist);
		
		
		// 쪽지
		ArrayList<MessageDTO> msgListBox = maindao.msgListBox(loginId);
		mav.addObject("msgListBox",msgListBox);
		
		return mav;
	}

}
