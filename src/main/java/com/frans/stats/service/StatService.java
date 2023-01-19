package com.frans.stats.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frans.stats.dao.StatDAO;

@Service
public class StatService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired StatDAO statdao;

	public HashMap<String, Object> provinceListCall() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", statdao.provinceListCall());
		
		
		return map;
	}

	public HashMap<String, Object> cityListCall(int idx) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", statdao.cityListCall(idx));
		
		return map;
	}

	public HashMap<String, Object> shopListCall(int idx) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", statdao.shopListCall(idx));
		
		return map;
	}

	public HashMap<String, Object> subListCall(String subSort) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(subSort.equals("subAll")) {
			map.put("list", statdao.subListCall1());
		}else {
			map.put("list", statdao.subListCall2(subSort));
		}
		return map;
	}

	public ArrayList<HashMap<String, Object>> subStatGender1() {
		
		return statdao.subStatGender1();
	}

	public ArrayList<HashMap<String, Object>> subStatGender2(String sub_idx) {
		return statdao.subStatGender2(sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender3(String sub_sort_idx) {
		return statdao.subStatGender3(sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender4( String sub_sort_idx, String sub_idx) {
		return statdao.subStatGender4(sub_sort_idx, sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender5(String gender) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		
		return statdao.subStatGender5(gender1);
	}

	public ArrayList<HashMap<String, Object>> subStatGender6(String gender, String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		
		return statdao.subStatGender6(gender1, sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender7(String gender, String sub_sort_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		
		return statdao.subStatGender7(gender1, sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender8(String gender, String sub_sort_idx, String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		
		return statdao.subStatGender8(gender1, sub_sort_idx, sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender9(String year) {
		return statdao.subStatGender9(year);
	}

	public ArrayList<HashMap<String, Object>> subStatGender10(String year, String sub_idx) {
		return statdao.subStatGender10(year, sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender11(String year, String sub_sort_idx) {
		return statdao.subStatGender11(year, sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender12(String year, String sub_sort_idx, String sub_idx) {
		return statdao.subStatGender12(year, sub_sort_idx, sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender13(String year, String gender) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender13(year, gender1);
	}

	public ArrayList<HashMap<String, Object>> subStatGender14(String year, String gender, String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender14(year, gender1, sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender15(String year, String gender, String sub_sort_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender15(year, gender1, sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender16(String year, String gender, String sub_sort_idx,
			String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender16(year, gender1,sub_sort_idx,sub_idx);
	}
	
}
