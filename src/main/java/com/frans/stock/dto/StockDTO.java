package com.frans.stock.dto;

import org.apache.ibatis.type.Alias;

@Alias("StockDTO")
public class StockDTO {
	private String stock_idx; 
	private String com_stock_amount;
	private String stock_sort_idx;
	private String stock_name;
	private String stock_price;
	private String shop_idx;
	private String shop_stock_amount;
	private String emp_id;
	private String shop_name;
	private String order_amount;
	private String order_date;
	private String order_send;
	private String order_state;
	
	
	
	
	
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getOrder_send() {
		return order_send;
	}
	public void setOrder_send(String order_send) {
		this.order_send = order_send;
	}
	public String getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(String order_amount) {
		this.order_amount = order_amount;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getStock_idx() {
		return stock_idx;
	}
	public void setStock_idx(String stock_idx) {
		this.stock_idx = stock_idx;
	}
	public String getCom_stock_amount() {
		return com_stock_amount;
	}
	public void setCom_stock_amount(String com_stock_amount) {
		this.com_stock_amount = com_stock_amount;
	}
	public String getStock_sort_idx() {
		return stock_sort_idx;
	}
	public void setStock_sort_idx(String stock_sort_idx) {
		this.stock_sort_idx = stock_sort_idx;
	}
	public String getStock_name() {
		return stock_name;
	}
	public void setStock_name(String stock_name) {
		this.stock_name = stock_name;
	}
	public String getStock_price() {
		return stock_price;
	}
	public void setStock_price(String stock_price) {
		this.stock_price = stock_price;
	}
	public String getShop_idx() {
		return shop_idx;
	}
	public void setShop_idx(String shop_idx) {
		this.shop_idx = shop_idx;
	}
	public String getShop_stock_amount() {
		return shop_stock_amount;
	}
	public void setShop_stock_amount(String shop_stock_amount) {
		this.shop_stock_amount = shop_stock_amount;
	}
	
	
	
}
