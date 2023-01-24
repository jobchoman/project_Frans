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
	   float: right;
	}
	
	#updateMenu {
	   font-size: 8pt;
	   background-color:#2A3F54;
	   border-color:#2A3F54;
	   float: right;
	}
	
	#th {
		font-weight: bold;
		width: 10%;
	}
	.addWrap {
	   display: flex;
	   flex-direction: column;
	   justify-content: flex-start;
	   align-items: center;
	}
	#menudiv{
		display: flex;
		justify-content: center;
   		align-items: center;
   		flex-direction: column;
   		width: 80%;
	}

	.col-sm-12 {
		justify-content: center;
		display: flex;
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
			<div id="menudiv" style="white-space: nowrap">
			<div class="table-responsive">
				<table id="datatable" class="table table-striped table-bordered dataTable no-footer" style="width: 80%; text-align: center" aria-describedby="datatable_info">
					<thead>
						<tr>
							<td colspan="2"><img width="300" src="/photo/${detail.menu_photo}"/></td>
						</tr>
						<tr>
							<td id="th">메뉴 이름</td>
							<td>${detail.menu_name}</td>
						</tr>
						<tr>
							<td id="th">메뉴 가격</td>
							<td><fmt:formatNumber value="${detail.menu_price}" pattern="#,###" />원</td>
						</tr>
						<tr>
							<td id="th">메뉴 출시일</td>
							<td>${detail.menu_start}</td>
						</tr>
						<tr>
							<td id="th">메뉴 레시피</td>
							<td>${detail.menu_recipe}</td>
						</tr>
						<tr>
							<td colspan="2">
								<c:if test="${sessionScope.team != '점장'}">
								<button type="button" class="btn btn-round btn-info" id="updateMenu" onclick="location.href='/menuUpdate?menu_idx=${detail.menu_idx}'">수정하기</button>
								</c:if>
								<button type="button" class="btn btn-round btn-secondary" id="goToList" onclick="location.href='/menuList'">리스트</button>
							</td>
						</tr>
					</thead>
					</table>
			</div>
			<!-- /page content -->
		</div>
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