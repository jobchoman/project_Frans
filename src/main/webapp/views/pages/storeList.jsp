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
	.addWrap {
	   display: flex;
	   flex-direction: column;
	   justify-content: flex-start;
	   align-items: center;
	}
	
	.row > .col-sm-6:first-child {
		display: none;
	}
	
	#datatable_info {
		display: none;
	}
	
	#registShop {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 8pt;
	}
	
	#datatable {
		text-align: center;
	}
	
	#storelistdiv{
		display: flex;
		justify-content: center;
   		align-items: center;
   		flex-direction: column;
   		width: 80%;
	}
	
	.x_panel {
		background: none;
		border: none;
	}
	
	.statoption {
		float:left;
		text-align: center;
		padding: 10px 5px
	}
	.provinces {
	display: block;
    float: left;
    width: 33.3%;
    height: 40px;
    border: 1px solid #e5e5e5;
    letter-spacing: -1px;
    font-family: NanumGothic, NanumGothicWebFont, "Apple SD Gothic Neo", 돋움, Dotum, sans-serif;
    color: rgb(34, 34, 34);
    font-size: 12px;
    line-height: 19px;
    padding: 10px 0 4px 4px;
    cursor: pointer;
    }
    #provinceList {
    	padding: 0px;
    }
	.cities {
	display: block;
    float: left;
    width: 33.3%;
    height: 40px;
    border: 1px solid #e5e5e5;
    letter-spacing: -1px;
    font-family: NanumGothic, NanumGothicWebFont, "Apple SD Gothic Neo", 돋움, Dotum, sans-serif;
    color: rgb(34, 34, 34);
    font-size: 12px;
    line-height: 19px;
    padding: 10px 0 4px 4px;
    cursor: pointer;
    }
    #cityList {
    	padding: 0px;
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
				<div id="storelistdiv" class="col-md-12 col-sm-12 ">
					<div class="x_panel">
						<div class="x_title">
							<h2>매장 리스트</h2>
							<button type="button" class="btn btn-round btn-info" id="registShop" onclick="location.href='/storeWrite.go'" style="float:right">매장 등록</button>
							<div class="clearfix"></div>
						</div>
						<div class="row">
						
							<div class="statoption" id="province" data-toggle="modal" data-target=".provinceModal" style="min-height: 30px; cursor: pointer;">
								<span id="provinceTitle">시도</span>
							</div>
							<div id="pro_idx"></div>
							<div class="statoption" id="city" data-toggle="modal" data-target=".cityModal" style="min-height: 30px; cursor: pointer;">
								<span id="cityTitle">시군구</span>
							</div>
							<div></div>
							<div class="statoption" id="all">
								<span id="all" onclick="reset(); listCall()">전체</span>
							</div>
							
							<div class="col-sm-12">
								<div class="card-box table-responsive">
									<table id="storelist" class="table table-striped table-bordered" style="width: 100%; text-align: center;">
										<thead>
											<tr>
												<th>매장코드</th>
												<th>매장명</th>
												<th>점장</th>
												<th>연락처</th>
											</tr>
										</thead>


	<%-- 									<tbody>
											<c:forEach items="${storelist}" var="store">
												<tr>
													<td><a
														href="/storeDetail.go?shop_idx=${store.shop_idx}">${store.shop_name}</a></td>
													<td>${store.emp_name}</td>
													<td>${store.shop_contact}</td>
												</tr>
											</c:forEach>
										</tbody> --%>
									</table>
								</div>
							</div>
						</div>
					</div>
					</div>
				</div>
			</div>
			<!-- /page content -->
			
			<!-- modal -->
			<div class="modal fade provinceModal" tabindex="-1" role="dialog" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel2">시도</h4>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
						</div>
						<div class="modal-body">
							<ul id="provinceList"></ul>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade cityModal" tabindex="-1" role="dialog" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title" id="myModalLabel2">시군구</h4>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
						</div>
						<div class="modal-body">
							<ul id="cityList"></ul>
						</div>
					</div>
				</div>
			</div>
			<!-- /modal -->
		</div>
	<jsp:include page="script.jsp" />
</body>
<script>

listCall();

function listCall() {
	
	var table = $('#storelist').DataTable(
			{
				destroy : true,
				aaSorting : [],
				"dom": 'frtp',
				bAutoWidth: false,
//				pageLength : 15,
				serverSide : false,
				ajax : {
					"url" : "store/list.do",
					"type" : "get",
					"data" : {

					}
				},
				columns : [
						{
							data : "shop_idx",
						},
						{
							data : "shop_name",
							"render" : function(data, type, row) {
								if (type == 'display') {
									
										data = '<a href="/storeDetail.go?shop_idx='+row.shop_idx+'">'+row.shop_name+'</a>';
								}
								return data;
							}
						},
						{
							data : "emp_name",
							
						},
						{
							data : "shop_contact"
						}
						
					],
				
				columnDefs : [
					{
						target : [0],
						
						searchable : false,

						visible : false
					}
				]

			});

}

