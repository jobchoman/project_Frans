package com.frans.member.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frans.member.dao.MemberDAO;
import com.frans.member.dto.MemberDTO;

@Service
public class MemberService {

	Logger logger = LoggerFactory.getLogger(getClass());
	@Value("${file.location}")private String root;

	@Autowired MemberDAO memberDao;
	@Autowired PasswordEncoder encoder;

	public String login(String emp_id, String emp_pw,RedirectAttributes rAttr, HttpSession session,Model model,HttpServletRequest req) {
		boolean match = false;
		String page = "redirect:/";
		String msg = "아이디와 비밀번호를 확인하세요";
		String loginId = memberDao.loginId(emp_id,emp_pw);
		String userIP = req.getRemoteAddr();
		ArrayList<MemberDTO> fileList = memberDao.fileList(emp_id);
		MemberDTO dto = memberDao.memberDetail(emp_id,model);
		String emp_name = dto.getEmp_name();
		int power = dto.getEmp_admin_auth();
		String team = dto.getTeam_name();
		String enc_pw = memberDao.login(emp_id);
		
		match = encoder.matches(emp_pw, enc_pw);
		logger.info(emp_id+"/"+emp_pw);
		if(match != false) {
			page = "index";
			msg = "안녕하세요. "+emp_id+" 님";
			session.setAttribute("loginId", loginId);
			session.setAttribute("emp_name", emp_name);
			session.setAttribute("fileList", fileList);
			session.setAttribute("power", power);
			session.setAttribute("team", team);
			session.setAttribute("userIP", userIP);
			
		}
		logger.info("match : "+match);
		rAttr.addFlashAttribute("msg",msg);
		
		return page;
	}


