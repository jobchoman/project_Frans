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
.nav_menu {
     margin-bottom: 0;
}

/*    #signlistdiv{
      display: flex;
      justify-content: center;
         align-items: center;
         flex-direction: column;
         width: 80%;
   } */

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
				<div id="signlistdiv">
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
									<table id="comOrderList"
									class="table table-striped table-bordered dataTable bulk_action"
									style="width: 100%" aria-describedby="datatable_info">
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
											<th>발주일자</th>
											<th>매장번호</th>
											<th>발주일자</th>
											<th>매장명</th>
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
	</div>
	<!-- /page content -->

	<jsp:include page="script.jsp" />
</body>
<script>
	FoodOrderListCall();
	function FoodOrderListCall() {
		$("#order_state").prop('selectedIndex',0);
		var table = $('#comOrderList').DataTable(
				{
					destroy : true,
					"dom": 'frtp',
					serverSide : false,
					bAutoWidth: false,
					ajax : {
						"url" : "/order/comOrderList.do",
						"type" : "get",
						"data" : {"stock_sort_idx" : 0}
					},
					columns : [
							{
								data : "order_date"
							},
							{
								data : "shop_idx"
							},
							{
								data : "order_date",
								"render" : function(data, type, row) {
									if (type == 'display') {
										
											data = '<a href="/foodOrderDetail.go?order_date='+row.order_date+'&shop_idx='+row.shop_idx+'">'
												+ row.order_date +'</a>';
											
										
									}
									return data;
								}
							},
							{
								data : "shop_name"
							}
							
						],
					columnDefs : [ {

						targets : [ 0 ],

						searchable : false,

						visible : false

					}
						
					]

				});

	}
	
	function FoodOrderListOkCall() {
		var table = $('#comOrderList').DataTable(
				{
					destroy : true,
					"dom": 'frtp',
					serverSide : false,
					bAutoWidth: false,
					ajax : {
						"url" : "/order/comOrderListOk.do",
						"type" : "get",
						"data" : {"stock_sort_idx" : 0}
					},
					columns : [
							{
								data : "order_date"
							},
							{
								data : "shop_idx"
							},
							{
								data : "order_date",
								"render" : function(data, type, row) {
									if (type == 'display') {
										
											data = '<a href="/foodOrderDetail.go?order_date='+row.order_date+'&shop_idx='+row.shop_idx+'">'
												+ row.order_date +'</a>';
											
										
									}
									return data;
								}
							},
							{
								data : "shop_name"
							}
							
						],
					columnDefs : [ {

						targets : [ 0 ],

						searchable : false,

						visible : false

					}
						
					]

				});

	}
	
	function SubOrderListCall() {
		$("#order_state").prop('selectedIndex',0);
		var table = $('#comOrderList').DataTable(
				{
					destroy : true,
					"dom": 'frtp',
					serverSide : false,
					bAutoWidth: false,
					ajax : {
						"url" : "/order/comOrderList.do",
						"type" : "get",
						"data" : {"stock_sort_idx" : 1}
					},
					columns : [
							{
								data : "order_date"
							},
							{
								data : "shop_idx"
							},
							{
								data : "order_date",
								"render" : function(data, type, row) {
									if (type == 'display') {
										
											data = '<a href="/subOrderDetail.go?order_date='+row.order_date+'&shop_idx='+row.shop_idx+'">'
												+ row.order_date +'</a>';
											
										
									}
									return data;
								}
							},
							{
								data : "shop_name"
							}
							
						],
					columnDefs : [ {

						targets : [ 0 ],

						searchable : false,

						visible : false

					}
						
					]

				});

	}
	
	function SubOrderListOkCall() {
		var table = $('#comOrderList').DataTable(
				{
					destroy : true,
					"dom": 'frtp',
					serverSide : false,
					bAutoWidth: false,
					ajax : {
						"url" : "/order/comOrderListOk.do",
						"type" : "get",
						"data" : {"stock_sort_idx" : 1}
					},
					columns : [
							{
								data : "order_date"
							},
							{
								data : "shop_idx"
							},
							{
								data : "order_date",
								"render" : function(data, type, row) {
									if (type == 'display') {
										
											data = '<a href="/subOrderDetail.go?order_date='+row.order_date+'&shop_idx='+row.shop_idx+'">'
												+ row.order_date +'</a>';
											
										
									}
									return data;
								}
							},
							{
								data : "shop_name"
							}
							
						],
					columnDefs : [ {

						targets : [ 0 ],

						searchable : false,

						visible : false

					}
						
					]

				});

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


	
	 
</script>
</html>