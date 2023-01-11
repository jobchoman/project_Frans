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
			
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
<!-- 								<div class="x_title"> -->
<!-- 									<h2>Form Design <small>different form elements</small></h2> -->
<!-- 									<ul class="nav navbar-right panel_toolbox"> -->
<!-- 										<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a> -->
<!-- 										</li> -->
<!-- 										<li class="dropdown"> -->
<!-- 											<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-wrench"></i></a> -->
<!-- 											<ul class="dropdown-menu" role="menu"> -->
<!-- 												<li><a class="dropdown-item" href="#">Settings 1</a> -->
<!-- 												</li> -->
<!-- 												<li><a class="dropdown-item" href="#">Settings 2</a> -->
<!-- 												</li> -->
<!-- 											</ul> -->
<!-- 										</li> -->
<!-- 										<li><a class="close-link"><i class="fa fa-close"></i></a> -->
<!-- 										</li> -->
<!-- 									</ul> -->
<!-- 									<div class="clearfix"></div> -->
<!-- 								</div> -->
								<div class="x_content">
									<br />
									<form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" action="menuWrite.do" method="POST">
														
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">구독권 이름 <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="menu_name" class="form-control" required="required">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">구독권 가격 <span class="required">*</span></label> 
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="menu_price" class="form-control" required="required">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">횟수/요일 <span class="required">*</span></label> 
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="menu_price" class="form-control" required="required">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">횟수 <span class="required">*</span></label> 
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="menu_price" class="form-control" required="required">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">메뉴 <span class="required">*</span></label> 
											<div class="col-md-6 col-sm-6 ">
												<div class="col-md-9 col-sm-9 ">
												
													<select id="selectbox" name="sub_period" class="form-control" style="width: 300px;" onchange="chageLangSelect()">
                                             			<option value="" selected="selected">선택하기</option>
                                             			<c:forEach var="item" items="${menuList}">
                                             			<option value="${item.menu_idx}">${item.menu_name}</option>              
                                             			</c:forEach>                          			
                                          			</select>
												
													<div id="menuTags" style="border: 1px solid #D3D3D3; width: 300px; height:100px;"></div>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">기간 <span class="required">*</span></label> 
											<div class="col-md-6 col-sm-6 ">
												<select name="sub_period" class="form-control ">
                                             		<option value="" selected="selected">선택하기</option>
                                             		<option value="1">1개월</option>
                                             		<option value="2">2개월</option>
                                             		<option value="3">3개월</option>
                                             		<option value="4">4개월</option>
                                             		<option value="5">5개월</option>
                                             		<option value="6">6개월</option>
                                             		<option value="7">7개월</option>
                                             		<option value="8">8개월</option>
                                             		<option value="9">9개월</option>
                                             		<option value="10">10개월</option>
                                             		<option value="11">11개월</option>
                                             		<option value="12">12개월</option>
                                          		</select>
											</div>
										</div>
<!-- 										<div class="item form-group"> -->
<!-- 											<label class="col-form-label col-md-3 col-sm-3 label-align">Gender</label> -->
<!-- 											<div class="col-md-6 col-sm-6 "> -->
<!-- 												<div id="gender" class="btn-group" data-toggle="buttons"> -->
<!-- 													<label class="btn btn-secondary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default"> -->
<!-- 														<input type="radio" name="gender" value="male" class="join-btn"> &nbsp; Male &nbsp; -->
<!-- 													</label> -->
<!-- 													<label class="btn btn-primary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default"> -->
<!-- 														<input type="radio" name="gender" value="female" class="join-btn"> Female -->
<!-- 													</label> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</div> -->
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">출시일<span class="required"> *</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input name="sub_start" class="date-picker form-control" placeholder="dd-mm-yyyy" type="text" required="required" type="text" onfocus="this.type='date'" onmouseover="this.type='date'" onclick="this.type='date'" onblur="this.type='text'" onmouseout="timeFunctionLong(this)">
												<script>
													function timeFunctionLong(input) {
														setTimeout(function() {
															input.type = 'text';
														}, 60000);
													}
												</script>
											</div>

										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">종료일<span class="required"> *</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input name="sub_end" class="date-picker form-control" placeholder="dd-mm-yyyy" type="text" required="required" type="text" onfocus="this.type='date'" onmouseover="this.type='date'" onclick="this.type='date'" onblur="this.type='text'" onmouseout="timeFunctionLong(this)">
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
												
												<button class="btn btn-primary" type="reset">초기화</button>
												<button type="submit" class="btn btn-success" id="menuRigist">등록하기</button>
											</div>
										</div>

									</form>
									<div class="col-md-6 col-sm-6 offset-md-3">
									<button class="btn btn-primary" type="button" onclick="location.href='/menuList'">리스트</button>
									</div>
								</div>
							</div>
						</div>
					</div>
			
			</div>
			<!-- /page content -->

		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>
$(function() {
	$('#menuRigist').click(function(){
		if(!confirm('메뉴 등록을 하시겠습니까?')) {
			return false;
		}
	});
});

function putTags(selectValue,selectText) {
	
	var content = '<div><span>'+selectText+'</span><a href="#" onclick="delTags();">x</a><input type="hidden" value="'+selectValue+'"></div>';
	
	$('#menuTags').append(content);
	
}


function chageLangSelect(){
    var langSelect = document.getElementById("selectbox");
     
    // select element에서 선택된 option의 value가 저장된다.
    var selectValue = langSelect.options[langSelect.selectedIndex].value;
 	
    // select element에서 선택된 option의 text가 저장된다.
    var selectText = langSelect.options[langSelect.selectedIndex].text;
    
    putTags(selectValue,selectText);
}



</script>
</html>