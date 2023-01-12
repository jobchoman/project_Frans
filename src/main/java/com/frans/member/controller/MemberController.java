package com.frans.member.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frans.member.dto.MemberDTO;
import com.frans.member.service.MemberService;

@Controller
public class MemberController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MemberService memberService;
	@Autowired PasswordEncoder encoder;
	
	@GetMapping(value = "/login.go")
	public String index() {
	   return "memberLogin";
	}
	
	@GetMapping(value="/memberLogout.do")
	public String logout(HttpSession session) {
		session.removeAttribute("loginId");
		return "redirect:/";
	}
	
	@PostMapping(value="/memberLogin.do")
	public String login(String emp_id, String emp_pw,RedirectAttributes rAttr, HttpSession session) {
		String page = "redirect:/";
		String msg = "아이디와 비밀번호를 확인하세요";
		String loginId = null;
		loginId = memberService.login(emp_id,emp_pw);
		if(loginId != null) {
			page = "index";
			msg = "안녕하세요. "+emp_id+" 님";
			session.setAttribute("loginId", loginId);

		}
		rAttr.addFlashAttribute("msg",msg);
		
		return page;
	}
	
	@GetMapping(value="/memberJoin.go")
	public String joinForm(Model model) {
		ArrayList<MemberDTO> teamDto = memberService.teamList();
		ArrayList<MemberDTO> posDto = memberService.posList();
		ArrayList<MemberDTO> dutyDto = memberService.dutyList();
		ArrayList<MemberDTO> stateDto = memberService.stateList();
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
	public String join(@RequestParam HashMap<String, String> params, MultipartFile file, MultipartFile file2, HttpServletRequest req) {
		logger.info("params : {}",params);
		memberService.join(params,file,file2,req);
		return "index";
	}
	
	
	@GetMapping(value="/memberList.go")
	public String listMain(Model model) {
		ArrayList<MemberDTO> memberList = memberService.memberList();
		ArrayList<MemberDTO> teamDto = memberService.teamList();
		ArrayList<MemberDTO> posDto = memberService.posList();
		ArrayList<MemberDTO> dutyDto = memberService.dutyList();
		ArrayList<MemberDTO> stateDto = memberService.stateList();
		model.addAttribute("teamMem",teamDto);
		model.addAttribute("posMem",posDto);
		model.addAttribute("dutyMem",dutyDto);
		model.addAttribute("stateMem",stateDto);
		model.addAttribute("list",memberList);
		return "memberList";
	}
	
	@GetMapping(value="/memberDetail.do")
	public String memberDetail(String emp_id,Model model) {
		MemberDTO dto = memberService.memberDetail(emp_id,model);
		ArrayList<MemberDTO> SchoolDTO =  memberService.memberDetailSchool(emp_id,model); 
		ArrayList<MemberDTO> LicenseDTO =  memberService.memberDetailLicense(emp_id,model); 
		model.addAttribute("memSchool",SchoolDTO);
		model.addAttribute("memLicense",LicenseDTO);
		logger.info("memLicense : {}",LicenseDTO);
		logger.info("memSchool : {}",SchoolDTO);
		logger.info("mem : {}",dto);
		model.addAttribute("mem",dto);
		ArrayList<MemberDTO> fileList = memberService.fileList(emp_id);
		ArrayList<MemberDTO> fileList1 = memberService.fileList1(emp_id);
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
	
	@ResponseBody
	@GetMapping(value="/selList.ajax")
	public HashMap<String, Object> selList(Model model){
		logger.info("컨트롤러 호출");
		ArrayList<MemberDTO> teamDto = memberService.teamList();
		ArrayList<MemberDTO> posDto = memberService.posList();
		ArrayList<MemberDTO> dutyDto = memberService.dutyList();
		ArrayList<MemberDTO> stateDto = memberService.stateList();
		model.addAttribute("teamMem",teamDto);
		model.addAttribute("posMem",posDto);
		model.addAttribute("dutyMem",dutyDto);
		model.addAttribute("stateMem",stateDto);
		logger.info("dto :{}",teamDto);
		logger.info("dto :{}",posDto);
		logger.info("dto :{}",dutyDto);
		logger.info("dto :{}",stateDto);

		return memberService.selList();
	}
	
	@ResponseBody
	@GetMapping(value="/subsubSel.ajax")
	public HashMap<String, Object> subsubSel(@RequestParam HashMap<String, String> params, String controll){
		logger.info("sub 리스트 요청 :{}",controll);
		logger.info("params :{}",params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("controll", controll);
		map.put("subList", memberService.subsubSel(controll,params));
		
		return map;
	}
	
	
	@ResponseBody
	@GetMapping(value="/subSelList.ajax")
	public HashMap<String, Object> subSelList(String select, String subSelect){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("controll", select);
		logger.info("subSelect:{}",subSelect);
		logger.info("select: {} ",select);
		return memberService.subSelList(select,subSelect);
	}
	
	@GetMapping(value="/memberUpdate.go")
	public String updateForm(String emp_id, Model model) {
		logger.info("detail emp_id : "+emp_id);
		MemberDTO dto = memberService.memberDetail(emp_id, model);
		ArrayList<MemberDTO> fileList = memberService.fileList(emp_id);
		ArrayList<MemberDTO> fileList1 = memberService.fileList1(emp_id);
		ArrayList<MemberDTO> teamDto = memberService.teamList();
		ArrayList<MemberDTO> posDto = memberService.posList();
		ArrayList<MemberDTO> dutyDto = memberService.dutyList();
		ArrayList<MemberDTO> stateDto = memberService.stateList();
		model.addAttribute("teamMem",teamDto);
		model.addAttribute("posMem",posDto);
		model.addAttribute("dutyMem",dutyDto);
		model.addAttribute("stateMem",stateDto);
		model.addAttribute("fileList",fileList);
		model.addAttribute("fileList1",fileList1);
		model.addAttribute("mem", dto);
		return "memberUpdateForm";
	}
	
	@PostMapping(value="/memberUpdate.do")
	public String update(MemberDTO dto, @RequestParam HashMap<String, String> params, MultipartFile file, MultipartFile file2) {
		memberService.memberUpdate(dto);
//		memberService.fileUpdate(dto,file,file2);
		return "redirect:/memberList.go";
	}
	
	

	

	
}
