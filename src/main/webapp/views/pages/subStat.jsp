<!-- 
작성자 : 전형근 (HYEONGGEUN JEON)
제목 : 통계 파트 중 구독권 통계를 다루기 위한 JSP
개발 기간 : 2023-01-13 ~ 2023-01-20
내용 : 구독권 관련 3종류의 차트(크게 성별, 나이, 선호도로 구분)가 있으며 각 종류 마다 다른 옵션들을 줄 수 있다.

        선택한 옵션에 해당하는 통계 데이터로 그래프를 그려준다.
        크게 단일 막대그래프, 다중 막대그래프, 파이 차트 로 그래프는 구성한다(옵션에 따라 바뀜). 
        
        옵션에는 기본적으로 시도/시군구/매장을 선택하는 기능이 있다. 
        
        시도를 선택하면 해당 시도의 하위 시군구를 선택할 수 있고 시군구를 선택하면 해당 시군구의 하위 매장들을 선택할 수 있다.
        
        년, 월을 선택하지 않으면 2022년과 2023년. 년도 단위 통계 그래프를 표시한다. 
        년을 선택하고 월을 선택하지 않으면 해당 년도의 1~12월의 통계 그래프를 보여준다. 12개의 막대
        년을 선택하고 월까지 선택하면 해당 년도 해당 월의 일일 통계 그래프를 보여준다. 약 28~31개 막대
        
        파이 차트로 구독권 별 점유율을 보여준다.
        시도/시군구/매장 필터링, 년,월 선택. 
        
        10,20,30,40,50,60 대를 대상으로 나이 통계 그래프를 형성한다.
        ex) 구독권 전체 기준 구독권 등록 수를 나이대별로 보여줌
              횟수권/요일권 별로 구독권 등록 수를 나이대별로 보여줌.
              구독권 별 구독권 등록 수를 나이대별로 보여줌.
 -->
 
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
<style type="text/css">
	.statoption {
		float:left;
		text-align: center;
		padding: 10px 5px
	}
	.provinces {
	display: block;
    float: left;
    width: 33.3%;
    height: 40px;
    border: 1px solid #e5e5e5;
    letter-spacing: -1px;
    font-family: NanumGothic, NanumGothicWebFont, "Apple SD Gothic Neo", 돋움, Dotum, sans-serif;
    color: rgb(34, 34, 34);
    font-size: 12px;
    line-height: 19px;
    padding: 10px 0 4px 4px;
    cursor: pointer;
    }
    #provinceList {
    	padding: 0px;
    }
	.cities {
	display: block;
    float: left;
    width: 33.3%;
    height: 40px;
    border: 1px solid #e5e5e5;
    letter-spacing: -1px;
    font-family: NanumGothic, NanumGothicWebFont, "Apple SD Gothic Neo", 돋움, Dotum, sans-serif;
    color: rgb(34, 34, 34);
    font-size: 12px;
    line-height: 19px;
    padding: 10px 0 4px 4px;
    cursor: pointer;
    }
    #cityList {
    	padding: 0px;
    }
	.shops {
	display: block;
    width: 50%;
    float:left;
    height: 40px;
    border: 1px solid #e5e5e5;
    letter-spacing: -1px;
    font-family: NanumGothic, NanumGothicWebFont, "Apple SD Gothic Neo", 돋움, Dotum, sans-serif;
    color: rgb(34, 34, 34);
    font-size: 12px;
    text-align: center;
    line-height: 19px;
    padding: 10px 0 4px 4px;
    cursor: pointer;
    }
    #shopList {
    	padding: 0px;
    }
	#makeChart {
   		font-size: 8pt;
   		margin-left: 20px;
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

			

                  <div class="modal fade provinceModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                      <div class="modal-content">

                        <div class="modal-header">
                          <h4 class="modal-title" id="myModalLabel2">시도</h4>
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
                          </button>
                        </div>
                        <div class="modal-body">
							<ul id="provinceList"></ul>
                        </div>
