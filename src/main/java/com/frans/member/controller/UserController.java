package com.frans.member.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.frans.member.dao.UserDAO;
import com.frans.member.dto.MemberDTO;
import com.frans.member.dto.UserDTO;
import com.frans.member.service.MemberService;
import com.frans.member.service.UserService;

@Controller
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired UserService userService;
	@Autowired MemberService memberService;
	@Autowired PasswordEncoder encoder;
	
	
	@GetMapping(value="/userList.go")
	public String listMain(Model model) {
		ArrayList<UserDTO> dto = userService.userList();
		model.addAttribute("list",dto);

		return "userList";
	}
	
	@ResponseBody
	@GetMapping(value="/userList.ajax")
	public HashMap<String, Object> userList(){
		return userService.clientList();
	}
	
	@GetMapping(value="/userJoin.go")
	public String userJoinForm() {
		return "userJoin";
	}
	
	@PostMapping(value="/userJoin.do")
	public String userJoin(@RequestParam HashMap<String, String> params) {
		logger.info("params : {}",params);
		userService.userJoin(params);
		return "redirect:/userList.go";
	}
	
	@GetMapping(value="/userDetail.do")
	public String userDetail(Model model,String client_id) {
		UserDTO dto = userService.userDetail(client_id);
		model.addAttribute("client",dto);
		return "userDetail";
	}
	
	@GetMapping(value="/userUpdate.go")
	public String userUpdateForm(String client_id, Model model) {
		UserDTO dto = userService.userDetail(client_id);
		model.addAttribute("client",dto);
		return "userUpdate";
	}
	
	@PostMapping(value="/userUpdate.do")
	public String update(UserDTO dto, @RequestParam HashMap<String, String> params,String client_id,Model model) {
		userService.userUpdate(dto);
		logger.info("params: {}",params);
		UserDTO dto1 = userService.userDetail(client_id);
		client_id = dto1.getClient_id();
		return "redirect:/userDetail.do?client_id="+client_id;
	}
	
	@GetMapping(value="/subUserList.go")
	public String subUserList(Model model, HttpSession session) {
		String emp_id = (String) session.getAttribute("loginId");
		logger.info("emp_id:{}",emp_id);
		ArrayList<UserDTO> dto = userService.subUserList(emp_id);
		model.addAttribute("list",dto);
		return "subUserList";
	}
	
	@ResponseBody
	@GetMapping(value="/subUserList.ajax")
	public HashMap<String, Object> subList(HttpSession session) {
		String emp_id = (String) session.getAttribute("loginId");
		return userService.subList(emp_id);
	}
	
	@GetMapping(value="/subUserJoin.go")
	public String subJoinForm(Model model,HttpSession session) {
		ArrayList<UserDTO> searchList = userService.searchList();
		ArrayList<UserDTO> clientSearchList = userService.clientSearchList();
		String emp_id = (String) session.getAttribute("loginId");
		UserDTO shop = userService.shopSearch(emp_id);
		logger.info("shop:{}",shop);
		model.addAttribute("shop",shop);
		model.addAttribute("list",searchList);
		model.addAttribute("mem",clientSearchList);
		return "subUserJoin";
	}
	
	@PostMapping(value="/subUserJoin.do")
	public String subJoin(@RequestParam HashMap<String, String> params, String client_id,HttpSession session) {
		String emp_id = (String) session.getAttribute("loginId");
		logger.info("params : {}",params);
		UserDTO client_idx = userService.subUserDetail(client_id);
		userService.subUserJoin(params);
		return "redirect:/subUserList.go";
	}
	
	@GetMapping(value="/subUserDetail.do")
	public String subJoinDetail(@RequestParam HashMap<String, String> params,Model model, String client_id) {
		UserDTO dto = userService.subUserDetail(client_id);
		ArrayList<UserDTO> list = userService.rec(client_id);
		model.addAttribute("dtl",dto);
		model.addAttribute("list",list);
		logger.info("dto",dto);
		return "subUserDetail";
	}
	
	@GetMapping(value="/subUserUpdate.go")
	public String subUserUpdateForm(String client_id, Model model) {
		ArrayList<UserDTO> searchList = userService.searchList();
		UserDTO dto = userService.subUserDetail(client_id);
		ArrayList<UserDTO> list = userService.rec(client_id);
		model.addAttribute("sear",searchList);
		model.addAttribute("dtl",dto);
		model.addAttribute("list",list);
		return "subUserUpdate";
	}
	
	@PostMapping(value="/subUserUpdate.do")
	public String subUpdate(UserDTO dto, @RequestParam HashMap<String, String> params,String client_id,Model model) {
		userService.subUpdate(dto);
		logger.info("params: {}",params);
		UserDTO dto1 = userService.subUserDetail(client_id);
		client_id = dto1.getClient_id();
		return "redirect:/subUserDetail.do?client_id="+client_id;
	}
	
	

	
	

	

	

	
}
