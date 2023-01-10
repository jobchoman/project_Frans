package com.frans.schedule.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

@Alias("FacilitiesDTO")
public class FacilitiesDTO {

	// 시설
	private String fac_idx;
	private String fac_name;
	private String fac_map;
	private String fac_status;
	private String fac_type;
	// 시설 일정
	private String emp_id;
	private String rent_pur;
	private Date rent_date_start;
	private Date rent_date_end;
	
	
	public String getFac_idx() {
		return fac_idx;
	}
	public void setFac_idx(String fac_idx) {
		this.fac_idx = fac_idx;
	}
	public String getFac_name() {
		return fac_name;
	}
	public void setFac_name(String fac_name) {
		this.fac_name = fac_name;
	}
	public String getFac_map() {
		return fac_map;
	}
	public void setFac_map(String fac_map) {
		this.fac_map = fac_map;
	}
	public String getFac_status() {
		return fac_status;
	}
	public void setFac_status(String fac_status) {
		this.fac_status = fac_status;
	}
	public String getFac_type() {
		return fac_type;
	}
	public void setFac_type(String fac_type) {
		this.fac_type = fac_type;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getRent_pur() {
		return rent_pur;
	}
	public void setRent_pur(String rent_pur) {
		this.rent_pur = rent_pur;
	}
	public Date getRent_date_start() {
		return rent_date_start;
	}
	public void setRent_date_start(Date rent_date_start) {
		this.rent_date_start = rent_date_start;
	}
	public Date getRent_date_end() {
		return rent_date_end;
	}
	public void setRent_date_end(Date rent_date_end) {
		this.rent_date_end = rent_date_end;
	}
	
	
	
	
}
