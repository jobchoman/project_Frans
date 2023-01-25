<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	
	#signdetaildiv{
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
	
	#goList {
		font-size: 8pt;
		margin-right: 2%;
	}
	
	#sign, #signmodal {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 8pt;
	}
	
	#nosign, #signreturn {
		font-size: 8pt;
	}
	
	#nosign:hover {
		background-color:orange;
		border-color:orange;
	}
	
	#signHistory {
		font-size: 8pt;
		margin-left: 5%;
		padding-inline: revert;
	}
	
	#commentInput, #commentInputOwn, #commentInputOther {
		margin-left: 1%;
	}
		
</style>
</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col"><jsp:include page="sidebar.jsp" /></div>

			<!-- top navigation -->
			<div class="top_nav"><jsp:include page="top_nav.jsp" />
			</div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col addWrap" role="main">
				<div id="signdetaildiv">
<!-- 				<form action="/sign/write.do" method="post" enctype="multipart/form-data"> -->
				<form action="/sign/detail.do" method="post">
					<div class="col-md-6 col-sm-6  " style="max-width: 100%">
						<div class="x_panel" style="display: inline-table">
							<div class="x_title">
								<div style="float: left; white-space: nowrap">
								<h2>결재 문서</h2>
								<input type="hidden" value="${signdto.sign_state_type}" id="sign_state"/>
								<input type="hidden" value="${loginName}" id="loginName">
								<c:if test="${admin lt '2'}">
								<button type="button" class="btn btn-warning" id="signHistory" onclick="sign_history()">히스토리</button>
								</c:if>
								</div>
								<div>
									<table id="sign_img" style="float:right; border:1px solid lightgray">
										<tr id="signImg_th">
											<th rowspan="2" style="writing-mode: vertical-rl; text-align: center">결재</th>
											<c:forEach items="${signDoMemCnt}" var="cnt">
												<th>${cnt.emp_name}</th>
											</c:forEach>
										</tr>
											<tr id="signImg_td">
											<c:forEach items="${sign_img}" var="img">
												<td>
													<img width="80" height="auto" src="sign/photo.do?path=${img}">
													<input type="hidden" value="${img}" id="sign_img"/>
												</td>
											</c:forEach>
											</tr>
									</table>
								</div>
									<div class="clearfix"></div>
							</div>

							<div class="x_content">

							<table class="table">
								<tr>
									<th scope="row">문서 종류</th>
									<td>${signdto.doc_type_name}
									<input type="hidden" id="sign_idx" value="${signdto.sign_idx}">
									</td>
								</tr>
								<tr>
									<th scope="row">공개 여부</th>
									<td>${signdto.sign_team_open}</td>
								</tr>
								<tr>
									<th scope="row">작성자</th>
									<td>${signdto.emp_name}</td>
								</tr>
								<tr>
									<th scope="row">팀</th>
									<td>${signdto.team_name}</td>
								</tr>
								<tr>
									<th scope="row">결재경로</th>
									<td>
										<c:forEach items="${signmemlist}" var="sign">
											<c:if test="${fn:length(signmemlist) eq 1 || sign.sign_mem_order == fn:length(signmemlist)}">
													${sign.emp_name}
											</c:if>
											<c:if test="${sign.sign_mem_order lt fn:length(signmemlist)}">
													${sign.emp_name} - 
											</c:if>
										</c:forEach>
									<td>
								</tr>
								<tr>
									<th scope="row">참조자</th>
									<td>
										<c:forEach items="${referlist}" var="refer">
										<%-- <c:if test="${fn:length(referlist) eq 1}"> --%>
											${refer.emp_name}&nbsp;&nbsp;
											<%-- </c:if>
											<c:if test="${fn:length(referlist) gt 1}">
													${refer.emp_name}, 
											</c:if> --%>
										</c:forEach>
									</td>
								</tr>
								<tr>
									<th scope="row">제목</th>
									<td>${signdto.sign_title}</td>
								</tr>
								<tr>
									<th scope="row">내용</th>
									<td>
										<div id="div_editor"></div>
										<input type="hidden" name="sign_content"/>
									</td>
								</tr>
								<!-- 결재 시 sign_mem 테이블에 코멘트, 시간, 결재 상태 업데이트 -->
								<c:set var="loginId" value="${sessionScope.loginId}" scope="page"/>
									<c:forEach items="${signmemlist}" var="sign">
										<c:if test="${loginId eq sign.emp_id}">
											<tr>
												<th scope="row">의견</th>
												<td>
													<c:forEach items="${signmemlist}" var="signmem">
														<c:choose>
														<c:when test="${signmem.emp_id eq loginId}">
														
															<c:choose>
																<c:when test="${signmem.sign_mem_comment ne null && signmem.sign_mem_comment ne '없음'}">
																	<p style="font-weight:bold" class="commentName">${signmem.emp_name}<input type="text" id="commentInputOwn" style="width:80%; border: none" readonly value="${signmem.sign_mem_comment}"/> </p>
																</c:when>
																<c:otherwise>
																	<p style="font-weight:bold" class="commentName">${signmem.emp_name}<input type="text" id="commentInput" style="width:80%; border: 1px solid lightgray" placeholder="코멘트를 입력하세요"/> </p>
																</c:otherwise>
															</c:choose>
															
														</c:when>
														<c:when test="${signmem.emp_id ne loginId}">
														
															<c:choose>
																<c:when test="${signmem.sign_mem_comment ne null && signmem.sign_mem_comment ne '없음'}">
																	<p style="font-weight:bold" class="commentNameOther">${signmem.emp_name}<input type="text" id="commentInputOther" style="width:80%; border: none" readonly value="${signmem.sign_mem_comment}"/> </p>
																</c:when>
																<c:otherwise>
																	<p style="font-weight:bold" class="commentNameOther">${signmem.emp_name}<input type="text" id="commentInputOther" style="width:80%; background-color:#D5D5D5; border: gray" readonly/> </p>
																</c:otherwise>
															</c:choose>
														
														</c:when>
														</c:choose>
													</c:forEach>
												</td>
											</tr>
										</c:if> 
									</c:forEach>
								<tr>
									<th scope="row">첨부파일</th>
										<td>
										<c:forEach items="${fileList}" var="path" varStatus="status">
											<p><a href="download.do?path=${path}">${orifileList[status.index]}</a></p>
										</c:forEach>
										</td>									
								</tr>
								<tr>
									<td colspan="2">
										<button type="button" onclick="location.href='/signList.go'" class="btn btn-round btn-secondary" id="goList" style="float:left">목록</button>
										<c:set var="loginId" value="${sessionScope.loginId}" scope="page"/>
										<c:if test="${signdto.sign_state_type eq '결재전' || signdto.sign_state_type eq '결재중'}">
											<c:forEach items="${signmemlist}" var="sign">
												<c:if test="${loginId eq sign.emp_id && sign.sign_mem_state eq '0'}">
													<input type="hidden" value="${sign.sign_mem_order}" id="sign_mem_order"/>
													<input type="hidden" value="${lastOrder}" id="lastorder"/>
													<!-- <button type="button" onclick="signDo()" class="btn btn-round btn-secondary" id="sign" style="float:right">결재</button> -->
													<button type="button" onclick="modalopen()" class="btn btn-round btn-secondary" id="sign" style="float:right">결재</button>
													<button type="button" onclick="returnmodal()" class="btn btn-round btn-secondary" id="nosign" style="float:right">반려</button>
												</c:if> 
											</c:forEach>
										</c:if>
									</td>
								</tr>
							</table>
							<div id="signdetail" style="display:none">${signdto.sign_content}</div>
							</div>
						</div>
						</div>
				
					</form>
				</div>
			</div>
			
			<!-- modal -->
			<div class="modal fade bs-example-modal-lg" id="signhistorymodal" tabindex="-1" role="dialog" aria-hidden="true">
				<div class="modal-dialog modal-lg">
	  					<div class="modal-content">
	  						<div class="modal-header">
	     						<h5 class="modal-title">결재 히스토리</h5>
	     						<input type="hidden" >
	      						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
	    					</div>
							<div class="modal-body">
								
							<div class="table-responsive">
								<table id="signhistoryTable" class="table table-striped jambo_table bulk_action" style="width:100%; text-align:center">
									<thead>
										<tr class="headings">
											<th class="column-title">사번</th>
											<th class="column-title">IP</th>
											<th class="column-title">이름</th>
											<th class="column-title">직급</th>
											<th class="column-title">날짜</th>
											<th class="column-title">결재상태</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${history}" var="his">
										<tr>
											<td>${his.emp_id}</td>
											<td>${his.sign_mem_ip}</td>
											<td>${his.emp_name}</td>
											<td>${his.pos_name}</td>
											<td>${his.sign_mem_time}</td>
											<c:choose>
												<c:when test="${his.sign_mem_state eq '0'}">
													<td>결재전</td>
												</c:when>
												<c:when test="${his.sign_mem_state eq '1'}">
													<td style="font-weight:bold; color:red">결재완료</td>
												</c:when>
												<c:when test="${his.sign_mem_state eq '2'}">
													<td style="font-weight:bold; color:orange">반려</td>
												</c:when>
											</c:choose>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>

							</div>