<!--                         <div class="modal-footer"> -->
<!--                           <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!--                           <button type="button" class="btn btn-primary">Save changes</button> -->
<!--                         </div> -->

                      </div>
                    </div>
                  </div>
                  <div class="modal fade cityModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                      <div class="modal-content">

                        <div class="modal-header">
                          <h4 class="modal-title" id="myModalLabel2">시군구</h4>
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
                          </button>
                        </div>
                        <div class="modal-body">
                          <ul id="cityList"></ul>
                         
                        </div>
                        

                      </div>
                    </div>
                  </div>
                  
                  <div class="modal fade shopModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                      <div class="modal-content">

                        <div class="modal-header">
                          <h4 class="modal-title" id="myModalLabel2">매장</h4>
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
                          </button>
                        </div>
                        <div class="modal-body">
                          <ul id="shopList"></ul>
                         
                        </div>
                        

                      </div>
                    </div>
                  </div>
				
				
				<div class="col-md-9 col-sm-9 ">
					<div class="item form-group">
					<div class="statoption" id="province" data-toggle="modal" data-target=".provinceModal" style="min-height: 30px; cursor: pointer;" >
						<span id="provinceTitle">시도</span>
					</div>
					<div id="pro_idx"></div>
					<div class="statoption" id="city" data-toggle="modal" data-target=".cityModal" style="min-height: 30px; cursor: pointer;">
						<span id="cityTitle">시군구</span>
					</div>
					<div id="ci_idx"></div>
					<div class="statoption" id="shop" data-toggle="modal" data-target=".shopModal" style="min-height: 30px; cursor: pointer;">
						<span id="shopTitle">매장</span>
					</div>
					<div id="sh_idx"></div>
					<div class="statoption" id="year">
						<div style="float:right;">
				          <select name="year">
				            <option value="noYear">년도</option>
				            <option value="2022">2022</option>
				            <option value="2023">2023</option>
				          </select>
				        </div>
				     </div>
				     <div class="statoption" id="month">
						<div style="float:right;">
				          <select name="month">
				            <option value="noMonth">월</option>
				            <option value="1">1월</option>
				            <option value="2">2월</option>
				            <option value="3">3월</option>
				            <option value="4">4월</option>
				            <option value="5">5월</option>
				            <option value="6">6월</option>
				            <option value="7">7월</option>
				            <option value="8">8월</option>
				            <option value="9">9월</option>
				            <option value="10">10월</option>
				            <option value="11">11월</option>
				            <option value="12">12월</option>
				          </select>
				        </div>
					</div>
					<div class="statoption" style="min-height: 30px;">
						<div class="radio" style="float:left;">
							<label>
								<input type="radio" checked="" value="genderAll" id="genderRadio1" name="optionsRadios"> 전체
								&nbsp; 
							</label>
						</div>
						<div class="radio" style="float:left;">
							<label>
								<input type="radio" value="man" id="genderRadio2" name="optionsRadios"> 남자
								&nbsp;
							</label>
						</div>
						<div class="radio" style="float:left;">
							<label>
								<input type="radio" value="woman" id="genderRadio3" name="optionsRadios"> 여자
								&nbsp; 
							</label>
						</div>
					</div>
					<div class="statoption" style="min-height: 30px;">
						<div class="radio" style="float:left;">
							<label>
								<input type="radio" checked="" value="subAll" id="subRadio1" name="iCheck"> 전체
								&nbsp; 
							</label>
						</div>
						<div class="radio" style="float:left;">
							<label>
								<input type="radio" value="0" id="subRadio2" name="iCheck"> 횟수권
								&nbsp;
							</label>
						</div>
						<div class="radio" style="float:left;">
							<label>
								<input type="radio" value="1" id="subRadio3" name="iCheck"> 요일권
								&nbsp; 
							</label>
						</div>
						<div class="select_box" style="float:left;">
					    	<select id="subList" name="subSelect">
					            
					    	</select>
						</div>
						<button type="button"  onclick="makeChart();" class="btn btn-round btn-secondary" id="makeChart">차트 만들기</button>
					</div>
				</div>	

			
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Bar Chart Group <small>Sessions</small></h2>

                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content1">
                    <div id="graph_bar_group" style="width:100%; height:280px;"></div>
                  </div>
                </div>
              </div>

			<div class="col-md-9 col-sm-9  ">
			<div style="width: 100%; border: 1px solid black; min-height: 50px;"></div>
                <div class="x_panel">
                  <div class="x_title">
                    <h2>구독권 통계 <small>나이</small></h2>
                    
					
					
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                    <div id="graph_bar" style="width:100%; height:280px;"></div>
                  </div>
                </div>
              </div>
			
			</div>
			<!-- /page content -->

		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>

