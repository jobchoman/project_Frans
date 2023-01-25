package com.frans.stock.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frans.stock.dao.StoreDAO;
import com.frans.stock.dto.StoreDTO;
import com.frans.stock.service.StoreService;

@Controller
public class StoreController {
	
	@Autowired StoreService storeservice;
	
	Logger logger = LoggerFactory.getLogger(getClass());

	/* 매장 리스트 */
	@ResponseBody
	@GetMapping(value="/store/list.do")
	public HashMap<String, Object> storeList() {
		logger.info("매장 리스트 컨트롤러");
		return storeservice.storeList();
	}
	
	// 점장 리스트
	@GetMapping(value="/storeWrite.go")
	public ModelAndView managerList() {
		logger.info("점장 리스트 컨트롤러");
		return storeservice.managerList();
	}
	
	
	/* 매장 등록 */
	@PostMapping(value="/store/write.do")
	public ModelAndView storeWrite(@RequestParam HashMap<String, String> params) {
		logger.info("매장 등록 컨트롤러");
		logger.info("params: {}",params);
		return storeservice.storeWrite(params);
	}
	
	
	/* 매장 상세보기 */
	@GetMapping(value="/storeDetail.go")
	public ModelAndView storeDetail(String shop_idx) {
		logger.info("매장 상세 컨트롤러");
		logger.info("shop_idx: "+shop_idx);
		return storeservice.storeDetail(shop_idx);
	}
	
	/* 매장 수정 */
	@GetMapping(value="/store/update.go")
	public ModelAndView storeUpdateGo(String shop_idx, RedirectAttributes rAttr) {
		logger.info("매장 수정 호출 컨트롤러");
		logger.info("수정할 매장: "+shop_idx);
		return storeservice.storeUpdateGo(shop_idx, rAttr);
	}
	
	@PostMapping(value="/store/update.do")
	public ModelAndView storeUpdateDo(@RequestParam HashMap<String, String> params) {
		logger.info("매장 수정 컨트롤러");
		logger.info("params: {}",params);
		// {shop_idx=SH007, shop_name=샐러드상자 분당점, shop_manager=점장2, shop_managerId=20230012, shop_contact=031-555-0000, shop_empNum=35, shop_space=60
		// sido=경기, sigungu=성남시 분당구, roadname=구미로, addressNum=115, detailAddress=101호, lat=127.12064428249, lon=37.3399103881443}
		return storeservice.storeUpdateDo(params);
	}
	
	/* 시/도/시군구 선택 */
	
	@ResponseBody
	@GetMapping(value="/store/provinceList.do")
	public HashMap<String, Object> provinceListCall() {
		
		return storeservice.provinceListCall();
	}
	
	@ResponseBody
	@GetMapping(value="/store/cityList.do")
	public HashMap<String, Object> cityListCall(@RequestParam int idx) {
		logger.info("proinceIdx = "+idx);
		return storeservice.cityListCall(idx);
	}
	
	@ResponseBody
	@GetMapping(value="/store/selprovince.do")
	public HashMap<String, Object> provincestore(String idx){
		logger.info("province idx: "+idx);
		return storeservice.provincestore(idx);
	}
	
	@ResponseBody
	@GetMapping(value="/store/filter.do")
	public HashMap<String, Object> storeFilter(String idx){
		logger.info("city idx: "+idx);
		return storeservice.storeFilter(idx);
	}
	
	
}