<!-- 							<div class="modal-footer">
								<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
							</div> -->
						</div>
					</div>
				</div>
			<!-- /modal -->
			
			<!-- modal -->
			<div class="modal fade bs-example-modal-sm" id="signdomodal" tabindex="-1" role="dialog" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">

						<div class="modal-header">
							<h5 class="modal-title" id="myModalLabel2"></h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
						</div>
						<div class="modal-body">
							<h6 id="modaltitle">결재하시겠습니까?</h6>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-round btn-secondary"  onclick="signDo()" id="signmodal" data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
			<!-- /modal -->
			
			<!-- modal -->
			<div class="modal fade bs-example-modal-sm" id="signreturnmodal" tabindex="-1" role="dialog" aria-hidden="true">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">

						<div class="modal-header">
							<h5 class="modal-title" id="myModalLabel2"></h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
						</div>
						<div class="modal-body">
							<h6 id="modaltitle">반려하시겠습니까?</h6>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-round btn-secondary"  onclick="signReturn()" id="signreturn" data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
			<!-- /modal -->
			
			<!-- /page content -->

		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>

var config = {};
config.editorResizeMode = "none";
config.toolbar = "simple"

config.toolbar_simple = "{save,print,html2pdf,code}";

var editor = new RichTextEditor("#div_editor",config);

