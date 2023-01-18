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
#resetBtn{
	background-color:#2A3F54;
   border-color:#2A3F54;
   font-size: 8pt;
   float: right;
   margin-top: 5px;
}

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
			<div class="right_col" role="main">
				<div class="">
					<div class="page-title">

					</div>
					<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_title">
									<h2>정보 수정</h2>

									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<br />
									<form action="memberUpdate.do" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left" method="POST" enctype="multipart/form-data">

										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">사진
											</label>
											<div class="col-md-6 col-sm-6 ">
												<c:forEach items="${fileList}" var="path">
													<img width="100" src="memberPhoto.do?path=${path.file_new}"/>
												</c:forEach>
												<input type="file" name="file" class="form-control " readonly="readonly">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">아이디
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="emp_id" class="form-control " value="${mem.emp_id}" readonly="readonly">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">비밀번호
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="password" name="emp_pw" class="form-control " readonly="readonly">
												
											</div>
											<button onclick = "$('#secondmodal').modal()" type="button" class="btn btn-round btn-info"
										id="resetBtn" value="">초기화</button>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">이름
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="emp_name" value="${mem.emp_name}" class="form-control " readonly="readonly"> 
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">연락처
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="emp_phone" value="${mem.emp_phone}" class="form-control " readonly="readonly">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">이메일
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" name="emp_email" value="${mem.emp_email}" class="form-control " readonly="readonly">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">주소
											</label>
											<div class="col-md-6 col-sm-6 ">
												<input type="text" id="emp_address" onclick="addr()" value="${mem.emp_address}" name="emp_address" class="form-control " readonly="readonly"/>
												<input type="text" id="address_detail" name="address_detail"  class="form-control " readonly="readonly">
											</div>
										</div>
										<div>
											<div class="item form-group">
												<label class="col-form-label col-md-3 col-sm-3 label-align">학력
												</label>
												<div id="career1" class="col-md-6 col-sm-6 " >
													<div class="school">
														<c:forEach items="${memSchool}" var="memSchool">
														<c:if test="${memSchool.emp_career_idx != '경력'}">
															<select name="emp_career_idx" class="form-control "> 
																<option value="없음" ${memSchool.emp_career_idx == '없음' ? "selected" :""}>없음</option>
																<option value="고등학교" ${memSchool.emp_career_idx == '고등학교' ? "selected" :""}>고등학교</option>
																<option value="대학교" ${memSchool.emp_career_idx == '대학교' ? "selected" :""}>대학교</option>
																<option value="대학원" ${memSchool.emp_career_idx == '대학원' ? "selected" :""}>대학원</option>
															</select>
															<input type="text" name="emp_school_name" value="${memSchool.emp_school_name}" class="form-control ">
															<input type="text" name="emp_department" value="${memSchool.emp_department}" class="form-control ">
 															<input type="text" name="emp_degree" value="${memSchool.emp_degree}" class="form-control ">
															입학일<input type="date" name="emp_career_start" value="${memSchool.emp_career_start}" class="form-control ">
															졸업일<input type="date" name="emp_career_end" value="${memSchool.emp_career_end}" class="form-control ">
															<input type="text" name="emp_career_etc" value="${memSchool.emp_career_etc}" class="form-control ">
															<br>
														</c:if>
														</c:forEach>
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
														<c:forEach items="${memSchool}" var="memSchool">
														<c:if test="${memSchool.emp_career_idx == '경력'}">
															<select name="emp_career_idx" class="form-control "> 
																<option value="없음" ${memSchool.emp_career_idx == '없음' ? "selected" :""}>없음</option>
																<option value="경력" ${memSchool.emp_career_idx == '경력' ? "selected" :""}>경력</option>
															</select>
															<input type="text" name="emp_school_name" value="${memSchool.emp_school_name}" class="form-control ">
															<input type="text" name="emp_department" value="${memSchool.emp_department}" class="form-control ">
 															<input type="text" name="emp_degree" value="${memSchool.emp_degree}" class="form-control ">
															입사일<input type="date" name="emp_career_start" value="${memSchool.emp_career_start}" class="form-control ">
															퇴사일<input type="date" name="emp_career_end" value="${memSchool.emp_career_end}" class="form-control ">
															<input type="text" name="emp_career_etc" value="${memSchool.emp_career_etc}" class="form-control ">
															<br>
														</c:if>
														</c:forEach>
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
														<c:if test="${memLicense.size()>0}">
														<c:forEach items="${memLicense}" var="memLicense">
																<input type="text" name="license_name" value="${memLicense.license_name}" class="form-control ">
																취득일<input type="date" name="license_date" value="${memLicense.license_date}" class="form-control ">
																<input type="text" name="license_place" value="${memLicense.license_place}" class="form-control ">
																<input type="text" name="license_result" value="${memLicense.license_result}" class="form-control ">
																<br>
														</c:forEach>
														</c:if>
														<input type="button" value="추가" id="add2"/>
													</div>
												</div>
											</div>
										</div>
										<c:if test="${sessionScope.power == '0'}">
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">관리자 권한
											</label>
											<div class="col-md-6 col-sm-6 ">
												<select name="emp_admin_auth" class="form-control ">
													<option value="2" ${mem.emp_admin_auth == "2" ? "selected" :""}>일반사원</option>
													<option value="1" ${mem.emp_admin_auth == "1" ? "selected" :""}>관리자</option>
													<option value="0" ${mem.emp_admin_auth == "0" ? "selected" :""}>최고관리자</option>
												</select>
											</div>
										</div>	
										</c:if>									
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">팀
											</label>
											<div class="col-md-6 col-sm-6 ">
												<select id="team" name="team_idx" class="form-control ">
													<c:forEach items="${teamMem}" var="teamMem">
														<option value="${teamMem.team_idx}" ${mem.team_name == teamMem.team_name ? "selected" :""}>${teamMem.team_name}</option>
													</c:forEach>
											    </select>
											</div>
										</div>										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">직급
											</label>
											<div class="col-md-6 col-sm-6 ">
												<select name="pos_idx" class="form-control ">
													<c:forEach items="${posMem}" var="posMem">
													<option value="${posMem.pos_idx}" ${mem.pos_name == posMem.pos_name ? "selected" :""}>${posMem.pos_name}</option>
													</c:forEach>
											    </select>
											</div>
										</div>										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">직책
											</label>
											<div class="col-md-6 col-sm-6 ">
												<select name="duty_idx" class="form-control ">
													<c:forEach items="${dutyMem}" var="dutyMem">
													<option value="${dutyMem.duty_idx}" ${mem.duty_name == dutyMem.duty_name ? "selected" :""}>${dutyMem.duty_name}</option>
													</c:forEach>
											    </select>
											</div>
										</div>										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">상태
											</label>
											<div class="col-md-6 col-sm-6 ">
												<select name="emp_state_idx" class="form-control ">
													<c:forEach items="${stateMem}" var="stateMem">
													<option value="${stateMem.emp_state_idx}" ${mem.emp_state_name == stateMem.emp_state_name ? "selected" :""}>${stateMem.emp_state_name}</option>
													</c:forEach>
											    </select>
											</div>
										</div>										
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">팀등급
											</label>
											<div class="col-md-6 col-sm-6 ">
												<div></div>
												<select name="auth_type" class="form-control ">
												  <option value="1">공개문서 열람</option>
												  <option value="2">전체 열람</option>
											    </select>
											</div>
											
										</div>	
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align">서명 이미지
											</label>
											<div class="col-md-6 col-sm-6 ">
												<c:forEach items="${fileList1}" var="path">
													<img width="100" src="memberPhoto.do?path=${path.file_new}"/>
												</c:forEach>
												<input type="file" name="file2" class="form-control ">
											</div>
										</div>									
										<div class="ln_solid"></div>
										<div class="item form-group">
											<div class="col-md-6 col-sm-6 offset-md-3">
												<button class="btn btn-round btn-secondary ghl"  type="button" onclick="location.href='memberDetail.do?emp_id=${mem.emp_id}'">리스트</button>
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
					
					<!-- 비밀번호 초기화 모달 -->
					<div class="modal fade bs-example-modal-sm" id="secondmodal" tabindex="-1" role="dialog" aria-hidden="true">
                  <div class="modal-dialog modal-sm">
                       <div class="modal-content">

                         <div class="modal-header">
                              <h5 class="modal-title" id="myModalLabel2">비밀번호 초기화</h5>
                              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                         </div>
                         
                         <div class="modal-body">
                         	비밀번호를 초기화 하시겠습니까?
                        </div>
                         <div class="modal-footer">
                           <button type="button" class="btn btn-round btn-secondary ghl" data-dismiss="modal">취소</button>
                           <button id="delModalBtn" type="button" onclick = "resetPw('${mem.emp_id}')" data-dismiss="modal" class="btn btn-primary nam">확인</button>
                         </div>
             
                     </div>
                  </div>
               </div>
               <!-- 비밀번호 초기화 모달 끝 -->
               
			<!-- /page content -->

		
		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>

