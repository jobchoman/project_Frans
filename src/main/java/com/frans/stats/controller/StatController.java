package com.frans.stats.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.frans.stats.service.StatService;


@RestController
public class StatController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired StatService statservice;
	

	@GetMapping(value="/subStat")
	public ModelAndView subStat() {
		
		return new ModelAndView("subStat");
	}
	
	@GetMapping(value="/sub/provinceList.do")
	public HashMap<String, Object> provinceListCall() {
		
		return statservice.provinceListCall();
	}
	
	@GetMapping(value="/sub/cityList.do")
	public HashMap<String, Object> cityListCall(@RequestParam int idx) {
		logger.info("proinceIdx = "+idx);
		return statservice.cityListCall(idx);
	}
	
	@GetMapping(value="/sub/shopList.do")
	public HashMap<String, Object> shopListCall(@RequestParam int idx) {
		logger.info("cityIdx = "+idx);
		return statservice.shopListCall(idx);
	}
	
	@GetMapping(value="/sub/subList.do")
	public HashMap<String, Object> subListCall(@RequestParam String subSort) {
		logger.info(subSort);
		return statservice.subListCall(subSort);
	}
	
	@GetMapping(value="/sub/subStat.do")
	public HashMap<String, Object> subStat(@RequestParam HashMap<String, String> params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		logger.info("데이터 = "+params);
		int size = params.size();
		logger.info("데이터 사이즈 = "+size);
		
		ArrayList<HashMap<String, Object>> seoul = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> busan = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> daegu = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> incheon = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> gwangju = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> daejeon = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> ulsan = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> saejong = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> gyeonggi = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> gangwon = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> choongbook = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> choongnam = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> jeonbook = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> jeonnam = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> gyeongbook = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> gyeongnam = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> jeju = new ArrayList<HashMap<String,Object>>();
		
		// labels
		// label
		// data
		// type
		
		// labels
		// label1
		// label2
		// data1
		// data2
		// type = 2
		
		if(size == 5) {
			// size 가 5라는 것은 시도/시군구/매장 모두 선택하지 않았을 때이다.
			// 시도/시군구/매장 아무것도 선택하지 않았을 때는 시도 끼리 비교하는 그래프를 그린다.
			// 년도와 월도 선택하지 않았다면 년단위 즉 2022년 2023년에 서울특별시, 경기도 막대 각 1개 총 2개 막대 
			// 막대는 시도를 기준으로 갯수를 측정
			String year = params.get("year");
			String month = params.get("month");
			String gender = params.get("gender");
			String sub_sort_idx = params.get("sub_sort_idx");
			String sub_idx = params.get("sub_idx");

			
			if(year.equals("noYear") && month.equals("noMonth")) { // 년도와 월이 둘다 선택되지 않았을 때
				if(gender.equals("genderAll")) { // 성별 전체
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
							// 시도/시군구/매장 선택 x, 년도, 월 선택 x, 성별 전체, 횟수권 요일권 통합, 구독권 선택 x 
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender1();
							
							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int i2=0; i2<list.size(); i2++) {
								provinceName.add((String) list.get(i2).get("province_name"));
							}
							
							for(int i3=0; i3<list.size();i3+=2) {
								data1.add(list.get(i3).get("subJoinCount").toString());
							}
							for(int i4=1; i4<list.size();i4+=2) {
								data2.add(list.get(i4).get("subJoinCount").toString());
							}
							
							logger.info("data1 은 ?? = "+data1);
							logger.info("data2 은 ?? = "+data2);
							
							
							LinkedHashSet<String> labels = new LinkedHashSet<>();
					        for(String item : yearList){
					        	labels.add(item);
					        }
					        
					        LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
					        
					        logger.info("label1 과 label2"+label);
					        
					        logger.info("중복제거한 것 = "+labels);
							// labels
							// label1
							// label2
							// data1
							// data2
							map.put("labels", labels);
							map.put("label", label);
							map.put("data1", data1);
							map.put("data2", data2);
							map.put("type", 2);
							map.put("title", "서울,경기 연간 총 구독 등록수");

						}else{ // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							// 시도/시군구/매장 선택 x, 년도, 월 선택 x, 성별 전체, 횟수권 요일권 통합, 구독권 선택 O
							
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender2(sub_idx);
							
							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int i2=0; i2<list.size(); i2++) {
								provinceName.add((String) list.get(i2).get("province_name"));
							}
							
							for(int i3=0; i3<list.size();i3+=2) {
								data1.add(list.get(i3).get("subJoinCount").toString());
							}
							for(int i4=1; i4<list.size();i4+=2) {
								data2.add(list.get(i4).get("subJoinCount").toString());
							}
							
							logger.info("data1 은 ?? = "+data1);
							logger.info("data2 은 ?? = "+data2);
							
							
							LinkedHashSet<String> labels = new LinkedHashSet<>();
					        for(String item : yearList){
					        	labels.add(item);
					        }
					        
					        LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
					        
					        logger.info("label1 과 label2"+label);
					        
					        logger.info("중복제거한 것 = "+labels);
							// labels
							// label1
							// label2
							// data1
							// data2
							map.put("labels", labels);
							map.put("label", label);
							map.put("data1", data1);
							map.put("data2", data2);
							map.put("type", 2);
							String sub_name = statservice.subName(sub_idx);
							map.put("title", "서울,경기 연간 "+sub_name+" 등록수");
						}
					}else { // 횟수권/요일권 중 하나가 선택 되었을 때
						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender3(sub_sort_idx);
							
							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int i2=0; i2<list.size(); i2++) {
								provinceName.add((String) list.get(i2).get("province_name"));
							}
							
							for(int i3=0; i3<list.size();i3+=2) {
								data1.add(list.get(i3).get("subJoinCount").toString());
							}
							for(int i4=1; i4<list.size();i4+=2) {
								data2.add(list.get(i4).get("subJoinCount").toString());
							}
							
							logger.info("data1 은 ?? = "+data1);
							logger.info("data2 은 ?? = "+data2);
							
							
							LinkedHashSet<String> labels = new LinkedHashSet<>();
					        for(String item : yearList){
					        	labels.add(item);
					        }
					        
					        LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
					        
					        logger.info("label1 과 label2"+label);
					        
					        logger.info("중복제거한 것 = "+labels);
							// labels
							// label1
							// label2
							// data1
							// data2
							map.put("labels", labels);
							map.put("label", label);
							map.put("data1", data1);
							map.put("data2", data2);
							map.put("type", 2);
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", "서울,경기 연간 "+sub_sort_type+" 등록수");
						}else { // 구독권 선택을 했을 때
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender4(sub_sort_idx, sub_idx);
							
							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int i2=0; i2<list.size(); i2++) {
								provinceName.add((String) list.get(i2).get("province_name"));
							}
							
							for(int i3=0; i3<list.size();i3+=2) {
								data1.add(list.get(i3).get("subJoinCount").toString());
							}
							for(int i4=1; i4<list.size();i4+=2) {
								data2.add(list.get(i4).get("subJoinCount").toString());
							}
							
							logger.info("data1 은 ?? = "+data1);
							logger.info("data2 은 ?? = "+data2);
							
							
							LinkedHashSet<String> labels = new LinkedHashSet<>();
					        for(String item : yearList){
					        	labels.add(item);
					        }
					        
					        LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
					        
					        logger.info("label1 과 label2"+label);
					        
					        logger.info("중복제거한 것 = "+labels);
							// labels
							// label1
							// label2
							// data1
							// data2
							map.put("labels", labels);
							map.put("label", label);
							map.put("data1", data1);
							map.put("data2", data2);
							map.put("type", 2);
							String sub_name = statservice.subName(sub_idx);
							map.put("title", "서울,경기 연간 "+sub_name+" 등록수");
						}
					}
				}else { // 성별이 남 또는 여 가 선택 되었을 때
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender5(gender);
							
							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int i2=0; i2<list.size(); i2++) {
								provinceName.add((String) list.get(i2).get("province_name"));
							}
							
							for(int i3=0; i3<list.size();i3+=2) {
								data1.add(list.get(i3).get("subJoinCount").toString());
							}
							for(int i4=1; i4<list.size();i4+=2) {
								data2.add(list.get(i4).get("subJoinCount").toString());
							}
							
							logger.info("data1 은 ?? = "+data1);
							logger.info("data2 은 ?? = "+data2);
							
							
							LinkedHashSet<String> labels = new LinkedHashSet<>();
					        for(String item : yearList){
					        	labels.add(item);
					        }
					        
					        LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
					        
					        logger.info("label1 과 label2"+label);
					        
					        logger.info("중복제거한 것 = "+labels);
							// labels
							// label1
							// label2
							// data1
							// data2
							map.put("labels", labels);
							map.put("label", label);
							map.put("data1", data1);
							map.put("data2", data2);
							map.put("type", 2);
							map.put("title", "서울,경기 연간 총 구독 등록수("+gender+")");
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender6(gender, sub_idx);
							
							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int i2=0; i2<list.size(); i2++) {
								provinceName.add((String) list.get(i2).get("province_name"));
							}
							
							for(int i3=0; i3<list.size();i3+=2) {
								data1.add(list.get(i3).get("subJoinCount").toString());
							}
							for(int i4=1; i4<list.size();i4+=2) {
								data2.add(list.get(i4).get("subJoinCount").toString());
							}
							
							logger.info("data1 은 ?? = "+data1);
							logger.info("data2 은 ?? = "+data2);
							
							
							LinkedHashSet<String> labels = new LinkedHashSet<>();
					        for(String item : yearList){
					        	labels.add(item);
					        }
					        
					        LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
					        
					        logger.info("label1 과 label2"+label);
					        
					        logger.info("중복제거한 것 = "+labels);
							// labels
							// label1
							// label2
							// data1
							// data2
							map.put("labels", labels);
							map.put("label", label);
							map.put("data1", data1);
							map.put("data2", data2);
							map.put("type", 2);
							String sub_name = statservice.subName(sub_idx);
							map.put("title", "서울,경기 연간 "+sub_name+" 등록수("+gender+")");
						}
					}else { // 횟수권/요일권 중 하나가 선택 되었을 때
						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender7(gender, sub_sort_idx);
							
							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int i2=0; i2<list.size(); i2++) {
								provinceName.add((String) list.get(i2).get("province_name"));
							}
							
							for(int i3=0; i3<list.size();i3+=2) {
								data1.add(list.get(i3).get("subJoinCount").toString());
							}
							for(int i4=1; i4<list.size();i4+=2) {
								data2.add(list.get(i4).get("subJoinCount").toString());
							}
							
							logger.info("data1 은 ?? = "+data1);
							logger.info("data2 은 ?? = "+data2);
							
							
							LinkedHashSet<String> labels = new LinkedHashSet<>();
					        for(String item : yearList){
					        	labels.add(item);
					        }
					        
					        LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
					        
					        logger.info("label1 과 label2"+label);
					        
					        logger.info("중복제거한 것 = "+labels);
							// labels
							// label1
							// label2
							// data1
							// data2
							map.put("labels", labels);
							map.put("label", label);
							map.put("data1", data1);
							map.put("data2", data2);
							map.put("type", 2);
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", "서울,경기 연간 "+sub_sort_type+" 등록수("+gender+")");
						}else { // 구독권 선택을 했을 때
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender8(gender, sub_sort_idx, sub_idx);
							
							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int i2=0; i2<list.size(); i2++) {
								provinceName.add((String) list.get(i2).get("province_name"));
							}
							
							for(int i3=0; i3<list.size();i3+=2) {
								data1.add(list.get(i3).get("subJoinCount").toString());
							}
							for(int i4=1; i4<list.size();i4+=2) {
								data2.add(list.get(i4).get("subJoinCount").toString());
							}
							
							logger.info("data1 은 ?? = "+data1);
							logger.info("data2 은 ?? = "+data2);
							
							
							LinkedHashSet<String> labels = new LinkedHashSet<>();
					        for(String item : yearList){
					        	labels.add(item);
					        }
					        
					        LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
					        
					        logger.info("label1 과 label2"+label);
					        
					        logger.info("중복제거한 것 = "+labels);
							// labels
							// label1
							// label2
							// data1
							// data2
							map.put("labels", labels);
							map.put("label", label);
							map.put("data1", data1);
							map.put("data2", data2);
							map.put("type", 2);
							String sub_name = statservice.subName(sub_idx);
							map.put("title", "서울,경기 연간 "+sub_name+" 등록수("+gender+")");
						}
					}
				}
				
			}else if(!year.equals("noYear") && month.equals("noMonth") ) { // 년도는 선택이 되었고 월은 선택이 되지 않았을 때
				if(gender.equals("genderAll")) { // 성별 전체
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender9(year);
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int i2=0; i2<list.size(); i2++) {
								provinceName.add((String) list.get(i2).get("province_name"));
							}
							
							for(int i3=0; i3<list.size();i3+=2) {
								data1.add(list.get(i3).get("subJoinCount").toString());
							}
							for(int i4=1; i4<list.size();i4+=2) {
								data2.add(list.get(i4).get("subJoinCount").toString());
							}
							
							logger.info("data1 은 ?? = "+data1);
							logger.info("data2 은 ?? = "+data2);
							
							
							LinkedHashSet<String> labels = new LinkedHashSet<>();
					        for(String item : monthList){
					        	labels.add(item);
					        }
					        
					        LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
					        
					        logger.info("label1 과 label2"+label);
					        
					        logger.info("중복제거한 것 = "+labels);
							// labels
							// label1
							// label2
							// data1
							// data2
							map.put("labels", labels);
							map.put("label", label);
							map.put("data1", data1);
							map.put("data2", data2);
							map.put("title", "서울,경기 "+year+"년 월별 구독 등록수");
							map.put("type", 2);
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender10(year, sub_idx);
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int i2=0; i2<list.size(); i2++) {
								provinceName.add((String) list.get(i2).get("province_name"));
							}
							
							for(int i3=0; i3<list.size();i3+=2) {
								data1.add(list.get(i3).get("subJoinCount").toString());
							}
							for(int i4=1; i4<list.size();i4+=2) {
								data2.add(list.get(i4).get("subJoinCount").toString());
							}
							
							logger.info("data1 은 ?? = "+data1);
							logger.info("data2 은 ?? = "+data2);
							
							
							LinkedHashSet<String> labels = new LinkedHashSet<>();
					        for(String item : monthList){
					        	labels.add(item);
					        }
					        
					        LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
					        
					        logger.info("label1 과 label2"+label);
					        
					        logger.info("중복제거한 것 = "+labels);
							// labels
							// label1
							// label2
							// data1
							// data2
							map.put("labels", labels);
							map.put("label", label);
							map.put("data1", data1);
							map.put("data2", data2);
							String sub_name = statservice.subName(sub_idx);
							map.put("title", "서울,경기 "+year+"년 "+sub_name+" 등록수");
							map.put("type", 2);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender11(year, sub_sort_idx);
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int i2=0; i2<list.size(); i2++) {
								provinceName.add((String) list.get(i2).get("province_name"));
							}
							
							for(int i3=0; i3<list.size();i3+=2) {
								data1.add(list.get(i3).get("subJoinCount").toString());
							}
							for(int i4=1; i4<list.size();i4+=2) {
								data2.add(list.get(i4).get("subJoinCount").toString());
							}
							
							logger.info("data1 은 ?? = "+data1);
							logger.info("data2 은 ?? = "+data2);
							
							
							LinkedHashSet<String> labels = new LinkedHashSet<>();
					        for(String item : monthList){
					        	labels.add(item);
					        }
					        
					        LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
					        
					        logger.info("label1 과 label2"+label);
					        
					        logger.info("중복제거한 것 = "+labels);
							// labels
							// label1
							// label2
							// data1
							// data2
							map.put("labels", labels);
							map.put("label", label);
							map.put("data1", data1);
							map.put("data2", data2);
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", "서울,경기 "+year+"년 "+sub_sort_type+" 등록수");
							map.put("type", 2);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender12(year,sub_sort_idx,sub_idx);
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int i2=0; i2<list.size(); i2++) {
								provinceName.add((String) list.get(i2).get("province_name"));
							}
							
							for(int i3=0; i3<list.size();i3+=2) {
								data1.add(list.get(i3).get("subJoinCount").toString());
							}
							for(int i4=1; i4<list.size();i4+=2) {
								data2.add(list.get(i4).get("subJoinCount").toString());
							}
							
							logger.info("data1 은 ?? = "+data1);
							logger.info("data2 은 ?? = "+data2);
							
							
							LinkedHashSet<String> labels = new LinkedHashSet<>();
					        for(String item : monthList){
					        	labels.add(item);
					        }
					        
					        LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
					        
					        logger.info("label1 과 label2"+label);
					        
					        logger.info("중복제거한 것 = "+labels);
							// labels
							// label1
							// label2
							// data1
							// data2
							map.put("labels", labels);
							map.put("label", label);
							map.put("data1", data1);
							map.put("data2", data2);
							String sub_name = statservice.subName(sub_idx);
							map.put("title", "서울,경기 "+year+"년 "+sub_name+" 등록수");
							map.put("type", 2);
						}
					}
				}else { // 성별 선택
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender13(year, gender);
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int i2=0; i2<list.size(); i2++) {
								provinceName.add((String) list.get(i2).get("province_name"));
							}
							
							for(int i3=0; i3<list.size();i3+=2) {
								data1.add(list.get(i3).get("subJoinCount").toString());
							}
							for(int i4=1; i4<list.size();i4+=2) {
								data2.add(list.get(i4).get("subJoinCount").toString());
							}
							
							logger.info("data1 은 ?? = "+data1);
							logger.info("data2 은 ?? = "+data2);
							
							
							LinkedHashSet<String> labels = new LinkedHashSet<>();
					        for(String item : monthList){
					        	labels.add(item);
					        }
					        
					        LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
					        
					        logger.info("label1 과 label2"+label);
					        
					        logger.info("중복제거한 것 = "+labels);
							// labels
							// label1
							// label2
							// data1
							// data2
							map.put("labels", labels);
							map.put("label", label);
							map.put("data1", data1);
							map.put("data2", data2);
							
							map.put("title", "서울,경기 "+year+"년 총 구독 등록수("+gender+")");
							map.put("type", 2);
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender14(year,gender,sub_idx);
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int i2=0; i2<list.size(); i2++) {
								provinceName.add((String) list.get(i2).get("province_name"));
							}
							
							for(int i3=0; i3<list.size();i3+=2) {
								data1.add(list.get(i3).get("subJoinCount").toString());
							}
							for(int i4=1; i4<list.size();i4+=2) {
								data2.add(list.get(i4).get("subJoinCount").toString());
							}
							
							logger.info("data1 은 ?? = "+data1);
							logger.info("data2 은 ?? = "+data2);
							
							
							LinkedHashSet<String> labels = new LinkedHashSet<>();
					        for(String item : monthList){
					        	labels.add(item);
					        }
					        
					        LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
					        
					        logger.info("label1 과 label2"+label);
					        
					        logger.info("중복제거한 것 = "+labels);
							// labels
							// label1
							// label2
							// data1
							// data2
							map.put("labels", labels);
							map.put("label", label);
							map.put("data1", data1);
							map.put("data2", data2);
							String sub_name = statservice.subName(sub_idx);
							map.put("title", "서울,경기 "+year+"년 "+sub_name+" 등록수("+gender+")");
							map.put("type", 2);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender15(year, gender, sub_sort_idx);
							
							logger.info("list 사이즈 ===== "+list.size());
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int i2=0; i2<list.size(); i2++) {
								provinceName.add((String) list.get(i2).get("province_name"));
							}
							
							for(int i3=0; i3<list.size();i3+=2) {
								data1.add(list.get(i3).get("subJoinCount").toString());
							}
							for(int i4=1; i4<list.size();i4+=2) {
								data2.add(list.get(i4).get("subJoinCount").toString());
							}
							
							logger.info("data1 은 ?? = "+data1);
							logger.info("data2 은 ?? = "+data2);
							
							
							LinkedHashSet<String> labels = new LinkedHashSet<>();
					        for(String item : monthList){
					        	labels.add(item);
					        }
					        
					        LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
					        
					        logger.info("label1 과 label2"+label);
					        
					        logger.info("중복제거한 것 = "+labels);
							// labels
							// label1
							// label2
							// data1
							// data2
							map.put("labels", labels);
							map.put("label", label);
							map.put("data1", data1);
							map.put("data2", data2);
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", "서울,경기 "+year+"년 "+sub_sort_type+" 등록수("+gender+")");
							map.put("type", 2);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender16(year,gender,sub_sort_idx,sub_idx);
							
							logger.info("list 사이즈 ===== "+list.size());
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int i2=0; i2<list.size(); i2++) {
								provinceName.add((String) list.get(i2).get("province_name"));
							}
							
							for(int i3=0; i3<list.size();i3+=2) {
								data1.add(list.get(i3).get("subJoinCount").toString());
							}
							for(int i4=1; i4<list.size();i4+=2) {
								data2.add(list.get(i4).get("subJoinCount").toString());
							}
							
							logger.info("data1 은 ?? = "+data1);
							logger.info("data2 은 ?? = "+data2);
							
							
							LinkedHashSet<String> labels = new LinkedHashSet<>();
					        for(String item : monthList){
					        	labels.add(item);
					        }
					        
					        LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
					        
					        logger.info("label1 과 label2"+label);
					        
					        logger.info("중복제거한 것 = "+labels);
							// labels
							// label1
							// label2
							// data1
							// data2
							map.put("labels", labels);
							map.put("label", label);
							map.put("data1", data1);
							map.put("data2", data2);
							String sub_name = statservice.subName(sub_idx);
							map.put("title", "서울,경기 "+year+"년 "+sub_name+" 등록수("+gender+")");
							map.put("type", 2);
						}
					}
				}
			}else { // 년도와 월 둘다 선택이 되었을 때
				if(gender.equals("genderAll")) { // 성별 전체
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender17(year,month);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<String> provinceName = new ArrayList<String>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
//								logger.info("타입은? = "+days.get(i9).getClass().getName());
							}
							
							for(int i7=0; i7<list.size(); i7++) {
								provinceName.add((String) list.get(i7).get("province_name"));
							}
							
							LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
							
//							map.put("days", days);
							
							for(int i=0; i<list.size(); i++) {
								if(list.get(i).get("province_name").equals("서울특별시")) {
									seoul.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("부산광역시")) {
									busan.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("대구광역시")) {
									daegu.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("인천광역시")) {
									incheon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("광주광역시")) {
									gwangju.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("대전광역시")) {
									daejeon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("울산광역시")) {
									ulsan.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("세종특별자치시")) {
									saejong.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경기도")) {
									gyeonggi.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("강원도")) {
									gangwon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("충청북도")) {
									choongbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("충청남도")) {
									choongnam.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("전라북도")) {
									jeonbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("전라남도")) {
									jeonnam.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경상북도")) {
									gyeongbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경상남도")) {
									gyeongnam.add(list.get(i));
								}else {
									jeju.add(list.get(i));
								}
							}
							
							
								
								if(seoul.size()>0) {
//									logger.info("담겨 있는 리스트 : "+seoul);
									for(int i8=0; i8<seoul.size(); i8++) {
										Date day = (Date) seoul.get(i8).get("day");
										compareDay.add(day);
										//logger.info("서울 기준 - 바뀐 arrayList = "+compareDay);
									}
									
									//logger.info("서울기준 - 값이 담긴 compareDay = "+compareDay);
//									logger.info("담겨 있는 리스트 : "+gyeonggi);
									for(int i2=0; i2<days.size(); i2++) {
										HashMap<String,Object> addMap1 = new HashMap<String,Object>();
										if(compareDay.contains(days.get(i2))) {
											
											//logger.info(i2+"번 째 동일");
										}else {
											addMap1.put("day", days.get(i2));
											addMap1.put("subJoinCount", 0);
											addMap1.put("province_name", "서울특별시");
											seoul.add(addMap1);
										}
									}
									
									seoul = (ArrayList<HashMap<String, Object>>) seoul.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
									
									if(map.containsKey("data1")) {
										ArrayList<Object> seouldata = new ArrayList<Object>();
										for(int a=0; a<seoul.size(); a++) {
											seouldata.add(seoul.get(a).get("subJoinCount"));
										}
										map.put("data2", seouldata);
									}else {
										ArrayList<Object> seouldata = new ArrayList<Object>();
										for(int a=0; a<seoul.size(); a++) {
											seouldata.add(seoul.get(a).get("subJoinCount"));
										}
										map.put("data1", seouldata);
									}
								}
								if(busan.size()>0) {
//									logger.info("담겨 있는 리스트 : "+busan);
									map.put("busan", busan);
								}
								if(daegu.size()>0) {
//									logger.info("담겨 있는 리스트 : "+daegu);
									map.put("daegu", daegu);
								}
								if(incheon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+incheon);
									map.put("incheon", incheon);
								}
								if(gwangju.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gwangju);
									map.put("gwangju", gwangju);
								}
								if(daejeon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+daejeon);
									map.put("daejeon", daejeon);
								}
								if(ulsan.size()>0) {
//									logger.info("담겨 있는 리스트 : "+ulsan);
									map.put("ulsan", ulsan);
								}
								if(saejong.size()>0) {
//									logger.info("담겨 있는 리스트 : "+saejong);
									map.put("saejong", saejong);
								}
								if(gyeonggi.size()>0) {
									compareDay.clear();
									
									for(int i8=0; i8<gyeonggi.size(); i8++) {
										Date day = (Date) gyeonggi.get(i8).get("day");
										compareDay.add(day);
										//logger.info("경기도 기준 - 바뀐 arrayList = "+compareDay);
									}
									
									//logger.info("경기도 기준 경기도 기준 값이 담긴 compareDay = "+compareDay);
//									logger.info("담겨 있는 리스트 : "+gyeonggi);
									for(int i2=0; i2<days.size(); i2++) {
										HashMap<String,Object> addMap1 = new HashMap<String,Object>();
										if(compareDay.contains(days.get(i2))) {
											
											// logger.info(i2+"번 째 동일");
										}else {
											addMap1.put("day", days.get(i2));
											addMap1.put("subJoinCount", 0);
											addMap1.put("province_name", "경기도");
											gyeonggi.add(addMap1);
										}
									}
									
									gyeonggi = (ArrayList<HashMap<String, Object>>) gyeonggi.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
									
									if(map.containsKey("data1")) {
										ArrayList<Object> gyeonggidata = new ArrayList<Object>();
										for(int a=0; a<gyeonggi.size(); a++) {
											gyeonggidata.add(gyeonggi.get(a).get("subJoinCount"));
										}
										map.put("data2", gyeonggidata);
									}else {
										ArrayList<Object> gyeonggidata = new ArrayList<Object>();
										for(int a=0; a<gyeonggi.size(); a++) {
											gyeonggidata.add(gyeonggi.get(a).get("subJoinCount"));
										}
										map.put("data1", gyeonggidata);
									}

								}
								if(gangwon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gangwon);
									map.put("gangwon", gangwon);
								}
								if(choongbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+choongbook);
									map.put("choongbook", choongbook);
								}
								if(choongnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+choongnam);
									map.put("choongnam", choongnam);
								}
								if(jeonbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeonbook);
									map.put("jeonbook", jeonbook);
								}
								if(jeonnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeonnam);
									map.put("jeonnam", jeonnam);
								}
								if(gyeongbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gyeongbook);
									map.put("gyeongbook", gyeongbook);
								}
								if(gyeongnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gyeongnam);
									map.put("gyeongnam", gyeongnam);
								}
								if(jeju.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeju);
									map.put("jeju", jeju);
								}
								
								map.put("labels", days);
								map.put("label", label);
								map.put("title", "서울,경기 "+year+"년 "+month+"월 구독 등록수");
								map.put("type", 2);
							
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender18(year,month,sub_idx);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<String> provinceName = new ArrayList<String>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
//								logger.info("타입은? = "+days.get(i9).getClass().getName());
							}
							
							for(int i7=0; i7<list.size(); i7++) {
								provinceName.add((String) list.get(i7).get("province_name"));
							}
							
							LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
							
