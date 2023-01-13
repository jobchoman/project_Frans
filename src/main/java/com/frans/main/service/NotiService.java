package com.frans.main.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.frans.main.dao.NotiDAO;
import com.frans.stock.dao.OrderDAO;
import com.frans.stock.dao.StockDAO;
import com.frans.stock.dto.StockDTO;

@Service
public class NotiService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired NotiDAO notidao;
	
	public ArrayList<StockDTO> notiList(String emp_id) {
		logger.info("알림 리스트 서비스 아이디 : "+emp_id);
		return notidao.notiList(emp_id);
	}

	public ArrayList<StockDTO> notiListBox(String emp_id) {
		logger.info("알림 수신함 서비스 아이디 : "+emp_id);
		return notidao.notiListBox(emp_id);
	}

	public int notiDelete(String noti_idx, String emp_id) {
		logger.info("알림삭제 서비스 param: {}",noti_idx);
		return notidao.notiDelete(noti_idx,emp_id);
	}

	

}
