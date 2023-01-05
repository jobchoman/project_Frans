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
	                <h3>Calendar <small>Click to add/edit events</small></h3>
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
	              <div class="col-md-12">
	                <div class="x_panel">
	                  <div class="x_title">
	                    <h2>Calendar Events <small>Sessions</small></h2>
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
	
	                    <div id='calendar'></div>
	
	                  </div>
	                </div>
	              </div>
	            </div>
	          </div>
	        </div>
			<!-- /page content -->

			<!-- footer content -->
			<footer><jsp:include page="footer.jsp" /></footer>
			<!-- /footer content -->
			
		    <!-- calendar modal -->
		    <div id="CalenderModalNew" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		      <div class="modal-dialog">
		        <div class="modal-content">
		
		          <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		            <h4 class="modal-title" id="myModalLabel">New Calendar Entry</h4>
		          </div>
		          <div class="modal-body">
		            <div id="testmodal" style="padding: 5px 20px;">
		              <form id="antoform" class="form-horizontal calender" role="form">
		                <div class="form-group">
		                  <label class="col-sm-3 control-label">Title</label>
		                  <div class="col-sm-9">
		                    <input type="text" class="form-control" id="title" name="title">
		                  </div>
		                </div>
		                <div class="form-group">
		                  <label class="col-sm-3 control-label">Description</label>
		                  <div class="col-sm-9">
		                    <textarea class="form-control" style="height:55px;" id="descr" name="descr"></textarea>
		                  </div>
		                </div>
		              </form>
		            </div>
		          </div>
		          <div class="modal-footer">
		            <button type="button" class="btn btn-default antoclose" data-dismiss="modal">Close</button>
		            <button type="button" class="btn btn-primary antosubmit">Save changes</button>
		          </div>
		        </div>
		      </div>
		    </div>
		    <div id="CalenderModalEdit" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		      <div class="modal-dialog">
		        <div class="modal-content">
		
		          <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		            <h4 class="modal-title" id="myModalLabel2">Edit Calendar Entry</h4>
		          </div>
		          <div class="modal-body">
		
		            <div id="testmodal2" style="padding: 5px 20px;">
		              <form id="antoform2" class="form-horizontal calender" role="form">
		                <div class="form-group">
		                  <label class="col-sm-3 control-label">Title</label>
		                  <div class="col-sm-9">
		                    <input type="text" class="form-control" id="title2" name="title2">
		                  </div>
		                </div>
		                <div class="form-group">
		                  <label class="col-sm-3 control-label">Description</label>
		                  <div class="col-sm-9">
		                    <textarea class="form-control" style="height:55px;" id="descr2" name="descr"></textarea>
		                  </div>
		                </div>
		
		              </form>
		            </div>
		          </div>
		          <div class="modal-footer">
		            <button type="button" class="btn btn-default antoclose2" data-dismiss="modal">Close</button>
		            <button type="button" class="btn btn-primary antosubmit2">Save changes</button>
		          </div>
		        </div>
		      </div>
		    </div>
		
		    <div id="fc_create" data-toggle="modal" data-target="#CalenderModalNew"></div>
		    <div id="fc_edit" data-toggle="modal" data-target="#CalenderModalEdit"></div>
		    <!-- /calendar modal -->
    
		</div>
	</div>
	<jsp:include page="script.jsp" />
</body>
<script>
</script>
</html>