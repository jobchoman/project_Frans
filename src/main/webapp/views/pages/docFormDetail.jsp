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
<!-- <script type="text/javascript">
history.replaceState({}, null, location.pathname);
</script> -->
<style>
	#formWritediv{
		justify-content: center;
   		align-items: center;
   		flex-direction: column;
   		width: 100%;
	}
	
	th {
		display: inline-block;
		white-space: nowrap;
	}
	
	#usebtn {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 8pt;
	}
	
	#goList {
		font-size: 8pt;
		margin-right: 2%;
	}
	
	#uphit {
		float: right;
		font-size: 7pt;
		color: gray;
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
			<div id="formWritediv">
				<form action="/docForm/write.do" method="post">
					<div class="col-md-6 col-sm-6  " style="max-width: 100%">
						<div class="x_panel" style="display: inline-table; width: 80%; margin-top: 3%;">
							<div class="x_title">
								<h2>문서 서식</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
							<div id="uphit">조회수: ${docformdto.doc_form_upHit} 사용수: ${docformdto.doc_form_use}</div>
							<table class="table">
								<tr>
								<!-- 양식 종류, 작성자, 팀, 조회수, 사용수, 양식 이름, 내용 -->
									<th scope="row">문서 종류</th>
									<td colspan="3">${docformdto.doc_type_name}</td>
								</tr>
								<tr>
									<th scope="row">작성자</th>
									<td>${docformdto.emp_name}</td>
									<th scope="row">팀</th>
									<td>${docformdto.team_name}</td>
								</tr>
								<tr>
									<th scope="row">이름</th>
									<td>${docformdto.doc_form_name}</td>
								</tr>
								<tr>
									<th scope="row">서식</th>
									<td>
										<div id="div_editor"></div>
										<%-- <div id="doc_form_content" name="doc_form_content" style="display:none">${docformdto.doc_form_content}</div> --%>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<button type="button" onclick="location.href='/signWrite.go?doc_form_idx=${docformdto.doc_form_idx}'" class="btn btn-round btn-info" id="usebtn" style="float:right">사용</button>
										<button type="button" onclick="location.href='/docFormList.go'" class="btn btn-round btn-secondary" id="goList" style="float:right">목록</button>
									</td>
								</tr>
							</table>
							<div id="doc_form_content" name="doc_form_content" style="display:none">${docformdto.doc_form_content}</div>
							</div>
						</div>
					</div>
				
				</form>
				</div>
			</div>
 
			<div class="modal fade bs-example-modal-sm" id="secondmodal" tabindex="-1" role="dialog" aria-hidden="true">
				<div class="modal-dialog modal-sm">
  					<div class="modal-content">
  						<div class="modal-header">
     						<h5 class="modal-title" id="myModalLabel2">서식 등록</h5>
      						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
    					</div>
						<div class="modal-body">
							<h6 id="modaltitle">${msg}</h6>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
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

	var config = {};
	config.editorResizeMode = "none";
	config.toolbar = "simple"

	config.toolbar_simple = "{save,print,html2pdf,code}";
	
	var editor = new RichTextEditor("#div_editor",config);
	
	editor.setHTMLCode($("#doc_form_content").html());
	editor.setReadOnly();
	
	 var display = document.getElementById('modaltitle');
     if("${msg}" != ""){
		display.innerHTML = "${msg}";
		$('#secondmodal').modal();
     }

	
</script>
</html>