editor.setHTMLCode($("#signdetail").html());
editor.setReadOnly();

/*  var display = document.getElementById('modaltitle');
 if("${msg}" != ""){
	display.innerHTML = "${msg}";
	$('#secondmodal').modal();
 } */
 
 var loginId = ${sessionScope.loginId};
 console.log(loginId);
 
function sign_history(){
	$('#signhistorymodal').modal();

}

function modalopen(){
	$('#signdomodal').modal();
}

function returnmodal(){
	$('#signreturnmodal').modal();
}

var sign_idx = $('#sign_idx').val();
var sign_order = $('#sign_mem_order').val();
var last_order_id = $('#lastorder').val();

console.log(sign_idx);
console.log(sign_order);


function signDo(){
	
	var comment = $('#commentInput').val();
//	console.log(comment);
	
	$.ajax({
		type:'get',
		url:'sign.do',
		data:{
			"loginId" : loginId,
			"sign_idx" : sign_idx,
			"comment" : comment,
			"sign_order" : sign_order,
			"last_order_id" : last_order_id
			},
		dataType:'JSON',
		success:function(data){
			console.log(data);
			draw_signImg_th(data);
			draw_signImg_td(data);
			inputComment(comment);
			window.location.href = "/signDetail.go?sign_idx="+sign_idx;
		},
		error:function(e){
			console.log(e);
		}
		
	});
}

var login_name = $('#loginName').val();
var sign_img = $('#sign_img').val();
//consoin_name);

function draw_signImg_th(list){
	console.log(list);
	var content = '';
	content += '<th>'+login_name+'</th>';
//	$('#signImg_th').empty();
	$('#signImg_th').append(content);
}

function draw_signImg_td(list){
	var content = '';
	content += '<td><img width="200" src="/photo.do?path='+sign_img+'"></td>';
//	$('#signImg_td').empty();
	$('#signImg_td').append(content);
}

function inputComment(comment){
	console.log($(".commentName").text());
	console.log($(".commentNameOther").text());
	console.log($(".commentNameOther").length);
	
	var otherSignLength = $(".commentNameOther").length;
	
	if(login_name == $(".commentName").text()){
		$("#commentInputOwn").val(comment);
	}

}

function signReturn(){
	
	var comment = $('#commentInput').val();
	
	$.ajax({
		type:'get',
		url:'sign/return.do',
		data:{
			"loginId" : loginId,
			"sign_idx" : sign_idx,
			"comment" : comment,
			"sign_order" : sign_order,
			"last_order_id" : last_order_id
			},
		dataType:'JSON',
		success:function(data){
			console.log(data);
//			draw_signImg_th(data);
//			draw_returnImg_td(data);
			inputComment(comment);
			window.location.href = "/signDetail.go?sign_idx="+sign_idx;
		},
		error:function(e){
			console.log(e);
		}
		
	});
}


 
</script>
</html>