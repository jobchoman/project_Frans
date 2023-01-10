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


				<div class="x_content">
					<form action="facilitiesUdapte.do" method="POST">
					<table class="table">
						
						<tr>
							<th>시설명</th>
							<td>								
								<input type="hidden" name="fac_idx" value="${facDetail.fac_idx}"/>
								<input type="text" name="fac_name" value="${facDetail.fac_name}" />
							</td>
						</tr>
						<tr>
							<th>위치</th>
							<td><input type="text" name="fac_map" value="${facDetail.fac_map}" /></td>
						</tr>
						<tr>
							<th>시설 상태</th>
							<td><input type="text" name="fac_status" value="${facDetail.fac_status}" /></td>
						</tr>
						<tr>
							<th>시설 종류</th>
							<td><input type="text" name="fac_type" value="${facDetail.fac_type}"/></td>
						</tr>
							<tr>
							<td colspan="2"><input type="button"
								onclick="location.href='./facilitiesList.do'" value="시설 관리 리스트" />
								<button>저장</button></td>
						</tr>
						
					</table>
					</form>
				</div>

				
				


			</div>
			<!-- /page content -->


		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>
</script>
</html>