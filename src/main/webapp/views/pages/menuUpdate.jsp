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
									<form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" action="menuUpdate.do" method="POST" enctype="multipart/form-data">
														
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">
												<input type="file" name="uploadFile"/>
												<img width="300" src="/photo/${detail.menu_photo}"/>
											</label>
											
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">메뉴 이름 <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="menu_name" class="form-control" required="required" value="${detail.menu_name}">
												<input type="hidden" name="menu_idx" value="${detail.menu_idx}">
											<select name="menu_state" class="form-control ">
                                             	<option value="${detail.menu_state}" selected="selected">${detail.menu_state}</option>
                                             	<c:if test="${detail.menu_state eq '판매중'}">
                                             	<option value="준비중">준비중</option>
                                             	<option value="비활성화">비활성화</option>
                                             	</c:if>
                                             	<c:if test="${detail.menu_state eq '준비중'}">
                                             	<option value="판매중">판매중</option>
                                             	<option value="비활성화">비활성화</option>
                                             	</c:if>
                                             	<c:if test="${detail.menu_state eq '비활성화'}">
                                             	<option value="판매중">판매중</option>
                                             	<option value="준비중">준비중</option>
                                             	</c:if>
                                          	</select>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">메뉴 가격 <span class="required">*</span></label> 
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="menu_price" class="form-control" required="required" value="${detail.menu_price}">
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
											<label class="col-form-label col-md-3 col-sm-3 label-align">출시 일자 <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input name="menu_start" class="date-picker form-control" value="${detail.menu_start}" placeholder="dd-mm-yyyy" type="text" required="required" type="text" onfocus="this.type='date'" onmouseover="this.type='date'" onclick="this.type='date'" onblur="this.type='text'" onmouseout="timeFunctionLong(this)" readonly>
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
										
										
											<label class="col-form-label col-md-3 col-sm-3 label-align">레시피 <span class="required">*</span></label>
											<div class="col-md-6 col-sm-6 ">
											<textarea name="menu_recipe" required="required" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="10" data-parsley-maxlength="100" data-parsley-minlength-message="10글자 이상 작성 바람" data-parsley-validation-threshold="10">${detail.menu_recipe}</textarea>
											</div>
										</div>

										<div class="ln_solid"></div>
										<div class="item form-group">
											<div class="col-md-6 col-sm-6 offset-md-3">
												
												<button class="btn btn-primary" type="reset">초기화</button>
												<button type="submit" class="btn btn-success" id="menuUpdate">수정완료</button>
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
	$('#menuUpdate').click(function(){
		if(!confirm('메뉴 수정을 하시겠습니까?')) {
			return false;
		}
	});
});


</script>
</html>