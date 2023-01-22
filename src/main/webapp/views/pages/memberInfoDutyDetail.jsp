<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- <script src="//dmaps.daum.net/map_js_init/postcode.v2.js"></script> -->
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
					<div class="row" >
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_title">
									<h2>직책 등록</h2>

									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<br />
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">직책 코드
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="duty_idx" class="form-control " value="${mem.duty_idx}">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">직책 명
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="duty_name" class="form-control " value="${mem.duty_name}">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">상태
											</label>
											<div class="col-md-6 col-sm-6 ">
												<c:if test="${mem.duty_state=='1'}">사용</c:if>
												<c:if test="${mem.duty_state=='0'}">미사용</c:if>
											</div>
										</div>
										<div class="ln_solid"></div>
										<div class="item form-group">
											<div class="col-md-6 col-sm-6 offset-md-3">
												<button class="btn btn-round btn-secondary ghl" onclick="location.href='memberInfoDutyList.go'" type="reset">리스트</button>
												<button type="button" class="btn btn-round btn-info nam" onclick="location.href='memberInfoDutyUpdate.go?duty_idx=${mem.duty_idx}'">수정</button>
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
var msg = "${msg}";

if(msg != ""){
	alert(msg);
}

window.onload = function(){
	$('input:radio[name="duty_state"]:radio[value="'+${mem.duty_state}+'"]').prop('checked',true);
}
	$('input:radio[name="duty_state"]:radio').prop('checked',false);



</script>
</html>