package com.frans.stock.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.frans.stock.dto.StoreDTO;

@Mapper
public interface StoreDAO {

	ArrayList<StoreDTO> storeList();

}
