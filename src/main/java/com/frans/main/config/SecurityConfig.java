package com.frans.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public PasswordEncoder getpassPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//기본 로그인 기능 사용하지 않음
		//403 : 권한에러 - csrf기능을 비활성화 하면 생기지 않는다.
		//csrf (Cross Site Request Forgery) : 내가 요청하지 않은 내용으로 요청되는 해킹방법(주로 자바스크립트로 이용한 페이지 변조)
		//이걸 막기위해서 csrf토큰을 만들어 서버에서 페이지에 내려주게 된다.
		//그리고 페이지에서 서버에 요청을 보낼 경우 이 토큰값을 보내게된다.
		//그리고 요청 시 보내온 토큰값과 서버가 가지고 있는 토큰값이 일치하는지 검사
		//일치하지 않거나 토큰이 없으면 403에러(그런데 우리는 jsp에 토큰을 발행하는 로직을 넣지않았다.)
		http.httpBasic().disable().csrf().disable();
		
		return http.build();
	}

}
