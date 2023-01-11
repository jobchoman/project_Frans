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

#updateBtn{
	font-size: 5pt;
}

#datatable{
	text-align: center;
}

.sorting_1{
	display:none;
}
#hiddenData, #updateStockIdx{
	display:none;
}

p{
	margin-top: 1px;
	margin-bottom : 1px;
	font-size: 11pt;
}

.modal{
	top : 25%;
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

									<table id="datatable"
													class="table table-striped table-bordered dataTable no-footer"
													style="width: 100%" aria-describedby="datatable_info">
										<thead>
											<tr>
												<th id="hiddenData">stock_idx</th>
												<th>물품명</th>
												<th>가격(원)</th>
												<th>재고량(개)</th>
												<th>수정</th>
											</tr>
										</thead>
									</table>

								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="clearfix"></div>
			</div>
			<div class="modal fade bs-example-modal-sm" id="secondmodal" tabindex="-1" role="dialog" aria-hidden="true">
                  <div class="modal-dialog modal-sm">
                       <div class="modal-content">

                         <div class="modal-header">
                              <h5 class="modal-title" id="myModalLabel2">자재 수정</h5>
                              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                         </div>
                         <div class="modal-body">
                           <div class="col-md-9 col-sm-9 ">
                           		<input type="text" id="updateStockIdx" name="stock_idx" class="form-control" placeholder="idx" >
                           		<p>자재명</p>
								<input type="text" id="updateStockName" name="stock_name" class="form-control" placeholder="자재명" >
								<p>가격</p>
								<input type="number" id="updateStockPrice" name="stock_price" class="form-control" placeholder="가격">
								<p>추가할 재고량</p>
								<input type="number" id="updateStockamount" name="com_stock_amount" class="form-control" placeholder="추가할 재고량">
							</div>
                        </div>
                         <div class="modal-footer">
                           <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                           <button type="button" onclick="updateStock()" class="btn btn-primary" data-dismiss="modal">수정</button>
                         </div>
                     </div>
                  </div>
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
function foodListCall() {
	var table = $('#datatable').DataTable({
		destroy:true,
		serverSide: false,
		ajax : {
            "url":"/stock/foodList.do",
            "type":"get",
            "data": {
            	"stock_sort_idx" : 0
            }
        },
        columns : [
        	{data : "stock_idx"},
        	{data : "stock_name"},
            {data: "stock_price"},
            {data: "com_stock_amount"},
            {data: null, defaultContent: "<button id='updateBtn' onclick = 'updateView(event)' type='button' class='btn btn-round btn-secondary'>수정</button>"}
        ],
        columnDefs: [{

        	targets: [0],

        	searchable: false,

  			visible: true

        }]

    });
	
}	
	
	function subListCall() {
		var table = $('#datatable').DataTable({
			destroy:true,
			serverSide: false,
			ajax : {
	            "url":"/stock/subList.do",
	            "type":"get",
	            "data": {
	            	"stock_sort_idx" : 1
	            }
	        },
	        columns : [
	        	{data : "stock_idx"},
	        	{data : "stock_name"},
	            {data: "stock_price"},
	            {data: "com_stock_amount"},
	            {data: null, defaultContent: "<button id='updateBtn' onclick = 'updateView(event)' type='button' class='btn btn-round btn-secondary'>수정</button>"}
	        ],
	        columnDefs: [{

	        	targets: [0],

	        	searchable: false,

	  			visible: true

	        }],
	        createdRow: function (row, data, dataIndex, full) {
	            
	     },

	    });
	}
	
/* 	function updateStock() {
		var rowData = new Array();
		var tdArr = new Array();
		var val = $("#updateBtn").val();
		console.log(typeof val);
		var tr = checkbox.parent().parent();
		var td = tr.children();
		var shop_idx = td.eq(1).text();
		tdArr.push(shop_idx);
		console.log("shop_idx : " + shop_idx);
		
		rowData.push(tr.text());
	} */
	function updateView(event) {
		var stock_idx = event.target.parentNode.parentNode.firstChild.innerHTML;
		console.log(stock_idx)
		
		$.ajax({
			type : 'get',
			url : '/stock/updateView.do',
			dataType : 'json',
			data:{stock_idx : stock_idx},
			success : function(data) {
				console.log(data);
				console.log(data.data[0].stock_name);
				$('#secondmodal').modal();
				$("#updateStockIdx").val(data.data[0].stock_idx);
				$("#updateStockName").val(data.data[0].stock_name);
				$("#updateStockPrice").val(data.data[0].stock_price);

			},
			error : function(e) {
				console.log(e);
			} 
		});

		
	}
	
	function updateStock() {
		var stock_idx = $("#updateStockIdx").val();
		var stock_name = $("#updateStockName").val();
		var stock_price = $("#updateStockPrice").val();
		var com_stock_amount = $("#updateStockamount").val();
		$.ajax({
			type : 'get',
			url : '/stock/updateStock.do',
			dataType : 'text',
			data:{stock_name : stock_name,
				stock_price : stock_price,
				com_stock_amount : com_stock_amount,	
				stock_idx : stock_idx
			},
			success : function(data) {
				console.log(data);
				if(data == "성공"){
				window.location.href="/stockAdd.go";
					
				}

			},
			error : function(e) {
				console.log(e);
			} 
		});
	}

	
	
/* 	$("#updateBtn").click(function(){ 
		
		var rowData = new Array();
		var tdArr = new Array();
		var checkBtn = $(this);

			// checkbox.parent() : checkbox의 부모는 <td>이다.
			// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
			var tr = checkBtn.parent().parent();
			var td = tr.children();
			
			// 체크된 row의 모든 값을 배열에 담는다.
			rowData.push(tr.text());
			console.log("클릭한 Row의 모든 데이터 : "+tr.text());
			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var shop_idx = td.eq(0).text();
			var stock_idx = td.eq(1).text();
			var order_amount = td.eq(2).text();
			var order_date = td.eq(3).text();
			
			// 가져온 값을 배열에 담는다.
			tdArr.push(shop_idx);
			tdArr.push(stock_idx);
			tdArr.push(order_amount);
			
			console.log("shop_idx : " + shop_idx);
			console.log("stock_idx : " + stock_idx);
			console.log("order_amount : " + order_amount);
			console.log("order_date : " + order_date);
	}); */
	/* var str = datatable.rows[1].cells[1].innerText; */
	/* var str = document.getElementsByTagName('td')[4].innerText; */
 	/* 	$.ajax({
			type : 'get',
			url : '/stock/updateView.do',
			dataType : 'json',
			data:{stock_idx : },
			success : function(data) {
				console.log(data);
				drawList(data.data)
			},
			error : function(e) {
				console.log(e);
			} 
		});
		$('#secondmodal').modal();  */

</script>
</html>