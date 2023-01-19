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

	ArrayList<MessageDTO> msgList(String loginId);

	ArrayList<MessageDTO> msgListBox(String loginId);

	MessageDTO msgListDetail(String loginId, String msg_idx);

	void msgDateUpdate(String msg_idx, String loginId);

	ArrayList<MessageDTO> msgSendListBox(String loginId);

	MessageDTO msgListSendDetail(String msg_idx, String loginId);

	ArrayList<MessageDTO> msgListSendDetailMem(String msg_idx, String loginId);

	int msgDelete(String msg_idx, String emp_id);

//	ArrayList<MessageDTO> msgSendListBoxName(String loginId);

	


}
