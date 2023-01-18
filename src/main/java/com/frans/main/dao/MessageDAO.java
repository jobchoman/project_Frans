package com.frans.main.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.frans.main.dto.MessageDTO;
import com.frans.main.dto.NotiDTO;
import com.frans.member.dto.MemberDTO;
import com.frans.stock.dto.StockDTO;

@Mapper
public interface MessageDAO {

	int msgWriteDo(MessageDTO msgdto);

	void msgBoxWriteDo(String msg_idx, String emp_id);

	ArrayList<StockDTO> msgList(String loginId);

	


}
