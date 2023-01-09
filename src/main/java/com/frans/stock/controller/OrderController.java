package com.frans.stock.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frans.stock.dto.StockDTO;
import com.frans.stock.service.OrderService;
import com.frans.stock.service.StockService;

@Controller
public class OrderController {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired OrderService orderservice;

	@ResponseBody
	@GetMapping(value="/stock/foodOrderList.do")
	public HashMap<String, Object> foodList(@RequestParam HashMap<String, String> params){
		//		HashMap<String, Object> map = new HashMap<String, Object>();
		//		ArrayList<StockDTO> comStockList = stockservice.comList();
		//		map.put("comStockList", comStockList);
		logger.info("params : {}",params);
		String stock_sort_idx = params.get("stock_sort_idx");
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StockDTO> foodList = orderservice.foodList(stock_sort_idx);
		map.put("data", foodList);
		return map;

	}

	@PostMapping(value="/stock/shopOrder.do")
	public String shopOrder(@RequestParam HashMap<String, String> params, HttpServletRequest req){
		logger.info("params : {}",params);
		String stock_idx[] = req.getParameterValues("stock_idx");
		String order_amount[] = req.getParameterValues("order_amount");
		String shop_idx = params.get("shop_idx");
		logger.info("shop_idx : "+shop_idx);
		for(int i=0; i<stock_idx.length; i++) {
			logger.info("stock_idx : {},{}", stock_idx[i],order_amount[i]);
			if(order_amount[i] != "") {
				orderservice.shopOrder(stock_idx[i],order_amount[i],shop_idx);	
			}
			//logger.info("order_amount : {}", order_amount[i]);		
		}


		return "redirect:/shopStock.go";

	}

	@ResponseBody
	@GetMapping(value="/stock/FoodOrderList.do")
	public HashMap<String, Object> OrderList(@RequestParam HashMap<String, String> params){
		//		HashMap<String, Object> map = new HashMap<String, Object>();
		//		ArrayList<StockDTO> comStockList = stockservice.comList();
		//		map.put("comStockList", comStockList);
		logger.info("본사 식자재 발주 params : {}",params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StockDTO> FoodOrderList = orderservice.FoodOrderList();
		map.put("data", FoodOrderList);
		return map;

	}

	@ResponseBody
	@GetMapping(value="/stock/FoodOrderListOk.do")
	public HashMap<String, Object> FoodOrderListOk(@RequestParam HashMap<String, String> params){
		//		HashMap<String, Object> map = new HashMap<String, Object>();
		//		ArrayList<StockDTO> comStockList = stockservice.comList();
		//		map.put("comStockList", comStockList);
		logger.info("본사 식자재 발주 처리완료 params : {}",params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StockDTO> FoodOrderListOk = orderservice.FoodOrderListOk();
		map.put("data", FoodOrderListOk);
		return map;

	}

	@ResponseBody
	@GetMapping(value="/stock/SubOrderList.do")
	public HashMap<String, Object> SubOrderList(@RequestParam HashMap<String, String> params){
		//		HashMap<String, Object> map = new HashMap<String, Object>();
		//		ArrayList<StockDTO> comStockList = stockservice.comList();
		//		map.put("comStockList", comStockList);
		logger.info("본사 부자재 발주 params : {}",params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StockDTO> SubOrderList = orderservice.SubOrderList();
		map.put("data", SubOrderList);
		return map;

	}

	@ResponseBody
	@GetMapping(value="/stock/SubOrderListOk.do")
	public HashMap<String, Object> SubOrderListOk(@RequestParam HashMap<String, String> params){
		//		HashMap<String, Object> map = new HashMap<String, Object>();
		//		ArrayList<StockDTO> comStockList = stockservice.comList();
		//		map.put("comStockList", comStockList);
		logger.info("본사 부자재 발주 처리완료 params : {}",params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StockDTO> SubOrderListOk = orderservice.SubOrderListOk();
		map.put("data", SubOrderListOk);
		return map;

	}

	@RequestMapping(value="/foodOrderDetail.go")
	public HashMap<String, Object> FoodOrderDetail(@RequestParam HashMap<String, String> params, HttpServletRequest req){
		logger.info("params : {}",params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StockDTO> OrderDetail = orderservice.FoodOrderDetail(params);
		map.put("data", OrderDetail);

		return map;

	}

	@RequestMapping(value="/subOrderDetail.go")
	public HashMap<String, Object> subOrderDetail(@RequestParam HashMap<String, String> params, HttpServletRequest req){
		logger.info("params : {}",params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<StockDTO> subOrderDetail = orderservice.subOrderDetail(params);
		map.put("data", subOrderDetail);

		return map;

	}
	@ResponseBody
	@RequestMapping(value="/order/orderChk.do")
	public String orderChk(@RequestParam HashMap<String, String> params,HttpServletRequest req, Model model){
		String stock_idx = params.get("stock_idx");
		String order_amount = params.get("order_amount");
		String order_date = params.get("order_date");
		String shop_idx = params.get("shop_idx");
		String msg = "실패";
		for(int i=0; i<stock_idx.length(); i++) {
			logger.info("params : {}",params);
			if(order_amount != "0" && order_amount != null) {
				logger.info("발주처리하기");
				orderservice.orderChk(params);
				msg = "성공";
			}
		}
		//		String shop_idx = params.get("shop_idx");
		//		String stock_idx[] = req.getParameterValues("stock_idx");
		//		String order_amount[] = req.getParameterValues("order_amount");
		//		for(int i=0; i<stock_idx.length; i++) {
		//			logger.info("stock_idx : {},{}", stock_idx[i],order_amount[i]);
		//			if(order_amount[i] != "") {
		//				orderservice.shopOrder(stock_idx[i],order_amount[i],shop_idx);	
		//			}
		//			//logger.info("order_amount : {}", order_amount[i]);		
		//		}
//		String page = "/orderList.go";
		//content += '<td><a href= "/foodOrderDetail.go?order_date='+list[i].order_date+'&shop_idx='+list[i].shop_idx+'">' + list[i].order_date + '</a></td>';
		String page = "redirect:/foodOrderDetail.go?order_date="+order_date+"&shop_idx="+shop_idx;
		model.addAttribute("msg",msg);		
//		return "redirect:/revDetail.go?eat_idx="+eat_idx;
		return msg;

	}
}
