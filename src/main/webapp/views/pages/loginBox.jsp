<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="login"></div>
<script>
	var loginId = "${sessionScope.loginId}";
	var emp_name = "${sessionScope.emp_name}";
	var fileList = "${sessionScope.fileList}";
	var power = "${sessionScope.power}";
	var team = "${sessionScope.team}";
	if(loginId != ""){
		var content = '안녕하세요 '+loginId+' 님 ';
	}else{
		alert("로그인이 필요한 서비스입니다.");
		location.href="/";
	}
</script>