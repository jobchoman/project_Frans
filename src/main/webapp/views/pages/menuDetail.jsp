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
<style>
.dataTables_empty {
	display: none;
}

#goToList {
   background-color:#2A3F54;
   border-color:#2A3F54;
   font-size: 8pt;
}

#updateMenu {
   font-size: 8pt;
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
			
				<table id="datatable" class="table table-striped table-bordered dataTable no-footer"
					style="width: 100%" aria-describedby="datatable_info">
					<thead>
						<tr>
							<th colspan="2"><img width="300" src="/photo/${detail.menu_photo}"/></th>
						</tr>
						<tr>
							<th>메뉴 이름</th>
							<td>${detail.menu_name}</td>
						</tr>
						<tr>
							<th>메뉴 가격</th>
							<td>${detail.menu_price}</td>
						</tr>
						<tr>
							<th>메뉴 출시일</th>
							<td>${detail.menu_start}</td>
						</tr>
						<tr>
							<th colspan="2">메뉴 레시피</th>
						</tr>
						<tr>
							<td colspan="2">${detail.menu_recipe}</td>
						</tr>
					</thead>
				</table>
			<button type="button" class="btn btn-round btn-info" id="goToList" onclick="location.href='/menuList'">리스트</button>
			<button type="button" class="btn btn-round btn-secondary" id="updateMenu" onclick="location.href='/menuUpdate?menu_idx=${detail.menu_idx}'">수정하기</button>
			</div>
			<!-- /page content -->

		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>

$('#datatable').DataTable({
	
	lengthChange : false, 
	searching : false,
	ordering : false,
	info : false,
	paging : false
	
});

</script>
</html>