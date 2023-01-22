<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">

<title>Gentelella Alela! |</title>

<style type="text/css">
#notiIcon{
	padding-left: 10px;
}

.hiddenClass{
	display: none;
}

.message { 
display:block; 
width:200px; 
overflow:hidden; 
text-overflow:ellipsis; 
white-space:nowrap; 
}

.table-bordered td, .table-bordered th{
	border: none;
}

.table-bordered{
	border: none;
}

#Drawcontent{
	width: 650px;
}

#msgDetailModal > tr> th{
	width: 15%;
}

.nav_menu{
	margin-bottom: 0;
}
</style>

</head>

<body>

<jsp:include page="loginBox.jsp"/>
	<div class="nav_menu">
		<div class="nav toggle">
			<a id="menu_toggle"><i class="fa fa-bars"></i></a>
		</div>
		<nav class="nav navbar-nav">
			<ul class=" navbar-right">
			
				<li class="nav-item dropdown open" style="padding-left: 15px;">
					<a href="javascript:;" class="user-profile dropdown-toggle"
					aria-haspopup="true" id="navbarDropdown" data-toggle="dropdown"
					aria-expanded="false"> <c:if test="${fileList.size()>0}">
													<c:forEach items="${sessionScope.fileList}" var="path">
													<img  src="memberPhoto.do?path=${path.file_new}"/>
													</c:forEach>
												</c:if>${sessionScope.emp_name}
				</a>
					<div class="dropdown-menu dropdown-usermenu pull-right"
						aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="/memberMyPage.go?emp_id=${sessionScope.loginId}"> 내 정보 관리</a>  
						<a
							class="dropdown-item" href="/memberLogout.do"><i
							class="fa fa-sign-out pull-right"></i> Log Out</a>
					</div>
				</li>
				
				<!-- 알림창 -->
				<li id = "notiIcon" role="presentation" class="nav-item dropdown open"><a
					href="javascript:;" class="dropdown-toggle info-number"
					id="navbarDropdown1" data-toggle="dropdown" aria-expanded="false">
						<i class="fa fa-bell-o"></i> 
						<!-- 알림 갯수 --> 
						<span id="notiCount" class="badge bg-green"></span>
				</a>
				
					<ul id="notiList" class="dropdown-menu list-unstyled msg_list" role="menu"
						aria-labelledby="navbarDropdown1">
					
						
						<li class="nav-item">
							<div class="text-center">
								<a href="/notiList.go" class="dropdown-item"> <strong>알림 더보기</strong> <i
									class="fa fa-angle-right"></i>
								</a>
							</div>
						</li>
					</ul></li>
				
				
				<!-- 메신저창 -->
				<li role="presentation" class="nav-item dropdown open"><a
					href="javascript:;" class="dropdown-toggle info-number"
					id="navbarDropdown2" data-toggle="dropdown" aria-expanded="false">
						<i class="fa fa-envelope-o"></i> 
						<!-- 메신저 알림 갯수 --> 
						<span id= "messageCount" class="badge bg-green"></span>
				</a>
					<ul id="msgList" class="dropdown-menu list-unstyled msg_list" role="menu"
						aria-labelledby="navbarDropdown1">
						
						<li class="nav-item">
							<div class="text-center">
								<a href="/msgList.go" class="dropdown-item"> <strong>메신저 더보기</strong> <i
									class="fa fa-angle-right"></i>
								</a>
							</div>
						</li>
					</ul></li>

			</ul>
		</nav>
	</div>
	
	
	
	<!-- 디테일 모달 -->
				<div class="modal fade bs-example-modal-lg" id="detailMsg" tabindex="-1" role="dialog" aria-hidden="true">
                  <div class="modal-dialog modal-lg">
                       <div class="modal-content">

                         <div class="modal-header">
                              <h5 class="modal-title" id="myModalLabel2">쪽지 상세정보</h5>
                              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                         </div>
                         <div class="modal-body">
                           <table  class="table table-bordered" style="width:100%">
									<tbody id="msgDetailModal"></tbody>
								</table>
                        </div>
                         <div class="modal-footer">
                           <button id="delModalBtn" type="button" class="btn btn-primary" onclick="msgList()" data-dismiss="modal">확인</button>
                         </div>
                     </div>
                  </div>
               </div>
               <!-- 디테일 모달 끝 -->
</body>

<script type="text/javascript">

/* <li class="nav-item"><a class="dropdown-item"> <!-- 보낸사람 이미지 -->
 <span>
<!-- 보낸사람 --> <span>김채영</span> 
<!-- 받은시간 --> <span class="time">2023-01-02</span>						
</span>  
<!-- 받은 내용 --> 
<span class="message"> 알림 </span> 

</a></li> */



</script>
</html>

