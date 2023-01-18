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
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_title">
									<h2>직원 등록</h2>

									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<br />
									<form action="memberJoin.do" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" method="POST" enctype="multipart/form-data" onsubmit="return checks()">

										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">사진<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="file" name="file" id="file1" required="required" class="form-control "/>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">아이디<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="emp_id" id="id" required="required" class="form-control "/>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">비밀번호<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="password" id="pw" required="required" class="form-control "/>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">비밀번호 확인<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="password" name="emp_pw" id="pw_chk" required="required" class="form-control "/>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">이름<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="emp_name" id="name" required="required" class="form-control "/>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">생년월일<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="date" name="emp_birth" value="yyyy-mm-dd" required="required" class="form-control "/>
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
												<input type="text" name="emp_phone" id="phone" required="required" class="form-control ">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">이메일<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="emp_email" id="email" required="required" class="form-control ">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">주소<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" id="emp_address" onclick="addr()" name="emp_address" class="form-control "/>
												<input type="text" id="address_detail" placeholder="상세주소" name="address_detail" required="required" class="form-control ">
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
												<label class="col-form-label col-md-3 col-sm-3 label-align">학력
												</label>
												<div id="career1" class="col-md-6 col-sm-6 " >
													<div class="school">
														<select name="emp_career_idx" class="form-control ">
															<option value="없음" selected="selected">없음</option>
															<option value="고등학교">고등학교</option>
															<option value="대학교">대학교</option>
															<option value="대학원">대학원</option>
														</select>
														<input type="text" name="emp_school_name" placeholder="학교명" class="form-control ">
														<input type="text" name="emp_department" placeholder="학과" class="form-control ">
														<input type="text" name="emp_degree" placeholder="학위" class="form-control ">
														입학일<input type="date" name="emp_career_start" placeholder="yyyy-mm-dd" class="form-control ">
														졸업일<input type="date" name="emp_career_end" placeholder="yyyy-mm-dd" class="form-control ">
														<input type="text" name="emp_career_etc" placeholder="비고" class="form-control ">
														<input type="button" value="추가" id="add"/>
													</div>
												</div>
											</div>
										</div>
										<div>
											<div class="item form-group">
												<label class="col-form-label col-md-3 col-sm-3 label-align">이력
												</label>
												<div id="career2" class="col-md-6 col-sm-6 ">
													<div class="spec">
														<select name="emp_career_idx" class="form-control ">
															<option value="없음" selected="selected">없음</option>
															<option value="경력">경력</option>
														</select>
														<input type="text" name="emp_school_name" placeholder="회사명" class="form-control ">
														<input type="text" name="emp_department" placeholder="부서" class="form-control ">
														<input type="text" name="emp_degree" placeholder="직급" class="form-control ">
														입사일<input type="date" name="emp_career_start" placeholder="yyyy-mm-dd" class="form-control ">
														퇴사일<input type="date" name="emp_career_end" placeholder="yyyy-mm-dd" class="form-control ">
														<input type="text" name="emp_career_etc" placeholder="맡은업무" class="form-control ">
														<input type="button" value="추가" id="add1"/>
													</div>
												</div>
											</div>
										</div>
										<div>
											<div class="item form-group">
												<label class="col-form-label col-md-3 col-sm-3 label-align">자격증
												</label>
												<div id="license" class="col-md-6 col-sm-6 ">
													<div class="licen">
														<input type="text" name="license_name" placeholder="자격증명" class="form-control ">
														취득일<input type="date" name="license_date" placeholder="yyyy-mm-dd" class="form-control ">
														<input type="text" name="license_place" placeholder="발급처" class="form-control ">
														<input type="text" name="license_result" placeholder="결과" class="form-control ">
														<input type="button" value="추가" id="add2"/>
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
												<select name="auth_type" class="form-control " required="required">
												  <option value="1" selected="selected">공개문서 열람</option>
												  <option value="2">전체 열람</option>
											    </select>
											</div>
										</div>	
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">서명 이미지<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="file" name="file2" id="file2" required="required" class="form-control ">
											</div>
										</div>									
										<div class="ln_solid"></div>
										<div class="item form-group">
											<div class="col-md-6 col-sm-6 offset-md-3">
												<button class="btn btn-round btn-secondary ghl" onclick="location.href='memberList.go'" type="reset">직원리스트</button>
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
            document.getElementById("emp_address").value = addr;
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
		sel1 += '<select name="emp_career_idx" class="form-control ">'
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

  
function checks(){
    var hobbyCheck = false;
    var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
    var getCheck= RegExp(/^[a-zA-Z0-9]{4,12}$/);
    var getName= RegExp(/^[가-힣]+$/);
    var getId = RegExp(/^[0-9]{8}$/);
    var fmt = RegExp(/^\d{6}[1234]\d{6}$/); //형식 설정
    var buf = new Array(13); //주민등록번호 배열
    var phone = RegExp(/^\d{3}-\d{3,4}-\d{4}$/);
    
    //아이디 공백 확인
    if($("#id").val() == ""){
      alert("아이디를 입력해주세요.");
      $("#id").focus();
      return false;
    }
         
    //아이디 유효성검사
    if(!getId.test($("#id").val())){
      alert("00년00월0000순서로 숫자8자리 입력해주세요. ");
      $("#id").val("");
      $("#id").focus();
      return false;
    }

    //비밀번호 공백 확인
    if($("#pw").val() == ""){
      alert("패스워드를 입력해주세요.");
      $("#pw").focus();
      return false;
    }
         
    
    //비밀번호 유효성검사
    if(!getCheck.test($("#pw").val())){
      alert("4자리 이상 12자리 이하로 입력해주세요.");
      $("#pw").val("");
      $("#pw").focus();
      return false;
    }
         
    //비밀번호 확인란 공백 확인
    if($("#pw_chk").val() == ""){
      alert("패스워드 확인란을 입력해주세요");
      $("#pw_chk").focus();
      return false;
    }
         
    //비밀번호 서로확인
    if($("#pw").val() != $("#pw_chk").val()){
        alert("비밀번호가 상이합니다");
        $("#pw").val("");
        $("#pw_chk").val("");
        $("#pw").focus();
        return false;
    }
        
    //이메일 공백 확인
    if($("#email").val() == ""){
      alert("이메일을 입력해주세요");
      $("#email").focus();
      return false;
    }
         
    //이메일 유효성 검사
    if(!getMail.test($("#email").val())){
      alert("이메일형식에 맞게 입력해주세요")
      $("#email").val("");
      $("#email").focus();
      return false;
    }
         
    //이름 공백 검사
    if($("#name").val() == ""){
      alert("이름을 입력해주세요");
      $("#name").focus();
      return false;
    }

    //이름 유효성 검사
    if(!getName.test($("#name").val())){
      alert("이름형식에 맞게 입력해주세요")
      $("#name").val("");
      $("#name").focus();
      return false;
    }
    
    //전화번호 공백 검사
    if($("#phone").val() == ""){
      alert("전화번호를 입력해주세요");
      $("#phone").focus();
      return false;
    }

    //전화번호 유효성 검사
    if(!phone.test($("#phone").val())){
      alert("전화번호 형식에 맞게 입력해주세요")
      $("#phone").val("");
      $("#phone").focus();
      return false;
    }
    
    //프로필 사진 공백 검사
    if($("#file1").val() == ""){
      alert("프로필사진을 첨부해주세요");
      $("#file1").focus();
      return false;
    }
    
    //서명이미지 공백 검사
    if($("#file2").val() == ""){
      alert("서명이미지를 첨부해주세요");
      $("#file2").focus();
      return false;
    }

}


</script>
</html>