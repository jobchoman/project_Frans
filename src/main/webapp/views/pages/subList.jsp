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
				<div class="title_left">
					<h2>메뉴 리스트</h2>
				</div>
				<div class="row">
				<button type="button" class="btn btn-round btn-secondary" id="ongoing" onclick="ongoingList();">진행중인 구독권</button>
				<button type="button" class="btn btn-round btn-secondary" id="ready" onclick="readyList();">준비중인 구독권</button>
				<button type="button" class="btn btn-round btn-secondary" id="end" onclick="endList();">종료된 구독권</button>
				
<!-- 					<button type="button" class="btn btn-secondary" onclick="orgChartAddEmpPopup()">직원 추가</button> -->
					<table id="datatable" class="table table-striped table-bordered dataTable no-footer"
						style="width: 100%" aria-describedby="datatable_info">
						<thead>
							<tr>
								<th></th>
								<th>구독권 이름</th>
								<th>가격</th>
								<th>메뉴</th>
								<th>구독권 종류</th>
								<th>기간(개월)</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<!-- /page content -->

		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>
ongoingList();
var state = "";
function ongoingList() {
	state = "진행중";
	console.log(state);
	   var table = $('#datatable').DataTable({
	      destroy:true,
	      serverSide: false,
	      ajax : {
	            "url":"/subList.do",
	            "type":"GET",
	            "data": {
	               "sub_state" : state
	            }
	        },
	        columns : [
	        	{data : "sub_idx",
	        		"render": function(data, type, row) {
		        		   if(type='display') {
		        				data = '<input type="hidden" value="'+data+'"/>';
		        			}
		        			return data;
		        	   }
	        	},
	        	{data: "sub_name",
		        	   "render": function(data, type, row) {
		        		   if(type='display') {
		        				data = '<a href="subDetail?sub_idx='+row.sub_idx+'">'+data+'</a>';
		        			}
		        			return data;
		        	   }
		            },
	            {data: "sub_price"},
	            {data: "sub_menu"},
	            {data: "sub_sort_type"},
	            {data: "sub_period"},
	        ],
	        columnDefs: [{

	            targets: [0],

	            searchable: false,

	            visible: true

	         }]

	    });
	   
}   

function readyList() {
	state = "준비중";
	console.log(state);
	   var table = $('#datatable').DataTable({
	      destroy:true,
	      serverSide: false,
	      ajax : {
	            "url":"/subList.do",
	            "type":"GET",
	            "data": {
	               "sub_state" : state
	            }
	        },
	        columns : [
	        	{data : "sub_idx",
	        		"render": function(data, type, row) {
		        		   if(type='display') {
		        				data = '<input type="hidden" value="'+data+'"/>';
		        			}
		        			return data;
		        	   }
	        	},
	        	{data: "sub_name",
		        	   "render": function(data, type, row) {
		        		   if(type='display') {
		        				data = '<a href="subDetail?sub_idx='+row.sub_idx+'">'+data+'</a>';
		        			}
		        			return data;
		        	   }
		            },
	            {data: "sub_price"},
	            {data: "sub_menu"},
	            {data: "sub_sort_type"},
	            {data: "sub_period"},
	        ],
	        columnDefs: [{

	            targets: [0],

	            searchable: false,

	            visible: true

	         }]

	    });
	   
}

function endList() {
	state = "종료";
	console.log(state);
	   var table = $('#datatable').DataTable({
	      destroy:true,
	      serverSide: false,
	      ajax : {
	            "url":"/subList.do",
	            "type":"GET",
	            "data": {
	               "sub_state" : state
	            }
	        },
	        columns : [
	        	{data : "sub_idx",
	        		"render": function(data, type, row) {
		        		   if(type='display') {
		        				data = '<input type="hidden" value="'+data+'"/>';
		        			}
		        			return data;
		        	   }
	        	},
	        	{data: "sub_name",
		        	   "render": function(data, type, row) {
		        		   if(type='display') {
		        				data = '<a href="subDetail?sub_idx='+row.sub_idx+'">'+data+'</a>';
		        			}
		        			return data;
		        	   }
		            },
	            {data: "sub_price"},
	            {data: "sub_menu"},
	            {data: "sub_sort_type"},
	            {data: "sub_period"},
	        ],
	        columnDefs: [{

	            targets: [0],

	            searchable: false,

	            visible: true

	         }]

	    });
	   
}



</script>
</html>