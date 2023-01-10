package com.frans.schedule.controller;

import java.sql.Date;
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

import com.frans.schedule.dao.FacilitiesDAO;
import com.frans.schedule.dto.FacilitiesDTO;
import com.frans.schedule.service.FacilitiesService;

@Controller
public class FacilitiesController {

	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired FacilitiesService service;
	@Autowired FacilitiesDAO dao;
	
		// 시설 리스트 (관리자 페이지)
	  @GetMapping(value = "/facilitiesList.do")
	  public String facilitiesList(Model model){
		  
		  ArrayList<FacilitiesDTO> facilitiesList = dao.facilitiesList();
		  
		  model.addAttribute("facilitiesList", facilitiesList);
		  
		  return "facilitiesList";
	  }
	  
	  @GetMapping(value = "/facilitiesWrite.go")
	  public String facilitiesWrite(){

		  return "facilitiesWriteForm";
	  }
	  
	  	// 시설 등록 (관리자 페이지)
	  @PostMapping(value = "/facilitiesWrite.do")
	  public String facilitiesWrite(Model model, @RequestParam HashMap<String, String> params){
		  logger.info("params : "+ params);
		 
		  FacilitiesDTO FacilitiesDTO = new FacilitiesDTO();
		  FacilitiesDTO.setFac_name(params.get("fac_name"));
		  FacilitiesDTO.setFac_map(params.get("fac_map"));
		  FacilitiesDTO.setFac_type(params.get("fac_type"));
		  dao.facilitiesWrite(FacilitiesDTO);
		
		  return "redirect:/facilitiesList.do";
	  }
	  
	  
	  	// 시설 수정 (관리자 페이지)
	  @GetMapping(value = "/facilitiesUpdate.go")
	  public String facilitiesUpdate(String fac_idx,Model model){
		  
		  logger.info("deatail fac_idx : "+ fac_idx);
		  FacilitiesDTO facDetail = dao.facilitiesDetail(fac_idx);
		  
		  model.addAttribute("facDetail", facDetail);
		  
		  return "facilitiesUpdate";
	  }
	  
	  @PostMapping(value = "/facilitiesUdapte.do")
	  public String facilitiesUdapte(FacilitiesDTO FacilitiesDTO) {
		  dao.facilitiesUdapte(FacilitiesDTO);
		  
		  return "redirect:/facilitiesList.do";
		  
	  }
	  
	  
	// 시설 삭제 (관리자 페이지)
	  @GetMapping(value = "/facilitiesDelete.do")
	  public String facilitiesDelete(String fac_idx){
		  logger.info("=====Fac_Delete fac_idx "+ fac_idx);
		  
		  dao.facilitiesDelete(fac_idx);
		  

		  return "redirect:/facilitiesList.do";
	  }
	  
	 
	  @GetMapping(value = "/facilitiesCalender.do")
	  public String facilitiesCalender(){
		  logger.info("시설 일정 페이지");
		  
		  
		return "facilitiesCalender"; 
	  }
	  
	  @GetMapping(value = "/rentWrite.do")
	  public String rentWrite(){
		  logger.info("시설 일정 페이지");
		  
		  
		return "rentWriteForm"; 
	  }
	  
	  
//	@PostMapping(value="/rentWrite.go")
//	public String facilitiesCalenderWrite(Model model, @RequestParam HashMap<String, String> params){
//
//		 FacilitiesDTO FacilitiesDTO = new FacilitiesDTO();
//		 FacilitiesDTO.setRent_pur(params.get("Rent_pur"));
//		 FacilitiesDTO.setRent_date_start(params.get("Rent_date_start"));
//		 FacilitiesDTO.setRent_date_end(params.get("Rent_date_end"));
//		 dao.rentWrite(FacilitiesDTO);
//		 
//		 
//		return "rentWriteForm";
//	}	  
	  
	  
	  
}
