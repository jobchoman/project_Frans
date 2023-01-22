<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<jsp:include page="css.jsp" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.1/js/bootstrap.min.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=871ee99d1a335f5263332d5425015004871ee99d1a335f5263332d542501500&libraries=services"></script>
</head>
<style>
	.form-control{
		height: 80%;
	}
	
	.addWrap, .x_content {
	   display: flex;
	   flex-direction: column;
	   justify-content: flex-start;
	   align-items: center;
	}
	
	tr, td{
		padding: 2%;
	}
	
	* {
		box-sizing: border-box;
	}

	#myInput {
	  /* background-image: url('/css/searchicon.png'); */
	  background-position: 10px 10px;
	  background-repeat: no-repeat;
	  width: 100%;
	  height: 20%;
	  font-size: 12px;
	  padding: 12px 20px 12px 40px;
	  border: 1px solid #ddd;
	  margin-bottom: 12px;
	}
	
	#myTable {
	  border-collapse: collapse;
	  width: 100%;
	  border: none;
	  font-size: 12px;
	}
	
	#myTable th, #myTable td {
	  text-align: center;
	  padding: 12px;
	}
	
	#myTable tr {
	  border-bottom: none;
	}
	
	#myTable tr.header, #myTable tr:hover {
	  background-color: #f1f1f1;
	}
	
	#shop_regist {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 8pt;
	}
	
	#shopRegist{
		width: 65%;
	}

</style>
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
				<div id="shopWrite" style="width:80%">
					<form action="/store/write.do" method="post">
						<div class="col-md-6 col-sm-6  " style="max-width: 100%">
							<div class="x_panel" style="display: inline-table">
								<div class="x_title">
									<h2>매장 등록</h2>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<!-- 매장이름, 점장, 매장연락처, 주소, 직원수, 평 수 -->
									<table id="shopRegist" style="border: none">
										<tr>
											<th>매장 이름</th>
											<td><input type="text" class="form-control" name="shop_name" id="shop_name" /></td>
										</tr>
										<tr>
											<th>점장</th>
											<td>
												<input type="text" class="form-control" id="shop_manager" name="shop_manager" onclick="managerSearch(event)" readonly />
												<input type="hidden" id="shop_managerId" name="shop_managerId" />
											</td>
										</tr>
										<tr>
											<th>매장 연락처</th>
											<td><input type="text" class="form-control" id="shop_contact" name="shop_contact"></td>
										</tr>
										<tr>
											<th>주소</th>
											<td>
											<div class="form-group">
												<input type="text" class="form-control" id="address" onclick="shopAddr()" placeholder="주소"><br>
												
												<div style="display:inline; float:left; width:50%"><input type="text" class="form-control" name="detailAddress" id="detailAddress" placeholder="상세주소"></div>
												<div style="float:right; width:48%"><input type="text" class="form-control" id="extraAddress" placeholder="참고항목"></div>
												<br/><br/>
												<input type="hidden" id="sido" name="sido"/>
												<input type="hidden" id="sigungu" name="sigungu"/>
												<input type="hidden" id="roadname" name="roadname"/>
												<input type="hidden" id="addressNum" name="addressNum"/>
												<input type="hidden" id="lat" name="lat"/>
												<input type="hidden" id="lon" name="lon"/>
												<input type="hidden" id="fullAddr" name="fullAddr"/>
												
												<div id="map" style="width:500px;height:300px;margin-top:10px"></div>
												<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=871ee99d1a335f5263332d5425015004&libraries=services,clusterer,drawing"></script>
											</div>
											</td>
										</tr>
										<tr>
											<th>직원 수</th>
											<td><input type="number" class="form-control" name="shop_empNum" id="shop_empNum" /></td>
										</tr>
										<tr>
											<th>평 수</th>
											<td><input type="number" class="form-control" name="shop_space" id="shop_space" /></td>
										</tr>
										<tr><th colspan="2">
											<button type="button" onclick="submitConfirm()" class="btn btn-round btn-info" id="shop_regist" style="float:right">등록</button>
										</th></tr>
									</table>

								</div>
							</div>
						</div>
					</form>
				</div>
			</div>


			<!-- modal -->
			<div class="modal fade bs-example-modal-sm" id="searchManager" tabindex="-1" role="dialog" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="searchManagerLabel">점장 검색</h5>
							<input type="hidden">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
						</div>
						<div class="modal-body">
							<div>
								<input type="text" id="myInput" onkeyup="myFunction()" placeholder="이름을 입력하세요" title="Type in a name">

								<table id="myTable">
									<tr class="header">
										<th style="width: 40%;">사번</th>
										<th style="width: 60%;">이름</th>
									</tr>
									<c:forEach items="${managerlist}" var="manager">
										<tr class="memberlistTr" value="${manager.emp_id}" onclick="managerSel(this)">
											<td>${manager.emp_id}</td>
											<td>${manager.emp_name}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /modal -->
			<!-- modal -->
			<div class="modal fade bs-example-modal-sm" id="storeSubmit" tabindex="-1" role="dialog" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">

							<input type="hidden">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
						</div>
						<div class="modal-body">
							<h6>등록하시겠습니까?</h6>
						</div>
						<div class="modal-footer">
							<button type="button" onclick = "modalClose()" class="btn btn-secondary" data-dismiss="modal" style="font-size:9pt">취소</button>
							<button type="submit" class="btn btn-primary" data-dismiss="modal" style="font-size:9pt">확인</button>
						</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /modal -->
			<!-- /page content -->

		</div>
	<jsp:include page="script.jsp" />
