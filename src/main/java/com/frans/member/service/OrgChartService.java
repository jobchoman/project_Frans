package com.frans.member.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frans.member.dao.OrgChartDAO;

@Service
public class OrgChartService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired OrgChartDAO orgDAO;

	public ArrayList<HashMap<String, String>> searchEmp(String emp_name) {
		
		ArrayList<HashMap<String, String>> resultList = orgDAO.searchEmp(emp_name);
		
		logger.info("결과 값 = "+resultList);
		
		return resultList;
	}


	
}
