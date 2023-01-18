package com.frans.stock.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.servlet.ModelAndView;

import com.frans.member.dto.MemberDTO;
import com.frans.stock.dto.StoreDTO;

@Mapper
public interface StoreDAO {

	ArrayList<StoreDTO> storeList();

	ArrayList<MemberDTO> managerList();

	int storeCount();

	int selProvince(String sido);

	int selCity(String sigungu, int province_id);

	int storeWrite(StoreDTO storedto);

	StoreDTO storeDetail(String shop_idx);

	int storeUpdateDo(StoreDTO storedto);

	int storeUpdateDo(String sign_idx, String emp_id, String shop_name, String shop_space, String shop_emp_num,
			String shop_contact, String shop_location, String shop_lat, String shop_lon, int province_id, int city_id);


}
