<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<jsp:include page="css.jsp" />
<style>
#tableUp {
	float: inherit;
}

#addMain {
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
}


.addWrap {
	display: flex;
	flex-direction: column;
	justify-content: flex-start;
	align-items: center;
}

.addList {
	width: 100%;
	display: flex;
	justify-content: center;
	align-items: center;
}

#addButton {
	float: right;
	font-size: 9pt;
	background-color: #2A3F54;
}

#updateButton {
	font-size: 9pt;
	background-color: #2A3F54;
}

.col-md-6 {
	flex: 0 0 100%;
	max-width: 100%;
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
				<div id="addMain">
					<div class="col-md-6 col-sm-6 " id="tableUp">
						<div class="x_panel">
							<div class="x_title">
								<h2>자재등록</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<form action="/stock/add.do">
									<table class="table">
										<thead>
											<tr>
												<th>자재분류</th>
												<th>물품명</th>
												<th>가격(원)</th>
												<th>재고량(개)</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>식자재 : <input type="radio" class="flat"
													name="stock_sort_idx" id="genderM" value="0" checked=""
													required /> </br> 부자재 : <input type="radio" class="flat"
													name="stock_sort_idx" " id="genderF" value="1" />
												</td>
												<td><div class="form-group row">
														<div class="col-md-9 col-sm-9 ">
															<input type="text" class="form-control" name="stock_name"
																placeholder="물품명">
														</div>
													</div></td>
												<td><div class="form-group row">
														<div class="col-md-9 col-sm-9 ">
															<input type="number" class="form-control"
																name="stock_price" placeholder="가격">
														</div>
													</div></td>
												<td><div class="form-group row">
														<div class="col-md-9 col-sm-9 ">
															<input type="number" class="form-control"
																name="com_stock_amount" placeholder="재고량">
														</div>
													</div></td>
											</tr>

										</tbody>
									</table>
									<input type="submit" class="btn btn-round btn-info"
										id="addButton" value="등록"></input>
								</form>
							</div>
						</div>
					</div>

				
			

				<div class="x_content">
					<ul class="nav nav-tabs bar_tabs" id="myTab" role="tablist">
                      <li class="nav-item">
                        <a class="nav-link active" id="home-tab" data-toggle="tab" role="tab" aria-selected="true" onclick="foodListCall()">식자재</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" id="profile-tab" data-toggle="tab" role="tab"  aria-selected="false" onclick="subListCall()">부자재</a>
                      </li>
                    </ul>
					<div class="row addList">
						<div class="col-md-6 col-sm-6  ">
							<div class="x_panel">
								<div class="x_title">
									<h2>자재리스트</h2>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">

									<table class="table">
										<thead>
											<tr>
												<th>물품명</th>
												<th>가격(원)</th>
												<th>재고량(개)</th>
											</tr>
										</thead>
										<tbody id="stockList">
										</tbody>
										<tr>
											<td colspan="5" id="paging">
												<div class="container">
													<nav aria-label="Page navigaion">
														<ul class="pagination" id="pagination"></ul>

													</nav>
												</div>
											</td>
										</tr>
									</table>

								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="clearfix"></div>
			</div>
			<!-- /page content -->

			<!-- footer content -->
			<%-- 	<footer><jsp:include page="footer.jsp" /></footer> --%>
			<!-- /footer content -->
		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>
foodListCall();
/* 	function stockListCall() {
		$.ajax({
			type : 'get',
			url : '/stock/list.do',
			dataType : 'json',
			success : function(data) {
				console.log(data);
				drawList(data.list)
				$('#paging').twbsPagination({
					startPage : 1, // 시작 페이지
					totalPages : data.total, // 총 페이지 수
					visiblePages : 5, // 기본으로 보여줄 페이지 수
					onPageClick : function(e, page) { // 클릭했을때 실행 내용
						//console.log(e);
						stockListCall(page);

					}
				});
			},
			error : function(e) {
				console.log(e);
			}
		});
	} */
	function drawList(list) {
		var content = '';
		console.log(list);
		for (i = 0; i < list.length; i++) {
			console.log(list[i]);
			content += '<tr>';
			content += '<td><a href="#">' + list[i].stock_name + '</a></td>';
			content += '<td>' + list[i].stock_price + '</td>';
			content += '<td>' + list[i].com_stock_amount + '</td>';
			content += '</tr>';
		}
		$('#stockList').empty();
		$('#stockList').append(content);

	}
	
	function foodListCall() {
		$.ajax({
			type : 'get',
			url : '/stock/foodList.do',
			dataType : 'json',
			data:{stock_sort_idx : 0},
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
	
	function subListCall() {
		$.ajax({
			type : 'get',
			url : '/stock/subList.do',
			dataType : 'json',
			data:{stock_sort_idx : 1},
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

	var stock_name = document.querySelector('input[name="stock_name"]');
	var stock_price = document.querySelector('input[name="stock_price"]');
	var com_stock_amount = document
			.querySelector('input[name="com_stock_amount"]');

	/* $('#addButton').click(function() {

	 $.ajax({
	 type:'get'
	 ,url:'/stock/add.do'
	 ,data: {
	 stock_name : stock_name,
	 stock_price : stock_price,
	 com_stock_amout : com_stock_amount
	 }
	 ,dataType:'json'
	 ,success:function(data){
	 console.log(data);
	 drawList(data.comStockList)
	 }
	 ,error:function(e){
	 console.log(e);
	 }
	 });
	 }); */
</script>
</html>