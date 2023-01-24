<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	font-size: 8pt;
}

#updateMenu {
	background-color: #2A3F54;
	border-color: #2A3F54;
	font-size: 8pt;
}

.addWrap {
	display: flex;
	flex-direction: column;
	justify-content: flex-start;
	align-items: center;
}

#th {
	font-weight: bold;
	width: 15%;
}

#subdetaildiv{
		display: flex;
		justify-content: center;
   		align-items: center;
   		flex-direction: column;
   		width: 80%;
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
			<div class="right_col addWrap" role="main">
				<div id="subdetaildiv">
				<div style="width:100%"><h3 style="float:left">구독권 정보</h3></div>
				<br/><br/>
				
				<div class="table-responsive">
				<table id="datatable" class="table table-striped table-bordered dataTable no-footer" aria-describedby="datatable_info">
					<thead>
						<tr>
							<td id="th">구독권 이름</td>
							<td colspan="2">${detail.sub_name}</td>
						</tr>
						<c:if test="${detail.sub_sort_type eq '요일권'}">
						<tr>
							<td id="th">횟수</td>
							<td colspan="2">주${detail.sub_num}회</td>
						</tr>
						</c:if>
						<c:if test="${detail.sub_sort_type eq '횟수권'}">
						<tr>
							<td id="th">횟수</td>
							<td colspan="2">${detail.sub_num}회권</td>
						</tr>
						</c:if>
						<tr>
							<td id="th">구독권 가격</td>
							<td colspan="2"><fmt:formatNumber value="${detail.sub_price}" pattern="#,###" />원</td>
						</tr>
						<tr>
							<td id="th">구독권 상태</td>
							<td colspan="2">${detail.sub_state}</td>
						</tr>
						<tr>
							<td id="th">구독권 기간</td>
							<td style="width:10%">${detail.sub_period}개월</td>
							<c:if test="${detail.sub_sort_type eq '요일권'}">
							<td style="color:#7D78FF; font-weight:bold">* ${detail.sub_sort_type}</td>
							</c:if>
							<c:if test="${detail.sub_sort_type eq '횟수권'}">
							<td style="color:#8DB72A; font-weight:bold">* ${detail.sub_sort_type}</td>
							</c:if>
						</tr>
						<tr>
							<td id="th">구독권 메뉴</td>
							<td colspan="2">${detail.sub_menu}</td>
						</tr>
						<tr>
							<td id="th">출시일</td>
							<td colspan="2">${detail.sub_start}</td>
						</tr>
						<tr>
							<td id="th">종료일</td>
							<td colspan="2">${detail.sub_end}</td>
						</tr>
					</thead>
				</table>
				</div>
				<div id="buttons" style="width:100%">
				<c:if test="${sessionScope.team != '점장'}">
				<button type="button" class="btn btn-round btn-secondary" id="updateMenu" style="float:right;" onclick="location.href='/subUpdate?sub_idx=${detail.sub_idx}'">수정</button>
				</c:if>
				<button type="button" class="btn btn-round btn-secondary" id="goToList" style="float:right;" onclick="location.href='/subList'">리스트</button>
				</div>
			</div>
			<!-- /page content -->
			</div>
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