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
	.addWrap {
	   display: flex;
	   flex-direction: column;
	   justify-content: flex-start;
	   align-items: center;
	}
	
	.row > .col-sm-6:first-child {
		display: none;
	}
	
	#datatable_info {
		display: none;
	}
	
	#registShop {
		background-color:#2A3F54;
		border-color:#2A3F54;
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
			<div class="right_col addWrap" role="main">
				<div class="col-md-12 col-sm-12 ">
					<div class="x_panel">
						<div class="x_title">
							<h2>매장 리스트</h2>
							<button type="button" class="btn btn-round btn-info" id="registShop" onclick="location.href='/storeWrite.go'" style="float:right">매장 등록</button>
							<div class="clearfix"></div>
						</div>
						<div class="x_content">
							<div class="row">
								<div class="col-sm-12">
									<div class="card-box table-responsive">
										<table id="datatable" class="table table-striped table-bordered" style="width: 100%">
											<thead>
												<tr>
													<th>매장명</th>
													<th>점장</th>
													<th>연락처</th>
												</tr>
											</thead>


											<tbody>
												<c:forEach items="${storelist}" var="store">
													<tr>
														<td><a href="/storeDetail.go?shop_idx='${store.shop_idx}'">${store.shop_name}</a></td>
														<td>${store.emp_name}</td>
														<td>${store.shop_contact}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
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