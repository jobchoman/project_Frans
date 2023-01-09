package com.frans.member.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrgChartDAO {

	ArrayList<HashMap<String, String>> searchEmp(String emp_name);

}
