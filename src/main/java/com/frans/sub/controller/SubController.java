/*
작성자 : 전형근 (HYEONGGEUN JEON)
제목 : 구독권 등록, 수정, 리스트, 상세보기를 다루기 위한 Controller 
개발 기간 : 2023-01-10 ~ 2023-01-11
내용 : 
		□ 구독권 등록
		- 구독권 이름, 가격 등록
		- 구독권 상태(횟수권, 요일권)를 Radio 버튼으로 선택
		- 횟수 등록(횟수권이면 n회, 요일권이면 주 n회로 기입)
		- 구독권을 진행할 메뉴는 select box로 선택할 수 있으며 선택하면 아래 tags box 안에 
		  추가되는 형식이며 x표를 눌러 개별 삭제 또한 가능하다.
		- 기간(1~12 개월 자유롭게 선택)
		- 출시일과 종료일을 선택
		□ 구독권 리스트
		- 구독권 이름, 가격, 메뉴, 구독권 종류, 기간을 볼 수 있다.
		- 구독권 이름 클릭 시 상세보기로 이동
		- 구독권 상태에 따른 필터링 기능
		□ 구독권 상세보기
		- 구독권 상세보기에서는 구독권 등록시 기입한 내용을 볼 수 있다.
		□ 구독권 수정하기
		- 준비중인 구독권 : 모두 수정 가능
		- 진행중인 구독권 : 출시일 수정 불가능
		- 종료된 구독권 : 다시 활성화 가능
*/

package com.frans.sub.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frans.sub.dto.SubDTO;
import com.frans.sub.service.SubService;

@Controller
public class SubController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired SubService subService;
	
	@GetMapping(value="/subList")
	public String subList() {
		
		return "subList";
	}
	
	@ResponseBody
	@RequestMapping(value="/subList.do")
	public HashMap<String, Object> subListCall(String sub_state) {
		
		return subService.subListCall(sub_state);
	}
	
	
	
	@GetMapping(value="/subRegister")
	public String subRegister(Model model) {
		
		model.addAttribute("menuList", subService.menuList());
		
		return "subRegister";
	}
	
	@PostMapping(value="/subWrite.do")
	public String subWrite(String sub_name, int sub_price, int sub_sort_idx, 
			int sub_num, int sub_period, String sub_start, String sub_end, 
			@RequestParam List<String> menuIdx) {
		
		String sub_idx = "";
		int cnt = subService.subCount()+1;
		if(cnt < 10) {
			sub_idx = "SB00"+cnt;
		}else if(10 <= cnt && cnt < 100) {
			sub_idx = "SB0"+cnt;
		}else {
			sub_idx = "SB"+cnt;
		}

		SubDTO subdto = new SubDTO();
		
		subdto.setSub_idx(sub_idx);
		subdto.setSub_name(sub_name);
		subdto.setSub_price(sub_price);
		subdto.setSub_num(sub_num);
		subdto.setSub_period(sub_period);
		subdto.setSub_sort_idx(sub_sort_idx);
		subdto.setSub_start(sub_start);
		subdto.setSub_end(sub_end);
		
		
		
		

		
		Object[] menuList = menuIdx.toArray();
		
		subService.subWrite(subdto, sub_idx, menuList);
		
		return "redirect:/subList";
	}
	
	@GetMapping(value="/subDetail")
	public String subDetail(String sub_idx, Model model) {
		
		model.addAttribute("detail", subService.subDetail(sub_idx));
		
		return "subDetail";
	}
	
	@GetMapping(value="/subUpdate")
	public String subUpdate(String sub_idx, Model model) {
		
		model.addAttribute("detail", subService.subDetail(sub_idx));
		model.addAttribute("menuList", subService.menuList());
		model.addAttribute("sub_menu", subService.subMenu(sub_idx));
		return "subUpdate";
	}
	
	@PostMapping(value="subUpdate.do")
	public String updateSub(String sub_idx, String sub_name, int sub_price, int sub_sort_idx, 
			int sub_num, int sub_period, String sub_start, String sub_end, String sub_state, 
			@RequestParam List<String> menuIdx) {
		
		SubDTO subdto = new SubDTO();
		subdto.setSub_idx(sub_idx);
		subdto.setSub_name(sub_name);
		subdto.setSub_price(sub_price);
		subdto.setSub_sort_idx(sub_sort_idx);
		subdto.setSub_num(sub_num);
		subdto.setSub_period(sub_period);
		subdto.setSub_start(sub_start);
		subdto.setSub_end(sub_end);
		subdto.setSub_state(sub_state);
		
		Object[] menuList = menuIdx.toArray();
		
		subService.subUpdate(subdto, menuList);
		
		logger.info("업데이트 완료!!");
		
		return "redirect:/subList";
	}
	
}
