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
									<h2>직원 등록</h2>

									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<br />
									<form action="/userUpdate.do" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" method="POST" enctype="multipart/form-data">


										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">이름
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="client_name" id="name" value="${client.client_name}" class="form-control "/>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">아이디
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="client_id" id="id" value="${client.client_id}" class="form-control " readonly="readonly"/>
											</div>
										</div>
<!-- 										<div class="item form-group"> -->
<!-- 											<label class="col-form-label col-md-3 col-sm-3 label-align">비밀번호<span class="required">*</span> -->
<!-- 											</label> -->
<!-- 											<div class="col-md-6 col-sm-6 "> -->
<!-- 												<input type="password" id="pw" required="required" class="form-control "/> -->
<!-- 											</div> -->
<!-- 										</div> -->
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">비밀번호
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="password" name="client_pw" id="pw2" class="form-control "/>
											</div>
										</div>
										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">성별
											</label>
											<div class="col-md-6 col-sm-6 ">
												${client.client_gender}
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">연락처
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="client_phone" id="phone" value="${client.client_phone}" class="form-control ">
											</div>
										</div>
										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">주소
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" id="client_address" onclick="addr()" value="${client.client_address} " name="client_address" class="form-control "/>
												<input type="text" id="address_detail" placeholder="상세주소" name="address_detail" class="form-control ">
											</div>
										</div>

										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">생년월일
											</label>
											<div class="col-md-6 col-sm-6 ">
												${client.client_birth}
											</div>
										</div>
															
										<div class="ln_solid"></div>
										<div class="item form-group">
											<div class="col-md-6 col-sm-6 offset-md-3">
												<button class="btn btn-round btn-secondary ghl" onclick="location.href='userList.go'" type="reset">직원리스트</button>
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

if(msg != ""){
	alert(msg);
}

// var phone = /^\d{3}-\d{3,4}-\d{4}$/;
// var idRule = /^\d{8}/;
// var RegExp = /^[a-zA-Z0-9]{4,12}$/; //id와 pwassword 유효성 검사 정규식
// var objId = document.getElementById("id"); //아이디
// var objPwd = document.getElementById("pwd"); //비밀번호
// var objPwd2 = document.getElementById("pwd2"); //비밀번호확인
// var objEmail = document.getElementById("email");//메일
// var objName = document.getElementById("name"); //이름
// var objphone = document.getElementById("phone"); //폰번호

// if(objId.value==''){
//     alert("ID를 입력해주세요.");
//     return false;
// }
// if(!id.test(objId.value)){ //아이디 유효성검사
//     alert("ID는 8자의 숫자로만 입력하여 주세요.");        
//     document.getElementById("id").focus();
// };

// function idChk(){
// 	var id = document.getElementById('id').value;
	
// 	if(id.match(idRule) == null){
// 		alert("ID는 8자의 숫자로만 입력하여 주세요.");      
// 	}else{
// 		document.getElementById('idChk1').style.color = "green";
// 		document.getElementById('idChk1').innerHTML = "아이디 조건에 충족하였습니다."; 
// 	}
// 	if(idCheck==false){
// 		document.getElementById('idChk2').style.color = 'red';
// 		document.getElementById('idChk2').innerHTML = "! ID 중복 확인을 해 주세요";
// 	}
// }

function addr() {
    new daum.Postcode({
        oncomplete: function(data) {
        	// 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R' || data.userSelectedType === 'J') { // 사용자가 주소를 선택했을 경우
                addr = data.roadAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
            } 
            
            var zoneCode = '(' + data.zonecode + ') ';
            
            addr = zoneCode + addr + extraAddr;
            document.getElementById("client_address").value = addr;
            document.getElementById("address_detail").focus();
        }
    }).open();
}
 
$('#add').on('click',function() {
		var sel = '';
		var cnt = $('#career1 div').length;
		if(cnt < 4) {
		sel += '<div class="school'+cnt+'">';
		sel += '<select name="emp_career_idx" class="form-control ">'
		sel += '<option value="없음" selected="selected">없음</option>'
		sel += '<option value="고등학교">고등학교</option>'	  
		sel += '<option value="대학교">대학교</option>'  
		sel += '<option value="대학원">대학원</option></select>'	
		sel += '<input type="text" name="emp_school_name" placeholder="학교명" class="form-control "/>'	  
		sel += '<input type="text" name="emp_department" placeholder="학과" class="form-control "/>'	  
		sel += '<input type="text" name="emp_degree" placeholder="학위" class="form-control "/>'	  
		sel += '입학일<input type="date" name="emp_career_start" value="yyyy-mm-dd" class="form-control "/>'	  
		sel += '졸업일<input type="date" name="emp_career_end" value="yyyy-mm-dd" class="form-control "/>'	  
		sel += '<input type="text" name="emp_career_etc" placeholder="비고" class="form-control "/>'	  
		sel += '<input type="button" name="delete" value="삭제" class="del1"/>'	  
		sel += '</div>'
		$('#career1').append(sel);
		} else {
			return false;
		}
		
});

