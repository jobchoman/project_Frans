package com.frans.sub.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frans.sub.dao.SubDAO;
import com.frans.sub.dto.SubDTO;

@Service
public class SubService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired SubDAO subDAO;

	public ArrayList<HashMap<String, String>> menuList() {
		ArrayList<HashMap<String, String>> menuList = subDAO.menuList();
		return menuList;
	}

	public int subCount() {
		int cnt = subDAO.subCount();
		return cnt;
	}

	public void subWrite(SubDTO subdto, String sub_idx, Object[] menuList) {
		int row1 = subDAO.subWrite(subdto);
		logger.info(row1+"개의 값이 입력되었습니다.");
		
		for(int i=0; i<menuList.length; i++) {
			subDAO.subMenuWrite(sub_idx, menuList[i].toString());
			logger.info((i+1)+"번째 입력 완료");
		}
		
	}

	public HashMap<String, Object> subListCall(String sub_state) {

		HashMap<String, Object> subList = new HashMap<String, Object>();
		
		ArrayList<HashMap<String, Object>> sub = subDAO.subListCall(sub_state);
		logger.info("한줄 : "+sub.get(0));
		logger.info("sub 크기"+sub.size());
		
		String sub_idx = "";
		for(int i = 0; i<sub.size(); i++) {
			sub_idx = (String) sub.get(i).get("sub_idx");
			ArrayList<String> menuList = subDAO.menuListCall(sub_idx);
			sub.get(i).put("sub_menu", menuList);
		}
		
		subList.put("data", sub);
		
		return subList;
	}

	public HashMap<String, Object> subDetail(String sub_idx) {
		HashMap<String, Object> sdetail = subDAO.subDetail(sub_idx);
		ArrayList<String> menuList = subDAO.menuListCall(sub_idx);
		sdetail.put("sub_menu", menuList);
		return sdetail;
	}

	public ArrayList<HashMap<String, Object>> subMenu(String sub_idx) {
		ArrayList<HashMap<String, Object>> sub_menu = subDAO.subMenu(sub_idx);
		return sub_menu;
	}

	public void subUpdate(SubDTO subdto, Object[] menuList) {
		subDAO.subUpdate(subdto);
		logger.info("구독 테이블 업데이트 완료");
		
		String sub_idx = subdto.getSub_idx();
		
		
		int row = subDAO.subMenuDelete(sub_idx);
		logger.info(row+"개의 구독 메뉴 삭제");
			
		
		
		for(int i=0; i<menuList.length; i++) {
			subDAO.subMenuWrite(sub_idx, menuList[i].toString());
			logger.info((i+1)+"번째 구독 메뉴 입력 완료");
		}
		
	}
	
}
