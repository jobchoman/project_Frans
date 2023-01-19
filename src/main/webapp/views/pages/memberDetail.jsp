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
				<div class="" style="width:100%">
					<div class="page-title">

					</div>
					<div class="clearfix"></div>
					<div class="row" style="width:100%">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_title">
									<h2>직원 상세보기</h2>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<br />
										

										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">사진
											</label>
											<div class="col-md-6 col-sm-6 ">
												<c:if test="${fileList.size()>0}">
													<c:forEach items="${fileList}" var="path">
													<img width="100" src="memberPhoto.do?path=${path.file_new}"/>
													</c:forEach>
												</c:if>
											</div>
										</div>
										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">아이디
											</label>
											<div class="col-md-6 col-sm-6 ">
												${mem.emp_id}
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">비밀번호
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="password" name="emp_pw" required="required" class="form-control " readonly="readonly">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">이름
											</label>
											<div class="col-md-6 col-sm-6 ">
												${mem.emp_name}
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">생년월일
											</label>
											<div class="col-md-6 col-sm-6 ">
												${mem.emp_birth}
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">성별
											</label>
											<div class="col-md-6 col-sm-6 ">
												${mem.emp_gender}
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">연락처
											</label>
											<div class="col-md-6 col-sm-6 ">
												${mem.emp_phone}
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">이메일
											</label>
											<div class="col-md-6 col-sm-6 ">
												${mem.emp_email}
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">주소
											</label>
											<div class="col-md-6 col-sm-6 ">
												${mem.emp_address}
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">입사일
											</label>
											<div class="col-md-6 col-sm-6 ">
												${mem.emp_hire_date}
											</div>
										</div>
										<div>
											<div class="item form-group">
												<label class="col-form-label col-md-3 col-sm-3 label-align">학력
												</label>
												<div id="career" class="col-md-6 col-sm-6 " >
													<div>
													<ul>
														<c:forEach items="${memSchool}" var="memSchool">
														<c:if test="${memSchool.emp_career_idx != '경력'}">
													<li>
															${memSchool.emp_career_idx}
 															${memSchool.emp_school_name}
															${memSchool.emp_department}
															${memSchool.emp_degree}
															${memSchool.emp_career_start}
															${memSchool.emp_career_end}
															${memSchool.emp_career_etc} 
													</li>
													<br>
														</c:if>
														</c:forEach>
													</ul>
													</div>
												</div>
											</div>
										</div>
										<div>
											<div class="item form-group">
												<label class="col-form-label col-md-3 col-sm-3 label-align">이력
												</label>
												<div class="col-md-6 col-sm-6 ">
													<div id="career1">
													<ul>
														<c:forEach items="${memSchool}" var="memSchool">
														<c:if test="${memSchool.emp_career_idx == '경력'}">
														<li>
															${memSchool.emp_career_idx}
 															${memSchool.emp_school_name}
															${memSchool.emp_department}
															${memSchool.emp_degree}
															${memSchool.emp_career_start}
															${memSchool.emp_career_end}
															${memSchool.emp_career_etc}
														</li><br>
														</c:if>
														</c:forEach>
													</ul>
													</div>
												</div>
											</div>
										</div>
										<div>
											<div class="item form-group">
												<label class="col-form-label col-md-3 col-sm-3 label-align">자격증
												</label>
												<div class="col-md-6 col-sm-6 ">
													<div id="license">
													<ul>
													<c:if test="${memLicense.size()>0}">
														<c:forEach items="${memLicense}" var="memLicense">
														<li>
																${memLicense.license_name}
	 															${memLicense.license_date}
																${memLicense.license_place}
																${memLicense.license_result}
														</li><br>
														</c:forEach>
													</c:if>
													</ul>
													</div>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">관리자 권한
											</label>
											<div class="col-md-6 col-sm-6 ">
												<c:if test="${mem.emp_admin_auth == '2'}">일반사원</c:if>
												<c:if test="${mem.emp_admin_auth == '1'}">관리자</c:if>
												<c:if test="${mem.emp_admin_auth == '0'}">최고관리자</c:if>
											</div>
										</div>										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">팀
											</label>
											<div class="col-md-6 col-sm-6 ">
												${mem.team_name}
											</div>
										</div>										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">직급
											</label>
											<div class="col-md-6 col-sm-6 ">
												${mem.pos_name}
											</div>
										</div>										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">직책
											</label>
											<div class="col-md-6 col-sm-6 ">
												${mem.duty_name}
											</div>
										</div>										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">상태
											</label>
											<div class="col-md-6 col-sm-6 ">
												${mem.emp_state_name}
											</div>
										</div>										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">팀등급
											</label>
											<div class="col-md-6 col-sm-6 ">
											<c:forEach items="${rgh}" var="rgh">
												${rgh.team_name}
											</c:forEach>
											</div>
										</div>	
										<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align">서명 이미지
											</label>
											<div class="col-md-6 col-sm-6 ">
												<c:if test="${fileList1.size()>0}">
													<c:forEach items="${fileList1}" var="path">
													<img width="100" src="memberPhoto.do?path=${path.file_new}"/>
													</c:forEach>
												</c:if>
											</div>
										</div>			
										<div class="item form-group">
										<label class="col-form-label col-md-3 col-sm-3 label-align">히스토리
											</label>
											<div class="col-md-6 col-sm-6 ">
												<button type="button" class="btn btn-round btn-info nam" onclick="hist(event)">변경 히스토리</button>
											</div>
										</div>								
										
										<div class="ln_solid"></div>
										<div class="item form-group" >
											<div class="col-md-6 col-sm-6 offset-md-3" >
												<button class="btn btn-round btn-secondary ghl"  type="button" onclick="location.href='memberList.go'">리스트</button>
												<button type="button" onclick="location.href='memberUpdate.go?emp_id=${mem.emp_id}'" class="btn btn-round btn-info nam">수정</button>
											</div>
										</div>
				<!-- modal -->
				<div class="modal fade bs-example-modal-lg" id="hist" tabindex="-1" role="dialog" aria-hidden="true">
					<div class="modal-dialog modal-lg">
	  					<div class="modal-content">
	  						<div class="modal-header">
	     						<h5 class="modal-title" id="subSearchLabel">팀/직급/직책 히스토리</h5>
	     						<input type="hidden" >
	      						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	    					</div>
							<div class="modal-body">
							<div class="card-box table-responsive">
								<table id="datatable" class="table table-striped table-bordered" style="width:100%">
									<thead>
										<tr>
											<th>변경날짜</th>
											<th>변경사항</th>
											<th>사유</th>
											<th>구분</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${hist}" var="hist">
										<tr class = "memberlistTr" >				
											<td>${hist.change_date}</td>
											<td>${hist.changes}</td>
											<td>${hist.change_reason}</td>
											<td>${hist.change_division}</td>
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
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
console.log(${mem.emp_career_idx});

var msg = "${msg}";

if(msg != ""){
	alert(msg);
}


function hist(event){
	console.log(document.getElementById("hist"));
	empInputIdx = event.target.id;
	console.log(empInputIdx);
	
	$('#hist').modal('show');
}

 


</script>
</html>