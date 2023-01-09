<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="/resources/css/common.css" type="text/css">
</head>
<body>
	<h3>LOG IN</h3>
	<form action="memberLogin.do" method="POST">
	<table>
		<tr>
			<th>ID</th>
			<td><input type="text" name="emp_id"/></td>
		</tr>
		<tr>
			<th>PW</th>
			<td><input type="password" name="emp_pw"/></td>
		</tr>
		<tr>
			<th colspan="2">
				<button>LOGIN</button>
				<button type="button" onclick="location.href='memberJoin.go'">회원가입</button>
			</th>
		</tr>
	</table>	
	</form>
</body>
<script>
var msg = "${msg}";

if(msg != ""){
	alert(msg);
}
</script>
</html>