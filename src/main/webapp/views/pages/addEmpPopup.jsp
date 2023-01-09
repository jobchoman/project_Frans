<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
</head>
<body>
	<div class="searchBox1">
		<label>이름 : <input type="text" id="employee" class="empName" placeholder="검색할 이름을 입력하시오.">
		<input type="button" onclick="search1()" value="검색하기"></label>
		<input type="hidden" class="empId" value="">
	</div>
	<div class="resultList1"></div>
	<div class="team">
		<label>팀 : <input type="text" class="teamName" value="" placeholder="팀명" readonly></label>
		<input type="hidden" class="teamIdx" value="">
	</div>
	<div class="position">
		<label>직급 : <input type="text" class="posName" value="" placeholder="직급" readonly></label>
		<input type="hidden" class="posIdx" value="">
	</div>
	<div class="searchBox2">
		<label>상사 : <input type="text" id="senior" class="seniorName" placeholder="검색할 이름을 입력하시오.">
		<input type="button" onclick="search2()" value="검색하기"></label>
		<input type="hidden" class="seniorEmpId" value="">
	</div>
	<div class="resultList2"></div>
	<div class="stack">
		<label>stack : <input type="text" placeholder="stack"></label>
	</div>	
	<br>
	<button>등록하기</button>
</body>
<script>
function search1() {

	var empname = $('#employee').val();
	console.log(empname);
	
	$.ajax({
		type:'POST',
		url:'orgChartSearch.do',
		data:{'emp_name':empname},
		success:function(data) {
			console.log(data.empList);
			drawList1(data.empList);
		}
	});
}

function search2() {
	
	var seniorName = $('#senior').val();
	console.log(seniorName);
	
	$.ajax({
		type:'POST',
		url:'orgChartSearch.do',
		data:{'emp_name':seniorName},
		success:function(data) {
			console.log(data.empList);
			drawList2(data.empList);
		}
	});
}


function drawList1(list) {
	var content = "";
	for(var i=0; i<list.length; i++) {

		o2sList = JSON.stringify(list[i]);
		content += "<span onclick='putVal1("+o2sList+");' style='cursor:pointer;'>"+'이름 : '+list[i].emp_name+'&nbsp;'+'&nbsp;'+'&nbsp;'+'팀 : '+list[i].team_name+'&nbsp;'+'&nbsp;'+'&nbsp;'+'직급 : '+list[i].pos_name+"</span>";
		content += "<br>";
	}
	$(".resultList1").empty();
	$(".resultList1").append(content);
}

function drawList2(list) {
	var content = "";
	for(var i=0; i<list.length; i++) {

		o2sList = JSON.stringify(list[i]);
		content += "<span onclick='putVal2("+o2sList+");' style='cursor:pointer;'>"+'이름 : '+list[i].emp_name+'&nbsp;'+'&nbsp;'+'&nbsp;'+'팀 : '+list[i].team_name+'&nbsp;'+'&nbsp;'+'&nbsp;'+'직급 : '+list[i].pos_name+"</span>";
		content += "<br>";
	}
	$(".resultList2").empty();
	$(".resultList2").append(content);
}

function putVal1(param) {
// 	$('.empName').attr('text', "");
	$('.empName').val(param.emp_name);
	$('.empId').attr('value', param.emp_id);
	$('.teamName').attr('value', param.team_name);
	$('.teamIdx').attr('value', param.team_idx);
	$('.posName').attr('value', param.pos_name);
	$('.posIdx').attr('value', param.pos_idx);

	$(".resultList1").empty();
}
function putVal2(param) {
	$('.seniorName').val(param.emp_name);
	$('.seniorEmpId').attr('value', param.emp_id);

	$(".resultList2").empty();
}


</script>
</html>