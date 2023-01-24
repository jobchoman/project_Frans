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
	.addWrap {
	   display: flex;
	   flex-direction: column;
	   justify-content: flex-start;
	   align-items: center;
	}

	#signwritediv{
		justify-content: center;
   		align-items: center;
   		flex-direction: column;
   		width: 80%;
	}
	
	th {
		display: inline-block;
		white-space: nowrap;
		display: table-cell;
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
	
	#addSignEmp, #addRefEmp {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 9pt;
		padding: revert;
	}
	
	button[id^=delEmp]{
		font-size: 9pt;
		padding: revert;
		width: 22.41px;
		margin-left: 0.7%;
	}
	
	button[id^=delRef]{
		font-size: 9pt;
		padding: revert;
		width: 22.41px;
		margin-left: 0.7%;
	}
	
	#datatable_length {
		display: none;
	}
	
	.row > .col-sm-6:first-child {
		display: none;
	}

	#datatable_info {
		display: none;
	}
	
	.form-control{
		width: 20%;
		font-size: 9pt;
		display: inline;
	}
	
	input[type='search'] {
		width: 100%;
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
			<div id="signwritediv">
				<form action="/sign/write.do" method="post" enctype="multipart/form-data">
					<div class="col-md-6 col-sm-6  " style="max-width: 100%">
						<div class="x_panel" style="display: inline-table">
							<div class="x_title">
								<h2>결재 문서 작성</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
							<table class="table">
								<tr>
									<th scope="row">문서 종류</th>
									<td>
										${docformdto.doc_type_name}
										<input type="hidden" name="doc_type_idx" value="${docformdto.doc_type_idx}">
										<input type="hidden" name="doc_form_idx" value="${docformdto.doc_form_idx}">
									</td>
								</tr>
								<tr>
									<th scope="row">공개 여부</th>
									<td>
									<div class="form-group">
									<select id="team_open" class="form-control" name="sign_team_open" style="width:10%">
										<option value="select" selected="selected" hidden="" disabled="disabled">선택</option>
										<option value="공개">공개</option>
										<option value="비공개">비공개</option>
									</select>
									</div>
									</td>
								</tr>
								<tr>
									<th scope="row">작성자</th>
									<td>${memberdto.emp_name}</td>
								</tr>
								<tr>
									<th scope="row">팀</th>
									<td>${memberdto.team_name}</td>
								</tr>
								<tr>
									<th scope="row">결재경로</th>
										<td>
									      <div id="sign">
									         <div id="emp1">
									         	<!-- <input type="text" class="empName1" name="empName1" id="empName1" placeholder="사원 검색" onclick="empSearch_popup(event)" readonly> -->
									            <input type="text" class="empName1 form-control" name="empName" id="empName1" placeholder="사원 검색" onclick="empSearch_popup(event)" readonly>
									            <input class="empIdx_input1" name="empIdx_input" readonly="readonly" type="hidden" />
									            <button type="button" id="addSignEmp" name="addSignEmp" onclick="clickP()" class="btn btn-round btn-info">+</button>
									         </div>
									      </div>
								      <td>
								</tr>
								<tr>
									<th scope="row">참조자</th>
									<td>
										<div id="ref">
									         <div id="ref_emp1">
									         	<!-- <input type="text" class="empName1" name="empName1" id="empName1" placeholder="사원 검색" onclick="empSearch_popup(event)" readonly> -->
									            <input type="text" class="ref_empName1 form-control" name="ref_empName" id="ref_empName1" placeholder="사원 검색" onclick="empSearch_popup(event)" readonly>
									            <input class="ref_empIdx_input1" name="ref_empIdx_input" value="없음" readonly="readonly" type="hidden" />
									            <button type="button" id="addRefEmp" name="addRefEmp" onclick="addRef()" class="btn btn-round btn-info">+</button>
									         </div>
									      </div>
									</td>
								</tr>
								<tr>
									<th scope="row">제목</th>
									<td><input type="text" name="sign_title" class="form-control" placeholder="제목을 입력하세요." style="width:50%"></td>
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
				<div class="modal fade bs-example-modal-lg" id="searchEmp" tabindex="-1" role="dialog" aria-hidden="true">
					<div class="modal-dialog modal-lg">
	  					<div class="modal-content">
	  						<div class="modal-header">
	     						<h5 class="modal-title" id="searchEmpLabel">사원 검색</h5>
	     						<input type="hidden" >
	      						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	    					</div>
							<div class="modal-body">
								
							<div class="card-box table-responsive">
								<table id="datatable" class="table table-striped table-bordered" style="width:100%">
									<thead>
										<tr>
											<th>사번</th>
											<th>이름</th>
											<th>직급</th>
											<th>팀</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${memberlist}" var="memberlist" varStatus="idx">
										<tr class = "memberlistTr row_${idx.count}" value="${memberlist.emp_name}" onclick="memberSel(this)">
											<td>${memberlist.emp_id}</td>
											<td>${memberlist.emp_name}</td>
											<td>${memberlist.pos_name}</td>
											<td>${memberlist.team_name}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>

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
var loginId = "${sessionScope.emp_id}";
console.log(loginId);

var config = {};
config.editorResizeMode = "none";

var editor = new RichTextEditor("#div_editor",config);

editor.setHTMLCode($("#docformsigncontent").html());


function signSave(){
	$('input[name="sign_content"]').val(editor.getHTMLCode());
	$('form').submit();
}


/* 결재자 추가 */

// + 버튼 1번 눌렀을 때 cnt 1

function clickP(){
//	console.log("사원 추가");
	var addSign = '';
	var cnt = $('#sign div').length;
	console.log(cnt);
	if (cnt<5){
		addSign += '<div class="emp'+(cnt+1)+'">';
		addSign += '<input type="text" class="empName'+(cnt+1)+' form-control" name="empName" id="empName'+(cnt+1)+'" placeholder="사원 검색" onclick="empSearch_popup(event);" readonly>';
		addSign += '<input class="empIdx_input'+(cnt+1)+'" name="empIdx_input" readonly="readonly" type="hidden" />';
		addSign += '<button type="button" id="delEmp'+(cnt+1)+'" class="btn btn-round btn-secondary">-</button>';
		addSign += '</div>';
		$("#sign").append(addSign);
	}else {
		return false;
	}
}

function addRef(){
	var addInput = '';
	var cnt = $('#ref div').length;
	console.log(cnt);
	if (cnt<5){
		addInput += '<div class="ref_emp'+(cnt+1)+'">';
		addInput += '<input type="text" class="ref_empName'+(cnt+1)+' form-control" name="ref_empName" id="ref_empName'+(cnt+1)+'" placeholder="사원 검색" onclick="empSearch_popup(event);" readonly>';
		addInput += '<input class="ref_empIdx_input'+(cnt+1)+'" name="ref_empIdx_input" readonly="readonly" type="hidden" />';
		addInput += '<button type="button" id="delRef'+(cnt+1)+'" class="btn btn-round btn-secondary">-</button>';
		addInput += '</div>';
		$("#ref").append(addInput);
	}else {
		return false;
	}
}



/* 결재자 검색 */


var empInputIdx;

function empSearch_popup(event){
	empInputIdx = event.target.id;
	console.log(empInputIdx);
	
	$('#searchEmp').modal('show');
}

function memberSel(elem){
	console.log($(elem));
//		var empName = $(this).attr('value');
//		var empName = $(elem);
		var empName = $(elem).children('td:eq(1)').text();
		var empIdx = $(elem).children('td:eq(0)').text();
		console.log("클릭한 사원 이름: "+empName); 
		console.log("클릭한 사원 사번: "+empIdx); 
		
		if(empInputIdx == "empName1"){
			$("#empName1").val(empName);
			$(".empIdx_input1").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
		}else if (empInputIdx == 'empName2'){
//			debugger;
			$("#empName2").val(empName);
			$(".empIdx_input2").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}else if (empInputIdx == 'empName3'){
			$("#empName3").val(empName);
			$(".empIdx_input3").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}else if (empInputIdx == 'empName4'){
			$("#empName4").val(empName);
			$(".empIdx_input4").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}else if (empInputIdx == 'empName5'){
			$("#empName5").val(empName);
			$(".empIdx_input5").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}else if (empInputIdx == 'ref_empName1'){
			$("#ref_empName1").val(empName);
			$(".ref_empIdx_input1").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}else if (empInputIdx == 'ref_empName2'){
			$("#ref_empName2").val(empName);
			$(".ref_empIdx_input2").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}else if (empInputIdx == 'ref_empName3'){
			$("#ref_empName3").val(empName);
			$(".ref_empIdx_input3").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}else if (empInputIdx == 'ref_empName4'){
			$("#ref_empName4").val(empName);
			$(".ref_empIdx_input4").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}else if (empInputIdx == 'ref_empName5'){
			$("#ref_empName5").val(empName);
			$(".ref_empIdx_input5").val(empIdx);
			$(elem).attr('onclick','event.cancelBubble=true');
			$(elem).css('background-color',"lightgray");
			$(elem).css('color',"gray");
			$(elem).css('font-style',"bold");
			
		}
		
		$('#searchEmp').modal('hide');
}


/*
$(document).ready(function(){
	$("#searchEmp").modal("hide");

	$("#empName1").click(function(){
		$("#searchEmp").modal("show");
		console.log(this.id); // empName1
		var event = this.id;
		empSearch_popup(event);
	});
	
});
*/


/* 결재자 삭제 */


$(document).on("click","#delEmp2",function(){
	$('.emp2').remove();
   
	for(var i=0; i<3; i++){
		$(".emp"+(i+3)).attr("class","emp"+(i+2));
		$(".empName"+(i+3)).attr("class","empName"+(i+2)+" form-control");
		$("#empName"+(i+3)).attr("id","empName"+(i+2));
		$(".empIdx_input"+(i+3)).attr("class","empIdx_input"+(i+2));
		$("#delEmp"+(i+3)).attr("id","delEmp"+(i+2));
	}
});

$(document).on("click","#delEmp3",function(){
	$('.emp3').remove();
	   
	for(var i=0; i<2; i++){
		$(".emp"+(i+4)).attr("class","emp"+(i+3));
		$(".empName"+(i+4)).attr("class","empName"+(i+3)+" form-control");
		$("#empName"+(i+4)).attr("id","empName"+(i+3));
		$(".empIdx_input"+(i+4)).attr("class","empIdx_input"+(i+3));
		$("#delEmp"+(i+4)).attr("id","delEmp"+(i+3));
	}
});
	
$(document).on("click","#delEmp4",function(){
	$('.emp4').remove();
	var i = 0;	   
		$(".emp"+(i+5)).attr("class","emp"+(i+4));
		$(".empName"+(i+5)).attr("class","empName"+(i+4)+" form-control");
		$("#empName"+(i+5)).attr("id","empName"+(i+4));
		$(".empIdx_input"+(i+5)).attr("class","empIdx_input"+(i+4));
		$("#delEmp"+(i+5)).attr("id","delEmp"+(i+4));
});

$(document).on("click","#delEmp5",function(){
	$('.emp5').remove();
});


/* 참조자 삭제 */


$(document).on("click","#delRef2",function(){
	$('.ref_emp2').remove();
   
	for(var i=0; i<3; i++){
		$(".ref_emp"+(i+3)).attr("class","ref_emp"+(i+2));
		$(".ref_empName"+(i+3)).attr("class","ref_empName"+(i+2)+" form-control");
		$("#ref_empName"+(i+3)).attr("id","ref_empName"+(i+2));
		$(".ref_empIdx_input"+(i+3)).attr("class","ref_empIdx_input"+(i+2));
		$("#delRef"+(i+3)).attr("id","delRef"+(i+2));
	}
});

$(document).on("click","#delRef3",function(){
	$('.ref_emp3').remove();
	   
	for(var i=0; i<2; i++){
		$(".ref_emp"+(i+4)).attr("class","ref_emp"+(i+3));
		$(".ref_empName"+(i+4)).attr("class","ref_empName"+(i+3)+" form-control");
		$("#ref_empName"+(i+4)).attr("id","ref_empName"+(i+3));
		$(".ref_empIdx_input"+(i+4)).attr("class","ref_empIdx_input"+(i+3));
		$("#delRef"+(i+4)).attr("id","delRef"+(i+3));
	}
});
	
$(document).on("click","#delRef4",function(){
	$('.ref_emp4').remove();
	var i = 0;	   
		$(".ref_emp"+(i+5)).attr("class","ref_emp"+(i+4));
		$(".ref_empName"+(i+5)).attr("class","ref_empName"+(i+4)+" form-control");
		$("#ref_empName"+(i+5)).attr("id","ref_empName"+(i+4));
		$(".ref_empIdx_input"+(i+5)).attr("class","ref_empIdx_input"+(i+4));
		$("#delRef"+(i+5)).attr("id","delRef"+(i+4));
});

$(document).on("click","#delRef5",function(){
	$('.ref_emp5').remove();
});



/*
function empSearch_popup1(event){
	var empInputIdx = event.target.id;
	console.log(empInputIdx);
	
	var table = document.getElementById('datatable');
    var rowList = table.rows;
//    var rowName = rowList[10].innerHTML;
    console.log(rowList[10]);
//    console.log(rowName);
    
	
    $('#searchEmp').modal();
*/   
    
/*     $('.memberlistTr').on('click', emp_name_click);
	function emp_name_click(){
		var id = $(this).html();
    	console.log(id);
    	$("#empName1").innerHTML(id);
    } */
    
/*
    $("#datatable .memberlistTr").click(function(event){
 		var empName = $(this).attr('value');
		console.log(empName); 
//	    console.log(e.parentNode.parentNode.parentNode.parentNode.parentNode.rowIndex);
		$("#empName1").val(empName);
		
	    $('#searchEmp').modal('hide');
    });

}



function empSearch_popup2(event){
	var empInputIdx = event.target.id;
	console.log(empInputIdx);

	$('#searchEmp').modal();
	
	$("#datatable .memberlistTr").click(function(){
 		var empName = $(this).attr('value');
		console.log(empName); 
		$("#empName2").val(empName);
		$('#searchEmp').modal('hide');
	});
}

*/ 
    
    /*
	var table = document.getElementById('datatable');
    var rowList = table.rows;
    
    for (i=1; i<rowList.length; i++) {
        var row = rowList[i];     //thead 부분을 제외하기 위해 i가 1부터 시작됩니다.
        var tdsNum = row.childElementCount;     //아래 for문에서 사용하기 위해 row 하위에 존재하는 td의 갯수를 구합니다.
    		
        row.onclick = function() {
          return function() {
            var str = '';
            //row 안에 있는 값들을 순차적으로 가져오기
            for (var j=0; j<tdsNum; j++) {
              var row_value = this.cells[j].innerHTML;
              str += row_value + ' ';     //값을 하나의 문자열으로 만듭니다.
            };
            document.querySelector('.empName1').innerText = str;     //p태그 안에 값을 넣어줍니다.
          };
        }(row);
      }*/
    
/* 	var test = $(".memberlistTr").attr('value');
	console.log(test);
	for(var i=0; i<test.length; i++){
		console.log(test);
	} */
	






</script>
</html>