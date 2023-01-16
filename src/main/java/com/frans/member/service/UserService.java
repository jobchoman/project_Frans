package com.frans.member.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.frans.member.dao.UserDAO;
import com.frans.member.dto.UserDTO;

@Service
public class UserService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Value("${file.location}")private String root;
	
	@Autowired UserDAO userDao;
	@Autowired PasswordEncoder encoder;


	public ArrayList<UserDTO> userList() {
		
		return userDao.userList();
	}

	public HashMap<String, Object> clientList() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<UserDTO> userList = userDao.clientList();
		map.put("data", userList);
		return map;
	}

	public void userJoin(HashMap<String, String> params) {
		String plain_pw = params.get("client_pw");
		String enc_pw = encoder.encode(plain_pw);
		params.put("client_pw", enc_pw);
		userDao.userJoin(params);
		
	}

	public UserDTO userDetail(String client_id) {
		return userDao.userDetail(client_id);
	}

	public void userUpdate(UserDTO dto) {
		userDao.userUpdate(dto);
		
	}

//	public UserDTO userClient_id(String client_id) {
//		return userDao.userClient_id(client_id);
//	}









	
	

}
