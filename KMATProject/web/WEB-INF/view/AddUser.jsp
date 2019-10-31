<%-- 
    Document   : AddUser
    Created on : Nov 12, 2016, 7:18:13 PM
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
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Knowledge Management for All Tool (KMAT)" />
	<meta name="keywords" content="KMAT, user, description, admin, knowledge engineer" />
	<meta name="author" content="KMAT Team" />
        
        <jsp:include page="../../includes/link.jsp" /> 
        <title>Add a new user</title>     
             
        
    </head>
    
    <body class = "add-user">
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
                        Add a new user
                            <!--<span class="border"></span>-->
                        <!--</h1>-->
                    </div>                        
                </aside>
                <!-- END: Page heading-->
                
                <!-- START: Page contents -->
                <div class="container">
                    <!-- START: Page contents main row -->
                    <div class="row">
                        <!-- START: Add user form area -->
                        <div class="col-md-10 col-md-push-2">
                            <br/>
                            <form name = "signup" method="post" action="InsertUserController" onSubmit="return verifyEmail()">
								<!--To add hidden variable: hidden with value:AddUser-->
                                <input type="hidden" name="hidden" value="AddUser">
                                <!-- Includes Sign Up Form -->
                                <jsp:include page="../../includes/signup-form.jsp" />
                            </form>
                        </div>
                        <!-- END: Add user form area -->
                        <!-- START: Side bar-->
                        <div class="col-md-2 col-md-pull-10 back-color" >
                            <%-- <jsp:include page="includes/verticalUserMenu.jsp" />  --%>
                            <!-- Left side bar includes here -->
                            <jsp:include page="../../includes/left-sidebar.jsp" />
                        </div>
                        <!-- END: Side bar -->
                    </div>
                    <!-- START: Page contents main row -->
                </div>
                <!-- END: Page contents -->
               
                <!-- adds js -->                
                <jsp:include page="../../includes/js.jsp" /> 
                <script src="js/signup-verifications.js"></script>  
                
                <!-- footer.jspf integrates here -->