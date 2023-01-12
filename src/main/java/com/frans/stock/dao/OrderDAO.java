package com.frans.stock.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.frans.stock.dto.StockDTO;


@Mapper
public interface OrderDAO {

	ArrayList<StockDTO> foodList(String stock_sort_idx, String emp_id);

	void shopOrder(String stock_idx, String order_amount, String shop_idx);

	ArrayList<StockDTO> comOrderList(HashMap<String, String> params);

	ArrayList<StockDTO> FoodOrderDetail(HashMap<String, String> params);

	ArrayList<StockDTO> subOrderDetail(HashMap<String, String> params);

	ArrayList<StockDTO> comOrderListOk(HashMap<String, String> params);

	void orderChk(HashMap<String, String> params);

	void orderStateChange(HashMap<String, String> params);

	void orderComChk(HashMap<String, String> params);

	ArrayList<StockDTO> shopOrderList(String shop_idx, String stock_sort_idx);

	ArrayList<StockDTO> shopFoodOrderDetail(HashMap<String, String> params);

	ArrayList<StockDTO> shopOrderListOk(String shop_idx, String stock_sort_idx);

	ArrayList<StockDTO> shopSubOrderListCall();

	ArrayList<StockDTO> shopSubOrderListOkCall();

	ArrayList<StockDTO> shopSubOrderDetail(HashMap<String, String> params);

	String shopEmp(String emp_id);

	int stockCnt(String stock_idx, String shop_idx);

	void orderInsert(String stock_idx, String shop_idx, String shop_stock_amount);

//	ArrayList<StockDTO> subList(String stock_sort_idx);


}
