package com.frans.main.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("MessageDTO")
public class MessageDTO {
	private String msg_idx;
	private String emp_id;
	private String msg_content;
	private String msg_date;
	private String message_state;
	private String message_time;
	private String emp_name;
	private String msg_count;
	
	
	
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getMsg_count() {
		return msg_count;
	}
	public void setMsg_count(String msg_count) {
		this.msg_count = msg_count;
	}
	public String getMsg_idx() {
		return msg_idx;
	}
	public void setMsg_idx(String msg_idx) {
		this.msg_idx = msg_idx;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getMsg_content() {
		return msg_content;
	}
	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}
	public String getMsg_date() {
		return msg_date;
	}
	public void setMsg_date(String msg_date) {
		this.msg_date = msg_date;
	}
	public String getMessage_state() {
		return message_state;
	}
	public void setMessage_state(String message_state) {
		this.message_state = message_state;
	}
	public String getMessage_time() {
		return message_time;
	}
	public void setMessage_time(String message_time) {
		this.message_time = message_time;
	}
	
	
	
}