//							map.put("days", days);
							
							for(int i=0; i<list.size(); i++) {
								if(list.get(i).get("province_name").equals("서울특별시")) {
									seoul.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("부산광역시")) {
									busan.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("대구광역시")) {
									daegu.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("인천광역시")) {
									incheon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("광주광역시")) {
									gwangju.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("대전광역시")) {
									daejeon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("울산광역시")) {
									ulsan.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("세종특별자치시")) {
									saejong.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경기도")) {
									gyeonggi.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("강원도")) {
									gangwon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("충청북도")) {
									choongbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("충청남도")) {
									choongnam.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("전라북도")) {
									jeonbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("전라남도")) {
									jeonnam.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경상북도")) {
									gyeongbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경상남도")) {
									gyeongnam.add(list.get(i));
								}else {
									jeju.add(list.get(i));
								}
							}
							
							
								
								if(seoul.size()>0) {
//									logger.info("담겨 있는 리스트 : "+seoul);
									for(int i8=0; i8<seoul.size(); i8++) {
										Date day = (Date) seoul.get(i8).get("day");
										compareDay.add(day);
										//logger.info("서울 기준 - 바뀐 arrayList = "+compareDay);
									}
									
									//logger.info("서울기준 - 값이 담긴 compareDay = "+compareDay);
//									logger.info("담겨 있는 리스트 : "+gyeonggi);
									for(int i2=0; i2<days.size(); i2++) {
										HashMap<String,Object> addMap1 = new HashMap<String,Object>();
										if(compareDay.contains(days.get(i2))) {
											
											//logger.info(i2+"번 째 동일");
										}else {
											addMap1.put("day", days.get(i2));
											addMap1.put("subJoinCount", 0);
											addMap1.put("province_name", "서울특별시");
											seoul.add(addMap1);
										}
									}
									
									seoul = (ArrayList<HashMap<String, Object>>) seoul.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
									
									if(map.containsKey("data1")) {
										ArrayList<Object> seouldata = new ArrayList<Object>();
										for(int a=0; a<seoul.size(); a++) {
											seouldata.add(seoul.get(a).get("subJoinCount"));
										}
										map.put("data2", seouldata);
									}else {
										ArrayList<Object> seouldata = new ArrayList<Object>();
										for(int a=0; a<seoul.size(); a++) {
											seouldata.add(seoul.get(a).get("subJoinCount"));
										}
										map.put("data1", seouldata);
									}
								}
								if(busan.size()>0) {
//									logger.info("담겨 있는 리스트 : "+busan);
									map.put("busan", busan);
								}
								if(daegu.size()>0) {
//									logger.info("담겨 있는 리스트 : "+daegu);
									map.put("daegu", daegu);
								}
								if(incheon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+incheon);
									map.put("incheon", incheon);
								}
								if(gwangju.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gwangju);
									map.put("gwangju", gwangju);
								}
								if(daejeon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+daejeon);
									map.put("daejeon", daejeon);
								}
								if(ulsan.size()>0) {
//									logger.info("담겨 있는 리스트 : "+ulsan);
									map.put("ulsan", ulsan);
								}
								if(saejong.size()>0) {
//									logger.info("담겨 있는 리스트 : "+saejong);
									map.put("saejong", saejong);
								}
								if(gyeonggi.size()>0) {
									compareDay.clear();
									
									for(int i8=0; i8<gyeonggi.size(); i8++) {
										Date day = (Date) gyeonggi.get(i8).get("day");
										compareDay.add(day);
										//logger.info("경기도 기준 - 바뀐 arrayList = "+compareDay);
									}
									
									//logger.info("경기도 기준 경기도 기준 값이 담긴 compareDay = "+compareDay);
//									logger.info("담겨 있는 리스트 : "+gyeonggi);
									for(int i2=0; i2<days.size(); i2++) {
										HashMap<String,Object> addMap1 = new HashMap<String,Object>();
										if(compareDay.contains(days.get(i2))) {
											
											// logger.info(i2+"번 째 동일");
										}else {
											addMap1.put("day", days.get(i2));
											addMap1.put("subJoinCount", 0);
											addMap1.put("province_name", "경기도");
											gyeonggi.add(addMap1);
										}
									}
									
									gyeonggi = (ArrayList<HashMap<String, Object>>) gyeonggi.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
									
									if(map.containsKey("data1")) {
										ArrayList<Object> gyeonggidata = new ArrayList<Object>();
										for(int a=0; a<gyeonggi.size(); a++) {
											gyeonggidata.add(gyeonggi.get(a).get("subJoinCount"));
										}
										map.put("data2", gyeonggidata);
									}else {
										ArrayList<Object> gyeonggidata = new ArrayList<Object>();
										for(int a=0; a<gyeonggi.size(); a++) {
											gyeonggidata.add(gyeonggi.get(a).get("subJoinCount"));
										}
										map.put("data1", gyeonggidata);
									}

								}
								if(gangwon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gangwon);
									map.put("gangwon", gangwon);
								}
								if(choongbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+choongbook);
									map.put("choongbook", choongbook);
								}
								if(choongnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+choongnam);
									map.put("choongnam", choongnam);
								}
								if(jeonbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeonbook);
									map.put("jeonbook", jeonbook);
								}
								if(jeonnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeonnam);
									map.put("jeonnam", jeonnam);
								}
								if(gyeongbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gyeongbook);
									map.put("gyeongbook", gyeongbook);
								}
								if(gyeongnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gyeongnam);
									map.put("gyeongnam", gyeongnam);
								}
								if(jeju.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeju);
									map.put("jeju", jeju);
								}
								
								map.put("labels", days);
								map.put("label", label);
								String sub_name = statservice.subName(sub_idx);
								map.put("title", "서울,경기 "+year+"년 "+month+"월 "+sub_name+" 등록수");
								map.put("type", 2);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender19(year,month,sub_sort_idx);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<String> provinceName = new ArrayList<String>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
