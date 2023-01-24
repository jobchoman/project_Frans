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
.orderData{
	display: none;
}

#delButton{
	background-color:#2A3F54;
   border-color:#2A3F54;
   font-size: 8pt;
}

#listBtn{
	float: left;
}

#listBtn{
	font-size: 8pt;
}
.addWrap{
	overflow:hidden;
}
#cardBox{
	overflow:hidden;
}
#noti_idx_hidden{
	display: none;
}

.table-bordered td, .table-bordered th{
	border: none;
}

.table-bordered{
	border: none;
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
			<div class="right_col addWrap" role="main">

				<div class="x_content">
					<div class="col-md-12 col-sm-12 ">
						<div class="x_panel">
							<div class="x_title"></div>
							<div class="x_content">
								<div class="row">
									<div class="col-sm-12">
										<div id="cardBox" class="card-box table-responsive">
											<form action="/noti/notiDelete.do" method="post">
												<table id="notiListBox"
													class="table table-striped table-bordered bulk_action"
													style="width: 100%" aria-describedby="datatable_info">
													<h3>알림 리스트</h3>
													<h4></h4>
													<thead>
														<tr>
															<th><input type="checkbox" id="check-all"></th>														
															<th id="noti_idx_hidden">noti_idx</th>
															<th>보낸사람</th>
															<th>알림 내용</th>
															<th>받은시간</th>
															<th>확인시간</th>			
														</tr>
													</thead>


			
												</table>
												<div id="listBtn">
												<button onclick = "$('#secondmodal').modal()" type="button" class="btn btn-round btn-info"
										id="delButton" value="">삭제</button></div>
											</form>
											
										</div>			
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="modal fade bs-example-modal-sm" id="secondmodal" tabindex="-1" role="dialog" aria-hidden="true">
                  <div class="modal-dialog modal-sm">
                       <div class="modal-content">

                         <div class="modal-header">
                              <h5 class="modal-title" id="myModalLabel2">알림 삭제</h5>
                              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                         </div>
                         <div class="modal-body">
                           <h6 id="modaltitle">알림을 삭제하시겠습니까?</h6>
                        </div>
                         <div class="modal-footer">
                           <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                           <button id="delModalBtn" onclick="delNoti()" type="button" class="btn btn-primary">확인</button>
                         </div>
                     </div>
                  </div>
               </div>
				<!-- /page content -->
			</div>
		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>
console.log("시작1");
notiListCall();
function notiListCall() {
	
		
	var table = $('#notiListBox').DataTable(
			{
				"bDestroy": true,
				destroy : true,
				"dom": 'frtp',
				aaSorting : [],
				serverSide : false,
				bAutoWidth: false,
				ajax : {
					"url" : "/noti/notiListBox.ajax",
					"type" : "get",
					"data" : 
					{
						
					}
				},
				columns : [
						{
							data : "noti_pk",
							"render" : function(data, type, row) {
								if (type == 'display') {
									
										data = '<input name="chk" type="checkbox" id="check-all" value=""></input>';
												
								}
								return data;
							}
						},
												
						{
							data : "noti_idx"
								
							
						},
						{
							data : "emp_name"
						},
						{
							data : "noti_type",
							"render" : function(data, type, row) {
										var noti_type = row.noti_type;
								if (type == 'display') {
										if(row.noti_type == '결재'){
											row.noti_type = '결재 알림입니다.'
										}else if(row.noti_type == '참조'){
											row.noti_type = '참조 알림입니다.'
										}else if(row.noti_type == '결재완료'){
											row.noti_type = '결재가 완료되었습니다.'
										}else if(row.noti_type == '반려'){
											row.noti_type = '결재가 반려되었습니다.'
										}else{
											row.noti_type = '기타 알림입니다.'
										}
										
										
										data = '<a href="#" onclick="notiBoxMove(this.id,this.text)" id="' + row.noti_pk + '">'+ row.noti_type +'</a>';		
								}
								return data;
							}
							
						},
						{
							data : "noti_hour"
						},
						{
							data : "no_date", "defaultContent": "읽지않음"
						}
						
					],
					createdRow: function (row, data, dataIndex, full) {
						$(row).children('td:nth-child(2)').css('display', 'none');
				 },
				columnDefs : [ 
				{
					targets : [ 0 ],

					searchable : false,
					orderable: false

					
				

				},
				{
					targets : [ 1 ],

					searchable : false,
					orderable: false,
					visible : true

					
				

				}
				
				
				]

			});

}

function notiBoxMove(idx,text) {
	console.log("작동");
	console.log("idx : "+idx);
	console.log("text : "+text);
	if(text == "참조 알림입니다."){
		console.log("리스트 참조디테일 이동");
		window.location.href="/signDetail.go?sign_idx="+idx;
	}else if(text == "결재 알림입니다."){
		console.log("리스트 결재 디테일 이동");
		window.location.href="/signDetail.go?sign_idx="+idx;
	}else if(text == "결재가 완료되었습니다."){
		console.log("결재완료 디테일 이동");
		window.location.href="/signDetailTest.do?sign_idx="+idx;
	}
	else if(text == "결재가 반려되었습니다."){
		console.log("반려 디테일 이동");
		var noti_type = '반려';
		window.location.href="/signDenyDetail.do?sign_idx="+idx;
	}else{
		console.log("기타");
	}
}

let orderStateArray = document.querySelectorAll("#notiListBox>tr");
console.log("제발 : "+orderStateArray)
for(let i = 0; i<orderStateArray.length; i++){
	let orderState = orderStateArray[i].children[0];
	console.log("짠 : "+orderState);
	orderState.css("display","none");
/* 	if(orderState == "처리완료"){
		orderStateArray[i].children[0].innerHTML = "";
		console.log()
	} */
}
console.log("짠1 :" + $("#notiType").innerHTML);


$(document).ready(function() {
	$("#check-all").click(function() {
		if($("#check-all").is(":checked")) $("input[name=chk]").prop("checked", true);
		else $("input[name=chk]").prop("checked", false);
	});

	$("input[name=chk]").click(function() {
		var total = $("input[name=chk]").length;
		var checked = $("input[name=chk]:checked").length;

		if(total != checked) $("#check-all").prop("checked", false);
		else $("#check-all").prop("checked", true); 
	});
});

function delNoti() {
	console.log("알림 삭제")
	var rowData = new Array();
	var tdArr = new Array();
	var checkbox = $("input[name=chk]:checked");
	
	// 체크된 체크박스 값을 가져온다
	checkbox.each(function(i) {

		// checkbox.parent() : checkbox의 부모는 <td>이다.
		// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
		var tr = checkbox.parent().parent().eq(i);
		var td = tr.children();
		
		// 체크된 row의 모든 값을 배열에 담는다.
		rowData.push(tr.text());
		
		// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
		var noti_idx = td.eq(1).text();
		
		// 가져온 값을 배열에 담는다.
		tdArr.push(noti_idx);
		
		console.log("noti_idx : " + noti_idx);
		
		$.ajax({
			type : 'get',
			url : '/noti/notiDelete.ajax',
			dataType : 'text',
			data:{noti_idx : noti_idx},
			success : function(data) {
				/* window.location.href = "/orderList.go"; */
				console.log(data);
				if(data == '성공'){
					window.location.href = "/notiList.go";
				}else{
					alert('알림 삭제에 실패했습니다.');
				}
			},
			error : function(e) {
				console.log(e);
			}
		});
	});
}
/* $("#delModalBtn").click(function(){ 
	
}); */











	
</script>
</html>