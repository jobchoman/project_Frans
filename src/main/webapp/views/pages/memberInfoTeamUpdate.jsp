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
				
					<div class="clearfix"></div>
					<div class="row" >
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_title">
									<h2>팀 등록</h2>

									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<br />
									<form action="memberInfoTeamUpdate.do" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" method="post" enctype="multipart/form-data">

										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">팀 코드
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="team_idx" class="form-control " value="${mem.team_idx}"/>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">팀 명
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="team_name" class="form-control " value="${mem.team_name}"/>
											</div>
										</div>

										<div>
											<label class="col-form-label col-md-3 col-sm-3 label-align">상태
											</label>
											
											<!-- 테스트 박스 -->
											<div class="radio">
												<label>
													<input type="radio" class="radioBtn555" value="1" id="on" name="team_state" >활성화
												</label>
											</div>
											<div class="radio">
												<label>
													<input type="radio" class="radioBtn555" value="0" id="off" name="team_state">비활성화
												</label>
											</div>
										</div>
										<div class="ln_solid"></div>
										<div class="item form-group">
											<div class="col-md-6 col-sm-6 offset-md-3">
												<button class="btn btn-round btn-secondary ghl" onclick="location.href='memberInfoList.go'" type="reset">리스트</button>
												<button type="submit" id="maker" class="btn btn-round btn-info nam">등록</button>
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

<script>
var msg = "${msg}";


//1. value 값을 가져온다 (라디오 버튼의 밸류 .. 예를 들면 1..) = > 변수로 담아.. 
// ex) var radioNum = $("#testBox1").val();
//2. 돔탐색을 해서... input class 또는 name의 이름을 찾는다
//3. 2번에서 찾은 이름을 통해 속성을 변경 해준다.
//3번의 예시 ) if(radioNum == ) $(".className").prop("checked",true);

// window.onload=function(){
// 	var testBox =$("#testBox1").val();
// 	console.log()
	
// }

// window.onload = function(){
// 	$('input:radio[name="team_state"]:radio[value="'+${mem.team_state}+'"]').prop('checked',true); 
// }

window.onload = function(){
	$('input:radio[name="team_state"]:radio[value="'+${mem.team_state}+'"]').prop('checked',true);
}
	$('input:radio[name="team_state"]:radio').prop('checked',false);

// 	$('#btnPinkChangeChecked').click(function() {
// 		$('#rdPink').prop('checked', true);
// 		$('#rdPink').trigger('change');
// 	});

// 	/* PINK (Radio 1개) Checked 해제 */
// 	$('#btnPinkChangeUnchecked').click(function() {
// 		$('#rdPink').prop('checked', false);
// 		$('#rdPink').trigger('change');
// 	});	


// $('input[name="team_state"]').change(function(){
	
//     var value = $(this).val();
//     var checked = $(this).prop('checked');
// });

${mem.team_state}
if(msg != ""){
	alert(msg);
}

// var check = document.getElementById("on");
// var check1 = $("#on").click
// console.log(check);
// if (frm.on.checked == true) {
// 	 frm.option.value = "1"; 
// 	} 
// 	else if (frm.of.checked == true) {
// 	 frm.option.value = "0";
// 	}
	
// $("input[name='team_state']").each(function(){
// 	console.log($(this).val())
// });

// $('input[name="team_state"]').change(function(){
	
//     var value = $(this).val();
//     var checked = $(this).prop('checked');
//     console.log(value);
//     console.log(checked);
//     $('input:radio[name="team_state"]:radio[value="'+${mem.team_state}+'"]').prop('checked',true); 
// });

//$('input:radio[name="라디오네임"]:radio[value="'+체크값(밸류)+'"]').prop('checked',true); 
//$('input:radio[name="team_state"]:radio[value="'+${mem.team_state}+'"]').prop('checked',true); 

</script>
</html>