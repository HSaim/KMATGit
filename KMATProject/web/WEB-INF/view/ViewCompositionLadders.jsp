<%-- 
    Document   : ViewCompositionLadders
    Created on : Nov 3, 2016, 11:05:27 AM
    Author     : Habiba Saim
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.LadderBean"%>

<!-- Code to prevent user from accessing any user specific page after logout/session-end -->
<%
    response.setHeader("Pragma","no-cache"); 
    response.setHeader("Cache-Control","no-store");
    response.setDateHeader("Expires",-1);
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
		<meta name="description" content="Knowledge Management for All Tool (KMAT)" />
		<meta name="keywords" content="KMAT, resource, concept, concept map, list, tool, composition, composition ladder" />
		<meta name="author" content="KMAT Team" />
		<title>Composition Ladders</title>
		
		<!-- css includes here -->
        <jsp:include page="../../includes/link.jsp" />
        <jsp:include page="../../includes/view-ladder-link.jsp" />

	</head>
    
    <body class = "view-c-ladders">
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
                       Composition Ladders
                            <!--<span class="border"></span>-->
                        <!--</h1>-->
                    </div>                        
                </aside>
                <!-- END: Page heading-->
                
                <div class="container">
                    <div class="row">
                        <div id="inner-body" class="col-md-10 col-md-push-2">
							
							<hr style="height:1px;border:none;color:#333;background-color:#a9a9a9;" />
							<div class = "row">
                                <b>
                                <div class="col-md-6 center-block text-center talign-center">
                                    <b>Ladder Name</b>                                    
                                </div>
                                <div class = "col-md-3 center-block text-center talign-center">
                                    Edit
                                </div>
                                <div class = "col-md-3 center-block text-center talign-center">
                                    Delete
                                </div>
                                </b>
                            </div>
                            <hr style="height:1px;border:none;color:#333;background-color:#a9a9a9;" />
                        </div>
						
                        <div class="col-md-2 col-md-pull-10 back-color" >
                            <!-- Left side bar includes here -->
                            <jsp:include page="../../includes/left-sidebar.jsp" />
                        </div>                        
                    </div>
                </div>

                <!-- adds js --> 
				<jsp:include page="../../includes/composition-ladders-view-js.jsp" /> 
                <jsp:include page="../../includes/js.jsp" /> 
                
                <!-- footer.jspf integrates here -->
