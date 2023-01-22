<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.1/css/bootstrap.min.css">
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.1/js/bootstrap.min.js"></script>
<jsp:include page="css.jsp" />
<style>
	#signlistdiv{
		display: flex;
		justify-content: center;
   		align-items: center;
   		flex-direction: column;
   		width: 80%;
	}
	
	.addWrap {
	   display: flex;
	   flex-direction: column;
	   justify-content: flex-start;
	   align-items: center;
	}
	
	#dateSearch {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 9pt;
	}
	
	#reflist {
		width:100%;
		white-space: nowrap;
		text-align: center;
		border: none;
	}
	
	.calDiv > * {
		margin-top: auto;
		margin-bottom: auto;
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
			<div id="signlistdiv">
				<div style="width:100%"><h3 style="float:left">결재 문서</h3></div>
				<br/>
				<div class="calDiv" style="width:100%">
					<div class="input-group" style="float: left">
						<h6 style="font-weight: bold">기간</h6>
						&nbsp;&nbsp;&nbsp;
						<p>시작일</p>
						&nbsp;&nbsp;
						<fieldset>
                          <div class="control-group">
                            <div class="controls">
                              <div class="col-md-11 xdisplay_inputx form-group row has-feedback">
                                <input type="text" class="form-control has-feedback-left" name="single_cal1" id="single_cal1" placeholder="First Name" aria-describedby="inputSuccess2Status">
                                <span class="fa fa-calendar-o form-control-feedback left" aria-hidden="true"></span>
                                <span id="inputSuccess2Status" class="sr-only">(success)</span>
                              </div>
                            </div>
                          </div>
                        </fieldset>
                        
                        <p>종료일</p>
                        &nbsp;&nbsp;
                        <fieldset>
                          <div class="control-group">
                            <div class="controls">
                              <div class="col-md-11 xdisplay_inputx form-group row has-feedback">
                                <input type="text" class="form-control has-feedback-left" name="single_cal2" id="single_cal2" placeholder="First Name" aria-describedby="inputSuccess2Status2">
                                <span class="fa fa-calendar-o form-control-feedback left" aria-hidden="true"></span>
                                <span id="inputSuccess2Status2" class="sr-only">(success)</span>
                              </div>
                            </div>
                          </div>
                        </fieldset>
						<span class="input-group-btn">
							<button type="button" class="btn btn-primary" id="dateSearch" onclick="seldate(single_cal1,single_cal2)">검색</button>
						</span> 
                      </div>
                        
				</div>

				<div class="container">
				<ul class="nav nav-tabs bar_tabs" id="myTab" role="tablist">
					<li class="nav-item"><a class="nav-link active" id="allList" data-toggle="tab" role="tab" aria-selected="true" onclick="listCall(single_cal1,single_cal2)">결재전</a></li>
					<li class="nav-item"><a class="nav-link" id="signEnd" data-toggle="tab" role="tab" aria-selected="false" onclick="endList(single_cal1,single_cal2)">결재완료</a></li>
					<li class="nav-item"><a class="nav-link" id="signReturn" data-toggle="tab" role="tab" aria-selected="false" onclick="returnList(single_cal1,single_cal2)">반려</a></li>
				</ul>
				</div>
				
				<div class="table-responsive">
					<table id="reflist" class="table table-striped table-bordered bulk_action">
						<thead>
						<tr class="headings">
							<th style="width:10%">문서번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>문서결재상태</th>
						</tr>
						</thead>

					</table>
				</div>
				
			</div>
			</div>
			<!-- /page content -->
		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>

$(function() {
	  $('input[name="single_cal1"]').daterangepicker({
	    singleDatePicker: true,
	    startDate: moment().subtract(14, 'days'),
	    locale: {
	        format: 'YYYY-MM-DD'
	      }
	});
});

$(function() {
	  $('input[name="single_cal2"]').daterangepicker({
	    singleDatePicker: true,
	    endDate: moment(),
	    locale: {
	        format: 'YYYY-MM-DD'
	      }
	});
});


listCall(single_cal1,single_cal2);

function listCall(single_cal1, single_cal2) {
	var date1 = single_cal1.value;
	var date2 = single_cal2.value;

	
	var table = $('#reflist').DataTable(
			{
				destroy : true,
				aaSorting : [],
				"dom": 'frtp',
				bAutoWidth: false,
				serverSide : false,
				ajax : {
					"url" : "sign/list_refermem.do",
					"type" : "get",
					"data" : {
						"date1" : date1,
						"date2" : date2
					}
				},
				columns : [
						{
							data : "sign_idx",
						},
						{
							data : "sign_title",
							"render" : function(data, type, row) {
								if (type == 'display') {
									
										data = '<a href="/signDetail.go?sign_idx='+row.sign_idx+'">'+row.sign_title+'</a>';
								}
								return data;
							}
						},
						{
							data : "emp_name"
						},
						{
							data : "sign_date"

						},
						{
							data : "sign_state_type",
							"render" : function(data, type, row) {
								if (type == 'display') {
									if (row.sign_state_type == '결재완료'){
										data = '<span style="font-weight:bold;color:red">'+row.sign_state_type+'</span>';
									}else if (row.sign_state_type == '반려'){
										data = '<span style="font-weight:bold;color:orange">'+row.sign_state_type+'</span>';
									}else if (row.sign_state_type == '결재전'){
										data = '<span>'+row.sign_state_type+'</span>';
									}
									
							}
							return data;
						}
					}],
				
				columnDefs : []

			});

}

function endList(single_cal1,single_cal2){
	
	var date1 = single_cal1.value;
	var date2 = single_cal2.value;

	
	var table = $('#reflist').DataTable(
			{
				destroy : true,
				aaSorting : [],
				"dom": 'frtp',
				bAutoWidth: false,
				serverSide : false,
				ajax : {
					"url" : "sign/endlist_refermem.do",
					"type" : "get",
					"data" : {
						"date1" : date1,
						"date2" : date2
					}
				},
				columns : [
						{
							data : "sign_idx",
						},
						{
							data : "sign_title",
							"render" : function(data, type, row) {
								if (type == 'display') {
									
										data = '<a href="/signDetail.go?sign_idx='+row.sign_idx+'">'+row.sign_title+'</a>';
								}
								return data;
							}
						},
						{
							data : "emp_name"
						},
						{
							data : "sign_date"

						},
						{
							data : "sign_state_type",
							"render" : function(data, type, row) {
								if (type == 'display') {
									if (row.sign_state_type == '결재완료'){
										data = '<span style="font-weight:bold;color:red">'+row.sign_state_type+'</span>';
									}else if (row.sign_state_type == '반려'){
										data = '<span style="font-weight:bold;color:orange">'+row.sign_state_type+'</span>';
									}else if (row.sign_state_type == '결재전'){
										data = '<span>'+row.sign_state_type+'</span>';
									}
									
							}
							return data;
						}

						}
						
					],
				
				columnDefs : []

			});

}


function returnList(single_cal1,single_cal2){
	
	var date1 = single_cal1.value;
	var date2 = single_cal2.value;

	
	var table = $('#reflist').DataTable(
			{
				destroy : true,
				aaSorting : [],
				"dom": 'frtp',
				bAutoWidth: false,
				serverSide : false,
				ajax : {
					"url" : "sign/returnlist_refermem.do",
					"type" : "get",
					"data" : {
						"date1" : date1,
						"date2" : date2
					}
				},
				columns : [
						{
							data : "sign_idx",
						},
						{
							data : "sign_title",
							"render" : function(data, type, row) {
								if (type == 'display') {
									
										data = '<a href="/signDetail.go?sign_idx='+row.sign_idx+'">'+row.sign_title+'</a>';
								}
								return data;
							}
						},
						{
							data : "emp_name"
						},
						{
							data : "sign_date"

						},
						{
							data : "sign_state_type",
							"render" : function(data, type, row) {
								if (type == 'display') {
									if (row.sign_state_type == '결재완료'){
										data = '<span style="font-weight:bold;color:red">'+row.sign_state_type+'</span>';
									}else if (row.sign_state_type == '반려'){
										data = '<span style="font-weight:bold;color:orange">'+row.sign_state_type+'</span>';
									}else if (row.sign_state_type == '결재전'){
										data = '<span>'+row.sign_state_type+'</span>';
									}
									
							}
							return data;
						}

						}
					],
				
				columnDefs : []

			});

}


function seldate(single_cal1,single_cal2){
	
	if($('#allList').attr('aria-selected') == 'true'){
		console.log($('#allList').attr('aria-selected'));
		console.log(single_cal1.value);
		listCall(single_cal1,single_cal2);
		
	}else if($('#signEnd').attr('aria-selected') == 'true'){
		endList(single_cal1,single_cal2);
			
	}else if($('#signReturn').attr('aria-selected') == 'true'){
		returnList(single_cal1,single_cal2);				
	}
}


</script>
</html>