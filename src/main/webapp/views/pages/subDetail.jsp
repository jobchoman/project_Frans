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
							<th>구독권 이름</th>
							<td colspan="2">${detail.sub_name}</td>
						</tr>
						<c:if test="${detail.sub_sort_type eq '요일권'}">
						<tr>
							<th>횟수</th>
							<td colspan="2">주${detail.sub_num}회</td>
						</tr>
						</c:if>
						<c:if test="${detail.sub_sort_type eq '횟수권'}">
						<tr>
							<th>횟수</th>
							<td colspan="2">${detail.sub_num}회권</td>
						</tr>
						</c:if>
						<tr>
							<th>구독권 가격</th>
							<td colspan="2">${detail.sub_price}</td>
						</tr>
						<tr>
							<th>구독권 상태</th>
							<td colspan="2">${detail.sub_state}</td>
						</tr>
						<tr>
							<th>구독권 기간</th>
							<td>${detail.sub_period}개월</td>
							<td>${detail.sub_sort_type}</td>
						</tr>
						<tr>
							<th>구독권 메뉴</th>
							<td colspan="2">${detail.sub_menu}</td>
						</tr>
						<tr>
							<th>출시일</th>
							<td colspan="2">${detail.sub_start}</td>
						</tr>
						<tr>
							<th>종료일</th>
							<td colspan="2">${detail.sub_end}</td>
						</tr>
					</thead>
				</table>
			<button type="button" class="btn btn-round btn-info" id="goToList" onclick="location.href='/subList'">리스트</button>
			<button type="button" class="btn btn-round btn-secondary" id="updateMenu" onclick="location.href='/subUpdate?sub_idx=${detail.sub_idx}'">수정하기</button>
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