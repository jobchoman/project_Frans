package com.frans.schedule.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("CalenderDTO")
public class CalenderDTO {
	
	private String cal_idx;
	private int emp_id;
	private int emp_state_idx;
	private Date cal_start;
	private Date cal_end; 
	private String cal_team;
	private String cal_content;
	private String emp_state_name; // 직원 상태 명
	private String emp_name; // 직원명
	
	
	
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_state_name() {
		return emp_state_name;
	}
	public void setEmp_state_name(String emp_state_name) {
		this.emp_state_name = emp_state_name;
	}
	public String getCal_idx() {
		return cal_idx;
	}
	public void setCal_idx(String cal_idx) {
		this.cal_idx = cal_idx;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public int getEmp_state_idx() {
		return emp_state_idx;
	}
	public void setEmp_state_idx(int emp_state_idx) {
		this.emp_state_idx = emp_state_idx;
	}
	public Date getCal_start() {
		return cal_start;
	}
	public void setCal_start(Date cal_start) {
		this.cal_start = cal_start;
	}
	public Date getCal_end() {
		return cal_end;
	}
	public void setCal_end(Date cal_end) {
		this.cal_end = cal_end;
	}
	public String getCal_team() {
		return cal_team;
	}
	public void setCal_team(String cal_team) {
		this.cal_team = cal_team;
	}
	public String getCal_content() {
		return cal_content;
	}
	public void setCal_content(String cal_content) {
		this.cal_content = cal_content;
	}
	
	
	
	
	
}
