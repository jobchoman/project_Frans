package com.frans.stock.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.frans.stock.dao.MenuDAO;
import com.frans.stock.dto.MenuDTO;

@Service
public class MenuService {
	
	
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MenuDAO mDAO;

	public void menuRegister(MenuDTO mDTO) {
		int row = mDAO.menuRegister(mDTO);
		logger.info(row+"개 메뉴 등록 완료!");
	}


	public int menuCount() {
		int cnt = mDAO.menuCount();
		return cnt;
	}

//	public ArrayList<HashMap<String, String>> menuList() {
//		ArrayList<HashMap<String, String>> list = mDAO.menuList();
//		return list;
//	}


	public HashMap<String, Object> menuListCall(String menu_state) {
		
		HashMap<String, Object> menuList = new HashMap<String, Object>();
		
		menuList.put("data", mDAO.menuListCall(menu_state));
		
		logger.info("메뉴 리스트 : "+menuList);
		
		return menuList;
	}


	public MenuDTO menuDetail(String menu_idx) {
		MenuDTO dto = mDAO.menuDetail(menu_idx);
		return dto;
	}


	public void menuUpdate1(MenuDTO mDTO) {
		mDAO.menuUpdate1(mDTO);
		
	}


	public void menuUpdate2(MenuDTO mDTO) {
		mDAO.menuUpdate2(mDTO);
		
	}
	
}
