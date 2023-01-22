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

	public ArrayList<HashMap<String, Object>> subStatGender17(String year, String month) {
		return statdao.subStatGender17(year, month);
	}

	public ArrayList<String> subStatGenderDays(String year, String month) {
		String date = year+"-"+month+"-"+"01";
		return statdao.subStatGenderDays(date);
	}

	public ArrayList<HashMap<String, Object>> subStatGender18(String year, String month, String sub_idx) {
		return statdao.subStatGender18(year, month, sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender19(String year, String month, String sub_sort_idx) {
		return statdao.subStatGender19(year, month, sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender20(String year, String month, String sub_sort_idx,
			String sub_idx) {
		return statdao.subStatGender20(year, month, sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender21(String year, String month, String gender) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender21(year, month, gender1);
	}

	public ArrayList<HashMap<String, Object>> subStatGender22(String year, String month, String gender,
			String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender22(year, month, gender1,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender23(String year, String month, String gender,
			String sub_sort_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender23(year, month, gender1,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender24(String year, String month, String gender,
			String sub_sort_idx, String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender24(year, month, gender1,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender25(String province_idx) {
		return statdao.subStatGender25(province_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender26(String province_idx, String sub_idx) {
		return statdao.subStatGender26(province_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender27(String province_idx, String sub_sort_idx) {
		return statdao.subStatGender27(province_idx,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender28(String province_idx, String sub_sort_idx,
			String sub_idx) {
		return statdao.subStatGender28(province_idx,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender29(String province_idx, String gender) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender29(province_idx,gender1);
	}

	public ArrayList<HashMap<String, Object>> subStatGender30(String province_idx, String gender, String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender30(province_idx,gender1,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender31(String province_idx, String gender, String sub_sort_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender31(province_idx,gender1,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender32(String province_idx, String gender, String sub_sort_idx,
			String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender32(province_idx,gender1,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender33(String province_idx, String year) {
		return statdao.subStatGender33(province_idx,year);
	}

	public ArrayList<HashMap<String, Object>> subStatGender34(String province_idx, String year, String sub_idx) {
		return statdao.subStatGender34(province_idx,year,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender35(String province_idx, String year, String sub_sort_idx) {
		return statdao.subStatGender35(province_idx,year,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender36(String province_idx, String year, String sub_sort_idx,
			String sub_idx) {
		return statdao.subStatGender36(province_idx,year,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender37(String province_idx, String year, String gender) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender37(province_idx,year,gender1);
	}

	public ArrayList<HashMap<String, Object>> subStatGender38(String province_idx, String year, String gender,
			String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender38(province_idx,year,gender1,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender39(String province_idx, String year, String gender,
			String sub_sort_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender39(province_idx,year,gender1,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender40(String province_idx, String year, String gender,
			String sub_sort_idx, String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender40(province_idx,year,gender1,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender41(String province_idx, String year, String month) {
		return statdao.subStatGender41(province_idx,year,month);
	}

	public ArrayList<HashMap<String, Object>> subStatGender42(String province_idx, String year, String month,
			String sub_idx) {
		return statdao.subStatGender42(province_idx,year,month,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender43(String province_idx, String year, String month,
			String sub_sort_idx) {
		return statdao.subStatGender43(province_idx,year,month,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender44(String province_idx, String year, String month,
			String sub_sort_idx, String sub_idx) {
		return statdao.subStatGender44(province_idx,year,month,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender45(String province_idx, String year, String month,
			String gender) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender45(province_idx,year,month,gender1);
	}

	public ArrayList<HashMap<String, Object>> subStatGender46(String province_idx, String year, String month,
			String gender, String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender46(province_idx,year,month,gender1,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender47(String province_idx, String year, String month,
			String gender, String sub_sort_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender47(province_idx,year,month,gender1,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender48(String province_idx, String year, String month,
			String gender, String sub_sort_idx, String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender48(province_idx,year,month,gender1,sub_sort_idx,sub_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender49(String city_idx) {
		return statdao.subStatGender49(city_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender50(String city_idx, String sub_idx) {
		return statdao.subStatGender50(city_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender51(String city_idx, String sub_sort_idx) {
		return statdao.subStatGender51(city_idx,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender52(String city_idx, String sub_sort_idx,
			String sub_idx) {
		return statdao.subStatGender52(city_idx,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender53(String city_idx, String gender) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender53(city_idx,gender1);
	}

	public ArrayList<HashMap<String, Object>> subStatGender54(String city_idx, String gender, String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender54(city_idx,gender1,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender55(String city_idx, String gender, String sub_sort_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender55(city_idx,gender1,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender56(String city_idx, String gender, String sub_sort_idx,
			String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender56(city_idx,gender1,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender57(String city_idx, String year) {
		return statdao.subStatGender57(city_idx,year);
	}

	public ArrayList<HashMap<String, Object>> subStatGender58(String city_idx, String year, String sub_idx) {
		return statdao.subStatGender58(city_idx,year,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender59(String city_idx, String year, String sub_sort_idx) {
		return statdao.subStatGender59(city_idx,year,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender60(String city_idx, String year, String sub_sort_idx,
			String sub_idx) {
		return statdao.subStatGender60(city_idx,year,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender61(String city_idx, String year, String gender) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender61(city_idx,year,gender1);
	}

	public ArrayList<HashMap<String, Object>> subStatGender62(String city_idx, String year, String gender,
			String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender62(city_idx,year,gender1,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender63(String city_idx, String year, String gender,
			String sub_sort_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender63(city_idx,year,gender1,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender64(String city_idx, String year, String gender,
			String sub_sort_idx, String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender64(city_idx,year,gender1,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender65(String city_idx, String year, String month) {
		return statdao.subStatGender65(city_idx,year,month);
	}

	public ArrayList<HashMap<String, Object>> subStatGender66(String city_idx, String year, String month,
			String sub_idx) {
		return statdao.subStatGender66(city_idx,year,month,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender67(String city_idx, String year, String month,
			String sub_sort_idx) {
		return statdao.subStatGender67(city_idx,year,month,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender68(String city_idx, String year, String month,
			String sub_sort_idx, String sub_idx) {
		return statdao.subStatGender68(city_idx,year,month,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender69(String city_idx, String year, String month,
			String gender) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender69(city_idx,year,month,gender1);
	}

	public ArrayList<HashMap<String, Object>> subStatGender70(String city_idx, String year, String month,
			String gender, String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender70(city_idx,year,month,gender1,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender71(String city_idx, String year, String month,
			String gender, String sub_sort_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender71(city_idx,year,month,gender1,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatGender72(String city_idx, String year, String month,
			String gender, String sub_sort_idx, String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender72(city_idx,year,month,gender1,sub_sort_idx,sub_idx);
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public ArrayList<HashMap<String, Object>> subStatGender73(String shop_idx) {
		return statdao.subStatGender73(shop_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender74(String shop_idx, String sub_idx) {
		return statdao.subStatGender74(shop_idx,sub_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender75(String shop_idx, String sub_sort_idx) {
		return statdao.subStatGender75(shop_idx,sub_sort_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender76(String shop_idx, String sub_sort_idx,
			String sub_idx) {
		return statdao.subStatGender76(shop_idx,sub_sort_idx,sub_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender77(String shop_idx, String gender) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender77(shop_idx,gender1);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender78(String shop_idx, String gender, String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender78(shop_idx,gender1,sub_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender79(String shop_idx, String gender, String sub_sort_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender79(shop_idx,gender1,sub_sort_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender80(String shop_idx, String gender, String sub_sort_idx,
			String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender80(shop_idx,gender1,sub_sort_idx,sub_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender81(String shop_idx, String year) {
		return statdao.subStatGender81(shop_idx,year);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender82(String shop_idx, String year, String sub_idx) {
		return statdao.subStatGender82(shop_idx,year,sub_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender83(String shop_idx, String year, String sub_sort_idx) {
		return statdao.subStatGender83(shop_idx,year,sub_sort_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender84(String shop_idx, String year, String sub_sort_idx,
			String sub_idx) {
		return statdao.subStatGender84(shop_idx,year,sub_sort_idx,sub_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender85(String shop_idx, String year, String gender) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender85(shop_idx,year,gender1);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender86(String shop_idx, String year, String gender,
			String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender86(shop_idx,year,gender1,sub_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender87(String shop_idx, String year, String gender,
			String sub_sort_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender87(shop_idx,year,gender1,sub_sort_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender88(String shop_idx, String year, String gender,
			String sub_sort_idx, String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender88(shop_idx,year,gender1,sub_sort_idx,sub_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender89(String shop_idx, String year, String month) {
		return statdao.subStatGender89(shop_idx,year,month);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender90(String shop_idx, String year, String month,
			String sub_idx) {
		return statdao.subStatGender90(shop_idx,year,month,sub_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender91(String shop_idx, String year, String month,
			String sub_sort_idx) {
		return statdao.subStatGender91(shop_idx,year,month,sub_sort_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender92(String shop_idx, String year, String month,
			String sub_sort_idx, String sub_idx) {
		return statdao.subStatGender92(shop_idx,year,month,sub_sort_idx,sub_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender93(String shop_idx, String year, String month,
			String gender) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender93(shop_idx,year,month,gender1);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender94(String shop_idx, String year, String month,
			String gender, String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender94(shop_idx,year,month,gender1,sub_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender95(String shop_idx, String year, String month,
			String gender, String sub_sort_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender95(shop_idx,year,month,gender1,sub_sort_idx);
	}
	
	public ArrayList<HashMap<String, Object>> subStatGender96(String shop_idx, String year, String month,
			String gender, String sub_sort_idx, String sub_idx) {
		String gender1 = "";
		if(gender.equals("man")) {
			gender1 = "남";
		}else {
			gender1 = "여";
		}
		return statdao.subStatGender96(shop_idx,year,month,gender1,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge1(String year) {
		return statdao.subStatAge1(year);
	}

	public ArrayList<HashMap<String, Object>> subStatAge2(String year, String sub_idx) {
		return statdao.subStatAge2(year,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge3(String year, String sub_sort_idx) {
		return statdao.subStatAge3(year,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge4(String year, String sub_sort_idx, String sub_idx) {
		return statdao.subStatAge4(year,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge5(String year, String month) {
		return statdao.subStatAge5(year,month);
	}

	public ArrayList<HashMap<String, Object>> subStatAge6(String year, String month, String sub_idx) {
		return statdao.subStatAge6(year,month,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge7(String year, String month, String sub_sort_idx) {
		return statdao.subStatAge7(year,month,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge8(String year, String month, String sub_sort_idx,
			String sub_idx) {
		return statdao.subStatAge8(year,month,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge9(String year, String province_idx) {
		return statdao.subStatAge9(year,province_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge10(String year, String province_idx, String sub_idx) {
		return statdao.subStatAge10(year,province_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge11(String year, String province_idx, String sub_sort_idx) {
		return statdao.subStatAge11(year,province_idx,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge12(String year, String province_idx, String sub_sort_idx,
			String sub_idx) {
		return statdao.subStatAge12(year,province_idx,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge13(String year, String month, String province_idx) {
		return statdao.subStatAge13(year,month,province_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge14(String year, String month, String province_idx,
			String sub_idx) {
		return statdao.subStatAge14(year,month,province_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge15(String year, String month, String province_idx,
			String sub_sort_idx) {
		return statdao.subStatAge15(year,month,province_idx,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge16(String year, String month, String province_idx,
			String sub_sort_idx, String sub_idx) {
		return statdao.subStatAge16(year,month,province_idx,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge17(String year, String city_idx) {
		return statdao.subStatAge17(year,city_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge18(String year, String city_idx, String sub_idx) {
		return statdao.subStatAge18(year,city_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge19(String year, String city_idx, String sub_sort_idx) {
		return statdao.subStatAge19(year,city_idx,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge20(String year, String city_idx, String sub_sort_idx,
			String sub_idx) {
		return statdao.subStatAge20(year,city_idx,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge21(String year, String month, String city_idx) {
		return statdao.subStatAge21(year,month,city_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge22(String year, String month, String city_idx, String sub_idx) {
		return statdao.subStatAge22(year,month,city_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge23(String year, String month, String city_idx,
			String sub_sort_idx) {
		return statdao.subStatAge23(year,month,city_idx,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge24(String year, String month, String city_idx,
			String sub_sort_idx, String sub_idx) {
		return statdao.subStatAge24(year,month,city_idx,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge25(String year, String shop_idx) {
		return statdao.subStatAge25(year,shop_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge26(String year, String shop_idx, String sub_idx) {
		return statdao.subStatAge26(year,shop_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge27(String year, String shop_idx, String sub_sort_idx) {
		return statdao.subStatAge27(year,shop_idx,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge28(String year, String shop_idx, String sub_sort_idx,
			String sub_idx) {
		return statdao.subStatAge28(year,shop_idx,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge29(String year, String month, String shop_idx) {
		return statdao.subStatAge29(year,month,shop_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge30(String year, String month, String shop_idx, String sub_idx) {
		return statdao.subStatAge30(year,month,shop_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge31(String year, String month, String shop_idx,
			String sub_sort_idx) {
		return statdao.subStatAge31(year,month,shop_idx,sub_sort_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatAge32(String year, String month, String shop_idx,
			String sub_sort_idx, String sub_idx) {
		return statdao.subStatAge32(year,month,shop_idx,sub_sort_idx,sub_idx);
	}

	public ArrayList<HashMap<String, Object>> subStatPreference1(String year) {
		return statdao.subStatPreference1(year);
	}

	public ArrayList<HashMap<String, Object>> subStatPreference2(String year, String month) {
		return statdao.subStatPreference2(year,month);
	}

	public ArrayList<HashMap<String, Object>> subStatPreference3(String province_idx, String year) {
		return statdao.subStatPreference3(province_idx,year);
	}

	public ArrayList<HashMap<String, Object>> subStatPreference4(String province_idx, String year, String month) {
		return statdao.subStatPreference4(province_idx,year,month);
	}

	public ArrayList<HashMap<String, Object>> subStatPreference5(String city_idx, String year) {
		return statdao.subStatPreference5(city_idx,year);
	}

	public ArrayList<HashMap<String, Object>> subStatPreference6(String city_idx, String year, String month) {
		return statdao.subStatPreference6(city_idx,year,month);
	}

	public ArrayList<HashMap<String, Object>> subStatPreference7(String shop_idx, String year) {
		return statdao.subStatPreference7(shop_idx,year);
	}

	public ArrayList<HashMap<String, Object>> subStatPreference8(String shop_idx, String year, String month) {
		return statdao.subStatPreference8(shop_idx,year,month);
	}
	
}
