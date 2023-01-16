package com.frans.main.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.frans.main.dto.NotiDTO;
import com.frans.member.dto.MemberDTO;
import com.frans.stock.dto.StockDTO;

@Mapper
public interface NotiDAO {

	ArrayList<StockDTO> notiList(String emp_id);

	ArrayList<StockDTO> notiListBox(String emp_id);

	int notiDelete(String noti_idx, String emp_id);
	
	void notiSignInsert(NotiDTO notidto);

	void notiBoxInsert(String noti_idx, String emp_id);

	String notiSignMem(String emp_id, String sign_idx);

	String notiRefMem(String emp_id, String sign_idx);

	void notiBoxUpdate(String noti_idx, String emp_id);

	String notiSignMemIdx(String sign_idx);

	ArrayList<String> notiSignAll(String sign_idx);

	void notiSignAllInsert(String emp_id, String noti_idx);

	String notiEmpSearch(String noti_idx);

	void newNotification(NotiDTO notidto, String sign_idx);

	void notiSoloInsert(String notiEmp, String newNotiIdx);

	ArrayList<String> notiRefList(String sign_idx);

	String notiSignMemOk(String emp_id, String sign_idx);

	String notiRefMemOk(String emp_id, String sign_idx);



}
