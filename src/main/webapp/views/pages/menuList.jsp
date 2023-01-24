<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
   function orgChartAddEmpPopup(){
      var url="addEmpPopup";
      var name="조직도 직원 추가";
      var option="width=400,height=500,left=50,top=50";   
      window.open(url,name,option);
   }
</script>
<jsp:include page="css.jsp" />
<style>

	.addWrap {
	   display: flex;
	   flex-direction: column;
	   justify-content: flex-start;
	   align-items: center;
	}

	#menulistdiv{
		display: flex;
		justify-content: center;
   		align-items: center;
   		flex-direction: column;
   		width: 80%;
	}
	
	#menuRegister {
		background-color:#2A3F54;
		border-color:#2A3F54;
		font-size: 8pt;
		display: flex;
		float: right;
	   /*  margin-bottom: -100%; */
	    margin-right: 2%;
	    white-space: nowrap;
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
			<div id="menulistdiv">
				<div class="title_left" style="width:100%">
					<h2 style="float:left">메뉴 리스트</h2>
				</div>
				<br/>
				<div class="row" style="width: 100%;">
				
				<ul class="nav nav-tabs bar_tabs" id="myTab" role="tablist">
					<li class="nav-item"><a class="nav-link active" id="selling" data-toggle="tab" role="tab" aria-selected="true" onclick="sellList()">판매중</a></li>
					<li class="nav-item"><a class="nav-link" id="ready" data-toggle="tab" role="tab" aria-selected="false" onclick="readyList()">준비중</a></li>
					<li class="nav-item"><a class="nav-link" id="end" data-toggle="tab" role="tab" aria-selected="false" onclick="endList()">판매종료</a></li>
				</ul>
				<div style="width:100%"><button type="button" class="btn btn-round btn-secondary" id="menuRegister" onclick="location.href='/menuRegister.go'">등록</button></div>
<!-- 					<button type="button" class="btn btn-secondary" onclick="orgChartAddEmpPopup()">직원 추가</button> -->
					<table id="datatable" class="table table-striped table-bordered dataTable no-footer" style="width: 100%" aria-describedby="datatable_info">
						<thead>
							<tr>
								<th>메뉴 번호</th>
								<th>사진</th>
								<th>메뉴 이름</th>
								<th>가격</th>
							</tr>
						</thead>
					</table>
				</div>
				
			</div>
			<!-- /page content -->
			</div>
		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>
sellList();
var state = "";
function sellList() {
	state = "판매중";
	console.log(state);
	   var table = $('#datatable').DataTable({
	      destroy:true,
	      "dom": 'frtp',
	      bAutoWidth: false,
	      serverSide: false,
	      ajax : {
	            "url":"/menuList.do",
	            "type":"GET",
	            "data": {
	               "menu_state" : state
	            }
	        },
	        columns : [
	        	{data : "menu_idx"},
	           	{data : "menu_photo",
	        		"render": function(data, type, row) {
	        			if(type='display') {
	        				data = '<a href="menuDetail?menu_idx='+row.menu_idx+'"><img width="75" src="/photo/'+data+'"/></a>';
	        			}
	        			return data;
	        		}
	           },
	           
	            {data: "menu_name",
	        	   "render": function(data, type, row) {
	        		   if(type='display') {
	        				data = '<a href="menuDetail?menu_idx='+row.menu_idx+'">'+data+'</a>';
	        			}
	        			return data;
	        	   }
	            },
	            {data: "menu_price"},	         
	        ],
	        columnDefs: [{

	            targets: [0],

	            searchable: false,

	            visible: true

	         },
	         {
	        	 targets: [3] ,
	        	 
	        	 render: $.fn.dataTable.render.number( ',' , '.' , 0 , '' , '원' )
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
	            "url":"/menuList.do",
	            "type":"GET",
	            "data": {
	               "menu_state" : state
	            }
	        },
	        columns : [
	        	{data : "menu_idx"},
	           	{data : "menu_photo",
	        		"render": function(data, type, row) {
	        			if(type='display') {
	        				data = '<a href="menuDetail?menu_idx='+row.menu_idx+'"><img width="75" src="/photo/'+data+'"/></a>';
	        			}
	        			return data;
	        		}
	           },
	           
	           {data: "menu_name",
	        	   "render": function(data, type, row) {
	        		   if(type='display') {
	        				data = '<a href="menuDetail?menu_idx='+row.menu_idx+'">'+data+'</a>';
	        			}
	        			return data;
	        	   }
	            },
	            {data: "menu_price"},	         
	        ],
	        columnDefs: [{

	            targets: [0],

	            searchable: false,

	            visible: true

	         },
	         {
	        	 targets: [3] ,
	        	 
	        	 render: $.fn.dataTable.render.number( ',' , '.' , 0 , '' , '원' )
	         }]

	    });
	   
	}   
	
function endList() {
	state = "비활성화";
	console.log(state);
	   var table = $('#datatable').DataTable({
	      destroy:true,
	      "dom": 'frtp',
	      bAutoWidth: false,
	      serverSide: false,
	      ajax : {
	            "url":"/menuList.do",
	            "type":"GET",
	            "data": {
	               "menu_state" : state
	            }
	        },
	        columns : [
	        	{data : "menu_idx"},
	           	{data : "menu_photo",
	        		"render": function(data, type, row) {
	        			if(type='display') {
	        				data = '<a href="menuDetail?menu_idx='+row.menu_idx+'"><img width="75" src="/photo/'+data+'"/></a>';
	        			}
	        			return data;
	        		}
	           },
	           
	           {data: "menu_name",
	        	   "render": function(data, type, row) {
	        		   if(type='display') {
	        				data = '<a href="menuDetail?menu_idx='+row.menu_idx+'">'+data+'</a>';
	        			}
	        			return data;
	        	   }
	            },
	            {data: "menu_price"},	         
	        ],
	        columnDefs: [{

	            targets: [0],

	            searchable: false,

	            visible: true

	         },
	         {
	        	 targets: [3] ,
	        	 
	        	 render: $.fn.dataTable.render.number( ',' , '.' , 0 , '' , '원' )
	         }]

	    });
	   
	}   



// <tbody>
// <c:forEach items="${list}" var="menuList">
// 	<tr>
// 		<td><img width="100" src="/photo/${menuList.menu_photo}"/></td>
// 		<td><a href="detail.do?idx=${menuList.menu_idx}">${menuList.menu_name}</a></td>
// 		<td>${menuList.price}</td>
// 		<!-- <td><input type="button" onclick="location.href='delete.do?idx='+${bbs.idx}" value="삭제"/></td> -->
// 	</tr>
// </c:forEach>
// </tbody>



</script>
</html>