//								logger.info("타입은? = "+days.get(i9).getClass().getName());
							}
							
							for(int i7=0; i7<list.size(); i7++) {
								provinceName.add((String) list.get(i7).get("province_name"));
							}
							
							LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
							
//							map.put("days", days);
							
							for(int i=0; i<list.size(); i++) {
								if(list.get(i).get("province_name").equals("서울특별시")) {
									seoul.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("부산광역시")) {
									busan.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("대구광역시")) {
									daegu.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("인천광역시")) {
									incheon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("광주광역시")) {
									gwangju.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("대전광역시")) {
									daejeon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("울산광역시")) {
									ulsan.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("세종특별자치시")) {
									saejong.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경기도")) {
									gyeonggi.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("강원도")) {
									gangwon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("충청북도")) {
									choongbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("충청남도")) {
									choongnam.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("전라북도")) {
									jeonbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("전라남도")) {
									jeonnam.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경상북도")) {
									gyeongbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경상남도")) {
									gyeongnam.add(list.get(i));
								}else {
									jeju.add(list.get(i));
								}
							}
							
							
								
								if(seoul.size()>0) {
//									logger.info("담겨 있는 리스트 : "+seoul);
									for(int i8=0; i8<seoul.size(); i8++) {
										Date day = (Date) seoul.get(i8).get("day");
										compareDay.add(day);
										//logger.info("서울 기준 - 바뀐 arrayList = "+compareDay);
									}
									
									//logger.info("서울기준 - 값이 담긴 compareDay = "+compareDay);
//									logger.info("담겨 있는 리스트 : "+gyeonggi);
									for(int i2=0; i2<days.size(); i2++) {
										HashMap<String,Object> addMap1 = new HashMap<String,Object>();
										if(compareDay.contains(days.get(i2))) {
											
											//logger.info(i2+"번 째 동일");
										}else {
											addMap1.put("day", days.get(i2));
											addMap1.put("subJoinCount", 0);
											addMap1.put("province_name", "서울특별시");
											seoul.add(addMap1);
										}
									}
									
									seoul = (ArrayList<HashMap<String, Object>>) seoul.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
									
									if(map.containsKey("data1")) {
										ArrayList<Object> seouldata = new ArrayList<Object>();
										for(int a=0; a<seoul.size(); a++) {
											seouldata.add(seoul.get(a).get("subJoinCount"));
										}
										map.put("data2", seouldata);
									}else {
										ArrayList<Object> seouldata = new ArrayList<Object>();
										for(int a=0; a<seoul.size(); a++) {
											seouldata.add(seoul.get(a).get("subJoinCount"));
										}
										map.put("data1", seouldata);
									}
								}
								if(busan.size()>0) {
//									logger.info("담겨 있는 리스트 : "+busan);
									map.put("busan", busan);
								}
								if(daegu.size()>0) {
//									logger.info("담겨 있는 리스트 : "+daegu);
									map.put("daegu", daegu);
								}
								if(incheon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+incheon);
									map.put("incheon", incheon);
								}
								if(gwangju.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gwangju);
									map.put("gwangju", gwangju);
								}
								if(daejeon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+daejeon);
									map.put("daejeon", daejeon);
								}
								if(ulsan.size()>0) {
//									logger.info("담겨 있는 리스트 : "+ulsan);
									map.put("ulsan", ulsan);
								}
								if(saejong.size()>0) {
//									logger.info("담겨 있는 리스트 : "+saejong);
									map.put("saejong", saejong);
								}
								if(gyeonggi.size()>0) {
									compareDay.clear();
									
									for(int i8=0; i8<gyeonggi.size(); i8++) {
										Date day = (Date) gyeonggi.get(i8).get("day");
										compareDay.add(day);
										//logger.info("경기도 기준 - 바뀐 arrayList = "+compareDay);
									}
									
									//logger.info("경기도 기준 경기도 기준 값이 담긴 compareDay = "+compareDay);
//									logger.info("담겨 있는 리스트 : "+gyeonggi);
									for(int i2=0; i2<days.size(); i2++) {
										HashMap<String,Object> addMap1 = new HashMap<String,Object>();
										if(compareDay.contains(days.get(i2))) {
											
											// logger.info(i2+"번 째 동일");
										}else {
											addMap1.put("day", days.get(i2));
											addMap1.put("subJoinCount", 0);
											addMap1.put("province_name", "경기도");
											gyeonggi.add(addMap1);
										}
									}
									
									gyeonggi = (ArrayList<HashMap<String, Object>>) gyeonggi.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
									
									if(map.containsKey("data1")) {
										ArrayList<Object> gyeonggidata = new ArrayList<Object>();
										for(int a=0; a<gyeonggi.size(); a++) {
											gyeonggidata.add(gyeonggi.get(a).get("subJoinCount"));
										}
										map.put("data2", gyeonggidata);
									}else {
										ArrayList<Object> gyeonggidata = new ArrayList<Object>();
										for(int a=0; a<gyeonggi.size(); a++) {
											gyeonggidata.add(gyeonggi.get(a).get("subJoinCount"));
										}
										map.put("data1", gyeonggidata);
									}

								}
								if(gangwon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gangwon);
									map.put("gangwon", gangwon);
								}
								if(choongbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+choongbook);
									map.put("choongbook", choongbook);
								}
								if(choongnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+choongnam);
									map.put("choongnam", choongnam);
								}
								if(jeonbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeonbook);
									map.put("jeonbook", jeonbook);
								}
								if(jeonnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeonnam);
									map.put("jeonnam", jeonnam);
								}
								if(gyeongbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gyeongbook);
									map.put("gyeongbook", gyeongbook);
								}
								if(gyeongnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gyeongnam);
									map.put("gyeongnam", gyeongnam);
								}
								if(jeju.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeju);
									map.put("jeju", jeju);
								}
								
								map.put("labels", days);
								map.put("label", label);
								String sub_sort_type = statservice.subSortType(sub_sort_idx);
								map.put("title", "서울,경기 "+year+"년 "+month+"월 "+sub_sort_type+" 등록수");
								map.put("type", 2);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender20(year,month,sub_sort_idx,sub_idx);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<String> provinceName = new ArrayList<String>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
//								logger.info("타입은? = "+days.get(i9).getClass().getName());
							}
							
							for(int i7=0; i7<list.size(); i7++) {
								provinceName.add((String) list.get(i7).get("province_name"));
							}
							
							LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
							
//							map.put("days", days);
							
							for(int i=0; i<list.size(); i++) {
								if(list.get(i).get("province_name").equals("서울특별시")) {
									seoul.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("부산광역시")) {
									busan.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("대구광역시")) {
									daegu.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("인천광역시")) {
									incheon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("광주광역시")) {
									gwangju.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("대전광역시")) {
									daejeon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("울산광역시")) {
									ulsan.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("세종특별자치시")) {
									saejong.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경기도")) {
									gyeonggi.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("강원도")) {
									gangwon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("충청북도")) {
									choongbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("충청남도")) {
									choongnam.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("전라북도")) {
									jeonbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("전라남도")) {
									jeonnam.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경상북도")) {
									gyeongbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경상남도")) {
									gyeongnam.add(list.get(i));
								}else {
									jeju.add(list.get(i));
								}
							}
							
							
								
								if(seoul.size()>0) {
//									logger.info("담겨 있는 리스트 : "+seoul);
									for(int i8=0; i8<seoul.size(); i8++) {
										Date day = (Date) seoul.get(i8).get("day");
										compareDay.add(day);
										//logger.info("서울 기준 - 바뀐 arrayList = "+compareDay);
									}
									
									//logger.info("서울기준 - 값이 담긴 compareDay = "+compareDay);
//									logger.info("담겨 있는 리스트 : "+gyeonggi);
									for(int i2=0; i2<days.size(); i2++) {
										HashMap<String,Object> addMap1 = new HashMap<String,Object>();
										if(compareDay.contains(days.get(i2))) {
											
											//logger.info(i2+"번 째 동일");
										}else {
											addMap1.put("day", days.get(i2));
											addMap1.put("subJoinCount", 0);
											addMap1.put("province_name", "서울특별시");
											seoul.add(addMap1);
										}
									}
									
									seoul = (ArrayList<HashMap<String, Object>>) seoul.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
									
									if(map.containsKey("data1")) {
										ArrayList<Object> seouldata = new ArrayList<Object>();
										for(int a=0; a<seoul.size(); a++) {
											seouldata.add(seoul.get(a).get("subJoinCount"));
										}
										map.put("data2", seouldata);
									}else {
										ArrayList<Object> seouldata = new ArrayList<Object>();
										for(int a=0; a<seoul.size(); a++) {
											seouldata.add(seoul.get(a).get("subJoinCount"));
										}
										map.put("data1", seouldata);
									}
								}
								if(busan.size()>0) {
//									logger.info("담겨 있는 리스트 : "+busan);
									map.put("busan", busan);
								}
								if(daegu.size()>0) {
//									logger.info("담겨 있는 리스트 : "+daegu);
									map.put("daegu", daegu);
								}
								if(incheon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+incheon);
									map.put("incheon", incheon);
								}
								if(gwangju.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gwangju);
									map.put("gwangju", gwangju);
								}
								if(daejeon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+daejeon);
									map.put("daejeon", daejeon);
								}
								if(ulsan.size()>0) {
//									logger.info("담겨 있는 리스트 : "+ulsan);
									map.put("ulsan", ulsan);
								}
								if(saejong.size()>0) {
//									logger.info("담겨 있는 리스트 : "+saejong);
									map.put("saejong", saejong);
								}
								if(gyeonggi.size()>0) {
									compareDay.clear();
									
									for(int i8=0; i8<gyeonggi.size(); i8++) {
										Date day = (Date) gyeonggi.get(i8).get("day");
										compareDay.add(day);
										//logger.info("경기도 기준 - 바뀐 arrayList = "+compareDay);
									}
									
									//logger.info("경기도 기준 경기도 기준 값이 담긴 compareDay = "+compareDay);
//									logger.info("담겨 있는 리스트 : "+gyeonggi);
									for(int i2=0; i2<days.size(); i2++) {
										HashMap<String,Object> addMap1 = new HashMap<String,Object>();
										if(compareDay.contains(days.get(i2))) {
											
											// logger.info(i2+"번 째 동일");
										}else {
											addMap1.put("day", days.get(i2));
											addMap1.put("subJoinCount", 0);
											addMap1.put("province_name", "경기도");
											gyeonggi.add(addMap1);
										}
									}
									
									gyeonggi = (ArrayList<HashMap<String, Object>>) gyeonggi.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
									
									if(map.containsKey("data1")) {
										ArrayList<Object> gyeonggidata = new ArrayList<Object>();
										for(int a=0; a<gyeonggi.size(); a++) {
											gyeonggidata.add(gyeonggi.get(a).get("subJoinCount"));
										}
										map.put("data2", gyeonggidata);
									}else {
										ArrayList<Object> gyeonggidata = new ArrayList<Object>();
										for(int a=0; a<gyeonggi.size(); a++) {
											gyeonggidata.add(gyeonggi.get(a).get("subJoinCount"));
										}
										map.put("data1", gyeonggidata);
									}

								}
								if(gangwon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gangwon);
									map.put("gangwon", gangwon);
								}
								if(choongbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+choongbook);
									map.put("choongbook", choongbook);
								}
								if(choongnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+choongnam);
									map.put("choongnam", choongnam);
								}
								if(jeonbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeonbook);
									map.put("jeonbook", jeonbook);
								}
								if(jeonnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeonnam);
									map.put("jeonnam", jeonnam);
								}
								if(gyeongbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gyeongbook);
									map.put("gyeongbook", gyeongbook);
								}
								if(gyeongnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gyeongnam);
									map.put("gyeongnam", gyeongnam);
								}
								if(jeju.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeju);
									map.put("jeju", jeju);
								}
								
								map.put("labels", days);
								map.put("label", label);
								String sub_name = statservice.subName(sub_idx);
								map.put("title", "서울,경기 "+year+"년 "+month+"월 "+sub_name+" 등록수");
								map.put("type", 2);
						}
					}
				}else { // 성별 선택
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender21(year,month,gender);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<String> provinceName = new ArrayList<String>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
//								logger.info("타입은? = "+days.get(i9).getClass().getName());
							}
							
							for(int i7=0; i7<list.size(); i7++) {
								provinceName.add((String) list.get(i7).get("province_name"));
							}
							
							LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
							
