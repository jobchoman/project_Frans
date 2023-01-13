package com.frans.sign.dto;

import org.apache.ibatis.type.Alias;

@Alias("SignHistoryDTO")
public class SignHistoryDTO {
	
	private String emp_id;
	private String emp_name;
	private String sign_idx;
	private String pos_idx;
	private String pos_name;
	private String sign_mem_ip;
	private String sign_mem_time;
	private String sign_mem_state;
	private String sign_state_idx;
	
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getSign_idx() {
		return sign_idx;
	}
	public void setSign_idx(String sign_idx) {
		this.sign_idx = sign_idx;
	}
	public String getPos_idx() {
		return pos_idx;
	}
	public void setPos_idx(String pos_idx) {
		this.pos_idx = pos_idx;
	}
	public String getPos_name() {
		return pos_name;
	}
	public void setPos_name(String pos_name) {
		this.pos_name = pos_name;
	}
	public String getSign_mem_ip() {
		return sign_mem_ip;
	}
	public void setSign_mem_ip(String sign_mem_ip) {
		this.sign_mem_ip = sign_mem_ip;
	}
	public String getSign_mem_time() {
		return sign_mem_time;
	}
	public void setSign_mem_time(String sign_mem_time) {
		this.sign_mem_time = sign_mem_time;
	}
	public String getSign_mem_state() {
		return sign_mem_state;
	}
	public void setSign_mem_state(String sign_mem_state) {
		this.sign_mem_state = sign_mem_state;
	}
	public String getSign_state_idx() {
		return sign_state_idx;
	}
	public void setSign_state_idx(String sign_state_idx) {
		this.sign_state_idx = sign_state_idx;
	}
	
}
