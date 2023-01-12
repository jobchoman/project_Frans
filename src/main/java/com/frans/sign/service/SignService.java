package com.frans.sign.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.frans.member.dto.MemberDTO;
import com.frans.sign.dao.SignDAO;
import com.frans.sign.dto.DocFormDTO;
import com.frans.sign.dto.ReferDTO;
import com.frans.sign.dto.signDTO;
import com.frans.sign.dto.signMemDTO;

@Service
public class SignService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired SignDAO signdao;

	public HashMap<String, Object> signList() {
		logger.info("결재 문서 리스트 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<signDTO> signdto = signdao.signList();
		map.put("signdto", signdto);
		return map;
	}

	public HashMap<String, Object> dateSearch(String date1, String date2) {
		logger.info("결재 문서 기간 검색 서비스");
//		logger.info(date1+"/"+date2);
		
		String first_month = date1.substring(0,date1.indexOf("/"));
		String first_date = date1.substring(3,5);
		String first_year = date1.substring(date1.lastIndexOf("/")+1);
		logger.info(first_year+"년"+first_month+"월"+first_date+"일");
		
		String second_month = date2.substring(0,date2.indexOf("/"));
		String second_date = date2.substring(3,5);
		String second_year = date2.substring(date2.lastIndexOf("/")+1);
		logger.info(second_year+"년"+second_month+"월"+second_date+"일");
		
		String startdate = first_year+first_month+first_date;
		String enddate = second_year+second_month+second_date;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<signDTO> searchlist = signdao.dateSearch(startdate,enddate);
		map.put("searchlist", searchlist);
		
		return map;
	}

	public ModelAndView signWriteGo(String doc_form_idx) {
		ModelAndView mav = new ModelAndView("signWrite");
		DocFormDTO docformdto = signdao.signWriteGo(doc_form_idx);
		ArrayList<MemberDTO> memberlist = signdao.memberList();
		mav.addObject("docformdto",docformdto);
		mav.addObject("memberlist",memberlist);
		return mav;
	}

	
	public String signWriteDo(HashMap<String, String> params, String[] empIdx_input, String[] ref_empIdx_input) {

		signDTO signdto = new signDTO();
		signdto.setDoc_form_idx(params.get("doc_form_idx"));
		signdto.setDoc_type_idx(params.get("doc_type_idx"));
		signdto.setSign_content(params.get("sign_content"));
		signdto.setSign_team_open(params.get("sign_team_open"));
		signdto.setSign_title(params.get("sign_title"));
		int success = signdao.signWriteDo(signdto);
		logger.info("작성된 결재 문서 idx: "+signdto.getSign_idx());
		
		if (success != 0){
			for (int i=0; i<empIdx_input.length; i++) {
				logger.info("결재자: {}",empIdx_input[i]);
				if (empIdx_input[i] != "") {
					signMemDTO signmemdto = new signMemDTO();
					String signmem = empIdx_input[i];
					int signorder = i+1;
					signmemdto.setEmp_id(signmem);
					signmemdto.setSign_idx(signdto.getSign_idx());
					signmemdto.setSign_mem_order(signorder);
					signdao.signMember(signmemdto);
				}
			}
			for (int i=0; i<ref_empIdx_input.length; i++) {
				logger.info("참조자: {}",ref_empIdx_input[i]);
				if (ref_empIdx_input[i] != "") {
					ReferDTO referdto = new ReferDTO();
					String refermem = ref_empIdx_input[i];
					referdto.setEmp_id(refermem);
					referdto.setSign_idx(signdto.getSign_idx());
					signdao.refMember(referdto);				
				}
			}
			logger.info("doc_form_idx"+signdto.getDoc_form_idx());
			signdao.docForm_use_update(signdto.getDoc_form_idx());
		}
		
		return "redirect:/signList.go";
	
	}
	

	public ModelAndView signDetailGo(String sign_idx) {
		logger.info("결재 문서 상세페이지 서비스");
		ModelAndView mav = new ModelAndView("signDetail");
		signDTO signdto = new signDTO();
		signMemDTO signmemdto = new signMemDTO();
		
		return null;
	}
}
