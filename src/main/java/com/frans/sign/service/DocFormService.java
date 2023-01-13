package com.frans.sign.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frans.sign.dao.DocFormDAO;
import com.frans.sign.dto.DocFormDTO;
import com.frans.sign.dto.signDTO;

@Service
public class DocFormService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired DocFormDAO docformdao;

	public HashMap<String, Object> docFormList(String doc_type, String lineup) {
		logger.info("서식 리스트 호출 서비스");
		logger.info("doc_type:{} ",doc_type);
		logger.info("lineup:{} ",lineup);
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<DocFormDTO> docformlist = docformdao.docFormList(doc_type,lineup);
		map.put("data", docformlist);
		return map;
	}

	public HashMap<String, Object> docFormSearch(String keyword) {
		logger.info("서식 검색 서비스");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<DocFormDTO> docformlist = docformdao.docFormSearch(keyword);
		map.put("searchlist", docformlist);
		return map;
	}

	public String docFormWrite(DocFormDTO docFormDTO, RedirectAttributes rAttr) {
		logger.info("서식 작성 서비스");
		
		String page = "redirect:/docFormList.go";
		String msg = "서식이 등록되었습니다.";
		
		
		if(docFormDTO.getDoc_form_content() != null) {
			int success = docformdao.docFormWrite(docFormDTO);
			logger.info("insert: "+success);
			logger.info("doc_form_idx: "+docFormDTO.getDoc_form_idx());
			page = "redirect:/docFormDetail.go?doc_form_idx="+docFormDTO.getDoc_form_idx();
		}else {
			msg = "오류가 발생하였습니다.";
		}
		
		rAttr.addFlashAttribute("msg", msg);
			
		return page;
	}

	public ModelAndView docFormDetail(String doc_form_idx) {
		docformdao.docForm_hit_update(doc_form_idx);
		ModelAndView mav = new ModelAndView("docFormDetail");
		DocFormDTO docformdto = docformdao.docFormDetail(doc_form_idx);
		mav.addObject("docformdto",docformdto);
		
		return mav;
	}

	public int docFormDelete(ArrayList<String> docFormDeleteList) {
		
		logger.info("서식 삭제 서비스");
		int total=0;
	      
	      for(String doc_form_idx : docFormDeleteList) {
	         logger.info("delete: "+doc_form_idx);
	         total +=docformdao.docFormDelete(doc_form_idx);
	         //MypageCommentDeleteBoardTable(board_idx);
	         //route테이블에서 한번 board테이블에서 한번 지워준다
	      }
	      logger.info("삭제 개수: "+total);

	      return total;
	}
	
	

}
