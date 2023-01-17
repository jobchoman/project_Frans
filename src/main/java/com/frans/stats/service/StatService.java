package com.frans.stats.service;

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
	
}
