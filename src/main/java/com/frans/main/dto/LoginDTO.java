package com.frans.main.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

public class LoginDTO {
	
	private String emp_id;
	private String emp_pw;
	private String emp_name;
	private String emp_gender;
	private Date emp_birth;
	private String emp_address;
	private String emp_phone;
	private String emp_email;
	private Date emp_hire_date;
	private int emp_admin_auth;
	private String pos_idx;
	private String duty_idx;
	private String team_idx;
	private int emp_state_idx;
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_pw() {
		return emp_pw;
	}
	public void setEmp_pw(String emp_pw) {
		this.emp_pw = emp_pw;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_gender() {
		return emp_gender;
	}
	public void setEmp_gender(String emp_gender) {
		this.emp_gender = emp_gender;
	}
	public Date getEmp_birth() {
		return emp_birth;
	}
	public void setEmp_birth(Date emp_birth) {
		this.emp_birth = emp_birth;
	}
	public String getEmp_address() {
		return emp_address;
	}
	public void setEmp_address(String emp_address) {
		this.emp_address = emp_address;
	}
	public String getEmp_phone() {
		return emp_phone;
	}
	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}
	public String getEmp_email() {
		return emp_email;
	}
	public void setEmp_email(String emp_email) {
		this.emp_email = emp_email;
	}
	public Date getEmp_hire_date() {
		return emp_hire_date;
	}
	public void setEmp_hire_date(Date emp_hire_date) {
		this.emp_hire_date = emp_hire_date;
	}
	public int getEmp_admin_auth() {
		return emp_admin_auth;
	}
	public void setEmp_admin_auth(int emp_admin_auth) {
		this.emp_admin_auth = emp_admin_auth;
	}
	public String getPos_idx() {
		return pos_idx;
	}
	public void setPos_idx(String pos_idx) {
		this.pos_idx = pos_idx;
	}
	public String getDuty_idx() {
		return duty_idx;
	}
	public void setDuty_idx(String duty_idx) {
		this.duty_idx = duty_idx;
	}
	public String getTeam_idx() {
		return team_idx;
	}
	public void setTeam_idx(String team_idx) {
		this.team_idx = team_idx;
	}
	public int getEmp_state_idx() {
		return emp_state_idx;
	}
	public void setEmp_state_idx(int emp_state_idx) {
		this.emp_state_idx = emp_state_idx;
	}


}
