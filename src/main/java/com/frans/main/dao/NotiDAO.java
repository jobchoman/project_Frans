package com.frans.main.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.frans.member.dto.MemberDTO;
import com.frans.stock.dto.StockDTO;

@Mapper
public interface NotiDAO {

	ArrayList<StockDTO> notiList(String emp_id);

	ArrayList<StockDTO> notiListBox(String emp_id);

	int notiDelete(String noti_idx, String emp_id);

}
