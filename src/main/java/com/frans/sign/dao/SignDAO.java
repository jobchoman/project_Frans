package com.frans.sign.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.frans.member.dto.MemberDTO;
import com.frans.sign.dto.DocFormDTO;
import com.frans.sign.dto.ReferDTO;
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


}
