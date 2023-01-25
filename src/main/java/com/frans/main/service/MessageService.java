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
		ArrayList<MemberDTO> memberlist = signdao.membermsgList();
		mav.addObject("memberlist",memberlist);
		return mav;
	}
	
	public ModelAndView msgSendListGo(String loginId) {
		ModelAndView mav = new ModelAndView("msgSendList");
		ArrayList<MemberDTO> memberSendlist = signdao.membermsgList();
		mav.addObject("memberlist",memberSendlist);
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
	public ArrayList<MessageDTO> msgList(String loginId) {
		logger.info("메신저 리스트 아이디 : "+loginId);
		return msgdao.msgList(loginId);
	}

	//메신저 수신함 리스트
	public ArrayList<MessageDTO> msgListBox(String loginId) {
		logger.info("메신저 수신함 아이디 : "+loginId);
		return msgdao.msgListBox(loginId);
	}

	public MessageDTO msgListDetail(HashMap<String, String> params, String loginId) {
		String msg_idx = params.get("msg_idx");
		logger.info("메신저 디테일 idx : "+msg_idx);
		logger.info("메신저 디테일 id : "+loginId);
		return msgdao.msgListDetail(loginId,msg_idx);
	}

	public void msgDateUpdate(HashMap<String, String> params, String loginId) {
		String msg_idx = params.get("msg_idx");
		logger.info("메신저 읽음확인 idx : "+msg_idx);
		logger.info("메신저 읽음확인 id : "+loginId);
		msgdao.msgDateUpdate(msg_idx,loginId);
		
	}

	public ArrayList<MessageDTO> msgSendListBox(String loginId) {
		logger.info("메신저 발신함 아이디 : "+loginId);
		return msgdao.msgSendListBox(loginId);
	}

	public MessageDTO msgListSendDetail(HashMap<String, String> params, String loginId) {
		logger.info("메신저 발신함 아이디 : "+loginId);
		String msg_idx = params.get("msg_idx");
		return msgdao.msgListSendDetail(msg_idx,loginId);
	}

	public ArrayList<MessageDTO> msgListSendDetailMem(HashMap<String, String> params, String loginId) {
		logger.info("메신저 발신함 아이디 : "+loginId);
		String msg_idx = params.get("msg_idx");
		return msgdao.msgListSendDetailMem(msg_idx,loginId);
	}

	public int msgDelete(String msg_idx, String emp_id) {
		logger.info("메신저 삭제 서비스 param: {}",msg_idx);
		return msgdao.msgDelete(msg_idx,emp_id);
	}

	/*
	 * public ArrayList<MessageDTO> msgSendListBoxName(String loginId) {
	 * logger.info("메신저 발신함 이름 아이디 : "+loginId); return
	 * msgdao.msgSendListBoxName(loginId); }
	 */
	
	
	

	

}
