package com.frans.main.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.frans.main.dao.MessageDAO;
import com.frans.main.dao.NotiDAO;
import com.frans.main.dto.MessageDTO;
import com.frans.member.dto.MemberDTO;
import com.frans.sign.dao.SignDAO;
import com.frans.sign.dto.DocFormDTO;
import com.frans.stock.dao.OrderDAO;
import com.frans.stock.dao.StockDAO;
import com.frans.stock.dto.StockDTO;

@Service
public class MessageService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MessageDAO msgdao;
	@Autowired SignDAO signdao;

	public ModelAndView msgListGo(String loginId) {
		ModelAndView mav = new ModelAndView("msgList");
		ArrayList<MemberDTO> memberlist = signdao.memberList();
		mav.addObject("memberlist",memberlist);
		return mav;
	}

	public void msgWriteDo(HashMap<String, String> params, String loginId, HttpServletRequest req) {
		logger.info("쪽지 보내기 서비스 params : {}",params);
		String msg_content = params.get("msg_content");
		MessageDTO msgdto = new MessageDTO();
		msgdto.setEmp_id(loginId);		
		msgdto.setMsg_content(msg_content);
		int success = msgdao.msgWriteDo(msgdto);
		String msg_idx = msgdto.getMsg_idx();
		logger.info("msg idx : "+msg_idx);
		String emp_id[] = req.getParameterValues("empIdx_input");
		if(success > 0) {
			for(int i=0; i<emp_id.length; i++) {
				logger.info("메신저 수신함 추가 {}",emp_id[i]);
				msgdao.msgBoxWriteDo(msg_idx,emp_id[i]);
			}
		}
		
	}
	
	//메인화면 메신저 리스트
	public ArrayList<StockDTO> msgList(String loginId) {
		logger.info("메신저 리스트 아이디 : "+loginId);
		return msgdao.msgList(loginId);
	}
	
	
	

	

}
