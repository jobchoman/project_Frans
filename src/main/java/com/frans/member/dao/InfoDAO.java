package com.frans.member.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.frans.member.dto.MemberDTO;

@Mapper
public interface InfoDAO {

	ArrayList<MemberDTO> infoTeamList();

	ArrayList<MemberDTO> infoPosList();

	ArrayList<MemberDTO> infoDutyList();

	ArrayList<MemberDTO> infoList();
	
	ArrayList<MemberDTO> teamList();

	ArrayList<MemberDTO> dutyList();

	ArrayList<MemberDTO> posList();

	void teamWrite(HashMap<String, String> params);

	void posWrite(HashMap<String, String> params);

	void dutyWrite(HashMap<String, String> params);

	int subCount();

	MemberDTO teamUpdateForm(String team_idx);

	void teamUpdate(HashMap<String, String> params);

	MemberDTO dutyUpdateForm(String duty_idx);

	void dutyUpdate(HashMap<String, String> params);

	MemberDTO posUpdateForm(String pos_idx);

	void posUpdate(HashMap<String, String> params);

	int TeamDelete(String team_idx);

	void DutyDelete(String duty_idx);

	void PosDelete(String pos_idx);

	MemberDTO dutyDetail(String duty_idx);

	MemberDTO posDetail(String pos_idx);

	MemberDTO teamDetail(String team_idx);

	ArrayList<MemberDTO> per(String duty_idx);

	ArrayList<MemberDTO> perso(String team_idx);

	ArrayList<MemberDTO> pers(String pos_idx);










}
