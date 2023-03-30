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

	// 시도/시군구/매장 선택 X, 년/월 선택 X, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender1();

	// 시도/시군구/매장 선택 X, 년/월 선택 X, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender2(String sub_idx);

	// 시도/시군구/매장 선택 X, 년/월 선택 X, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender3(String sub_sort_idx);

	// 시도/시군구/매장 선택 X, 년/월 선택 X, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 O  
	ArrayList<HashMap<String, Object>> subStatGender4(String sub_sort_idx, String sub_idx);

	// 시도/시군구/매장 선택 X, 년/월 선택 X, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender5(String gender);

	// 시도/시군구/매장 선택 X, 년/월 선택 X, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender6(String gender1, String sub_idx);

	// 시도/시군구/매장 선택 X, 년/월 선택 X, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatGender7(String gender1, String sub_sort_idx);
	
	// 시도/시군구/매장 선택 X, 년/월 선택 X, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender8(String gender1, String sub_sort_idx, String sub_idx);

	// 시도/시군구/매장 선택 X, 년 선택 O, 월 선택 X, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender9(String year);

	// 시도/시군구/매장 선택 X, 년 선택 O, 월 선택 X, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatGender10(String year, String sub_idx);

	// 시도/시군구/매장 선택 X, 년 선택 O, 월 선택 X, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender11(String year, String sub_sort_idx);

	// 시도/시군구/매장 선택 X, 년 선택 O, 월 선택 X, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 O  
	ArrayList<HashMap<String, Object>> subStatGender12(String year, String sub_sort_idx, String sub_idx);

	// 시도/시군구/매장 선택 X, 년 선택 O, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 X 
	ArrayList<HashMap<String, Object>> subStatGender13(String year, String gender1);

	// 시도/시군구/매장 선택 X, 년 선택 O, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender14(String year, String gender1, String sub_idx);

	// 시도/시군구/매장 선택 X, 년 선택 O, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatGender15(String year, String gender1, String sub_sort_idx);

	// 시도/시군구/매장 선택 X, 년 선택 O, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender16(String year, String gender1, String sub_sort_idx,
			String sub_idx);

	// 시도/시군구/매장 선택 X, 년 선택 O, 월 선택 O, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender17(String year, String month);

	
	ArrayList<String> subStatGenderDays(String date);

	// 시도/시군구/매장 선택 X, 년 선택 O, 월 선택 O, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatGender18(String year, String month, String sub_idx);

	// 시도/시군구/매장 선택 X, 년 선택 O, 월 선택 O, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 X  

	ArrayList<HashMap<String, Object>> subStatGender19(String year, String month, String sub_sort_idx);

	// 시도/시군구/매장 선택 X, 년 선택 O, 월 선택 O, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 O  
	ArrayList<HashMap<String, Object>> subStatGender20(String year, String month, String sub_sort_idx, String sub_idx);

	// 시도/시군구/매장 선택 X, 년 선택 O, 월 선택 O, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender21(String year, String month, String gender1);

	// 시도/시군구/매장 선택 X, 년 선택 O, 월 선택 O, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender22(String year, String month, String gender1, String sub_idx);

	// 시도/시군구/매장 선택 X, 년 선택 O, 월 선택 O, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatGender23(String year, String month, String gender1, String sub_sort_idx);

	// 시도/시군구/매장 선택 X, 년 선택 O, 월 선택 O, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender24(String year, String month, String gender1, String sub_sort_idx,
			String sub_idx);

	// 시도 선택, 시군구/매장 선택 X, 년 선택 X, 월 선택 X, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender25(String province_idx);

	// 시도 선택, 시군구/매장 선택 X, 년 선택 X, 월 선택 X, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatGender26(String province_idx, String sub_idx);

	// 시도 선택, 시군구/매장 선택 X, 년 선택 X, 월 선택 X, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender27(String province_idx, String sub_sort_idx);

	// 시도 선택, 시군구/매장 선택 X, 년 선택 X, 월 선택 X, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 O  
	ArrayList<HashMap<String, Object>> subStatGender28(String province_idx, String sub_sort_idx, String sub_idx);

	// 시도 선택, 시군구/매장 선택 X, 년 선택 X, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender29(String province_idx, String gender1);

	// 시도 선택, 시군구/매장 선택 X, 년 선택 X, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender30(String province_idx, String gender1, String sub_idx);

	// 시도 선택, 시군구/매장 선택 X, 년 선택 X, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatGender31(String province_idx, String gender1, String sub_sort_idx);

	// 시도 선택, 시군구/매장 선택 X, 년 선택 X, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender32(String province_idx, String gender1, String sub_sort_idx,
			String sub_idx);

	// 시도 선택 O, 시군구/매장 선택 X, 년 선택 X, 월 선택 X, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender33(String province_idx, String year);

	// 시도 선택 O, 시군구/매장 선택 X, 년 선택 X, 월 선택 X, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatGender34(String province_idx, String year, String sub_idx);

	// 시도 선택 O, 시군구/매장 선택 X, 년 선택 X, 월 선택 X, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender35(String province_idx, String year, String sub_sort_idx);

	// 시도 선택 O, 시군구/매장 선택 X, 년 선택 X, 월 선택 X, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 O  
	ArrayList<HashMap<String, Object>> subStatGender36(String province_idx, String year, String sub_sort_idx,
			String sub_idx);

	// 시도 선택 O, 시군구/매장 선택 X, 년 선택 X, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender37(String province_idx, String year, String gender1);

	// 시도 선택 O, 시군구/매장 선택 X, 년 선택 X, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender38(String province_idx, String year, String gender1,
			String sub_idx);

	// 시도 선택 O, 시군구/매장 선택 X, 년 선택 X, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatGender39(String province_idx, String year, String gender1,
			String sub_sort_idx);

	// 시도 선택 O, 시군구/매장 선택 X, 년 선택 X, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender40(String province_idx, String year, String gender1,
			String sub_sort_idx, String sub_idx);

	// 시도 선택 O, 시군구/매장 선택 X, 년 선택 O, 월 선택 O, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender41(String province_idx, String year, String month);

	// 시도 선택 O, 시군구/매장 선택 X, 년 선택 O, 월 선택 O, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatGender42(String province_idx, String year, String month, String sub_idx);

	// 시도 선택 O, 시군구/매장 선택 X, 년 선택 O, 월 선택 O, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender43(String province_idx, String year, String month,
			String sub_sort_idx);

	// 시도 선택 O, 시군구/매장 선택 X, 년 선택 O, 월 선택 O, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 O  
	ArrayList<HashMap<String, Object>> subStatGender44(String province_idx, String year, String month,
			String sub_sort_idx, String sub_idx);

	// 시도 선택 O, 시군구/매장 선택 X, 년 선택 O, 월 선택 O, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender45(String province_idx, String year, String month, String gender1);

	// 시도 선택 O, 시군구/매장 선택 X, 년 선택 O, 월 선택 O, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender46(String province_idx, String year, String month, String gender1,
			String sub_idx);

	// 시도 선택 O, 시군구/매장 선택 X, 년 선택 O, 월 선택 O, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatGender47(String province_idx, String year, String month, String gender1,
			String sub_sort_idx);

	// 시도 선택 O, 시군구/매장 선택 X, 년 선택 O, 월 선택 O, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender48(String province_idx, String year, String month, String gender1,
			String sub_sort_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 X, 월 선택 X, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender49(String city_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 X, 월 선택 X, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatGender50(String city_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 X, 월 선택 X, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender51(String city_idx, String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 X, 월 선택 X, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatGender52(String city_idx, String sub_sort_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 X, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 X 
	ArrayList<HashMap<String, Object>> subStatGender53(String city_idx, String gender1);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 X, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender54(String city_idx, String gender1, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 X, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatGender55(String city_idx, String gender1, String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 X, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender56(String city_idx, String gender1, String sub_sort_idx,
			String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 X, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender57(String city_idx, String year);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 X, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatGender58(String city_idx, String year, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 X, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 X 
	ArrayList<HashMap<String, Object>> subStatGender59(String city_idx, String year, String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 X, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 O  
	ArrayList<HashMap<String, Object>> subStatGender60(String city_idx, String year, String sub_sort_idx,
			String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender61(String city_idx, String year, String gender1);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender62(String city_idx, String year, String gender1, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatGender63(String city_idx, String year, String gender1,
			String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender64(String city_idx, String year, String gender1,
			String sub_sort_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 O, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender65(String city_idx, String year, String month);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 O, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatGender66(String city_idx, String year, String month, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 O, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender67(String city_idx, String year, String month, String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 O, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 O  
	ArrayList<HashMap<String, Object>> subStatGender68(String city_idx, String year, String month, String sub_sort_idx,
			String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 O, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender69(String city_idx, String year, String month, String gender1);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 O, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender70(String city_idx, String year, String month, String gender1,
			String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 O, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatGender71(String city_idx, String year, String month, String gender1,
			String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 O, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender72(String city_idx, String year, String month, String gender1,
			String sub_sort_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 X, 월 선택 X, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 X 
	ArrayList<HashMap<String, Object>> subStatGender73(String shop_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 X, 월 선택 X, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatGender74(String shop_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 X, 월 선택 X, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender75(String shop_idx, String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 X, 월 선택 X, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 O  
	ArrayList<HashMap<String, Object>> subStatGender76(String shop_idx, String sub_sort_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 X, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender77(String shop_idx, String gender1);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 X, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender78(String shop_idx, String gender1, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 X, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatGender79(String shop_idx, String gender1, String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 X, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender80(String shop_idx, String gender1, String sub_sort_idx,
			String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 X, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender81(String shop_idx, String year);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 X, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatGender82(String shop_idx, String year, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 X, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender83(String shop_idx, String year, String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 X, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender84(String shop_idx, String year, String sub_sort_idx,
			String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender85(String shop_idx, String year, String gender1);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender86(String shop_idx, String year, String gender1, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatGender87(String shop_idx, String year, String gender1,
			String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 X, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender88(String shop_idx, String year, String gender1,
			String sub_sort_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 O, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatGender89(String shop_idx, String year, String month);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 O, 성별 전체, 횟수권, 요일권 선택 X, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatGender90(String shop_idx, String year, String month, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 O, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 X 
	ArrayList<HashMap<String, Object>> subStatGender91(String shop_idx, String year, String month, String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 O, 성별 전체, 횟수권/요일권 선택 O, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatGender92(String shop_idx, String year, String month, String sub_sort_idx,
			String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 O, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatGender93(String shop_idx, String year, String month, String gender1);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 O, 성별 선택, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatGender94(String shop_idx, String year, String month, String gender1,
			String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 O, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatGender95(String shop_idx, String year, String month, String gender1,
			String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 O, 성별 선택, 횟수권, 요일권 선택 O, 구독권 선택 O

	ArrayList<HashMap<String, Object>> subStatGender96(String shop_idx, String year, String month, String gender1,
			String sub_sort_idx, String sub_idx);

	// 시도 선택 X, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 X, 횟수권, 요일권 선택 X, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatAge1(String year);

	// 시도 선택 X, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 X, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatAge2(String year, String sub_idx);

	// 시도 선택 X, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 X, 횟수권/요일권 선택 O, 구독권 선택 X 
	ArrayList<HashMap<String, Object>> subStatAge3(String year, String sub_sort_idx);

	// 시도 선택 X, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 X, 횟수권/요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatAge4(String year, String sub_sort_idx, String sub_idx);

	// 시도 선택 X, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 O, 횟수권, 요일권 선택 X, 구독권 선택 X 
	ArrayList<HashMap<String, Object>> subStatAge5(String year, String month);

	// 시도 선택 X, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 O, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatAge6(String year, String month, String sub_idx);

	// 시도 선택 X, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 O, 횟수권, 요일권 선택 O, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatAge7(String year, String month, String sub_sort_idx);

	// 시도 선택 X, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 O, 횟수권, 요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatAge8(String year, String month, String sub_sort_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 X, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatAge9(String year, String province_idx);

	// 시도 선택 O, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 X, 횟수권, 요일권 선택 X, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatAge10(String year, String province_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 X, 횟수권/요일권 선택 O, 구독권 선택 X 
	ArrayList<HashMap<String, Object>> subStatAge11(String year, String province_idx, String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 X, 횟수권/요일권 선택 O, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatAge12(String year, String province_idx, String sub_sort_idx,
			String sub_idx);

	// 시도 선택 O, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 O, 횟수권, 요일권 선택 X, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatAge13(String year, String month, String province_idx);

	// 시도 선택 O, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 O, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatAge14(String year, String month, String province_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 O, 횟수권, 요일권 선택 O, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatAge15(String year, String month, String province_idx,
			String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 O, 횟수권, 요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatAge16(String year, String month, String province_idx, String sub_sort_idx,
			String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 X, 월 선택 X, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatAge17(String year, String city_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 X, 월 선택 X, 횟수권, 요일권 선택 X, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatAge18(String year, String city_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 X, 월 선택 X, 횟수권/요일권 선택 O, 구독권 선택 X 
	ArrayList<HashMap<String, Object>> subStatAge19(String year, String city_idx, String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 X, 월 선택 X, 횟수권/요일권 선택 O, 구독권 선택 O  
	ArrayList<HashMap<String, Object>> subStatAge20(String year, String city_idx, String sub_sort_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 X, 월 선택 O, 횟수권, 요일권 선택 X, 구독권 선택 X 
	ArrayList<HashMap<String, Object>> subStatAge21(String year, String month, String city_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 X, 월 선택 O, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatAge22(String year, String month, String city_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 X, 월 선택 O, 횟수권, 요일권 선택 O, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatAge23(String year, String month, String city_idx, String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 X, 월 선택 O, 횟수권, 요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatAge24(String year, String month, String city_idx, String sub_sort_idx,
			String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 X, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatAge25(String year, String shop_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 X, 횟수권, 요일권 선택 X, 구독권 선택 O 
	ArrayList<HashMap<String, Object>> subStatAge26(String year, String shop_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 X, 횟수권/요일권 선택 O, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatAge27(String year, String shop_idx, String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 X, 횟수권/요일권 선택 O, 구독권 선택 O  
	ArrayList<HashMap<String, Object>> subStatAge28(String year, String shop_idx, String sub_sort_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 O, 횟수권, 요일권 선택 X, 구독권 선택 X  
	ArrayList<HashMap<String, Object>> subStatAge29(String year, String month, String shop_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 O, 횟수권, 요일권 선택 X, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatAge30(String year, String month, String shop_idx, String sub_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 O, 횟수권, 요일권 선택 O, 구독권 선택 X
	ArrayList<HashMap<String, Object>> subStatAge31(String year, String month, String shop_idx, String sub_sort_idx);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 O, 횟수권, 요일권 선택 O, 구독권 선택 O
	ArrayList<HashMap<String, Object>> subStatAge32(String year, String month, String shop_idx, String sub_sort_idx,
			String sub_idx);

	// 시도 선택 X, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 X
	ArrayList<HashMap<String, Object>> subStatPreference1(String year);

	// 시도 선택 X, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 O
	ArrayList<HashMap<String, Object>> subStatPreference2(String year, String month);

	// 시도 선택 O, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 X
	ArrayList<HashMap<String, Object>> subStatPreference3(String province_idx, String year);

	// 시도 선택 O, 시군구 선택 X, 매장 선택 X, 년 선택 O, 월 선택 O
	ArrayList<HashMap<String, Object>> subStatPreference4(String province_idx, String year, String month);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 X
	ArrayList<HashMap<String, Object>> subStatPreference5(String city_idx, String year);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 X, 년 선택 O, 월 선택 O
	ArrayList<HashMap<String, Object>> subStatPreference6(String city_idx, String year, String month);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 X
	ArrayList<HashMap<String, Object>> subStatPreference7(String shop_idx, String year);

	// 시도 선택 O, 시군구 선택 O, 매장 선택 O, 년 선택 O, 월 선택 O
	ArrayList<HashMap<String, Object>> subStatPreference8(String shop_idx, String year, String month);

	String subSortType(String sub_sort_idx);

	String subName(String sub_idx);

	String getProName(String province_idx);

	String getCityName(String city_idx);

	String getShopName(String shop_idx);

	int amountSubPre8(String shop_idx, String year, String month);

	int amountSubPre7(String shop_idx, String year);

	int amountSubPre6(String city_idx, String year, String month);

	int amountSubPre5(String city_idx, String year);

	int amountSubPre3(String province_idx, String year);

	int amountSubPre4(String province_idx, String year, String month);

	int amountSubPre1(String year);

	int amountSubPre2(String year, String month);




}
