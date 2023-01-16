package com.frans.member.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

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
import com.frans.member.service.UserService;

@Controller
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired UserService userService;
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
	

	

	

	
}
