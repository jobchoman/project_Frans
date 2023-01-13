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
                    <h2>팀/직급/직책 관리</h2>
                    <div class="clearfix"></div>
                  </div>
                  
                  <div class="x_content">
                      <div class="row">
                          <div class="col-sm-12">
                            <div class="card-box table-responsive">

<!--                      		<select name="info" id="info"> -->
<!-- 								<option id="team" value="team" onclick="teamList();" selected="selected">팀</option> -->
<!-- 								<option id="duty" value="duty" onclick="dutyList();">직책</option> -->
<!-- 								<option id="pos" value="pos" onclick="posList();">직급</option> -->
<!-- 							</select> -->
								<button type="button" class="btn btn-round btn-secondary" id="team" onclick="location.href='memberInfoList.go'">팀</button>
								<button type="button" class="btn btn-round btn-secondary" id="pos" onclick="location.href='memberInfoPosList.go'">직급</button>
								<button type="button" class="btn btn-round btn-secondary" id="duty" onclick="location.href='memberInfoDutyList.go'">직책</button>
                   

							
				
                            
                    <table id="datatable" class="table table-striped table-bordered dataTable no-footer"
													style="width: 100%" aria-describedby="datatable_info">
                      <thead>
						<tr>
							<th>직책코드</th>
							<th>직책명</th>
							<th>상태</th>
							<th>수정</th>
						</tr>
					  </thead>
					  <tbody>
						<c:forEach items="${list}" var="list">
							<tr>
								<td>${list.duty_idx}</td>
								<td>${list.duty_name}</td>
								<td>${list.duty_state}</td>
								<td>수정</td>
							</tr>
						</c:forEach>
					  </tbody>
					 </table>
					 <button type="button" onclick="location.href='memberInfoDutyWrite.go'">추가</button>
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
ListCall();

var con = $("#con option:selected").val();
console.log(con);
console.log($("#con option:selected").val());
console.log($("#con target"));


function ListCall(){
	var table = $("#datatable").DataTable({
		destroy:true,
		serverSide:false,
		ajax:{
			"url":"/teamList.ajax",
			"type":"get",
			"data":{}
		},
		columns:[
			{data:"team_idx"},
			{data:"team_name"},
			{data:"team_state",
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
	//subListCall();
}

function subListCall(target) {
	console.log("중분류 선택 !");
	console.log(target.value);
	
	var controll = target.value;
	
	$.ajax({
		type: 'get',
		url: '/subsubSel.ajax',
		data: {"controll" : controll},
		dataType: 'json',
		success: function(data){
			console.log(data);
			//var sublist = data.subList;
			
			drawList(data);
		}
	});
	
	

}

function drawList(data){
	var list = data.subList;
	console.log(list);
	var content = '';
	

	for(var i=0; i<list.length; i++){
		if(data.controll == 'team'){
			content += '<option value="'+list[i].team_idx+'">'+list[i].team_name+'</option>';
		}
		if(data.controll == 'pos'){
			content += '<option value="'+list[i].pos_idx+'">'+list[i].pos_name+'</option>';
		}
		if(data.controll == 'duty'){
			content += '<option value="'+list[i].duty_idx+'">'+list[i].duty_name+'</option>';
		}
		if(data.controll == 'state'){
			content += '<option value="'+list[i].emp_state_idx+'">'+list[i].emp_state_name+'</option>';
		}
	}
	$('#subsel').empty();
	$('#subsel').append(content);
}

var select = controll.options[controll.selectedIndex].value
console.log(select);


// 	var val1 = $(this).val();

// 	var select = controll.options[controll.selectedIndex].value
// 	var sel1 = $("#subsel option:selected").val();
// 	console.log(sel1);

function maListCall(){
	var sel1 = document.getElementById('con');
	var sel2 = document.getElementById('subsel');
// 	var sel3 = sel2.options[sel2.selectedIndex].value
	var sel3 = sel1.options[sel1.selectedIndex].value
	
	console.log(sel1);
// 	console.log(sel2);
	console.log(sel3);
	var select = sel2.options[sel2.selectedIndex].value
	var subSelect = sel1.options[sel1.selectedIndex].value
	var table = $("#datatable").DataTable({
		destroy:true,
		serverSide:false,
		ajax:{
			"url":"/subSelList.ajax",
			"type":"get",
			"data":{"select" : select,"subSelect":subSelect}
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
	//subListCall();
}

// function maListCall(){
// 	var 
// 	$.ajax({
// 		type:'get',
// 		url:'subSelList.ajax',
// 		data:{
// 			'doc_type': doc_type.options[doc_type.selectedIndex].value,
// 			'lineup' : lineup.options[lineup.selectedIndex].value
// 			},
// 		dataType:'JSON',
// 		success:function(data){
// 			drawList(data.docformlist);
// 		},
// 		error:function(e){
// 			console.log(e);
// 		}
// 	});
// }



// function suListCall(obj){
// 	console.log(obj);
// }




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