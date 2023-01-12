package com.frans.sub.dto;

import org.apache.ibatis.type.Alias;

@Alias("SubDTO")
public class SubDTO {

	private String sub_idx;
	private int sub_sort_idx;
	private String sub_name;
	private int sub_price;
	private int sub_num;
	private String sub_start;
	private String sub_end;
	private int sub_period;
	private String sub_state;
	
	public String getSub_idx() {
		return sub_idx;
	}
	public void setSub_idx(String sub_idx) {
		this.sub_idx = sub_idx;
	}
	public int getSub_sort_idx() {
		return sub_sort_idx;
	}
	public void setSub_sort_idx(int sub_sort_idx) {
		this.sub_sort_idx = sub_sort_idx;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public int getSub_price() {
		return sub_price;
	}
	public void setSub_price(int sub_price) {
		this.sub_price = sub_price;
	}
	public int getSub_num() {
		return sub_num;
	}
	public void setSub_num(int sub_num) {
		this.sub_num = sub_num;
	}
	public String getSub_start() {
		return sub_start;
	}
	public void setSub_start(String sub_start) {
		this.sub_start = sub_start;
	}
	public String getSub_end() {
		return sub_end;
	}
	public void setSub_end(String sub_end) {
		this.sub_end = sub_end;
	}
	public int getSub_period() {
		return sub_period;
	}
	public void setSub_period(int sub_period) {
		this.sub_period = sub_period;
	}
	public String getSub_state() {
		return sub_state;
	}
	public void setSub_state(String sub_state) {
		this.sub_state = sub_state;
	}

	
	
	
	
	
	
	
	
	
	
}
