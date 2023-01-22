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
	#subRegister {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 8pt;
		display: flex;
		float: right;
	    white-space: nowrap;
	}
	
	.addWrap {
	   display: flex;
	   flex-direction: column;
	   justify-content: flex-start;
	   align-items: center;
	}
	
	#sublistdiv{
		display: flex;
		justify-content: center;
   		align-items: center;
   		flex-direction: column;
   		width: 80%;
	}
	
	
	#datatable {
		text-align: center;
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
			<div id="sublistdiv">
				<div class="title_left" style="width:100%">
					<h2 style="float:left">구독권 리스트</h2>
				</div>
				<br/>
				<div class="row" style="width:100%">

					<ul class="nav nav-tabs bar_tabs" id="myTab" role="tablist">
						<li class="nav-item"><a class="nav-link active" id="ongoing" data-toggle="tab" role="tab" aria-selected="true" onclick="ongoingList()">진행중</a></li>
						<li class="nav-item"><a class="nav-link" id="ready" data-toggle="tab" role="tab" aria-selected="false" onclick="readyList()">준비중</a></li>
						<li class="nav-item"><a class="nav-link" id="end" data-toggle="tab" role="tab" aria-selected="false" onclick="endList()">종료</a></li>
					</ul>
				<div style="width:100%"><button type="button" class="btn btn-round btn-secondary" id="subRegister" onclick="location.href='/subRegister'">등록</button></div>
					<!-- 					<button type="button" class="btn btn-secondary" onclick="orgChartAddEmpPopup()">직원 추가</button> -->
					<table id="datatable" class="table table-striped table-bordered dataTable no-footer" style="width: 100%" aria-describedby="datatable_info">
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
	      "dom": 'frtp',
	      bAutoWidth: false,
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

	            visible: false,

	        },
	        {
	        	targets: [1],
	            width: "18%"
	        },
	        {
	        	targets: [2] , 
	        	width: "10%",
	        	render: $.fn.dataTable.render.number( ',' , '.' , 0 , '' , '원' )
	        },
	        {
	        	targets: [4],
	            width: "12%"
	        },
	        {
	        	targets: [5],
	            width: "12%"
	        }
			]
	    });
	   
}   

function readyList() {
	state = "준비중";
	console.log(state);
	   var table = $('#datatable').DataTable({
	      destroy:true,
	      "dom": 'frtp',
	      bAutoWidth: false,
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

	            visible: false

	         },
	         {
		        	targets: [1],
		            width: "18%"
		     },
		     {
		        	targets: [2] , 
		        	width: "10%",
		        	render: $.fn.dataTable.render.number( ',' , '.' , 0 , '' , '원' )
		     },
		     {
		        	targets: [4],
		            width: "12%"
		     },
		     {
		        	targets: [5],
		            width: "12%"
		     }
	         
	        ]

	    });
	   
}

function endList() {
	state = "종료";
	console.log(state);
	   var table = $('#datatable').DataTable({
	      destroy:true,
	      "dom": 'frtp',
	      bAutoWidth: false,
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

	            visible: false

	         },
	         {
		        	targets: [1],
		            width: "18%"
		     },
		     {
		        	targets: [2] , 
		        	width: "10%",
		        	render: $.fn.dataTable.render.number( ',' , '.' , 0 , '' , '원' )
		     },
		     {
		        	targets: [4],
		            width: "12%"
		     },
		     {
		        	targets: [5],
		            width: "12%"
		     }
		     ]
	    });
	   
}



</script>
</html>