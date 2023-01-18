package com.frans.stock.dto;

import org.apache.ibatis.type.Alias;

@Alias("StoreDTO")
public class StoreDTO {
	
	private String shop_idx;
	private String emp_id;
	private String emp_name;
	private String shop_name;
	private String shop_location;
	private String shop_emp_num;
	private String shop_space;
	private String shop_contact;
	private int province_idx;
	private int city_idx;
	private String shop_lat;
	private String shop_lon;
	
	

	public String getShop_lat() {
		return shop_lat;
	}
	public void setShop_lat(String shop_lat) {
		this.shop_lat = shop_lat;
	}
	public String getShop_lon() {
		return shop_lon;
	}
	public void setShop_lon(String shop_lon) {
		this.shop_lon = shop_lon;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getShop_idx() {
		return shop_idx;
	}
	public void setShop_idx(String shop_idx) {
		this.shop_idx = shop_idx;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShop_location() {
		return shop_location;
	}
	public void setShop_location(String shop_location) {
		this.shop_location = shop_location;
	}
	public String getShop_emp_num() {
		return shop_emp_num;
	}
	public void setShop_emp_num(String shop_emp_num) {
		this.shop_emp_num = shop_emp_num;
	}
	public String getShop_space() {
		return shop_space;
	}
	public void setShop_space(String string) {
		this.shop_space = string;
	}
	public String getShop_contact() {
		return shop_contact;
	}
	public void setShop_contact(String shop_contact) {
		this.shop_contact = shop_contact;
	}
	public int getProvince_idx() {
		return province_idx;
	}
	public void setProvince_idx(int province_idx) {
		this.province_idx = province_idx;
	}
	public int getCity_idx() {
		return city_idx;
	}
	public void setCity_idx(int city_idx) {
		this.city_idx = city_idx;
	}
}
