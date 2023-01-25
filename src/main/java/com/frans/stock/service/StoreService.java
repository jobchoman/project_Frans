package com.frans.stock.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frans.member.dto.MemberDTO;
import com.frans.stock.dao.StoreDAO;
import com.frans.stock.dto.StoreDTO;


@Service
public class StoreService {
	
	@Autowired StoreDAO storedao;

	Logger logger = LoggerFactory.getLogger(getClass());

	/* 매장 리스트 */
	public HashMap<String, Object> storeList() {
		logger.info("매장 리스트 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StoreDTO> storedto = storedao.storeList();
		map.put("data", storedto);
		return map;
	}

	public ModelAndView managerList() {
		logger.info("점장 리스트 서비스");
		ModelAndView mav = new ModelAndView();
		ArrayList<MemberDTO> managerlist = storedao.managerList();
		mav.addObject("managerlist",managerlist);
		return mav;
	}

	/* 매장 등록 */
	
	// shop_name=샐러드 박스 안양점, shop_manager=주우재, shop_managerId=20102010, shop_empNum=45, shop_space=80, shop_contact=031-1234-1234
	// sido=경기, sigungu=안양시 동안구, roadname=갈산로, addressNum=34, detailAddress=12345
	public ModelAndView storeWrite(HashMap<String, String> params) {
		logger.info("매장 등록 서비스");
		ModelAndView mav = new ModelAndView();
		String page = "redirect:/storeList.go";
		
		String shop_idx = "";
		int cnt = storedao.storeCount()+1;
		if(cnt < 10) {
			shop_idx = "SH00"+cnt;
		}else if(10 <= cnt && cnt < 100) {
			shop_idx = "SH0"+cnt;
		}else {
			shop_idx = "SH"+cnt;
		}
		logger.info("shop_idx: "+shop_idx);
		
		String sido = params.get("sido");
		String sigungu = params.get("sigungu");
		logger.info(sido);
		logger.info(sigungu);
		int province_id = storedao.selProvince(sido); // 시/도 
		int city_id = storedao.selCity(sigungu,province_id); // 시/군/구
		
		
		StoreDTO storedto = new StoreDTO();
		storedto.setEmp_id(params.get("shop_managerId")); // 점장 아이디
		storedto.setShop_name(params.get("shop_name")); // 매장명
		storedto.setShop_space(params.get("shop_space")); // 평 수
		storedto.setShop_emp_num(params.get("shop_empNum")); // 직원 수
		storedto.setShop_contact(params.get("shop_contact")); // 연락처
		storedto.setShop_idx(shop_idx); // 매장 코드
		
		// 주소
		// params: {shop_name=샐러드상자 안양점, shop_manager=주우재, shop_managerId=20102010, shop_contact=031-123-4567, detailAddress=11번길, sido=경기, sigungu=성남시 분당구, roadname=안양판교로, addressNum=806-6, shop_empNum=45, shop_space=80}
		String detailAddress = params.get("fullAddr")+" "+params.get("detailAddress");
		storedto.setShop_location(detailAddress);
		storedto.setProvince_idx(province_id);
		storedto.setCity_idx(city_id);
		storedto.setShop_lat(params.get("lat")); // 위도
		storedto.setShop_lon(params.get("lon")); // 경도
		int success = storedao.storeWrite(storedto);
		mav.addObject("storedto",storedto);
		mav.setViewName(page);
		
		return mav;
	}

	public ModelAndView storeDetail(String shop_idx) {
		logger.info("매장 상세 서비스");
		ModelAndView mav = new ModelAndView();
		StoreDTO storedto = storedao.storeDetail(shop_idx);
		mav.addObject("storedto",storedto);
		return mav;
	}

	public ModelAndView storeUpdateGo(String shop_idx, RedirectAttributes rAttr) {
		logger.info("매장 수정 호출 서비스");
		String page = "redirect:/storeUpdate.go?shop_idx="+shop_idx;
		ModelAndView mav = new ModelAndView();
		StoreDTO storedto = storedao.storeDetail(shop_idx);
		ArrayList<MemberDTO> managerlist = storedao.managerList();
		mav.setViewName(page);
		rAttr.addFlashAttribute("storedto",storedto);
		rAttr.addFlashAttribute("managerlist",managerlist);
		return mav;
	}

	public ModelAndView storeUpdateDo(HashMap<String, String> params) {
		logger.info("매장 수정 서비스");
		logger.info("수정할 매장: "+params.get("shop_idx"));
		ModelAndView mav = new ModelAndView();
		String page = "redirect:/storeDetail.go?shop_idx="+params.get("shop_idx");
		
		String sido = params.get("sido");
		String sigungu = params.get("sigungu");
		int province_id = storedao.selProvince(sido); // 시/도 
		int city_id = storedao.selCity(sigungu,province_id); // 시/군/구
		
		StoreDTO storedto = new StoreDTO();
		String emp_id = params.get("shop_managerId"); // 점장 아이디
		String shop_name = params.get("shop_name"); // 매장명
		String shop_space = params.get("shop_space"); // 평 수
		String shop_emp_num = params.get("shop_empNum"); // 직원 수
		String shop_contact = params.get("shop_contact"); // 연락처
		String shop_idx = params.get("shop_idx");
		
		// 주소
		// sido=경기, sigungu=성남시 분당구, roadname=구미로, addressNum=115, detailAddress=101호, lat=127.12064428249, lon=37.3399103881443
		String shop_location = params.get("fullAddr")+" "+params.get("detailAddress");
		String shop_lat = params.get("lat"); // 위도
		String shop_lon =params.get("lon"); // 경도
		int success = storedao.storeUpdateDo(shop_idx,emp_id,shop_name,shop_space,shop_emp_num,shop_contact,shop_location,shop_lat,shop_lon,province_id,city_id);
		mav.addObject("storedto",storedto);
		mav.setViewName(page);
		
		return mav;
	}

	public HashMap<String, Object> storeFilter(String idx) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StoreDTO> storelist = storedao.storeFilter(idx);
		map.put("data", storelist);
		return map;
	}

	public HashMap<String, Object> provinceListCall() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", storedao.provinceListCall());
		
		return map;
	}
	
	public HashMap<String, Object> cityListCall(int idx) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", storedao.cityListCall(idx));
		
		return map;
	}

	public HashMap<String, Object> provincestore(String idx) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StoreDTO> storelist = storedao.provincestore(idx);
		map.put("data", storelist);
		return map;
	}

	
	
}
