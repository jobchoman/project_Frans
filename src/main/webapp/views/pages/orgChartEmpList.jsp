<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
   function orgChartAddEmpPopup(){
      var url="addEmpPopup";
      var name="조직도 직원 추가";
      var option="width=400,height=500,left=50,top=50";   
      window.open(url,name,option);
   }
</script>
<jsp:include page="css.jsp" />
<style>
div#datatable_filter {
	float:right;
}


</style>
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
				<div class="title_left">
					<h2>조직도 직원 리스트</h2>
				</div>
				<div class="row">
					<button type="button" class="btn btn-secondary" onclick="orgChartAddEmpPopup()">직원 추가</button>
					<table id="datatable" class="table table-striped table-bordered"
						style="width: 100%">
						<thead>
							<tr>
								<th>이름</th>
								<th>팀</th>
								<th>직급</th>
								<th>상사</th>
								<th>stack</th>
							</tr>
						</thead>
						<tbody>
						
						</tbody>
					</table>
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