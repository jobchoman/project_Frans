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
	.subtable {
		border: 1px solid #EAEAEA;
		display: inline-table;
		background-color: #EAEAEA;
	}
	
	.x_panel {
		text-align: center;
   		white-space: nowrap;
	}
	.col-md-8 {
		flex: 0 0 60%;
	    max-width: 60%;
	}
	
	.x_content {
		display: flex;
		justify-content: center;
	}
	
	#msg {
		float: right;
	    justify-content: right;
	    display: flex;
	}
	
	#daytable {
		margin-left: 3%;
	}
	
	img {
		width:120px;
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
				<br /> <br /> <br /> <br />
				<div class="row">

					<div class="col-md-8 col-sm-8 ">
						<div class="row">

							<!-- 신메뉴 -->
							<div class="col-md-12 col-sm-12 ">
								<div class="x_panel">
									<div class="x_title">
										<h2>이번달 구독권</h2>
										<div class="clearfix"></div>
									</div>
									<div class="x_content">
										<div class="col-md-4 hidden-small subtable" id="numtable">
											<h6 class="line_30" style="font-weight: bold">횟수권</h6>

											<table class="countries_list">
												<thead>
													<tr>
														<th>이름</th>
														<th>가격</th>
														<th>출시일</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${sublistnum}" var="num">
														<tr>
															<td><a href="subDetail?sub_idx=${num.sub_idx}">${num.sub_name}</a></td>
															<td><fmt:formatNumber value="${num.sub_price}"
																	pattern="#,###" />원</td>
															<td>${num.sub_start}</td>
														</tr>
													</c:forEach>

												</tbody>
											</table>
										</div>

										<div class="col-md-4 hidden-small subtable" id="daytable">
											<h6 class="line_30" style="font-weight: bold">요일권</h6>

											<table class="countries_list">
												<thead>
													<tr>
														<th>이름</th>
														<th>가격</th>
														<th>출시일</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${sublistday}" var="day">
														<tr>
															<td><a href="subDetail?sub_idx=${day.sub_idx}">${day.sub_name}</a></td>
															<td><fmt:formatNumber value="${day.sub_price}"
																	pattern="#,###" />원</td>
															<td>${day.sub_start}</td>
														</tr>
													</c:forEach>

												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>

						</div>
						<!-- /신메뉴 -->

						<!-- 새 구독권 -->
						<div class="row">

							<div class="col-md-12 col-sm-12 ">
								<div class="x_panel">
									<div class="x_title">
										<h2>이번달 신메뉴</h2>
										<div class="clearfix"></div>
									</div>
									<div class="x_content">
										<div class="dashboard-widget-content">
											<div class="col-md-4 hidden-small">
												<h6 class="line_30" style="font-weight: bold"></h6>

												<table class="countries_list">
													<tbody>
														<c:forEach items="${menulist}" var="menu">
															<tr>
																<td><img src="/photo/${menu.menu_photo}" /></td>
																<td style="font-weight: bold">${menu.menu_name}</td>
																<td><fmt:formatNumber value="${menu.menu_price}"
																		pattern="#,###" />원</td>
																<td>${menu.menu_start}</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
											<div id="world-map-gdp" class="col-md-8 col-sm-12 "
												style="height: 230px;"></div>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>
					<!-- /새 구독권 -->

					<div class="col-md-4 col-sm-4 " id="msg">
						<div class="x_panel">
							<div class="x_title">
								<h2>쪽지함</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<div class="dashboard-widget-content" style="width: 100%;">
									<table class="countries_list">
										<thead>
											<tr>
												<th>보낸사람</th>
												<th>받은시간</th>
												<th>확인</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${msgListBox}" var="msg">
												<tr onclick="selmsg(${msg.msg_idx})" id="msgtr" value="${msg.msg_idx}">
													<td>${msg.emp_name}</td>
													<td>${msg.msg_date}</td>
													<c:if test="${msg.message_time eq null}">
														<td style="font-weight: bold; color: red">읽지 않음</td>
													</c:if>
													<c:if test="${msg.message_time ne null}">
														<td>${msg.message_time}</td>
													</c:if>
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
			<!-- /page content -->


			<!-- 디테일 모달 -->
			<div class="modal fade bs-example-modal-lg" id="detailMsg" tabindex="-1" role="dialog" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">

						<div class="modal-header">
							<h5 class="modal-title" id="myModalLabel2">쪽지 상세정보</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
						</div>
						<div class="modal-body">
							<table class="table table-bordered" style="width: 100%">
								<tbody id="msgDetailModal"></tbody>
							</table>
						</div>
						<!-- <div class="modal-footer">
                           <button id="delModalBtn" type="button" class="btn btn-primary" onclick="msgBoxListCall()" data-dismiss="modal">확인</button>
                         </div> -->
					</div>
				</div>
			</div>
			<!-- 디테일 모달 끝 -->

		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>

/* var msg_idx = $('#msgtr').val();
console.log(msg_idx); */

function selmsg(idx){
	
		console.log("작동 : "+idx);
		$.ajax({
			type : 'get',
			url : '/msg/msgListDetail.ajax',
			dataType : 'json',
			data : {
				"msg_idx" : idx
			},
			success : function(data) {
				console.log(data);
				msgDetailDrawList(data) 
				
			},
			error : function(e) {
				console.log(e);
			}
		});
		
		
		
	}

	function msgDetailDrawList(list) {
		var content = '';
		$('#detailMsg').modal('show');
		console.log(list);
			if(list.message_time == null){
				list.message_time = "읽지않음";
			}
			content += '<tr>';
			content += '<th>보낸사람</th><td colspan="3">' +list.emp_name+ '</td>';
			content += '</tr>';
			content += '<tr>';
			content += '<th>받은시간</th><td>' +list.msg_date+ '</td>';
			content += '<th>읽은시간</th><td>' +list.message_time+ '</td>';
			content += '</tr>';
			content += '<tr>';
			content += '<th>보낸사람</th><td colspan="3"><textarea readonly id="Drawcontent" name="msg_content"  cols="100" rows="10">' +list.msg_content+ '</textarea></td>';
			content += '</tr>';
				
			
			
		
		$('#msgDetailModal').empty();
		$('#msgDetailModal').append(content);
		
	}
	

</script>
</html>