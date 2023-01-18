notiList();
msgList();
function notiList() {
	$.ajax({
		type : 'get',
		url : '/noti/notiList.ajax',
		dataType : 'json',
		data : {
			
		},
		success : function(data) {
			console.log(data);
			drawList(data)
		}
	});
	
	
}
function msgList() {
	console.log("메시지 리스트");
	$.ajax({
		type : 'get',
		url : '/msg/msgList.ajax',
		dataType : 'json',
		data : {
			
		},
		success : function(data) {
			console.log(data);
			drawMsgList(data)
		}
	});
	
	
}

function drawList(list) {
	var test = "실패";
	var content = '';
	console.log("수정함4");
	console.log("test: "+list.notiList[0].noti_count);
	$("#notiCount").text(list.notiList[0].noti_count); 

		
	let i =0;
	while(i<3){
		if(i >= list.notiList.length){
			break;
		} else {
			if(list.notiList[i].noti_type == '결재'){
				test = "결재 알림입니다.";
			}else if(list.notiList[i].noti_type == '참조'){
				test = "참조 알림입니다.";
			}else if(list.notiList[i].noti_type == '결재완료'){
				test = "결재가 완료되었습니다.";			
			}else{
				test = "알림";
			}
			
			content += '<li class="nav-item"><a onclick="notiMove(this.id,this.text)" class="dropdown-item" id="' + list.notiList[i].noti_pk + '">';
			content += '<span>';
			content += '<span class= "hiddenClass" >' + list.notiList[i].noti_pk + '</span>';
			content += '<span>' + list.notiList[i].emp_name + '</span>';
			content += '<span class="time">'+list.notiList[i].noti_hour+'</span>';
			content += '</span>';
			content += '<span class="message">'+test+'</span>';
			content += '</a></li>';	
		}
		i++;
	}
	$('#notiList').empty();
	$('#notiList').append(content);
	var text = '<li class="nav-item"><div class="text-center"><a href = "/notiList.go" class="dropdown-item"> <strong>알림 더보기</strong> <i class="fa fa-angle-right"></i></a></div></li>'
	$('#notiList').append(text);
}

function drawMsgList(list) {
	var test = "실패";
	var content = '';
	console.log("수정함5");
	console.log("test입니다: "+list.msgList[0].msg_count);
	$("#messageCount").text(list.msgList[0].msg_count); 

		
	let i =0;
	while(i<3){
		if(i >= list.msgList.length){
			break;
		} else {
		
			content += '<li class="nav-item"><a onclick="notiMove(this.id,this.text)" class="dropdown-item" id="' + list.msgList[i].msg_idx + '">';
			content += '<span>';
			content += '<span class= "hiddenClass" >' + list.msgList[i].msg_idx + '</span>';
			content += '<span>' + list.msgList[i].emp_name + '</span>';
			content += '<span class="time">'+list.msgList[i].msg_date+'</span>';
			content += '</span>';
			content += '<div class="message" >'+list.msgList[i].msg_content+'</div>';
			content += '</a></li>';	
		}
		i++;
	}
	$('#msgList').empty();
	$('#msgList').append(content);
	var text = '<li class="nav-item"><div class="text-center"><a href = "/msgList.go" class="dropdown-item"> <strong>메신저 더보기</strong> <i class="fa fa-angle-right"></i></a></div></li>'
	$('#msgList').append(text);
}

function notiMove(idx,text) {
	console.log(idx);

	// var idx = document.getElementsByClassName("hiddenClass")[0].text;
	
	if(text.includes("참조 알림")){
		console.log("참조 디테일 이동");
		window.location.href="/signDetail.go?sign_idx="+idx;
	}else if(text.includes("결재 알림")){
		console.log("결재 디테일 이동");
		window.location.href="/signDetail.go?sign_idx="+idx;
	}else if(text.includes("결재가 완료")){
		console.log("결재완료 디테일 이동");
		var noti_type = '결재완료';
		window.location.href="/signDetailTest.do?sign_idx="+idx;
	}else{
		console.log("기타이동");
	}
	
}