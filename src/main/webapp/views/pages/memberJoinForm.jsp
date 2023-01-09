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
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>Form Elements</h3>
						</div>

						<div class="title_right">
							<div class="col-md-5 col-sm-5  form-group pull-right top_search">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="Search for...">
									<span class="input-group-btn">
										<button class="btn btn-default" type="button">Go!</button>
									</span>
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_title">
									<h2>Form Design <small>different form elements</small></h2>
									<ul class="nav navbar-right panel_toolbox">
										<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
										</li>
										<li class="dropdown">
											<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-wrench"></i></a>
											<ul class="dropdown-menu" role="menu">
												<li><a class="dropdown-item" href="#">Settings 1</a>
												</li>
												<li><a class="dropdown-item" href="#">Settings 2</a>
												</li>
											</ul>
										</li>
										<li><a class="close-link"><i class="fa fa-close"></i></a>
										</li>
									</ul>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<br />
									<form action="memberJoin.do" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" method="POST" enctype="multipart/form-data">

										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">사진<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="file" name="file" required="required" class="form-control ">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">아이디<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="emp_id" required="required" class="form-control ">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">비밀번호<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="password" name="emp_pw" required="required" class="form-control ">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">이름<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="emp_name" required="required" class="form-control ">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">생년월일<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="date" name="emp_birth" value="yyyy-mm-dd" required="required" class="form-control ">
											</div>
										</div>
										<div>
											<label class="col-form-label col-md-3 col-sm-3 label-align">성별<span class="required">*</span>
											</label>
											<div class="radio">
												<label>
													<input type="radio" value="남" id="optionsRadios1" name="emp_gender" >남
												</label>
											</div>
											<div class="radio">
												<label>
													<input type="radio" value="여" id="optionsRadios2" name="emp_gender">여
												</label>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">연락처<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="emp_phone" required="required" class="form-control ">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">이메일<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="emp_email" required="required" class="form-control ">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">주소<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" id="address_kakao" name="emp_address" required="required" class="form-control ">
												<input type="text" name="address_detail" required="required" class="form-control ">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">입사일<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="date" name="emp_hire_date" placeholder="yyyy-mm-dd" required="required" class="form-control ">
											</div>
										</div>
										<div>
											<div class="item form-group">
												<label class="col-form-label col-md-3 col-sm-3 label-align">학력<span class="required">*</span>
												</label>
												<div id="career" class="col-md-6 col-sm-6 " >
													<div class="del">
														<select name="emp_career_idx" class="form-control ">
															<option value="없음" selected="selected">없음</option>
															<option value="고등학교">고등학교</option>
															<option value="대학교">대학교</option>
															<option value="대학원">대학원</option>
														</select>
														<input type="text" name="emp_school_name" placeholder="학교명" required="required" class="form-control ">
														<input type="text" name="emp_department" placeholder="학과" required="required" class="form-control ">
														<input type="text" name="emp_degree" placeholder="학위" required="required" class="form-control ">
														입학일<input type="date" name="emp_career_start" placeholder="yyyy-mm-dd" required="required" class="form-control ">
														졸업일<input type="date" name="emp_career_end" placeholder="yyyy-mm-dd" required="required" class="form-control ">
														<input type="text" name="emp_career_etc" placeholder="비고" required="required" class="form-control ">
														<input type="button" name="delete" value="삭제" onclick="delete1()">
														<input type="button" value="추가" onclick="add_textbox()"/>
													</div>
												</div>
											</div>
										</div>
										<div>
											<div class="item form-group">
												<label class="col-form-label col-md-3 col-sm-3 label-align">이력<span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 ">
													<div id="career1">
														<select name="emp_career_idx" class="form-control ">
															<option value="없음" selected="selected">없음</option>
															<option value="경력">경력</option>
														</select>
														<input type="text" name="emp_school_name" placeholder="회사명" required="required" class="form-control ">
														<input type="text" name="emp_department" placeholder="부서" required="required" class="form-control ">
														<input type="text" name="emp_degree" placeholder="직급" required="required" class="form-control ">
														입사일<input type="date" name="emp_career_start" placeholder="yyyy-mm-dd" required="required" class="form-control ">
														퇴사일<input type="date" name="emp_career_end" placeholder="yyyy-mm-dd" required="required" class="form-control ">
														<input type="text" name="emp_career_etc" placeholder="맡은업무" required="required" class="form-control ">
														<input type="button" name="delete" value="삭제" onclick="delete2()">
														<input type="button" value="추가" onclick="add_textbox1()"/>
													</div>
												</div>
											</div>
										</div>
										<div>
											<div class="item form-group">
												<label class="col-form-label col-md-3 col-sm-3 label-align">자격증<span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 ">
													<div id="license">
														<input type="text" name="license_name" placeholder="자격증명" required="required" class="form-control ">
														취득일<input type="date" name="license_date" placeholder="yyyy-mm-dd" required="required" class="form-control ">
														<input type="text" name="license_place" placeholder="발급처" required="required" class="form-control ">
														<input type="text" name="license_result" placeholder="결과" required="required" class="form-control ">
														<input type="button" name="delete" value="삭제" onclick="delete3()">
														<input type="button" value="추가" onclick="add_textbox2()"/>
													</div>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">관리자 권한<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<select name="emp_admin_auth" class="form-control " required="required">
													<option value="2" selected="selected">일반사원</option>
													<option value="1">관리자</option>
													<option value="2">최고관리자</option>
												</select>
											</div>
										</div>										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">팀<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<select name="team_idx" class="form-control " required="required">
													<c:forEach items="${teamMem}" var="teamMem">
														<option value="${teamMem.team_idx}">${teamMem.team_name}</option>
													</c:forEach>
											    </select>
											</div>
										</div>										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">직급<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<select name="pos_idx" class="form-control " required="required">
													<c:forEach items="${posMem}" var="posMem">
													<option value="${posMem.pos_idx}">${posMem.pos_name}</option>
													</c:forEach>
											    </select>
											</div>
										</div>										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">직책<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<select name="duty_idx" class="form-control " required="required">
													<c:forEach items="${dutyMem}" var="dutyMem">
													<option value="${dutyMem.duty_idx}">${dutyMem.duty_name}</option>
													</c:forEach>
											    </select>
											</div>
										</div>										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">상태<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<select name="emp_state_idx" class="form-control " required="required">
													<c:forEach items="${stateMem}" var="stateMem">
													<option value="${stateMem.emp_state_idx}">${stateMem.emp_state_name}</option>
													</c:forEach>
											    </select>
											</div>
										</div>										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">팀등급<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<div></div>
												<select name="auth_type" class="form-control " required="required">
												  <option value="1">공개문서 열람</option>
												  <option value="2">전체 열람</option>
											    </select>
											</div>
										</div>	
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">서명 이미지<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="file" name="file2" required="required" class="form-control ">
											</div>
										</div>									
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">Date Of Birth <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input id="birthday" class="date-picker form-control" placeholder="dd-mm-yyyy" type="text" required="required" type="text" onfocus="this.type='date'" onmouseover="this.type='date'" onclick="this.type='date'" onblur="this.type='text'" onmouseout="timeFunctionLong(this)">
												<script>
													function timeFunctionLong(input) {
														setTimeout(function() {
															input.type = 'text';
														}, 60000);
													}
												</script>
											</div>
										</div>
										<div class="ln_solid"></div>
										<div class="item form-group">
											<div class="col-md-6 col-sm-6 offset-md-3">
												<button class="btn btn-primary" type="button">Cancel</button>
												<button class="btn btn-primary" type="reset">Reset</button>
												<button type="submit" class="btn btn-success">Submit</button>
											</div>
										</div>

									</form>
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
var msg = "${msg}";

