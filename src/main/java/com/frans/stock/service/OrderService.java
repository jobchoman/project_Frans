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

import com.frans.stock.dao.OrderDAO;
import com.frans.stock.dao.StockDAO;
import com.frans.stock.dto.StockDTO;

@Service
public class OrderService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired OrderDAO orderdao;

	public ArrayList<StockDTO> foodList(String stock_sort_idx, String emp_id) {
		
		return orderdao.foodList(stock_sort_idx,emp_id);
	}
//	public ArrayList<StockDTO> subList(String stock_sort_idx) {
//		
//		return orderdao.subList(stock_sort_idx);
//	}

	public void shopOrder(String stock_idx, String order_amount, String shop_idx) {
		orderdao.shopOrder(stock_idx,order_amount,shop_idx);
		
	}

	public ArrayList<StockDTO> FoodOrderList() {
		
		return orderdao.FoodOrderList();
	}

	public ArrayList<StockDTO> SubOrderList() {
		
		return orderdao.SubOrderList();
	}

	public ArrayList<StockDTO> FoodOrderDetail(HashMap<String, String> params) {
		logger.info("발주 상세정보 서비스");
		return orderdao.FoodOrderDetail(params);
	}

	public ArrayList<StockDTO> subOrderDetail(HashMap<String, String> params) {
		logger.info("발주 상세정보 서비스");
		return orderdao.subOrderDetail(params);
	}

	public ArrayList<StockDTO> FoodOrderListOk() {
		logger.info("처리완료");
		return orderdao.FoodOrderListOk();
	}

	public ArrayList<StockDTO> SubOrderListOk() {
		logger.info("처리완료");
		return orderdao.SubOrderListOk();
	}

	public void orderChk(HashMap<String, String> params) {
		logger.info("발주처리하기");
		 orderdao.orderChk(params); // 매장 재고 업데이트
		 orderdao.orderStateChange(params); // 발주 상태 변경
		 orderdao.orderComChk(params);	// 본사 재고 업데이트
	}

	

}
