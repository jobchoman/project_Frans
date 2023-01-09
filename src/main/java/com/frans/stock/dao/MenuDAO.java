package com.frans.stock.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.frans.stock.dto.MenuDTO;

@Mapper
public interface MenuDAO {

	int menuRegister(MenuDTO mDTO);

	void menuPhotoUpload(String oriFileName, String newFileName, String menu_idx);

	int menuCount();

}
