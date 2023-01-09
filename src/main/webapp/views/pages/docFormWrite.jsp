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
	#formWritediv{
		justify-content: center;
   		align-items: center;
   		flex-direction: column;
   		width: 80%;
/*    		position: absolute; */
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
	
	input[type="text"]{
		width: 100%;
		border: 1px solid lightgray;
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
					<div class="col-md-6 col-sm-6  ">
						<div class="x_panel" style="display: inline-table">
							<div class="x_title">
								<h2>문서 서식 작성</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
							<table class="table">
								<tr>
									<th scope="row">문서 종류</th>
									<td>
									<select id="doc_type" name="doc_type_idx">
										<option value="select" selected="selected" hidden="" disabled="disabled">선택</option>
										<option value="DT001">품의서</option>
										<option value="DT002">지출결의서</option>
										<option value="DT003">세금계산서</option>
										<option value="DT004">전표</option>
										<option value="DT005">기안서</option>
										<option value="DT006">제안서</option>
										<option value="DT007">보고서</option>
									</select>
									</td>
								</tr>
								<tr>
									<th scope="row">작성자</th>
									<td></td>
								</tr>
								<tr>
									<th scope="row">서식 이름</th>
									<td><input type="text" name="doc_form_name" placeholder="서식 이름을 입력하세요." style="width:50%"></td>
								</tr>
								<tr>
									<th scope="row">서식 작성</th>
									<td>
										<div id="div_editor"></div>
										<input type="hidden" name="doc_form_content"/>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<button type="button" onclick="save()" class="btn btn-round btn-info" id="savebtn" style="float:right">저장</button>
										<button type="button" onclick="location.href='/docFormList.go'" class="btn btn-round btn-secondary" id="goList" style="float:right">목록</button>
									</td>
								</tr>
							</table>
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
/* 	var msg="${msg}";
	if(msg != ""){
		alert(msg);
	} */

	var config = {};
	config.editorResizeMode = "none";

	var editor = new RichTextEditor("#div_editor",config);
	
	function save(){
		$('input[name="doc_form_content"]').val(editor.getHTMLCode());
		$('form').submit();
	}
</script>
</html>