package com.frans.stock.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frans.stock.dto.StockDTO;
import com.frans.stock.service.StockService;

@Controller
public class StockController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired StockService stockservice;
	
	@ResponseBody
	@GetMapping(value="/stock/list.do")
	public HashMap<String, Object> comStockList(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StockDTO> comStockList = stockservice.comList();
		map.put("data", comStockList);
		
		return map;
	
	}
	
	@ResponseBody
	@GetMapping(value="/stock/comStockList.do")
	public HashMap<String, Object> comStockList(@RequestParam HashMap<String, String> params){
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		ArrayList<StockDTO> comStockList = stockservice.comList();
//		map.put("comStockList", comStockList);
		logger.info("params : {}",params);
		String stock_sort_idx = params.get("stock_sort_idx");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StockDTO> foodStockList = stockservice.comStockList(stock_sort_idx);
		map.put("data", foodStockList);
		return map;
		
	}
	
//	@ResponseBody
//	@GetMapping(value="/stock/subList.do")
//	public HashMap<String, Object> subList(@RequestParam HashMap<String, String> params){
////		HashMap<String, Object> map = new HashMap<String, Object>();
////		ArrayList<StockDTO> comStockList = stockservice.comList();
////		map.put("comStockList", comStockList);
//		logger.info("params : {}",params);
//		String stock_sort_idx = params.get("stock_sort_idx");
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		ArrayList<StockDTO> subStockList = stockservice.subList(stock_sort_idx);
//		map.put("data", subStockList);
//		return map;
//		
//	}
	
	@ResponseBody
	@GetMapping(value="/stock/shopStockList.do")
	public HashMap<String, Object> shopStockList(@RequestParam HashMap<String, String> params,HttpServletRequest req){
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		ArrayList<StockDTO> comStockList = stockservice.comList();
//		map.put("comStockList", comStockList);
		HttpSession session = req.getSession();
		String emp_id = (String) session.getAttribute("loginId");
		logger.info("emp_id : "+emp_id);
		logger.info("params : {}",params);
		String stock_sort_idx = params.get("stock_sort_idx");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StockDTO> shopStockList = stockservice.shopStockList(stock_sort_idx,emp_id);
		map.put("data", shopStockList);
		return map;
		
	}
	
	@GetMapping(value="/stock/add.do")
	public String comStockAdd(@RequestParam HashMap<String, String> params){
		logger.info("params : {}",params);
		stockservice.StockAdd(params);
		
		return "redirect:/stockAdd.go";
		
		
	}
	
	@ResponseBody
	@GetMapping(value="/stock/updateView.do")
	public HashMap<String, Object> updateView(@RequestParam HashMap<String, String> params){
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		ArrayList<StockDTO> comStockList = stockservice.comList();
//		map.put("comStockList", comStockList);
		// 모달
		logger.info("params : {}",params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StockDTO> updateView = stockservice.updateView(params);
		map.put("data", updateView);
		return map;
		
	}
	
	@ResponseBody
	@GetMapping(value="/stock/updateStock.do")
	public String updateStock(@RequestParam HashMap<String, String> params){
		String data = "실패";
		logger.info("params : {}",params);
		HashMap<String, Object> map = stockservice.updateStock(params);
		if(map != null) {
			data = "성공";
		}
		
		return data;

		
		
	}
}
