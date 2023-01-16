package com.frans.member.dto;

import java.sql.Date;

public class UserDTO {
	
	private String client_id;
	private String client_pw;
	private String client_name;
	private String client_gender;
	private Date client_birth;
	private String client_phone;
	private String client_address;
	private int client_state;
	
	
	public String getClient_phone() {
		return client_phone;
	}
	public void setClient_phone(String client_phone) {
		this.client_phone = client_phone;
	}
	public String getClient_address() {
		return client_address;
	}
	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getClient_pw() {
		return client_pw;
	}
	public void setClient_pw(String client_pw) {
		this.client_pw = client_pw;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getClient_gender() {
		return client_gender;
	}
	public void setClient_gender(String client_gender) {
		this.client_gender = client_gender;
	}
	public Date getClient_birth() {
		return client_birth;
	}
	public void setClient_birth(Date client_birth) {
		this.client_birth = client_birth;
	}
	public int getClient_state() {
		return client_state;
	}
	public void setClient_state(int client_state) {
		this.client_state = client_state;
	}
}
