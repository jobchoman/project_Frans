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
#orderMain {
	width: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
}

.orderWrap {
	display: flex;
	flex-direction: column;
	justify-content: flex-start;
	align-items: center;
	text-align: center;
}

.col-md-6 {
	flex: 0 0 100%;
	max-width: 100%;
}

#orderButton {
	float: right;
	font-size: 9pt;
	background-color: #2A3F54;
}

.tdNone {
	display: none;
}
.nav_menu {
     margin-bottom: 0;
}

.dataTables_info{
	display : none;
}

#datatable_filter{
	float: left;
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
			<div class="right_col orderWrap" role="main">
				<div id="orderMain">

					<div class="col-md-6 col-sm-6 " id="tableUp">
						<div class="x_panel">
							<div class="x_title">
								<h2>매장 자재</h2>
								<div class="clearfix"></div>
								<h3>${data[0].shop_name}</h3>
							</div>
							<div class="x_content">
								<ul class="nav nav-tabs bar_tabs" id="myTab" role="tablist">
									<li class="nav-item"><a class="nav-link active"
										id="home-tab" data-toggle="tab" role="tab"
										aria-selected="true" onclick="foodStockListCall()">식자재</a></li>
									<li class="nav-item"><a class="nav-link" id="profile-tab"
										data-toggle="tab" role="tab" aria-selected="false"
										onclick="subStockListCall()">부자재</a></li>
								</ul>
								<button type="submit" class="btn btn-round btn-info"
									id="orderButton" value="신청" onClick="orderPage()">발주
									신청</button>
								<div class="row">
									<div class="col-sm-12">
										<div class="card-box table-responsive">
											<table id="datatable"
												class="table table-striped table-bordered dataTable no-footer"
												style="width: 100%" aria-describedby="datatable_info">
												<thead>
													<tr>
														<th>물품명</th>
														<th>매장 재고량(개)</th>
														<th>가격(원)</th>
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

		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>
foodStockListCall();
function foodStockListCall(){		
var table = $('#datatable').DataTable({
	destroy : true,
	serverSide: false,
	"dom": 'frtp',
	ajax : {
        "url":"/stock/shopStockList.do",
        "type":"get",
        "data": {
        	"stock_sort_idx" : 0
        } 
        
    },
    columns : [	
    	{data : "stock_name"},
        {data: "shop_stock_amount","defaultContent": "0"},	
        {data: "stock_price"}
    ],
    columnDefs: [{ targets: 1 , render: $.fn.dataTable.render.number( ',' , '.' , 0 , '' , '개' ) },
    { targets: 2 , render: $.fn.dataTable.render.number( ',' , '.' , 0 , '' , '원'  ) }
    
    ]

});
	
}

	function subStockListCall() {
	
		var table = $('#datatable').DataTable({
			destroy : true,
			serverSide: false,
			"dom": 'frtp',
			ajax : {
		        "url":"/stock/shopStockList.do",
		        "type":"get",
		        "data": {
		        	"stock_sort_idx" : 1
		        } 
		        
		    },
		    columns : [	
		    	{data : "stock_name"},
		        {data: "shop_stock_amount","defaultContent": "0"},
		        {data: "stock_price"}
		    ],
		    columnDefs: [{ targets: 1 , render: $.fn.dataTable.render.number( ',' , '.' , 0 , '' , '개' ) },
		        { targets: 2 , render: $.fn.dataTable.render.number( ',' , '.' , 0 , '' , '원'  ) }
		        
		        ]
		    

		});
	}
	
	console.log($('#home-tab').attr('aria-selected'));
 	function orderPage() {
		if($('#home-tab').attr('aria-selected') == 'true'){
			window.location.href = "shopFoodOrder.go";
		}else{
			window.location.href = "shopSubOrder.go";
		}
	} 
 	
 	/* var stock_idx[] = $("#addAdd1").get();
	var shop_idx = document.getElementById("#addAdd2");
	var order_amount = document.getElementById("#addAdd3");
	console.log("성공"+stock_idx[0]);
	function orderClick() {
		$.ajax({
			type : 'post',
			url : '/stock/shopOrder.do',
			dataType : 'json',
			data : {
				stock_idx : stock_idx,
				shop_idx : shop_idx,
				order_amount : order_amount
				
			},
			success : function(data) {
				console.log(data);
				foodOrderListCall();
			 
			},
			error : function(e) {
				console.log(e);
			}
		});
	}   */
</script>
</html>