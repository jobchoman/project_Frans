package com.frans.member.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

public class MemberDTO {
	
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
	private int file_idx;
	private String file_sort_idx;
	private String file_from;
	private String file_ori;
	private String file_new;
	private String pos_name;
	private int pos_level;
	private int pos_state;
	private String duty_name;
	private int duty_state;
	private String team_name;
	private int team_state;
	private String emp_school_name;
	private String emp_school_name1;
	private String emp_state_name;
	private String emp_career_idx;
	private String emp_career_idx1;
	private Date emp_career_start;
	private Date emp_career_start1;
	private Date emp_career_end;
	private Date emp_career_end1;
	private Date change_date;
	private String changes;
	private String change_reason;
	private String change_division;
	
	public Date getChange_date() {
		return change_date;
	}
	public void setChange_date(Date change_date) {
		this.change_date = change_date;
	}
	public String getChanges() {
		return changes;
	}
	public void setChanges(String changes) {
		this.changes = changes;
	}
	public String getChange_reason() {
		return change_reason;
	}
	public void setChange_reason(String change_reason) {
		this.change_reason = change_reason;
	}
	public String getChange_division() {
		return change_division;
	}
	public void setChange_division(String change_division) {
		this.change_division = change_division;
	}
	public String getEmp_school_name1() {
		return emp_school_name1;
	}
	public void setEmp_school_name1(String emp_school_name1) {
		this.emp_school_name1 = emp_school_name1;
	}
	public Date getEmp_career_start1() {
		return emp_career_start1;
	}
	public void setEmp_career_start1(Date emp_career_start1) {
		this.emp_career_start1 = emp_career_start1;
	}
	public Date getEmp_career_end1() {
		return emp_career_end1;
	}
	public void setEmp_career_end1(Date emp_career_end1) {
		this.emp_career_end1 = emp_career_end1;
	}
	public String getEmp_department1() {
		return emp_department1;
	}
	public void setEmp_department1(String emp_department1) {
		this.emp_department1 = emp_department1;
	}
	public String getEmp_degree1() {
		return emp_degree1;
	}
	public void setEmp_degree1(String emp_degree1) {
		this.emp_degree1 = emp_degree1;
	}
	public String getEmp_career_etc1() {
		return emp_career_etc1;
	}
	public void setEmp_career_etc1(String emp_career_etc1) {
		this.emp_career_etc1 = emp_career_etc1;
	}
	private String emp_department;
	private String emp_department1;
	private String emp_degree;
	private String emp_degree1;
	private String emp_career_etc;
	private String emp_career_etc1;
	private String license_name;
	private Date license_date;
	private int auth_type;
	
	public int getAuth_type() {
		return auth_type;
	}
	public void setAuth_type(int auth_type) {
		this.auth_type = auth_type;
	}
	public String getEmp_career_idx1() {
		return emp_career_idx1;
	}
	public void setEmp_career_idx1(String emp_career_idx1) {
		this.emp_career_idx1 = emp_career_idx1;
	}
	public String getEmp_career_etc() {
		return emp_career_etc;
	}
	public void setEmp_career_etc(String emp_career_etc) {
		this.emp_career_etc = emp_career_etc;
	}
	public String getEmp_career_idx() {
		return emp_career_idx;
	}
	public void setEmp_career_idx(String emp_career_idx) {
		this.emp_career_idx = emp_career_idx;
	}
	public String getEmp_school_name() {
		return emp_school_name;
	}
	public void setEmp_school_name(String emp_school_name) {
		this.emp_school_name = emp_school_name;
	}
	public Date getEmp_career_start() {
		return emp_career_start;
	}
	public void setEmp_career_start(Date emp_career_start) {
		this.emp_career_start = emp_career_start;
	}
	public Date getEmp_career_end() {
		return emp_career_end;
	}
	public void setEmp_career_end(Date emp_career_end) {
		this.emp_career_end = emp_career_end;
	}
	public String getEmp_department() {
		return emp_department;
	}
	public void setEmp_department(String emp_department) {
		this.emp_department = emp_department;
	}
	public String getEmp_degree() {
		return emp_degree;
	}
	public void setEmp_degree(String emp_degree) {
		this.emp_degree = emp_degree;
	}
	public String getLicense_name() {
		return license_name;
	}
	public void setLicense_name(String license_name) {
		this.license_name = license_name;
	}
	public Date getLicense_date() {
		return license_date;
	}
	public void setLicense_date(Date license_date) {
		this.license_date = license_date;
	}
	public String getLicense_place() {
		return license_place;
	}
	public void setLicense_place(String license_place) {
		this.license_place = license_place;
	}
	public String getLicense_result() {
		return license_result;
	}
	public void setLicense_result(String license_result) {
		this.license_result = license_result;
	}
	private String license_place;
	private String license_result;
	
	public String getPos_name() {
		return pos_name;
	}
	public void setPos_name(String pos_name) {
		this.pos_name = pos_name;
	}
	public int getPos_level() {
		return pos_level;
	}
	public void setPos_level(int pos_level) {
		this.pos_level = pos_level;
	}
	public int getPos_state() {
		return pos_state;
	}
	public void setPos_state(int pos_state) {
		this.pos_state = pos_state;
	}
	public String getDuty_name() {
		return duty_name;
	}
	public void setDuty_name(String duty_name) {
		this.duty_name = duty_name;
	}
	public int getDuty_state() {
		return duty_state;
	}
	public void setDuty_state(int duty_state) {
		this.duty_state = duty_state;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public int getTeam_state() {
		return team_state;
	}
	public void setTeam_state(int team_state) {
		this.team_state = team_state;
	}
	public String getEmp_state_name() {
		return emp_state_name;
	}
	public void setEmp_state_name(String emp_state_name) {
		this.emp_state_name = emp_state_name;
	}
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
	public int getFile_idx() {
		return file_idx;
	}
	public void setFile_idx(int file_idx) {
		this.file_idx = file_idx;
	}
	public String getFile_sort_idx() {
		return file_sort_idx;
	}
	public void setFile_sort_idx(String file_sort_idx) {
		this.file_sort_idx = file_sort_idx;
	}
	public String getFile_from() {
		return file_from;
	}
	public void setFile_from(String file_from) {
		this.file_from = file_from;
	}
	public String getFile_ori() {
		return file_ori;
	}
	public void setFile_ori(String file_ori) {
		this.file_ori = file_ori;
	}
	public String getFile_new() {
		return file_new;
	}
	public void setFile_new(String file_new) {
		this.file_new = file_new;
	}

}
