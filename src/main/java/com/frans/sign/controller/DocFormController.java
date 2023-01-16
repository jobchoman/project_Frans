package com.frans.sign.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frans.sign.dto.DocFormDTO;
import com.frans.sign.service.DocFormService;

@Controller
public class DocFormController {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired DocFormService docformservice;
	
	@GetMapping(value="/docFormList.go")
	public HashMap<String, Object> docFormListGo(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String loginId = (String) session.getAttribute("loginId");
		logger.info("서식 리스트 go");
		return docformservice.docFormListGo(loginId);
	}
	
	@ResponseBody
	@GetMapping(value="/docForm/list.do")
	public HashMap<String, Object> docFormList(@RequestParam String doc_type, @RequestParam String lineup) {
		logger.info("서식 리스트 호출 컨트롤러");
		//String doc_type = params.get(key)
		logger.info("doc_type:{} ",doc_type);
		logger.info("lineup:{} ",lineup);
		return docformservice.docFormList(doc_type,lineup);
	}
	
	@ResponseBody
	@GetMapping(value="/docForm/search.do")
	public HashMap<String, Object> docFormSearch(HttpServletRequest req){
		String keyword = req.getParameter("keyword");
//		String page = req.getParameter("page");
		logger.info("서식 검색 컨트롤러");
		logger.info("keyword: "+keyword);
		
		return docformservice.docFormSearch(keyword);
	}
	
	
	@PostMapping(value="/docForm/write.do")
	public String docFormWrite(DocFormDTO DocFormDTO, RedirectAttributes rAttr) {
		logger.info("서식 작성 컨트롤러");
		logger.info("서식 이름: "+DocFormDTO.getDoc_form_name());
		logger.info("문서 종류: "+DocFormDTO.getDoc_type_idx());
//		logger.info("작성자: "+DocFormDTO.getEmp_name());
		
		return docformservice.docFormWrite(DocFormDTO, rAttr);
	}
	
	@GetMapping(value="/docFormDetail.go")
	public ModelAndView docFormDetail(String doc_form_idx) {
		logger.info("서식 상세 페이지 컨트롤러");
		logger.info("doc_form_idx: "+doc_form_idx);
		return docformservice.docFormDetail(doc_form_idx);
	}
	
	@ResponseBody
	@GetMapping(value="/docForm/delete.do")
	public HashMap<String, Object> docFormDelete(@RequestParam(value="docFormDeleteList[]") ArrayList<String> docFormDeleteList){
		logger.info("서식 삭제 컨트롤러");
		logger.info("list{}",docFormDeleteList);
		
		int cnt = docformservice.docFormDelete(docFormDeleteList);
//		String msg = docFormDeleteList.size()+"개 요청중";
		String msg = cnt+"개 삭제 완료되었습니다.";
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("msg", msg);
		
		return map;
	}
	
	
}