</body>
<script>

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
mapOption = {
    center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
    level: 4 // 지도의 확대 레벨
};

//지도를 미리 생성
var map = new daum.maps.Map(mapContainer, mapOption);
//주소-좌표 변환 객체를 생성
var geocoder = new daum.maps.services.Geocoder();
//마커를 미리 생성
var marker = new daum.maps.Marker({
	position: new daum.maps.LatLng(37.537187, 127.005476),
	map: map
});


function shopAddr() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수
            var sido = '';
            var sigungu = '';
            var roadname = '';
            var addressEnglish = '';

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            	sido = data.sido;
            	sigungu = data.sigungu;
            	roadname = data.roadname;
            	addressEnglish = data.addressEnglish;
            	
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
                sido = data.sido;
            	sigungu = data.sigungu;
            	roadname = data.roadname;
            	addressEnglish = data.addressEnglish;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
//            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").focus();
            

        	// 주소로 상세 정보를 검색
        	geocoder.addressSearch(data.address, function(results, status) {
            // 정상적으로 검색이 완료됐으면
            if (status === daum.maps.services.Status.OK) {

                var result = results[0]; //첫번째 결과의 값을 활용

                // 해당 주소에 대한 좌표를 받아서
                var coords = new daum.maps.LatLng(result.y, result.x);
                // 지도를 보여준다.
                mapContainer.style.display = "block";
                map.relayout();
                // 지도 중심을 변경한다.
                map.setCenter(coords);
                // 마커를 결과값으로 받은 위치로 옮긴다.
                marker.setPosition(coords);
                
                console.log(coords.La);
                console.log(coords.Ma);
                document.getElementById("lat").value = coords.La;
                document.getElementById("lon").value = coords.Ma;
            }
        	});
            
            // 도로명 주소 마지막 숫자 받기
            var str = addressEnglish.indexOf(",");
            console.log(str);
            addressNum = addressEnglish.substr(0,str);
            console.log(addressNum);
            
            document.getElementById("sido").value = sido;
            document.getElementById("sigungu").value = sigungu;
            document.getElementById("roadname").value = roadname;
            document.getElementById("addressNum").value = addressNum;
            document.getElementById("fullAddr").value = addr;
            
            
            console.log(addr);
           
        }
    }).open();
}

function myFunction() {
	  var input, filter, table, tr, td, i, txtValue;
	  input = document.getElementById("myInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("myTable");
	  tr = table.getElementsByTagName("tr");
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[1];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    }       
	  }
	}

function managerSearch(event){
	var managerInput = event.target.id;
	console.log(managerInput);
	console.log(event);
	$('#searchManager').modal('show');
}


function managerSel(mgr){
	var mgrId = $(mgr).attr('value');
	var mgrName = $(mgr).children('td:eq(1)').text();

	$("#shop_manager").val(mgrName);
	$("#shop_managerId").val(mgrId);
	
	$('#searchManager').modal('hide');
	
} 

function submitConfirm(){
	$('#storeSubmit').modal('show');
}

function modalClose(){
	$('#storeSubmit').modal('hide');
}

</script>
</html>