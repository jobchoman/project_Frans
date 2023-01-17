package com.frans.stock.dto;

import org.apache.ibatis.type.Alias;

@Alias("StoreDTO")
public class StoreDTO {
	
	private String shop_idx;
	private String emp_id;
	private String emp_name;
	private String shop_name;
	private String shop_location;
	private int shop_emp_num;
	private int shop_space;
	private String shop_contact;
	private int province_idx;
	private int city_idx;
	
	
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
	public int getShop_emp_num() {
		return shop_emp_num;
	}
	public void setShop_emp_num(int shop_emp_num) {
		this.shop_emp_num = shop_emp_num;
	}
	public int getShop_space() {
		return shop_space;
	}
	public void setShop_space(int shop_space) {
		this.shop_space = shop_space;
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
