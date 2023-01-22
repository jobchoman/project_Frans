package com.frans.main.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.frans.main.dto.MessageDTO;
import com.frans.stock.dto.MenuDTO;
import com.frans.sub.dto.SubDTO;

@Mapper
public interface MainDAO {

	ArrayList<SubDTO> main_subListNum();

	ArrayList<SubDTO> main_subListDay();

	ArrayList<MenuDTO> menuList();

	ArrayList<MessageDTO> msgListBox(String loginId);

	
}
