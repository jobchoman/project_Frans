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
#writeButton{
	background-color:#aa5ed0;
   border-color:#aa5ed0;
   font-size: 8pt;
}
#writeButton:hover{
	background-color:orange;
	border-color:orange;
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
#myTabDiv{
	float: right;
}
#addSignEmp, #addRefEmp {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 9pt;
		padding: revert;
	}
	button[id^=delEmp]{
		font-size: 9pt;
		padding: revert;
		width: 22.41px;
		margin-left: 0.7%;
	}
	
#contentSize{
	font-size: 4pt;
	color: gray;
}
.table > th{
 text-align: center;
}
.table > th > p{
 text-align: center;
}

#datatable_length {
		display: none;
	}
	
	.row > .col-sm-6:first-child {
		display: none;
	}

	#datatable_info {
		display: none;
	}
	
	textarea{
		width: 500px;
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
												<table id="msgListBox"
													class="table table-striped table-bordered bulk_action"
													style="width: 100%" aria-describedby="datatable_info">
													<h3>쪽지 리스트</h3>
													<div id="myTabDiv">
													<ul class="nav nav-tabs bar_tabs" id="myTab" role="tablist">
													<button onclick = "$('#msgWrite').modal()" type="button" class="btn btn-round btn-info"
										id="writeButton" value="">작성</button>
														<li class="nav-item"><a class="nav-link active"
															id="home-tab" data-toggle="tab" role="tab"
															aria-selected="true" onclick="FoodOrderListCall()"
															value="식자재">수신함</a></li>
														<li class="nav-item"><a class="nav-link"
															id="profile-tab" data-toggle="tab" role="tab"
															aria-selected="false" onclick="SubOrderListCall()"
															value="부자재">발신함</a></li>
													</ul>
													</div>
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
				
				<!-- 메시지 작성창 -->
				
				 <div class="modal fade bs-example-modal-sm" id="msgWrite" tabindex="-1" role="dialog" aria-hidden="true">
               <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                       <div class="modal-header">
                          <h5 class="modal-title" id="searchEmpLabel">쪽지 작성</h5>
                           <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                      </div>
                      <form action="/msg/write.do" method="post">
                     <div class="modal-body">
							
								<table class="table">
									<tr>
										<th>받는사람</th>
										<td>
											<div id="sign">
												<div id="emp1">
													<!-- <input type="text" class="empName1" name="empName1" id="empName1" placeholder="사원 검색" onclick="empSearch_popup(event)" readonly> -->
													<input type="text" class="empName1" name="empName"
														id="empName1" placeholder="사원 검색"
														onclick="empSearch_popup(event)" readonly> <input
														class="empIdx_input1" name="empIdx_input"
														readonly="readonly" type="hidden" />
													<button type="button" id="addSignEmp" name="addSignEmp"
														onclick="clickP()" class="btn btn-round btn-info">+</button>
												</div>
											</div>
										</td>
									</tr>
									<th>내용 <p id="contentSize">(최대 500자)</p></th>
									<td>
										<textarea id="content" name="msg_content" cols="50" rows="10"></textarea>
									</td>
					
                       
								</table>
								
								<!--  -->

							</div>
                     <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">작성</button>
                     </div>
                     </form>
                  </div>
               </div>
            </div>
			<!-- /메시지 작성창 끝 -->	
			
			
			<!-- 사원 검색 modal -->
				<div class="modal fade bs-example-modal-lg" id="searchEmp" tabindex="-1" role="dialog" aria-hidden="true">
					<div class="modal-dialog modal-lg">
	  					<div class="modal-content">
	  						<div class="modal-header">
	     						<h5 class="modal-title" id="searchEmpLabel">사원 검색</h5>
	     						<input type="hidden" >
	      						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	    					</div>
							<div class="modal-body">
								
							<div class="card-box table-responsive">
								<table id="datatable" class="table table-striped table-bordered" style="width:100%">
									<thead>
										<tr>
											<th>사번</th>
											<th>이름</th>
											<th>직급</th>
											<th>팀</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${memberlist}" var="memberlist" varStatus="idx">
										<tr class = "memberlistTr row_${idx.count}" value="${memberlist.emp_name}" onclick="memberSel(this)">
											<td>${memberlist.emp_id}</td>
											<td>${memberlist.emp_name}</td>
											<td>${memberlist.pos_name}</td>
											<td>${memberlist.team_name}</td>
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
			
			
				
				<div class="modal fade bs-example-modal-sm" id="secondmodal" tabindex="-1" role="dialog" aria-hidden="true">
                  <div class="modal-dialog modal-sm">
                       <div class="modal-content">

                         <div class="modal-header">
                              <h5 class="modal-title" id="myModalLabel2">쪽지 삭제</h5>
                              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                         </div>
                         <div class="modal-body">
                           <h6 id="modaltitle">쪽지를 삭제하시겠습니까?</h6>
                        </div>
                         <div class="modal-footer">
                           <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
                           <button id="delModalBtn" type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
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
const detailReview = document.querySelector('textarea[name="msg_content"]');
detailReview.addEventListener("keyup", e => {
  //console.log(detailReview.value.length);
  if(detailReview.value.length > 500){
    detailReview.value = detailReview.value.substr(0, 500);
  }
});
function clickP(){
//	console.log("사원 추가");
	var addSign = '';
	var cnt = $('#sign div').length;
	console.log(cnt);
	if (cnt<5){
		addSign += '<div class="emp'+(cnt+1)+'">';
		addSign += '<input type="text" class="empName'+(cnt+1)+'" name="empName" id="empName'+(cnt+1)+'" placeholder="사원 검색" onclick="empSearch_popup(event);" readonly>';
		addSign += '<input class="empIdx_input'+(cnt+1)+'" name="empIdx_input" readonly="readonly" type="hidden" />';
		addSign += '<button type="button" id="delEmp'+(cnt+1)+'" class="btn btn-round btn-secondary">-</button>';
		addSign += '</div>';
		$("#sign").append(addSign);
	}else {
		return false;
	}
}
$(document).on("click","#delEmp2",function(){
	$('.emp2').remove();
   
	for(var i=0; i<3; i++){
		$(".emp"+(i+3)).attr("class","emp"+(i+2));
		$(".empName"+(i+3)).attr("class","empName"+(i+2));
		$("#empName"+(i+3)).attr("id","empName"+(i+2));
		$(".empIdx_input"+(i+3)).attr("class","empIdx_input"+(i+2));
		$("#delEmp"+(i+3)).attr("id","delEmp"+(i+2));
	}
});

$(document).on("click","#delEmp3",function(){
	$('.emp3').remove();
	   
	for(var i=0; i<2; i++){
		$(".emp"+(i+4)).attr("class","emp"+(i+3));
		$(".empName"+(i+4)).attr("class","empName"+(i+3));
		$("#empName"+(i+4)).attr("id","empName"+(i+3));
		$(".empIdx_input"+(i+4)).attr("class","empIdx_input"+(i+3));
		$("#delEmp"+(i+4)).attr("id","delEmp"+(i+3));
	}
});
	
$(document).on("click","#delEmp4",function(){
	$('.emp4').remove();
	var i = 0;	   
		$(".emp"+(i+5)).attr("class","emp"+(i+4));
		$(".empName"+(i+5)).attr("class","empName"+(i+4));
		$("#empName"+(i+5)).attr("id","empName"+(i+4));
		$(".empIdx_input"+(i+5)).attr("class","empIdx_input"+(i+4));
		$("#delEmp"+(i+5)).attr("id","delEmp"+(i+4));
});

$(document).on("click","#delEmp5",function(){
	$('.emp5').remove();
});


/* 결재자 검색 */


var empInputIdx;

function empSearch_popup(event){
	empInputIdx = event.target.id;
	console.log(empInputIdx);
	
	$('#searchEmp').modal('show');
}

function memberSel(elem){
	console.log($(elem));
//		var empName = $(this).attr('value');
//		var empName = $(elem);
		var empName = $(elem).children('td:eq(1)').text();
		var empIdx = $(elem).children('td:eq(0)').text();
		console.log("클릭한 사원 이름: "+empName); 
		console.log("클릭한 사원 사번: "+empIdx); 
		
		if(empInputIdx == "empName1"){
			$("#empName1").val(empName);
			$(".empIdx_input1").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
		}else if (empInputIdx == 'empName2'){
//			debugger;
			$("#empName2").val(empName);
			$(".empIdx_input2").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}else if (empInputIdx == 'empName3'){
			$("#empName3").val(empName);
			$(".empIdx_input3").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}else if (empInputIdx == 'empName4'){
			$("#empName4").val(empName);
			$(".empIdx_input4").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}else if (empInputIdx == 'empName5'){
			$("#empName5").val(empName);
			$(".empIdx_input5").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}else if (empInputIdx == 'ref_empName1'){
			$("#ref_empName1").val(empName);
			$(".ref_empIdx_input1").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}else if (empInputIdx == 'ref_empName2'){
			$("#ref_empName2").val(empName);
			$(".ref_empIdx_input2").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}else if (empInputIdx == 'ref_empName3'){
			$("#ref_empName3").val(empName);
			$(".ref_empIdx_input3").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}else if (empInputIdx == 'ref_empName4'){
			$("#ref_empName4").val(empName);
			$(".ref_empIdx_input4").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}else if (empInputIdx == 'ref_empName5'){
			$("#ref_empName5").val(empName);
			$(".ref_empIdx_input5").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}
		
		$('#searchEmp').modal('hide');
}


//notiListCall();
function notiListCall() {
	
		
	var table = $('#msgListBox').DataTable(
			{
				"bDestroy": true,
				destroy : true,
				"dom": 'frtp',
				aaSorting : [],
				serverSide : false,
				ajax : {
					"url" : "/msg/msgListBox.ajax",
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
		var noti_type = '결재완료';
		window.location.href="/signDetailTest.do?sign_idx="+idx;
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


$("#delModalBtn").click(function(){ 
	
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
});











	
</script>
</html>