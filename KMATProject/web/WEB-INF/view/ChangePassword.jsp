<%-- 
    Document   : ChangePassword
    Created on : Jan 16, 2017, 1:58:01 PM
    Author     : Habiba Saim
--%>

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
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">	
	<meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Logged in user password change option">
        <meta name="keywords" content="old, new, password">
        <meta name="author" content="KMAT Team">
        
         <!-- css includes here -->
        <jsp:include page="../../includes/link.jsp" />
        
        <title>Change Password</title>
    </head>
    
    <body class = "change-password">
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
                       Change Password                            
                    </div>                        
                </aside>
                <!-- END: Page heading-->
                
                <div class="container">
                    <div class="row">
                        <div class="col-md-10 col-md-push-2">
                        </div>
                        <!-- START: Left side bar -->
                        <div class="col-md-2 col-md-pull-10 back-color" >
                            <div class="sidebox" id = "navbar">
                                <!--<h3 class="sidebox-lead">KMAT Components</h3>	-->
                                <ul class="sidebox-menu">
                                    <li class = "edit-profile selected" >
                                        <a href="my-profile">
                                            <div class="sidebox-menu-blurb-sub">
                                                Edit Profile
                                            </div>
                                        </a>
                                    </li>
                                
                                    <li class = "change-password">
                                        <a href = "change-password">
                                            <div class="sidebox-menu-blurb-sub">
                                                Change Password
                                            </div>
                                        </a>
                                    </li>
                                    <li>
                                        <div class="extend-sidebar"> <!-- Spaces are added here to stretch the sidebar -->
                                            <p></p>
                                        </div>
                                    </li>
                                </ul>
                            </div>                            
                        </div>         
                        <!-- END: Left side bar -->
                    </div>
                </div>
                          
                <!-- adds js -->                
                <jsp:include page="../../includes/js.jsp" /> 
                
                <!-- footer.jspf integrates here -->


        
    
