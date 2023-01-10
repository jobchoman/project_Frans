package com.frans.stock.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@GetMapping(value="/stock/foodList.do")
	public HashMap<String, Object> foodList(@RequestParam HashMap<String, String> params){
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		ArrayList<StockDTO> comStockList = stockservice.comList();
//		map.put("comStockList", comStockList);
		logger.info("params : {}",params);
		String stock_sort_idx = params.get("stock_sort_idx");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StockDTO> foodStockList = stockservice.foodList(stock_sort_idx);
		map.put("data", foodStockList);
		return map;
		
	}
	
	@ResponseBody
	@GetMapping(value="/stock/subList.do")
	public HashMap<String, Object> subList(@RequestParam HashMap<String, String> params){
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		ArrayList<StockDTO> comStockList = stockservice.comList();
//		map.put("comStockList", comStockList);
		logger.info("params : {}",params);
		String stock_sort_idx = params.get("stock_sort_idx");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StockDTO> subStockList = stockservice.subList(stock_sort_idx);
		map.put("data", subStockList);
		return map;
		
	}
	
	@ResponseBody
	@GetMapping(value="/stock/shopSubList.do")
	public HashMap<String, Object> shopSubList(@RequestParam HashMap<String, String> params){
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		ArrayList<StockDTO> comStockList = stockservice.comList();
//		map.put("comStockList", comStockList);
		logger.info("params : {}",params);
		String stock_sort_idx = params.get("stock_sort_idx");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StockDTO> shopSubList = stockservice.shopSubList(stock_sort_idx);
		map.put("data", shopSubList);
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