<script>
var msg = "${msg}";

if(msg != ""){
	alert(msg);
}

function resetPw(data) {
	console.log(data);
	$.ajax({
		type : 'post',
		url : '/member/resetPw.ajax',
		dataType : 'TEXT',
		data : {
			"emp_id" : data
		},
		success : function(data) {
			console.log(data);
			if(data == "성공"){
				alert("비밀번호 변경 성공");
			}
			
		},
		error : function(e) {
			console.log(e);
		}
	});
}


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
		sel1 += '<input type="text" name="emp_school_name" placeholder="회사명" class="form-control "/>'	  
		sel1 += '<input type="text" name="emp_department" placeholder="부서" class="form-control "/>'	  
		sel1 += '<input type="text" name="emp_degree" placeholder="직급" class="form-control "/>'	  
		sel1 += '입학일<input type="date" name="emp_career_start" value="yyyy-mm-dd" class="form-control "/>'	  
		sel1 += '졸업일<input type="date" name="emp_career_end" value="yyyy-mm-dd" class="form-control "/>'	  
		sel1 += '<input type="text" name="emp_career_etc" placeholder="맡은업무" class="form-control "/>'	  
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

// $(document).ready(function() {
//     $('#team').change(teamList);
// })

// function teamList() { // 학과
//         var teamCode = $('#team option:selected').val();
//         $('input#mem.team_name').val(teamCode);
//     };



</script>
</html>