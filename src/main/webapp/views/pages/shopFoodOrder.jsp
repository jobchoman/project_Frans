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
	width:100%;
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

.tdNone{
	display: none;
}
.nav_menu {
     margin-bottom: 0;
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
								<h2>발주신청</h2>
								<div class="clearfix"></div>
								<h3>${data[0].shop_name}</h3>
							</div>
							<div class="x_content">
								<form action="/order/shopOrder.do" method="post">
									<table class="table">
										<thead>
											<tr>
												<th>물품명</th>
												<th>매장 재고량(개)</th>
												<th>가격(원)</th>
												<th>발주량</th>
											</tr>
										</thead>
										<tbody id="stockList">
										</tbody>
									</table>
									<button type="submit" class="btn btn-round btn-info"
										id="orderButton" value="신청">신청</button>
								</form>
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
	foodOrderListCall();
	console.log(${list});

	function drawList(data) {
		var content = '';
		console.log(data);
		for (i = 0; i < data.length; i++) {
			if(data[i].shop_stock_amount == null){
				data[i].shop_stock_amount = '0';
			}
			console.log(data[i]);
			content += '<tr>';
		 	content += '<td class="tdNone"><input id="addAdd1" name = "stock_idx" type="text" value="' + data[i].stock_idx + '"</td>'; 
		 	content += '<td class="tdNone"><input id="addAdd2" name = "shop_idx" type="text" value="' + data[i].shop_idx + '""</td>'; 
			content += '<td>' + data[i].stock_name + '</td>';
			content += '<td>' + data[i].shop_stock_amount + '</td>';
			content += '<td>' + data[i].stock_price + '</td>';
			content += '<td><input id="addAdd3" name="order_amount" type="number" placeholder="수량을 입력해주세요."/></td>';
			content += '</tr>';
		}
		$('#stockList').empty();
		$('#stockList').append(content);

	}

	function foodOrderListCall() {
		$.ajax({
			type : 'get',
			url : '/stock/foodOrderList.do',
			dataType : 'json',
			data : {
				stock_sort_idx : 0
			},
			success : function(data) {
				console.log(data);
				drawList(data.data)
				/* $('#paging').twbsPagination({
					startPage : 1, // 시작 페이지
					totalPages : data.total, // 총 페이지 수
					visiblePages : 5, // 기본으로 보여줄 페이지 수
					onPageClick : function(e, page) { // 클릭했을때 실행 내용
						//console.log(e);
						stockListCall(page);

					}
				}); */
			},
			error : function(e) {
				console.log(e);
			}
		});
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