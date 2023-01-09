package com.frans.main.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frans.main.service.LoginService;

@Controller
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired LoginService service;
	@Autowired PasswordEncoder encoder;
	
	String hash = "";
	
	@PostMapping(value="/memberLogin.do")
	public String login(String emp_id, String emp_pw,RedirectAttributes rAttr) {
		String page = "redirect:/";
		String msg = "아이디와 비밀번호를 확인하세요";
		if(service.login(emp_id,emp_pw)) {
			page = "index";
			msg = "안녕하세요. "+emp_id+" 님";
		}
		rAttr.addFlashAttribute("msg",msg);
		
		return page;
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {

		session.invalidate();
		return "redirect:/";
	}

}
