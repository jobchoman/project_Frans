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
	.addWrap {
	   display: flex;
	   flex-direction: column;
	   justify-content: flex-start;
	   align-items: center;
	}
	#menuadddiv{
		display: flex;
		justify-content: center;
   		align-items: center;
   		flex-direction: column;
   		width: 80%;
	}
	.row {
		width: 100%;
		text-align: center;
	}
	img {
		width: 30%;
	}
	
	textarea {
		resize: none;
	}
	#reset {
		font-size: 8pt;
		float: right;
	}
	#menuRigist {
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
			<div id="menuadddiv" style="white-space: nowrap">
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
							<div class="x_title">
								<h2>메뉴 등록</h2>
								<div class="clearfix"></div>
							</div>
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
									<form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" action="menuWrite.do" method="POST" enctype="multipart/form-data">
														
										<div id="image_container"></div>
										<div class="item form-group"  style="justify-content: center">
											<label class="col-form-label col-md-3 col-sm-3 label-align">
												<!-- <input type="file" id="image" disabled="disabled" onchange="setThumbnail(event);"/> -->
												<input type="file" name="uploadFile" onchange="setThumbnail(event);"/>
											</label>
											
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">메뉴 이름 <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="menu_name" class="form-control" required="required">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">메뉴 가격 <span class="required">*</span></label> 
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="menu_price" class="form-control" required="required">
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
												<input name="menu_start" class="date-picker form-control" placeholder="dd-mm-yyyy" type="text" required="required" type="text" onfocus="this.type='date'" onmouseover="this.type='date'" onclick="this.type='date'" onblur="this.type='text'" onmouseout="timeFunctionLong(this)">
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
											<textarea name="menu_recipe" required="required" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="10" data-parsley-maxlength="100" data-parsley-minlength-message="10글자 이상 작성 바람" data-parsley-validation-threshold="10"></textarea>
											</div>
										</div>

										<div class="ln_solid"></div>
										<div>
											<div class="col-md-6 col-sm-6 offset-md-3" style="width:100%; margin-left: 0; max-width: 100%">
												
												<button type="submit" class="btn btn-round btn-info" id="menuRigist">등록</button>
												<button class="btn btn-round btn-secondary" type="reset" id="reset">초기화</button>
											</div>
										</div>

									</form>
									<div class="col-md-6 col-sm-6 offset-md-3" style="width:100%; margin-left: 0; float: left; max-width: 100%">
										<button class="btn btn-round btn-secondary" type="button" id="golist" onclick="location.href='/menuList'">리스트</button>
									</div>
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

function setThumbnail(event) {
    var reader = new FileReader();

    reader.onload = function(event) {
      var img = document.createElement("img");
      img.setAttribute("src", event.target.result);
      document.querySelector("div#image_container").appendChild(img);
    };

    reader.readAsDataURL(event.target.files[0]);
  }

</script>
</html>