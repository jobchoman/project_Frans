'<%@ page language="java" contentType="text/html; charset=UTF-8"
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
.btn{
	background-color:#2A3F54;
    border-color:#2A3F54;
    font-size: 8pt;
}
.addWrap {
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: center;
 }
</style>
</head>
<body class="nav-md">
<jsp:include page="loginBox.jsp"/>
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col"><jsp:include page="sidebar.jsp" /></div>

			<!-- top navigation -->
			<div class="top_nav"><jsp:include page="top_nav.jsp" /></div>
			<!-- /top navigation -->

			<!-- page content -->
		<div class="right_col addWrap" role="main">

            <div class="clearfix"></div>

            <div class="row" style="width:100%">
              <div class="col-md-12 col-sm-12 ">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>직원 리스트 <small>Users</small></h2>
                    <div class="clearfix"></div>
                  </div>
                  
                  <div class="x_content">
                      <div class="row">
                          <div class="col-sm-12">
                            <div class="card-box table-responsive">
                            
<!--                             <select id="con" onchange="subListCall(this)"> -->
<!-- 								<option class="all" value="all" selected="selected">전체</option> -->
<!-- 								<option class="team" value="team" >팀</option> -->
<!-- 								<option class="pos" value="pos">직급</option> -->
<!-- 								<option class="duty" value="duty">직책</option> -->
<!-- 								<option class="state" value="state">상태</option> -->
<!-- 							</select> -->
							
<!-- 							<select id="subsel" onchange="maListCall(this)"> -->
								
<!-- 							</select> -->
		
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
                    <button type="button" class="btn btn-round btn-info" onclick="location.href='/memberJoin.go'" style="margin-left: 90%">직원 등록</button>       
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
ListCall();

var con = $("#con option:selected").val();
console.log(con);
console.log($("#con option:selected").val());
console.log($("#con target"));


function ListCall(){
	var table = $("#datatable").DataTable({
		destroy:true,
		serverSide:false,
		"dom": 'frtp',
		ajax:{
			"url":"/selList.ajax",
			"type":"get",
			"data":{}
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

// function subListCall(target) {
// 	console.log("중분류 선택 !");
// 	console.log(target.value);
	
// 	var controll = target.value;
	
// 	$.ajax({
// 		type: 'get',
// 		url: '/subsubSel.ajax',
// 		data: {"controll" : controll},
// 		dataType: 'json',
// 		success: function(data){
// 			console.log(data);
// 			//var sublist = data.subList;
			
// 			drawList(data);
// 		}
// 	});
	
	

// }

// function drawList(data){
// 	var list = data.subList;
// 	console.log(list);
// 	var content = '';
	

// 	for(var i=0; i<list.length; i++){
// 		if(data.controll == 'team'){
// 			content += '<option value="'+list[i].team_idx+'">'+list[i].team_name+'</option>';
// 		}
// 		if(data.controll == 'pos'){
// 			content += '<option value="'+list[i].pos_idx+'">'+list[i].pos_name+'</option>';
// 		}
// 		if(data.controll == 'duty'){
// 			content += '<option value="'+list[i].duty_idx+'">'+list[i].duty_name+'</option>';
// 		}
// 		if(data.controll == 'state'){
// 			content += '<option value="'+list[i].emp_state_idx+'">'+list[i].emp_state_name+'</option>';
// 		}
// 	}
// 	$('#subsel').empty();
// 	$('#subsel').append(content);
// }

// var select = controll.options[controll.selectedIndex].value
// console.log(select);


// 	var val1 = $(this).val();

// 	var select = controll.options[controll.selectedIndex].value
// 	var sel1 = $("#subsel option:selected").val();
// 	console.log(sel1);

// function maListCall(){
// 	var sel1 = document.getElementById('con');
// 	var sel2 = document.getElementById('subsel');
// // 	var sel3 = sel2.options[sel2.selectedIndex].value
// 	var sel3 = sel1.options[sel1.selectedIndex].value
	
// 	console.log(sel1);
// // 	console.log(sel2);
// 	console.log(sel3);
// 	var select = sel2.options[sel2.selectedIndex].value
// 	var subSelect = sel1.options[sel1.selectedIndex].value
// 	var table = $("#datatable").DataTable({
// 		destroy:true,
// 		serverSide:false,
// 		ajax:{
// 			"url":"/subSelList.ajax",
// 			"type":"get",
// 			"data":{"select" : select,"subSelect":subSelect}
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
// 	//subListCall();
// }




</script>
</html>