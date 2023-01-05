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
	          <div class="">
	            <div class="page-title">
	              <div class="title_left">
	                <h3>Chart Js <small>Some examples to get you started</small></h3>
	              </div>
	
	              <div class="title_right">
	                <div class="col-md-5 col-sm-5   form-group pull-right top_search">
	                  <div class="input-group">
	                    <input type="text" class="form-control" placeholder="Search for...">
	                    <span class="input-group-btn">
	                      <button class="btn btn-default" type="button">Go!</button>
	                    </span>
	                  </div>
	                </div>
	              </div>
	            </div>
	
	            <div class="clearfix"></div>
	
	            <div class="row">
	              <div class="col-md-4 col-sm-6  ">
	                <div class="x_panel">
	                  <div class="x_title">
	                    <h2>Line Graph <small>Sessions</small></h2>
	                    <ul class="nav navbar-right panel_toolbox">
	                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
	                      </li>
	                      <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
	                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
	                            <a class="dropdown-item" href="#">Settings 1</a>
	                            <a class="dropdown-item" href="#">Settings 2</a>
	                          </div>
	                      </li>
	                      <li><a class="close-link"><i class="fa fa-close"></i></a>
	                      </li>
	                    </ul>
	                    <div class="clearfix"></div>
	                  </div>
	                  <div class="x_content">
	                    <canvas id="lineChart"></canvas>
	                  </div>
	                </div>
	              </div>
	
	              <div class="col-md-4 col-sm-6  ">
	                <div class="x_panel">
	                  <div class="x_title">
	                    <h2>Bar Graph <small>Sessions</small></h2>
	                    <ul class="nav navbar-right panel_toolbox">
	                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
	                      </li>
	                      <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
	                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
	                            <a class="dropdown-item" href="#">Settings 1</a>
	                            <a class="dropdown-item" href="#">Settings 2</a>
	                          </div>
	                      </li>
	                      <li><a class="close-link"><i class="fa fa-close"></i></a>
	                      </li>
	                    </ul>
	                    <div class="clearfix"></div>
	                  </div>
	                  <div class="x_content">
	                    <canvas id="mybarChart"></canvas>
	                  </div>
	                </div>
	              </div>
	
	              <div class="col-md-4 col-sm-6  ">
	                <div class="x_panel">
	                  <div class="x_title">
	                    <h2>Donut Chart Graph <small>Sessions</small></h2>
	                    <ul class="nav navbar-right panel_toolbox">
	                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
	                      </li>
	                      <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
	                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
	                            <a class="dropdown-item" href="#">Settings 1</a>
	                            <a class="dropdown-item" href="#">Settings 2</a>
	                          </div>
	                      </li>
	                      <li><a class="close-link"><i class="fa fa-close"></i></a>
	                      </li>
	                    </ul>
	                    <div class="clearfix"></div>
	                  </div>
	                  <div class="x_content">
	                    <canvas id="canvasDoughnut"></canvas>
	                  </div>
	                </div>
	              </div>
	
	              <div class="col-md-4 col-sm-6  ">
	                <div class="x_panel">
	                  <div class="x_title">
	                    <h2>Radar Chart <small>Sessions</small></h2>
	                    <ul class="nav navbar-right panel_toolbox">
	                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
	                      </li>
	                      <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
	                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
	                            <a class="dropdown-item" href="#">Settings 1</a>
	                            <a class="dropdown-item" href="#">Settings 2</a>
	                          </div>
	                      </li>
	                      <li><a class="close-link"><i class="fa fa-close"></i></a>
	                      </li>
	                    </ul>
	                    <div class="clearfix"></div>
	                  </div>
	                  <div class="x_content">
	                    <canvas id="canvasRadar"></canvas>
	                  </div>
	                </div>
	              </div>
	
	              <div class="col-md-4 col-sm-6  ">
	                <div class="x_panel">
	                  <div class="x_title">
	                    <h2>Pie Area Chart <small>Sessions</small></h2>
	                    <ul class="nav navbar-right panel_toolbox">
	                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
	                      </li>
	                      <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
	                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
	                            <a class="dropdown-item" href="#">Settings 1</a>
	                            <a class="dropdown-item" href="#">Settings 2</a>
	                          </div>
	                      </li>
	                      <li><a class="close-link"><i class="fa fa-close"></i></a>
	                      </li>
	                    </ul>
	                    <div class="clearfix"></div>
	                  </div>
	                  <div class="x_content">
	                    <canvas id="polarArea"></canvas>
	                  </div>
	                </div>
	              </div>
	
	              <div class="col-md-4 col-sm-6  ">
	                <div class="x_panel">
	                  <div class="x_title">
	                    <h2>Pie Chart Graph <small>Sessions</small></h2>
	                    <ul class="nav navbar-right panel_toolbox">
	                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
	                      </li>
	                      <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
	                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
	                            <a class="dropdown-item" href="#">Settings 1</a>
	                            <a class="dropdown-item" href="#">Settings 2</a>
	                          </div>
	                      </li>
	                      <li><a class="close-link"><i class="fa fa-close"></i></a>
	                      </li>
	                    </ul>
	                    <div class="clearfix"></div>
	                  </div>
	                  <div class="x_content">
	                    <canvas id="pieChart"></canvas>
	                  </div>
	                </div>
	              </div>
	            </div>
	            <div class="clearfix"></div>
	            <br />
	          </div>
	        </div>
	        <!-- /page content -->

			<!-- footer content -->
			<footer><jsp:include page="footer.jsp" /></footer>
			<!-- /footer content -->
		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>
</script>
</html>