package com.frans.stock.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.frans.stock.dto.StockDTO;


@Mapper
public interface StockDAO {

	ArrayList<StockDTO> comList();

	int StockAdd(StockDTO dto);

	void comStockAdd(String stock_idx,String com_stock_amount);

	ArrayList<StockDTO> comStockList(String stock_sort_idx);

//	ArrayList<StockDTO> subList(String stock_sort_idx);

	ArrayList<StockDTO> shopStockList(String stock_sort_idx, String emp_id);

	ArrayList<StockDTO> updateView(HashMap<String, String> params); // 모달

	int updateStock(HashMap<String, String> params);

	int updateComStock(HashMap<String, String> params);

//	void updateStock(HashMap<String, String> params);


}
