package com.frans.sign.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.frans.member.dto.MemberDTO;
import com.frans.sign.dto.DocFormDTO;
import com.frans.sign.dto.ReferDTO;
import com.frans.sign.dto.SignHistoryDTO;
import com.frans.sign.dto.fileDTO;
import com.frans.sign.dto.signDTO;
import com.frans.sign.dto.signMemDTO;

@Mapper
public interface SignDAO {

	ArrayList<signDTO> signList(String team_value, String auth_type);

	ArrayList<signDTO> dateSearch(String startdate, String enddate, String team_value, String auth_type);

	DocFormDTO signWriteGo(String doc_form_idx);

	ArrayList<MemberDTO> memberList();
	
	ArrayList<MemberDTO> membermsgList();

	int signWriteDo(signDTO signdto);

//	void signMember(int sign_idx, String sign_emp, int index);

//	void refMember(int sign_idx, String ref_emp);

	void docForm_use_update(String doc_form_idx);

	void signMember(signMemDTO signmemdto);

	void refMember(ReferDTO referdto);

	signDTO signDetailGo(String sign_idx);

	ArrayList<signMemDTO> signDetailSignmem(String sign_idx);

	ArrayList<ReferDTO> signDetailRefermem(String sign_idx);

	MemberDTO selectEmpName(String loginId);

	ArrayList<SignHistoryDTO> signHistory(String sign_idx);

	String lastOrder(String sign_idx);

	int signMemUpdate(String sign_idx, String loginId, String userIP, String comment);

	int signStateUpdate(String sign_idx, String loginId, String sign_order, String last_order_id);

	int signStateUpdate_first(String sign_idx);

	String loginName(String loginId);

	void fileUpload(int sign_idx, String oriFileName, String newFileName);

	ArrayList<signMemDTO> signDoMemCnt(String sign_idx);

	ArrayList<fileDTO> fileList(String sign_idx);

	String download(String path);

	ArrayList<fileDTO> orifileList(String sign_idx);

	int signReturn(String comment, String userIP, String loginId, String sign_idx);

	void signReturnUpdate(String sign_idx);
	
	//알람 관련
	String notiSignNext(String sign_idx);

	int signReturn(signMemDTO signmemdto);

	ArrayList<fileDTO> signImgUpdate(String loginId);

	ArrayList<MemberDTO> selTeam(String loginId);

	ArrayList<signDTO> signList_signmem(String loginId);

	ArrayList<signDTO> dateSearch_signmem(String loginId, String startdate, String enddate);

	ArrayList<String> auth_team(String loginId);

	String auth_type(String string, String loginId);

	ArrayList<signDTO> signList_refermem(String loginId);

	ArrayList<signDTO> dateSearch_refermem(String loginId, String startdate, String enddate);

	/*
	 * ArrayList<signDTO> signEndList(String team_value, String auth_type);
	 * 
	 * ArrayList<signDTO> dateSearch_endList(String startdate, String enddate,
	 * String team_value, String auth_type);
	 */

	ArrayList<signDTO> signUserWriteList(String team_value, String loginId);

	ArrayList<signDTO> dateSearch_userList(String startdate, String enddate, String team_value, String loginId);

	ArrayList<signDTO> signList_endsignmem(String loginId);

	ArrayList<signDTO> dateSearch_endsignmem(String loginId, String startdate, String enddate);

	ArrayList<signDTO> signList_returnsignmem(String loginId);

	ArrayList<signDTO> dateSearch_returnsignmem(String loginId, String startdate, String enddate);

	String signEmp(String sign_idx);

	int signMemCount(String sign_idx);

	ArrayList<String> denyMemList(String sign_idx, String loginId);

	ArrayList<String> denyRefMemList(String sign_idx);

	ArrayList<signDTO> dateSearch_endrefermem(String loginId, String startdate, String enddate);

	ArrayList<signDTO> signList_endrefermem(String loginId);

	ArrayList<signDTO> signList_returnrefermem(String loginId);

	ArrayList<signDTO> dateSearch_returnrefermem(String loginId, String startdate, String enddate);

	String signMax(String sign_idx);

	String signLast(String signMax, String sign_idx);

}
