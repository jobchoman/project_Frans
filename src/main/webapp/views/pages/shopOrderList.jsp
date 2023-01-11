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

#orderListSelect>select {
	float: right;
	height: 30px;
}

th {
	pointer-events: none;
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
							onclick="shopFoodOrderListCall()" value="식자재">식자재</a></li>
						<li class="nav-item"><a class="nav-link" id="profile-tab"
							data-toggle="tab" role="tab" aria-selected="false"
							onclick="shopSubOrderListCall()" value="부자재">부자재</a></li>
					</ul>


					<div class="row">
						<div class="col-sm-12">
							<div class="card-box table-responsive">
								<table id="datatable"
									class="table table-striped table-bordered dataTable no-footer"
									style="width: 100%" aria-describedby="datatable_info">
									<h3>발주리스트</h3>

									<div id="orderListSelect">
										<select id="order_state" name="order_state"
											onchange="orderState(this.value)">
											<option value="처리전" selected="selected">처리전</option>
											<option value="처리완료">처리완료</option>
										</select>
									</div>
									<thead>
										<tr>
											<th>매장명</th>
											<th>발주일자</th>
											<th>매장명</th>
											<th>발주일자</th>
										</tr>
									</thead>

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
	shopFoodOrderListCall();
	function shopFoodOrderListCall() {
		var table = $('#datatable').DataTable(
				{
					destroy : true,
					serverSide : false,
					ajax : {
						"url" : "/order/shopFoodOrderList.do",
						"type" : "get",
						"data" : {}
					},
					columns : [
							{
								data : "order_date"
							},
							{
								data : "shop_idx"
							},
							{
								data : "shop_name"
							},
							{
								data : "order_date",
								"render" : function(data, type, row) {
									if (type == 'display') {
										
											data = '<a href="/shopFoodOrderDetail.go?order_date='+row.order_date+'&shop_idx='+row.shop_idx+'">'
												+ row.order_date +'</a>';
											
										
									}
									return data;
								}
							}
							
						],
					columnDefs : [ {

						targets : [ 0 ],

						searchable : false,

						visible : false

					},
					{

						targets : [ 1 ],

						searchable : false,

						visible : false

					}
					
					
					]

				});

	}
	
	function shopFoodOrderListOkCall() {
		var table = $('#datatable').DataTable(
				{
					destroy : true,
					serverSide : false,
					ajax : {
						"url" : "/order/shopFoodOrderListOk.do",
						"type" : "get",
						"data" : {}
					},
					columns : [
							{
								data : "order_date"
							},
							{
								data : "shop_idx"
							},
							{
								data : "shop_name"
							},
							{
								data : "order_date",
								"render" : function(data, type, row) {
									if (type == 'display') {
										
											data = '<a href="/shopFoodOrderDetail.go?order_date='+row.order_date+'&shop_idx='+row.shop_idx+'">'
												+ row.order_date +'</a>';
											
										
									}
									return data;
								}
							}
							
						],
					columnDefs : [ {

						targets : [ 0 ],

						searchable : false,

						visible : false

					},
					{

						targets : [ 1 ],

						searchable : false,

						visible : false

					}
					
					
					]

				});

	}
	
	function shopSubOrderListCall() {
		$("#order_state").prop('selectedIndex', 0);
		var table = $('#datatable').DataTable(
				{
					destroy : true,
					serverSide : false,
					ajax : {
						"url" : "/order/shopSubOrderListCall.do",
						"type" : "get",
						"data" : {}
					},
					columns : [
							{
								data : "order_date"
							},
							{
								data : "shop_idx"
							},
							{
								data : "shop_name"
							},
							{
								data : "order_date",
								"render" : function(data, type, row) {
									if (type == 'display') {
										
											data = '<a href="/shopSubOrderDetail.go?order_date='+row.order_date+'&shop_idx='+row.shop_idx+'">'
												+ row.order_date +'</a>';
											
										
									}
									return data;
								}
							}
							
						],
					columnDefs : [ {

						targets : [ 0 ],

						searchable : false,

						visible : false

					},
					{

						targets : [ 1 ],

						searchable : false,

						visible : false

					}
					
					
					]

				});

	}
	
	function shopSubOrderListOkCall() {
		var table = $('#datatable').DataTable(
				{
					destroy : true,
					serverSide : false,
					ajax : {
						"url" : "/order/shopSubOrderListOkCall.do",
						"type" : "get",
						"data" : {}
					},
					columns : [
							{
								data : "order_date"
							},
							{
								data : "shop_idx"
							},
							{
								data : "shop_name"
							},
							{
								data : "order_date",
								"render" : function(data, type, row) {
									if (type == 'display') {
										
											data = '<a href="/shopSubOrderDetail.go?order_date='+row.order_date+'&shop_idx='+row.shop_idx+'">'
												+ row.order_date +'</a>';
											
										
									}
									return data;
								}
							}
							
						],
					columnDefs : [ {

						targets : [ 0 ],

						searchable : false,

						visible : false

					},
					{

						targets : [ 1 ],

						searchable : false,

						visible : false

					}
					
					
					]

				});

	}
	
	function orderState(value) {
		console.log(value);
		console.log($('#home-tab').attr('aria-selected'));
		if ($('#home-tab').attr('aria-selected') == 'true' && value == '처리완료') {
			shopFoodOrderListOkCall();
		} else if ($('#home-tab').attr('aria-selected') == 'true'
				&& value == '처리전') {
			shopFoodOrderListCall();

		} else if ($('#profile-tab').attr('aria-selected') == 'true'
				&& value == '처리완료') {
			shopSubOrderListOkCall();
		} else {
			shopSubOrderListCall();
		}

	}

	console.log($('#home-tab').attr('aria-selected'));

	function orderPage() {
		if ($('#home-tab').attr('aria-selected') == 'true') {
			window.location.href = "shopFoodOrder.go";
		} else {
			window.location.href = "shopSubOrder.go";
		}
	}
</script>
</html>