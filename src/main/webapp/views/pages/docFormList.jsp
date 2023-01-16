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
	#docformdiv{
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
	
 	#docformbtn {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 9pt;
		margin-top: -1%;
	}
	
	#docFormDelBtn {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 9pt;
	}
	
	#docFormSearchBtn {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 9pt;
	}
	
	.modal fade bs-example-modal-sm {
		width: 100%;
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
	
	.col-sm-6 {
		flex: 100%;
		max-width: 100%;
	}
	
	.row {
		width: 100%;
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
			<div id="docformdiv">
			<div class="table-responsive">
				<div style="width:100%"><h3 style="float:left">결재 문서 양식</h3></div>
				<input type="hidden" value="${auth_type}" id="auth_type"/>
 				<div style="width:100%">
				
				<select id="lineup" style="float: right" onchange="listCall(doc_type,lineup,auth_type)">
					<option value="최신순" selected="selected">최신순</option>
					<option value="조회수">조회수</option>
					<option value="사용수">사용수</option>
				</select>
				
				<select id="doc_type" name="doc_type" style="float: right" onchange="listCall(doc_type,lineup,auth_type)">
					<option value="all" selected="selected">전체</option>
					<option value="DT001">품의서</option>
					<option value="DT002">지출결의서</option>
					<option value="DT003">세금계산서</option>
					<option value="DT004">전표</option>
					<option value="DT005">기안서</option>
					<option value="DT006">제안서</option>
					<option value="DT007">보고서</option>
				</select>
				
				<button type="button" onclick="location.href='/docFormWrite.go'" class="btn btn-round btn-info" id="docformbtn" style="float:right">양식작성</button>
				</div> 
				
					<table id="docform" class="table table-striped table-bordered bulk_action" style="width:100%">
						
						<thead>
						<tr class="headings">
						<%-- <c:if test="${auth_type ne '2'}"> --%>
							<th><input type="checkbox" id="check-all" class="flat"></th>
						<%-- </c:if> --%>
							<th>양식 idx</th>
							<th>양식 종류</th>
							<th>제목</th>
							<th>작성자</th>
							<th>사용수</th>
							<th>조회수</th>
						</tr>
						</thead>

<!-- 						<tbody id="docformlist">

						</tbody> -->
					</table>
					<!-- <button type="button" onclick="docFormDelete()" class="btn btn-round btn-info" id="docFormDelBtn" style="float:left">삭제</button> -->
					 
					<button type="button" class="btn btn-round btn-info" data-toggle="modal" data-target=".bs-example-modal-sm" id="docFormDelBtn" style="float:left">삭제</button>
					<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-hidden="true">
						<div class="modal-dialog modal-sm">
  							<div class="modal-content">

    							<div class="modal-header">
     								 <h5 class="modal-title" id="myModalLabel2">서식 삭제</h5>
      								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
    							</div>
							    <div class="modal-body">
									<h6>정말 삭제하시겠습니까?</h6>
								</div>
							    <div class="modal-footer">
							      <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
							      <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="docFormDelete()">확인</button>
							    </div>
							</div>
						</div>
					</div>
					
					<div class="modal fade bs-example-modal-sm" id="secondmodal" tabindex="-1" role="dialog" aria-hidden="true">
						<div class="modal-dialog modal-sm">
  							<div class="modal-content">

    							<div class="modal-header">
     								 <h5 class="modal-title" id="myModalLabel2">서식 삭제</h5>
      								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
    							</div>
							    <div class="modal-body">
									<h6 id="modaltitle"></h6>
								</div>
							    <div class="modal-footer">
							      <button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
							    </div>
							</div>
						</div>
					</div>
					
					
					<!-- 
					<div style="width:100%; float: left">
					<button type="button" class="btn btn-round btn-info" id="docFormDelBtn">삭제</button>
					</div>
					<div class="col-sm-7">
					<div class="dataTables_paginate paging_simple_numbers" id="datatable_paginate"><ul class="pagination">
						<li class="paginate_button previous disabled" id="datatable_previous"><a href="#" aria-controls="datatable" data-dt-idx="0" tabindex="0">Previous</a></li>
						<li class="paginate_button active"><a href="#" aria-controls="datatable" data-dt-idx="1" tabindex="0">1</a></li><li class="paginate_button "><a href="#" aria-controls="datatable" data-dt-idx="2" tabindex="0">2</a></li><li class="paginate_button "><a href="#" aria-controls="datatable" data-dt-idx="3" tabindex="0">3</a></li><li class="paginate_button "><a href="#" aria-controls="datatable" data-dt-idx="4" tabindex="0">4</a></li><li class="paginate_button "><a href="#" aria-controls="datatable" data-dt-idx="5" tabindex="0">5</a></li><li class="paginate_button "><a href="#" aria-controls="datatable" data-dt-idx="6" tabindex="0">6</a></li><li class="paginate_button next" id="datatable_next"><a href="#" aria-controls="datatable" data-dt-idx="7" tabindex="0">Next</a></li></ul></div></div>
				 -->
				</div>
			</div>
			</div>
			<!-- /page content -->

		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>

listCall(doc_type,lineup);

// var auth_type = $("#auth_type").val();
// console.log("관리자 권한: "+auth_type);


function listCall(doc_type,lineup) {
	var doc_type_value = doc_type.value;
	var lineup_value = lineup.value;
	var auth_type = $("#auth_type").val();
	console.log(doc_type_value);
	console.log(lineup_value);
	console.log("관리자 권한: "+auth_type);

		
	var table = $('#docform').DataTable(
			{
				destroy : true,
				aaSorting : [],
				"dom": 'frtp',
				pageLength : 15,
				serverSide : false,
				ajax : {
					"url" : "docForm/list.do",
					"type" : "get",
					"data" : {
								"doc_type" : doc_type_value,
								 "lineup" : lineup_value
							}
				},
				columns : [
						{
							data : "doc_form_idx",
 							"render" : function(data, type, row) {
									if (type == 'display') {
										if(auth_type != '2'){
											data = '<input type="checkbox" value='+row.doc_form_idx+' class="flat" name="table_records">';											
										}
									}
								return data;
							} 
						},
						{
							data : "doc_form_idx"
						},
						{
							data : "doc_type_name"
						},
						{
							data : "doc_form_name",
								"render" : function(data, type, row) {
									
									if (type == 'display') {
											data = '<a href="/docFormDetail.go?doc_form_idx='+row.doc_form_idx+'">'+row.doc_form_name+'</a>';
									}
									return data;
								}
						},
						{
							data : "emp_name"

						},
						{
							data : "doc_form_use"

						},
						{
							data : "doc_form_upHit"
						}
						
					],
				columnDefs : [ {

					targets : [ 1 ],

					searchable : false,

					visible : false

				},
				{

					targets : [ 0 ],

					searchable : false,

					visible : true,
					
					/* render: function (data, row) {
		                if ($("#auth_type").val() != "2") {
		                  return data;
		                } 
		              } */
					
					}
			              
				]

			});

}


//function listCall_order(order)

// var doc_type = $("#doc_type option:selected").val();
// console.log(doc_type);
//var lineup = $("#lineup option:selected").val();
/*
function listCall(){
	$.ajax({
		type:'get',
		url:'docForm/list.do',
		data:{
			'doc_type': doc_type.options[doc_type.selectedIndex].value,
			'lineup' : lineup.options[lineup.selectedIndex].value
			},
		dataType:'JSON',
		success:function(data){
			drawList(data.docformlist);
		},
		error:function(e){
			console.log(e);
		}
	});
}

function drawList(list){
	var content = '';
	if(list.length>0){
		for(var i=0; i<list.length; i++){
			content += '<tr class="even pointer">';
			content += '<td class="a-center "><input type="checkbox" value='+list[i].doc_form_idx+' class="flat" name="table_records"></td>';
			content += '<td>'+list[i].doc_type_name+'</a></td>';
			content += '<td><a href="docFormDetail.go?doc_form_idx='+list[i].doc_form_idx+'">'+list[i].doc_form_name+'</a></td>';
			content += '<td>'+list[i].emp_name+'</td>';
			content += '<td>'+list[i].doc_form_use+'</td>';
			content += '<td>'+list[i].doc_form_upHit+'</td>';
			content += '</tr>';
		}
	}
	$('#docformlist').empty();
	$('#docformlist').append(content);
}
*/
/*
var flag = true;

function flags(){
	if(!flag){
		flag = true;
	}
}

function drawPage(){
	var paging = "";
	$("#page").empty();
	paging += "<td colspan='3' id='paging'>";
	paging += "<div>";
	paging += "<nav aria-label='Page navigation' style='text-align:center'>";
	paging += "<ul class='pagination' id='pagination'></ul>"
	paging += "</nav></div></td>";
	$("#page").append(paging);
}


function docFormSearch(){
	var keyword = document.getElementById('searchInput').value;
	console.log("검색어: "+keyword);
	$.ajax({
		url:"docForm/search.do",
		type:"get",
		dataType:"JSON",
		data:{"keyword":keyword},
		success:function(data){
			console.log(data.searchlist);
			drawList(data.searchlist);
		}
			
	})
}
*/
$("#check-all").click(function(){
	   var $chk = $('input[type="checkbox"]');
	   console.log($chk);
	   
	   if($(this).is(':checked')){
	      $chk.prop('checked',true);
	   }else{
	      $chk.prop('checked',false);
	   }
});

function docFormDelete(){
	var chkArr=[];
	
	$('input[type="checkbox"]:checked').each(function(idx,item){
		if($(this).val()!='on'){
			chkArr.push($(this).val());
		}
	});
	console.log(chkArr);
	
	$.ajax({
	      type:'get',
	      url:'docForm/delete.do',
	      data:{'docFormDeleteList':chkArr},
	      dataType:'JSON',
	      success:function(data){
	         console.log(data);
	         var display = document.getElementById('modaltitle');
	         if(data.msg != ""){
//				alert(data.msg);
				display.innerHTML = data.msg;
				$('#secondmodal').modal();
				listCall(value);
//	         	listCall(showPage);
	         }
	         
	      },
	      error:function(e){
	         console.log(e);
	      }
	   }); 
	
	/*
	var result = confirm("정말 삭제하시겠습니까?");
	if(result == true){
		$.ajax({
		      type:'get',
		      url:'docForm/delete.do',
		      data:{'docFormDeleteList':chkArr},
		      dataType:'JSON',
		      success:function(data){
		         console.log(data);
		         if(data.msg != ""){
					alert(data.msg);
					listCall();
	//	         	listCall(showPage);
		         }
		         
		      },
		      error:function(e){
		         console.log(e);
		      }
		   }); 
	}else if(result == false){
		return false;
	}
	*/
}



</script>
</html>