	public void join(HashMap<String, String> params, MultipartFile file, MultipartFile file2, HttpServletRequest req) {
		MemberDTO dto = new MemberDTO();
		//		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		dto.setEmp_id(params.get("emp_id"));
		String emp_id = params.get("emp_id");
		String plain_pw = params.get("emp_pw");
		String enc_pw = encoder.encode(plain_pw);
		String emp_career_idx[] = req.getParameterValues("emp_career_idx");
		String emp_school_name[] = req.getParameterValues("emp_school_name");
		String emp_department[] = req.getParameterValues("emp_department");
		String emp_degree[] = req.getParameterValues("emp_degree");
		String emp_career_start[] = req.getParameterValues("emp_career_start");
		String emp_career_end[] = req.getParameterValues("emp_career_end");
		String emp_career_etc[] = req.getParameterValues("emp_career_etc");
		String license_name[] = req.getParameterValues("license_name");
		String license_date[] = req.getParameterValues("license_date");
		String license_place[] = req.getParameterValues("license_place");
		String license_result[] = req.getParameterValues("license_result");
		logger.info("plain pw : "+plain_pw);
		logger.info("emp_id : "+emp_id);
		logger.info("enc_pw : "+enc_pw);
		params.put("emp_pw", enc_pw);
		logger.info("params : {}",params);
		int success = memberDao.join(params);
		// 직원 넣기 성공시 실행
		if(success > 0) {
			for(int i=0; i<emp_career_idx.length; i++) {
				//				if(emp_career_etc[i].equals("") || emp_career_etc[i] == null) {
				//					emp_career_etc[i] = "없음";
				//				}
				if(!emp_career_idx[i].equals("없음") && emp_career_idx[i] != "없음" && emp_career_idx[i] != "" && emp_career_idx[i] != null) {
					if(emp_career_idx[i].equals("고등학교")) {
						memberDao.joinHigh(emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i]);
					}else if(emp_career_idx[i].equals("경력")){
						memberDao.joinCareer(emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i],emp_degree[i]);
						logger.info("경력개수:"+emp_career_idx[i].length());
					}else {
						memberDao.joinCareer(emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i],emp_degree[i]);
						logger.info("경력개수:"+emp_career_idx[i].length());
					}
				}
				logger.info("career idx : {},{},{}",emp_school_name[i],emp_career_idx[i],emp_career_etc[i]);
			}
			for(int j=0; j<license_name.length; j++) {

				if(!license_name[j].equals("")) {
					logger.info("자격증 넣기");
					memberDao.join4(emp_id,license_name[j],license_date[j],license_place[j],license_result[j]);	// 자격증

				}
			}
			memberDao.join5(params);	// 팀권한


		}
		logger.info("join success : "+success);
		if(success>0) {
			Upload(file,emp_id);
			Upload2(file2,emp_id);
		}
		
		String team_name = params.get("team_name");
		String team_idx= params.get("team_idx");
		String pos_name = params.get("pos_name");
		String duty_name = params.get("duty_name");
		logger.info("team_name:{}",team_name);
		logger.info("team_idx:{}",team_idx);
		logger.info("duty_name:{}",duty_name);
		if(team_name != "" && team_name != null) {
			int teamhis = memberDao.teamHis(emp_id,team_name);
		}
		if(pos_name != "" && pos_name != null) {
			int poshis = memberDao.posHis(emp_id,pos_name);
		}
		if(!duty_name.equals("없음") && duty_name != "" && duty_name != null) {
			int dutyhis = memberDao.dutyHis(emp_id,duty_name);
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
		return memberDao.memberDetail(emp_id, model);
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


	public HashMap<String, Object> selList() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<MemberDTO> selList = memberDao.selList();
		map.put("data", selList);
		return map;
	}


	public Object subsubSel(String controll, HashMap<String, String> params) {
		return memberDao.subsubSel(controll,params);
	}


	public HashMap<String, Object> subSelList(String select, String subSelect) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<MemberDTO> subSelList = memberDao.subSelList(select,subSelect);
		map.put("data", subSelList);
		return map;
	}


	public ArrayList<MemberDTO> memberDetailSchool(String emp_id, Model model) {
		return memberDao.memberDetailSchool(emp_id);
	}


	public ArrayList<MemberDTO> memberDetailLicense(String emp_id, Model model) {
		return memberDao.memberDetailLicense(emp_id);
	}

	public ArrayList<MemberDTO> memberDetailRight(String emp_id) {
		return memberDao.memberDetailRight(emp_id);
	}

	//	public void memberUpdate(MemberDTO dto, HttpServletRequest req) {
	//		String emp_id = dto.getEmp_id();
	//		String emp_career_idx[] = req.getParameterValues("emp_career_idx");
	//		String emp_school_name[] = req.getParameterValues("emp_school_name");
	//		String emp_department[] = req.getParameterValues("emp_department");
	//		String emp_degree[] = req.getParameterValues("emp_degree");
	//		String emp_career_start[] = req.getParameterValues("emp_career_start");
	//		String emp_career_end[] = req.getParameterValues("emp_career_end");
	//		String emp_career_etc[] = req.getParameterValues("emp_career_etc");
	//		String license_name[] = req.getParameterValues("license_name");
	//		String license_date[] = req.getParameterValues("license_date");
	//		String license_place[] = req.getParameterValues("license_place");
	//		String license_result[] = req.getParameterValues("license_result");
	//		for(int i=0; i<emp_career_idx.length; i++) {
	//			if(!emp_career_idx[i].equals("없음") && emp_career_idx[i] != "없음" && emp_career_idx[i] != "" && emp_career_idx[i] != null) {
	//				if(emp_career_idx[i].equals("고등학교")) {
	//					memberDao.highUpdate(emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i]);
	//				}else if(emp_career_idx[i].equals("경력")){
	//					memberDao.careerUpdate(emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i],emp_degree[i]);
	//					logger.info("경력개수:"+emp_career_idx[i].length());
	//				}else {
	//					memberDao.careerUpdate(emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i],emp_degree[i]);
	//					logger.info("경력개수:"+emp_career_idx[i].length());
	//				}
	//			}
	//			logger.info("career idx : {},{},{}",emp_school_name[i],emp_career_idx[i],emp_career_etc[i]);
	//		}
	//		for(int j=0; j<license_name.length; j++) {
	//			
	//			if(!license_name[j].equals("")) {
	//				logger.info("자격증 넣기");
	//				memberDao.licenUpdate(emp_id,license_name[j],license_date[j],license_place[j],license_result[j]);	// 자격증
	//				
	//			}
	//		}
	//		String plain_pw = dto.getEmp_pw();
	//		String enc_pw = encoder.encode(plain_pw);
	//		dto.setEmp_pw(enc_pw);
	//		memberDao.memberUpdate(params);
	//		
	//	}

	public void memberUpdateParam(HashMap<String, String> params, HttpServletRequest req) {
		String emp_id = params.get("emp_id");
		String plain_pw = params.get("emp_pw");
		String enc_pw = encoder.encode(plain_pw);
		params.put("emp_pw", enc_pw);
		
		String right_team[] = req.getParameterValues("right_team");
		String right_auth_type[] = req.getParameterValues("auth_type");
		
		String emp_career_idx[] = req.getParameterValues("emp_career_idx");
		String emp_school_name[] = req.getParameterValues("emp_school_name");
		String emp_department[] = req.getParameterValues("emp_department");
		String emp_degree[] = req.getParameterValues("emp_degree");
		String emp_career_start[] = req.getParameterValues("emp_career_start");
		String emp_career_end[] = req.getParameterValues("emp_career_end");
		String emp_career_etc[] = req.getParameterValues("emp_career_etc");
		String license_name[] = req.getParameterValues("license_name");
		String license_date[] = req.getParameterValues("license_date");
		String license_place[] = req.getParameterValues("license_place");
		String license_result[] = req.getParameterValues("license_result");
		logger.info("업데이트 서비스 params : {}",params);
		
		//히스토리 insert
		
				String pos_idx = params.get("pos_idx"); 
				String duty_idx = params.get("duty_idx"); 
				String team_idx = params.get("team_idx"); 
				int empPosIdx = memberDao.empPosIdx(emp_id,pos_idx); 
				if(empPosIdx < 1) { 
					String type = "직급"; 
					String reason = "승진"; 
					String pos_name = memberDao.pos_name(pos_idx);
					memberDao.historyUpdate(emp_id,type,reason,pos_name); 
				} 
				int empDutyIdx = memberDao.empDutyIdx(emp_id,duty_idx); 
				if(empDutyIdx < 1) { 
					String type = "직책"; 
					String reason = "변경"; 
					String duty_name = memberDao.duty_name(duty_idx);
					memberDao.historyUpdate(emp_id,type,reason,duty_name); 
				} 
				int empTeamIdx = memberDao.empTeamIdx(emp_id,team_idx); 
				if(empTeamIdx < 1) { 
					String type = "팀"; 
					String reason = "이동"; 
					String team_name = memberDao.team_name(team_idx);
					memberDao.historyUpdate(emp_id,type,reason,team_name); 
				}
		
		
		int success = memberDao.memberUpdate(params);				
		if(success > 0) {
			
			if(params.get("right_team") != null && params.get("right_team") != "") {
				for(int k=0; k<right_team.length; k++) {
					logger.info("팀: {}, 권한 : {}",right_team[k],right_auth_type[k]);
					memberDao.rightUpdate(emp_id,right_team[k],right_auth_type[k]);
					
				}
				
			}
			
			if(params.get("emp_career_idx") != null && params.get("emp_career_idx") != "") {
				// 이력, 학력 업데이트
				for(int i=0; i<emp_career_idx.length; i++) {
					if(!emp_career_idx[i].equals("없음") && emp_career_idx[i] != "없음" && emp_career_idx[i] != "" && emp_career_idx[i] != null) {
						if(emp_career_idx[i].equals("고등학교")) {
							int career = memberDao.memCareer(emp_id,emp_career_idx[i],emp_school_name[i]);
							if(career > 0) {
								//업데이트
								logger.info("이력학력 업데이트");
								memberDao.highUpdate(emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i]);
								
							}else {
								//insert
								logger.info("이력학력 insert");
								memberDao.joinHigh(emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i]);
							}
						}else if(emp_career_idx[i].equals("경력") || emp_career_idx[i].equals("대학교")|| emp_career_idx[i].equals("대학원")){
							int career = memberDao.memCareer(emp_id,emp_career_idx[i],emp_school_name[i]);
							logger.info("경력개수:"+emp_career_idx[i].length());
							if(career > 0) {
								//업데이트
								logger.info("이력학력 업데이트");
								memberDao.careerUpdate(emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i],emp_degree[i]);
								
							}else {
								//insert
								logger.info("이력학력 insert");
								memberDao.joinCareer(emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i],emp_degree[i]);
								
							}
						}else {
							//memberDao.joinCareer(emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i],emp_degree[i]);
							logger.info("경력개수:"+emp_career_idx[i].length());
							logger.info("else : {},{},{},{},{},{},{},{}",emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i],emp_degree[i]);
						}
						
					}
				}
				
			}
			// 자격증 업데이트
			logger.info("license : "+params.get("license_name"));
			if(params.get("license_name") != null && params.get("license_name") != "") {
				for(int j=0; j<license_name.length; j++) {

					if(!license_name[j].equals("")&&license_name[j] != null &&!license_name[j].equals("없음")&&license_name[j] != "없음") {
						int career = memberDao.memLicense(emp_id,license_name[j],license_date[j],license_place[j],license_result[j]);
						if(career > 0) {
							//업데이트
							logger.info("자격증 업데이트");
							memberDao.licenUpdate(emp_id,license_name[j],license_date[j],license_place[j],license_result[j]);	// 자격증

						}else {
							//insert
							logger.info("자격증 insert");
							memberDao.join4(emp_id,license_name[j],license_date[j],license_place[j],license_result[j]);	// 자격증
						}


					}
				}

			}


		}



	}
	
	public void myPageUpdate(HashMap<String, String> params, HttpServletRequest req) {
		String emp_id = params.get("emp_id");
		
		String emp_career_idx[] = req.getParameterValues("emp_career_idx");
		String emp_school_name[] = req.getParameterValues("emp_school_name");
		String emp_department[] = req.getParameterValues("emp_department");
		String emp_degree[] = req.getParameterValues("emp_degree");
		String emp_career_start[] = req.getParameterValues("emp_career_start");
		String emp_career_end[] = req.getParameterValues("emp_career_end");
		String emp_career_etc[] = req.getParameterValues("emp_career_etc");
		String license_name[] = req.getParameterValues("license_name");
		String license_date[] = req.getParameterValues("license_date");
		String license_place[] = req.getParameterValues("license_place");
		String license_result[] = req.getParameterValues("license_result");
		logger.info("마이페이지 업데이트 서비스 params : {}",params);
		int success = memberDao.myPageUpdate(params);
		if(success > 0) {
			if(params.get("emp_career_idx") != null && !params.get("emp_career_idx").equals("")) {
				// 이력, 학력 업데이트
				for(int i=0; i<emp_career_idx.length; i++) {
					if(!emp_career_idx[i].equals("없음") && emp_career_idx[i] != "없음" && emp_career_idx[i] != "" && emp_career_idx[i] != null) {
						if(emp_career_idx[i].equals("고등학교")) {
							int career = memberDao.memCareer(emp_id,emp_career_idx[i],emp_school_name[i]);
							if(career > 0) {
								//업데이트
								logger.info("이력학력 업데이트");
								memberDao.highUpdate(emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i]);
								
							}else {
								//insert
								logger.info("이력학력 insert");
								memberDao.joinHigh(emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i]);
							}
						}else if(emp_career_idx[i].equals("경력") || emp_career_idx[i].equals("대학교")|| emp_career_idx[i].equals("대학원")){
							int career = memberDao.memCareer(emp_id,emp_career_idx[i],emp_school_name[i]);
							logger.info("경력개수:"+emp_career_idx[i].length());
							if(career > 0) {
								//업데이트
								logger.info("이력학력 업데이트");
								memberDao.careerUpdate(emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i],emp_degree[i]);
								
							}else {
								//insert
								logger.info("이력학력 insert");
								memberDao.joinCareer(emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i],emp_degree[i]);
								
							}
						}else {
							//memberDao.joinCareer(emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i],emp_degree[i]);
							logger.info("경력개수:"+emp_career_idx[i].length());
							logger.info("else : {},{},{},{},{},{},{},{}",emp_career_idx[i],emp_school_name[i],emp_department[i],emp_career_start[i],emp_career_end[i],emp_id,emp_career_etc[i],emp_degree[i]);
						}
						
					}
				}
				
			}
			// 자격증 업데이트
			logger.info("license : "+params.get("license_name"));
			if(params.get("license_name") != null && params.get("license_name") != "") {
				for(int j=0; j<license_name.length; j++) {

					if(!license_name[j].equals("")&&license_name[j] != null &&!license_name[j].equals("없음")&&license_name[j] != "없음") {
						int career = memberDao.memLicense(emp_id,license_name[j],license_date[j],license_place[j],license_result[j]);
						if(career > 0) {
							//업데이트
							logger.info("자격증 업데이트");
							memberDao.licenUpdate(emp_id,license_name[j],license_date[j],license_place[j],license_result[j]);	// 자격증

						}else {
							//insert
							logger.info("자격증 insert");
							memberDao.join4(emp_id,license_name[j],license_date[j],license_place[j],license_result[j]);	// 자격증
						}


					}
				}

			}


		}
		
	}


	public void fileUpdate(MemberDTO dto, MultipartFile file, MultipartFile file2) {
		String emp_id = dto.getEmp_id();
		if(!file.getOriginalFilename().equals("")) {
			Update(file,emp_id);
		}
		if(!file2.getOriginalFilename().equals("")) {
			Update2(file2,emp_id);
		}

	}

	private void Update(MultipartFile file, String emp_id) {
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
			memberDao.fileUpdate1(file_ori,file_new,emp_id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void Update2(MultipartFile file, String emp_id) {
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
			memberDao.fileUpdate2(file_ori,file_new,emp_id);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public void updatePw(HashMap<String, String> params, String emp_id) {
		
		String plain_pw = params.get("emp_pw");
		String enc_pw = encoder.encode(plain_pw);
		params.put("emp_pw", enc_pw);
		String emp_pw = enc_pw;
		logger.info("emp_pw : "+emp_pw);
		memberDao.updatePw(emp_id,emp_pw);
		
	}


	public int resetPw(HashMap<String, String> params) {
		
		String plain_pw = "0000";
		String enc_pw = encoder.encode(plain_pw);
		String emp_pw = enc_pw;
		String emp_id = params.get("emp_id");
		int success = memberDao.resetPw(emp_id,emp_pw);
		
		return success;
	}


	public ArrayList<MemberDTO> memberHisLog(String emp_id) {
		return memberDao.memberHistLog(emp_id);
	}


	public boolean idCheck(String emp_id) {
		String idCheck = memberDao.idCheck(emp_id);
		return idCheck == null ? false : true;
	}


	public HashMap<String, Object> chTeamList(String com,String emp_id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<MemberDTO> dto = null;
		dto = memberDao.chNotTeamList(com,emp_id);
		logger.info("무엇일까요:{}",dto);
//		if(com != null) {
//			dto = memberDao.chTeamList(com,emp_id);
//			logger.info("무엇일까요11:{}",dto);
//		}

		map.put("data", dto);
		return map;
	}
	
	public ArrayList<MemberDTO> rightTeam(String emp_id) {
		logger.info("권한 리스트");
		return memberDao.rightTeam(emp_id);
	}


	
















}