//							map.put("days", days);
							
							for(int i=0; i<list.size(); i++) {
								if(list.get(i).get("province_name").equals("서울특별시")) {
									seoul.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("부산광역시")) {
									busan.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("대구광역시")) {
									daegu.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("인천광역시")) {
									incheon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("광주광역시")) {
									gwangju.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("대전광역시")) {
									daejeon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("울산광역시")) {
									ulsan.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("세종특별자치시")) {
									saejong.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경기도")) {
									gyeonggi.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("강원도")) {
									gangwon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("충청북도")) {
									choongbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("충청남도")) {
									choongnam.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("전라북도")) {
									jeonbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("전라남도")) {
									jeonnam.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경상북도")) {
									gyeongbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경상남도")) {
									gyeongnam.add(list.get(i));
								}else {
									jeju.add(list.get(i));
								}
							}
							
							
								
								if(seoul.size()>0) {
//									logger.info("담겨 있는 리스트 : "+seoul);
									for(int i8=0; i8<seoul.size(); i8++) {
										Date day = (Date) seoul.get(i8).get("day");
										compareDay.add(day);
										//logger.info("서울 기준 - 바뀐 arrayList = "+compareDay);
									}
									
									//logger.info("서울기준 - 값이 담긴 compareDay = "+compareDay);
//									logger.info("담겨 있는 리스트 : "+gyeonggi);
									for(int i2=0; i2<days.size(); i2++) {
										HashMap<String,Object> addMap1 = new HashMap<String,Object>();
										if(compareDay.contains(days.get(i2))) {
											
											//logger.info(i2+"번 째 동일");
										}else {
											addMap1.put("day", days.get(i2));
											addMap1.put("subJoinCount", 0);
											addMap1.put("province_name", "서울특별시");
											seoul.add(addMap1);
										}
									}
									
									seoul = (ArrayList<HashMap<String, Object>>) seoul.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
									
									if(map.containsKey("data1")) {
										ArrayList<Object> seouldata = new ArrayList<Object>();
										for(int a=0; a<seoul.size(); a++) {
											seouldata.add(seoul.get(a).get("subJoinCount"));
										}
										map.put("data2", seouldata);
									}else {
										ArrayList<Object> seouldata = new ArrayList<Object>();
										for(int a=0; a<seoul.size(); a++) {
											seouldata.add(seoul.get(a).get("subJoinCount"));
										}
										map.put("data1", seouldata);
									}
								}
								if(busan.size()>0) {
//									logger.info("담겨 있는 리스트 : "+busan);
									map.put("busan", busan);
								}
								if(daegu.size()>0) {
//									logger.info("담겨 있는 리스트 : "+daegu);
									map.put("daegu", daegu);
								}
								if(incheon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+incheon);
									map.put("incheon", incheon);
								}
								if(gwangju.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gwangju);
									map.put("gwangju", gwangju);
								}
								if(daejeon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+daejeon);
									map.put("daejeon", daejeon);
								}
								if(ulsan.size()>0) {
//									logger.info("담겨 있는 리스트 : "+ulsan);
									map.put("ulsan", ulsan);
								}
								if(saejong.size()>0) {
//									logger.info("담겨 있는 리스트 : "+saejong);
									map.put("saejong", saejong);
								}
								if(gyeonggi.size()>0) {
									compareDay.clear();
									
									for(int i8=0; i8<gyeonggi.size(); i8++) {
										Date day = (Date) gyeonggi.get(i8).get("day");
										compareDay.add(day);
										//logger.info("경기도 기준 - 바뀐 arrayList = "+compareDay);
									}
									
									//logger.info("경기도 기준 경기도 기준 값이 담긴 compareDay = "+compareDay);
//									logger.info("담겨 있는 리스트 : "+gyeonggi);
									for(int i2=0; i2<days.size(); i2++) {
										HashMap<String,Object> addMap1 = new HashMap<String,Object>();
										if(compareDay.contains(days.get(i2))) {
											
											// logger.info(i2+"번 째 동일");
										}else {
											addMap1.put("day", days.get(i2));
											addMap1.put("subJoinCount", 0);
											addMap1.put("province_name", "경기도");
											gyeonggi.add(addMap1);
										}
									}
									
									gyeonggi = (ArrayList<HashMap<String, Object>>) gyeonggi.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
									
									if(map.containsKey("data1")) {
										ArrayList<Object> gyeonggidata = new ArrayList<Object>();
										for(int a=0; a<gyeonggi.size(); a++) {
											gyeonggidata.add(gyeonggi.get(a).get("subJoinCount"));
										}
										map.put("data2", gyeonggidata);
									}else {
										ArrayList<Object> gyeonggidata = new ArrayList<Object>();
										for(int a=0; a<gyeonggi.size(); a++) {
											gyeonggidata.add(gyeonggi.get(a).get("subJoinCount"));
										}
										map.put("data1", gyeonggidata);
									}

								}
								if(gangwon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gangwon);
									map.put("gangwon", gangwon);
								}
								if(choongbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+choongbook);
									map.put("choongbook", choongbook);
								}
								if(choongnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+choongnam);
									map.put("choongnam", choongnam);
								}
								if(jeonbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeonbook);
									map.put("jeonbook", jeonbook);
								}
								if(jeonnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeonnam);
									map.put("jeonnam", jeonnam);
								}
								if(gyeongbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gyeongbook);
									map.put("gyeongbook", gyeongbook);
								}
								if(gyeongnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gyeongnam);
									map.put("gyeongnam", gyeongnam);
								}
								if(jeju.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeju);
									map.put("jeju", jeju);
								}
								
								map.put("labels", days);
								map.put("label", label);
								map.put("title", "서울,경기 "+year+"년 "+month+"월 구독 등록수("+gender+")");
								map.put("type", 2);
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender22(year,month,gender,sub_idx);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<String> provinceName = new ArrayList<String>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
//								logger.info("타입은? = "+days.get(i9).getClass().getName());
							}
							
							for(int i7=0; i7<list.size(); i7++) {
								provinceName.add((String) list.get(i7).get("province_name"));
							}
							
							LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
							
//							map.put("days", days);
							
							for(int i=0; i<list.size(); i++) {
								if(list.get(i).get("province_name").equals("서울특별시")) {
									seoul.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("부산광역시")) {
									busan.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("대구광역시")) {
									daegu.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("인천광역시")) {
									incheon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("광주광역시")) {
									gwangju.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("대전광역시")) {
									daejeon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("울산광역시")) {
									ulsan.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("세종특별자치시")) {
									saejong.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경기도")) {
									gyeonggi.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("강원도")) {
									gangwon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("충청북도")) {
									choongbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("충청남도")) {
									choongnam.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("전라북도")) {
									jeonbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("전라남도")) {
									jeonnam.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경상북도")) {
									gyeongbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경상남도")) {
									gyeongnam.add(list.get(i));
								}else {
									jeju.add(list.get(i));
								}
							}
							
							
								
								if(seoul.size()>0) {
//									logger.info("담겨 있는 리스트 : "+seoul);
									for(int i8=0; i8<seoul.size(); i8++) {
										Date day = (Date) seoul.get(i8).get("day");
										compareDay.add(day);
										//logger.info("서울 기준 - 바뀐 arrayList = "+compareDay);
									}
									
									//logger.info("서울기준 - 값이 담긴 compareDay = "+compareDay);
//									logger.info("담겨 있는 리스트 : "+gyeonggi);
									for(int i2=0; i2<days.size(); i2++) {
										HashMap<String,Object> addMap1 = new HashMap<String,Object>();
										if(compareDay.contains(days.get(i2))) {
											
											//logger.info(i2+"번 째 동일");
										}else {
											addMap1.put("day", days.get(i2));
											addMap1.put("subJoinCount", 0);
											addMap1.put("province_name", "서울특별시");
											seoul.add(addMap1);
										}
									}
									
									seoul = (ArrayList<HashMap<String, Object>>) seoul.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
									
									if(map.containsKey("data1")) {
										ArrayList<Object> seouldata = new ArrayList<Object>();
										for(int a=0; a<seoul.size(); a++) {
											seouldata.add(seoul.get(a).get("subJoinCount"));
										}
										map.put("data2", seouldata);
									}else {
										ArrayList<Object> seouldata = new ArrayList<Object>();
										for(int a=0; a<seoul.size(); a++) {
											seouldata.add(seoul.get(a).get("subJoinCount"));
										}
										map.put("data1", seouldata);
									}
								}
								if(busan.size()>0) {
//									logger.info("담겨 있는 리스트 : "+busan);
									map.put("busan", busan);
								}
								if(daegu.size()>0) {
//									logger.info("담겨 있는 리스트 : "+daegu);
									map.put("daegu", daegu);
								}
								if(incheon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+incheon);
									map.put("incheon", incheon);
								}
								if(gwangju.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gwangju);
									map.put("gwangju", gwangju);
								}
								if(daejeon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+daejeon);
									map.put("daejeon", daejeon);
								}
								if(ulsan.size()>0) {
//									logger.info("담겨 있는 리스트 : "+ulsan);
									map.put("ulsan", ulsan);
								}
								if(saejong.size()>0) {
//									logger.info("담겨 있는 리스트 : "+saejong);
									map.put("saejong", saejong);
								}
								if(gyeonggi.size()>0) {
									compareDay.clear();
									
									for(int i8=0; i8<gyeonggi.size(); i8++) {
										Date day = (Date) gyeonggi.get(i8).get("day");
										compareDay.add(day);
										//logger.info("경기도 기준 - 바뀐 arrayList = "+compareDay);
									}
									
									//logger.info("경기도 기준 경기도 기준 값이 담긴 compareDay = "+compareDay);
//									logger.info("담겨 있는 리스트 : "+gyeonggi);
									for(int i2=0; i2<days.size(); i2++) {
										HashMap<String,Object> addMap1 = new HashMap<String,Object>();
										if(compareDay.contains(days.get(i2))) {
											
											// logger.info(i2+"번 째 동일");
										}else {
											addMap1.put("day", days.get(i2));
											addMap1.put("subJoinCount", 0);
											addMap1.put("province_name", "경기도");
											gyeonggi.add(addMap1);
										}
									}
									
									gyeonggi = (ArrayList<HashMap<String, Object>>) gyeonggi.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
									
									if(map.containsKey("data1")) {
										ArrayList<Object> gyeonggidata = new ArrayList<Object>();
										for(int a=0; a<gyeonggi.size(); a++) {
											gyeonggidata.add(gyeonggi.get(a).get("subJoinCount"));
										}
										map.put("data2", gyeonggidata);
									}else {
										ArrayList<Object> gyeonggidata = new ArrayList<Object>();
										for(int a=0; a<gyeonggi.size(); a++) {
											gyeonggidata.add(gyeonggi.get(a).get("subJoinCount"));
										}
										map.put("data1", gyeonggidata);
									}

								}
								if(gangwon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gangwon);
									map.put("gangwon", gangwon);
								}
								if(choongbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+choongbook);
									map.put("choongbook", choongbook);
								}
								if(choongnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+choongnam);
									map.put("choongnam", choongnam);
								}
								if(jeonbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeonbook);
									map.put("jeonbook", jeonbook);
								}
								if(jeonnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeonnam);
									map.put("jeonnam", jeonnam);
								}
								if(gyeongbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gyeongbook);
									map.put("gyeongbook", gyeongbook);
								}
								if(gyeongnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gyeongnam);
									map.put("gyeongnam", gyeongnam);
								}
								if(jeju.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeju);
									map.put("jeju", jeju);
								}
								
								map.put("labels", days);
								map.put("label", label);
								String sub_name = statservice.subName(sub_idx);
								map.put("title", "서울,경기 "+year+"년 "+month+"월 "+sub_name+" 등록수("+gender+")");
								map.put("type", 2);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender23(year,month,gender,sub_sort_idx);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<String> provinceName = new ArrayList<String>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
//								logger.info("타입은? = "+days.get(i9).getClass().getName());
							}
							
							for(int i7=0; i7<list.size(); i7++) {
								provinceName.add((String) list.get(i7).get("province_name"));
							}
							
							LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
							
