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
 td,th{
 	text-align: center;
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
                    <h2>구독회원 <small>Users</small></h2>
                    <div class="clearfix"></div>
                  </div>
                  
                  <div class="x_content">
                      <div class="row">
                          <div class="col-sm-12">
                            <div class="card-box table-responsive">

                    <button type="button" class="btn btn-round btn-info nam" onclick="location.href='/subUserJoin.go'" style="margin-left: 90%">구독 회원등록</button>        
                    <table id="datatable" class="table table-striped table-bordered dataTable no-footer"
													style="width: 100%" aria-describedby="datatable_info">
                      <thead>
						<tr>
							<th>회원 아이디</th>
							<th>회원 이름</th>
							<th>연락처</th>
							<th>이용권 이름</th>
							<th>시작일</th>
							<th>종료일</th>

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




function ListCall(){
	var table = $("#datatable").DataTable({
		destroy:true,
		serverSide:false,
		"dom": 'frtp',
		ajax:{
			"url":"/subUserList.ajax",
			"type":"get",
			"data":{}
		},
		columns:[
			{data:"client_id",
				"render":function(data,type,row){
					if(type='display'){
						data = '<a href="/subUserDetail.do?client_id='+row.client_id+'">'
								+row.client_id+'</a>';
					}
					return data;
				}
			},
			{data:"client_name"},
			{data:"client_phone"},
			{data:"sub_name"},
			{data:"sub_use_start"},
			{data:"sub_use_end"}

		],
        columnDefs: [{
        	
		targets : [ 0 ],

		searchable : true,

		visible : true     

        }]

	});
};

</script>
</html>