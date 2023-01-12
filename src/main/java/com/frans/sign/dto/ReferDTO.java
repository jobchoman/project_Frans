package com.frans.sign.dto;

import org.apache.ibatis.type.Alias;

@Alias("ReferDTO")
public class ReferDTO {
	
	private int sign_idx;
	private String emp_id;

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
}
