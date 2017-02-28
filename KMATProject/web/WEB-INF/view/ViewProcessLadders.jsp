<%-- 
    Document   : ViewProcessLadders
    Created on : Nov 16, 2016, 9:35:43 AM
    Author     : Habiba Saim
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.LadderBean"%>

<!-- Code to prevent user from accessing any user specific page after logout/session-end -->
<%
    response.setHeader("Cache-Control","no-cache");  //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control","no-store");  //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires",-1);            //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache");         //HTTP 1.0 backward compatibility
    
    if(session.getAttribute("CurrentSessionUser")==null){
    
        response.sendRedirect("Home.jsp");
    }
%>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">	
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Roles in Knowledge Management for All Tool - KMAT" />
		<meta name="keywords" content="KMAT, users, roles, knowledge engineers, concept map, list, tool, composition, composition ladder" />
		<meta name="author" content="KMAT Team" />
        <title>Process Ladders</title>
		<!-- css includes here -->
        <jsp:include page="../../includes/link.jsp" />
        <jsp:include page="../../includes/view-ladder-link.jsp" />
		
    </head>

    <body class = "view-p-ladders">
        <!-- START wrapper -->
        <div id = "wrapper">
            <!-- START page-->
            <div id = "page">
                <!-- START header -->
                <jsp:include page="../../includes/user-header.jsp" />                 
                <!-- END header -->
                
                <!-- START: Page heading-->
                <aside class="heading-bg" > <!--style="background: url(images/heading-bg1.jpg) repeat;">-->
                    <div class="container">
                        <!--<h1 class="page-heading-lead">-->
                       Process Ladders
                            <!--<span class="border"></span>-->
                        <!--</h1>-->
                    </div>                        
                </aside>
                <!-- END: Page heading-->
			   
                <div class="container">
                    <div class="row">
						<!--<a href=""><h3>Something</h3></a>-->
                        <div id="inner-body" class="col-md-10 col-md-push-2">
							
							<div id="list-header-row-id">
								<hr style="height:1px;border:none;color:#333;background-color:#a9a9a9;" />
									<div class = "row">
										<div class="col-md-6 center-block text-center talign-center">
											<b>Ladder Name</b>                                    
										</div>
										<div class = "col-md-3 center-block text-center talign-center">
											Edit
										</div>
										<div class = "col-md-3 center-block text-center talign-center">
											Delete
										</div>
									</div>
								<hr style="height:1px;border:none;color:#333;background-color:#a9a9a9;" />
							</div>
							
							<!-- EXTRA
							<form name = "runtool" method="post" action="RunToolController" onSubmit="">
								<br>
								<div class="form-group">
									<input type="text" name="input" placeholder="Input Parameter" required>
								</div>
								<div class="form-group">
									<input type="submit" value="Run Process" class="btn btn-primary">
								</div>
								<div>
									<%--<p>Solution: <//%session.getAttribute("output");%></p>--%>
								</div>
							</form>
							<%--<a href="file:///Users/maryamkhalid/Desktop/gridWorld.pdf">Link 1</a>-
							<a href="file:///Users\maryamkhalid\Desktop\gridWorld.pdf">Link 1</a>--%>
							<p onclick="download()">Click Here</p>
							<a href="ViewAProcessLadder.jsp">View Process Ladder 1</a> -->
                        </div>
						
                        <div class="col-md-2 col-md-pull-10 back-color" >
                            <!-- Left side bar includes here -->
                            <jsp:include page="../../includes/left-sidebar.jsp" />
                        </div>                        
                    </div>
                </div>
                <!-- EXTRA
				<script>
					function download()
					{
						alert("In download!!");
						window.open("/Users/maryamkhalid/Desktop/gridWorld");
						//window.location.assign("Users/maryamkhalid/Desktop/gridWorld.pdf");
					}
				</script>-->
                <!-- adds js --> 
				<jsp:include page="../../includes/process-ladders-view-js.jsp" /> 
                <jsp:include page="../../includes/js.jsp" /> 
                
                <!-- footer.jspf integrates here -->


