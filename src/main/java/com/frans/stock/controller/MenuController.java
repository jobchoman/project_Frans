package com.frans.stock.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.frans.stock.dto.MenuDTO;
import com.frans.stock.service.MenuService;

@Controller
public class MenuController {
	
	@Value("${file.location}") private String root;

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MenuService mService;
	
	@GetMapping(value="/menuList")
	public String menuList() {
		
		
//		model.addAttribute("list", mService.menuList());
		return "menuList";
	}
	
	@ResponseBody
	@RequestMapping(value="/menuList.do")
	public HashMap<String, Object> menuListCall(@RequestParam String menu_state) {
		
		return mService.menuListCall(menu_state);
	}
	
	@GetMapping(value="/menuWrite")
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
		
		// 1. 파일명 추출
		String oriFileName = uploadFile.getOriginalFilename();
		logger.info("oriFileName : "+oriFileName);
		String ext = oriFileName.substring(oriFileName.lastIndexOf("."));
		// 2. 새 파일명 생성
		String newFileName = System.currentTimeMillis()+ext;
		
		
		// 파일 저장
		try {
			// uploadFile 에서 바이트 추출
			byte[] arr = uploadFile.getBytes();
			// 저장할 파일
			Path path = Paths.get(root+newFileName);
			// 파일 쓰기
			Files.write(path, arr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		MenuDTO mDTO = new MenuDTO();
		mDTO.setMenu_idx(menu_idx);
		mDTO.setMenu_name(menu_name);
		mDTO.setMenu_price(menu_price);
		mDTO.setMenu_recipe(menu_recipe);
		mDTO.setMenu_start(menu_start);
		mDTO.setMenu_photo(newFileName);
		
		mService.menuRegister(mDTO);
		logger.info("메뉴 등록 완료");

		return "redirect:/menuList";
	}
	
	@GetMapping(value="/menuDetail")
	public String menuDetail(String menu_idx, Model model) {
		
		model.addAttribute("detail", mService.menuDetail(menu_idx));
		
		return "menuDetail";
	}
	
	@GetMapping(value="/menuUpdate")
	public String menuUpdate(String menu_idx, Model model) {
		
		model.addAttribute("detail", mService.menuDetail(menu_idx));
		
		return "menuUpdate";
	}
	
	@PostMapping(value="/menuUpdate.do")
	public String updateMenu(MultipartFile uploadFile, String menu_idx, String menu_name, int menu_price, String menu_recipe, String menu_state) {
			
		logger.info("출시일 : "+menu_idx);
		logger.info("이름 : "+menu_name);
		logger.info("가격 : "+menu_price);
		logger.info("레시피 : "+menu_recipe);
		logger.info("상태 : "+menu_state);
		
		MenuDTO mDTO = new MenuDTO();
		
		if(uploadFile.getSize()>0) {
			// 1. 파일명 추출
			String oriFileName = uploadFile.getOriginalFilename();
			logger.info("oriFileName : "+oriFileName);
			String ext = oriFileName.substring(oriFileName.lastIndexOf("."));
			// 2. 새 파일명 생성
			String newFileName = System.currentTimeMillis()+ext;
			
		
			// 파일 저장
			try {
				// uploadFile 에서 바이트 추출
				byte[] arr = uploadFile.getBytes();
				// 저장할 파일
				Path path = Paths.get(root+newFileName);
				// 파일 쓰기
				Files.write(path, arr);
			} catch (Exception e) {
				e.printStackTrace();
			}

			mDTO.setMenu_idx(menu_idx);
			mDTO.setMenu_name(menu_name);
			mDTO.setMenu_price(menu_price);
			mDTO.setMenu_recipe(menu_recipe);
			mDTO.setMenu_state(menu_state);
			mDTO.setMenu_photo(newFileName);
			
			mService.menuUpdate1(mDTO);
		}else {
			mDTO.setMenu_idx(menu_idx);
			mDTO.setMenu_name(menu_name);
			mDTO.setMenu_price(menu_price);
			mDTO.setMenu_recipe(menu_recipe);
			mDTO.setMenu_state(menu_state);
			
			mService.menuUpdate2(mDTO);
		}
		
		
		logger.info("메뉴 등록 완료");

		return "redirect:/menuList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
