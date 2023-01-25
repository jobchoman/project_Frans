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

import com.frans.main.dao.NotiDAO;
import com.frans.main.dto.NotiDTO;
import com.frans.stock.dao.OrderDAO;
import com.frans.stock.dao.StockDAO;
import com.frans.stock.dto.StockDTO;

@Service
public class OrderService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired OrderDAO orderdao;
	@Autowired NotiDAO notidao;

	public ArrayList<StockDTO> foodList(String stock_sort_idx, String emp_id) {
		
		return orderdao.foodList(stock_sort_idx,emp_id);
	}
//	public ArrayList<StockDTO> subList(String stock_sort_idx) {
//		
//		return orderdao.subList(stock_sort_idx);
//	}

	public void shopOrder(String stock_idx, String order_amount, String shop_idx) {
		/*
		 * NotiDTO notidto = new NotiDTO(); String noti_type = "발주"; String shopOwner =
		 * orderdao.shopOwner(shop_idx); if(shopOwner != null && shopOwner != "") { int
		 * noti_pk = Integer.parseInt(shop_idx); notidto.setEmp_id(shopOwner);
		 * notidto.setNoti_pk(noti_pk); notidto.setNoti_type(noti_type); // 알림 noti 추가 }
		 * String noti_idx = notidto.getNoti_idx(); logger.info("발주 noti : "+noti_idx);
		 * String signEmp = "19910923"; if(noti_idx != null && noti_idx != "") {
		 * notidao.notiBoxInsert(noti_idx, signEmp); }
		 */
		orderdao.shopOrder(stock_idx,order_amount,shop_idx);	
		
	}

	public ArrayList<StockDTO> comOrderList(HashMap<String, String> params) {
		
		return orderdao.comOrderList(params);
	}

	public ArrayList<StockDTO> FoodOrderDetail(HashMap<String, String> params) {
		logger.info("발주 상세정보 서비스");
		return orderdao.FoodOrderDetail(params);
	}

	public ArrayList<StockDTO> subOrderDetail(HashMap<String, String> params) {
		logger.info("발주 상세정보 서비스");
		return orderdao.subOrderDetail(params);
	}

	public ArrayList<StockDTO> comOrderListOk(HashMap<String, String> params) {
		logger.info("처리완료");
		return orderdao.comOrderListOk(params);
	}

	public void orderChk(HashMap<String, String> params) {
		logger.info("발주처리하기");
		String stock_idx = params.get("stock_idx");
		String shop_idx = params.get("shop_idx");
		String shop_stock_amount = params.get("order_amount");
		int stockCnt = orderdao.stockCnt(stock_idx,shop_idx);
		if(stockCnt > 0) {
			orderdao.orderChk(params); // 매장 재고 업데이트			
		}else {
			orderdao.orderInsert(stock_idx,shop_idx,shop_stock_amount);
		}
		 orderdao.orderStateChange(params); // 발주 상태 변경
		 orderdao.orderComChk(params);	// 본사 재고 업데이트
	}

	public ArrayList<StockDTO> shopOrderList(String shop_idx, String stock_sort_idx) {
		
		return orderdao.shopOrderList(shop_idx,stock_sort_idx);
	}

	public ArrayList<StockDTO> shopFoodOrderDetail(HashMap<String, String> params) {
		
		return orderdao.shopFoodOrderDetail(params);
	}

	public ArrayList<StockDTO> shopOrderListOk(String shop_idx, String stock_sort_idx) {
		
		return orderdao.shopOrderListOk(shop_idx,stock_sort_idx);
	}

	public ArrayList<StockDTO> shopSubOrderListCall() {

		return orderdao.shopSubOrderListCall();
	}

	public ArrayList<StockDTO> shopSubOrderListOkCall() {
		
		return orderdao.shopSubOrderListOkCall();
	}

	public ArrayList<StockDTO> shopSubOrderDetail(HashMap<String, String> params) {
		
		return orderdao.shopSubOrderDetail(params);
	}

	public String shopEmp(String emp_id) {
		
		return orderdao.shopEmp(emp_id);
	}

	

}
