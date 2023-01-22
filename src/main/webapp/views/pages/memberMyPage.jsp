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
	#but{
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
 
 #mainDiv{
	width: 80%;
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
				<div class="" id="mainDiv">
					<div class="page-title">

					</div>
					<div class="clearfix"></div>
					<div class="row" style="width:100%">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_title">
									<h2>내 정보보기</h2>
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
														<c:forEach items="${memSchool}" var="memSchool">
														<c:if test="${memSchool.emp_career_idx != '경력'}">
															${memSchool.emp_career_idx}
 															${memSchool.emp_school_name}
															${memSchool.emp_department}
															${memSchool.emp_degree}
															${memSchool.emp_career_start}
															${memSchool.emp_career_end}
															${memSchool.emp_career_etc} <br>
														</c:if>
														</c:forEach>
		
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
														<c:forEach items="${memSchool}" var="memSchool">
														<c:if test="${memSchool.emp_career_idx == '경력'}">
															${memSchool.emp_career_idx}
 															${memSchool.emp_school_name}
															${memSchool.emp_department}
															${memSchool.emp_degree}
															${memSchool.emp_career_start}
															${memSchool.emp_career_end}
															${memSchool.emp_career_etc} <br>
														</c:if>
														</c:forEach>
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
													<c:if test="${memLicense.size()>0}">
														<c:forEach items="${memLicense}" var="memLicense">
																${memLicense.license_name}
	 															${memLicense.license_date}
																${memLicense.license_place}
																${memLicense.license_result}<br>
														</c:forEach>
													</c:if>
													</div>
												</div>
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
											<label class="col-form-label col-md-3 col-sm-3 label-align">보유 팀 권한
											</label>
											
											<div class="col-md-6 col-sm-6 ">
												
													<div id="license">
													<ul>
													<c:if test="${rightTeam.size()>0}">
														<c:forEach items="${rightTeam}" var="rightTeam">
														<li>
																${rightTeam.team_name}
																<c:if test="${rightTeam.auth_type == 1}">기본권한</c:if>
														<c:if test="${rightTeam.auth_type == 2}">전체권한</c:if>
	 															
														</li><br>
														</c:forEach>
													</c:if>
													</ul>
													</div>
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
									
										<div class="ln_solid"></div>
										<div class="item form-group">
											<div class="col-md-6 col-sm-6 offset-md-3">
												<button type="button" class="btn btn-round btn-info" id="but" onclick="location.href='myPageUpdate.go?emp_id=${sessionScope.loginId}'" class="btn btn-success">수정하기</button>
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
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
console.log(${mem.emp_career_idx});

var msg = "${msg}";

if(msg != ""){
	alert(msg);
}




 


</script>
</html>