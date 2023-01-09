package com.frans.stock.controller;

import java.sql.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.frans.stock.dto.MenuDTO;
import com.frans.stock.service.MenuService;

@Controller
public class MenuController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MenuService mService;
	
	@GetMapping(value="/menuList.go")
	public String menuList() {
		
		return "menuList";
	}
	
	@GetMapping(value="/menuWrite.go")
	public String menuRegister() {
		
		return "menuRegister";
	}
	
	@GetMapping(value="/profile.go")
	public String profile() {
		
		return "profile";
	}
	
	@PostMapping(value="/menuWrite.do")
	public String menuWrite(MultipartFile uploadFile, String menu_name, int menu_price, String menu_start, String menu_recipe) {
		String menu_idx = "";
		int cnt = mService.menuCount()+1;
		if(cnt < 10) {
			menu_idx = "ME00"+cnt;
		}else if(10 <= cnt && cnt < 100) {
			menu_idx = "ME0"+cnt;
		}else {
			menu_idx = "ME"+cnt;
		}
		
		logger.info("출시일 : "+menu_start);
		
		MenuDTO mDTO = new MenuDTO();
		mDTO.setMenu_idx(menu_idx);
		mDTO.setMenu_name(menu_name);
		mDTO.setMenu_price(menu_price);
		mDTO.setMenu_recipe(menu_recipe);
		mDTO.setMenu_start(menu_start);
		mService.menuRegister(mDTO);
		logger.info("1차 메뉴 등록 완료");
		mService.menuPhotoUpload(uploadFile, menu_idx);
		logger.info("메뉴 사진까지 등록 완료!!");
		return "menuList";
	}
}
