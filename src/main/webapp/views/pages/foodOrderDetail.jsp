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
.orderData{
	display: none;
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

				<div class="x_content">
					<div class="col-md-12 col-sm-12 ">
						<div class="x_panel">
							<div class="x_title"></div>
							<div class="x_content">
								<div class="row">
									<div class="col-sm-12">
										<div class="card-box table-responsive">
											<form action="/order/orderChk.do" method="post">
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
															<th class="orderData">shop_idx</th>
															<th class="orderData">stock_idx</th>
															<th class="orderData">order_amount</th>
															<th class="orderData">order_date</th>
														</tr>
													</thead>


													<tbody id="orderList">
														<c:forEach items="${data}" var="list">
															<tr>

																<td><input name="chk" type="checkbox"
																	id="check-all" value="${list.shop_idx}"></td>	
																<td>${list.stock_name}</td>
																<td>${list.com_stock_amount}</td>
																<td>${list.order_amount}</td>
																<td>${list.order_send}</td>
																<td class="orderData">${list.shop_idx}</td>
																<td class="orderData">${list.stock_idx}</td>
																<td class="orderData">${list.order_amount}</td>
																<td class="orderData">${list.order_date}</td>
																
															</tr>
														</c:forEach>

													</tbody>
												</table>
												<div id="orderBtn">
												<button type="button" class="btn btn-round btn-info"
										id="orderButton" value="">발주확인</button></div>
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


$("#orderButton").click(function(){ 
	
	var rowData = new Array();
	var tdArr = new Array();
	var checkbox = $("input[name=chk]:checked");
	
	// 체크된 체크박스 값을 가져온다
	checkbox.each(function(i) {

		// checkbox.parent() : checkbox의 부모는 <td>이다.
		// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
		var tr = checkbox.parent().parent().eq(i);
		var td = tr.children();
		
		// 체크된 row의 모든 값을 배열에 담는다.
		rowData.push(tr.text());
		
		// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
		var shop_idx = td.eq(5).text();
		var stock_idx = td.eq(6).text();
		var order_amount = td.eq(7).text();
		var order_date = td.eq(8).text();
		
		// 가져온 값을 배열에 담는다.
		tdArr.push(shop_idx);
		tdArr.push(stock_idx);
		tdArr.push(order_amount);
		
		console.log("shop_idx : " + shop_idx);
		console.log("stock_idx : " + stock_idx);
		console.log("order_amount : " + order_amount);
		console.log("order_date : " + order_date);
		$.ajax({
			type : 'get',
			url : '/order/orderChk.do',
			dataType : 'text',
			data:{shop_idx : shop_idx,
				stock_idx : stock_idx,
				order_amount : order_amount,
				order_date : order_date},
			success : function(data) {
				/* window.location.href = "/orderList.go"; */
				console.log(data);
				if(data == '성공'){
					window.location.href = "/foodOrderDetail.go?order_date="+order_date+"&shop_idx="+shop_idx;
					
				}
			},
			error : function(e) {
				/* window.location.href = "/orderList.go"; */
				console.log(e);
			}
		});
	});
});

	
</script>
</html>