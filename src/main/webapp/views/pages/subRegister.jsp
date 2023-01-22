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
<style>
	.addWrap, .x_content {
	   display: flex;
	   flex-direction: column;
	   justify-content: flex-start;
	   align-items: center;
	}
	#subregistdiv {
/*		display: flex;*/
		justify-content: center;
   		align-items: center;
   		flex-direction: column;
   		width: 80%;
	}
	label {
		font-weight: bold;
		width: 20%;
	}
	form {
		width: 100%;
	}
	div.radio {
		white-space: nowrap;
	}
	.col-md-6 col-sm-6 {
		width: 50%;
	}
	.form-control {
		width: 65%;
	}
	#reset {
		font-size: 8pt;
		float: right;
	}
	#subRigist {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 8pt;
		float: right;
	}
	
	#reset:hover {
		background-color:orange;
		border-color:orange;
	}
	
	#golist {
		font-size: 8pt;
		float: left;
	}
</style>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col"><jsp:include page="sidebar.jsp" /></div>

			<!-- top navigation -->
			<div class="top_nav"><jsp:include page="top_nav.jsp" /></div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col addWrap" role="main">
				<div id="subregistdiv">
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
							<div style="width:100%"><h3 style="float:left">구독권 등록</h3></div>
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
									<form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" action="subWrite.do" method="POST">
														
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">구독권 이름 <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="sub_name" class="form-control" required="required">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">구독권 가격 <span class="required">*</span></label> 
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="sub_price" class="form-control" required="required">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">횟수/요일 <span class="required">*</span></label> 
											<div class="col-md-6 col-sm-6 ">
												<div class="radio">
													<label>
														<input type="radio" class="flat" checked name="sub_sort_idx" value="0"> 횟수권
														&nbsp;&nbsp;&nbsp;
														<input type="radio" class="flat" name="sub_sort_idx" value="1"> 요일권
													</label>
												</div>

											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">횟수 <span class="required">*</span></label> 
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="sub_num" class="form-control" required="required">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">메뉴 <span class="required">*</span></label> 
											<div class="col-md-6 col-sm-6 ">
												<!-- <div class="col-md-9 col-sm-9 "> -->
												
													<select id="selectbox" class="form-control" style="width: 300px;" onchange="chageLangSelect()">
                                             			<option value="" selected="selected">선택하기</option>
                                             			<c:forEach var="item" items="${menuList}">
                                             			<option value="${item.menu_idx}">${item.menu_name}</option>              
                                             			</c:forEach>                          			
                                          			</select>
												
													<div id="menuTags" style="border: 1px solid #D3D3D3; width: 300px; min-height:100px; padding-left: 3%; padding-top: 2%;"></div>
												<!-- </div> -->
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
										<div style="width:100%">
										<div class="item form-group" style="width:100%">
											<div class="col-md-6 col-sm-6 offset-md-3" style="float:left; width:50%; margin-left:0%">
												<button class="btn btn-round btn-secondary" type="button" id="golist" onclick="location.href='/subList'">목록</button>
											</div>
											<div class="col-md-6 col-sm-6 offset-md-3" style="float:right; width:50%; margin-left:0%">
												<button type="submit" class="btn btn-round btn-info" id="subRigist">등록</button>
												<button class="btn btn-round btn-secondary" id="reset" type="reset">초기화</button>
											</div>
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

		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>
$(function() {
	$('#subRigist').click(function(){
		if(!confirm('구독권 등록을 하시겠습니까?')) {
			return false;
		}
	});
});

function putTags(selectValue,selectText) {
	
	var content = '<div><span>'+selectText+'</span><a class="delete" href="javascript:void(0)"> x</a><input type="hidden" id="idx" name="menuIdx" value="'+selectValue+'"></div>';
	
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

$(document).on("click", ".delete", function(){
    $(this).closest("div").remove();
});

</script>
</html>