package com.frans.sign.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("signMemDTO")
public class signMemDTO {
	
	private String emp_id;
	private String emp_name;
	private int sign_idx;
	private int sign_mem_order;
	private boolean sign_mem_state;
	private String sign_mem_comment;
	private String sign_mem_ip;
	private Date sign_mem_time;
	
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public int getSign_idx() {
		return sign_idx;
	}
	public void setSign_idx(int sign_idx) {
		this.sign_idx = sign_idx;
	}
	public int getSign_mem_order() {
		return sign_mem_order;
	}
	public void setSign_mem_order(int sign_mem_order) {
		this.sign_mem_order = sign_mem_order;
	}
	public boolean isSign_mem_state() {
		return sign_mem_state;
	}
	public void setSign_mem_state(boolean sign_mem_state) {
		this.sign_mem_state = sign_mem_state;
	}
	public String getSign_mem_comment() {
		return sign_mem_comment;
	}
	public void setSign_mem_comment(String sign_mem_comment) {
		this.sign_mem_comment = sign_mem_comment;
	}
	public String getSign_mem_ip() {
		return sign_mem_ip;
	}
	public void setSign_mem_ip(String sign_mem_ip) {
		this.sign_mem_ip = sign_mem_ip;
	}
	public Date getSign_mem_time() {
		return sign_mem_time;
	}
	public void setSign_mem_time(Date sign_mem_time) {
		this.sign_mem_time = sign_mem_time;
	}
	
}
