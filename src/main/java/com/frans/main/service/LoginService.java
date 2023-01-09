package com.frans.main.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.frans.main.dao.LoginDAO;

@Service
public class LoginService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired LoginDAO mapper;
	@Autowired PasswordEncoder encoder;


	public boolean login(String emp_id, String emp_pw) {
		boolean match = false;
		
		logger.info(emp_id+"/"+emp_pw);
		String enc_pw = mapper.login(emp_id);
		if(enc_pw != null) {
			match = encoder.matches(emp_pw, enc_pw);
		}
		logger.info("match : "+match);
		return match;
	}



	
	

}
