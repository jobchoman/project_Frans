package com.frans.stock.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.frans.stock.dto.MenuDTO;

@Mapper
public interface MenuDAO {

	int menuRegister(MenuDTO mDTO);

	int menuCount();

//	ArrayList<HashMap<String, String>> menuList();

	ArrayList<HashMap<String, Object>> menuListCall(String menu_state);

	MenuDTO menuDetail(String menu_idx);

	void menuUpdate1(MenuDTO mDTO);

	void menuUpdate2(MenuDTO mDTO);

}
