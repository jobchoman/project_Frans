package com.frans.stock.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("menu")
public class MenuDTO {

	private String menu_idx;
	private String menu_name;
	private int menu_price;
	private String menu_recipe;
	private String menu_start;
	private String menu_state;
	
	public String getMenu_idx() {
		return menu_idx;
	}
	public void setMenu_idx(String menu_idx) {
		this.menu_idx = menu_idx;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public int getMenu_price() {
		return menu_price;
	}
	public void setMenu_price(int menu_price) {
		this.menu_price = menu_price;
	}
	public String getMenu_recipe() {
		return menu_recipe;
	}
	public void setMenu_recipe(String menu_recipe) {
		this.menu_recipe = menu_recipe;
	}
	public String getMenu_start() {
		return menu_start;
	}
	public void setMenu_start(String menu_start) {
		this.menu_start = menu_start;
	}
	public String getMenu_state() {
		return menu_state;
	}
	public void setMenu_state(String menu_state) {
		this.menu_state = menu_state;
	}
	
	
	

	
	
	
	
}
