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

import com.frans.main.dao.NotiDAO;
import com.frans.main.dto.NotiDTO;
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
	@Autowired NotiDAO notidao;
	
	@Value("${file.location}") private String root;
	
	public HashMap<String, Object> signListGo(String loginId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<MemberDTO> memberdto = signdao.selTeam(loginId);
		map.put("memberdto",memberdto);
		
		
		return map;
	}
	

	public HashMap<String, Object> signList(String date1, String date2, String team_value, String loginId, String auth_type) {
		logger.info("결재 문서 리스트 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		/* ArrayList<String> auth_team = signdao.auth_team(loginId); */
		/*
		 * String auth_type = ""; for(int i=0; i<auth_team.size(); i++) {
		 * auth_type=signdao.auth_type(auth_team.get(i),loginId);
		 * logger.info("권한 리스트: "+auth_team); }
		 */
		
		if(date1 == "") {
			ArrayList<signDTO> signdto = signdao.signList(team_value,auth_type);
			map.put("data", signdto);
		}else if(date1 != "") {
			String first_year = date1.substring(0,date1.indexOf("-"));
			String first_month = date1.substring(5,7);
			String first_date = date1.substring(date1.lastIndexOf("-")+1);
			logger.info(first_year+"년"+first_month+"월"+first_date+"일");
			
			String second_year = date2.substring(0,date2.indexOf("-"));
			String second_month = date2.substring(5,7);
			String second_date = date2.substring(date2.lastIndexOf("-")+1);
			logger.info(second_year+"년"+second_month+"월"+second_date+"일");
			
			String startdate = first_year+first_month+first_date;
			String enddate = second_year+second_month+second_date;
			ArrayList<signDTO> signdto = signdao.dateSearch(startdate,enddate,team_value,auth_type);
			map.put("data", signdto);
		}
		
		return map;
	}
	
/*
	public HashMap<String, Object> signEndList(String date1, String date2, String team_value, String loginId) {
		logger.info("결재완료 문서 리스트 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<String> auth_team = signdao.auth_team(loginId);
		String auth_type = "";
		for(int i=0; i<auth_team.size(); i++) {
			auth_type=signdao.auth_type(auth_team.get(i),loginId);
			logger.info("권한 리스트: "+auth_team);
		}
		
		if(date1 == "") {
			ArrayList<signDTO> signdto = signdao.signEndList(team_value,auth_type);
			map.put("data", signdto);
		}else if(date1 != "") {
			String first_year = date1.substring(0,date1.indexOf("-"));
			String first_month = date1.substring(5,7);
			String first_date = date1.substring(date1.lastIndexOf("-")+1);
			logger.info(first_year+"년"+first_month+"월"+first_date+"일");
			
			String second_year = date2.substring(0,date2.indexOf("-"));
			String second_month = date2.substring(5,7);
			String second_date = date2.substring(date2.lastIndexOf("-")+1);
			logger.info(second_year+"년"+second_month+"월"+second_date+"일");
			
			String startdate = first_year+first_month+first_date;
			String enddate = second_year+second_month+second_date;
			ArrayList<signDTO> signdto = signdao.dateSearch_endList(startdate,enddate,team_value,auth_type);
			map.put("data", signdto);
		}
		
		return map;
	}
	
*/
	public HashMap<String, Object> signUserWriteList(String date1, String date2, String team_value, String loginId) {
		logger.info("내 결재 문서 리스트 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();
		/* ArrayList<String> auth_team = signdao.auth_team(loginId); */
		
		/*
		 * String auth_type = ""; for(int i=0; i<auth_team.size(); i++) {
		 * auth_type=signdao.auth_type(auth_team.get(i),loginId);
		 * logger.info("권한 리스트: "+auth_team); }
		 */
		
		if(date1 == "") {
			ArrayList<signDTO> signdto = signdao.signUserWriteList(team_value,loginId);
			map.put("data", signdto);
		}else if(date1 != "") {
			String first_year = date1.substring(0,date1.indexOf("-"));
			String first_month = date1.substring(5,7);
			String first_date = date1.substring(date1.lastIndexOf("-")+1);
			logger.info(first_year+"년"+first_month+"월"+first_date+"일");
			
			String second_year = date2.substring(0,date2.indexOf("-"));
			String second_month = date2.substring(5,7);
			String second_date = date2.substring(date2.lastIndexOf("-")+1);
			logger.info(second_year+"년"+second_month+"월"+second_date+"일");
			
			String startdate = first_year+first_month+first_date;
			String enddate = second_year+second_month+second_date;
			ArrayList<signDTO> signdto = signdao.dateSearch_userList(startdate,enddate,team_value,loginId);
			map.put("data", signdto);
		}
		
		return map;
	}

	
	
/*
	public HashMap<String, Object> dateSearch(String date1, String date2, String team_value) {
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
		ArrayList<signDTO> searchlist = signdao.dateSearch(startdate,enddate,team_value);
		map.put("data", searchlist);
		
		return map;
	}
*/
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

	
	public String signWriteDo(List<MultipartFile> files, HashMap<String, String> params, String[] empIdx_input, String[] ref_empIdx_input, String loginId, String userIP) {

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
		logger.info("첫번째 참조자: "+ref_empIdx_input[0]);
		
		 // 알림 작업
	      String emp_id = loginId;
	      logger.info("noti emp_id : "+emp_id);
	      logger.info("작성된 결재 문서 idx: "+signdto.getSign_idx());
		
		if (success != 0){

			logger.info("doc_form_idx"+signdto.getDoc_form_idx());
			signdao.docForm_use_update(signdto.getDoc_form_idx());
			
			int noti_pk = signdto.getSign_idx();
	         NotiDTO notidto = new NotiDTO();
	         notidto.setEmp_id(emp_id);
	         notidto.setNoti_pk(noti_pk);
	         if(noti_pk != 0) {
	            // notification 테이블 결재 insert
	            logger.info("noti_pk");
	            String noti_type = "결재";
	            notidto.setNoti_type(noti_type);
	            notidao.notiSignInsert(notidto);      
	            String noti_idx = notidto.getNoti_idx();
	            if(ref_empIdx_input != null && ref_empIdx_input[0] != "" && !ref_empIdx_input[0].equals("없음")) {
	               noti_type = "참조";
	               logger.info("첫번째 참조자: "+ref_empIdx_input[0]);
	               notidto.setNoti_type(noti_type);
	               notidao.notiSignInsert(notidto);
	               String refNoti_idx = notidto.getNoti_idx();   
	               // 참조자 알림 수신함 추가
	               for (int i=0; i<ref_empIdx_input.length; i++) {
	                  String ref_empId = ref_empIdx_input[i];
	                  notidao.notiBoxInsert(refNoti_idx,ref_empId);
	               }
	            }
	            //결재자 알림 수신함 추가
	            String notiBox_emp_id = empIdx_input[0];
	            logger.info("notiBox_emp_id : "+notiBox_emp_id);
	            notidao.notiBoxInsert(noti_idx,notiBox_emp_id);
	         }
			
			for (int i=0; i<empIdx_input.length; i++) {
				logger.info("결재자: {}",empIdx_input[i]);
				if (empIdx_input[i] != "") {
					signMemDTO signmemdto = new signMemDTO();
					String signmem = empIdx_input[i];
					int signorder = i+1;
					signmemdto.setEmp_id(signmem);
					signmemdto.setSign_idx(signdto.getSign_idx());
					signmemdto.setSign_mem_order(signorder);
					signmemdto.setSign_mem_ip(userIP);
					signdao.signMember(signmemdto);
				}
			}
			for (int i=0; i<ref_empIdx_input.length; i++) {
				logger.info("참조자: {}",ref_empIdx_input[i]);
				if (ref_empIdx_input[i] != "" && !(ref_empIdx_input[i] == "") && !ref_empIdx_input[0].equals("없음")) {
					ReferDTO referdto = new ReferDTO();
					String refermem = ref_empIdx_input[i];
					referdto.setEmp_id(refermem);
					referdto.setSign_idx(signdto.getSign_idx());
					signdao.refMember(referdto);				
				}
			}

			if(files != null && !files.get(0).getOriginalFilename().equals("")) {
				logger.info("파일 리스트: "+files.size());
				logger.info("파일 리스트: "+files);
				fileUpload(files,sign_idx);
			}
			
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

	public ModelAndView signDetailGo(String sign_idx, String loginId, int admin_type) {
		logger.info("결재 문서 상세페이지 서비스");
		ModelAndView mav = new ModelAndView("signDetail");
		signDTO signdto = signdao.signDetailGo(sign_idx);
		ArrayList<signMemDTO> signmemlist = signdao.signDetailSignmem(sign_idx); // 결재자
		ArrayList<ReferDTO> referlist = signdao.signDetailRefermem(sign_idx); // 참조자
		ArrayList<SignHistoryDTO> history = signdao.signHistory(sign_idx); // 결재 히스토리
		String lastOrder = signdao.lastOrder(sign_idx); // 마지막 결재자
		String loginName = signdao.loginName(loginId); // 유저 이름
		ArrayList<signMemDTO> signDoMemCnt = signdao.signDoMemCnt(sign_idx); // 결재 순서
		ArrayList<fileDTO> sign_fileList = signdao.fileList(sign_idx); // 첨부파일 리스트
		ArrayList<fileDTO> orifileList = signdao.orifileList(sign_idx); // 첨부파일 oriname
		ArrayList<fileDTO> sign_img = signdao.signImgUpdate(sign_idx); // 서명 이미지
		
		
		mav.addObject("signdto", signdto);
		mav.addObject("signmemlist", signmemlist);
		mav.addObject("referlist", referlist);
		mav.addObject("history",history);
		mav.addObject("lastOrder", lastOrder);
		mav.addObject("loginName", loginName);
		mav.addObject("signDoMemCnt", signDoMemCnt);
		mav.addObject("sign_fileList",sign_fileList);
		mav.addObject("orifileList",orifileList);
		mav.addObject("sign_img", sign_img);
		mav.addObject("admin",admin_type);
		
		// 알림 업데이트
	      String emp_id = loginId;
	      String notiSignMemIdx = notidao.notiSignMem(emp_id,sign_idx);
	      String notiRefMemIdx = notidao.notiRefMem(emp_id,sign_idx);
	      String notiSignLastIdx = notidao.notiSignLastIdx(emp_id,sign_idx);
	      logger.info("결재 알림 noti_idx : "+notiSignMemIdx);
	      logger.info("참조 알림 noti_idx : "+notiRefMemIdx);
	      logger.info("결재완료 알림 noti_idx : "+notiSignLastIdx);
	      if(notiSignMemIdx != "" && notiSignMemIdx != null) {
	         notidao.notiBoxUpdate(notiSignMemIdx,emp_id);
	      }else if(notiRefMemIdx != "" && notiRefMemIdx != null){
	         notidao.notiBoxUpdate(notiRefMemIdx,emp_id);
	      }else if(notiSignLastIdx != "" && notiSignLastIdx != null){
              notidao.notiBoxUpdate(notiSignLastIdx,emp_id);
	      }else {
	         logger.info("검색결과 없음");
	      }

		return mav;
	}
	
	
	public ModelAndView signDetailTest(String sign_idx, String loginId) {
	      logger.info("결재 문서 상세페이지 서비스");
	      ModelAndView mav = new ModelAndView("signDetail");
	      signDTO signdto = signdao.signDetailGo(sign_idx);
	      ArrayList<signMemDTO> signmemlist = signdao.signDetailSignmem(sign_idx);
	      ArrayList<ReferDTO> referlist = signdao.signDetailRefermem(sign_idx);
	      ArrayList<SignHistoryDTO> history = signdao.signHistory(sign_idx);
	      String lastOrder = signdao.lastOrder(sign_idx);
	      String loginName = signdao.loginName(loginId);
	      ArrayList<signMemDTO> signDoMemCnt = signdao.signDoMemCnt(sign_idx);
	      ArrayList<fileDTO> sign_fileList = signdao.fileList(sign_idx);
	      
	      // 알림 업데이트
	            String emp_id = loginId;
	            String notiSignMemIdx = notidao.notiSignMemOk(emp_id,sign_idx);
	            String notiRefMemIdx = notidao.notiRefMemOk(emp_id,sign_idx);
	            logger.info("결재 알림 noti_idx : "+notiSignMemIdx);
	            logger.info("참조 알림 noti_idx : "+notiRefMemIdx);
	            if(notiSignMemIdx != "" && notiSignMemIdx != null) {
	               notidao.notiBoxUpdate(notiSignMemIdx,emp_id);
	            }else if(notiRefMemIdx != "" && notiRefMemIdx != null){
	               notidao.notiBoxUpdate(notiRefMemIdx,emp_id);
	            }else {
	               logger.info("검색결과 없음");
	            }
	            mav.addObject("signdto", signdto);
	            mav.addObject("signmemlist", signmemlist);
	            mav.addObject("referlist", referlist);
	            mav.addObject("history",history);
	            mav.addObject("lastOrder", lastOrder);
	            mav.addObject("loginName", loginName);
	            mav.addObject("signDoMemCnt", signDoMemCnt);
	            mav.addObject("sign_fileList",sign_fileList);
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
			
			// 최종결재 알림 바로 보내기(마지막 결재자 제외)
	         logger.info("결재자 한명 전체 알람 보내기");
	         
	         if(signStateUpdate_first >0) {
	             // 원래 결재자 찾아오기
	             String noti_idx = notidao.notiSignMemIdx(sign_idx);
	             String notiEmp = notidao.notiEmpSearch(noti_idx);
	             int noti_pk = Integer.parseInt(sign_idx);
	             ArrayList<String> notiRefList = notidao.notiRefList(sign_idx);
	             //새 알림 idx 만들기
	             NotiDTO notidto = new NotiDTO();
	             String noti_type = "결재완료";
	             notidto.setEmp_id(notiEmp);
	             notidto.setNoti_pk(noti_pk);
	             notidto.setNoti_type(noti_type);
	             
	             notidao.notiSignInsert(notidto);
	             String newNotiIdx = notidto.getNoti_idx();
	             logger.info("new noti_idx : "+notidto.getNoti_idx());
	             notidao.notiSignAllInsert(notiEmp,newNotiIdx);
	             
	             // 참조자 알림
	             logger.info("SignList size : "+notiRefList.size());
	             for(int i=0; i<notiRefList.size(); i++) {
	                logger.info("notiRefList : {}",notiRefList.get(i));
	                notidao.notiSignAllInsert(notiRefList.get(i),newNotiIdx);
	             }
	             
	          }
	         
		}else if((sign_order.equals("1")) || (loginId.equals(last_order_id))) {
			logger.info(sign_order+"번째 결재자");
			int signStateUpdate = signdao.signStateUpdate(sign_idx, loginId, sign_order, last_order_id);
			map.put("signStateUpdate", signStateUpdate);	
			 logger.info("signStateUpdate : "+signStateUpdate);
	         // 결재자 다음 사람 emp_id, noti_idx
	         if(signStateUpdate > 0) {
	            String notiSignNext = signdao.notiSignNext(sign_idx); //emp_id
	            String noti_idx = notidao.notiSignMemIdx(sign_idx);
	            logger.info("결재자 다음 사람 noti_idx :"+noti_idx);
	            
	            
	            if(notiSignNext != null && notiSignNext != "") {
	               notidao.notiBoxInsert(noti_idx, notiSignNext);
	            }
	            if(notiSignNext == null) {
	               logger.info("전체알람 보내기");
	             //for문으로 뽑아내기?
	               
	               ArrayList<String> notiSignList = notidao.notiSignAll(sign_idx);
	               ArrayList<String> notiRefList = notidao.notiRefList(sign_idx);
	               String signMax = signdao.signMax(sign_idx); // order max 구하기
	               String signLast = signdao.signLast(signMax, sign_idx); // 마지막 결재자 구하기
	               String notiEmp = notidao.notiEmpSearch(noti_idx);
	                  int noti_pk = Integer.parseInt(sign_idx);
	                  NotiDTO notidto = new NotiDTO();
	                  String noti_type = "결재완료";
	                  notidto.setEmp_id(signLast);
	                  notidto.setNoti_pk(noti_pk);
	                  notidto.setNoti_type(noti_type);
	                  
	                  notidao.notiSignInsert(notidto);
	                  String newNotiIdx = notidto.getNoti_idx();
	                  logger.info("new noti_idx : "+notidto.getNoti_idx());
	                  if(newNotiIdx != null && newNotiIdx != "") {
	                        notidao.notiSignAllInsert(notiEmp,newNotiIdx);
	                  }
	               
	               logger.info("SignList size : "+notiSignList.size());
	               for(int i=0; i<notiSignList.size(); i++) {
	                  logger.info("notiSignList : {}",notiSignList.get(i));
	                  notidao.notiSignAllInsert(notiSignList.get(i),newNotiIdx);
	               }
	               // 참조자 알림
	               logger.info("SignList size : "+notiRefList.size());
	               for(int i=0; i<notiRefList.size(); i++) {
	                  logger.info("notiRefList : {}",notiRefList.get(i));
	                  notidao.notiSignAllInsert(notiRefList.get(i),newNotiIdx);
	               }
	            }            
	         }
	      }else if((sign_order.equals("2")) ||(sign_order.equals("3")) ||(sign_order.equals("4"))){
	         // 결재자 다음 사람 emp_id, noti_idx
	         
	         String notiSignNext = signdao.notiSignNext(sign_idx); //emp_id
	         String noti_idx = notidao.notiSignMemIdx(sign_idx);
	         logger.info("결재자 다음 사람 noti_idx :"+noti_idx);
	         if(notiSignNext != null && notiSignNext != "") {
	            notidao.notiBoxInsert(noti_idx, notiSignNext);
	         }
	                     
	         
	      }
		
		return map;
	}

	public String download(String path) {
		return signdao.download(path);
	}

	/* 반려 */
	public HashMap<String, Object> signReturn(HashMap<String, String> params, String userIP) {
		logger.info("반려 서비스");
		signMemDTO signmemdto = new signMemDTO();
		String comment = params.get("comment");
		String loginId = params.get("loginId");
		signmemdto.setSign_mem_comment(params.get("comment"));
		signmemdto.setSign_mem_ip(userIP);
		String sign_idx = params.get("sign_idx");
		HashMap<String, Object> map = new HashMap<String, Object>();
		int signreturn = signdao.signReturn(comment,userIP,loginId, sign_idx);
		if (signreturn > 0) {
			signdao.signReturnUpdate(sign_idx);

			// 반려알림

			int noti_pk = Integer.parseInt(sign_idx);
			NotiDTO notidto = new NotiDTO();
			String noti_type = "반려";
			notidto.setEmp_id(loginId);
			notidto.setNoti_pk(noti_pk);
			notidto.setNoti_type(noti_type);

			notidao.notiSignInsert(notidto); // 반려 noti 등록

			String noti_idx = notidto.getNoti_idx();
			logger.info("반려알림 idx : " + noti_idx);

			int signMemCount = signdao.signMemCount(sign_idx); // emp_id
			logger.info("signMemCount : " + signMemCount);

			if (signMemCount == 1) {
				String signEmp = signdao.signEmp(sign_idx);
				notidao.notiBoxInsert(noti_idx, signEmp);
			} else if (signMemCount > 0) {
				ArrayList<String> denyMemList = signdao.denyMemList(sign_idx, loginId);
				ArrayList<String> denyRefMemList = signdao.denyRefMemList(sign_idx);
				logger.info("denyMemList size : " + denyMemList.size());
				String signEmp = signdao.signEmp(sign_idx);
				notidao.notiBoxInsert(noti_idx, signEmp);
				for (int i = 0; i < denyMemList.size(); i++) {
					logger.info("denyMemList : {}", denyMemList.get(i));
					notidao.notiBoxInsert(noti_idx, denyMemList.get(i));
				}
				for (int j = 0; j < denyRefMemList.size(); j++) {
					logger.info("denyRefMemList : {}", denyRefMemList.get(j));
					notidao.notiBoxInsert(noti_idx, denyRefMemList.get(j));
				}
			}
		}
		map.put("signreturn", signreturn);
		return map;
	}
	
	public ModelAndView signDenyDetail(String sign_idx, String loginId) {
        logger.info("반려 문서 상세페이지 서비스");
        ModelAndView mav = new ModelAndView("signDetail");
        signDTO signdto = signdao.signDetailGo(sign_idx);
        ArrayList<signMemDTO> signmemlist = signdao.signDetailSignmem(sign_idx);
        ArrayList<ReferDTO> referlist = signdao.signDetailRefermem(sign_idx);
        ArrayList<SignHistoryDTO> history = signdao.signHistory(sign_idx);
        String lastOrder = signdao.lastOrder(sign_idx);
        String loginName = signdao.loginName(loginId);
        ArrayList<signMemDTO> signDoMemCnt = signdao.signDoMemCnt(sign_idx);
        ArrayList<fileDTO> sign_fileList = signdao.fileList(sign_idx);
        
        // 알림 업데이트
              String emp_id = loginId;
              String notiSignMemDeny = notidao.notiSignMemDeny(emp_id,sign_idx);
              String notiRefMemDeny = notidao.notiRefMemDeny(emp_id,sign_idx);
              logger.info("결재 반려 알림 noti_idx : "+notiSignMemDeny);
              logger.info("참조 반려 알림 noti_idx : "+notiRefMemDeny);
              if(notiSignMemDeny != "" && notiSignMemDeny != null) {
                 notidao.notiBoxUpdate(notiSignMemDeny,emp_id);
              }else if(notiRefMemDeny != "" && notiRefMemDeny != null){
                 notidao.notiBoxUpdate(notiRefMemDeny,emp_id);
              }else {
                 logger.info("검색결과 없음");
              }
              mav.addObject("signdto", signdto);
              mav.addObject("signmemlist", signmemlist);
              mav.addObject("referlist", referlist);
              mav.addObject("history",history);
              mav.addObject("lastOrder", lastOrder);
              mav.addObject("loginName", loginName);
              mav.addObject("signDoMemCnt", signDoMemCnt);
              mav.addObject("sign_fileList",sign_fileList);
              return mav;
        
     }

	/* 결재자 문서 리스트 */
	
	// 결재전
	public HashMap<String, Object> dateSearch_signmem(String loginId, String date1, String date2) {
		logger.info("결재자 문서 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();

		if(date1 == "") {
			ArrayList<signDTO> signlist = signdao.signList_signmem(loginId);
			map.put("data",signlist);
		}else if(date1 != "") {
			String first_year = date1.substring(0, date1.indexOf("-"));
			String first_month = date1.substring(5, 7);
			String first_date = date1.substring(date1.lastIndexOf("-") + 1);
			logger.info(first_year + "년" + first_month + "월" + first_date + "일");
	
			String second_year = date2.substring(0, date2.indexOf("-"));
			String second_month = date2.substring(5, 7);
			String second_date = date2.substring(date2.lastIndexOf("-") + 1);
			logger.info(second_year + "년" + second_month + "월" + second_date + "일");
	
			String startdate = first_year + first_month + first_date;
			String enddate = second_year + second_month + second_date;
			ArrayList<signDTO> signdto = signdao.dateSearch_signmem(loginId, startdate, enddate);
			map.put("data", signdto);
		}
		return map;
	}

	// 결재완료
	public HashMap<String, Object> dateSearch_endsignmem(String loginId, String date1, String date2) {
		logger.info("결재자 문서 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();

		if(date1 == "") {
			ArrayList<signDTO> signlist = signdao.signList_endsignmem(loginId);
			map.put("data",signlist);
		}else if(date1 != "") {
			String first_year = date1.substring(0, date1.indexOf("-"));
			String first_month = date1.substring(5, 7);
			String first_date = date1.substring(date1.lastIndexOf("-") + 1);
			logger.info(first_year + "년" + first_month + "월" + first_date + "일");
	
			String second_year = date2.substring(0, date2.indexOf("-"));
			String second_month = date2.substring(5, 7);
			String second_date = date2.substring(date2.lastIndexOf("-") + 1);
			logger.info(second_year + "년" + second_month + "월" + second_date + "일");
	
			String startdate = first_year + first_month + first_date;
			String enddate = second_year + second_month + second_date;
			ArrayList<signDTO> signdto = signdao.dateSearch_endsignmem(loginId, startdate, enddate);
			map.put("data", signdto);
		}
		return map;
	}

	// 반려
	public HashMap<String, Object> dateSearch_returnsignmem(String loginId, String date1, String date2) {
		logger.info("결재자 문서 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();

		if(date1 == "") {
			ArrayList<signDTO> signlist = signdao.signList_returnsignmem(loginId);
			map.put("data",signlist);
		}else if(date1 != "") {
			String first_year = date1.substring(0, date1.indexOf("-"));
			String first_month = date1.substring(5, 7);
			String first_date = date1.substring(date1.lastIndexOf("-") + 1);
			logger.info(first_year + "년" + first_month + "월" + first_date + "일");
	
			String second_year = date2.substring(0, date2.indexOf("-"));
			String second_month = date2.substring(5, 7);
			String second_date = date2.substring(date2.lastIndexOf("-") + 1);
			logger.info(second_year + "년" + second_month + "월" + second_date + "일");
	
			String startdate = first_year + first_month + first_date;
			String enddate = second_year + second_month + second_date;
			ArrayList<signDTO> signdto = signdao.dateSearch_returnsignmem(loginId, startdate, enddate);
			map.put("data", signdto);
		}
		return map;
	}



	public HashMap<String, Object> dateSearch_refermem(String loginId, String date1, String date2) {
		logger.info("참조자 문서 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();

		if(date1 == "") {
			ArrayList<signDTO> refermemlist = signdao.signList_refermem(loginId);
			map.put("data",refermemlist);
		}else if(date1 != "") {
			String first_year = date1.substring(0, date1.indexOf("-"));
			String first_month = date1.substring(5, 7);
			String first_date = date1.substring(date1.lastIndexOf("-") + 1);
			logger.info(first_year + "년" + first_month + "월" + first_date + "일");
	
			String second_year = date2.substring(0, date2.indexOf("-"));
			String second_month = date2.substring(5, 7);
			String second_date = date2.substring(date2.lastIndexOf("-") + 1);
			logger.info(second_year + "년" + second_month + "월" + second_date + "일");
	
			String startdate = first_year + first_month + first_date;
			String enddate = second_year + second_month + second_date;
			ArrayList<signDTO> referdto = signdao.dateSearch_refermem(loginId, startdate, enddate);
			map.put("data", referdto);
		}
		return map;
	}


	public HashMap<String, Object> dateSearch_endrefermem(String loginId, String date1, String date2) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		if(date1 == "") {
			ArrayList<signDTO> refermemlist = signdao.signList_endrefermem(loginId);
			map.put("data",refermemlist);
		}else if(date1 != "") {
			String first_year = date1.substring(0, date1.indexOf("-"));
			String first_month = date1.substring(5, 7);
			String first_date = date1.substring(date1.lastIndexOf("-") + 1);
			logger.info(first_year + "년" + first_month + "월" + first_date + "일");
	
			String second_year = date2.substring(0, date2.indexOf("-"));
			String second_month = date2.substring(5, 7);
			String second_date = date2.substring(date2.lastIndexOf("-") + 1);
			logger.info(second_year + "년" + second_month + "월" + second_date + "일");
	
			String startdate = first_year + first_month + first_date;
			String enddate = second_year + second_month + second_date;
			ArrayList<signDTO> referdto = signdao.dateSearch_endrefermem(loginId, startdate, enddate);
			map.put("data", referdto);
		}
		return map;
	}


	public HashMap<String, Object> dateSearch_returnrefermem(String loginId, String date1, String date2) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		if(date1 == "") {
			ArrayList<signDTO> refermemlist = signdao.signList_returnrefermem(loginId);
			map.put("data",refermemlist);
		}else if(date1 != "") {
			String first_year = date1.substring(0, date1.indexOf("-"));
			String first_month = date1.substring(5, 7);
			String first_date = date1.substring(date1.lastIndexOf("-") + 1);
			logger.info(first_year + "년" + first_month + "월" + first_date + "일");
	
			String second_year = date2.substring(0, date2.indexOf("-"));
			String second_month = date2.substring(5, 7);
			String second_date = date2.substring(date2.lastIndexOf("-") + 1);
			logger.info(second_year + "년" + second_month + "월" + second_date + "일");
	
			String startdate = first_year + first_month + first_date;
			String enddate = second_year + second_month + second_date;
			ArrayList<signDTO> referdto = signdao.dateSearch_returnrefermem(loginId, startdate, enddate);
			map.put("data", referdto);
		}
		return map;
	}




	
}
