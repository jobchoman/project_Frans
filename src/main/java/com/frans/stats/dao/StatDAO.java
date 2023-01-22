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

	ArrayList<HashMap<String, Object>> subStatGender1();

	ArrayList<HashMap<String, Object>> subStatGender2(String sub_idx);

	ArrayList<HashMap<String, Object>> subStatGender3(String sub_sort_idx);

	ArrayList<HashMap<String, Object>> subStatGender4(String sub_sort_idx, String sub_idx);

	ArrayList<HashMap<String, Object>> subStatGender5(String gender);

	ArrayList<HashMap<String, Object>> subStatGender6(String gender1, String sub_idx);

	ArrayList<HashMap<String, Object>> subStatGender7(String gender1, String sub_sort_idx);

	ArrayList<HashMap<String, Object>> subStatGender8(String gender1, String sub_sort_idx, String sub_idx);

	ArrayList<HashMap<String, Object>> subStatGender9(String year);

	ArrayList<HashMap<String, Object>> subStatGender10(String year, String sub_idx);

	ArrayList<HashMap<String, Object>> subStatGender11(String year, String sub_sort_idx);

	ArrayList<HashMap<String, Object>> subStatGender12(String year, String sub_sort_idx, String sub_idx);

	ArrayList<HashMap<String, Object>> subStatGender13(String year, String gender1);

	ArrayList<HashMap<String, Object>> subStatGender14(String year, String gender1, String sub_idx);

	ArrayList<HashMap<String, Object>> subStatGender15(String year, String gender1, String sub_sort_idx);

	ArrayList<HashMap<String, Object>> subStatGender16(String year, String gender1, String sub_sort_idx,
			String sub_idx);

}
