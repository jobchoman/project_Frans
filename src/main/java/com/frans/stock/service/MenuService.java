package com.frans.stock.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.frans.stock.dao.MenuDAO;
import com.frans.stock.dto.MenuDTO;

@Service
public class MenuService {
	
	@Value("${file.location}") private String root;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired MenuDAO mDAO;

	public void menuRegister(MenuDTO mDTO) {
		int row = mDAO.menuRegister(mDTO);
		logger.info(row+"개 메뉴 등록 완료!");
	}

	public void menuPhotoUpload(MultipartFile uploadFile, String menu_idx) {
		
		// 1. 파일명 추출
		String oriFileName = uploadFile.getOriginalFilename();
		logger.info("oriFileName : "+oriFileName);
		String ext = oriFileName.substring(oriFileName.lastIndexOf("."));
		// 2. 새 파일명 생성
		String newFileName = System.currentTimeMillis()+ext;
		
		mDAO.menuPhotoUpload(oriFileName, newFileName, menu_idx);
		
		// 파일 저장
		try {
			// uploadFile 에서 바이트 추출
			byte[] arr = uploadFile.getBytes();
			// 저장할 파일
			Path path = Paths.get(root+newFileName);
			// 파일 쓰기
			Files.write(path, arr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int menuCount() {
		int cnt = mDAO.menuCount();
		return cnt;
	}
	
}
