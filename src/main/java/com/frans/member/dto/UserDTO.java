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
	private String shop_idx;
	private String sub_idx;
	private String sub_name;
	private Date sub_use_start;
	private Date sub_use_end;
	private int sub_use_state;
	private String sub_use_condition;
	private int sub_price;
	private String sub_state;
	private String shop_name;
	
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public int getSub_price() {
		return sub_price;
	}
	public void setSub_price(int sub_price) {
		this.sub_price = sub_price;
	}
	public String getSub_state() {
		return sub_state;
	}
	public void setSub_state(String sub_state) {
		this.sub_state = sub_state;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public String getShop_idx() {
		return shop_idx;
	}
	public void setShop_idx(String shop_idx) {
		this.shop_idx = shop_idx;
	}
	public String getSub_idx() {
		return sub_idx;
	}
	public void setSub_idx(String sub_idx) {
		this.sub_idx = sub_idx;
	}
	public Date getSub_use_start() {
		return sub_use_start;
	}
	public void setSub_use_start(Date sub_use_start) {
		this.sub_use_start = sub_use_start;
	}
	public Date getSub_use_end() {
		return sub_use_end;
	}
	public void setSub_use_end(Date sub_use_end) {
		this.sub_use_end = sub_use_end;
	}
	public int getSub_use_state() {
		return sub_use_state;
	}
	public void setSub_use_state(int sub_use_state) {
		this.sub_use_state = sub_use_state;
	}
	public String getSub_use_condition() {
		return sub_use_condition;
	}
	public void setSub_use_condition(String sub_use_condition) {
		this.sub_use_condition = sub_use_condition;
	}
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
