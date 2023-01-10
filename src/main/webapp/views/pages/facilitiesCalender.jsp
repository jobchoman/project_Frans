<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<jsp:include page="css.jsp" />
</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col"><jsp:include page="sidebar.jsp" /></div>

			<!-- top navigation -->
			<div class="top_nav"><jsp:include page="top_nav.jsp" /></div>
			<!-- /top navigation -->

			<!-- page content -->
	
			<div class="right_col" role="main">
				
				<h3>시설 일정</h3>
				<button onclick="facilitiesCalender()">시설 일정 등록</button>
				
			</div>
			<!-- /page content -->

			
		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>
loadList();

function facilitiesCalender(){
	chartWin = window.open('rentWrite.do','','width=800, height=600');
}

</script>
</html>