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
				
				<div class="col-md-9 col-sm-9 ">
					<div class="item form-group">
					<div class="statoption" id="province" style="min-height: 30px; cursor: pointer;" >
						<span>시도</span>
					</div>
					<div class="statoption" id="city" style="min-height: 30px; cursor: pointer;">
						<span>시군구</span>
					</div>
					<div class="statoption" id="shop" style="min-height: 30px; cursor: pointer;">
						<span>매장</span>
					</div>
					<div class="statoption" id="year">
						<div style="float:right;">
				          <select name="year">
				            <option value="">선택안함</option>
				            <option value="2020">2020</option>
				            <option value="2021">2021</option>
				            <option value="2022">2022</option>
				          </select>
				        </div>
				     </div>
				     <div class="statoption" id="month">
						<div style="float:right;">
				          <select name="month">
				            <option value="">선택안함</option>
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
								<input type="radio" checked="" value="option1" id="optionsRadios1" name="optionsRadios"> 전체
								&nbsp; 
							</label>
						</div>
						<div class="radio" style="float:left;">
							<label>
								<input type="radio" value="option2" id="optionsRadios2" name="optionsRadios"> 남자
								&nbsp;
							</label>
						</div>
						<div class="radio" style="float:left;">
							<label>
								<input type="radio" value="option3" id="optionsRadios3" name="optionsRadios"> 여자
								&nbsp; 
							</label>
						</div>
					</div>
					<div class="statoption" style="min-height: 30px;">
						<div class="radio" style="float:left;">
							<label>
								<input type="radio" checked="" value="option1" id="optionsRadios1" name="optionsRadios"> 전체
								&nbsp; 
							</label>
						</div>
						<div class="radio" style="float:left;">
							<label>
								<input type="radio" value="option2" id="optionsRadios2" name="optionsRadios"> 횟수권
								&nbsp;
							</label>
						</div>
						<div class="radio" style="float:left;">
							<label>
								<input type="radio" value="option3" id="optionsRadios3" name="optionsRadios"> 요일권
								&nbsp; 
							</label>
						</div>
						<div class="select_box" style="float:left;">
					    	<select name="sub">
					            <option value="">선택안함</option>
					            <option value="1">다이어트 샐러드 10회권 </option>
					            <option value="2">다이어트 샐러드 30회권</option>
					            <option value="3">벌크업 샐러드 주3회권</option>
					            <option value="4">벌크업 샐러드 주5회권</option>
					            <option value="5">아메리카노 100회권</option>
					            <option value="6">커피 주5회권</option>
					    	</select>
						</div>
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
$('#province').click(function(){
	console.log('시도 클릭');
	
});
$('#city').click(function(){
	console.log('시군구 클릭');
	
});
$('#shop').click(function(){
	console.log('매장 클릭');
	
});


</script>
</html>