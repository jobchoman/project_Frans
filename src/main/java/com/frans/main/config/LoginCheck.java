package com.frans.main.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//@service 도 애매하고 @mapper도 애매한 경우
//그렇다고 빈 등록을 안하면 @Autowired가 안되고
@Component
public class LoginCheck implements HandlerInterceptor {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	//컨트롤러 가기전에 이 메서드를 실행
	//반환값이 true면 컨트롤러로 진입 가능
	//반환값이 false면 진입되지 않는다.(하얀 화면)
	//그래서 false인 경우 response 갹채룰 이용해 redirect 한다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean pass = false;
		logger.info("request URI:"+request.getRequestURI());
		logger.info("===LOGIN CHECK===");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginId") != null) {
			pass = true;  //1. session 에서 loginId이 있으면 pass = true
		}else {  //2. 아니면 pass = false이므로 reponse를 활용해 "/"로 보내게 된다.
			response.sendRedirect("/");
		}
		
		return pass;
	}
	
	//컨트롤러를 지나 뷰에 도달하기 전에 실행되는 메서드
	//ModelAndView 객체를 통해 원하는 값을 뷰에 전달 가능
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView mav) throws Exception {
		logger.info("===POST HANDLER===");
		String userId = (String) request.getSession().getAttribute("loginId");
		String content = "<div>안녕하세요 "+userId+" 님, <a href='/logout.do'>로그아웃</a></div>";
		mav.addObject("loginBox",content);
		

	}

	
	

}
