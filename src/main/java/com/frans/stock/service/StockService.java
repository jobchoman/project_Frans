package com.frans.stock.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.frans.stock.dao.StockDAO;
import com.frans.stock.dto.StockDTO;

@Service
public class StockService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired StockDAO stockdao;
	public ArrayList<StockDTO> comList() {
		logger.info("자재 서비스");
		return stockdao.comList();
	}
	public void StockAdd(HashMap<String, String> params) {
		StockDTO dto = new StockDTO();
		dto.setStock_name(params.get("stock_name"));
		dto.setStock_price(params.get("stock_price"));
		dto.setStock_sort_idx(params.get("stock_sort_idx"));
		int row =  stockdao.StockAdd(dto);
		logger.info("row : "+row);
		String stock_idx = dto.getStock_idx();
		logger.info("stock_idx"+stock_idx);
		if(row > 0) {
			String com_stock_amount = params.get("com_stock_amount");
			logger.info("com_stock:"+com_stock_amount);
			stockdao.comStockAdd(stock_idx,com_stock_amount);			
		}
	}
	public ArrayList<StockDTO> foodList(String stock_sort_idx) {
		
		return stockdao.foodList(stock_sort_idx);
	}
	public ArrayList<StockDTO> subList(String stock_sort_idx) {
		
		return stockdao.subList(stock_sort_idx);
	}
	public ArrayList<StockDTO> shopSubList(String stock_sort_idx) {
		
		return stockdao.shopSubList(stock_sort_idx);
	}

	

}
