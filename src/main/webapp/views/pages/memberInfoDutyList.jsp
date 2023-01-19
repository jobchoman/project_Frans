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
                    <h2>팀/직급/직책 관리</h2>
                    <div class="clearfix"></div>
                  </div>
                  
                  <div class="x_content">
                  	<ul class="nav nav-tabs bar_tabs" id="myTab" role="tablist">
                      <li class="nav-item">
                        <a class="nav-link" id="profile-tab" data-toggle="tab" role="tab" aria-selected="false" onclick="location.href='memberInfoList.go'">팀</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link" id="profile-tab" data-toggle="tab" role="tab"  aria-selected="false" onclick="location.href='memberInfoPosList.go'">직급</a>
                      </li>
                      <li class="nav-item">
                        <a class="nav-link active" id="home-tab" data-toggle="tab" role="tab"  aria-selected="true" onclick="location.href='memberInfoDutyList.go'">직책</a>
                      </li>
                    </ul>
                      <div class="row">
                          <div class="col-sm-12">
                            <div class="card-box table-responsive">

<!--                      		<select name="info" id="info"> -->
<!-- 								<option id="team" value="team" onclick="teamList();" selected="selected">팀</option> -->
<!-- 								<option id="duty" value="duty" onclick="dutyList();">직책</option> -->
<!-- 								<option id="pos" value="pos" onclick="posList();">직급</option> -->
<!-- 							</select> -->
					
                   

							
				
                    <button type="button" onclick="location.href='memberInfoDutyWrite.go'" class="btn btn-round btn-info" style="margin-left: 90%">추가</button>
                    <table id="datatable" class="table table-striped table-bordered dataTable no-footer"
													style="width: 100%" aria-describedby="datatable_info">
                      <thead>
						<tr>
							<th>직책코드</th>
							<th>직책명</th>
							<th>상태</th>
							<th>수정</th>
							<th>삭제</th>	
						</tr>
					  </thead>
					  <tbody>
						<c:forEach items="${list}" var="list">
							<tr>						
								<td>${list.duty_idx}</td>
								<td>${list.duty_name}</td>
								<c:if test="${list.duty_state == '1'}"><td>사용</td></c:if>
								<c:if test="${list.duty_state == '0'}"><td>미사용</td></c:if>								
								<td><button type="button" class="btn btn-round btn-info" onclick="location.href='memberInfoDutyUpdate.go?duty_idx=${list.duty_idx}'">수정</button></td>
								<td><button type="button" class="btn btn-round btn-info" onclick="location.href='memberInfoDutyDelete.do?duty_idx=${list.duty_idx}'">삭제</button></td>
							</tr>
						</c:forEach>
					  </tbody>
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

</script>
</html>