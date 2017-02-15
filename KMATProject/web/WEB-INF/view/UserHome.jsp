<%-- 
    Document   : UserHome
    Created on : Oct 18, 2016, 11:58:49 AM
    Author     : Habiba Saim
--%>

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
	<title>KMAT User-Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Knowledge Management for All Tool (KMAT)" />
	<meta name="keywords" content="KMAT, resource, concept, concept map, list, tool" />
	<meta name="author" content="KMAT Team" />
        
        <jsp:include page="../../includes/link.jsp" /> 
    </head>
    <body class = "home">
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
                        Welcome to KMAT
                            <!--<span class="border"></span>-->
                        <!--</h1>-->
                    </div>                        
                </aside>
                <!-- END: Page heading-->
                
                <div class="container">
                    <div class="row">
                        <div class="col-md-10 col-md-push-2">
                            
                            <h2>Knowledge Management for All Tool (K-MAT) basic features </h2>
                            
                            <p>
                               <br/>
                                * Provide industry with a low cost KM software solution for SMEs
                                <br/>
                                * Readily integrate to most common existing DIMS, and legacy systems
                                <br/>
                                * Adapt to any SME sector with complete KM control
                                <br/>
                                * Will be easily manageable by SMEs without incurring specialized human resource cost.
                                
                                <br/>
                                * Provide industry with a low cost KM software solution for SMEs
                                <br/>
                                * Readily integrate to most common existing DIMS, and legacy systems
                                <br/>
                                * Adapt to any SME sector with complete KM control
                                <br/>
                                * Will be easily manageable by SMEs without incurring specialized human resource cost.<br/>
                                * Provide industry with a low cost KM software solution for SMEs
                                <br/>
                                * Readily integrate to most common existing DIMS, and legacy systems
                                <br/>
                                * Adapt to any SME sector with complete KM control
                                <br/>
                                * Will be easily manageable by SMEs without incurring specialized human resource cost.
                                
                                <br/>
                                * Provide industry with a low cost KM software solution for SMEs
                                <br/>
                                * Readily integrate to most common existing DIMS, and legacy systems
                                <br/>
                                * Adapt to any SME sector with complete KM control
                                <br/>
                                * Will be easily manageable by SMEs without incurring specialized human resource cost.
                            </p>
                            
                        </div>
                        <div class="col-md-2 col-md-pull-10 back-color" >
                            <%-- <jsp:include page="includes/verticalUserMenu.jsp" />  --%>
                            <!-- Left side bar includes here -->
                            <jsp:include page="../../includes/left-sidebar.jsp" />
                        </div>               
                        
                    </div>
                </div>
                          
                <!-- adds js -->                
                <jsp:include page="../../includes/js.jsp" /> 
                
                <!-- footer.jspf integrates here -->