$('#add1').on('click',function() {
		var sel1 = '';
		var cnt = $('#career2 div').length;
		if(cnt < 11) {
		sel1 += '<div class="spec'+cnt+'">';
		sel1 += '<select name="emp_career_idx1" class="form-control ">'
		sel1 += '<option value="없음" selected="selected">없음</option>'
		sel1 += '<option value="경력">경력</option></select>'	
		sel1 += '<input type="text" name="emp_school_name1" placeholder="회사명" class="form-control "/>'	  
		sel1 += '<input type="text" name="emp_department1" placeholder="부서" class="form-control "/>'	  
		sel1 += '<input type="text" name="emp_degree1" placeholder="직급" class="form-control "/>'	  
		sel1 += '입학일<input type="date" name="emp_career_start1" value="yyyy-mm-dd" class="form-control "/>'	  
		sel1 += '졸업일<input type="date" name="emp_career_end1" value="yyyy-mm-dd" class="form-control "/>'	  
		sel1 += '<input type="text" name="emp_career_etc1" placeholder="맡은업무" class="form-control "/>'	  
		sel1 += '<input type="button" name="delete" value="삭제" class="del2"/>'	  
		sel1 += '</div>'
		$('#career2').append(sel1);
		} else {
			return false;
		}
		
});
 
$('#add2').on('click',function() {
		var sel2 = '';
		var cnt = $('#license div').length;
		if(cnt < 11) {
		sel2 += '<div class="licen'+cnt+'">';
		sel2 += '<input type="text" name="license_name" placeholder="자격증명" class="form-control ">'	  
		sel2 += '취득일<input type="date" name="license_date" value="yyyy-mm-dd" class="form-control "/>'	  
		sel2 += '<input type="text" name="license_place" placeholder="발급처" class="form-control ">'	  
		sel2 += '<input type="text" name="license_result" placeholder="결과" class="form-control ">'	  
		sel2 += '<input type="button" name="delete" value="삭제" class="del3"/>'	  
		sel2 += '</div>'
		$('#license').append(sel2);
		} else {
			return false;
		}
		
});

$(document).on("click",".del1",function(){
	   $('.school1').remove();
	   
	   for(var i=0; i<4; i++){
	      $(".school"+(i+2)).attr("class","school"+(i+1));

	   }

});
$(document).on("click",".del2",function(){
	   $('.spec1').remove();
	   
	   for(var i=0; i<4; i++){
	      $(".spec"+(i+2)).attr("class","spec"+(i+1));

	   }

});
$(document).on("click",".del3",function(){
	   $('.licen1').remove();
	   
	   for(var i=0; i<4; i++){
	      $(".licen"+(i+2)).attr("class","licen"+(i+1));

	   }

});


	   
// $(document).on('click','#maker',function() {
// 	   if($(".sightName1").val().length > 0 && $(".sightName2").val().length > 0) {
// 	   var sight1 = [];
// 	   var locIdx1 = [];
// 	   for(var i=0; i<$('#route1').children().length+1; i++) {      
// 	      sight1[i] = $(".sightName"+(i+1)).val();
// 	      locIdx1[i] = $(".locationIdx_input"+(i+1)).val();
// 	   }
// 	   $.ajax({
// 		      url: 'routeWrite1',
// 		      dataType: 'json',
// 		      type: 'post',
// 		      data : {
// 		         locIdx : locIdx1,
// 		         content : content1,
// 		         loginId : loginId1,
// 		         title : title1
// 		      },
// 		      success: function(data) {
// 		         alert("글 작성을 완료하였습니다.");
// 		         board_idx = data.board_idx;
// 		         console.log(board_idx);
// 		         location.href="./routeDetail?board_idx="+board_idx;
// 		      }
// 		      });
// 		   }else {
// 		      alert("최소 출발지, 도착지 입력 바람");
// 		   }
// });

// $(document).on("click",".delWay1",function(){
// 	   $('.wayPoint1').remove();
	   
// 	   for(var i=0; i<4; i++){
// 	      $(".wayPoint"+(i+2)).attr("class","wayPoint"+(i+1));
// 	      $(".sightName"+(i+4)).attr("class","sightName"+(i+3));
// 	      $(".locationIdx_input"+(i+4)).attr("class","locationIdx_input"+(i+3));
// 	      $(".search"+(i+4)).attr("class","search"+(i+3));
// 	      $(".delWay"+(i+2)).attr("class","delWay"+(i+1));
// 	   }

// 	});
 
//  $('#addWay').on('click',function() {
// 	   var wayPoint = '';
// 	   var cnt = $('#route1 div').length;
// 	   if(cnt < 6) {
// 	   wayPoint += '<div class="wayPoint'+cnt+'">';
// 	   wayPoint += '<input type="text" class="sightName'+(cnt+2)+'" class="wpTxt'+cnt+'" value="" placeholder="경유지" readonly>';
// 	   wayPoint += '<input class="locationIdx_input'+(cnt+2)+'" readonly="readonly" type="hidden" />';
// 	   wayPoint += '<input type="button" value="위치찾기" '+'onclick="location_popup'+(cnt+2)+'();">';
// 	   wayPoint += '<input type="button" class="delWay'+cnt+'" value="-">';
// 	   wayPoint += '</div>';
// 	   $('#route1').append(wayPoint);
// 	   } else {
// 	      return false;
// 	   }

// 	});


</script>
</html>