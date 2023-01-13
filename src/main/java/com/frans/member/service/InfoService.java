package com.frans.member.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frans.member.dao.InfoDAO;
import com.frans.member.dto.MemberDTO;

@Service
public class InfoService {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired InfoDAO infoDao;


	public ArrayList<MemberDTO> infoTeamList() {
		return infoDao.infoTeamList();
	}


	public ArrayList<MemberDTO> infoPosList() {
		return infoDao.infoPosList();
	}


	public ArrayList<MemberDTO> infoDutyList() {
		
		return infoDao.infoDutyList();
	}

//	public ArrayList<MemberDTO> infoList() {
//		return infoDao.infoList();
//	}
//
//	public HashMap<String, Object> teamList() {
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		ArrayList<MemberDTO> teamList = infoDao.teamList();
//		map.put("data", teamList);
//		return map;
//	}
//
//
//	public HashMap<String, Object> dutyList() {
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		ArrayList<MemberDTO> dutyList = infoDao.dutyList();
//		map.put("data", dutyList);
//		return map;
//	}
//
//
//	public HashMap<String, Object> posList() {
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		ArrayList<MemberDTO> posList = infoDao.posList();
//		map.put("data", posList);
//		return map;
//	}


	public void teamWrite(HashMap<String, String> params) {
		infoDao.teamWrite(params);
	}


	public void posWrite(HashMap<String, String> params) {
		infoDao.posWrite(params);
		
	}


	public void dutyWrite(HashMap<String, String> params) {
		infoDao.dutyWrite(params);
		
	}


	public int subCount() {
		int cnt = infoDao.subCount();
		return cnt;
	}


	public MemberDTO teamUpdateForm(String team_idx) {
		return infoDao.teamUpdateForm(team_idx);
	}


	public void teamUpdate(HashMap<String, String> params) {
		infoDao.teamUpdate(params);
		
	}











	
	

}
