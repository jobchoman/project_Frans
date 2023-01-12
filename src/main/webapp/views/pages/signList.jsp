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
<style>
	#signlistdiv{
		display: flex;
		justify-content: center;
   		align-items: center;
   		flex-direction: column;
   		width: 80%;
	}
	
	.addWrap {
	   display: flex;
	   flex-direction: column;
	   justify-content: flex-start;
	   align-items: center;
	}
	
	#dateSearch {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 9pt;
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
			<div id="signlistdiv">
				<div style="width:100%"><h3 style="float:left">결재 문서</h3></div>
				<br/>
				<div style="width:100%">
					<div class="input-group" style="float: left">
						<h6 style="font-weight: bold">기간</h6>
						&nbsp;&nbsp;&nbsp;
						<p>시작일</p>
						&nbsp;&nbsp;
						<fieldset>
                          <div class="control-group">
                            <div class="controls">
                              <div class="col-md-11 xdisplay_inputx form-group row has-feedback">
                                <input type="text" class="form-control has-feedback-left" id="single_cal1" placeholder="First Name" aria-describedby="inputSuccess2Status">
                                <span class="fa fa-calendar-o form-control-feedback left" aria-hidden="true"></span>
                                <span id="inputSuccess2Status" class="sr-only">(success)</span>
                              </div>
                            </div>
                          </div>
                        </fieldset>
                        
                        <p>종료일</p>
                        &nbsp;&nbsp;
                        <fieldset>
                          <div class="control-group">
                            <div class="controls">
                              <div class="col-md-11 xdisplay_inputx form-group row has-feedback">
                                <input type="text" class="form-control has-feedback-left" id="single_cal2" placeholder="First Name" aria-describedby="inputSuccess2Status2">
                                <span class="fa fa-calendar-o form-control-feedback left" aria-hidden="true"></span>
                                <span id="inputSuccess2Status2" class="sr-only">(success)</span>
                              </div>
                            </div>
                          </div>
                        </fieldset>
						<span class="input-group-btn">
							<button type="button" class="btn btn-primary" id="dateSearch" onclick="dateSearch()">검색</button>
						</span> 
                      </div>
                        
				</div>
				<!-- 
				<div style="width:100%">
				
				<select id="lineup" style="float: right" onchange="listCall()">
					<option value="all" selected="selected">전체</option>
					<c:forEach var="testList" items="${testList}" varStatus="i">
         				<option value="${testList.name}">${testList.name}</option>
      				</c:forEach>
				</select>
				
				</div>
				 -->
				<div class="table-responsive">
					<table id="docform" class="table table-striped jambo_table bulk_action">
						<thead>
						<tr class="headings">
							<th class="column-title">글번호</th>
							<th class="column-title">제목</th>
							<th class="column-title">작성자</th>
							<th class="column-title">작성일</th>
							<th class="column-title">결재상태</th>
						</tr>
						</thead>

						<tbody id="signlist">
						</tbody>
					</table>
				</div>
			</div>
			</div>
			<!-- /page content -->
		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>

var msg="${msg}";
if(msg != ""){
	alert(msg);
}

listCall();

function listCall(){
	$.ajax({
		type:'get',
		url:'sign/list.do',
		data:{},
		dataType:'JSON',
		success:function(data){
			drawList(data.signdto);
/*			
			if(data.list.length==0){
				$("#pagination").twbsPagination("destroy");
			} else {
				$('#pagination').twbsPagination({
					startPage:1,
					totalPages:data.total, 
					visiblePages:5,
					onPageClick:function(e,page){ 
						listCall(page);
						console.log($("#pagination"));

					}
				});
			}
			*/
		},
		error:function(e){
			console.log(e);
		}
	});
}

function drawList(list){
	var content = '';
	if(list.length>0){
		for(var i=0; i<list.length; i++){
			content += '<tr>';
			content += '<td>'+list[i].sign_idx+'</td>';
//			content += '<td><a href="docFormDetail.go?doc_form_idx='+list[i].doc_form_idx+'">'+list[i].doc_form_name+'</a></td>';
			content += '<td><a href="signDetail.go?sign_idx='+list[i].sign_idx+'">'+list[i].sign_title+'</td>';
			content += '<td>'+list[i].emp_name+'</td>';
			content += '<td>'+list[i].sign_date+'</td>';
			content += '<td>'+list[i].sign_state_type+'</td>';
			content += '</tr>';
		}
	}else if(list.length == 0){
		content += '<tr>';
		content += '<td colspan="5" style="text-align:center">해당하는 데이터가 없습니다.</td>';
		content += '</tr>';
	}
	$('#signlist').empty();
	$('#signlist').append(content);
}


function dateSearch(){
	var date1 = document.getElementById('single_cal1').value;
	var date2 = document.getElementById('single_cal2').value;
	console.log(date1);
	console.log(date2);
//	console.log(typeof date1);

	$.ajax({
		type:'get',
		url:'sign/dateSearch.do',
		data:{
			'date1':date1,
			'date2':date2
			},
		dataType:'JSON',
		success:function(data){
//			console.log(data);
			drawList(data.searchlist);
		},
		error:function(e){
			console.log(e);
		}
		
	});
}

</script>
</html>