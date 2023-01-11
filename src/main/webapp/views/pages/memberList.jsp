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
</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col"><jsp:include page="sidebar.jsp" /></div>

			<!-- top navigation -->
			<div class="top_nav"><jsp:include page="top_nav.jsp" /></div>
			<!-- /top navigation -->

			<!-- page content -->
		<div class="right_col" role="main">

            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 ">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>직원 리스트 <small>Users</small></h2>
                    <div class="clearfix"></div>
                  </div>
                  
                  <div class="x_content">
                      <div class="row">
                          <div class="col-sm-12">
                            <div id="subSel" class="card-box table-responsive">
                            
                            <select id="con" onchange="maListCall()">
								<option class="all" value="all" selected="selected">전체</option>
								<option class="team" value="team" onclick="team()">팀</option>
								<option class="pos" value="pos">직급</option>
								<option class="duty" value="duty">직책</option>
								<option class="state" value="state">상태</option>
							</select>
		
<!-- 							<select name="sel" id="sel" onchange="suListCall()"> -->
<%-- 								<c:forEach items="${stateMem}" var="stateMem"> --%>
<%-- 								<option value="상태">${stateMem.emp_state_name}</option> --%>
<%-- 								</c:forEach> --%>
<%-- 								<c:forEach items="${dutyMem}" var="dutyMem"> --%>
<%-- 								<option value="직책">${dutyMem.duty_name}</option> --%>
<%-- 								</c:forEach> --%>
<%-- 								<c:forEach items="${posMem}" var="posMem"> --%>
<%-- 								<option value="직급">${posMem.pos_name}</option> --%>
<%-- 								</c:forEach> --%>
<%-- 								<c:forEach items="${teamMem}" var="teamMem"> --%>
<%-- 								<option value="팀" class="team">${teamMem.team_name}</option> --%>
<%-- 								</c:forEach> --%>
<!-- 							</select> -->
                            
                    <table id="datatable" class="table table-striped table-bordered dataTable no-footer"
													style="width: 100%" aria-describedby="datatable_info">
                      <thead>
						<tr>
							<th>이름</th>
							<th>이름</th>
							<th>아이디</th>
							<th>팀</th>
							<th>직급</th>
							<th>직책</th>
							<th>상태</th>
						</tr>
					  </thead>
<!-- 					  <tbody id="list"> -->
					   
<%-- 						<c:if test="${list eq null || list eq ''}"> --%>
<!-- 						<tr><td colspan="6">해당 데이터가 존재하지 않습니다.</td></tr> -->
<%-- 						</c:if> --%>
<%-- 						<c:forEach items="${list}" var="member"> --%>
<!-- 							<tr> -->
<%-- 								<td>${member.emp_name}</td> --%>
<%-- 								<td><a href="memberDetail.do?emp_id=${member.emp_id}">${member.emp_id}</a></td> --%>
<%-- 								<td>${member.team_name}</td> --%>
<%-- 								<td>${member.pos_name}</td> --%>
<%-- 								<td>${member.duty_name}</td> --%>
<%-- 								<td>${member.emp_state_name}</td> --%>
<!-- 							</tr> -->
<%-- 						</c:forEach> --%>
						
<!--                       </tbody> -->
                    </table>
                  </div>
                  </div>
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
maListCall();

var sel = $("#sel option:selected").val();
console.log(sel);
var con = $("#con option:selected").val();
console.log(con);
var data = JSON.stringify(jObject);


function maListCall(){
	var table = $("#datatable").DataTable({
		destroy:true,
		serverSide:false,
		ajax:{
			"url":"/selList.ajax",
			"type":"get",
			"data":{
				"con":"all",
				"con":"team",
				"con":"duty",
				"con":"pos",
				"con":"state",
			}
		},
		columns:[
			{data:"emp_name"},
			{data:"emp_id"},
			{data:"emp_id",
				"render" : function(data, type, row) {
					if (type == 'display') {
						
							data = '<a href="/memberDetail.do?emp_id='+row.emp_id+'">'
								+ row.emp_id +'</a>';
					}
					return data;
				}
			},
			{data:"team_name"},
			{data:"pos_name"},
			{data:"duty_name"},
			{data:"emp_state_name"}
		],
        columnDefs: [{
        	
		targets : [ 1 ],

		searchable : false,

		visible : false     

        }]

	});
	subListCall();
}

function subListCall() {
	$.ajax({
		type:'get',
		url:'/subSel.ajax',
		data:{
			"data":
			"data":
		},
		dataType:'JSON',
		success:function(data){
			drawList(data.list);

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
			content += '<select name="sel" id="sel" onchange="suListCall()">';
			content += '<c:forEach items="'+${stateMem}+'" var="stateMem">';
			content += '<option value="상태">'+list[i].emp_state_name+'</option>';
			content += '<td><a href="docFormDetail.go?doc_form_idx='+list[i].doc_form_idx+'">'+list[i].doc_form_name+'</a></td>';
			content += '</c:forEach>';
			content += '</select>';
		}
	}
	$('#subSel').empty();
	$('#subSel').append(content);
}




// function suListCall(obj){
// 	console.log(obj);
// }

function team(){
	var tea = $(".team").val();
	console.log(tea);
}
var sel1 = $(".sel option:selected").val();
console.log(sel1);



// function suListCall(){
// 	var table = $("#datatable").DataTable({
// 		destroy:true,
// 		serverSide:false,
// 		ajax:{
// 			"url":"/selList.ajax",
// 			"type":"get",
// 			"data":{
// 				"con":"all",
// 				"con":"team",
// 				"con":"duty",
// 				"con":"pos",
// 				"con":"state",
// 			}
// 		},
// 		columns:[
// 			{data:"emp_name"},
// 			{data:"emp_id"},
// 			{data:"emp_id",
// 				"render" : function(data, type, row) {
// 					if (type == 'display') {
						
// 							data = '<a href="/memberDetail.do?emp_id='+row.emp_id+'">'
// 								+ row.emp_id +'</a>';
// 					}
// 					return data;
// 				}
// 			},
// 			{data:"team_name"},
// 			{data:"pos_name"},
// 			{data:"duty_name"},
// 			{data:"emp_state_name"}
// 		],
//         columnDefs: [{
        	
// 		targets : [ 1 ],

// 		searchable : false,

// 		visible : false     

//         }]

// 	});
// }



// function drawList(list){
// 	var content = '';
// 	if(list.length>0){
// 		for(var i=0; i<list.length; i++){
// 			content += '<tr>';
// 			content += '<td>'+list[i].emp_name+'</td>';
// 			content += '<td><a href="memberDetail.do?emp_id='+list[i].emp_id+'">'+list[i].emp_id+'</a></td>';
// 			content += '<td>'+list[i].team_name+'</a></td>';
// 			content += '<td>'+list[i].pos_name+'</td>';
// 			content += '<td>'+list[i].duty_name+'</td>';
// 			content += '<td>'+list[i].emp_state_name+'</td>';
// 			content += '</tr>';
// 		}
// 	}
// 	$('#list').empty();
// 	$('#list').append(content);
// }
/*
<tr>
<td>${member.emp_name}</td>
<td><a href="memberDetail.do?emp_id=${member.emp_id}">${member.emp_id}</a></td>
<td>${member.team_name}</td>
<td>${member.pos_name}</td>
<td>${member.duty_name}</td>
<td>${member.emp_state_name}</td>
</tr>
*/
</script>
</html>