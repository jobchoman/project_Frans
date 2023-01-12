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
									<td>
										<input type="hidden" name="doc_type_idx" value="${docformdto.doc_type_idx}">
									</td>
								</tr>
								<tr>
									<th scope="row">공개 여부</th>
									<td></td>
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
										<td><td>
								</tr>
								<tr>
									<th scope="row">참조자</th>
										<td></td>
								</tr>
								<tr>
									<th scope="row">제목</th>
									<td></td>
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
										<button type="button" onclick="location.href='/signList.go'" class="btn btn-round btn-secondary" id="goList" style="float:right">목록</button>
									</td>
								</tr>
							</table>
							<div id="docformsigncontent" style="display:none">${docformdto.doc_form_content}</div>
							</div>
						</div>
						</div>
				
					</form>
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