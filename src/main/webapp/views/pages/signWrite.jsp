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
<link rel="stylesheet" href="/res/style.css" />
<link rel="stylesheet" href="/richtexteditor/rte_theme_default.css" />
<script type="text/javascript" src="/richtexteditor/rte.js"></script>
<script type="text/javascript" src='/richtexteditor/plugins/all_plugins.js'></script>
<style>
	#signwritediv{
		justify-content: center;
   		align-items: center;
   		flex-direction: column;
   		width: 80%;
	}
	
	th {
		display: inline-block;
		white-space: nowrap;
	}
	
	#savebtn {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 8pt;
	}
	
	#goList {
		font-size: 8pt;
		margin-right: 2%;
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
			<div id="signwritediv">
				<form action="/sign/write.do" method="post" enctype="multipart/form-data">
					<div class="col-md-6 col-sm-6  ">
						<div class="x_panel" style="display: inline-table">
							<div class="x_title">
								<h2>결재 문서 작성</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
							<table class="table">
								<tr>
									<th scope="row">문서 종류</th>
									<td>${docformdto.doc_type_name}</td>
								</tr>
								<tr>
									<th scope="row">공개 여부</th>
									<td>
									<select id="team_open" name="sign_team_open">
										<option value="select" selected="selected" hidden="" disabled="disabled">선택</option>
										<option value="공개">공개</option>
										<option value="비공개">비공개</option>
									</select>
									</td>
								</tr>
								<tr>
									<th scope="row">작성자</th>
									<td></td>
								</tr>
								<tr>
									<th scope="row">팀</th>
									<td></td>
								</tr>
								<tr>
									<th scope="row">결재경로</th>
										<td>
									      <div id="sign1">
									         <div id="emp1">
									            <input type="text" class="empName1" class="firstEmp" value="" placeholder="사원 검색" onclick="empSearch_popup();" readonly>
									            <input class="empIdx_input1" readonly="readonly" type="hidden" />
									            <input type="button" id="addSignEmp" name="addSignEmp" value="+">
									         </div>
									      </div>
								      <td>
								</tr>
								<tr>
									<th scope="row">참조자</th>
									<td></td>
								</tr>
								<tr>
									<th scope="row">제목</th>
									<td><input type="text" name="sign_title" placeholder="제목을 입력하세요." style="width:50%"></td>
								</tr>
								<tr>
									<th scope="row">내용</th>
									<td>
										<div id="div_editor"></div>
										<input type="hidden" name="sign_content"/>
									</td>
								</tr>
								<tr>
									<th scope="row">첨부파일</th>
									<td>
										<input type="file" name="files" multiple="multiple"/>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<button type="button" onclick="signSave()" class="btn btn-round btn-info" id="savebtn" style="float:right">저장</button>
										<button type="button" onclick="location.href='/docFormList.go'" class="btn btn-round btn-secondary" id="goList" style="float:right">목록</button>
									</td>
								</tr>
							</table>
							<div id="docformsigncontent" style="display:none">${docformdto.doc_form_content}</div>
							</div>
						</div>
					</div>
				
				</form>
				
				<!-- modal -->
				<div class="modal fade bs-example-modal-sm" id="secondmodal" tabindex="-1" role="dialog" aria-hidden="true">
					<div class="modal-dialog modal-sm">
	  					<div class="modal-content">
	  						<div class="modal-header">
	     						<h5 class="modal-title" id="myModalLabel2">사원 검색</h5>
	      						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	    					</div>
							<div class="modal-body">
								<h6 id="modaltitle"></h6>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
							</div>
						</div>
					</div>
				</div>
				<!-- /modal -->
				
				</div>
			</div>
			<!-- /page content -->

		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>

var config = {};
config.editorResizeMode = "none";

var editor = new RichTextEditor("#div_editor",config);

editor.setHTMLCode($("#docformsigncontent").html());


function signSave(){
	$('input[name="sign_content"]').val(editor.getHTMLCode());
	$('form').submit();
}


/* $('#addSignEmp').on('click',function() {
	   var addSign = '';
	   var cnt = $('#sign1 div').length;
	   console.log(cnt);
	   if(cnt < 5) {
		addSign += '<div class="emp'+(cnt+2)'">';
		addSign += '<input type="text" class="empName'+(cnt+2)+'" class="Emp'+(cnt+1)+'" value="" placeholder="사원 검색" onclick="empSearch_popup'+(cnt+2)+'();" readonly>';
		addSign += '<input class="empIdx_input'+(cnt+2)+'" readonly="readonly" type="hidden" />';
		addSign += '<input type="button" class="delEmp'+cnt+'" value="-">';
		addSign += '</div>';
	   $('#sign1').append(addSign);
	   } else {
	      return false;
	   }
}); */


/* function clickP()){
//	alert("사원 추가");
	console.log("사원 추가");
	var addSign = '';
		addSign += '<div class="emp2">';
		addSign += '<input type="text" class="empName2" class="secondEmp" value="" placeholder="사원 검색" onclick="empSearch_popup2();" readonly>';
		addSign += '<input class="empIdx_input2" readonly="readonly" type="hidden" />';
		addSign += '<input type="button" class="delEmp1" value="-">';
		addSign += '</div>';
		$('#sign1').append(addSign);
} */

$('#addSignEmp').on('click',function(){
	console.log("사원추가");
	var addSign = '';
	addSign += '<div class="emp2">';
	addSign += '<input type="text" class="empName2" class="secondEmp" value="" placeholder="사원 검색" onclick="empSearch_popup2();" readonly>';
	addSign += '<input class="empIdx_input2" readonly="readonly" type="hidden" />';
	addSign += '<input type="button" class="delEmp1" value="-">';
	addSign += '</div>';
	$('#sign1').append(addSign);
}

function empSearch_popup(){
	$('#secondmodal').modal()
	
}
ㄴ

</script>
</html>