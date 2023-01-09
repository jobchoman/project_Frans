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

<style type="text/css">
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

				<div class="x_content">
					<div class="col-md-12 col-sm-12 ">
						<div class="x_panel">
							<div class="x_title"></div>
							<div class="x_content">
								<div class="row">
									<div class="col-sm-12">
										<div class="card-box table-responsive">
											<form action="/order/orderChk">
												<table id="datatable-checkbox"
													class="table table-striped table-bordered bulk_action"
													style="width: 100%">
													<h3>발주내역</h3>
													<h4>${list[0].shop_name}</h4>
													<thead>
														<tr>

															<th><input type="checkbox" id="check-all"></th>

															<th>물품명</th>
															<th>본사 재고량</th>
															<th>신청량</th>
															<th>처리상태</th>
														</tr>
													</thead>


													<tbody id="orderList">
	
														<c:forEach items="${data}" var="list">
													
													
															<tr>
																<th><input name="chk" type="checkbox"
																	id="check"></th>
																<td>${list.stock_name}</td>
																<td>${list.com_stock_amount}</td>
																<td>${list.order_amount}</td>
																<td  id="orderSend">${list.order_send}</td>
															</tr>
											
														</c:forEach>

													</tbody>
												</table>
												<button type="submit" class="btn btn-round btn-info"
										id="orderButton" value="">발주확인</button>
											</form>
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
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>
$(document).ready(function() {
	$("#check-all").click(function() {
		if($("#check-all").is(":checked")) $("input[name=chk]").prop("checked", true);
		else $("input[name=chk]").prop("checked", false);
	});

	$("input[name=chk]").click(function() {
		var total = $("input[name=chk]").length;
		var checked = $("input[name=chk]:checked").length;

		if(total != checked) $("#check-all").prop("checked", false);
		else $("#check-all").prop("checked", true); 
	});
});
if($("orderSend").innerHTML == "처리완료"){
	($("#check").css("display") == "none")
}	

</script>
</html>