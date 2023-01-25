package com.frans.member.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.frans.member.dto.MemberDTO;
import com.frans.member.dto.UserDTO;

@Mapper
public interface UserDAO {

	ArrayList<UserDTO> userList();

	ArrayList<UserDTO> clientList();

	void userJoin(HashMap<String, String> params);

	UserDTO userDetail(String client_id);

	void userUpdate(UserDTO dto);

	UserDTO userClient_id(String client_id);

	ArrayList<UserDTO> subUserList(String emp_id);

	ArrayList<UserDTO> subList(String emp_id);

	ArrayList<UserDTO> searchList();

	ArrayList<UserDTO> searchPop();

	ArrayList<UserDTO> clientSearchList();

	int subUser(HashMap<String, String> params);

	UserDTO subUserDetail(String client_id);

	ArrayList<UserDTO> rec(String client_id);

	void cliUpdate(UserDTO dto);

	void userState(UserDTO client_idx);

	String userShop(String emp_id);

	UserDTO shopSearch(String emp_id);

//	void subscribe(HashMap<String, String> params);
//
//	void subUserJoin(HashMap<String, String> params);







}
