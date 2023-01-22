package com.frans.member.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

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

	MemberDTO memberDetail(String emp_id, Model model);

	ArrayList<MemberDTO> fileList(String emp_id);

	ArrayList<MemberDTO> fileList1(String emp_id);

	void join2(MemberDTO memberDTO);

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

	ArrayList<MemberDTO> selList();

	ArrayList<MemberDTO> subsubSel(String controll, HashMap<String, String> params);

	ArrayList<MemberDTO> subSelList(String select, String subSelect);

	ArrayList<MemberDTO> memberDetailSchool(String emp_id);

	ArrayList<MemberDTO> memberDetailLicense(String emp_id);

	ArrayList<MemberDTO> memberDetailRight(String emp_id);
	
	int memberUpdate(HashMap<String, String> params);

	int fileUpdate(String emp_id, MultipartFile file, MultipartFile file2);

	void fileUpdate1(String file_ori, String file_new, String emp_id);

	void fileUpdate2(String file_ori, String file_new, String emp_id);

	void highUpdate(String emp_career_idx, String emp_school_name, String emp_department, String emp_career_start,
			String emp_career_end, String emp_id, String emp_career_etc);

	void careerUpdate(String emp_career_idx, String emp_school_name, String emp_department, String emp_career_start,
			String emp_career_end, String emp_id,String emp_degree, String emp_career_etc);

	void licenUpdate(String emp_id, String license_name, String license_date, String license_place, String license_result);


	int memCareer(String emp_id, String emp_career_idx, String emp_school_name);

	int memLicense(String emp_id, String license_name, String license_date, String license_place, String license_result);

	void updatePw(String emp_id, String emp_pw);

	int resetPw(String emp_id, String emp_pw);

	int myPageUpdate(HashMap<String, String> params);

	String loginId(String emp_id, String emp_pw);

	ArrayList<MemberDTO> memberHistLog(String emp_id);

	int teamHis(String emp_id, String team_name);

	int posHis(String emp_id, String pos_name);

	int dutyHis(String emp_id, String duty_name);

	String idCheck(String emp_id);

	ArrayList<MemberDTO> chNotTeamList(String com, String emp_id);
	
	//히스토리 업데이트 관련
	String pos_name(String pos_idx);

	String duty_name(String duty_idx);

	String team_name(String team_idx);

	int empPosIdx(String emp_id, String pos_idx);

	int empDutyIdx(String emp_id, String duty_idx);

	int empTeamIdx(String emp_id, String team_idx);

	void historyUpdate(String emp_id, String type, String reason, String pos_name);

	void rightUpdate(String emp_id, String right_team, String right_auth_type);

	ArrayList<MemberDTO> rightTeam(String emp_id);








}