if(msg != ""){
	alert(msg);
}

window.onload = function(){
    document.getElementById("address_kakao").addEventListener("click", function(){ //주소입력칸을 클릭하면
        //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById("address_kakao").value = data.address; // 주소 넣기
                document.querySelector("input[name=address_detail]").focus(); //상세입력 포커싱
            }
        }).open();
    });
}

function add_textbox() {
	for(var i=1;, i<6;, i++;)
	var sel = '<div class="del">'
		sel += '<select name="emp_career_idx" class="form-control ">'
		sel += '<option value="없음" selected="selected">없음</option>'
		sel += '<option value="고등학교">고등학교</option>'	  
		sel += '<option value="대학교">대학교</option>'  
		sel += '<option value="대학원">대학원</option></select>'	
		sel += '<input type="text" name="emp_school_name" placeholder="학교명" required="required" class="form-control "/>'	  
		sel += '<input type="text" name="emp_department" placeholder="학과" required="required" class="form-control "/>'	  
		sel += '<input type="text" name="emp_degree" placeholder="학위" required="required" class="form-control "/>'	  
		sel += '입학일<input type="date" name="emp_career_start" value="yyyy-mm-dd" required="required" class="form-control "/>'	  
		sel += '졸업일<input type="date" name="emp_career_end" value="yyyy-mm-dd" required="required" class="form-control "/>'	  
		sel += '<input type="text" name="emp_career_etc" placeholder="비고" required="required" class="form-control "/>'	  
		sel += '<input type="button" name="delete" value="삭제" onclick="delete1()"/>'	  
		sel += '</div>'
		$('#career').append(sel);
};
function add_textbox1() {
	var sel1 = '<div>'
		sel1 += '<select name="emp_career_idx" class="form-control ">'
		sel1 += '<option value="없음" selected="selected">없음</option>'
		sel1 += '<option value="경력">경력</option></select>'	  
		sel1 += '<input type="text" name="emp_school_name" placeholder="회사명" required="required" class="form-control "/>'	  
		sel1 += '<input type="text" name="emp_department" placeholder="부서" required="required" class="form-control "/>'	  
		sel1 += '<input type="text" name="emp_degree" placeholder="직급" required="required" class="form-control "/>'	  
		sel1 += '입사일<input type="date" name="emp_career_start" value="yyyy-mm-dd" required="required" class="form-control "/>'	  
		sel1 += '퇴사일<input type="date" name="emp_career_end" value="yyyy-mm-dd" required="required" class="form-control "/>'	  
		sel1 += '<input type="text" name="emp_career_etc" placeholder="맡은업무" required="required" class="form-control "/>'	  
		sel1 += '<input type="button" name="delete" value="삭제" onclick="delete2()"/>'	  
		sel1 += '</div>'
		$('#career1').append(sel1);
};
function add_textbox2() {
	var sel2 = '<div>'
		sel2 += '<input type="text" name="license_name" placeholder="자격증명" required="required"/ class="form-control ">'	  
		sel2 += '취득일<input type="date" name="license_date" value="yyyy-mm-dd" required="required" class="form-control "/>'	  
		sel2 += '<input type="text" name="license_place" placeholder="발급처" required="required"/ class="form-control ">'	  
		sel2 += '<input type="text" name="license_result" placeholder="결과" required="required"/ class="form-control ">'	  
		sel2 += '<input type="button" name="delete" value="삭제" onclick="delete3()"/>'	  
		sel2 += '</div>'
		$('#license').append(sel2);
};
 function delete1(){
	 $(".del").remove();
 }


</script>
</html>