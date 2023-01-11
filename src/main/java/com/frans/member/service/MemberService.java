package com.frans.member.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.frans.sign.dto.DocFormDTO;

@Service
public class MemberService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	@Value("${file.location}")private String root;
	
	@Autowired MemberDAO memberDao;
	@Autowired PasswordEncoder encoder;

	public String login(String emp_id, String emp_pw) {
		boolean match = false;
		
		logger.info(emp_id+"/"+emp_pw);
		String enc_pw = memberDao.login(emp_id);
		if(enc_pw != null) {
			match = encoder.matches(emp_pw, enc_pw);
		}
		logger.info("match : "+match);
		return enc_pw;
	}
	
	
	public void join(HashMap<String, String> params, MultipartFile file, MultipartFile file2) {
		MemberDTO dto = new MemberDTO();
//		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		dto.setEmp_id(params.get("emp_id"));
		String emp_id = params.get("emp_id");
		String plain_pw = params.get("emp_pw");
		String enc_pw = encoder.encode(plain_pw);
		logger.info("plain pw : "+plain_pw);
		logger.info("emp_id : "+emp_id);
		logger.info("enc_pw : "+enc_pw);
		params.put("emp_pw", enc_pw);
		logger.info("params : {}",params);
		int success = memberDao.join(params);
//		memberDao.join2(params);
//		memberDao.join3(params);
		memberDao.join4(params);
		memberDao.join5(params);
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
			memberDao.fileUpload2(file_ori,file_new,emp_id);
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
			memberDao.fileUpload1(file_ori,file_new,emp_id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<MemberDTO> teamList() {
		return memberDao.teamList();
	}
	public ArrayList<MemberDTO> posList() {
		return memberDao.posList();
	}
	public ArrayList<MemberDTO> dutyList() {
		return memberDao.dutyList();
	}
	public ArrayList<MemberDTO> stateList() {
		return memberDao.stateList();
	}

	public ArrayList<MemberDTO> memberList() {
		return memberDao.memberList();
	}

	public MemberDTO memberDetail(String emp_id, Model model) {
		return memberDao.memberDetail(emp_id);
	}

	public ArrayList<MemberDTO> fileList(String emp_id) {
		return memberDao.fileList(emp_id);
	}

	public ArrayList<MemberDTO> fileList1(String emp_id) {
		return memberDao.fileList1(emp_id);
	}

	public void memberJoin1(MemberDTO memberDTO) {
		memberDao.join2(memberDTO);
		memberDao.join3(memberDTO);
		
	}


	public HashMap<String, Object> selList(String sel, String con) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<MemberDTO> selList = memberDao.selList(sel,con);
		map.put("sel", selList);
		return map;
	}










	
	

}
