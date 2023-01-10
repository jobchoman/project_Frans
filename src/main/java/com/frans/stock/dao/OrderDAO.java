package com.frans.stock.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.frans.stock.dto.StockDTO;


@Mapper
public interface OrderDAO {

	ArrayList<StockDTO> foodList(String stock_sort_idx, String emp_id);

	void shopOrder(String stock_idx, String order_amount, String shop_idx);

	ArrayList<StockDTO> FoodOrderList();

	ArrayList<StockDTO> SubOrderList();

	ArrayList<StockDTO> FoodOrderDetail(HashMap<String, String> params);

	ArrayList<StockDTO> subOrderDetail(HashMap<String, String> params);

	ArrayList<StockDTO> FoodOrderListOk();

	ArrayList<StockDTO> SubOrderListOk();

	void orderChk(HashMap<String, String> params);

	void orderStateChange(HashMap<String, String> params);

	void orderComChk(HashMap<String, String> params);

//	ArrayList<StockDTO> subList(String stock_sort_idx);


}
