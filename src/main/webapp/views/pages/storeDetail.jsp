<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<jsp:include page="css.jsp" />
<style>
	.addWrap, .x_content {
	   display: flex;
	   flex-direction: column;
	   justify-content: flex-start;
	   align-items: center;
	}
	
	tr, td{
		padding: 2%;
	}
	
	#update {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 8pt;
		float: right;
	}
	
	#goList {
		font-size: 8pt;
		margin-right: 2%;
		float:right;
	}
	
</style>

</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col"><jsp:include page="sidebar.jsp" /></div>

			<!-- top navigation -->
			<div class="top_nav"><jsp:include page="top_nav.jsp" />
			
			
			
			</div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col addWrap" role="main">
				<div style="width: 80%">
					<div class="col-md-6 col-sm-6  " style="max-width: 100%">
						<div class="x_panel" style="display: inline-table">
							<div class="x_title">
								<h2>매장 정보</h2>
								<input type="hidden" value="${storedto.shop_idx}"/>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">

								<table id="shopDetail" style="border: none">
									<tr>
										<th>매장 이름</th>
										<td>${storedto.shop_name}</td>
									</tr>
									<tr>
										<th>점장</th>
										<td>${storedto.emp_name}</td>
									</tr>
									<tr>
										<th>매장 연락처</th>
										<td>${storedto.shop_contact}</td>
									</tr>
									<tr>
										<th>주소</th>
										<td>${storedto.shop_location}</td>
									</tr>
									<tr>
										<th>직원 수</th>
										<td>${storedto.shop_emp_num}</td>
									</tr>
									<tr>
										<th>평 수</th>
										<td>${storedto.shop_space}</td>
									</tr>
									<tr>
										<th colspan="2">
											<div id="map" style="width:600px;height:400px"></div>
											<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=871ee99d1a335f5263332d5425015004&libraries=services,clusterer,drawing"></script>
											<!-- <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=871ee99d1a335f5263332d5425015004"></script> -->
										</th>
									</tr>
									<tr>
										<th colspan="2">
											<button type="button" class="btn btn-round btn-info" id="update" onclick="location.href='/store/update.go?shop_idx=${storedto.shop_idx}'">수정</button>
											<button type="button" onclick="location.href='/storeList.go'" class="btn btn-round btn-secondary" id="goList">목록</button>
										</th>
									</tr>
								</table>
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
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = { 
    center: new kakao.maps.LatLng(${storedto.shop_lon}, ${storedto.shop_lat}), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

//마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(${storedto.shop_lon}, ${storedto.shop_lat}); 

mapContainer.style.display = "block";
map.relayout();

//마커를 생성합니다
var marker = new kakao.maps.Marker({
position: markerPosition
});

//마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);
</script>
</html>