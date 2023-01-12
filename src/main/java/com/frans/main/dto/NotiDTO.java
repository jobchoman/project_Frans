package com.frans.main.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("NotiDTO")
public class NotiDTO {
	private String noti_idx;
	private String emp_id;
	private String noti_hour;
	private String noti_type;
	private String noti_pk;
	private String no_check;
	private String no_date;
	private String emp_name;
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
	public String getNoti_pk() {
		return noti_pk;
	}
	public void setNoti_pk(String noti_pk) {
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
