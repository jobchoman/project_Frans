package com.frans.member.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.frans.member.dto.MemberDTO;
import com.frans.member.service.InfoService;

@Controller
public class InfoController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired InfoService infoService;
	
	@GetMapping(value="/memberInfoList.go")
	public String memberInfoTeamList(Model model) {
		ArrayList<MemberDTO> infoTeamList = infoService.infoTeamList();
		model.addAttribute("list",infoTeamList);
		return "memberInfoTeamList";
	}
	
	@GetMapping(value="/memberInfoPosList.go")
	public String memberInfoPosList(Model model) {
		ArrayList<MemberDTO> infoPosList = infoService.infoPosList();
		model.addAttribute("list",infoPosList);
		return "memberInfoPosList";
	}
	
	@GetMapping(value="/memberInfoDutyList.go")
	public String memberInfoDutyList(Model model) {
		ArrayList<MemberDTO> infoDutyList = infoService.infoDutyList();
		model.addAttribute("list",infoDutyList);
		return "memberInfoDutyList";
	}
	
	@GetMapping(value="/memberInfoTeamWrite.go")
	public String infoTeamWriteForm() {
		return "memberInfoTeamWrite";
	}
	@GetMapping(value="/memberInfoTeamWrite.do")
	public String infoTeamWrite(@RequestParam HashMap<String, String> params) {
//		String team_idx = "";
//		int cnt = infoService.subCount()+1;
//		if(cnt < 10) {
//			team_idx = "TM00"+cnt;
//		}else if(10 <= cnt && cnt < 100) {
//			team_idx = "TM0"+cnt;
//		}else {
//			team_idx = "TM"+cnt;
//		}
//		MemberDTO dto = new MemberDTO();
//		dto.setTeam_idx(team_idx);
		infoService.teamWrite(params);
		return "redirect:/memberInfoList.go";
	}
	
	@GetMapping(value="/memberInfoPosWrite.go")
	public String infoPosWriteForm() {
		return "memberInfoPosWrite";
	}
	@GetMapping(value="/memberInfoPosWrite.do")
	public String infoPosWrite(@RequestParam HashMap<String, String> params) {
		infoService.posWrite(params);
		return "redirect:/memberInfoPosList.go";
	}
	
	@GetMapping(value="/memberInfoDutyWrite.go")
	public String infoDutyWriteForm() {
		return "memberInfoDutyWrite";
	}
	@GetMapping(value="/memberInfoDutyWrite.do")
	public String infoDutyWrite(@RequestParam HashMap<String, String> params) {
		infoService.dutyWrite(params);
		return "redirect:/memberInfoList.go";
	}
	
	@GetMapping(value="/memberInfoTeamUpdate.go")
	public String infoTeamUpdate(Model model, String team_idx) {
		MemberDTO dto = infoService.teamUpdateForm(team_idx);
		model.addAttribute("mem",dto);
		return "memberInfoTeamUpdate";
	}
	
	@GetMapping(value="/memberInfoTeamUpdate.do")
	public String teamUpdate(@RequestParam HashMap<String, String> params) {
		infoService.teamUpdate(params);
		return "redirect:/memberInfoList.go";
	}
	
	
	
	
//	@GetMapping(value="/memberInfoList.go")
//	public String memberInfoList(Model model) {
//		ArrayList<MemberDTO> infoList = infoService.infoList();
//		model.addAttribute("list",infoList);
//		return "memberInfoList";
//	}
	
//	@ResponseBody
//	@GetMapping(value="/teamList.ajax")
//	public HashMap<String, Object> teamList(){
//		return infoService.teamList();
//	}
//	@ResponseBody
//	@GetMapping(value="/dutyList.ajax")
//	public HashMap<String, Object> dutyList(){
//		return infoService.dutyList();
//	}
//	@ResponseBody
//	@GetMapping(value="/posList.ajax")
//	public HashMap<String, Object> posList(){
//		return infoService.posList();
//	}


	
	
	

	

	
}
