package com.frans.sub.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.frans.sub.dto.SubDTO;

@Mapper
public interface SubDAO {

	ArrayList<HashMap<String, String>> menuList();

	int subCount();

	int subWrite(SubDTO subdto);

	void subMenuWrite(String sub_idx, String menu_idx);

	ArrayList<HashMap<String, Object>> subListCall(String sub_state);

	ArrayList<String> menuListCall(String sub_idx);

	HashMap<String, Object> subDetail(String sub_idx);

	ArrayList<HashMap<String, Object>> subMenu(String sub_idx);

	void subUpdate(SubDTO subdto);

	int subMenuDelete(String sub_idx);





}