//							map.put("days", days);
							
							for(int i=0; i<list.size(); i++) {
								if(list.get(i).get("province_name").equals("서울특별시")) {
									seoul.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("부산광역시")) {
									busan.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("대구광역시")) {
									daegu.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("인천광역시")) {
									incheon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("광주광역시")) {
									gwangju.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("대전광역시")) {
									daejeon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("울산광역시")) {
									ulsan.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("세종특별자치시")) {
									saejong.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경기도")) {
									gyeonggi.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("강원도")) {
									gangwon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("충청북도")) {
									choongbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("충청남도")) {
									choongnam.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("전라북도")) {
									jeonbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("전라남도")) {
									jeonnam.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경상북도")) {
									gyeongbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경상남도")) {
									gyeongnam.add(list.get(i));
								}else {
									jeju.add(list.get(i));
								}
							}
							
							
								
								if(seoul.size()>0) {
//									logger.info("담겨 있는 리스트 : "+seoul);
									for(int i8=0; i8<seoul.size(); i8++) {
										Date day = (Date) seoul.get(i8).get("day");
										compareDay.add(day);
										//logger.info("서울 기준 - 바뀐 arrayList = "+compareDay);
									}
									
									//logger.info("서울기준 - 값이 담긴 compareDay = "+compareDay);
//									logger.info("담겨 있는 리스트 : "+gyeonggi);
									for(int i2=0; i2<days.size(); i2++) {
										HashMap<String,Object> addMap1 = new HashMap<String,Object>();
										if(compareDay.contains(days.get(i2))) {
											
											//logger.info(i2+"번 째 동일");
										}else {
											addMap1.put("day", days.get(i2));
											addMap1.put("subJoinCount", 0);
											addMap1.put("province_name", "서울특별시");
											seoul.add(addMap1);
										}
									}
									
									seoul = (ArrayList<HashMap<String, Object>>) seoul.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
									
									if(map.containsKey("data1")) {
										ArrayList<Object> seouldata = new ArrayList<Object>();
										for(int a=0; a<seoul.size(); a++) {
											seouldata.add(seoul.get(a).get("subJoinCount"));
										}
										map.put("data2", seouldata);
									}else {
										ArrayList<Object> seouldata = new ArrayList<Object>();
										for(int a=0; a<seoul.size(); a++) {
											seouldata.add(seoul.get(a).get("subJoinCount"));
										}
										map.put("data1", seouldata);
									}
								}
								if(busan.size()>0) {
//									logger.info("담겨 있는 리스트 : "+busan);
									map.put("busan", busan);
								}
								if(daegu.size()>0) {
//									logger.info("담겨 있는 리스트 : "+daegu);
									map.put("daegu", daegu);
								}
								if(incheon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+incheon);
									map.put("incheon", incheon);
								}
								if(gwangju.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gwangju);
									map.put("gwangju", gwangju);
								}
								if(daejeon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+daejeon);
									map.put("daejeon", daejeon);
								}
								if(ulsan.size()>0) {
//									logger.info("담겨 있는 리스트 : "+ulsan);
									map.put("ulsan", ulsan);
								}
								if(saejong.size()>0) {
//									logger.info("담겨 있는 리스트 : "+saejong);
									map.put("saejong", saejong);
								}
								if(gyeonggi.size()>0) {
									compareDay.clear();
									
									for(int i8=0; i8<gyeonggi.size(); i8++) {
										Date day = (Date) gyeonggi.get(i8).get("day");
										compareDay.add(day);
										//logger.info("경기도 기준 - 바뀐 arrayList = "+compareDay);
									}
									
									//logger.info("경기도 기준 경기도 기준 값이 담긴 compareDay = "+compareDay);
//									logger.info("담겨 있는 리스트 : "+gyeonggi);
									for(int i2=0; i2<days.size(); i2++) {
										HashMap<String,Object> addMap1 = new HashMap<String,Object>();
										if(compareDay.contains(days.get(i2))) {
											
											// logger.info(i2+"번 째 동일");
										}else {
											addMap1.put("day", days.get(i2));
											addMap1.put("subJoinCount", 0);
											addMap1.put("province_name", "경기도");
											gyeonggi.add(addMap1);
										}
									}
									
									gyeonggi = (ArrayList<HashMap<String, Object>>) gyeonggi.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
									
									if(map.containsKey("data1")) {
										ArrayList<Object> gyeonggidata = new ArrayList<Object>();
										for(int a=0; a<gyeonggi.size(); a++) {
											gyeonggidata.add(gyeonggi.get(a).get("subJoinCount"));
										}
										map.put("data2", gyeonggidata);
									}else {
										ArrayList<Object> gyeonggidata = new ArrayList<Object>();
										for(int a=0; a<gyeonggi.size(); a++) {
											gyeonggidata.add(gyeonggi.get(a).get("subJoinCount"));
										}
										map.put("data1", gyeonggidata);
									}

								}
								if(gangwon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gangwon);
									map.put("gangwon", gangwon);
								}
								if(choongbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+choongbook);
									map.put("choongbook", choongbook);
								}
								if(choongnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+choongnam);
									map.put("choongnam", choongnam);
								}
								if(jeonbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeonbook);
									map.put("jeonbook", jeonbook);
								}
								if(jeonnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeonnam);
									map.put("jeonnam", jeonnam);
								}
								if(gyeongbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gyeongbook);
									map.put("gyeongbook", gyeongbook);
								}
								if(gyeongnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gyeongnam);
									map.put("gyeongnam", gyeongnam);
								}
								if(jeju.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeju);
									map.put("jeju", jeju);
								}
								
								map.put("labels", days);
								map.put("label", label);
								String sub_sort_type = statservice.subSortType(sub_sort_idx);
								map.put("title", "서울,경기 "+year+"년 "+month+"월 "+sub_sort_type+" 등록수("+gender+")");
								map.put("type", 2);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender24(year,month,gender,sub_sort_idx,sub_idx);
							logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<String> provinceName = new ArrayList<String>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
//								logger.info("타입은? = "+days.get(i9).getClass().getName());
							}
							
							for(int i7=0; i7<list.size(); i7++) {
								provinceName.add((String) list.get(i7).get("province_name"));
							}
							
							LinkedHashSet<String> label = new LinkedHashSet<>();
					        for(String item : provinceName){
					        	label.add(item);
					        }
							