function reset(){
	$('#provinceTitle').html("시도");
	$('#cityTitle').html("시군구");
}



	//차트 옵션 중 시도를 클릭하면 ajax 통신을 통해 province idx와 name 리스트를 응답받음
	//리스트 데이터를 provinceListDraw function에 매개변수로 넣는다.
	//provinceListDraw function이 이 데이터를 가지고 forEach함수로 시도 리스트를
	//모달창에 뿌린다.
	$('#province').click(function() {
		$.ajax({
			type : 'GET',
			url : '/store/provinceList.do',
			success : function(data) {
				console.log(data);
				provinceListDraw(data);
			},
			error : function(e) {
				console.log(e);
			}
		});

	});

	function provinceListDraw(obj) {
		var content = "";

		obj.list.forEach(function(item) {
			/* content += '<li class="provinces" onclick="chooseProvince('+ item.province_idx+ ');" data-dismiss="modal" data-toggle="modal" data-target=".cityModal">'+ item.province_name + '</li>'; */
					// 뿌려진 province List의 요소 각각에 onclick 이벤트를 걸고 매개변수로 해당 province_idx를 가져간다. 그리고 해당 li를 클릭하면
					// province 선택 모달창이 닫히게 li 태그에 설정을 해두었음.
			content += '<li class="provinces" onclick="listcall_province('+ item.province_idx+ '); chooseProvince('+ item.province_idx+ ')" data-dismiss="modal" data-toggle="modal">'+ item.province_name + '</li>';
				});
		$('#provinceList').empty();
		$('#provinceList').append(content);

	}
	
	
	function listcall_province(provinceIdx){
		
		var table = $('#storelist').DataTable(
				{
					destroy : true,
					aaSorting : [],
					"dom": 'frtp',
					bAutoWidth: false,
					serverSide : false,
					ajax : {
						"url" : "/store/selprovince.do",
						"type" : "get",
						"data" : {'idx':provinceIdx}
					},
					columns : [
							{
								data : "shop_idx",
							},
							{
								data : "shop_name",
								"render" : function(data, type, row) {
									if (type == 'display') {
										
											data = '<a href="/storeDetail.go?shop_idx='+row.shop_idx+'">'+row.shop_name+'</a>';
									}
									return data;
								}
							},
							{
								data : "emp_name",
								
							},
							{
								data : "shop_contact"
							}
							
						],
					
					columnDefs : [
						{
							target : [0],
							
							searchable : false,

							visible : false
						}
					]

				});
	}
	
	/* $('#city').click(function() {
		console.log('시군구 클릭');
	}); */

	
/* $('#city').click(function() { */
	
	console.log('시군구 클릭');
	
	function chooseProvince(idx) {
		console.log(idx);// 매개 변수로 받은 idx
		var dv = event.currentTarget;
		// 최근 이벤트 지정
		console.log(dv.innerText);

		$('#provinceTitle').text(dv.innerText);
		// 지정된 이벤트의 text를 가져와 provinceTitle의 text를 바꿔준다. 
		// 시도 -> 선택한 시도의 명으로 바뀐다.
		$('#pro_idx').empty();
//		$('#ci_idx').empty();
		$('#pro_idx').append('<input type="hidden" name="province_idx" value="'+idx+'">');
		// 선택한 시도의 idx를 사용하기 위해 input type="hidden" 사용

		$.ajax({
			type : 'GET',
			url : '/store/cityList.do',
			data : {
				'idx' : idx
			},
			// 해당 시도의 하위 시군구 리스트를 받아오기 위해 매개변수로 받은 idx 데이터로 설정해서 요청. 
			success : function(data) {
				console.log(data);
				cityListDraw(data);
				// provinceListDraw와 같은 맥락
			},
			error : function(e) {
				console.log(e);
			}
		});

	}
/* }); */

	function cityListDraw(obj) {
		var content1 = "";

		obj.list.forEach(function(item) {
			content1 += '<li class="cities" onclick="choosecity('+ item.city_idx+ ');" data-dismiss="modal" data-toggle="modal" data-target=".shopModal">'+ item.city_name + '</li>';
		});
		$('#cityList').empty();
		$('#cityList').append(content1);

	}

	$(document).on('click', '.cities', function() {
		$('#cityTitle').text($(this).text());
		// event.currentTarget 이 위에서 먹고 다시 쓰려고 하니 안먹어서 
		// 다른 방식으로 진행하였음
		// 선택한 시군구의 text를 가져와서 시군구 -> 선택한 시군구 명으로 변경
	});
	
/* 	function choosecity(cityIdx) {
		// chooseprovince와 같은 맥락 
		console.log(cityIdx);

		// 선택한 시군구의 idx로 하위 매장들의 리스트를 가져오려고 한다.
		$.ajax({
			type:'GET',
			url:'/store/filter.do',
			data:{'idx':cityIdx},
			success:function(data) {
				console.log(data);
				storeFilterDraw(data);
				// 위에서 실행한 함수의 같은 기능
			},
			error:function(e) {
				console.log(e);
			}
		});
		
	} */
	
	
	function choosecity(cityIdx){
		
		var table = $('#storelist').DataTable(
				{
					destroy : true,
					aaSorting : [],
					"dom": 'frtp',
					bAutoWidth: false,
					serverSide : false,
					ajax : {
						"url" : "/store/filter.do",
						"type" : "get",
						"data" : {'idx':cityIdx}
					},
					columns : [
							{
								data : "shop_idx",
							},
							{
								data : "shop_name",
								"render" : function(data, type, row) {
									if (type == 'display') {
										
											data = '<a href="/storeDetail.go?shop_idx='+row.shop_idx+'">'+row.shop_name+'</a>';
									}
									return data;
								}
							},
							{
								data : "emp_name",
								
							},
							{
								data : "shop_contact"
							}
							
						],
					
					columnDefs : [
						{
							target : [0],
							
							searchable : false,

							visible : false
						}
					]

				});
	}
	

</script>
</html>