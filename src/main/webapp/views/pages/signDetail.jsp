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
	
	#sign {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 8pt;
	}
	
	#nosign {
		font-size: 8pt;
	}
	
	#nosign:hover {
		background-color:orange;
		border-color:orange;
	}
	
	#signHistory {
		font-size: 8pt;
		margin-left: 1%;
		padding-inline: revert;
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
								<h2>결재 문서</h2>
								<button type="button" class="btn btn-warning" id="signHistory" onclick="sign_history()">히스토리</button>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
							<table class="table">
								<tr>
									<th scope="row">문서 종류</th>
									<td>${signdto.doc_type_name}
										<%-- <input type="hidden" name="doc_type_idx" value="${docformdto.doc_type_idx}"> --%>
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
											${sign.emp_name}
										</c:forEach>
									<td>
								</tr>
								<tr>
									<th scope="row">참조자</th>
									<td>
										<c:forEach items="${referlist}" var="refer">
											${refer.emp_name}
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
												<th scope="row">코멘트</th>
												<td>
													<c:forEach items="${signmemlist}" var="signmem">
														<c:choose>
														<c:when test="${signmem.emp_id eq loginId}">
															<p style="font-weight:bold">${signmem.emp_name} : <input type="text" style="width:80%; border: 1px solid lightgray" placeholder="코멘트를 입력하세요"/> </p>
														</c:when>
														<c:when test="${signmem.emp_id ne loginId}">
															<p style="font-weight:bold">${signmem.emp_name} : <input type="text" style="width:80%; background-color:lightgray; border: gray" readonly/> </p>
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
										<input type="file" name="files" multiple="multiple"/>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<button type="button" onclick="location.href='/signList.go'" class="btn btn-round btn-secondary" id="goList" style="float:left">목록</button>
										<c:set var="loginId" value="${sessionScope.loginId}" scope="page"/>
											<c:forEach items="${signmemlist}" var="sign">
												<c:if test="${loginId eq sign.emp_id}">
													<button type="button" onclick="" class="btn btn-round btn-secondary" id="sign" style="float:right">결재</button>
													<button type="button" onclick="" class="btn btn-round btn-secondary" id="nosign" style="float:right">반려</button>
												</c:if> 
											</c:forEach>
										
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
											</c:choose>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
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

 var display = document.getElementById('modaltitle');
 if("${msg}" != ""){
	display.innerHTML = "${msg}";
	$('#secondmodal').modal();
 }
 
 var loginId = ${sessionScope.loginId};
 console.log(loginId);
 
function sign_history(){
	$('#signhistorymodal').modal();

}
 
</script>
</html>