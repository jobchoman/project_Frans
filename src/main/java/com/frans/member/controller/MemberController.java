package com.frans.member.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	String hash = "";
	
//	@GetMapping(value = "/login.go")
//	public String index() {
//	   return "memberLogin";
//	}
	
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
	
//	@PostMapping(value="/memberJoin.do")
//	public String join(@RequestParam HashMap<String, String> params, MultipartFile file, MultipartFile file2) {
//		logger.info("params : {}",params);
//		memberService.join(params,file,file2);
//		return "index";
//	}
	
	@PostMapping(value="/memberJoin.do")
	public String join(@RequestParam HashMap<String, String> params, MultipartFile file, MultipartFile file2, HttpServletRequest req) {
		logger.info("params : {}",params);
		// 이력번호, 라이센스 이름이 없으면 안넘어가도록
		memberService.join(params,file,file2,req);
		return "index";
	}
	
//	@ResponseBody
//	@PostMapping(value="/memberJoin1.ajax")
//	public HashMap<Object, Object> memberJoin (ArrayList<MemberDTO> list){
//		
//		HashMap<Object, Object> map = new HashMap<Object, Object>();
//		map.put("list", list);
//		
////		   public HashMap<String, Object> routeWrite1(@RequestParam(value="locIdx[]") List<Integer> locIdx, 
////		         @RequestParam String content, @RequestParam String loginId, @RequestParam String title) {
////
////		      Object[] locationIdx = locIdx.toArray();
////		      
////		      int board_idx = routeService.routeWrite(locationIdx, loginId, title, content);   
////		      HashMap<String, Object> map = new HashMap<String, Object>();
////		      map.put("board_idx", board_idx);
////		      return map;
////		   }
//		return map;
//	}
	
//	public HashMap<String, Object> routeWrite1(@RequestParam(value="locIdx[]") List<Integer> locIdx, 
//		     @RequestMapping(value="/routeWrite1")
//		   @ResponseBody
//		        @RequestParam String content, @RequestParam String loginId, @RequestParam String title) {
//
//		      Object[] locationIdx = locIdx.toArray();
//		      
//		      int board_idx = routeService.routeWrite(locationIdx, loginId, title, content);   
//		      HashMap<String, Object> map = new HashMap<String, Object>();
//		      map.put("board_idx", board_idx);
//		      return map;
//		   }
	
//    public void insetUser(Map<string, object=""> map) {
//        List<userdto> list = (ArrayList<userdto>)map.get("list");
//        for(UserDto dto : list) {
//            mapper.insert("user.insert", dto);
//        }
//    }
	
//	@ResponseBody
//	@PostMapping(value="/memberJoin1.ajax")
//	public HashMap<Object, Object> memberJoin (Map<String, Object> map){
//		return map;
//	}
	
	
	
	@GetMapping(value="/memberList.go")
	public String listMain(Model model) {
		ArrayList<MemberDTO> memberList = memberService.memberList();
		model.addAttribute("list",memberList);
		return "memberList";
	}
	
	@GetMapping(value="/memberDetail.do")
	public String memberDetail(String emp_id,Model model) {
		MemberDTO dto = memberService.memberDetail(emp_id,model);
		model.addAttribute("mem",dto);
		ArrayList<MemberDTO> fileList = memberService.fileList(emp_id);  //파일 정보 가져오기
		ArrayList<MemberDTO> fileList1 = memberService.fileList1(emp_id);  //파일 정보 가져오기
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
