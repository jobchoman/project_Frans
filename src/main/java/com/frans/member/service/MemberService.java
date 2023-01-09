package com.frans.member.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.frans.member.dao.MemberDAO;
import com.frans.member.dto.MemberDTO;

@Service
public class MemberService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Value("${file.location}")private String root;
	
	@Autowired MemberDAO mapper;
	@Autowired PasswordEncoder encoder;

	public void join(HashMap<String, String> params, MultipartFile file, MultipartFile file2) {
		MemberDTO dto = new MemberDTO();
		ArrayList<MemberDTO> dto1 = new ArrayList<MemberDTO>();
		dto.setEmp_id(params.get("emp_id"));
		String emp_id = params.get("emp_id");
		String plain_pw = params.get("emp_pw");
		String enc_pw = encoder.encode(plain_pw);
		logger.info("plain pw : "+plain_pw);
		logger.info("emp_id : "+emp_id);
		logger.info("enc_pw : "+enc_pw);
		params.put("emp_pw", enc_pw);
		logger.info("params : {}",params);
		int success = mapper.join(params);
		mapper.join2(params);
		mapper.join3(params);
		mapper.join4(params);
		mapper.join5(params);
		logger.info("join success : "+success);
		if(success>0) {
			Upload(file,emp_id);
			Upload2(file2,emp_id);
		}
	}
	
	private void Upload2(MultipartFile file2, String emp_id) {
		//1. 파일명 추출
		String file_ori = file2.getOriginalFilename();
		logger.info("emp_id:"+emp_id);
		logger.info("file_ori : "+file_ori);
		String ext = file_ori.substring(file_ori.lastIndexOf("."));
		
		//2. 새파일명 생성
		String file_new = System.currentTimeMillis()+ext;
		
		
		//3. 파일 저장
		try {
			//uploadFile 에서 바이트 추출
			byte[] arr = file2.getBytes();
			//저장할 파일 위치 지정
			Path path = Paths.get(root+file_new);
			//파일 쓰기
			Files.write(path, arr);
			mapper.fileUpload2(file_ori,file_new,emp_id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void Upload(MultipartFile file, String emp_id) {
		//1. 파일명 추출
		String file_ori = file.getOriginalFilename();
		logger.info("emp_id:"+emp_id);
		logger.info("file_ori : "+file_ori);
		String ext = file_ori.substring(file_ori.lastIndexOf("."));
		
		//2. 새파일명 생성
		String file_new = System.currentTimeMillis()+ext;
		
		
		//3. 파일 저장
		try {
			//uploadFile 에서 바이트 추출
			byte[] arr = file.getBytes();
			//저장할 파일 위치 지정
			Path path = Paths.get(root+file_new);
			//파일 쓰기
			Files.write(path, arr);
			mapper.fileUpload1(file_ori,file_new,emp_id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<MemberDTO> teamList() {
		return mapper.teamList();
	}
	public ArrayList<MemberDTO> posList() {
		return mapper.posList();
	}
	public ArrayList<MemberDTO> dutyList() {
		return mapper.dutyList();
	}
	public ArrayList<MemberDTO> stateList() {
		return mapper.stateList();
	}

	public ArrayList<MemberDTO> memberList() {
		return mapper.memberList();
	}

	public MemberDTO memberDetail(String emp_id, Model model) {
		return mapper.memberDetail(emp_id);
	}

	public ArrayList<MemberDTO> fileList(String emp_id) {
		return mapper.fileList(emp_id);
	}

	public ArrayList<MemberDTO> fileList1(String emp_id) {
		return mapper.fileList1(emp_id);
	}










	
	

}
