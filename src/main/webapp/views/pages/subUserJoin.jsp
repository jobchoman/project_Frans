<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- <script src="//dmaps.daum.net/map_js_init/postcode.v2.js"></script> -->


<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<jsp:include page="css.jsp" />
<style type="text/css">
.nam{
	background-color:#2A3F54;
    border-color:#2A3F54;
    font-size: 8pt;
}
.ghl{
    font-size: 8pt;
}
.addWrap {
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: center;
 }
 .offset-md-3{
 	text-align: right;
 	 margin-left:50%;
 }
.row > .col-sm-6:first-child {
      display: none;
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
			<div class="right_col addWrap" role="main" >
				<div class="" style="width: 100%">
					<div class="page-title">

					</div>
					<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
							
								<div class="x_title">
									<h2>회원 구독권 등록</h2>

									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<br />
									<form action="subUserJoin.do" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" method="POST" enctype="multipart/form-data">


										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">이용권 이름
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="sub_name" id="value" class="value" onclick="subSearch_popup(event)" class="form-control "/>
												<input type="hidden" name="sub_idx" id="sub_idx"/> 
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">회원이름
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="client_name" id="client" class="client" onclick="clientSearch_popup(event)" class="form-control "/>
												<input type="hidden" name="client_id" id="client_id"/> 
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">시작일
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="date" name="sub_use_start" class="form-control "/>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">종료일
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="date" name="sub_use_end" class="form-control "/>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">횟수/요일
											</label>
											<div class="col-md-6 col-sm-6 ">
												<div class="col-md-6 col-sm-6 ">
												<select name="sub_use_condition" class="form-control ">
												  <option value="횟수" selected="selected">횟수</option>
												  <option value="요일">요일</option>
											    </select>
											</div>
											</div>
										</div>
												
										<div class="ln_solid"></div>
										<div class="item form-group">
											<div class="col-md-6 col-sm-6 offset-md-3">
												<button class="btn btn-round btn-secondary ghl" onclick="location.href='subUserList.go'" type="reset">직원리스트</button>
												<button type="submit" id="maker" class="btn btn-round btn-info nam save">등록</button>
											</div>
										</div>
									</form>
									
									
				<!-- modal -->
				<div class="modal fade bs-example-modal-lg" id="subSearch" tabindex="-1" role="dialog" aria-hidden="true">
					<div class="modal-dialog modal-lg">
	  					<div class="modal-content">
	  						<div class="modal-header">
	     						<h5 class="modal-title" id="subSearchLabel">구독권 검색</h5>
	     						<input type="hidden" >
	      						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	    					</div>
							<div class="modal-body">
								
							<div class="card-box table-responsive">
								<table id="datatable" class="table table-striped table-bordered" style="width:100%">
									<thead>
										<tr>
											<th>구독권 코드</th>
											<th>구독권 이름</th>
											<th>구독권 가격</th>
											<th>구독권 상태</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${list}" var="list">
									<c:if test="${list.sub_state == '진행중'}">
										<tr class = "memberlistTr" value="${list.sub_name}" onclick="subSel(this)">				
											<td id="subIdx">${list.sub_idx}</td>
											<td>${list.sub_name}</td>
											<td>${list.sub_price}</td>
											<td>${list.sub_state}</td>
										</tr>
									</c:if>
									</c:forEach>
									</tbody>
								</table>
							</div>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /modal -->
				<!-- modal -->
				<div class="modal fade bs-example-modal-lg" id="clientSearch" tabindex="-1" role="dialog" aria-hidden="true">
					<div class="modal-dialog modal-lg">
	  					<div class="modal-content">
	  						<div class="modal-header">
	     						<h5 class="modal-title" id="clientSearchLabel">회원 검색</h5>
	     						<input type="hidden" >
	      						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	    					</div>
							<div class="modal-body">
								
							<div class="card-box table-responsive">
								<table id="datatable" class="table table-striped table-bordered" style="width:100%">
									<thead>
										<tr>
											<th>회원 아이디</th>
											<th>회원 이름</th>
											<th>회원 생년월일</th>
											<th>회원 전화번호</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${mem}" var="mem">
										<tr class = "memberlistTr" value="${mem.client_name}" onclick="cliSel(this)">
											<td>${mem.client_id}</td>
											<td>${mem.client_name}</td>
											<td>${mem.client_birth}</td>
											<td>${mem.client_phone}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /modal -->
								</div>
							</div>
						</div>
					</div>
					</div>
					</div>
			<!-- /page content -->

			<!-- footer content -->
			<footer><jsp:include page="footer.jsp" /></footer>
			<!-- /footer content -->
		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>

<script>
var msg = "${msg}";

if(msg != ""){
	alert(msg);
}


function addr() {
    new daum.Postcode({
        oncomplete: function(data) {
        	// 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R' || data.userSelectedType === 'J') { // 사용자가 주소를 선택했을 경우
                addr = data.roadAddress;
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
            } 
            
            var zoneCode = '(' + data.zonecode + ') ';
            
            addr = zoneCode + addr + extraAddr;
            document.getElementById("client_address").value = addr;
            document.getElementById("address_detail").focus();
        }
    }).open();
}


function subSearch_popup(event){
	console.log(document.getElementById("subSearch"));
	empInputIdx = event.target.id;
	console.log(empInputIdx);
	
	$('#subSearch').modal('show');
}

function clientSearch_popup(event){
	console.log(document.getElementById("clientSearch"));
	empInputIdx = event.target.id;
	console.log(empInputIdx);
	
	$('#clientSearch').modal('show');
}
 
// function subSearch() {
// 	// 함수 동작 테스트 
// 	//alert("팝업 테스트");
	
// 	//window.open("[팝업을 띄울 파일명 path]", "[별칭]", "[팝업 옵션]")
// 	var openWin = window.open("subSearchPop.go", "mypopup", "width=500, height=600, top=150, left=200");
// 	openWin.document.getElementById("value").value = "전달하고자 하는 값";
// }

function subSel(elem){
	console.log($(elem));
//		var empName = $(this).attr('value');
//		var empName = $(elem);
		var subIdx = $(elem).children('td:eq(0)').text();
		var subName = $(elem).children('td:eq(1)').text();
		console.log("클릭한 구독권 코드: "+subIdx); 
		console.log("클릭한 구독권 이름: "+subName); 
		$("#value").val(subName);
		$("#sub_idx").val(subIdx);
		

		$('#subSearch').modal('hide');
}

function cliSel(elem){
	console.log($(elem));
//		var empName = $(this).attr('value');
//		var empName = $(elem);
		var clientId = $(elem).children('td:eq(0)').text();
		var cliName = $(elem).children('td:eq(1)').text();
// 		console.log("선택할 회원 아이디: "+cliId); 
		console.log("선택할 회원 이름: "+cliName); 
		console.log("선택할 회원 아이디: "+clientId);
		$("#client").val(cliName);
		$("#client_id").val(clientId);

		$('#clientSearch').modal('hide');
}

$(".save").click(function(){
	if(confirm('등록하시겠습니까?'))  
		return true;
	else  
		return false;
});



</script>
</html>