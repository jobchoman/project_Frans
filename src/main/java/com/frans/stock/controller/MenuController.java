/*
작성자 : 전형근 (HYEONGGEUN JEON)
제목 : 메뉴 등록, 수정, 리스트, 상세보기를 다루기 위한 Controller 
개발 기간 : 2023-01-09 ~ 2023-01-12
내용 : 메뉴 사진, 메뉴 이름, 메뉴 가격, 출시 일자, 레시피를 등록 할 수 있다. 메뉴 상태는 출시일과 현재 날짜와 비교하여 
		판매중 또는 준비중으로 결정된다.
		메뉴 리스트에서는 메뉴 번호(PK), 메뉴 사진, 메뉴 이름, 가격을 볼 수 있고 메뉴 사진이나 이름을 클릭하면 상세보기로 이동한다.
		그리고 필터링 기능을 통해서 메뉴 상태에 따른 리스트를 볼 수 있다.
		메뉴 상세보기에서는 메뉴 등록한 내용을 볼 수 있다.
		메뉴 수정하기
		- 준비중인 메뉴 : 사진, 이름, 상태, 레시피, 출시일 모두 수정 가능
		- 판매중인 메뉴 : 사진, 이름, 상태, 레시피만 수정 가능하며 출시일 수정 불가능
		- 종료된 메뉴   : 기본적인 사진, 이름, 레시피 정도만 수정 가능
*/

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
	public String updateMenu(MultipartFile uploadFile, String menu_idx, String menu_name, int menu_price, String menu_recipe, String menu_state, String menu_start) {
			
		logger.info("메뉴 idx : "+menu_idx);
		logger.info("이름 : "+menu_name);
		logger.info("가격 : "+menu_price);
		logger.info("레시피 : "+menu_recipe);
		logger.info("상태 : "+menu_state);
		logger.info("출시일 : "+menu_start);
		
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
			mDTO.setMenu_start(menu_start);
			mDTO.setMenu_photo(newFileName);
			
			mService.menuUpdate1(mDTO);
		}else {
			mDTO.setMenu_idx(menu_idx);
			mDTO.setMenu_name(menu_name);
			mDTO.setMenu_price(menu_price);
			mDTO.setMenu_recipe(menu_recipe);
			mDTO.setMenu_state(menu_state);
			mDTO.setMenu_start(menu_start);
			
			mService.menuUpdate2(mDTO);
		}
		
		
		logger.info("메뉴 등록 완료");

		return "redirect:/menuList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
