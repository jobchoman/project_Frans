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
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>
								시설관리 <small>관리자페이지</small>
							</h3>
						</div>
					</div>

					<div class="title_right">
								
						<button onclick="location.href='facilitiesWrite.go'">시설 등록</button>
					</div>


					<div class="col-md-12 col-sm-12  ">
						<div class="x_panel">
							<div class="x_content">
								<div class="table-responsive">
									<table class="table table-striped jambo_table bulk_action">
										<thead>
											<tr class="headings">
												<th class="column-title">시설명</th>
												<th class="column-title">위치</th>
												<th class="column-title">상태</th>
												<th class="column-title">수정</th>
												<th class="column-title">삭제</th>
											</tr>
										</thead>

										<c:forEach items="${facilitiesList}" var="facilitiesList">

											<tbody>
												<tr class="even pointer">
													<td class=" ">${facilitiesList.fac_name}</td>
													<td class=" ">${facilitiesList.fac_map}</td>
													<td class=" ">${facilitiesList.fac_status}</td>
													<td><button onclick="location.href='./facilitiesUpdate.go?fac_idx=${facilitiesList.fac_idx}'">수정</button></td>
													<td><button
															onclick="location.href='./facilitiesDelete.do?fac_idx=${facilitiesList.fac_idx}'">삭제</button></td>
												</tr>

											</tbody>
										</c:forEach>
									</table>
								</div>


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>w
		<!-- /page content -->

		<!-- footer content -->
		<footer><jsp:include page="footer.jsp" /></footer>
		<!-- /footer content -->
	</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>




</script>
</html>