package com.frans.stats.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatDAO {

	ArrayList<HashMap<String, Object>> provinceListCall();

	ArrayList<HashMap<String, Object>> cityListCall(int idx);

	ArrayList<HashMap<String, Object>> shopListCall(int idx);

	ArrayList<HashMap<String, Object>> subListCall1();

	ArrayList<HashMap<String, Object>> subListCall2(String subSort);

}
