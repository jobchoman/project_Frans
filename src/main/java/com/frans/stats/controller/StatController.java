package com.frans.stats.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

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
							map.put("type", 2);
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender15(year, gender, sub_sort_idx);
							
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
							map.put("type", 2);
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							ArrayList<String> provinceName = new ArrayList<String>();
							ArrayList<String> monthList = new ArrayList<String>();
							ArrayList<String> data1 = new ArrayList<String>();
							ArrayList<String> data2 = new ArrayList<String>();
							
							ArrayList<HashMap<String, Object>> list = statservice.subStatGender16(year,gender,sub_sort_idx,sub_idx);
							
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
							map.put("type", 2);
						}
					}
				}
			}else { // 년도와 월 둘다 선택이 되었을 때
				if(gender.equals("genderAll")) { // 성별 전체
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							
						}
					}
				}else { // 성별 선택
					if(sub_sort_idx.equals("subAll")) { // 횟수권/요일권 통합
						if(sub_idx.equals("noSub")) {// 횟수권/요일권 통합 상태에서 구독권 선택을 하지 않았을 때
							
						}else { // 횟수권/요일권 통합 상태에서 구독권 선택을 했을 때
							
						}
					}else { // 횟수권/요일권 선택
						if(sub_idx.equals("noSub")) { // 횟수권/요일권 선택한 상태에서 구독권 선택을 하지 않았을 때
							
						}else { // 횟수권/요일권 선택한 상태에서 구독권 선택을 했을 때
							
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

		}
		
		if(size == 8){
			// size 가 8라는 것은 시도/시군구/매장 모두 선택했다는 것이다.
			
			
			
			
//			String province_idx = params.get("province_idx");
//			String city_idx = params.get("city_idx");
//			String shop_idx = params.get("shop_idx");
//			String year = params.get("year");
//			String month = params.get("month");
//			String gender = params.get("gender");
//			String sub_sort_idx = params.get("sub_sort_idx");
//			String sub_idx = params.get("sub_idx");
//			logger.info(province_idx);
//			logger.info(city_idx);
//			logger.info(shop_idx);
//			logger.info(year);
//			logger.info(month);
//			logger.info(gender);
//			logger.info(sub_sort_idx);
//			logger.info(sub_idx);
		}
		
		return map;
	}

	
}