//							map.put("days", days);
							
							for(int i=0; i<list.size(); i++) {
								if(list.get(i).get("province_name").equals("서울특별시")) {
									seoul.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("부산광역시")) {
									busan.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("대구광역시")) {
									daegu.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("인천광역시")) {
									incheon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("광주광역시")) {
									gwangju.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("대전광역시")) {
									daejeon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("울산광역시")) {
									ulsan.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("세종특별자치시")) {
									saejong.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경기도")) {
									gyeonggi.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("강원도")) {
									gangwon.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("충청북도")) {
									choongbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("충청남도")) {
									choongnam.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("전라북도")) {
									jeonbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("전라남도")) {
									jeonnam.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경상북도")) {
									gyeongbook.add(list.get(i));
								}else if(list.get(i).get("province_name").equals("경상남도")) {
									gyeongnam.add(list.get(i));
								}else {
									jeju.add(list.get(i));
								}
							}
							
							
								
								if(seoul.size()>0) {
//									logger.info("담겨 있는 리스트 : "+seoul);
									for(int i8=0; i8<seoul.size(); i8++) {
										Date day = (Date) seoul.get(i8).get("day");
										compareDay.add(day);
										//logger.info("서울 기준 - 바뀐 arrayList = "+compareDay);
									}
									
									//logger.info("서울기준 - 값이 담긴 compareDay = "+compareDay);
//									logger.info("담겨 있는 리스트 : "+gyeonggi);
									for(int i2=0; i2<days.size(); i2++) {
										HashMap<String,Object> addMap1 = new HashMap<String,Object>();
										if(compareDay.contains(days.get(i2))) {
											
											//logger.info(i2+"번 째 동일");
										}else {
											addMap1.put("day", days.get(i2));
											addMap1.put("subJoinCount", 0);
											addMap1.put("province_name", "서울특별시");
											seoul.add(addMap1);
										}
									}
									
									seoul = (ArrayList<HashMap<String, Object>>) seoul.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
									
									if(map.containsKey("data1")) {
										ArrayList<Object> seouldata = new ArrayList<Object>();
										for(int a=0; a<seoul.size(); a++) {
											seouldata.add(seoul.get(a).get("subJoinCount"));
										}
										map.put("data2", seouldata);
									}else {
										ArrayList<Object> seouldata = new ArrayList<Object>();
										for(int a=0; a<seoul.size(); a++) {
											seouldata.add(seoul.get(a).get("subJoinCount"));
										}
										map.put("data1", seouldata);
									}
								}
								if(busan.size()>0) {
//									logger.info("담겨 있는 리스트 : "+busan);
									map.put("busan", busan);
								}
								if(daegu.size()>0) {
//									logger.info("담겨 있는 리스트 : "+daegu);
									map.put("daegu", daegu);
								}
								if(incheon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+incheon);
									map.put("incheon", incheon);
								}
								if(gwangju.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gwangju);
									map.put("gwangju", gwangju);
								}
								if(daejeon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+daejeon);
									map.put("daejeon", daejeon);
								}
								if(ulsan.size()>0) {
//									logger.info("담겨 있는 리스트 : "+ulsan);
									map.put("ulsan", ulsan);
								}
								if(saejong.size()>0) {
//									logger.info("담겨 있는 리스트 : "+saejong);
									map.put("saejong", saejong);
								}
								if(gyeonggi.size()>0) {
									compareDay.clear();
									
									for(int i8=0; i8<gyeonggi.size(); i8++) {
										Date day = (Date) gyeonggi.get(i8).get("day");
										compareDay.add(day);
										//logger.info("경기도 기준 - 바뀐 arrayList = "+compareDay);
									}
									
									//logger.info("경기도 기준 경기도 기준 값이 담긴 compareDay = "+compareDay);
//									logger.info("담겨 있는 리스트 : "+gyeonggi);
									for(int i2=0; i2<days.size(); i2++) {
										HashMap<String,Object> addMap1 = new HashMap<String,Object>();
										if(compareDay.contains(days.get(i2))) {
											
											// logger.info(i2+"번 째 동일");
										}else {
											addMap1.put("day", days.get(i2));
											addMap1.put("subJoinCount", 0);
											addMap1.put("province_name", "경기도");
											gyeonggi.add(addMap1);
										}
									}
									
									gyeonggi = (ArrayList<HashMap<String, Object>>) gyeonggi.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
									
									if(map.containsKey("data1")) {
										ArrayList<Object> gyeonggidata = new ArrayList<Object>();
										for(int a=0; a<gyeonggi.size(); a++) {
											gyeonggidata.add(gyeonggi.get(a).get("subJoinCount"));
										}
										map.put("data2", gyeonggidata);
									}else {
										ArrayList<Object> gyeonggidata = new ArrayList<Object>();
										for(int a=0; a<gyeonggi.size(); a++) {
											gyeonggidata.add(gyeonggi.get(a).get("subJoinCount"));
										}
										map.put("data1", gyeonggidata);
									}

								}
								if(gangwon.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gangwon);
									map.put("gangwon", gangwon);
								}
								if(choongbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+choongbook);
									map.put("choongbook", choongbook);
								}
								if(choongnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+choongnam);
									map.put("choongnam", choongnam);
								}
								if(jeonbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeonbook);
									map.put("jeonbook", jeonbook);
								}
								if(jeonnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeonnam);
									map.put("jeonnam", jeonnam);
								}
								if(gyeongbook.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gyeongbook);
									map.put("gyeongbook", gyeongbook);
								}
								if(gyeongnam.size()>0) {
//									logger.info("담겨 있는 리스트 : "+gyeongnam);
									map.put("gyeongnam", gyeongnam);
								}
								if(jeju.size()>0) {
//									logger.info("담겨 있는 리스트 : "+jeju);
									map.put("jeju", jeju);
								}
								
								map.put("labels", days);
								map.put("label", label);
								String sub_name = statservice.subName(sub_idx);
								map.put("title", "서울,경기 "+year+"년 "+month+"월 "+sub_name+" 등록수("+gender+")");
								map.put("type", 2);
						}
					}
				}
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		if(size == 6) {
			// size 가 6라는 것은 시도만 선택하고 시군구/매장은 선택하지 않았을 때이다.
			String province_idx = params.get("province_idx");
			String year = params.get("year");
			String month = params.get("month");
			String gender = params.get("gender");
			String sub_sort_idx = params.get("sub_sort_idx");
			String sub_idx = params.get("sub_idx");
			
			if(year.equals("noYear") && month.equals("noMonth")) { // 년도와 월이 둘다 선택되지 않았을 때
				if(gender.equals("genderAll")) { // 성별 전체
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
							// 시도/시군구/매장 선택 x, 년도, 월 선택 x, 성별 전체, 횟수권 요일권 통합, 구독권 선택 x 
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender25(province_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							map.put("title", provinceName+" 연간 총 구독 등록수");
							map.put("type", 1);

						}else{ // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							// 시도/시군구/매장 선택 x, 년도, 월 선택 x, 성별 전체, 횟수권 요일권 통합, 구독권 선택 O
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender26(province_idx,sub_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" 연간 "+sub_name+" 등록수");
							
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 중 하나가 선택 되었을 때
						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender27(province_idx,sub_sort_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" 연간 "+sub_sort_type+" 등록수");
							
							map.put("type", 1);
						}else { // 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender28(province_idx,sub_sort_idx, sub_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" 연간 "+sub_name+" 등록수");
							
							map.put("type", 1);
						}
					}
				}else { // 성별이 남 또는 여 가 선택 되었을 때
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender29(province_idx,gender);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							map.put("title", provinceName+" 연간 총 구독 등록수("+gender+")");
							
							map.put("type", 1);
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender30(province_idx,gender, sub_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" 연간 "+sub_name+" 등록수("+gender+")");
							
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 중 하나가 선택 되었을 때
						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender31(province_idx,gender, sub_sort_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" 연간 "+sub_sort_type+" 등록수("+gender+")");
							
							map.put("type", 1);
						}else { // 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender32(province_idx, gender, sub_sort_idx, sub_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" 연간 "+sub_name+" 등록수("+gender+")");
							
							map.put("type", 1);
						}
					}
				}
				
			}else if(!year.equals("noYear") && month.equals("noMonth") ) { // 년도는 선택이 되었고 월은 선택이 되지 않았을 때
				if(gender.equals("genderAll")) { // 성별 전체
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender33(province_idx,year);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							map.put("title", provinceName+" "+year+"년 월별 구독 등록수");

							
							map.put("type", 1);
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender34(province_idx,year, sub_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" "+year+"년 "+sub_name+" 등록수");
							
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender35(province_idx,year, sub_sort_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" "+year+"년 "+sub_sort_type+" 등록수");
							
							map.put("type", 1);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender36(province_idx,year,sub_sort_idx,sub_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" "+year+"년 "+sub_name+" 등록수");
							
							map.put("type", 1);
						}
					}
				}else { // 성별 선택
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender37(province_idx,year, gender);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							map.put("title", provinceName+" "+year+"년 총 구독 등록수("+gender+")");
							
							map.put("type", 1);
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender38(province_idx,year,gender,sub_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" "+year+"년 "+sub_name+" 등록수("+gender+")");
							
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender39(province_idx,year, gender, sub_sort_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" "+year+"년 "+sub_sort_type+" 등록수("+gender+")");
							
							map.put("type", 1);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender40(province_idx,year,gender,sub_sort_idx,sub_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" "+year+"년 "+sub_name+" 등록수("+gender+")");
							
							map.put("type", 1);
						}
					}
				}
			}else { // 년도와 월 둘다 선택이 되었을 때
				if(gender.equals("genderAll")) { // 성별 전체
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender41(province_idx,year,month);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("province_name", list.get(0).get("province_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							map.put("title", provinceName+" "+year+"년 "+month+"월 구독 등록수");
							
							map.put("type", 1);
							
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender42(province_idx,year,month,sub_idx);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("province_name", list.get(0).get("province_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" "+year+"년 "+month+"월 "+sub_name+" 등록수");
							
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender43(province_idx,year,month,sub_sort_idx);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("province_name", list.get(0).get("province_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" "+year+"년 "+month+"월 "+sub_sort_type+" 등록수");
							
							map.put("type", 1);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender44(province_idx,year,month,sub_sort_idx,sub_idx);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("province_name", list.get(0).get("province_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" "+year+"년 "+month+"월 "+sub_name+" 등록수");
							
							map.put("type", 1);
						}
					}
				}else { // 성별 선택
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender45(province_idx,year,month,gender);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("province_name", list.get(0).get("province_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							map.put("title", provinceName+" "+year+"년 "+month+"월 구독 등록수("+gender+")");
							
							map.put("type", 1);
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender46(province_idx,year,month,gender,sub_idx);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("province_name", list.get(0).get("province_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" "+year+"년 "+month+"월 "+sub_name+" 등록수("+gender+")");
							
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender47(province_idx,year,month,gender,sub_sort_idx);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("province_name", list.get(0).get("province_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" "+year+"년 "+month+"월 "+sub_sort_type+" 등록수("+gender+")");
							
							map.put("type", 1);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender48(province_idx,year,month,gender,sub_sort_idx,sub_idx);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("province_name", list.get(0).get("province_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("province_name"));
							map.put("data", dataList);
							String provinceName = (String) list.get(0).get("province_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" "+year+"년 "+month+"월 "+sub_name+" 등록수("+gender+")");
							
							map.put("type", 1);

						}
					}
				}
			}

//			if(year.equals("noYear") && month.equals("noMonth")) { // 년도와 월이 둘다 선택되지 않았을 때
//				if(gender.equals("genderAll")) { // 성별 전체
//					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
//						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
//							// 시도/시군구/매장 선택 x, 년도, 월 선택 x, 성별 전체, 횟수권 요일권 통합, 구독권 선택 x 
//							
//
//						}else{ // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
//							// 시도/시군구/매장 선택 x, 년도, 월 선택 x, 성별 전체, 횟수권 요일권 통합, 구독권 선택 O
//							
//						}
//					}else { // 횟수권/요일권 중 하나가 선택 되었을 때
//						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
//							
//						}else { // 구독권 선택을 했을 때
//							
//						}
//					}
//				}else { // 성별이 남 또는 여 가 선택 되었을 때
//					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
//						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
//							
//						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
//							
//						}
//					}else { // 횟수권/요일권 중 하나가 선택 되었을 때
//						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
//							
//						}else { // 구독권 선택을 했을 때
//							
//						}
//					}
//				}
//				
//			}else if(!year.equals("noYear") && month.equals("noMonth") ) { // 년도는 선택이 되었고 월은 선택이 되지 않았을 때
//				if(gender.equals("genderAll")) { // 성별 전체
//					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
//						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
//							
//						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
//							
//						}
//					}else { // 횟수권/요일권 선택
//						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
//							
//						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
//							
//						}
//					}
//				}else { // 성별 선택
//					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
//						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
//							
//						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
//							
//						}
//					}else { // 횟수권/요일권 선택
//						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
//							
//						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
//							
//						}
//					}
//				}
//			}else { // 년도와 월 둘다 선택이 되었을 때
//				if(gender.equals("genderAll")) { // 성별 전체
//					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
//						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
//							
//						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
//							
//						}
//					}else { // 횟수권/요일권 선택
//						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
//							
//						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
//							
//						}
//					}
//				}else { // 성별 선택
//					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
//						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
//							
//						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
//							
//						}
//					}else { // 횟수권/요일권 선택
//						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
//							
//						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
//							
//						}
//					}
//				}
//			}
			

		}
		
		if(size == 7) {
			// size 가 7라는 것은 시도와 시군구는 선택하고 매장은 선택하지 않았을 때이다.
			String province_idx = params.get("province_idx");
			String city_idx = params.get("city_idx");
			String year = params.get("year");
			String month = params.get("month");
			String gender = params.get("gender");
			String sub_sort_idx = params.get("sub_sort_idx");
			String sub_idx = params.get("sub_idx");
			
			if(year.equals("noYear") && month.equals("noMonth")) { // 년도와 월이 둘다 선택되지 않았을 때
				if(gender.equals("genderAll")) { // 성별 전체
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
							// 시도/시군구/매장 선택 x, 년도, 월 선택 x, 성별 전체, 횟수권 요일권 통합, 구독권 선택 x 
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender49(city_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name"); 
							map.put("title", provinceName+" - "+cityName+" 연간 총 구독 등록수");
							map.put("type", 1);

						}else{ // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							// 시도/시군구/매장 선택 x, 년도, 월 선택 x, 성별 전체, 횟수권 요일권 통합, 구독권 선택 O
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender50(city_idx,sub_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" 연간 "+sub_name+" 등록수");
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 중 하나가 선택 되었을 때
						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender51(city_idx,sub_sort_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" - "+cityName+" 연간 "+sub_sort_type+" 등록수");
							map.put("type", 1);
						}else { // 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender52(city_idx,sub_sort_idx, sub_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" 연간 "+sub_name+" 등록수");
							map.put("type", 1);
						}
					}
				}else { // 성별이 남 또는 여 가 선택 되었을 때
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender53(city_idx,gender);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							map.put("title", provinceName+" - "+cityName+" 연간 총 구독 등록수("+gender+")");
							map.put("type", 1);
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender54(city_idx,gender, sub_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" 연간 "+sub_name+" 등록수("+gender+")");
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 중 하나가 선택 되었을 때
						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender55(city_idx,gender, sub_sort_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" - "+cityName+" 연간 "+sub_sort_type+" 등록수("+gender+")");
							map.put("type", 1);
						}else { // 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender56(city_idx, gender, sub_sort_idx, sub_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" 연간 "+sub_name+" 등록수("+gender+")");
							map.put("type", 1);
						}
					}
				}
				
			}else if(!year.equals("noYear") && month.equals("noMonth") ) { // 년도는 선택이 되었고 월은 선택이 되지 않았을 때
				if(gender.equals("genderAll")) { // 성별 전체
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender57(city_idx,year);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							map.put("title", provinceName+" - "+cityName+" "+year+"년 월별 구독 등록수");
							map.put("type", 1);
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender58(city_idx,year, sub_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" "+year+"년 "+sub_name+" 등록수");
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender59(city_idx,year, sub_sort_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" - "+cityName+" "+year+"년 "+sub_sort_type+" 등록수");
							map.put("type", 1);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender60(city_idx,year,sub_sort_idx,sub_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" "+year+"년 "+sub_name+" 등록수");
							map.put("type", 1);
						}
					}
				}else { // 성별 선택
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender61(city_idx,year, gender);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							map.put("title", provinceName+" - "+cityName+" "+year+"년 총 구독 등록수("+gender+")");
							map.put("type", 1);
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender62(city_idx,year,gender,sub_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" "+year+"년 "+sub_name+" 등록수("+gender+")");
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender63(city_idx,year, gender, sub_sort_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" - "+cityName+" "+year+"년 "+sub_sort_type+" 등록수("+gender+")");
							map.put("type", 1);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender64(city_idx,year,gender,sub_sort_idx,sub_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" "+year+"년 "+sub_name+" 등록수("+gender+")");
							map.put("type", 1);
						}
					}
				}
			}else { // 년도와 월 둘다 선택이 되었을 때
				if(gender.equals("genderAll")) { // 성별 전체
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender65(city_idx,year,month);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("city_name", list.get(0).get("city_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							map.put("title", provinceName+" - "+cityName+" "+year+"년 "+month+"월 구독 등록수");
							map.put("type", 1);
							
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender66(city_idx,year,month,sub_idx);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("city_name", list.get(0).get("city_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" "+year+"년 "+month+"월 "+sub_name+" 등록수");
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender67(city_idx,year,month,sub_sort_idx);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("city_name", list.get(0).get("city_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" - "+cityName+" "+year+"년 "+month+"월 "+sub_sort_type+" 등록수");
							map.put("type", 1);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender68(city_idx,year,month,sub_sort_idx,sub_idx);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("city_name", list.get(0).get("city_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" "+year+"년 "+month+"월 "+sub_name+" 등록수");
							map.put("type", 1);
						}
					}
				}else { // 성별 선택
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender69(city_idx,year,month,gender);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("city_name", list.get(0).get("city_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							map.put("title", provinceName+" - "+cityName+" "+year+"년 "+month+"월 구독 등록수("+gender+")");
							map.put("type", 1);
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender70(city_idx,year,month,gender,sub_idx);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("city_name", list.get(0).get("city_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" "+year+"년 "+month+"월 "+sub_name+" 등록수("+gender+")");
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender71(city_idx,year,month,gender,sub_sort_idx);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("city_name", list.get(0).get("city_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" - "+cityName+" "+year+"년 "+month+"월 "+sub_sort_type+" 등록수("+gender+")");
							map.put("type", 1);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender72(city_idx,year,month,gender,sub_sort_idx,sub_idx);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("city_name", list.get(0).get("city_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("city_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = (String) list.get(0).get("city_name");
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" "+year+"년 "+month+"월 "+sub_name+" 등록수("+gender+")");
							map.put("type", 1);

						}
					}
				}
			}

		}
		
		if(size == 8){
			// size 가 8라는 것은 시도/시군구/매장 모두 선택했다는 것이다.
			String province_idx = params.get("province_idx");
			String city_idx = params.get("city_idx");
			String shop_idx = params.get("shop_idx");
			String year = params.get("year");
			String month = params.get("month");
			String gender = params.get("gender");
			String sub_sort_idx = params.get("sub_sort_idx");
			String sub_idx = params.get("sub_idx");
			
			if(year.equals("noYear") && month.equals("noMonth")) { // 년도와 월이 둘다 선택되지 않았을 때
				if(gender.equals("genderAll")) { // 성별 전체
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
							// 시도/시군구/매장 선택 x, 년도, 월 선택 x, 성별 전체, 횟수권 요일권 통합, 구독권 선택 x 
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender73(shop_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name");  
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+" 연간 총 구독 등록수");
							map.put("type", 1);

						}else{ // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							// 시도/시군구/매장 선택 x, 년도, 월 선택 x, 성별 전체, 횟수권 요일권 통합, 구독권 선택 O
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender74(shop_idx,sub_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+" 연간 "+sub_name+" 등록수");
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 중 하나가 선택 되었을 때
						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender75(shop_idx,sub_sort_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+" 연간 "+sub_sort_type+" 등록수");
							map.put("type", 1);
						}else { // 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender76(shop_idx,sub_sort_idx, sub_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+" 연간 "+sub_name+" 등록수");
							map.put("type", 1);
						}
					}
				}else { // 성별이 남 또는 여 가 선택 되었을 때
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender77(shop_idx,gender);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+" 연간 총 구독 등록수("+gender+")");
							map.put("type", 1);
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender78(shop_idx,gender, sub_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+" 연간 "+sub_name+" 등록수("+gender+")");
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 중 하나가 선택 되었을 때
						if(sub_idx.equals("noSub")) { // 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender79(shop_idx,gender, sub_sort_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+" 연간 "+sub_sort_type+" 등록수("+gender+")");
							map.put("type", 1);
						}else { // 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender80(shop_idx, gender, sub_sort_idx, sub_idx);
							ArrayList<String> yearList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();

							for(int i=0; i<list.size(); i++) {
								yearList.add((String) list.get(i).get("year"));
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
					        map.put("labels", yearList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+" 연간 "+sub_name+" 등록수("+gender+")");
							map.put("type", 1);
						}
					}
				}
				
			}else if(!year.equals("noYear") && month.equals("noMonth") ) { // 년도는 선택이 되었고 월은 선택이 되지 않았을 때
				if(gender.equals("genderAll")) { // 성별 전체
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender81(shop_idx,year);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+"  "+year+"년 월별 구독 등록수");
							map.put("type", 1);
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender82(shop_idx,year, sub_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+"  "+year+"년 "+sub_name+" 등록수");
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender83(shop_idx,year, sub_sort_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+"  "+year+"년 "+sub_sort_type+" 등록수");
							map.put("type", 1);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender84(shop_idx,year,sub_sort_idx,sub_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+"  "+year+"년 "+sub_name+" 등록수");
							map.put("type", 1);
						}
					}
				}else { // 성별 선택
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender85(shop_idx,year, gender);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+"  "+year+"년 총 구독 등록수("+gender+")");
							map.put("type", 1);
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender86(shop_idx,year,gender,sub_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+"  "+year+"년 "+sub_name+" 등록수("+gender+")");
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender87(shop_idx,year, gender, sub_sort_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+"  "+year+"년 "+sub_sort_type+" 등록수("+gender+")");
							map.put("type", 1);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender88(shop_idx,year,gender,sub_sort_idx,sub_idx);
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i=0; i<list.size(); i++) {
								monthList.add(list.get(i).get("month").toString());
							}
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}

					        map.put("labels", monthList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+"  "+year+"년 "+sub_name+" 등록수("+gender+")");
							map.put("type", 1);
						}
					}
				}
			}else { // 년도와 월 둘다 선택이 되었을 때
				if(gender.equals("genderAll")) { // 성별 전체
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender89(shop_idx,year,month);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("shop_name", list.get(0).get("shop_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+"  "+year+"년 "+month+"월 구독 등록수");
							map.put("type", 1);
							
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender90(shop_idx,year,month,sub_idx);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("shop_name", list.get(0).get("shop_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+"  "+year+"년 "+month+"월"+sub_name+" 등록수");
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender91(shop_idx,year,month,sub_sort_idx);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("shop_name", list.get(0).get("shop_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+"  "+year+"년 "+month+"월 "+sub_sort_type+" 등록수");
							map.put("type", 1);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender92(shop_idx,year,month,sub_sort_idx,sub_idx);
							//logger.info("list = "+list);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("shop_name", list.get(0).get("shop_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+"  "+year+"년 "+month+"월 "+sub_name+" 등록수");
							map.put("type", 1);
						}
					}
				}else { // 성별 선택
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender93(shop_idx,year,month,gender);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("shop_name", list.get(0).get("shop_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+"  "+year+"년 "+month+"월 구독 등록수("+gender+")");
							map.put("type", 1);
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender94(shop_idx,year,month,gender,sub_idx);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("shop_name", list.get(0).get("shop_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+"  "+year+"년 "+month+"월 "+sub_name+" 등록수("+gender+")");
							map.put("type", 1);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender95(shop_idx,year,month,gender,sub_sort_idx);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("shop_name", list.get(0).get("shop_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_sort_type = statservice.subSortType(sub_sort_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+"  "+year+"년 "+month+"월 "+sub_sort_type+" 등록수("+gender+")");
							map.put("type", 1);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender96(shop_idx,year,month,gender,sub_sort_idx,sub_idx);
							ArrayList<String> dayList = statservice.subStatGenderDays(year,month);
							ArrayList<Date> compareDay = new ArrayList<Date>();
							ArrayList<Date> days = new ArrayList<Date>();
							ArrayList<Object> dataList = new ArrayList<Object>();
							
							for(int i9=0; i9<dayList.size(); i9++) {
								days.add(Date.valueOf(dayList.get(i9)));
							}

							for(int i8=0; i8<list.size(); i8++) {
								Date day = (Date) list.get(i8).get("day");
								compareDay.add(day);
							}

							for(int i2=0; i2<days.size(); i2++) {
								HashMap<String,Object> addMap1 = new HashMap<String,Object>();
								if(compareDay.contains(days.get(i2))) {
								}else {
									addMap1.put("day", days.get(i2));
									addMap1.put("subJoinCount", 0);
									addMap1.put("shop_name", list.get(0).get("shop_name"));
									list.add(addMap1);
								}
							}
							
							// 날짜 정렬
							list = (ArrayList<HashMap<String, Object>>) list.stream().sorted((o1, o2) -> o1.get("day").toString().compareTo(o2.get("day").toString()) ).collect(Collectors.toList());
							
							for(int a=0; a<list.size(); a++) {
								dataList.add(list.get(a).get("subJoinCount"));
							}
							
							logger.info("data = "+dataList);
						
							map.put("labels", dayList);
							map.put("label", list.get(0).get("shop_name"));
							map.put("data", dataList);
							String provinceName = statservice.getProName(province_idx);
							String cityName = statservice.getCityName(city_idx);
							String shop_name = (String) list.get(0).get("shop_name"); 
							String sub_name = statservice.subName(sub_idx);
							map.put("title", provinceName+" - "+cityName+" - "+shop_name+"  "+year+"년 "+month+"월 "+sub_name+" 등록수("+gender+")");
							map.put("type", 1);

						}
					}
				}
			}

		}
		
		return map;
	}
	
	
	@GetMapping(value="/sub/subStatAge.do")
	public HashMap<String, Object> subStatAge(@RequestParam HashMap<String, String> params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		logger.info("데이터 = "+params);
		int size = params.size();
		logger.info("데이터 사이즈 = "+size);
		
		ArrayList<HashMap<String, Object>> seoul = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> busan = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> daegu = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> incheon = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> gwangju = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> daejeon = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> ulsan = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> saejong = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> gyeonggi = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> gangwon = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> choongbook = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> choongnam = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> jeonbook = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> jeonnam = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> gyeongbook = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> gyeongnam = new ArrayList<HashMap<String,Object>>();
		ArrayList<HashMap<String, Object>> jeju = new ArrayList<HashMap<String,Object>>();
		
		ArrayList<Object> data1 = new ArrayList<Object>();
		ArrayList<Object> data2 = new ArrayList<Object>();
		ArrayList<Object> data = new ArrayList<Object>();
		
		String[] labels = {"10대","20대","30대","40대","50대","60대"};
		
		ArrayList<String> provinceName = new ArrayList<String>();
		
		if(size == 4) {
			// size 가 4라는 것은 시도/시군구/매장 모두 선택하지 않았을 때이다.
			// 시도/시군구/매장 아무것도 선택하지 않았을 때는 시도 끼리 비교하는 그래프를 그린다.
			// 년도는 기본으로 지정이 되어서 오고 월은 지정하지 않았을 때와 지정했을 때로 나뉜다. 서울특별시, 경기도 막대 각 1개 총 2개 막대 
			// 막대는 시도를 기준으로 갯수를 측정
			String year = params.get("year");
			String month = params.get("month");
			String sub_sort_idx = params.get("sub_sort_idx");
			String sub_idx = params.get("sub_idx");
			
			if(month.equals("noMonth")) { // 년도만 선택 되었을 때
				if (sub_sort_idx.equals("subAll")) {
					if (sub_idx.equals("noSub")) { // 년도 지정 O / 월 지정 X / 구독권 종류 지정 X / 구독권 지정 X
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge1(year);
						
						for(int i=0; i<list.size(); i++) {
							provinceName.add((String) list.get(i).get("province_name"));
							
							if(list.get(i).get("province_name").equals("서울특별시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
									
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("부산광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("대구광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("인천광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("광주광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("대전광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("울산광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("세종특별자치시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경기도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("강원도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("충청북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("전라북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("전라남도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경상북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경상남도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}
						}
						
						map.put("labels", labels);
						map.put("label", provinceName);
						map.put("data1", data1);
						map.put("data2", data2);
						map.put("title", year+"년 서울/경기 연령별 구독 등록수");
						map.put("type", 2);
						
						
					} else { // 년도 지정 O / 월 지정 X / 구독권 종류 지정 X / 구독권 지정 O
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge2(year, sub_idx);
						
						for(int i=0; i<list.size(); i++) {
							provinceName.add((String) list.get(i).get("province_name"));
							
							if(list.get(i).get("province_name").equals("서울특별시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
									
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("부산광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("대구광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("인천광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("광주광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("대전광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("울산광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("세종특별자치시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경기도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("강원도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("충청북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("전라북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("전라남도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경상북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경상남도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}
						}
						
						map.put("labels", labels);
						map.put("label", provinceName);
						map.put("data1", data1);
						map.put("data2", data2);
						String sub_name = statservice.subName(sub_idx);
						map.put("title", year+"년 서울/경기 "+sub_name+" 등록수");
						map.put("type", 2);
					}
				} else { // 년도 지정 O / 월 지정 X / 구독권 종류 지정 O / 구독권 지정 X
					if (sub_idx.equals("noSub")) {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge3(year, sub_sort_idx);
						
						for(int i=0; i<list.size(); i++) {
							provinceName.add((String) list.get(i).get("province_name"));
							
							if(list.get(i).get("province_name").equals("서울특별시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
									
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("부산광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("대구광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("인천광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("광주광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("대전광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("울산광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("세종특별자치시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경기도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("강원도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("충청북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("전라북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("전라남도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경상북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경상남도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}
						}
						
						map.put("labels", labels);
						map.put("label", provinceName);
						map.put("data1", data1);
						map.put("data2", data2);
						String sub_sort_type = statservice.subSortType(sub_sort_idx);
						map.put("title", year+"년 서울/경기 "+sub_sort_type+" 등록수");
						map.put("type", 2);
					} else { // 년도 지정 O / 월 지정 X / 구독권 종류 지정 O / 구독권 지정 O
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge4(year, sub_sort_idx, sub_idx);
						
						for(int i=0; i<list.size(); i++) {
							provinceName.add((String) list.get(i).get("province_name"));
							
							if(list.get(i).get("province_name").equals("서울특별시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
									
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("부산광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("대구광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("인천광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("광주광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("대전광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("울산광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("세종특별자치시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경기도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("강원도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("충청북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("전라북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("전라남도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경상북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경상남도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}
						}
						
						map.put("labels", labels);
						map.put("label", provinceName);
						map.put("data1", data1);
						map.put("data2", data2);
						String sub_name = statservice.subName(sub_idx);
						map.put("title", year+"년 서울/경기 "+sub_name+" 등록수");
						map.put("type", 2);
					}
				}

			}else { // 년도와 월 둘다 선택이 되었을 때

				if (sub_sort_idx.equals("subAll")) {
					if (sub_idx.equals("noSub")) { // 년도 지정 O / 월 지정 O / 구독권 종류 지정 X / 구독권 지정 X
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge5(year, month);
						
						for(int i=0; i<list.size(); i++) {
							provinceName.add((String) list.get(i).get("province_name"));
							
							if(list.get(i).get("province_name").equals("서울특별시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
									
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("부산광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("대구광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("인천광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("광주광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("대전광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("울산광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("세종특별자치시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경기도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("강원도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("충청북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("전라북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("전라남도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경상북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경상남도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}
						}
						
						map.put("labels", labels);
						map.put("label", provinceName);
						map.put("data1", data1);
						map.put("data2", data2);
						map.put("title", year+"년 "+month+"월 서울/경기 연령별 구독 등록수");
						map.put("type", 2);
					} else { // 년도 지정 O / 월 지정 O / 구독권 종류 지정 X / 구독권 지정 O
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge6(year,month,sub_idx);
						
						for(int i=0; i<list.size(); i++) {
							provinceName.add((String) list.get(i).get("province_name"));
							
							if(list.get(i).get("province_name").equals("서울특별시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
									
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("부산광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("대구광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("인천광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("광주광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("대전광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("울산광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("세종특별자치시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경기도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("강원도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("충청북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("전라북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("전라남도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경상북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경상남도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}
						}
						
						map.put("labels", labels);
						map.put("label", provinceName);
						map.put("data1", data1);
						map.put("data2", data2);
						String sub_name = statservice.subName(sub_idx);
						map.put("title", year+"년 "+month+"월 서울/경기 "+sub_name+" 등록수");
						map.put("type", 2);
					}
				} else {
					if (sub_idx.equals("noSub")) { // 년도 지정 O / 월 지정 O / 구독권 종류 지정 O / 구독권 지정 X
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge7(year, month, sub_sort_idx);
						
						for(int i=0; i<list.size(); i++) {
							provinceName.add((String) list.get(i).get("province_name"));
							
							if(list.get(i).get("province_name").equals("서울특별시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
									
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("부산광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("대구광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("인천광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("광주광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("대전광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("울산광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("세종특별자치시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경기도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("강원도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("충청북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("전라북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("전라남도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경상북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경상남도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}
						}
						
						map.put("labels", labels);
						map.put("label", provinceName);
						map.put("data1", data1);
						map.put("data2", data2);
						String sub_sort_type = statservice.subSortType(sub_sort_idx);
						map.put("title", year+"년 "+month+"월 서울/경기 "+sub_sort_type+" 등록수");
						map.put("type", 2);
					} else { // 년도 지정 O / 월 지정 O / 구독권 종류 지정 O / 구독권 지정 O
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge8(year, month, sub_sort_idx, sub_idx);
						
						for(int i=0; i<list.size(); i++) {
							provinceName.add((String) list.get(i).get("province_name"));
							
							if(list.get(i).get("province_name").equals("서울특별시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
									
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("부산광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("대구광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("인천광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("광주광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("대전광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("울산광역시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("세종특별자치시")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경기도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("강원도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("충청북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("전라북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("전라남도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경상북도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else if(list.get(i).get("province_name").equals("경상남도")) {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}else {
								if(data1.size()>0) {
									data2.add(list.get(i).get("10s"));
									data2.add(list.get(i).get("20s"));
									data2.add(list.get(i).get("30s"));
									data2.add(list.get(i).get("40s"));
									data2.add(list.get(i).get("50s"));
									data2.add(list.get(i).get("60s"));
								}else {
									data1.add(list.get(i).get("10s"));
									data1.add(list.get(i).get("20s"));
									data1.add(list.get(i).get("30s"));
									data1.add(list.get(i).get("40s"));
									data1.add(list.get(i).get("50s"));
									data1.add(list.get(i).get("60s"));
								}
							}
						}
						
						map.put("labels", labels);
						map.put("label", provinceName);
						map.put("data1", data1);
						map.put("data2", data2);
						String sub_name = statservice.subName(sub_idx);
						map.put("title", year+"년 "+month+"월 서울/경기 "+sub_name+" 등록수");
						map.put("type", 2);
					}
				}
			}
		}
		
		if(size == 5) {
			String province_idx = params.get("province_idx");
			String year = params.get("year");
			String month = params.get("month");
			String sub_sort_idx = params.get("sub_sort_idx");
			String sub_idx = params.get("sub_idx");
			
			if(month.equals("noMonth")) { // 년도만 선택 되었을 때

				if (sub_sort_idx.equals("subAll")) {
					if (sub_idx.equals("noSub")) {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge9(year,province_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("province_name"));
						map.put("data", data);
						String province_name = (String) list.get(0).get("province_name");
						map.put("title", year+"년 "+province_name+" 연령별 구독 등록수");
						map.put("type", 1);
					} else {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge10(year,province_idx,sub_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("province_name"));
						map.put("data", data);
						String province_name = (String) list.get(0).get("province_name");
						String sub_name = statservice.subName(sub_idx);
						map.put("title", year+"년 "+province_name+" "+sub_name+" 등록수");
						map.put("type", 1);
					}
				} else {
					if (sub_idx.equals("noSub")) {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge11(year,province_idx,sub_sort_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("province_name"));
						map.put("data", data);
						String province_name = (String) list.get(0).get("province_name");
						String sub_sort_type = statservice.subSortType(sub_sort_idx);
						map.put("title", year+"년 "+province_name+" "+sub_sort_type+" 등록수");
						map.put("type", 1);
					} else {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge12(year,province_idx,sub_sort_idx,sub_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("province_name"));
						map.put("data", data);
						String province_name = (String) list.get(0).get("province_name");
						String sub_name = statservice.subName(sub_idx);
						map.put("title", year+"년 "+province_name+" "+sub_name+" 등록수");
						map.put("type", 1);
					}
				}

			} else { // 년도와 월 둘다 선택이 되었을 때

				if (sub_sort_idx.equals("subAll")) {
					if (sub_idx.equals("noSub")) {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge13(year,month,province_idx);
						
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("province_name"));
						map.put("data", data);
						String province_name = (String) list.get(0).get("province_name");
						map.put("title", year+"년 "+month+"월 "+province_name+" 연령별 구독 등록수");
						map.put("type", 1);
					} else {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge14(year,month,province_idx,sub_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("province_name"));
						map.put("data", data);
						String province_name = (String) list.get(0).get("province_name");
						String sub_name = statservice.subName(sub_idx);
						map.put("title", year+"년 "+month+"월 "+province_name+" "+sub_name+" 등록수");
						map.put("type", 1);
					}
				} else {
					if (sub_idx.equals("noSub")) {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge15(year,month,province_idx,sub_sort_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("province_name"));
						map.put("data", data);
						String province_name = (String) list.get(0).get("province_name");
						String sub_sort_type = statservice.subSortType(sub_sort_idx);
						map.put("title", year+"년 "+month+"월 "+province_name+" "+sub_sort_type+" 등록수");
						map.put("type", 1);
					} else {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge16(year,month,province_idx,sub_sort_idx,sub_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("province_name"));
						map.put("data", data);
						String province_name = (String) list.get(0).get("province_name");
						String sub_name = statservice.subName(sub_idx);
						map.put("title", year+"년 "+month+"월 "+province_name+" "+sub_name+" 등록수");
						map.put("type", 1);
					}
				}
			}
			
		}
		
		if(size == 6) {
			String province_idx = params.get("province_idx");
			String city_idx = params.get("city_idx");
			String year = params.get("year");
			String month = params.get("month");
			String sub_sort_idx = params.get("sub_sort_idx");
			String sub_idx = params.get("sub_idx");
			
			if(month.equals("noMonth")) { // 년도만 선택 되었을 때

				if (sub_sort_idx.equals("subAll")) {
					if (sub_idx.equals("noSub")) {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge17(year,city_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("city_name"));
						map.put("data", data);
						String province_name = statservice.getProName(province_idx);
						String city_name = (String) list.get(0).get("city_name");
						map.put("title", year+"년 "+province_name+" - "+city_name+" 연령별 구독 등록수");
						map.put("type", 1);
					} else {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge18(year,city_idx,sub_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("city_name"));
						map.put("data", data);
						String province_name = statservice.getProName(province_idx);
						String city_name = (String) list.get(0).get("city_name");
						String sub_name = statservice.subName(sub_idx);
						map.put("title", year+"년 "+province_name+" - "+city_name+" "+sub_name+" 등록수");
						map.put("type", 1);
					}
				} else {
					if (sub_idx.equals("noSub")) {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge19(year,city_idx,sub_sort_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("city_name"));
						map.put("data", data);
						String province_name = statservice.getProName(province_idx);
						String city_name = (String) list.get(0).get("city_name");
						String sub_sort_type = statservice.subSortType(sub_sort_idx);
						map.put("title", year+"년 "+province_name+" - "+city_name+" "+sub_sort_type+" 등록수");
						map.put("type", 1);
					} else {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge20(year,city_idx,sub_sort_idx,sub_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("city_name"));
						map.put("data", data);
						String province_name = statservice.getProName(province_idx);
						String city_name = (String) list.get(0).get("city_name");
						String sub_name = statservice.subName(sub_idx);
						map.put("title", year+"년 "+province_name+" - "+city_name+" "+sub_name+" 등록수");
						map.put("type", 1);
					}
				}

			} else { // 년도와 월 둘다 선택이 되었을 때

				if (sub_sort_idx.equals("subAll")) {
					if (sub_idx.equals("noSub")) {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge21(year,month,city_idx);
						
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("city_name"));
						map.put("data", data);
						String province_name = statservice.getProName(province_idx);
						String city_name = (String) list.get(0).get("city_name");
						map.put("title", year+"년 "+month+"월 "+province_name+" - "+city_name+" 연령별 구독 등록수");
						map.put("type", 1);
					} else {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge22(year,month,city_idx,sub_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("city_name"));
						map.put("data", data);
						String province_name = statservice.getProName(province_idx);
						String city_name = (String) list.get(0).get("city_name");
						String sub_name = statservice.subName(sub_idx);
						map.put("title", year+"년 "+month+"월 "+province_name+" - "+city_name+" "+sub_name+" 등록수");
						map.put("type", 1);
					}
				} else {
					if (sub_idx.equals("noSub")) {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge23(year,month,city_idx,sub_sort_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("city_name"));
						map.put("data", data);
						String province_name = statservice.getProName(province_idx);
						String city_name = (String) list.get(0).get("city_name");
						String sub_sort_type = statservice.subSortType(sub_sort_idx);
						map.put("title", year+"년 "+month+"월 "+province_name+" - "+city_name+" "+sub_sort_type+" 등록수");
						map.put("type", 1);
					} else {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge24(year,month,city_idx,sub_sort_idx,sub_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("city_name"));
						map.put("data", data);
						String province_name = statservice.getProName(province_idx);
						String city_name = (String) list.get(0).get("city_name");
						String sub_name = statservice.subName(sub_idx);
						map.put("title", year+"년 "+month+"월 "+province_name+" - "+city_name+" "+sub_name+" 등록수");
						map.put("type", 1);
					}
				}
			}
			
		}
		
		if(size == 7) {
			String province_idx = params.get("province_idx");
			String city_idx = params.get("city_idx");
			String shop_idx = params.get("shop_idx");
			String year = params.get("year");
			String month = params.get("month");
			String sub_sort_idx = params.get("sub_sort_idx");
			String sub_idx = params.get("sub_idx");
			
			if(month.equals("noMonth")) { // 년도만 선택 되었을 때

				if (sub_sort_idx.equals("subAll")) {
					if (sub_idx.equals("noSub")) {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge25(year,shop_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("shop_name"));
						map.put("data", data);
						String province_name = statservice.getProName(province_idx);
						String city_name = statservice.getCityName(city_idx);
						String shop_name = (String) list.get(0).get("shop_name");
						map.put("title", year+"년 "+province_name+" - "+city_name+" - "+shop_name+" 연령별 구독 등록수");
						map.put("type", 1);
					} else {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge26(year,shop_idx,sub_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("shop_name"));
						map.put("data", data);
						String province_name = statservice.getProName(province_idx);
						String city_name = statservice.getCityName(city_idx);
						String shop_name = (String) list.get(0).get("shop_name");
						String sub_name = statservice.subName(sub_idx);
						map.put("title", year+"년 "+province_name+" - "+city_name+" - "+shop_name+"  "+sub_name+" 등록수");
						map.put("type", 1);
					}
				} else {
					if (sub_idx.equals("noSub")) {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge27(year,shop_idx,sub_sort_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("shop_name"));
						map.put("data", data);
						String province_name = statservice.getProName(province_idx);
						String city_name = statservice.getCityName(city_idx);
						String shop_name = (String) list.get(0).get("shop_name");
						String sub_sort_type = statservice.subSortType(sub_sort_idx);
						map.put("title", year+"년 "+province_name+" - "+city_name+" - "+shop_name+" "+sub_sort_type+" 등록수");
						map.put("type", 1);
					} else {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge28(year,shop_idx,sub_sort_idx,sub_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("shop_name"));
						map.put("data", data);
						String province_name = statservice.getProName(province_idx);
						String city_name = statservice.getCityName(city_idx);
						String shop_name = (String) list.get(0).get("shop_name");
						String sub_name = statservice.subName(sub_idx);
						map.put("title", year+"년 "+province_name+" - "+city_name+" - "+shop_name+" "+sub_name+" 등록수");
						map.put("type", 1);
					}
				}

			} else { // 년도와 월 둘다 선택이 되었을 때

				if (sub_sort_idx.equals("subAll")) {
					if (sub_idx.equals("noSub")) {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge29(year,month,shop_idx);
						
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("shop_name"));
						map.put("data", data);
						String province_name = statservice.getProName(province_idx);
						String city_name = statservice.getCityName(city_idx);
						String shop_name = (String) list.get(0).get("shop_name");
						map.put("title", year+"년 "+month+"월 "+province_name+" - "+city_name+" - "+shop_name+" 연령별 구독 등록수");
						map.put("type", 1);
					} else {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge30(year,month,shop_idx,sub_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("shop_name"));
						map.put("data", data);
						String province_name = statservice.getProName(province_idx);
						String city_name = statservice.getCityName(city_idx);
						String shop_name = (String) list.get(0).get("shop_name");
						String sub_name = statservice.subName(sub_idx);
						map.put("title", year+"년 "+month+"월 "+province_name+" - "+city_name+" - "+shop_name+" "+sub_name+" 등록수");
						map.put("type", 1);
					}
				} else {
					if (sub_idx.equals("noSub")) {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge31(year,month,shop_idx,sub_sort_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("shop_name"));
						map.put("data", data);
						String province_name = statservice.getProName(province_idx);
						String city_name = statservice.getCityName(city_idx);
						String shop_name = (String) list.get(0).get("shop_name");
						String sub_sort_type = statservice.subSortType(sub_sort_idx);
						map.put("title", year+"년 "+month+"월 "+province_name+" - "+city_name+" - "+shop_name+" "+sub_sort_type+" 등록수");
						map.put("type", 1);
					} else {
						ArrayList<HashMap<String, Object>> list = statservice.subStatAge32(year,month,shop_idx,sub_sort_idx,sub_idx);
						data.add(list.get(0).get("10s"));
						data.add(list.get(0).get("20s"));
						data.add(list.get(0).get("30s"));
						data.add(list.get(0).get("40s"));
						data.add(list.get(0).get("50s"));
						data.add(list.get(0).get("60s"));
						
						map.put("labels", labels);
						map.put("label", list.get(0).get("shop_name"));
						map.put("data", data);
						String province_name = statservice.getProName(province_idx);
						String city_name = statservice.getCityName(city_idx);
						String shop_name = (String) list.get(0).get("shop_name");
						String sub_name = statservice.subName(sub_idx);
						map.put("title", year+"년 "+month+"월 "+province_name+" - "+city_name+" - "+shop_name+" "+sub_name+" 등록수");
						map.put("type", 1);
					}
				}
			}
			
		}

		return map;
	}
	
	
	@GetMapping(value="/sub/subStatPreference.do")
	public HashMap<String, Object> subStatPreference(@RequestParam HashMap<String, String> params) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		logger.info("데이터 = "+params);
		int size = params.size();
		logger.info("데이터 사이즈 = "+size);
		
		ArrayList<Object> data = new ArrayList<Object>();
		ArrayList<String> labels = new ArrayList<String>();
		
		if(size == 2) {
			String year = params.get("year");
			String month = params.get("month");
			
			if(month.equals("noMonth")) {
				ArrayList<HashMap<String, Object>> list = statservice.subStatPreference1(year);
				
				for(int i=0; i<list.size(); i++) {
					data.add(list.get(i).get("subJoinCount"));
					labels.add((String) list.get(i).get("sub_name"));
				}
				
				map.put("labels", labels);
				map.put("data", data);
				map.put("title", year+"년 서울/경기 구독권 선호도");
				
			}else {
				ArrayList<HashMap<String, Object>> list = statservice.subStatPreference2(year,month);
				
				for(int i=0; i<list.size(); i++) {
					data.add(list.get(i).get("subJoinCount"));
					labels.add((String) list.get(i).get("sub_name"));
				}
				
				map.put("labels", labels);
				map.put("data", data);
				map.put("title", year+"년 "+month+"월 서울/경기 구독권 선호도");
				
			}
			
			
			
			
		}
		if(size == 3) {
			String province_idx = params.get("province_idx");
			String year = params.get("year");
			String month = params.get("month");
			
			if(month.equals("noMonth")) {
				ArrayList<HashMap<String, Object>> list = statservice.subStatPreference3(province_idx,year);
				
				for(int i=0; i<list.size(); i++) {
					data.add(list.get(i).get("subJoinCount"));
					labels.add((String) list.get(i).get("sub_name"));
				}
				
				map.put("labels", labels);
				map.put("data", data);
				String province_name = statservice.getProName(province_idx);
				map.put("title", year+"년 "+province_name+" 구독권 선호도");
				
			}else {
				ArrayList<HashMap<String, Object>> list = statservice.subStatPreference4(province_idx,year,month);
				
				for(int i=0; i<list.size(); i++) {
					data.add(list.get(i).get("subJoinCount"));
					labels.add((String) list.get(i).get("sub_name"));
				}
				
				map.put("labels", labels);
				map.put("data", data);
				String province_name = statservice.getProName(province_idx);
				map.put("title", year+"년 "+month+"월 "+province_name+" 구독권 선호도");
				
			}
			
		}
		if(size == 4) {
			String province_idx = params.get("province_idx");
			String city_idx = params.get("city_idx");
			String year = params.get("year");
			String month = params.get("month");
			
			if(month.equals("noMonth")) {
				ArrayList<HashMap<String, Object>> list = statservice.subStatPreference5(city_idx,year);
				
				for(int i=0; i<list.size(); i++) {
					data.add(list.get(i).get("subJoinCount"));
					labels.add((String) list.get(i).get("sub_name"));
				}
				
				map.put("labels", labels);
				map.put("data", data);
				String province_name = statservice.getProName(province_idx);
				String city_name = statservice.getCityName(city_idx);
				map.put("title", year+"년 "+province_name+" - "+city_name+" 구독권 선호도");
				
			}else {
				ArrayList<HashMap<String, Object>> list = statservice.subStatPreference6(city_idx,year,month);
				
				for(int i=0; i<list.size(); i++) {
					data.add(list.get(i).get("subJoinCount"));
					labels.add((String) list.get(i).get("sub_name"));
				}
				
				map.put("labels", labels);
				map.put("data", data);
				String province_name = statservice.getProName(province_idx);
				String city_name = statservice.getCityName(city_idx);
				map.put("title", year+"년 "+month+"월 "+province_name+" - "+city_name+" 구독권 선호도");
				
			}
			
		}
		if(size == 5) {
			String province_idx = params.get("province_idx");
			String city_idx = params.get("city_idx");
			String shop_idx = params.get("shop_idx");
			String year = params.get("year");
			String month = params.get("month");
			
			if(month.equals("noMonth")) {
				ArrayList<HashMap<String, Object>> list = statservice.subStatPreference7(shop_idx,year);
				
				for(int i=0; i<list.size(); i++) {
					data.add(list.get(i).get("subJoinCount"));
					labels.add((String) list.get(i).get("sub_name"));
				}
				
				map.put("labels", labels);
				map.put("data", data);
				String province_name = statservice.getProName(province_idx);
				String city_name = statservice.getCityName(city_idx);
				String shop_name = statservice.getShopName(shop_idx);
				map.put("title", year+"년 "+province_name+" - "+city_name+" - "+shop_name+" 구독권 선호도");
				
			}else {
				ArrayList<HashMap<String, Object>> list = statservice.subStatPreference8(shop_idx,year,month);
				
				for(int i=0; i<list.size(); i++) {
					data.add(list.get(i).get("subJoinCount"));
					labels.add((String) list.get(i).get("sub_name"));
				}
				
				map.put("labels", labels);
				map.put("data", data);
				String province_name = statservice.getProName(province_idx);
				String city_name = statservice.getCityName(city_idx);
				String shop_name = statservice.getShopName(shop_idx);
				map.put("title", year+"년 "+month+"월 "+province_name+" - "+city_name+" - "+shop_name+" 구독권 선호도");
				
			}
			
		}
		
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
}