// 횟수권/요일권 선택 쪽 "전체"라는 라디오 버튼이 기본으로 체크되어 있다.
// change 함수를 걸어둬서 radio 버튼이 바뀔 때 마다 옆의 구독권 선택 select 박스의 값들이 달라진다.
// 특성상 change가 일어나야 요청을 보내기 때문에 처음 "전체"에 체크가 되어 있으면 select box에 아무것도
// 나타나지 않는다. 그래서 페이지가 로드되자마자 구독권 리스트를 띄우는 함수를 실행시킨다.
// 그러면 기본적으로 구독권 선택 select box는 빈 박스가 아니다.
// ajax data에 전체를 나타내는 값을 넣어 보내고 db에서 해당 리스트를 불러온다.
subListCall();
function subListCall() {
	$.ajax({
		type:'GET',
		url:'/sub/subList.do',
		data:{'subSort':'subAll'},
		success:function(data) {
			console.log(data);
			subListDraw(data);
		},
		error:function(e) {
			console.log(e);
		}
	});
}

// 차트 옵션 중 시도를 클릭하면 ajax 통신을 통해 province idx와 name 리스트를 응답받음
// 리스트 데이터를 provinceListDraw function에 매개변수로 넣는다.
// provinceListDraw function이 이 데이터를 가지고 forEach함수로 시도 리스트를
// 모달창에 뿌린다.
$('#province').click(function(){
	$.ajax({
		type:'GET',
		url:'/sub/provinceList.do',
		success:function(data) {
			console.log(data);
			provinceListDraw(data);
		},
		error:function(e) {
			console.log(e);
		}
	});
	
});

function provinceListDraw(obj) {
	var content = "";
	
	
	obj.list.forEach(function(item) {
		content += '<li class="provinces" onclick="chooseProvince('+item.province_idx+');" data-dismiss="modal" data-toggle="modal" data-target=".cityModal">'+item.province_name+'</li>';
		// 뿌려진 province List의 요소 각각에 onclick 이벤트를 걸고 매개변수로 해당 province_idx를 가져간다. 그리고 해당 li를 클릭하면
		// province 선택 모달창이 닫히게 li 태그에 설정을 해두었음.
	});
	$('#provinceList').empty();
	$('#provinceList').append(content);
	
	
}
$('#city').click(function(){
	console.log('시군구 클릭');
	
});
$('#shop').click(function(){
	console.log('매장 클릭');
	
});

function chooseProvince(idx) {
	console.log(idx);// 매개 변수로 받은 idx
	var dv = event.currentTarget;
	// 최근 이벤트 지정
	console.log(dv.innerText);

	
	$('#provinceTitle').text(dv.innerText);
	// 지정된 이벤트의 text를 가져와 provinceTitle의 text를 바꿔준다. 
	// 시도 -> 선택한 시도의 명으로 바뀐다.
	$('#pro_idx').empty();
	$('#ci_idx').empty();
	$('#pro_idx').append('<input type="hidden" name="province_idx" value="'+idx+'">');
	// 선택한 시도의 idx를 사용하기 위해 input type="hidden" 사용

	$.ajax({
		type:'GET',
		url:'/sub/cityList.do',
		data:{'idx':idx},
		// 해당 시도의 하위 시군구 리스트를 받아오기 위해 매개변수로 받은 idx 데이터로 설정해서 요청. 
		success:function(data) {
			console.log(data);
			cityListDraw(data);
			// provinceListDraw와 같은 맥락
		},
		error:function(e) {
			console.log(e);
		}
	});
	
}

function cityListDraw(obj) {
	var content1 = "";
	
	
	obj.list.forEach(function(item) {
		content1 += '<li class="cities" onclick="choosecity('+item.city_idx+');" data-dismiss="modal" data-toggle="modal" data-target=".shopModal">'+item.city_name+'</li>';
	});
	$('#cityList').empty();
	$('#cityList').append(content1);
	
	
}

$(document).on('click','.cities', function(){
	$('#cityTitle').text($(this).text());
	// event.currentTarget 이 위에서 먹고 다시 쓰려고 하니 안먹어서 
	// 다른 방식으로 진행하였음
	// 선택한 시군구의 text를 가져와서 시군구 -> 선택한 시군구 명으로 변경
});


function choosecity(cityIdx) {
	// chooseprovince와 같은 맥락 
	console.log(cityIdx);

	$('#ci_idx').empty();
	$('#sh_idx').empty();
	$('#ci_idx').append('<input type="hidden" name="city_idx" value="'+cityIdx+'">');

	// 선택한 시군구의 idx로 하위 매장들의 리스트를 가져오려고 한다.
	$.ajax({
		type:'GET',
		url:'/sub/shopList.do',
		data:{'idx':cityIdx},
		success:function(data) {
			console.log(data);
			shopListDraw(data);
			// 위에서 실행한 함수의 같은 기능
		},
		error:function(e) {
			console.log(e);
		}
	});
	
}

