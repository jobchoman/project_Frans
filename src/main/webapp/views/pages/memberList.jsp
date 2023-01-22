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
.row > .col-sm-6:first-child {
      display: none;
   }
 td,th{
 	text-align: center;
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

            <div class="clearfix"></div>

            <div class="row" style="width:100%">
              <div class="col-md-12 col-sm-12 ">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>직원 리스트</h2>
                    <div class="clearfix"></div>
                  </div>
                  
                  <div class="x_content">
                      <div class="row">
                          <div class="col-sm-12">
                            <div class="card-box table-responsive">

<!--                      		<select name="info" id="info" onchange="window.open(this.value)"> -->
<!-- 								<option id="team" value="location.href='memberInfoList.go" onclick="location.href='memberInfoList.go'" selected="selected">팀</option> -->
<!-- 								<option id="duty" value="location.href='memberInfoPosList.go" onclick="location.href='memberInfoPosList.go'">직책</option> -->
<!-- 								<option id="pos" value="location.href='memberInfoDutyList.go" onclick="location.href='memberInfoDutyList.go'">직급</option> -->
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
}


</script>
</html>