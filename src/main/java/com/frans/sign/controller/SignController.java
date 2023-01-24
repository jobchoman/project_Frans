package com.frans.sign.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.frans.sign.service.DocFormService;
import com.frans.sign.service.SignService;

@Controller
public class SignController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired SignService signservice;
	@Autowired DocFormService docformservice;
	
	
	/* 결재 문서 리스트 */

	@GetMapping(value="/signList.go")
	public HashMap<String, Object> signListGo(HttpServletRequest req){
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		return signservice.signListGo(loginId);
	}
	
	// 전체
	@ResponseBody
	@GetMapping(value="/sign/list.do")
	public HashMap<String, Object> signList(@RequestParam HashMap<String, String> params, HttpServletRequest req){
		logger.info("결재 문서 리스트 컨트롤러");
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		String date1 = params.get("date1");
		String date2 = params.get("date2");
		String team_value = params.get("team_value");
		logger.info("시작일: "+date1+"종료일"+date2);
		logger.info("팀 이름: "+team_value);

		return signservice.signList(date1,date2,team_value,loginId);
	}
/*	
	// 결재완료
	@ResponseBody
	@GetMapping(value="/sign/endList.do")
	public HashMap<String, Object> signEndList(@RequestParam HashMap<String, String> params, HttpServletRequest req){
		logger.info("결재완료 문서 리스트 컨트롤러");
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		String date1 = params.get("date1");
		String date2 = params.get("date2");
		String team_value = params.get("team_value");
		logger.info("시작일: "+date1+"종료일"+date2);
		logger.info("팀 이름: "+team_value);

		return signservice.signEndList(date1,date2,team_value,loginId);
	}
*/
	// 내가 작성한 문서
	@ResponseBody
	@GetMapping(value="/sign/userWriteList.do")
	public HashMap<String, Object> signUserWriteList(@RequestParam HashMap<String, String> params, HttpServletRequest req){
		logger.info("내 결재 문서 리스트 컨트롤러");
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		String date1 = params.get("date1");
		String date2 = params.get("date2");
		String team_value = params.get("team_value");
		logger.info("시작일: "+date1+"종료일"+date2);
		logger.info("팀 이름: "+team_value);

		return signservice.signUserWriteList(date1,date2,team_value,loginId);
	}
	
	/*
	@ResponseBody
	@GetMapping(value="/sign/dateSearch.do")
	public HashMap<String, Object> dateSearch(@RequestParam HashMap<String, String> params){
		logger.info("결재 문서 기간 검색 컨트롤러");
		logger.info("params:{}",params);
		String date1 = params.get("date1");
		String date2 = params.get("date2");
		String team_value = params.get("team_value");
//		logger.info(date1+"/"+date2);
		
		return signservice.dateSearch(date1,date2,team_value);
	}
	*/
	
	@GetMapping(value="/signWrite.go")
	public ModelAndView signWriteGo(@RequestParam String doc_form_idx, HttpServletRequest req){
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		logger.info("결재 문서 작성 이동 컨트롤러");
		logger.info("로그인 아이디: "+loginId);
		logger.info("idx: "+doc_form_idx);
		
		return signservice.signWriteGo(doc_form_idx, loginId);
	}
	
	@PostMapping(value="/sign/write.do")
	public String signWriteDo(List<MultipartFile> files, @RequestParam HashMap<String, String> params, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		String userIP = (String) session.getAttribute("userIP");
		logger.info("결재 문서 작성 컨트롤러");
		logger.info("params: {}",params);
		String[] empIdx_input = req.getParameterValues("empIdx_input");
		String[] ref_empIdx_input = req.getParameterValues("ref_empIdx_input");
		logger.info("결재자 길이: "+empIdx_input.length);
		logger.info("참조자 길이: "+ref_empIdx_input.length);
		logger.info("참조자: "+ref_empIdx_input);
	
		return signservice.signWriteDo(files,params,empIdx_input,ref_empIdx_input,loginId,userIP);
	}
	
	@GetMapping(value="/signDetail.go")
	public ModelAndView signDetailGo(String sign_idx, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		int admin_type = (int) session.getAttribute("power");
		logger.info("결재 문서 상세페이지 컨트롤러");
		logger.info("글 idx: "+sign_idx);
		logger.info("로그인: "+loginId+" 권한: "+admin_type);
		return signservice.signDetailGo(sign_idx, loginId, admin_type);
	}
	
	@GetMapping(value = "/signDetailTest.do")
	public String signDetailTest(String sign_idx, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		logger.info("결재 문서 상세페이지 컨트롤러");
		logger.info("글 idx: " + sign_idx);

		signservice.signDetailTest(sign_idx, loginId);

		return "redirect:/signDetail.go?sign_idx=" + sign_idx;
	}

	@GetMapping(value = "/signDenyDetail.do")
	public String signDenyDetail(String sign_idx, HttpServletRequest req) {
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		logger.info("반려 문서 상세페이지 컨트롤러");
		logger.info("글 idx: " + sign_idx);

		signservice.signDenyDetail(sign_idx, loginId);

		return "redirect:/signDetail.go?sign_idx=" + sign_idx;
	}
	
	@ResponseBody
	@GetMapping(value="/sign.do")
	public HashMap<String, Object> sign(HttpServletRequest req, String sign_idx, String loginId, String comment, String sign_order, String last_order_id) {
		HttpSession session = req.getSession();
		String userIP = (String) session.getAttribute("userIP");
		logger.info("sign_idx: "+sign_idx+" / loginId: "+loginId+" / userIP: "+userIP+" / comment: "+comment+" / sign_order: "+sign_order);
		
		return signservice.sign(sign_idx, loginId, userIP, comment, sign_order, last_order_id);
	}
	
	@GetMapping(value="/download.do")
	public ResponseEntity<Resource> download(String path) {
		logger.info("file name: "+path);
		String filePath = "C:/fransUpload/"+path;
		String oriFileName = signservice.download(path);
	
		Resource resource = new FileSystemResource(filePath);
		HttpHeaders header = new HttpHeaders();
		
		try {
			
			// 한글 파일명은 다운로드 시 깨져서 표현된다.
			// 그래서 한글 깨짐 방지가 필요하다. - URLEncoder(java.net)
			String encodeName = URLEncoder.encode(oriFileName,"UTF-8");
			logger.info("encoded: "+encodeName);
			
			// image/... 은 이미지, text/... 은 문자열, application/octet-stream 은 바이너리
			header.add("Content-type","application/octet-stream");
			// content-Disposition 은 내려보낼 때 문자열(inline)인지 다운로드 받을 파일(attatchment)인지 데이터 종류를 의미한다.
			// fileName="" 형태로 이름을 지정하지 않으면 다운로드가 되지 않는다. 여기에는 원본 파일명이 들어간다.
			header.add("content-Disposition","attatchment;fileName=\""+encodeName+"\"");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource,header,HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping(value="/sign/return.do")
	public HashMap<String, Object> signReturn(@RequestParam HashMap<String, String> params, HttpServletRequest req){
		logger.info("반려 컨트롤러");
		logger.info("params: {}",params);
		HttpSession session = req.getSession();
		String userIP = (String) session.getAttribute("userIP");
		return signservice.signReturn(params, userIP);
	}
	
	@GetMapping(value="/sign/photo.do")
	public ResponseEntity<Resource> showImage(String path, String newFileName) { 
		logger.info("photo name: "+path);
		String filePath = "C:/fransUpload/"+path;
		
		Resource resource = new FileSystemResource(filePath);
		
		// Resource Header (내가 보낼 content 의 타입이 어떤것이다. spring framework 사용) 
		HttpHeaders header = new HttpHeaders();
		try {
			// file 경로로부터 file 의 type 알아냄 -> 어떤 확장자의 파일을 올릴지 모르기 때문
			String type = Files.probeContentType(Paths.get(filePath)); // console : image/jpeg
			logger.info("file type: "+type);
			header.add("Content-type", type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource,header,HttpStatus.OK); // (body, header, 서버에서 잘 받음)
	}
	
	
	
	/* 결재자 문서 리스트 */
	
	// 결재전
	@ResponseBody
	@GetMapping(value="/sign/list_signmem.do")
	public HashMap<String, Object> dateSearch_signmem(HttpServletRequest req, @RequestParam HashMap<String, String> params){
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		logger.info("결재자 문서 컨트롤러");
		String date1 = params.get("date1");
		String date2 = params.get("date2");
		logger.info("시작일: "+date1+"종료일"+date2);

		return signservice.dateSearch_signmem(loginId,date1,date2);
	}
	
	// 결재완료
	@ResponseBody
	@GetMapping(value="/sign/list_endsignmem.do")
	public HashMap<String, Object> dateSearch_endsignmem(HttpServletRequest req, @RequestParam HashMap<String, String> params){
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		logger.info("결재자 문서 컨트롤러");
		String date1 = params.get("date1");
		String date2 = params.get("date2");
		logger.info("시작일: "+date1+"종료일"+date2);

		return signservice.dateSearch_endsignmem(loginId,date1,date2);
	}
	// 반려
	@ResponseBody
	@GetMapping(value="/sign/list_returnsignmem.do")
	public HashMap<String, Object> dateSearch_returnsignmem(HttpServletRequest req, @RequestParam HashMap<String, String> params){
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		logger.info("결재자 문서 컨트롤러");
		String date1 = params.get("date1");
		String date2 = params.get("date2");
		logger.info("시작일: "+date1+"종료일"+date2);

		return signservice.dateSearch_returnsignmem(loginId,date1,date2);
	}
	
	/* 참조자 문서 리스트 */
	
	@ResponseBody
	@GetMapping(value="/sign/list_refermem.do")
	public HashMap<String, Object> dateSearch_refermem(HttpServletRequest req, @RequestParam HashMap<String, String> params){
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		logger.info("참조자 문서 컨트롤러");
		String date1 = params.get("date1");
		String date2 = params.get("date2");
		logger.info("시작일: "+date1+"종료일"+date2);

		return signservice.dateSearch_refermem(loginId,date1,date2);
	}
	
	@ResponseBody
	@GetMapping(value="/sign/endlist_refermem.do")
	public HashMap<String, Object> dateSearch_endrefermem(HttpServletRequest req, @RequestParam HashMap<String, String> params){
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		String date1 = params.get("date1");
		String date2 = params.get("date2");
		return signservice.dateSearch_endrefermem(loginId,date1,date2);
	}
	
	@ResponseBody
	@GetMapping(value="/sign/returnlist_refermem.do")
	public HashMap<String, Object> dateSearch_returnrefermem(HttpServletRequest req, @RequestParam HashMap<String, String> params){
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		String date1 = params.get("date1");
		String date2 = params.get("date2");
		return signservice.dateSearch_returnrefermem(loginId,date1,date2);
	}
	
}





