package com.frans.sign.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("signDTO")
public class signDTO {
	
	private int sign_idx;
	private String emp_id;
	private String sign_mem_ip;
	private int sign_state_idx;
	private String doc_type_idx;
	private String sign_content;
	private String sign_team_open;
	private Date sign_date;
	private String sign_title;
	private String doc_type_name;
	private String doc_form_idx;
	private String doc_form_name;
	private String emp_name;
	private String team_idx;
	private String sign_state_type;
	private String team_name;
	private String sign_mem_state;
	
	
	public String getSign_mem_state() {
		return sign_mem_state;
	}
	public void setSign_mem_state(String sign_mem_state) {
		this.sign_mem_state = sign_mem_state;
	}
	public String getSign_mem_ip() {
		return sign_mem_ip;
	}
	public void setSign_mem_ip(String sign_mem_ip) {
		this.sign_mem_ip = sign_mem_ip;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public String getDoc_form_idx() {
		return doc_form_idx;
	}
	public void setDoc_form_idx(String doc_form_idx) {
		this.doc_form_idx = doc_form_idx;
	}
	public String getSign_state_type() {
		return sign_state_type;
	}
	public void setSign_state_type(String sign_state_type) {
		this.sign_state_type = sign_state_type;
	}
	public String getDoc_type_name() {
		return doc_type_name;
	}
	public void setDoc_type_name(String doc_type_name) {
		this.doc_type_name = doc_type_name;
	}
	public String getDoc_form_name() {
		return doc_form_name;
	}
	public void setDoc_form_name(String doc_form_name) {
		this.doc_form_name = doc_form_name;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getTeam_idx() {
		return team_idx;
	}
	public void setTeam_idx(String team_idx) {
		this.team_idx = team_idx;
	}
	public int getSign_idx() {
		return sign_idx;
	}
	public void setSign_idx(int sign_idx) {
		this.sign_idx = sign_idx;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public int getSign_state_idx() {
		return sign_state_idx;
	}
	public void setSign_state_idx(int sign_state_idx) {
		this.sign_state_idx = sign_state_idx;
	}
	public String getDoc_type_idx() {
		return doc_type_idx;
	}
	public void setDoc_type_idx(String doc_type_idx) {
		this.doc_type_idx = doc_type_idx;
	}
	public String getSign_content() {
		return sign_content;
	}
	public void setSign_content(String sign_content) {
		this.sign_content = sign_content;
	}
	public String getSign_team_open() {
		return sign_team_open;
	}
	public void setSign_team_open(String sign_team_open) {
		this.sign_team_open = sign_team_open;
	}
	public Date getSign_date() {
		return sign_date;
	}
	public void setSign_date(Date sign_date) {
		this.sign_date = sign_date;
	}
	public String getSign_title() {
		return sign_title;
	}
	public void setSign_title(String sign_title) {
		this.sign_title = sign_title;
	}

}
