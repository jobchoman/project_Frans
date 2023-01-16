package com.frans.main.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("NotiDTO")
public class NotiDTO {
	private String noti_idx;
	private String emp_id;
	private String noti_hour;
	private String noti_type;
	private int noti_pk;
	private String no_check;
	private String no_date;
	private String emp_name;
	private String noti_count;
	private int sign_idx;
	private int sign_mem_order;
	private boolean sign_mem_state;
	
	
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
	public String getNoti_count() {
		return noti_count;
	}
	public void setNoti_count(String noti_count) {
		this.noti_count = noti_count;
	}
	public String getNoti_idx() {
		return noti_idx;
	}
	public void setNoti_idx(String noti_idx) {
		this.noti_idx = noti_idx;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getNoti_hour() {
		return noti_hour;
	}
	public void setNoti_hour(String noti_hour) {
		this.noti_hour = noti_hour;
	}
	public String getNoti_type() {
		return noti_type;
	}
	public void setNoti_type(String noti_type) {
		this.noti_type = noti_type;
	}
	public int getNoti_pk() {
		return noti_pk;
	}
	public void setNoti_pk(int noti_pk) {
		this.noti_pk = noti_pk;
	}
	public String getNo_check() {
		return no_check;
	}
	public void setNo_check(String no_check) {
		this.no_check = no_check;
	}
	public String getNo_date() {
		return no_date;
	}
	public void setNo_date(String no_date) {
		this.no_date = no_date;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	
	
}
