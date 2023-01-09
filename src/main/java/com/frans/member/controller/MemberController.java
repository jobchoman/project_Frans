package com.frans.member.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.frans.member.dto.MemberDTO;
import com.frans.member.service.MemberService;

@Controller
public class MemberController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MemberService service;

	
	@GetMapping(value="/memberJoin.go")
	public String joinForm(Model model) {
		ArrayList<MemberDTO> teamDto = service.teamList();
		ArrayList<MemberDTO> posDto = service.posList();
		ArrayList<MemberDTO> dutyDto = service.dutyList();
		ArrayList<MemberDTO> stateDto = service.stateList();
		model.addAttribute("teamMem",teamDto);
		model.addAttribute("posMem",posDto);
		model.addAttribute("dutyMem",dutyDto);
		model.addAttribute("stateMem",stateDto);
		logger.info("dto :{}",teamDto);
		logger.info("dto :{}",posDto);
		logger.info("dto :{}",dutyDto);
		logger.info("dto :{}",stateDto);
		return "memberJoinForm";
	}
	
	@PostMapping(value="/memberJoin.do")
	public String join(@RequestParam HashMap<String, String> params, MultipartFile file, MultipartFile file2) {
		logger.info("params : {}",params);
		service.join(params,file,file2);
		return "index";
	}
	
	@GetMapping(value="/memberList.go")
	public String listMain(Model model) {
		ArrayList<MemberDTO> memberList = service.memberList();
		model.addAttribute("list",memberList);
		return "memberList";
	}
	
	@GetMapping(value="/memberDetail.do")
	public String memberDetail(String emp_id,Model model) {
		MemberDTO dto = service.memberDetail(emp_id,model);
		model.addAttribute("mem",dto);
		ArrayList<MemberDTO> fileList = service.fileList(emp_id);  //파일 정보 가져오기
		ArrayList<MemberDTO> fileList1 = service.fileList1(emp_id);  //파일 정보 가져오기
		logger.info("fileList"+fileList);
		model.addAttribute("fileList",fileList);
		model.addAttribute("fileList1",fileList1);
		return "memberDetail";
	}
	
	@GetMapping(value="/memberPhoto.do")
	public ResponseEntity<Resource> showImage(String path) {
		logger.info("photo name : "+path);
		String filePath = "C:/fransUpload/"+path;
		
		//파일 시스템으로 리소스를 읽어와 담는다.(리소스 바디)
		Resource resource = new FileSystemResource(filePath);
		
		//헤더(내가 보낸 컨텐트의 타입이 어떤것이다.)
		HttpHeaders header = new HttpHeaders();
		try {
			String type = Files.probeContentType(Paths.get(filePath)); //image/png
			logger.info("file type : "+type);
			header.add("content-type", type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource,header,HttpStatus.OK);
	}
	

	
	
	

	

	
}
