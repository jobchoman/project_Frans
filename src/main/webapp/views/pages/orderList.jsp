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
#datatable_filter, #datatable_length, #datatable_info,
	#datatable_paginate {
	display: none;
}

#orderListSelect {
	width: 98.9%;
}

#orderListSelect > select {
	float: right;
	height: 30px;
}

th {
	pointer-events:none;
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

					<ul class="nav nav-tabs bar_tabs" id="myTab" role="tablist">
						<li class="nav-item"><a class="nav-link active" id="home-tab"
							data-toggle="tab" role="tab" aria-selected="true"
							onclick="FoodOrderListCall()" value = "식자재">식자재</a></li>
						<li class="nav-item"><a class="nav-link" id="profile-tab"
							data-toggle="tab" role="tab" aria-selected="false"
							onclick="SubOrderListCall()" value = "부자재">부자재</a></li>
					</ul>


					<div class="row">
						<div class="col-sm-12">
							<div class="card-box table-responsive">
								<table id="datatable" class="table table-striped table-bordered"
									style="width: 100%">
										<h3>발주리스트</h3>
									
									<div id="orderListSelect">
										<select id="order_state" name="order_state" onchange="orderState(this.value)">
											<option value="처리전" selected="selected"
												>처리전</option>
											<option value="처리완료">처리완료</option>
										</select>
									</div>
									<thead>
										<tr>
											<th>No.</th>
											<th>발주일자</th>
											<th>매장명</th>
										</tr>
									</thead>


									<tbody id="orderList">
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

	<jsp:include page="script.jsp" />
</body>
<script>
	FoodOrderListCall();
	function drawList(list) {
		var content = '';
		console.log(list);
		for (i = 0; i < list.length; i++) {
			console.log(list[i]);
			content += '<tr>';
			content += '<td>' + (i + 1) + '</td>';
			content += '<td><a href= "/foodOrderDetail.go?order_date='+list[i].order_date+'&shop_idx='+list[i].shop_idx+'">' + list[i].order_date + '</a></td>';
			content += '<td>' + list[i].shop_name + '</td>';
			content += '</tr>';
		}
		$('#orderList').empty();
		$('#orderList').append(content);

	}

	function FoodOrderListCall() {
		$("#order_state").prop('selectedIndex',0);
		$.ajax({
			type : 'get',
			url : '/stock/FoodOrderList.do',
			dataType : 'json',
			data : {

			},
			success : function(data) {
				console.log(data);
				drawList(data.data)
				/* $('#example_paginate').twbsPagination({
				startPage : 1, // 시작 페이지
				totalPages : data.total, // 총 페이지 수
				visiblePages : 3, // 기본으로 보여줄 페이지 수
				onPageClick : function(e, page) { // 클릭했을때 실행 내용
					//console.log(e);
					foodStockListCall(page);

				}
				});  */
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	function FoodOrderListOkCall() {
		$.ajax({
			type : 'get',
			url : '/stock/FoodOrderListOk.do',
			dataType : 'json',
			data : {

			},
			success : function(data) {
				console.log(data);
				drawList(data.data)
				/* $('#example_paginate').twbsPagination({
				startPage : 1, // 시작 페이지
				totalPages : data.total, // 총 페이지 수
				visiblePages : 3, // 기본으로 보여줄 페이지 수
				onPageClick : function(e, page) { // 클릭했을때 실행 내용
					//console.log(e);
					foodStockListCall(page);

				}
				});  */
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	function SubOrderListCall() {
		$("#order_state").prop('selectedIndex',0);
		$.ajax({
			type : 'get',
			url : '/stock/SubOrderList.do',
			dataType : 'json',
			data : {

			},
			success : function(data) {
				console.log(data);
				subDrawList(data.data)
				/* $('#example_paginate').twbsPagination({
				startPage : 1, // 시작 페이지
				totalPages : data.total, // 총 페이지 수
				visiblePages : 3, // 기본으로 보여줄 페이지 수
				onPageClick : function(e, page) { // 클릭했을때 실행 내용
					//console.log(e);
					foodStockListCall(page);

				}
				});  */
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	function SubOrderListOkCall() {
		$.ajax({
			type : 'get',
			url : '/stock/SubOrderListOk.do',
			dataType : 'json',
			data : {

			},
			success : function(data) {
				console.log(data);
				subDrawList(data.data)
				/* $('#example_paginate').twbsPagination({
				startPage : 1, // 시작 페이지
				totalPages : data.total, // 총 페이지 수
				visiblePages : 3, // 기본으로 보여줄 페이지 수
				onPageClick : function(e, page) { // 클릭했을때 실행 내용
					//console.log(e);
					foodStockListCall(page);

				}
				});  */
			},
			error : function(e) {
				console.log(e);
			}
		});
	}
	
	function subDrawList(list) {
		var content = '';
		console.log(list);
		for (i = 0; i < list.length; i++) {
			console.log(list[i]);
			content += '<tr>';
			content += '<td>' + (i + 1) + '</td>';
			content += '<td><a href= "/subOrderDetail.go?order_date='+list[i].order_date+'&shop_idx='+list[i].shop_idx+'">' + list[i].order_date + '</a></td>';
			content += '<td>' + list[i].shop_name + '</td>';
			content += '</tr>';
		}
		$('#orderList').empty();
		$('#orderList').append(content);

	}
	function orderState(value) {
	console.log(value);
	console.log($('#home-tab').attr('aria-selected'));
		if($('#home-tab').attr('aria-selected') == 'true' && value == '처리완료' ){
			FoodOrderListOkCall();
		}else if($('#home-tab').attr('aria-selected') == 'true' && value == '처리전' ){
			FoodOrderListCall();
			
		}else if($('#profile-tab').attr('aria-selected') == 'true' && value == '처리완료' ){
			SubOrderListOkCall(); 					
		}else{
			SubOrderListCall();  
		}
		
	}
	
	
	
	console.log($('#home-tab').attr('aria-selected'));

	function orderPage() {
	if($('#home-tab').attr('aria-selected') == 'true'){
		window.location.href = "shopFoodOrder.go";
	}else{
		window.location.href = "shopSubOrder.go";
	}
}
	
	 
</script>
</html>