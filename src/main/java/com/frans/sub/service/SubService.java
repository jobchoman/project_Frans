package com.frans.sub.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frans.sub.dao.SubDAO;

@Service
public class SubService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired SubDAO subDAO;

	public ArrayList<HashMap<String, String>> menuList() {
		ArrayList<HashMap<String, String>> menuList = subDAO.menuList();
		return menuList;
	}
	
}
