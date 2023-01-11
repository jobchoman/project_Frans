package com.frans.sub.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubDAO {

	ArrayList<HashMap<String, String>> menuList();

}
