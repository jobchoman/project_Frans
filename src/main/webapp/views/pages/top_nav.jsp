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
</style>
</head>

<body>
	<div class="nav_menu">
		<div class="nav toggle">
			<a id="menu_toggle"><i class="fa fa-bars"></i></a>
		</div>
		<nav class="nav navbar-nav">
			<ul class=" navbar-right">
			
				<li class="nav-item dropdown open" style="padding-left: 15px;">
					<a href="javascript:;" class="user-profile dropdown-toggle"
					aria-haspopup="true" id="navbarDropdown" data-toggle="dropdown"
					aria-expanded="false"> <img src="/production/images/eunwoo.png"
						alt="">차은우
				</a>
					<div class="dropdown-menu dropdown-usermenu pull-right"
						aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="javascript:;"> 내 정보 관리</a>  
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
						<span class="badge bg-green">3</span>
				</a>
				
					<ul class="dropdown-menu list-unstyled msg_list" role="menu"
						aria-labelledby="navbarDropdown1">
						<li id="notiList"></li>
						
						<li class="nav-item">
							<div class="text-center">
								<a class="dropdown-item"> <strong>알림 더보기</strong> <i
									class="fa fa-angle-right"></i>
								</a>
							</div>
						</li>
					</ul></li>
				
				
				<!-- 메신저창 -->
				<li role="presentation" class="nav-item dropdown open"><a
					href="javascript:;" class="dropdown-toggle info-number"
					id="navbarDropdown2" data-toggle="dropdown" aria-expanded="false">
						<i class="fa fa-envelope-o"></i> <!-- 메신저 알림 갯수 --> <span
						class="badge bg-green"></span>
				</a>
					<ul class="dropdown-menu list-unstyled msg_list" role="menu"
						aria-labelledby="navbarDropdown1">
						<li class="nav-item"><a class="dropdown-item"> <!-- 보낸사람 이미지 -->
								<span class="image"><img
									src="/production/images/eunwoo.png" alt="Profile Image" /></span> <span>
									<!-- 보낸사람 --> <span>김민정</span> <!-- 받은시간 --> <span class="time">2023-01-02</span>
							</span> <!-- 받은 내용 --> <span class="message"> 메신저 </span>
						</a></li>
						<li class="nav-item">
							<div class="text-center">
								<a class="dropdown-item"> <strong>메신저 더보기</strong> <i
									class="fa fa-angle-right"></i>
								</a>
							</div>
						</li>
					</ul></li>

			</ul>
		</nav>
	</div>
</body>

<script type="text/javascript">
notiList();
function notiList() {
	$.ajax({
		type : 'get',
		url : '/noti/notiList.ajax',
		dataType : 'json',
		data : {
			
		},
		success : function(data) {
			console.log(data);
			drawList(data.data)
		}
	});
	
	
}

function drawList(list) {
	var test = "실패";
	var content = '';
	console.log(list);
	for (i = 0; i < 2; i++) {
		if(list.noti_type == '결재'){
			test = "결재 알림입니다.";
		}else if(list.noti_type == '참조'){
			test = "참조 알림입니다.";
		}else{
			test = "성공";
		}
		console.log(list[i]);
		content += '<li class="nav-item"><a class="dropdown-item">';
		content += '<span>';
		content += '<span>' + list.emp_name + '</span>';
		content += '<span class="time">'+list.noti_hour+'</span>';
		content += '</span>';
		content += '<span class="message">'+test+'</span>';
		content += '</a></li>';
	}
	$('#notiList').empty();
	$('#notiList').append(content);

}

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

