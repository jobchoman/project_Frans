package com.frans.sign.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.frans.member.dto.MemberDTO;
import com.frans.sign.dao.SignDAO;
import com.frans.sign.dto.DocFormDTO;
import com.frans.sign.dto.ReferDTO;
import com.frans.sign.dto.SignHistoryDTO;
import com.frans.sign.dto.fileDTO;
import com.frans.sign.dto.signDTO;
import com.frans.sign.dto.signMemDTO;

@Service
public class SignService {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired SignDAO signdao;
	
	@Value("${file.location}") private String root;

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

	public ModelAndView signWriteGo(String doc_form_idx, String loginId) {
		ModelAndView mav = new ModelAndView("signWrite");
		DocFormDTO docformdto = signdao.signWriteGo(doc_form_idx);
		ArrayList<MemberDTO> memberlist = signdao.memberList();
		MemberDTO memberdto =  signdao.selectEmpName(loginId);
		mav.addObject("docformdto",docformdto);
		mav.addObject("memberlist",memberlist);
		mav.addObject("memberdto",memberdto);
//		mav.addObject("loginId", loginId);
		return mav;
	}

	
	public String signWriteDo(List<MultipartFile> files, HashMap<String, String> params, String[] empIdx_input, String[] ref_empIdx_input, String loginId) {

		signDTO signdto = new signDTO();
		signdto.setDoc_form_idx(params.get("doc_form_idx"));
		signdto.setDoc_type_idx(params.get("doc_type_idx"));
		signdto.setSign_content(params.get("sign_content"));
		signdto.setSign_team_open(params.get("sign_team_open"));
		signdto.setSign_title(params.get("sign_title"));
		signdto.setEmp_id(loginId);
		int success = signdao.signWriteDo(signdto);
		int sign_idx = signdto.getSign_idx();
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
			fileUpload(files,sign_idx);
			
			logger.info("doc_form_idx"+signdto.getDoc_form_idx());
			signdao.docForm_use_update(signdto.getDoc_form_idx());
		}
		
		return "redirect:/signList.go";
	
	}
	

	private void fileUpload(List<MultipartFile> files, int sign_idx) {
		
		for(int i=0; i<files.size(); i++) {
			MultipartFile file = files.get(i);
			String oriFileName = file.getOriginalFilename();
			String ext = oriFileName.substring(oriFileName.lastIndexOf("."));
			String newFileName = System.currentTimeMillis()+i+ext;
				
			try {
					signdao.fileUpload(sign_idx, oriFileName, newFileName);
					byte[] bytes = files.get(i).getBytes();
//					logger.info("root: "+root);
					Path path = Paths.get(root+newFileName);
					Files.write(path, bytes);
			} catch (Exception e) {
					e.printStackTrace();
			}
		}

	}

	public ModelAndView signDetailGo(String sign_idx, String loginId) {
		logger.info("결재 문서 상세페이지 서비스");
		ModelAndView mav = new ModelAndView("signDetail");
		signDTO signdto = signdao.signDetailGo(sign_idx);
		ArrayList<signMemDTO> signmemlist = signdao.signDetailSignmem(sign_idx);
		ArrayList<ReferDTO> referlist = signdao.signDetailRefermem(sign_idx);
		ArrayList<SignHistoryDTO> history = signdao.signHistory(sign_idx);
		String lastOrder = signdao.lastOrder(sign_idx);
		String loginName = signdao.loginName(loginId);
		ArrayList<signMemDTO> signDoMemCnt = signdao.signDoMemCnt(sign_idx);
		ArrayList<fileDTO> fileList = signdao.fileList(sign_idx);
		
		mav.addObject("signdto", signdto);
		mav.addObject("signmemlist", signmemlist);
		mav.addObject("referlist", referlist);
		mav.addObject("history",history);
		mav.addObject("lastOrder", lastOrder);
		mav.addObject("loginName", loginName);
		mav.addObject("signDoMemCnt", signDoMemCnt);
		mav.addObject("fileList",fileList);
		return mav;
	}

	
	public HashMap<String, Object> sign(String sign_idx, String loginId, String userIP, String comment, String sign_order, String last_order_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 결재자 테이블 업데이트
		signMemDTO signmem = new signMemDTO();
		signmem.setSign_mem_comment(comment);
		signmem.setSign_mem_ip(userIP);
		int signMemUpdate =  signdao.signMemUpdate(sign_idx, loginId, userIP, comment);
		map.put("signMemUpdate", signMemUpdate);
		
		logger.info("결재 순서: "+sign_order);
		logger.info("로그인 아이디: "+loginId+" / 마지막 결재자: "+last_order_id);
		
		// 결재 테이블 결재상태 업데이트
				
		if ((sign_order.equals("1")) && (loginId.equals(last_order_id))) {
			logger.info("처음이자 마지막 결재자");
			int signStateUpdate_first = signdao.signStateUpdate_first(sign_idx);
			map.put("signStateUpdate_first", signStateUpdate_first);
		}else if((sign_order.equals("1")) || (loginId.equals(last_order_id))) {
			logger.info(sign_order+"번째 결재자");
			int signStateUpdate = signdao.signStateUpdate(sign_idx, loginId, sign_order, last_order_id);
			map.put("signStateUpdate", signStateUpdate);	
		}
		
		return map;
	}
	
	
	
	
	
	
	
}
