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

	ArrayList<signDTO> signList();

	ArrayList<signDTO> dateSearch(String startdate, String enddate);

	DocFormDTO signWriteGo(String doc_form_idx);

	ArrayList<MemberDTO> memberList();

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




}
