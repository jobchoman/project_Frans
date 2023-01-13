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

<!--                      		<select name="info" id="info" onchange="window.open(this.value)"> -->
<!-- 								<option id="team" value="location.href='memberInfoList.go" onclick="location.href='memberInfoList.go'" selected="selected">팀</option> -->
<!-- 								<option id="duty" value="location.href='memberInfoPosList.go" onclick="location.href='memberInfoPosList.go'">직책</option> -->
<!-- 								<option id="pos" value="location.href='memberInfoDutyList.go" onclick="location.href='memberInfoDutyList.go'">직급</option> -->
<!-- 							</select> -->									
				                <button type="button" class="btn btn-round btn-secondary" id="team" onclick="location.href='memberInfoList.go'">팀</button>
								<button type="button" class="btn btn-round btn-secondary" id="pos" onclick="location.href='memberInfoPosList.go'">직급</button>
								<button type="button" class="btn btn-round btn-secondary" id="duty" onclick="location.href='memberInfoDutyList.go'">직책</button>

							
				
                            
                    <table id="datatable" class="table table-striped table-bordered dataTable no-footer"
													style="width: 100%" aria-describedby="datatable_info">
                      <thead>
						<tr>
							<th>팀코드</th>
							<th>팀명</th>
							<th>상태</th>
							<th>수정</th>
						</tr>
					  </thead>
					  <tbody>
						<c:forEach items="${list}" var="list">
							<tr>
								<td>${list.team_idx}</td>
								<td>${list.team_name}</td>
								<td>${list.team_state}</td>
								<td><button type="button" onclick="location.href='memberInfoTeamUpdate.go?team_idx=${list.team_idx}'">수정</button></td>

							</tr>
						</c:forEach>
					  </tbody>
                    </table>
                    <button type="button" onclick="location.href='memberInfoTeamWrite.go'">추가</button>
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