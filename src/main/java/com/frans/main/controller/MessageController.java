package com.frans.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.frans.main.dto.MessageDTO;
import com.frans.main.service.MessageService;
import com.frans.main.service.NotiService;
import com.frans.stock.dto.StockDTO;

@Controller
public class MessageController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired MessageService msgsevice;
	
	@GetMapping(value="/msgList.go")
	public ModelAndView msgListGo(HttpServletRequest req){
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		logger.info("결재 문서 작성 이동 컨트롤러");
		logger.info("로그인 아이디: "+loginId);
		
		
		return msgsevice.msgListGo(loginId);
	}
	
	@GetMapping(value="/msgSendList.go")
	public ModelAndView msgSendListGo(HttpServletRequest req){
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		logger.info("결재 문서 작성 이동 컨트롤러");
		logger.info("로그인 아이디: "+loginId);
		
		
		return msgsevice.msgSendListGo(loginId);
	}
	
	@PostMapping(value="/msg/write.do")
	public String msgWriteDo(@RequestParam HashMap<String, String> params, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		logger.info("쪽지 작성 컨트롤러");
		logger.info("params: {}",params);
		
		msgsevice.msgWriteDo(params,loginId,req);
		String page = "redirect:/msgList.go";
		return page;
	}
	
	//메인화면 메신저 리스트
	@ResponseBody
	@GetMapping(value="/msg/msgList.ajax")
	public HashMap<String, Object> msgList(HttpServletRequest req){
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		logger.info("메신저 리스트 컨트롤러");
		logger.info("로그인 아이디: "+loginId);
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<MessageDTO> msgList = msgsevice.msgList(loginId);
		map.put("msgList", msgList);
		
		return map;
	}
	
	//메신저 수신함 리스트
	@ResponseBody
	@GetMapping(value="/msg/msgListBox.ajax")
	public HashMap<String, Object> msgListBox(HttpServletRequest req){
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		logger.info("메신저 수신함 컨트롤러");
		logger.info("로그인 아이디: "+loginId);
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<MessageDTO> msgListBox = msgsevice.msgListBox(loginId);
		map.put("data", msgListBox);
		
		return map;
	}
	
	//메신저 발신함 리스트
	@ResponseBody
	@GetMapping(value="/msg/msgSendListBox.ajax")
	public HashMap<String, Object> msgSendListBox(HttpServletRequest req){
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		logger.info("메신저 발신함 컨트롤러");
		logger.info("로그인 아이디: "+loginId);
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<MessageDTO> msgSendListBox = msgsevice.msgSendListBox(loginId);
		//ArrayList<MessageDTO> msgSendListBoxName = msgsevice.msgSendListBoxName(loginId);
		map.put("data", msgSendListBox);
		
		return map;
	}
	
	//메신저 디테일
	@ResponseBody
	@GetMapping(value="/msg/msgListDetail.ajax")
	public MessageDTO msgListDetail(HttpServletRequest req,@RequestParam HashMap<String, String> params){
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		logger.info("메신저 디테일 컨트롤러");
		logger.info("디테일 params: {}",params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		MessageDTO msgListDetail = msgsevice.msgListDetail(params,loginId);
		if(msgListDetail != null && !msgListDetail.equals("")) {
			logger.info("쪽지 읽음시간 업데이트");
			msgsevice.msgDateUpdate(params,loginId);
		}
		map.put("data", msgListDetail);
		
		return msgListDetail;
	}
	
	//메신저 발신함 디테일
	@ResponseBody
	@GetMapping(value="/msg/msgListSendDetail.ajax")
	public HashMap<String, Object> msgListSendDetail(HttpServletRequest req,@RequestParam HashMap<String, String> params){
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		logger.info("메신저 디테일 컨트롤러");
		logger.info("디테일 params: {}",params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		MessageDTO msgListSendDetail = msgsevice.msgListSendDetail(params,loginId);
		ArrayList<MessageDTO> msgListSendDetailMem = msgsevice.msgListSendDetailMem(params,loginId);
		
		map.put("msgList", msgListSendDetail);
		map.put("memList", msgListSendDetailMem);
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value="/msg/msgDelete.ajax")
	public String msgDelete(@RequestParam HashMap<String, String> params,HttpServletRequest req, Model model){
		String msg = "실패";
		HttpSession session = req.getSession();
		String emp_id = (String) session.getAttribute("loginId");
		
		logger.info("params : {}",params);
		logger.info("params : {}",emp_id);
		String msg_idx = params.get("msg_idx");
		if(msg_idx != null && msg_idx !="") {
			int del = msgsevice.msgDelete(msg_idx,emp_id);			
			logger.info("del : "+del);
			if(del > 0) {
				msg = "성공";
			}
		}
			
		model.addAttribute("msg",msg);		

		return msg;

	}
	
	

}