function shopListDraw(obj) {
	var content2 = "";
	
	obj.list.forEach(function(item) {
		content2 += '<li class="shops" onclick="chooseshop(\''+item.shop_idx+'\');" data-dismiss="modal">'+item.shop_name+'</li>';
		// 시도, 시군구와 달리 shop_idx(ex.SH001, SH002, etc.)는 문자이기 때문에 escape 문자를 사용해야 매개변수로 idx 값을 담아 넘길 수 있다.
		// 그렇지 않으면 shop_idx의 정체성을 컴퓨터는 정의하지 못하고 에러를 뱉는다.
	});
	$('#shopList').empty();
	$('#shopList').append(content2);
	
	
}

function chooseshop(shopIdx) {
	// 위의 함수들과 같은 맥락
	console.log(shopIdx);

	$('#sh_idx').empty();
	$('#sh_idx').append('<input type="hidden" name="shop_idx" value="'+shopIdx+'">');

}

// 위와 같이 선택한 매장의 text를 가져와서 매장 -> 선택한 매장명으로 바꿔준다.
$(document).on('click','.shops', function(){
	$('#shopTitle').text($(this).text());
});

// radio 버튼의 checked가 변경될 때 마다 변경된 radio 버튼의 value 값을 고려하여 요청을 보낸다.
// 요청 data의 값들이 3가지로 다 다르다. 그래서 응답받는 데이터도 다름.
// 각 응답받은 데이터는 구독권 선택 select box 의 option으로 들어간다.
$("input[name='iCheck']").change(function() {
	if($("input[name='iCheck']:checked").val() == "subAll") {
		console.log("구독권 전체");
		$.ajax({
			type:'GET',
			url:'/sub/subList.do',
			data:{'subSort':'subAll'},
			success:function(data) {
				console.log(data);
				subListDraw(data);
				// select box 안에 option을 넣기 위해 데이터를 매개변수로 담아 함수 호출
			},
			error:function(e) {
				console.log(e);
			}
		});
	}else if($("input[name='iCheck']:checked").val() == "0") {
		console.log("횟수권");
		$.ajax({
			type:'GET',
			url:'/sub/subList.do',
			data:{'subSort':0},
			success:function(data) {
				console.log(data);
				subListDraw(data);
			},
			error:function(e) {
				console.log(e);
			}
		});
	}else {
		console.log("요일권");
		$.ajax({
			type:'GET',
			url:'/sub/subList.do',
			data:{'subSort':1},
			success:function(data) {
				console.log(data);
				subListDraw(data);
			},
			error:function(e) {
				console.log(e);
			}
		});
	}
});

function subListDraw(obj) {
	// 매개변수로 받은 데이터를 통해 forEach로 option을 불러온 데이터 row 수 만큼 만들어 준다.
	var content3 = "";
	obj.list.forEach(function(item) {
		content3 += '<option value="\''+item.sub_idx+'\'">'+item.sub_name+'</option>';
	});
	
	$('#subList').empty();
	// append 하기 전에 해당 select box를 비운다. 다른 radio 버튼 재선택 했을 때 option들이 쌓이지 않기 위해
	$('#subList').append('<option value="noSub" selected="selected">선택하기</option>');
	// 비워진 select 박스 안에 첫 값이 아무것도 선택되지 않은 option을 default로 두기 위해 
	// 위에서 forEach로 값을 넣은 변수 append 전 '선택하기' option append
	$('#subList').append(content3);
	// 첫 option 태그 아래에 content3 append
	
}

// 버튼을 클릭하면 선택하거나 선택하지 않은 옵션의 값들을 가져와서 요청을 보낸다.
// 요청을 보내기 전에 시도/시군구/매장의 선택 유무에 따라 요청을 달리 보내고 
// 나머지 값들은 Controller 에서 분기한다.
function makeChart() {
	
	var provinceIdx = $('input[name="province_idx"]').val();
	console.log(provinceIdx);
	var cityIdx = $('input[name="city_idx"]').val();
	console.log(cityIdx);
	var shopIdx = $('input[name="shop_idx"]').val();
	console.log(shopIdx);
	var year = $("select[name='year']").val();
	console.log(year);
	var month = $("select[name='month']").val();
	console.log(month);
	var subIdx = $("select[name='subSelect']").val();
	console.log(subIdx);
	
	
	var gender = $("input[name='optionsRadios']:checked").val();
	console.log(gender);
	var subType = $("input[name='iCheck']:checked").val();
	console.log(subType);
	
}




</script>
</html>