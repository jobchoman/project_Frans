package com.frans.member.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.frans.member.dto.MemberDTO;

@Mapper
public interface MemberDAO {

	int join(HashMap<String, String> params);

	void fileUpload1(String file_ori, String file_new, String emp_id);

	void fileUpload2(String file_ori, String file_new, String emp_id);

//	void join2(HashMap<String, String> params);
//
//	void join3(HashMap<String, String> params);

	void join4(HashMap<String, String> params);

	void join5(HashMap<String, String> params);

	ArrayList<MemberDTO> teamList();
	ArrayList<MemberDTO> posList();
	ArrayList<MemberDTO> dutyList();
	ArrayList<MemberDTO> stateList();

	ArrayList<MemberDTO> memberList();

	MemberDTO memberDetail(String emp_id);

	ArrayList<MemberDTO> fileList(String emp_id);

	ArrayList<MemberDTO> fileList1(String emp_id);

	void join2(MemberDTO memberDTO);
//
	void join3(MemberDTO memberDTO);

	void hash(String hash);

	String login(String emp_id);

	ArrayList<MemberDTO> selList(String sel, String con);

	void joinHigh(String emp_career_idx, String emp_school_name, String emp_department, String emp_career_start,
			String emp_career_end, String emp_id, String emp_career_etc);

	void joinUniv(String emp_career_idx, String emp_school_name, String emp_department, String emp_career_start,
			String emp_career_end, String emp_id, String emp_career_etc, String emp_degree);

	void joinGrad(String emp_career_idx, String emp_school_name, String emp_department, String emp_career_start,
			String emp_career_end, String emp_id, String emp_career_etc, String emp_degree);

	void joinCareer(String emp_career_idx, String emp_school_name, String emp_department, String emp_career_start,
			String emp_career_end, String emp_id, String emp_career_etc, String emp_degree);

	void join4(String emp_id, String license_name, String license_date, String license_place, String license_result);





}
