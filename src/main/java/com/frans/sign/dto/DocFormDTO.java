package com.frans.sign.dto;

import org.apache.ibatis.type.Alias;

@Alias("DocFormDTO")
public class DocFormDTO {
	
	private int doc_form_idx;
	private String emp_id;
	private String emp_name;
	private String doc_type_idx;
	private String doc_form_content;
	private String doc_form_name;
	private int doc_form_upHit;
	private int doc_form_use;
	private String doc_type_name;
	private String team_name;
	
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getDoc_type_name() {
		return doc_type_name;
	}
	public void setDoc_type_name(String doc_type_name) {
		this.doc_type_name = doc_type_name;
	}
	public int getDoc_form_idx() {
		return doc_form_idx;
	}
	public void setDoc_form_idx(int doc_form_idx) {
		this.doc_form_idx = doc_form_idx;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getDoc_type_idx() {
		return doc_type_idx;
	}
	public void setDoc_type_idx(String doc_type_idx) {
		this.doc_type_idx = doc_type_idx;
	}
	public String getDoc_form_content() {
		return doc_form_content;
	}
	public void setDoc_form_content(String doc_form_content) {
		this.doc_form_content = doc_form_content;
	}
	public String getDoc_form_name() {
		return doc_form_name;
	}
	public void setDoc_form_name(String doc_form_name) {
		this.doc_form_name = doc_form_name;
	}
	public int getDoc_form_upHit() {
		return doc_form_upHit;
	}
	public void setDoc_form_upHit(int doc_form_upHit) {
		this.doc_form_upHit = doc_form_upHit;
	}
	public int getDoc_form_use() {
		return doc_form_use;
	}
	public void setDoc_form_use(int doc_form_use) {
		this.doc_form_use = doc_form_use;
	}
	
}
